package sn.ipd.liggeytool.ui.myjobs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import sn.ipd.liggeytool.R;

public class MyJobsFragment extends Fragment {

    private MyJobsViewModel myJobsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        myJobsViewModel =
                ViewModelProviders.of(this).get(MyJobsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_myjobs, container, false);
        final TextView textView = root.findViewById(R.id.text_slideshow);
        myJobsViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}