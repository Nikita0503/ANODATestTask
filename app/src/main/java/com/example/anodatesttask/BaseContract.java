package com.example.anodatesttask;

public interface BaseContract {
    interface View{
        void initViews();
    }

    interface Presenter{
        void onStart();
        void onStop();
    }
}
