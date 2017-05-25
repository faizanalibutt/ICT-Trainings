package ict_trainings.ictapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import com.google.firebase.messaging.FirebaseMessaging;
import ict_trainigs.ictapp.R;
import ict_trainings.ictapp.about.About_ICT;
import ict_trainings.ictapp.courses.course_detail.CoursesDetail;
import ict_trainings.ictapp.courses.course_request.Course_Registration;
import ict_trainings.ictapp.courses.course_review.rate.rate;
import ict_trainings.ictapp.courses.course.fragment.Course;
import ict_trainings.ictapp.courses.latest_courses.Latest_Courses;
import ict_trainings.ictapp.events.Events;
import ict_trainings.ictapp.gallery.helper.fragment.ImageGallery;
import ict_trainings.ictapp.gallery.helper.model.Image;
import ict_trainings.ictapp.home.Home;
import ict_trainings.ictapp.maps.MapsActivity;
import ict_trainings.ictapp.pushnotifications.appconfig.Config;
import ict_trainings.ictapp.pushnotifications.util.NotificationUtils;

import static ict_trainings.ictapp.courses.course.model.Course.COURSE_BUNDLE;


public class ICThome extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        Course.OnFragmentInteractionListener, ImageGallery.OnFragmentInteractionListener {

    private static final String TAG = ICThome.class.getSimpleName();
    private BroadcastReceiver c_RegistrationBroadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_icthome);
        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // get Broadcast from service.
        getIntentNotification();
        Log.e(TAG, "came here if activity opens once or many");

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            String shome = extras.getString(COURSE_BUNDLE);
            if (shome != null) {
                Course course = Course.newInstance("home", "coming");
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, course).commit();
            }
        } else {
            Home home = new Home();
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, home).commit();
            // get Broadcast from service.
            getIntentNotification();
            Log.e(TAG, "came here for again calling fragment and broadcasting.");
        }
    }

    public void getIntentNotification() {
        c_RegistrationBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Log.e(TAG, "came here when broadcasting");
                if (intent.getAction().equals(Config.REGISTRATION_COMPLETE)) {
                    FirebaseMessaging.getInstance().subscribeToTopic(Config.TOPIC_EVENTS);
                    FirebaseMessaging.getInstance().subscribeToTopic(Config.TOPIC_LATEST_COURSES);
                    Log.e(TAG, "Subscribed to Topic: " + Config.TOPIC_EVENTS + "    &   " + Config.TOPIC_LATEST_COURSES);
                } else if (intent.getAction().equals(Config.PUSH_NOTIFICATION)) {
                    // new push notification is received
                    String message = intent.getStringExtra("message");
                    String title = intent.getStringExtra("title");
                    String type = intent.getStringExtra("type");
                    // String image = intent.getStringExtra("image"); "\n" + image
                    // TODO: 5/4/2017 bitmap image is getting using bitmapurl.
                    // Bitmap bitmap = new NotificationUtils(getApplicationContext()).getBitmapFromURL(image);
                    Toast.makeText(getApplicationContext(), "Push notification: \n" + message + "\n" + title,
                            Toast.LENGTH_LONG).show();
                    Log.e(TAG, "came here.");

                    switch (type) {
                        case "Latest_Courses":
                            intent = new Intent(ICThome.this, Latest_Courses.class);
                            intent.putExtra("message", message);
                            intent.putExtra("title", title);
                            intent.putExtra("type", type);
                            startActivity(intent);
                            break;
                        case "events":
                            intent = new Intent(ICThome.this, Events.class);
                            intent.putExtra("message", message);
                            intent.putExtra("title", title);
                            intent.putExtra("type", type);
                            startActivity(intent);
                            break;
                        default:
                            Toast.makeText(ICThome.this, "nope", Toast.LENGTH_LONG).show();
                            break;
                    }
                }
            }
        };
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
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.icthome, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.course) {
            Course course = Course.newInstance("course1", "course2");
            getSupportFragmentManager().beginTransaction().replace
                    (R.id.fragmentContainer, course).commit();
        } else if (id == R.id.rq_course) {
            Intent intent = new Intent(ICThome.this, Course_Registration.class);
            startActivity(intent);
        } else if (id == R.id.latest_courses) {
            Intent intent = new Intent(ICThome.this, Latest_Courses.class);
            startActivity(intent);
        } else if (id == R.id.ict_group) {
            About_ICT ab_ict = About_ICT.newInstance("about1", "about2");
            getSupportFragmentManager().beginTransaction().replace
                    (R.id.fragmentContainer, ab_ict).commit();
        } else if (id == R.id.ict_contact) {
            Intent intent = new Intent(ICThome.this, MapsActivity.class);
            startActivity(intent);
        } else if (id == R.id.review) {
            Intent intent = new Intent(ICThome.this, rate.class);
            startActivity(intent);
//        } else if (id == R.id.events) {
//            Intent intent = new Intent(ICThome.this, Events.class);
//            startActivity(intent);
        } else if (id == R.id.gallery) {
            ImageGallery gallery = ImageGallery.newInstance("course1", "course2");
            getSupportFragmentManager().beginTransaction().replace
                    (R.id.fragmentContainer, gallery).commit();
        } else if (id == R.id.nav_home) {
            Home home = new Home();
            getSupportFragmentManager().beginTransaction().replace
                    (R.id.fragmentContainer, home).addToBackStack(null).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void OnItemSelected(ict_trainings.ictapp.courses.course.model.Course course) {
        Bundle bundle = course.toBundle();
        Intent intent = new Intent(ICThome.this, CoursesDetail.class);
        intent.putExtra(COURSE_BUNDLE, bundle);
        startActivity(intent);
        finish();
    }

    @Override
    public void OnItemSelected(Image image) {

    }

    @Override
    protected void onResume() {
        super.onResume();

        // register FCM registration complete receiver
        LocalBroadcastManager.getInstance(this).registerReceiver(c_RegistrationBroadcastReceiver,
                new IntentFilter(Config.REGISTRATION_COMPLETE));
        Log.e(TAG, "onResume Called");

        // register new push message receiver
        // by doing this, the activity will be notified each time a new message arrives
        LocalBroadcastManager.getInstance(this).registerReceiver(c_RegistrationBroadcastReceiver,
                new IntentFilter(Config.PUSH_NOTIFICATION));

        // clear the notification area when the app is opened
        NotificationUtils.clearNotifications(getApplicationContext());
    }

    @Override
    protected void onPause() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(c_RegistrationBroadcastReceiver);
        super.onPause();
    }
}
