package com.sheikhimtiaz.pegasusride;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ScrollingTabContainerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class ProfileActivity extends AppCompatActivity {

    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    private TextView textViewUserName;
    private TextView textViewUserAge;
    private TextView textViewUserHometown;
    private TextView textViewUserEmail;
    private TextView textViewuserPhoneNumber;

    private ImageButton imageButtonProfilePhotoAdd;

    private Button buttonLogout;
    private Button buttonEditProfile;
    private Button buttonNewVehicle;

    private Uri imageUri;

    private static final int GALLERY_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        getSupportActionBar().setTitle(R.string.profile_toolbar_title);


        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() == null) {
            finish();
            startActivity(new Intent(ProfileActivity.this, LoginActivity.class));
        }

        FirebaseUser user = firebaseAuth.getCurrentUser();


        textViewUserName = (TextView) findViewById(R.id.textViewUserName);
        textViewUserAge = (TextView) findViewById(R.id.textViewUserAge);
        textViewUserHometown = (TextView) findViewById(R.id.textViewUserHometown);
        textViewUserEmail = (TextView) findViewById(R.id.textViewUserEmail);
        textViewuserPhoneNumber = (TextView) findViewById(R.id.textViewUserPhoneNumber);

        textViewUserEmail.setText(user.getEmail());

        imageButtonProfilePhotoAdd = (ImageButton) findViewById(R.id.imageButtonProfilePhotoAdd);

        buttonLogout = (Button) findViewById(R.id.buttonLogout);
        buttonEditProfile = (Button) findViewById(R.id.buttonEditProfile);
        buttonNewVehicle = (Button) findViewById(R.id.buttonNewVehicle);

        imageButtonProfilePhotoAdd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent galleryIntent=new Intent(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, GALLERY_REQUEST);
            }
        });

        buttonEditProfile.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(ProfileActivity.this, UpdateProfileActivity.class));
                return;
            }
        });


        buttonLogout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                finish();
                startActivity(new Intent(ProfileActivity.this, ScrollingActivity.class));
            }
        });

        buttonNewVehicle.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(ProfileActivity.this, NewVehicleActivity.class));
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
           super.onActivityResult(requestCode, resultCode, data);

         if(requestCode==GALLERY_REQUEST && resultCode==RESULT_OK){
            imageUri=data.getData();
            imageButtonProfilePhotoAdd.setImageURI(imageUri);
         }
    }

}