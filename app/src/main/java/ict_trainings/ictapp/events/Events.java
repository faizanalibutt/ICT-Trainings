package ict_trainings.ictapp.events;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import ict_trainigs.ictapp.R;
import ict_trainings.ictapp.ICThome;

public class Events extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
        android.support.v7.app.ActionBar actbar = getSupportActionBar();
        assert actbar != null;
        actbar.setDisplayHomeAsUpEnabled(true);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int i = item.getItemId();
        switch(i){
            case android.R.id.home:
                finish();
                Intent home = new Intent(Events.this, ICThome.class);
                startActivity(home);
                return super.onOptionsItemSelected(item);
        }
        return false;
    }
}
