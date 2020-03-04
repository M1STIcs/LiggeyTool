package sn.ipd.liggeytool;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class LoginActivity extends AppCompatActivity {

    public static final String UserEmail = "";
    EditText Email, Password;
    private static final String TAG = "LoginActivity";
    Button register, log_in;
    String PasswordHolder, EmailHolder;
    String finalResult;
    //String HttpURL = "https://localhost/liggeytool/Login.php";
    //songer à changer l'adresse ci-dessous avec l'adresse ip
    String HttpURL = "http://192.168.43.139/liggeytool/Login.php";
    Boolean CheckEditText;
    ProgressDialog progressDialog;
    HashMap<String, String> hashMap = new HashMap<>();
    HttpParse httpParse = new HttpParse();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Email = (EditText) findViewById(R.id.email);
        Password = (EditText) findViewById(R.id.password);
        log_in = (Button) findViewById(R.id.buttonLogin);
        register = (Button) findViewById(R.id.buttonRegister);

        log_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CheckEditTextIsEmptyOrNot();

                if (CheckEditText) {

                    UserLoginFunction(EmailHolder, PasswordHolder);

                } else {

                    Toast.makeText(LoginActivity.this, "Veuillez remplir tous les champs !", Toast.LENGTH_LONG).show();
                    Log.i(TAG,"Tous les chmaps ne sont pas remplis");
                }

            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Si l'utilisateur n'a pas encore de compte, redirection vers page d'Inscription
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                Log.i(TAG,"Ouverture de la page de d'inscription MainActivity");
            }
        });
    }

    public void CheckEditTextIsEmptyOrNot() {

        EmailHolder = Email.getText().toString();
        PasswordHolder = Password.getText().toString();

        if (TextUtils.isEmpty(EmailHolder) || TextUtils.isEmpty(PasswordHolder)) {
            CheckEditText = false;
        } else {

            CheckEditText = true;
        }
    }

    public void UserLoginFunction(final String email, final String password) {

        class UserLoginClass extends AsyncTask<String, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                progressDialog = ProgressDialog.show(LoginActivity.this, "Chargement des données ...", null, true, true);
            }

            @Override
            protected void onPostExecute(String httpResponseMsg) {

                super.onPostExecute(httpResponseMsg);

                progressDialog.dismiss();
                //Si la connexion a réussi Alors :


                if (httpResponseMsg.contains("Connexion réussie !")) {

                    finish();
                    //Nous entrons dans l'application, Bienvenue !
                    Intent intent;
                    //Si le type de compte est Recruteur
                    if (httpResponseMsg.contains("Recruteur")) {
                        intent = new Intent(LoginActivity.this, RecruiterActivity.class);
                        intent.putExtra("USER_EMAIL",EmailHolder);
                        startActivity(intent);
                        //Ajout du mail de l'utilisateur en extra
                       // intent.putExtra(UserEmail, email);
                    }
                    else {
                        intent = new Intent(LoginActivity.this, JobSeekerActivity.class);
                        intent.putExtra("USER_EMAIL",EmailHolder);
                        startActivity(intent);
                    }
//Si le type de compte est Candidat



                } else {

                    Toast.makeText(LoginActivity.this, httpResponseMsg, Toast.LENGTH_LONG).show();
                }

            }

            @Override
            protected String doInBackground(String... params) {

                hashMap.put("email", params[0]);

                hashMap.put("password", params[1]);

                finalResult = httpParse.postRequest(hashMap, HttpURL);
                System.out.println(finalResult);
                return finalResult;
            }
        }

        UserLoginClass userLoginClass = new UserLoginClass();

        userLoginClass.execute(email, password);
    }
}
