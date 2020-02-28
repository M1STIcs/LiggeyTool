package sn.ipd.liggeytool.ui.cvtheque;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CVThequeViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public CVThequeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is send fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}