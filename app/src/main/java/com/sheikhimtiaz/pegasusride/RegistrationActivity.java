package com.sheikhimtiaz.pegasusride;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;


public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener {


    private Button buttonRegister;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private EditText editTextPasswordVerify;

    private TextView textViewSignin;



    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        getSupportActionBar().setTitle(R.string.regActivity_title);

        firebaseAuth= FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser() != null)
        {
            finish();
            startActivity(new Intent(getApplicationContext(), ScrollingActivity.class));
        }
        progressDialog= new ProgressDialog(RegistrationActivity.this);

        buttonRegister = (Button) findViewById(R.id.buttonRegister);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        editTextPasswordVerify = (EditText) findViewById(R.id.editTextPasswordVerify);
        textViewSignin = (TextView) findViewById(R.id.textViewSignin);
        buttonRegister.setOnClickListener(this);
        textViewSignin.setOnClickListener(this);
    }

    private void registerUser()
    {
        String email=editTextEmail.getText().toString().trim();
        String password=editTextPassword.getText().toString().trim();
        String passwordVerify=editTextPasswordVerify.getText().toString().trim();

        if(TextUtils.isEmpty(email))
        {
            Toast.makeText(RegistrationActivity.this, "Please enter your email!", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(password))
        {
            Toast.makeText(RegistrationActivity.this, "Please enter password!", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(passwordVerify))
        {
            Toast.makeText(RegistrationActivity.this, "Please re-enter your password!", Toast.LENGTH_SHORT).show();
            return;
        }
        if(!password.equals(passwordVerify))
        {
            Toast.makeText(RegistrationActivity.this, "Passwords does not match!", Toast.LENGTH_SHORT).show();
            return;
        }
        progressDialog.setMessage("Registering New User.");
        progressDialog.show();
        firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(RegistrationActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful() ) {
                            //Toast.makeText(RegistrationActivity.this, "Registerd Successfully!", Toast.LENGTH_SHORT).show();
                            finish();
                            startActivity(new Intent(getApplicationContext(), ScrollingActivity.class));
                        } else {
                            Toast.makeText(RegistrationActivity.this, "Could not register at the moment. Try later.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }



    @Override
    public void onClick(View view)
    {
        if(view == buttonRegister)
        {
            registerUser();
        }
        if(view == textViewSignin)
        {
            startActivity(new Intent(RegistrationActivity.this, LoginActivity.class) );
        }
    }
}
