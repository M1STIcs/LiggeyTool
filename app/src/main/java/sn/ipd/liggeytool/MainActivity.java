package sn.ipd.liggeytool;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    Button register, log_in;
    EditText First_Name, Last_Name, Email, Password;
    RadioGroup Type_Compte;
    RadioButton Type_Compte_Choisi;
    String F_Name_Holder, L_Name_Holder, Email_Holder, Password_Holder, T_Compte_Holder;
    String finalResult;
    // String HttpURL = "https://localhost/liggeytool/Registration.php";
    String HttpURL = "http://192.168.43.139/liggeytool/Registration.php";
    Boolean CheckEditText;
    ProgressDialog progressDialog;
    HashMap<String, String> hashMap = new HashMap<>();
    HttpParse httpParse = new HttpParse();


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Assign Id'S
        First_Name = (EditText) findViewById(R.id.editTextF_Name);
        Last_Name = (EditText) findViewById(R.id.editTextL_Name);
        Email = (EditText) findViewById(R.id.editTextEmail);
        Password = (EditText) findViewById(R.id.editTextPassword);
        Type_Compte = (RadioGroup) findViewById(R.id.radioGroupT_Compte);


        register = (Button) findViewById(R.id.buttonRegister);
        log_in = (Button) findViewById(R.id.buttonLogin);

        //Adding Click Listener on button.
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Checking whether EditText is Empty or Not
                CheckEditTextIsEmptyOrNot();

                if (CheckEditText) {

                    // If EditText is not empty and CheckEditText = True then this block will execute.
                    Type_Compte_Choisi=(RadioButton)findViewById(Type_Compte.getCheckedRadioButtonId());
                    T_Compte_Holder=(String)Type_Compte_Choisi.getText();

                    Log.i(TAG,T_Compte_Holder);
                    UserRegisterFunction(F_Name_Holder, L_Name_Holder, Email_Holder, Password_Holder, T_Compte_Holder);

                } else {

                    // If EditText is empty then this block will execute .

                    Toast.makeText(MainActivity.this, "Veuillez remplir tous les champs.", Toast.LENGTH_LONG).show();
                }
            }
        });

        log_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);

            }
        });

    }

    public void CheckEditTextIsEmptyOrNot() {

        F_Name_Holder = First_Name.getText().toString();
        L_Name_Holder = Last_Name.getText().toString();
        Email_Holder = Email.getText().toString();
        Password_Holder = Password.getText().toString();

        if (TextUtils.isEmpty(F_Name_Holder) || TextUtils.isEmpty(L_Name_Holder) || TextUtils.isEmpty(Email_Holder) || TextUtils.isEmpty(Password_Holder)) {

            CheckEditText = false;

        } else {

            CheckEditText = true;
        }

    }

    public void UserRegisterFunction(final String F_Name, final String L_Name, final String email, final String password, final String T_Compte) {

        class UserRegisterFunctionClass extends AsyncTask<String, Void, String> {

            protected void onPreExecute() {
                super.onPreExecute();
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(register.getWindowToken(), 0);

                progressDialog = ProgressDialog.show(MainActivity.this, "Chargement des données", null, true, true);
            }

            protected void onPostExecute(String httpResponseMsg) {

                super.onPostExecute(httpResponseMsg);

                //   httpResponseMsg=httpParse.FinalHttpData;
                progressDialog.dismiss();
                Toast toast=Toast.makeText(MainActivity.this,"",Toast.LENGTH_LONG);
                toast.show();
                toast.setText(httpResponseMsg);
                //Toast.makeText(MainActivity.this, httpResponseMsg, Toast.LENGTH_LONG).show();
                //Ajouter Résultat Inscription dans le logcat
                Log.i(TAG, httpResponseMsg);
               /* try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                }*/
                toast.setText("Vous allez être rediriger vers la page de connexion !");

                //Si l'inscription est réussie Alors :
                if (httpResponseMsg.equals("Inscription réussie !")) {

                    //Redirection vers la page Login
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                    Log.i(TAG, "Redirection vers la page Login");
                }
            }

            @Override
            protected String doInBackground(String... params) {
                hashMap.put("f_name", params[0]);
                hashMap.put("l_name", params[1]);
                hashMap.put("email", params[2]);
                hashMap.put("password", params[3]);
                hashMap.put("t_compte", params[4]);
                finalResult = httpParse.postRequest(hashMap, HttpURL);
                return finalResult;
            }
        }
        UserRegisterFunctionClass userRegisterFunctionClass = new UserRegisterFunctionClass();
        userRegisterFunctionClass.execute(F_Name, L_Name, email, password, T_Compte);
    }
}
