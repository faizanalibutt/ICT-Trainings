package ict_trainings.ictapp.events;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import ict_trainigs.ictapp.R;

public class Events extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        TextView eveTxt = (TextView) findViewById(R.id.eveTxt);
        if(extras != null) {
            String notify = extras.getString(EventService.TAG);
            Log.d(EventService.TAG, notify + " coming notify");
            eveTxt.setText(notify);
        }

    }
}
