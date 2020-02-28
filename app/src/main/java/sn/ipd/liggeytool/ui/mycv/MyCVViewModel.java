package sn.ipd.liggeytool.ui.mycv;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MyCVViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public MyCVViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is tools fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}