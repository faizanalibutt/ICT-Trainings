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
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import ict_trainigs.ictapp.R;
import ict_trainings.ictapp.ICThome;

public class Splash extends AppCompatActivity {

    private static final String TAG = Splash.class.getSimpleName();
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        imageView = (ImageView) findViewById(R.id.ictlogo);
        imageView.setImageResource(R.drawable.ict_logo);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.hide();
        if (getIntent().getExtras() != null) {
            for (String key : getIntent().getExtras().keySet()) {
                Object value = getIntent().getExtras().get(key);
                Log.d(TAG, " Value is: " + value + " Key is: " + key);
            }
        }
        if (isOnline()) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(Splash.this, ICThome.class));
                    finish();
                }
            }, 3000);
        } else
            Toast.makeText(this, "No Internet Connection", Toast.LENGTH_LONG).show();
    }

    protected boolean isOnline() {
        ConnectivityManager conManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netinfo = conManager.getActiveNetworkInfo();
        return netinfo != null && netinfo.isConnectedOrConnecting();
    }

}