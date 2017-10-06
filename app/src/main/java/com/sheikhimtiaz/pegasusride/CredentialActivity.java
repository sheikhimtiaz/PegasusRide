package com.sheikhimtiaz.pegasusride;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class CredentialActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credential);
        getSupportActionBar().setTitle(R.string.about_title);
    }
}
