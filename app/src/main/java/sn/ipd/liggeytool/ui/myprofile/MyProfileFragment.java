package sn.ipd.liggeytool.ui.myprofile;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import sn.ipd.liggeytool.HttpParse2;
import sn.ipd.liggeytool.R;

import static android.content.ContentValues.TAG;
import static sn.ipd.liggeytool.LoginActivity.USER_EMAIL;

public class MyProfileFragment extends Fragment {
    HttpParse2 httpParse = new HttpParse2();
    ProgressDialog pDialog;


    String HttpURLUpdate = "http://192.168.43.139/liggeytool/UpdateProfile.php";
    // Http Url For Filter Student Data from Id Sent from previous activity.

    String HttpURLSelect = "http://192.168.43.139/liggeytool/SelectProfile.php";

    String finalResult;
    HashMap<String, String> hashMap = new HashMap<>();
    String ParseResult;
    HashMap<String, String> ResultHash = new HashMap<>();
    String FinalJSonObject;
    Boolean CheckEditText;


    EditText firstname, lastname, password, edtEmail;
    String s_firstname, s_lastname, s_password,s_nouveau_email;
    Button editerProfil;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View root = inflater.inflate(R.layout.fragment_myprofile, container, false);

        firstname = root.findViewById(R.id.editTextF_Name);
        lastname = root.findViewById(R.id.editTextL_Name);
        password = root.findViewById(R.id.editTextPassword);
        edtEmail = root.findViewById(R.id.editTextEmail);

        editerProfil = root.findViewById(R.id.editer_profil);


        //Calling method to filter Student Record and open selected record.
        ActiverChamps();
        edtEmail.setText(USER_EMAIL);
        System.out.println(edtEmail.getText());

        HttpWebCall(USER_EMAIL);
        DesactiverChamps();
        editerProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editerProfil.getText().equals("Editer Profil")) {
                    ActiverChamps();

                } else {
                    CheckEditTextIsEmptyOrNot();
                    if (CheckEditText) {

                        ProfileRecordUpdate(s_firstname, s_lastname, s_password,s_nouveau_email, USER_EMAIL);
                        System.out.println(s_firstname+ s_lastname+ s_password+ s_nouveau_email+USER_EMAIL);
                        USER_EMAIL=s_nouveau_email;
                    } else {

                        Toast.makeText(getContext(), "Veuillez remplir tous les champs !", Toast.LENGTH_LONG).show();
                        Log.i(TAG, "Tous les chmaps ne sont pas remplis");
                    }
                    DesactiverChamps();
                }
            }
        });

        return root;
    }


    //Method to show current record Current Selected Record
    public void HttpWebCall(final String email) {

        class HttpWebCallFunction extends AsyncTask<String, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                pDialog = ProgressDialog.show(getActivity(), "Chargement des données...", null, true, true);
            }

            @Override
            protected void onPostExecute(String httpResponseMsg) {

                super.onPostExecute(httpResponseMsg);
                System.out.println(httpResponseMsg);
                pDialog.dismiss();

                //Storing Complete JSon Object into String Variable.
                FinalJSonObject = httpResponseMsg;
                System.out.println(FinalJSonObject);
                //Parsing the Stored JSOn String to GetHttpResponse Method.
                new GetHttpResponse(getActivity()).execute();

            }

            @Override
            protected String doInBackground(String... params) {

                ResultHash.put("email", params[0]);

                ParseResult = httpParse.postRequest(ResultHash, HttpURLSelect);
                System.out.println(ParseResult);

                return ParseResult;
            }
        }

        HttpWebCallFunction httpWebCallFunction = new HttpWebCallFunction();

        httpWebCallFunction.execute(email);
    }


    // Parsing Complete JSON Object.
    private class GetHttpResponse extends AsyncTask<Void, Void, Void> {
        public Context context;

        public GetHttpResponse(Context context) {
            this.context = context;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            try {
                if (FinalJSonObject != null) {
                    //  JSONArray jsonArray = null;

                    try {
                        JSONArray jsonArray = new JSONArray(FinalJSonObject);

                        JSONObject obj = null;

                        for (int i = 0; i < jsonArray.length(); i++) {
                            obj = jsonArray.getJSONObject(0);

                            s_firstname = obj.getString("user_firstname").toString();
                            USER_EMAIL = obj.getString("user_email").toString();
                            s_lastname = obj.getString("user_lastname").toString();
                            s_password = obj.getString("user_password").toString();
                        }
                    } catch (JSONException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {

            // Setting into EditTexts after done all process .
            firstname.setText(s_firstname);
            lastname.setText(s_lastname);
            password.setText(s_password);
            edtEmail.setText(USER_EMAIL);


        }
    }


    // Method to Update Student Record.
    public void ProfileRecordUpdate(final String firstname, final String lastname, final String password, final
    String nouveau_email, final String ancien_email) {

        class CVRecordUpdateClass extends AsyncTask<String, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                pDialog = ProgressDialog.show(getActivity(), "Enregistrement des données ...", null, true, true);
            }

            @Override
            protected void onPostExecute(String httpResponseMsg) {

                super.onPostExecute(httpResponseMsg);

                pDialog.dismiss();

                Toast.makeText(getActivity(), httpResponseMsg.toString(), Toast.LENGTH_LONG).show();

            }

            @Override
            protected String doInBackground(String... params) {

                hashMap.put("firstname", params[0]);
                hashMap.put("lastname", params[1]);
                hashMap.put("password", params[2]);
                hashMap.put("nemail", params[3]);
                hashMap.put("anemail", params[4]);


                finalResult = httpParse.postRequest(hashMap, HttpURLUpdate);


                return finalResult;
            }
        }

        CVRecordUpdateClass cvRecordUpdateClass = new CVRecordUpdateClass();

        cvRecordUpdateClass.execute(firstname,lastname,password,nouveau_email,ancien_email);
    }

    private void DesactiverChamps() {
        lastname.setEnabled(false);
        password.setEnabled(false);
        firstname.setEnabled(false);
        edtEmail.setEnabled(false);

        editerProfil.setText("Editer Profil");
    }

    private void ActiverChamps() {
        lastname.setEnabled(true);
        password.setEnabled(true);
        firstname.setEnabled(true);
        edtEmail.setEnabled(true);
        editerProfil.setText("Enregistrer");
    }

    public void CheckEditTextIsEmptyOrNot() {

        s_firstname = firstname.getText().toString();
        s_lastname = lastname.getText().toString();
        s_password = password.getText().toString();
        s_nouveau_email = edtEmail.getText().toString();


        if (TextUtils.isEmpty(s_firstname) || TextUtils.isEmpty(s_lastname) || TextUtils.isEmpty(s_password)
                || TextUtils.isEmpty(s_nouveau_email)) {
            CheckEditText = false;
            System.out.println("L'un d'entre eux est vide !");
        } else {
            System.out.println("Elles ne sont pas vides !");
            CheckEditText = true;

        }
    }
}