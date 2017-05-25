package ict_trainings.ictapp.maps;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import ict_trainigs.ictapp.R;
import ict_trainings.ictapp.ICThome;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
//        TextView ictadr = (TextView) findViewById(R.id.ict_adr);
//        Typeface typeface = Typeface.createFromAsset(getAssets(), "");
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_network, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int i = item.getItemId();
        switch(i){
            case android.R.id.home:
                finish();
                Intent home = new Intent(MapsActivity.this, ICThome.class);
                startActivity(home);
                return super.onOptionsItemSelected(item);
        }
        return false;
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        // Add a marker in ICT-TRAININGS and move the camera
        LatLng ict_trainings = new LatLng(31.4698, 74.2621);
        googleMap.addMarker(new MarkerOptions().position(ict_trainings).title("ICT-Trainings, Lahore"));

        double zoomLevel = 17.1; //This goes up to 21
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ict_trainings, (float) zoomLevel));

        //googleMap.moveCamera(CameraUpdateFactory.newLatLng(ict_trainings));
    }
}