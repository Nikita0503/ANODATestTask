package com.example.anodatesttask.presenters;

import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.StyleSpan;
import android.util.Log;

import com.example.anodatesttask.BaseContract;
import com.example.anodatesttask.MainActivity;
import com.example.anodatesttask.model.DataProvider;
import com.example.anodatesttask.model.data.Content;
import com.example.anodatesttask.model.data.DataObject;
import com.example.anodatesttask.model.data.Post;
import com.example.anodatesttask.model.data.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class MainPresenter implements BaseContract.Presenter {

    private DataProvider mDataProvider;
    private CompositeDisposable mDisposables;
    private MainActivity mActivity;

    public MainPresenter(MainActivity activity) {
        mActivity = activity;
    }

    @Override
    public void onStart() {
        mDataProvider = new DataProvider(mActivity.getApplicationContext());
        mDisposables = new CompositeDisposable();
    }

    public void fetchData(){
        Disposable disposableData = mDataProvider
                .getData
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<DataObject>() {
                    @Override
                    public void onSuccess(DataObject dataObject) {
                        mActivity.setAuthorName(dataObject.post.userName);
                        mActivity.setAuthorPlace(dataObject.post.place);
                        mActivity.setTimeAgo(dataObject.post.content.date);
                        mActivity.setAuthorAvatar(dataObject.post.userAvatarUrl);
                        mActivity.setUserAvatar(dataObject.user.avatarUrl);
                        mActivity.setHashtags(dataObject.post.content.hashTags);
                        mActivity.setPhotos(dataObject.post.content.photos);
                        mActivity.setLikedBy(getLikedBy(dataObject.post.content.likedBy));
                        mActivity.setDescription(getDescription(dataObject.post.userName, dataObject.post.content.description));
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }
                });
        mDisposables.add(disposableData);
    }

    private SpannableStringBuilder getLikedBy(ArrayList<String> likedBy){
        int start = 8;
        int end = 8;
        String str = new String("Liked By ");
        for(int i = 0; i < likedBy.size(); i++){
            if(i == 2) break;
            if(i == 0) {
                str += likedBy.get(i);
                end += likedBy.get(i).length() + 2;
            }else{
                str += ", " + likedBy.get(i);
                end += likedBy.get(i).length() + 2;
            }
        }
        if(likedBy.size() > 2){
            str += " and " + String.valueOf(likedBy.size() - 2) + " others";
        }
        SpannableStringBuilder sb = new SpannableStringBuilder(str);
        StyleSpan bold = new StyleSpan(android.graphics.Typeface.BOLD);
        sb.setSpan(bold, start, end, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        if(likedBy.size() > 2) {
            StyleSpan boldOthers = new StyleSpan(android.graphics.Typeface.BOLD);
            sb.setSpan(boldOthers, end + 4, str.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        }
        return sb;
    }

    public SpannableStringBuilder getDescription(String userName, String description){
        String str = new String(userName + " " + description);
        SpannableStringBuilder sb = new SpannableStringBuilder(str);
        StyleSpan bold = new StyleSpan(android.graphics.Typeface.BOLD);
        sb.setSpan(bold, 0, userName.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        return sb;
    }

    @Override
    public void onStop() {
        mDisposables.clear();
    }
}
