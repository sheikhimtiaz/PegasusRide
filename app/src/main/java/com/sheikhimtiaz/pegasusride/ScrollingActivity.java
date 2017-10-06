package com.sheikhimtiaz.pegasusride;

import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class ScrollingActivity extends AppCompatActivity {

    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.clear();
        if(firebaseAuth.getCurrentUser()== null) {
            menu.add(0, R.id.logIn, Menu.NONE, R.string.logIn).setIcon(null);
            menu.add(0, R.id.registerNow, Menu.NONE, R.string.registerNow).setIcon(null);
            menu.add(0, R.id.action_settings, Menu.NONE, R.string.action_settings).setIcon(null);
        }
        if(firebaseAuth.getCurrentUser()!= null) {
            menu.add(0, R.id.profileView, Menu.NONE, R.string.profileView).setIcon(null);
            menu.add(0, R.id.logOut, Menu.NONE, R.string.logOut).setIcon(null);
            menu.add(0, R.id.action_settings, Menu.NONE, R.string.action_settings).setIcon(null);
        }
        return super.onPrepareOptionsMenu(menu);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.toolbar_title);

        progressDialog= new ProgressDialog(this);
        firebaseAuth = FirebaseAuth.getInstance();


        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Snackbar.make(view, "Refreshed", Snackbar.LENGTH_LONG)
                       .setAction("Action", null).show();
           }
        });*/

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        MenuItem searchItem = (MenuItem) MenuItemCompat.getActionView(menu.findItem(R.id.menu_search));
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        // int id = item.getItemId();

        switch (item.getItemId()) {

            case R.id.menu_search:
                Toast.makeText(ScrollingActivity.this, "Hey Its working.", Toast.LENGTH_SHORT).show();
                break;

            case R.id.profileView:
                //Toast.makeText(ScrollingActivity.this, "Please come back later.", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(ScrollingActivity.this, ProfileActivity.class));
                break;

            case R.id.logIn:
                //Toast.makeText(ScrollingActivity.this, "Please come back later.", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(ScrollingActivity.this, LoginActivity.class) );
                break;

            case R.id.logOut:
                if(firebaseAuth.getCurrentUser() != null) {
                    firebaseAuth.signOut();
                    finish();
                    startActivity(new Intent(ScrollingActivity.this, ScrollingActivity.class));
                } else {
                    Toast.makeText(this, "You must have somehow been logged out between the time the menu button was pressed and now.", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.registerNow:
                    //Toast.makeText(ScrollingActivity.this, "Construction work going on. Sorry!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(ScrollingActivity.this, RegistrationActivity.class) );
                    break;

            case R.id.action_settings:
                    //Toast.makeText(ScrollingActivity.this, "Developed by sheikhimtiaz", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(ScrollingActivity.this, CredentialActivity.class) );
                    break;
        }
        //noinspection SimplifiableIfStatement
        //if (id == R.id.action_settings) {
         //   return true;
        //}
        return super.onOptionsItemSelected(item);
    }
}
