package ict_trainings.ictapp.events;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import ict_trainigs.ictapp.R;
import ict_trainings.ictapp.ICThome;
import ict_trainings.ictapp.courses.latest_courses.latest_courses;

public class Events extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        TextView eveTxt = (TextView) findViewById(R.id.eveTxt);
        if(extras != null) {
            String notify = extras.getString(EventService.TAG);
            Log.d(EventService.TAG, notify + " coming notify");
            eveTxt.setText(notify);
        }

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
