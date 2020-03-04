package sn.ipd.liggeytool.ui.help;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import sn.ipd.liggeytool.R;


public class HelpRecruiterFragment extends Fragment {

    // 1 - Create keys for our Bundle
    private static final String KEY_POSITION = "position";
    private static final String KEY_COLOR = "color";


    public HelpRecruiterFragment() {
    }


    // 2 - Method that will create a new instance of PageFragment, and add data to its bundle.
    public static HelpRecruiterFragment newInstance(int position, int color) {

        // 2.1 Create new fragment
        HelpRecruiterFragment frag = new HelpRecruiterFragment();

        // 2.2 Create bundle and add it some data
        Bundle args = new Bundle();
        args.putInt(KEY_POSITION, position);
        args.putInt(KEY_COLOR, color);
        frag.setArguments(args);

        return (frag);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // 3 - Get layout of PageFragment
        View result = inflater.inflate(R.layout.fragment_help_job_seeker, container, false);

        // 4 - Get widgets from layout and serialise it
        LinearLayout rootView = (LinearLayout) result.findViewById(R.id.fragment_page_rootview);
        TextView textView = (TextView) result.findViewById(R.id.fragment_page_title);

        // 5 - Get data from Bundle (created in method newInstance)
        int position = getArguments().getInt(KEY_POSITION, -1);
        int color = getArguments().getInt(KEY_COLOR, -1);

        // 6 - Update widgets with it
        rootView.setBackgroundColor(color);
        String txtHelp = "Si vous choisi le profil Recruteur, vous avez le menu avec les éléments suivants :<br>" +
                "- Accueil : Liste toutes les annonces publiées par tout le monde. Vous pouvez avoir les détails de chaque publication en cliquant dessus." +
                "Vous pouvez effectuer également une recherche<br>" +
                "- Mon profil : Affiche vos informations de compte que vous pouvez modifier à tout moment " +
                "en cliquant sur le bouton \"Modifier mon compte\"<br>" +
                "- Mes annonces : Liste toutes vos annonces que vous avez déjà publiées et permet de créer" +
                " une nouvelle annonce grâce au bouton \"Nouveau job\"<br>" +
                "- CVThèque : Liste l'ensemble des CVs des candidats inscrits.<br>";
        textView.setText(Html.fromHtml(txtHelp));

        Log.e(getClass().getSimpleName(), "onCreateView called for fragment number " + position);

        return result;
    }
}