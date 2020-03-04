package sn.ipd.liggeytool.ui.help;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class PageAdapter extends FragmentPagerAdapter {

    //Array of colors that will be passed to PageFragment
    private int[] colors;

    //Default Constructor
    public PageAdapter(FragmentManager mgr, int[] colors) {
        super(mgr);
        this.colors = colors;
    }


    @Override
    public int getCount() {
        return(2);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: //Page number 1
                return HelpRecruiterFragment.newInstance(position,this.colors[position]);
            case 1: //Page number 2
                return HelpJobSeekerFragment.newInstance(position,this.colors[position]);
            default:
                return null;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0: //Page number 1
                return "Recruteur";
            case 1: //Page number 2
                return "Candidat";
            default:
                return null;
        }
    }
}