package sn.ipd.liggeytool.ui.myjobs;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MyJobsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public MyJobsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is slideshow fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}