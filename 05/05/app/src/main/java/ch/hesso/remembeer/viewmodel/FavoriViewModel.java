package ch.hesso.remembeer.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class FavoriViewModel {

    private MutableLiveData<String> mText;

    public FavoriViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Favori activity");
    }

    public LiveData<String> getText() {
        return mText;
    }

}
