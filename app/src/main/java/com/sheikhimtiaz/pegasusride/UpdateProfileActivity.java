package com.sheikhimtiaz.pegasusride;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class UpdateProfileActivity extends AppCompatActivity {

    private EditText editTextFirstName;
    private EditText editTextLastName;
    private EditText editTextAge;
    private EditText editTextPhoneNumber;
    private EditText editTextHometown;

    private ImageButton imageButtonProfilePhotoUpdate;
    private Button buttonUpdateProfile;

    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);

        getSupportActionBar().setTitle(R.string.updateprofiletittle);

        progressDialog= new ProgressDialog(UpdateProfileActivity.this);


        editTextAge=(EditText) findViewById(R.id.editTextAge);
        editTextFirstName=(EditText) findViewById(R.id.editTextFirstName);
        editTextPhoneNumber=(EditText) findViewById(R.id.editTextPhoneNumber);
        editTextLastName=(EditText) findViewById(R.id.editTextLastName);
        editTextHometown=(EditText) findViewById(R.id.editTextHometown);
        imageButtonProfilePhotoUpdate=(ImageButton) findViewById(R.id.imageButtonProfilePhotoUpdate);

        buttonUpdateProfile=(Button) findViewById(R.id.buttonUpdateProfile);

        buttonUpdateProfile.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(UpdateProfileActivity.this, "Please come back later.", Toast.LENGTH_SHORT).show();
            }
        });



    }

    public void onCLick(View v){
        if(v== buttonUpdateProfile){
            updateProfile();
        }
    }

    private void updateProfile() {

        String firstName=editTextFirstName.getText().toString().trim();
        String lastname=editTextLastName.getText().toString().trim();
        String name =firstName+" "+lastname;
        String phoneNumber=editTextPhoneNumber.getText().toString().trim();
        String hometown=editTextHometown.getText().toString().trim();
        String age=editTextAge.getText().toString().trim();


        if(TextUtils.isEmpty(firstName))
        {
            Toast.makeText(UpdateProfileActivity.this, "Please enter your first name!", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(lastname))
        {
            Toast.makeText(UpdateProfileActivity.this, "Please enter your last name!", Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(age))
        {
            Toast.makeText(UpdateProfileActivity.this, "Please enter your age!", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(phoneNumber))
        {
            Toast.makeText(UpdateProfileActivity.this, "Please enter your phone number!", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(hometown))
        {
            Toast.makeText(UpdateProfileActivity.this, "Please enter your hometown!", Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("Updating your profile.");
        progressDialog.show();



    }

}
