package com.sheikhimtiaz.pegasusride;

import android.app.ProgressDialog;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonSignin;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView textViewSignup;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().setTitle(R.string.loginActivity_title);
        buttonSignin=(Button) findViewById(R.id.logInButton);
        editTextEmail=(EditText) findViewById(R.id.logInId);
        editTextPassword=(EditText) findViewById(R.id.logInPassword);
        textViewSignup=(TextView) findViewById(R.id.textViewSignup);

        progressDialog= new ProgressDialog(this);
        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() != null){
            finish();
            startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
        }

        buttonSignin.setOnClickListener(this);
        textViewSignup.setOnClickListener(this);
    }


    private void userLogin(){
        String email=editTextEmail.getText().toString().trim();
        String password=editTextPassword.getText().toString().trim();

        if(TextUtils.isEmpty(email))
        {
            Toast.makeText(LoginActivity.this, "Please Enter Email!", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(password))
        {
            Toast.makeText(LoginActivity.this, "Please Enter Password!", Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("Logging you in.Please wait.");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if(task.isSuccessful())
                        {
                            finish();
                            startActivity(new Intent(getApplicationContext(), ScrollingActivity.class));
                        }
                        else
                        {
                            Toast.makeText(LoginActivity.this, "Log In Failed! Try Again.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    @Override
    public void onClick(View view){
        if(view == buttonSignin)
        {
            userLogin();
        }
        if(view == textViewSignup)
        {
            finish();
            startActivity(new Intent(LoginActivity.this, RegistrationActivity.class) );
        }
    }

}
