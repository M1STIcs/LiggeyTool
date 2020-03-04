package sn.ipd.liggeytool.ui.about;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import sn.ipd.liggeytool.R;

public class AboutFragment extends Fragment {

    private AboutViewModel mViewModel;
    String dataProtection, dataContent, data_contact;
    TextView tvDataProtection, tvDataContent, tvDataContact;

    public static AboutFragment newInstance() {
        return new AboutFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root=inflater.inflate(R.layout.fragment_about, container, false);

        //Remplissage de la 1ere partie
        dataProtection="Membre actif d'Internet Society (ISOC), Riz2ARC lutte activement pour la protection de vos données personnelles. " +
                "Vos données ne sont ni exposées ni vendues et vous avez pleinement le droit de demander une suppression définitive." +
                "Nous vous remercions de votre confiance que nous tenons tant à mériter.";
        tvDataProtection=root.findViewById(R.id.data_protection);
        tvDataProtection.setText(Html.fromHtml(dataProtection));

        //Remplissage de la 2nde partie
        dataContent="L'âge minimum recommandé pour l'utilisation de cette application est de 15 ans conformémemment à législation du " +
                "travail au Sénégal.";
        tvDataContent=root.findViewById(R.id.data_content);
        tvDataContent.setText(Html.fromHtml(dataContent));

        //Remplissage de la 3eme partie
        data_contact="Contactez-nous au 77 710 12 49 ou envoyez un mail à liggeytool@riz2arc.sn";
        tvDataContact=root.findViewById(R.id.data_contact);
        tvDataContact.setText(Html.fromHtml(data_contact));
        tvDataContact.setSelected(true);

        return root;

    }
}
