package com.example.lab8.ui.create_channel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CreateChannelViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public CreateChannelViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}