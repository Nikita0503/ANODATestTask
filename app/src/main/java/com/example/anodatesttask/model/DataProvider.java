package com.example.anodatesttask.model;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import com.example.anodatesttask.model.data.Content;
import com.example.anodatesttask.model.data.DataObject;
import com.example.anodatesttask.model.data.Post;
import com.example.anodatesttask.model.data.User;
import com.example.anodatesttask.presenters.MainPresenter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;

public class DataProvider {

    private Context mContext;


    public DataProvider(Context context){
        mContext = context;
    }

    public Single<DataObject> getData = Single.create(new SingleOnSubscribe<DataObject>() {
        @Override
        public void subscribe(SingleEmitter<DataObject> e) throws Exception {
            try {
                DataObject dataObject = new DataObject();
                JSONObject jsonObject = new JSONObject(getJSONString());

                JSONObject jsonObjectUser = jsonObject.getJSONObject("user");
                User user = new User();
                user.avatarUrl = jsonObjectUser.getString("avatarUrl");

                JSONObject jsonObjectPost = jsonObject.getJSONObject("post");
                Post post = new Post();
                post.userAvatarUrl = jsonObjectPost.getString("userAvatarUrl");
                post.userName = jsonObjectPost.getString("userName");
                post.place = jsonObjectPost.getString("place");

                Content content = new Content();
                JSONObject jsonObjectContent = jsonObjectPost.getJSONObject("content");
                content.description = jsonObjectContent.getString("description");
                JSONArray jsonArrayLikedBy = jsonObjectContent.getJSONArray("likedBy");
                for (int i = 0; i < jsonArrayLikedBy.length(); i++) {
                    content.likedBy.add(jsonArrayLikedBy.getString(i));
                }
                content.date = jsonObjectContent.getString("date");
                JSONArray jsonArrayPhotos = jsonObjectContent.getJSONArray("photos");
                for (int i = 0; i < jsonArrayPhotos.length(); i++) {
                    content.photos.add(jsonArrayPhotos.getString(i));
                }
                JSONArray jsonArrayHashTags = jsonObjectContent.getJSONArray("hashTags");
                for (int i = 0; i < jsonArrayHashTags.length(); i++) {
                    content.hashTags.add(jsonArrayHashTags.get(i).toString());
                }
                post.content = content;
                dataObject.user = user;
                dataObject.post = post;
                e.onSuccess(dataObject);
            } catch (Exception c){
                c.printStackTrace();
            }
        }
    });

    private String getJSONString(){
        String text = "object2.json";
        byte[] buffer = null;
        InputStream is;
        try {
            is = mContext.getAssets().open(text);
            int size = is.available();
            buffer = new byte[size];
            is.read(buffer);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String strData = new String(buffer);
        Log.d("JSON", strData);
        return strData;
    }

}
