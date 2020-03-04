package sn.ipd.liggeytool.ui.help;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.google.android.material.tabs.TabLayout;

import sn.ipd.liggeytool.R;

public class HelpFragment extends Fragment {

    String txtHelp1, txtHelp2;
    TextView tvHelp;
    TabLayout tabHelp;
    public static HelpFragment newInstance() {
        return new HelpFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View root =inflater.inflate(R.layout.fragment_help, container, false);

        tvHelp=root.findViewById(R.id.tvHelp);
        txtHelp1="LiggeyTool 1.0. vous propose 2 types de compte à l'inscription.";

        tvHelp.setText(Html.fromHtml(txtHelp1));

     //   tabHelp=root.findViewById(R.id.tabHelp);
      //  tabHelp.onTab
// 1 - Get ViewPager from layout
        ViewPager pager = root.findViewById(R.id.viewPager);
        // 2 - Set Adapter PageAdapter and glue it together
        pager.setAdapter(new PageAdapter(getChildFragmentManager(), getResources().getIntArray(R.array.colorPagesViewPager)) {
        });
        //  1 - Get TabLayout from layout
        tabHelp= root.findViewById(R.id.tabs);
        // 2 - Glue TabLayout and ViewPager together
        tabHelp.setupWithViewPager(pager);
        // 3 - Design purpose. Tabs have the same width
        tabHelp.setTabMode(TabLayout.MODE_FIXED);
       // tabHelp.getTabAt(0).setIcon(R.drawable.ic_restaurant);
        //tabHelp.getTabAt(1).setIcon(R.drawable.ic_glass_and_bottle_of_wine);

        //Set Adapter PageAdapter and glue it together
          return root;
    }


}
