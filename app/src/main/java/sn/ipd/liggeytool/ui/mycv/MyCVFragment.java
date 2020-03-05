package sn.ipd.liggeytool.ui.mycv;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import sn.ipd.liggeytool.HttpParse;
import sn.ipd.liggeytool.HttpParse2;
import sn.ipd.liggeytool.JobSeekerActivity;
import sn.ipd.liggeytool.LoginActivity;
import sn.ipd.liggeytool.R;
import sn.ipd.liggeytool.RecruiterActivity;
import sn.ipd.liggeytool.ui.home.HomeFragment;

import static android.content.ContentValues.TAG;
import static sn.ipd.liggeytool.LoginActivity.USER_EMAIL;
import static sn.ipd.liggeytool.SplashActivity.adresseIP;

public class MyCVFragment extends Fragment {
    HttpParse2 httpParse = new HttpParse2();
    ProgressDialog pDialog;


    String HttpURLUpdate = "http://"+adresseIP+"/liggeytool/UpdateCV.php";
    // Http Url For Filter Student Data from Id Sent from previous activity.

    String HttpURLSelect = "http://"+adresseIP+"/liggeytool/SelectCV.php";

    String finalResult ;
    HashMap<String,String> hashMap = new HashMap<>();
    String ParseResult ;
    HashMap<String,String> ResultHash = new HashMap<>();
    String FinalJSonObject ;
    Boolean CheckEditText;

    EditText userName, userAge,userAddress,userEmail, userPhone;
    EditText userCompetences, userExperiences,userFormation,userLangues,userLoisirs;
    String s_userName, s_userAge,s_userAddress,s_userEmail, s_userPhone;
    String s_userCompetences, s_userExperiences,s_userFormation,s_userLangues,s_userLoisirs;
    Button editerCV;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View root = inflater.inflate(R.layout.fragment_mycv, container, false);

        userName= root.findViewById(R.id.user_name);
        userAge= root.findViewById(R.id.user_age);
        userAddress= root.findViewById(R.id.user_address);
        userEmail= root.findViewById(R.id.user_email);
        userPhone= root.findViewById(R.id.user_phone);
        userCompetences= root.findViewById(R.id.user_competences);
        userExperiences= root.findViewById(R.id.user_experiences);
        userFormation= root.findViewById(R.id.user_formation);
        userLangues= root.findViewById(R.id.user_langues);
        userLoisirs= root.findViewById(R.id.user_loisirs);
        editerCV=root.findViewById(R.id.editer_cv);

        HttpWebCall(USER_EMAIL);

        userEmail.setText(USER_EMAIL);
        userName.setEnabled(false);
        userEmail.setEnabled(false);
        DesactiverChamps();
        editerCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editerCV.getText().equals("Editer CV")){
                    ActiverChamps();

                }else {
                    CheckEditTextIsEmptyOrNot();
                    if (CheckEditText) {

                        CVRecordUpdate(s_userEmail,s_userAge,s_userAddress,s_userPhone,s_userCompetences,
                                s_userExperiences,s_userFormation,s_userLangues,s_userLoisirs);

                    } else {

                        Toast.makeText(getContext(), "Veuillez remplir tous les champs !", Toast.LENGTH_LONG).show();
                        Log.i(TAG,"Tous les chmaps ne sont pas remplis");
                    }
                    DesactiverChamps();
                }
            }
        });

        return root;
    }


    //Method to show current record Current Selected Record
    public void HttpWebCall(final String email){

        class HttpWebCallFunction extends AsyncTask<String,Void,String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                pDialog = ProgressDialog.show(getActivity(),"Chargement des données...",null,true,true);
            }

            @Override
            protected void onPostExecute(String httpResponseMsg) {

                super.onPostExecute(httpResponseMsg);
                System.out.println(httpResponseMsg);
                pDialog.dismiss();

                //Storing Complete JSon Object into String Variable.
                FinalJSonObject = httpResponseMsg ;
                System.out.println(FinalJSonObject);
                //Parsing the Stored JSOn String to GetHttpResponse Method.
                new GetHttpResponse(getActivity()).execute();

            }

            @Override
            protected String doInBackground(String... params) {

                ResultHash.put("email",params[0]);

                ParseResult = httpParse.postRequest(ResultHash, HttpURLSelect);

                return ParseResult;
            }
        }

        HttpWebCallFunction httpWebCallFunction = new HttpWebCallFunction();

        httpWebCallFunction.execute(email);
    }


    // Parsing Complete JSON Object.
    private class GetHttpResponse extends AsyncTask<Void, Void, Void>
    {
        public Context context;

        public GetHttpResponse(Context context)
        {
            this.context = context;
        }

        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... arg0)
        {
            try
            {
                if(FinalJSonObject != null)
                {
                  //  JSONArray jsonArray = null;

                    try {
                    JSONArray  jsonArray = new JSONArray(FinalJSonObject);

                        JSONObject obj=null;

                       for(int i=0; i<jsonArray.length(); i++)
                        {
                            obj = jsonArray.getJSONObject(0);

                            s_userName = obj.getString("cv_name").toString();
                            s_userEmail = obj.getString("cv_email");
                            s_userAge = obj.getString("cv_age");
                            s_userAddress = obj.getString("cv_address");
                            s_userPhone = obj.getString("cv_phone");
                            s_userExperiences = obj.getString("cv_experiences");
                            s_userCompetences = obj.getString("cv_competences");
                            s_userFormation = obj.getString("cv_diplomes");
                            s_userLangues = obj.getString("cv_langues");
                            s_userLoisirs = obj.getString("cv_loisirs");

                        }
                    }
                    catch (JSONException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
            catch (Exception e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result)
        {

            // Setting into EditTexts after done all process .
            userName.setText(s_userName);
            userAge.setText(s_userAge);
            userAddress.setText(s_userAddress);
            userPhone.setText(s_userPhone);
            userCompetences.setText(s_userCompetences);
            userExperiences.setText(s_userExperiences);
            userFormation.setText(s_userFormation);
            userLangues.setText(s_userLangues);
            userLoisirs.setText(s_userLoisirs);

        }
    }


    // Method to Update Student Record.
    public void CVRecordUpdate(final String email,final String age,final String address,final
    String phone,final String competences,final String experiences,final String diplomes,final
    String langues,final String loisirs){

        class CVRecordUpdateClass extends AsyncTask<String,Void,String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                pDialog = ProgressDialog.show(getActivity(),"Enregistrement des données ...",null,true,true);
            }

            @Override
            protected void onPostExecute(String httpResponseMsg) {

                super.onPostExecute(httpResponseMsg);

                pDialog.dismiss();

                Toast.makeText(getActivity(),httpResponseMsg.toString(), Toast.LENGTH_LONG).show();

            }

            @Override
            protected String doInBackground(String... params) {

                hashMap.put("email", params[0]);
                hashMap.put("age", params[1]);
                hashMap.put("address", params[2]);
                hashMap.put("phone", params[3]);
                hashMap.put("competences", params[4]);
                hashMap.put("experiences", params[5]);
                hashMap.put("diplomes", params[6]);
                hashMap.put("langues", params[7]);
                hashMap.put("loisirs", params[8]);

                finalResult = httpParse.postRequest(hashMap, HttpURLUpdate);
                System.out.println(finalResult);

                return finalResult;
            }
        }

        CVRecordUpdateClass cvRecordUpdateClass = new CVRecordUpdateClass();

        cvRecordUpdateClass.execute(email,age,address,phone,competences,experiences,diplomes,langues,loisirs);
    }
            private void DesactiverChamps(){
                userAge.setEnabled(false);
                userAddress.setEnabled(false);
                userPhone.setEnabled(false);
                userCompetences.setEnabled(false);
                userExperiences.setEnabled(false);
                userFormation.setEnabled(false);
                userLangues.setEnabled(false);
                userLoisirs.setEnabled(false);
                editerCV.setText("Editer CV");
            }
            private void ActiverChamps(){
                userAge.setEnabled(true);
                userAddress.setEnabled(true);
                userPhone.setEnabled(true);
                userCompetences.setEnabled(true);
                userExperiences.setEnabled(true);
                userFormation.setEnabled(true);
                userLangues.setEnabled(true);
                userLoisirs.setEnabled(true);
                editerCV.setText("Enregistrer");
            }
    public void CheckEditTextIsEmptyOrNot() {

        s_userName = userName.getText().toString();
        s_userAge =  userAge.getText().toString();
        s_userAddress = userAddress.getText().toString();
        s_userEmail =  userEmail.getText().toString();
        s_userPhone =  userPhone.getText().toString();
        s_userCompetences =  userCompetences.getText().toString();
        s_userExperiences =  userExperiences.getText().toString();
        s_userFormation =  userFormation.getText().toString();
        s_userLangues =  userLangues.getText().toString();
        s_userLoisirs = userLoisirs.getText().toString();

        if (TextUtils.isEmpty(s_userName) || TextUtils.isEmpty(s_userAge) || TextUtils.isEmpty(s_userAddress)
                || TextUtils.isEmpty(s_userEmail) || TextUtils.isEmpty(s_userPhone) || TextUtils.isEmpty(s_userCompetences)
                || TextUtils.isEmpty(s_userExperiences) || TextUtils.isEmpty(s_userFormation) || TextUtils.isEmpty(s_userLangues) ||
                TextUtils.isEmpty(s_userLoisirs)) {
            CheckEditText = false;
        } else {

            CheckEditText = true;
        }
    }
}