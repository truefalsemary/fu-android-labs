package com.example.lab8.ui.about_app;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AboutAppViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public AboutAppViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}