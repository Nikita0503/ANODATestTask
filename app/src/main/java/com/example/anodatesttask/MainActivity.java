package com.example.anodatesttask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.viewpagerdots.DotsIndicator;
import com.bumptech.glide.Glide;
import com.example.anodatesttask.adapters.PhotoPagerAdapter;
import com.example.anodatesttask.model.data.DataObject;
import com.example.anodatesttask.presenters.MainPresenter;
import com.google.android.material.tabs.TabLayout;
import com.greenfrvr.hashtagview.HashtagView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity implements BaseContract.View {

    private MainPresenter mPresenter;
    private PhotoPagerAdapter mPhotoAdapter;
    private TextView mTextViewAuthorName;
    private TextView mTextViewAuthorPlace;
    private TextView mTextViewLikedBy;
    private TextView mTextViewDescription;
    private TextView mTextViewTimeAgo;
    private CircleImageView mCircleImageViewAuthorAvatar;
    private CircleImageView mCircleImageViewUserAvatar;
    private HashtagView mHashtagView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        mPresenter = new MainPresenter(this);
        mPresenter.onStart();
        mPresenter.fetchData();
    }

    @Override
    public void initViews(){
        mTextViewAuthorName = (TextView) findViewById(R.id.textviewAuthorName);
        mTextViewAuthorPlace = findViewById(R.id.textviewAuthorPlace);
        mTextViewLikedBy = findViewById(R.id.textViewLikedBy);
        mTextViewLikedBy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Открыть список тех, кто лайкнул", Toast.LENGTH_SHORT).show();
            }
        });
        mTextViewDescription = findViewById(R.id.textviewNameAndSubscription);
        mTextViewTimeAgo = findViewById(R.id.textviewTimeAgo);
        ImageView imageViewPhoto = findViewById(R.id.imageviewPhoto);
        imageViewPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Сделать фото", Toast.LENGTH_SHORT).show();
            }
        });
        ImageView imageViewDirect = findViewById(R.id.imageviewDirect);
        imageViewDirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Открыть директ", Toast.LENGTH_SHORT).show();
            }
        });
        ImageView imageViewLike = findViewById(R.id.imageviewLike);
        imageViewLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Поставить лайк", Toast.LENGTH_SHORT).show();
            }
        });
        ImageView imageViewComments = findViewById(R.id.imageviewComments);
        imageViewComments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Посмотреть комментарии", Toast.LENGTH_SHORT).show();
            }
        });
        ImageView imageViewChat = findViewById(R.id.imageviewChat);
        imageViewChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Начать чат", Toast.LENGTH_SHORT).show();
            }
        });
        ImageView imageViewFavorite = findViewById(R.id.imageviewFavorite);
        imageViewFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Добавить в лучшее (наверное)", Toast.LENGTH_SHORT).show();
            }
        });
        ImageView imageViewHome = findViewById(R.id.imageViewHome);
        imageViewHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Домой", Toast.LENGTH_SHORT).show();
            }
        });
        ImageView imageViewSearch = findViewById(R.id.imageViewSearch);
        imageViewSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Поиск", Toast.LENGTH_SHORT).show();
            }
        });
        ImageView imageViewPlus = findViewById(R.id.imageViewPlus);
        imageViewPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Не знаю что за кнопка, не пользуюсь Instagram)", Toast.LENGTH_SHORT).show();
            }
        });
        ImageView imageViewLikes = findViewById(R.id.imageViewLikes);
        imageViewLikes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Лайки", Toast.LENGTH_SHORT).show();
            }
        });
        mCircleImageViewAuthorAvatar = findViewById(R.id.imageviewAuthorAvatar);
        mCircleImageViewAuthorAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Открыть профиль автора", Toast.LENGTH_SHORT).show();
            }
        });
        mCircleImageViewUserAvatar = findViewById(R.id.imageViewUserAvatar);
        mCircleImageViewUserAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Открыть профиль", Toast.LENGTH_SHORT).show();
            }
        });
        mHashtagView = findViewById(R.id.hashtagView);
        mHashtagView.addOnTagClickListener(new HashtagView.TagsClickListener() {
            @Override
            public void onItemClicked(Object item) {
                Toast.makeText(getApplicationContext(), item.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        mPhotoAdapter = new PhotoPagerAdapter(getApplicationContext());
        ViewPager viewPager = findViewById(R.id.viewpagerPhotos);
        viewPager.setAdapter(mPhotoAdapter);
        TabLayout dotsIndicator = findViewById(R.id.dotsIndicator);
        dotsIndicator.setupWithViewPager(viewPager, true);
    }

    public void setAuthorName(String authorName){
        mTextViewAuthorName.setText(authorName);
    }

    public void setAuthorPlace(String AuthorPlace){
        mTextViewAuthorPlace.setText(AuthorPlace);
    }

    public void setTimeAgo(String timeAgo){
        mTextViewTimeAgo.setText(timeAgo);
    }

    public void setAuthorAvatar(String authorAvatar){
        Glide
                .with(getApplicationContext())
                .load(authorAvatar)
                .into(mCircleImageViewAuthorAvatar);
    }

    public void setUserAvatar(String userAvatar){
        Glide
                .with(getApplicationContext())
                .load(userAvatar)
                .into(mCircleImageViewUserAvatar);
    }

    public void setHashtags(ArrayList<String> hashtags){
        mHashtagView.setData(hashtags);
    }

    public void setPhotos(ArrayList<String> photos){
        mPhotoAdapter.addPhotos(photos);
    }

    public void setLikedBy(SpannableStringBuilder likedBy){
        mTextViewLikedBy.setText(likedBy);
    }

    public void setDescription(SpannableStringBuilder description){
        mTextViewDescription.setText(description);
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        mPresenter.onStop();
    }
}
