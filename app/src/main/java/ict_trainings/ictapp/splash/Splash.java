package ict_trainings.ictapp.splash;
//Package names are written in all lower case to avoid conflict with names of classes or interfaces.

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.Toast;

import ict_trainigs.ictapp.R;
import ict_trainings.ictapp.ICThome;
import ict_trainings.ictapp.home.Home;

public class Splash extends AppCompatActivity {

    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.hide();
        if(isOnline()){
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(Splash.this, ICThome.class));
                    finish();
                }
            }, 3000);
        } else
            Toast.makeText(this, "No Internet Connection", Toast.LENGTH_LONG).show();
        imageView = (ImageView) findViewById(R.id.ictlogo);
        imageView.setImageResource(R.drawable.ict_logo);
    }

    protected boolean isOnline(){
        ConnectivityManager conManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netinfo = conManager.getActiveNetworkInfo();
        return netinfo != null && netinfo.isConnectedOrConnecting();
    }

}