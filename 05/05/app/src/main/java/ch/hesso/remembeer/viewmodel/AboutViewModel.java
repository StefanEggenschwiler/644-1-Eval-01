package ch.hesso.remembeer.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class  AboutViewModel{

    private MutableLiveData<String> mText;

    public AboutViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is about activity");
    }

    public LiveData<String> getText() {
        return mText;
    }
}

