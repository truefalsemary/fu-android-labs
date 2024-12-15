package com.example.lab8.ui.new_group;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class NewGroupViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public NewGroupViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is new group fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}