package com.projeto.mercadoapp.ui.home;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

public class HomeViewModel extends AndroidViewModel {

    private MutableLiveData<String> mText;

    public HomeViewModel(Application application) {
        super(application);
        mText = new MutableLiveData<>();
        mText.setValue("");
    }

    public MutableLiveData<String> getText() { return mText; }


}