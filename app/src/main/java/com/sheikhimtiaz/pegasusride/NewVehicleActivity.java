package com.sheikhimtiaz.pegasusride;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Toast;

import com.firebase.client.Firebase;

import java.util.Random;

public class NewVehicleActivity extends AppCompatActivity {

    private static final String FIREBASE_URL = "https://project200-e7fa5.firebaseio.com/";
    private static final int GALLERY_REQUEST=2;
    private ImageButton imageButtonVehiclePhotoAdd;
    private EditText editTextModel;
    private EditText editTextBrand;
    private EditText editTextCode;
    private EditText editTextDescription;
    private Button buttonAddVehicle;
    private Firebase firebaseRef;
    private Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_vehicle);

        getSupportActionBar().setTitle(R.string.vehicle_post_title);

        imageButtonVehiclePhotoAdd=(ImageButton) findViewById(R.id.imageButtonVehiclePhotoAdd);
        imageButtonVehiclePhotoAdd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent galleryIntent=new Intent(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, GALLERY_REQUEST);
            }
        });
        editTextBrand=(EditText) findViewById(R.id.editTextBrand);
        editTextModel=(EditText) findViewById(R.id.editTextModel);
        editTextDescription=(EditText) findViewById(R.id.editTextDescription);
        editTextCode=(EditText) findViewById(R.id.editTextCode);
        buttonAddVehicle=(Button) findViewById(R.id.buttonAddVehicle);
        buttonAddVehicle.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(v==buttonAddVehicle) {
                    addNewVehicle();
                }
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==GALLERY_REQUEST && resultCode==RESULT_OK){
            imageUri=data.getData();
            imageButtonVehiclePhotoAdd.setImageURI(imageUri);
        }
    }


    private void addNewVehicle() {
        String brand=editTextBrand.getText().toString().trim();
        String model=editTextModel.getText().toString().trim();
        String code=editTextCode.getText().toString().trim();
        String description=editTextDescription.getText().toString().trim();

        if(TextUtils.isEmpty(brand))
        {
            Toast.makeText(NewVehicleActivity.this, "Please enter Brand!", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(model))
        {
            Toast.makeText(NewVehicleActivity.this, "Please enter Model!", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(code))
        {
            Toast.makeText(NewVehicleActivity.this, "Please enter Code!", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(description))
        {
            Toast.makeText(NewVehicleActivity.this, "Please enter Description!", Toast.LENGTH_SHORT).show();
            return;
        }

        Random ran=new Random();
        String ID="1000000"+(ran.nextInt(100000000)+1);

        PegasusVehicle pegasusVehicle=new PegasusVehicle(ID,model,code,brand,description);

        Firebase.setAndroidContext(this);
        firebaseRef=new Firebase(FIREBASE_URL);
        firebaseRef.push().setValue(pegasusVehicle);

    }

    public void onRadioButtonClicked(View view) {

        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_sell:
                if (checked)

                    break;
            case R.id.radio_rent:
                if (checked)

                    break;
            case R.id.radio_lost:
                if(checked)

                    break;
        }

    }

}
