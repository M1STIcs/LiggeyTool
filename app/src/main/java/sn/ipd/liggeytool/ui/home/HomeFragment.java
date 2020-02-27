package sn.ipd.liggeytool.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import sn.ipd.liggeytool.DashboardActivity;
import sn.ipd.liggeytool.LoginActivity;
import sn.ipd.liggeytool.R;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    Button LogOut;
    TextView EmailShow;
    String EmailHolder;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
      //  super.onCreate(savedInstanceState);

        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
       /* final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/

        LogOut = (Button) root.findViewById(R.id.button);
        EmailShow =(TextView) root.findViewById(R.id.EmailShow);


      //  Intent intent = getIntent();
       // EmailHolder = intent.getStringExtra(LoginActivity.UserEmail);
        // EmailShow.setText(EmailHolder);
        EmailShow.setText("coucou");


     /*   LogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();

                Intent intent = new Intent(getActivity(), LoginActivity.class);

                startActivity(intent);

                Toast.makeText(DashboardActivity.this, "Log Out Successfully", Toast.LENGTH_LONG).show();


            }
        })*/
        return root;
    }

  }