package sn.ipd.liggeytool.ui.cvtheque;

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

public class CVThequeFragment extends Fragment {

    private CVThequeViewModel CVThequeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        CVThequeViewModel =
                ViewModelProviders.of(this).get(CVThequeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_cvtheque, container, false);
        final TextView textView = root.findViewById(R.id.text_send);
        CVThequeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}