package ict_trainings.ictapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import ict_trainigs.ictapp.R;
import ict_trainings.ictapp.about.About_ICT;
import ict_trainings.ictapp.courses.course_detail.CourseDetail;
import ict_trainings.ictapp.courses.course_request.Course_Registration;
import ict_trainings.ictapp.courses.helper.fragment.Course;
import ict_trainings.ictapp.courses.latest_courses.latest_courses;
import ict_trainings.ictapp.events.Events;
import ict_trainings.ictapp.gallery.helper.fragment.ImageGallery;
import ict_trainings.ictapp.gallery.helper.model.Image;
import ict_trainings.ictapp.home.Home;
import ict_trainings.ictapp.maps.MapsActivity;
import ict_trainings.ictapp.courses.course_review.rate.rate;


public class ICThome extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        Course.OnFragmentInteractionListener, ImageGallery.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_icthome);
        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
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
            Intent intent = new Intent(ICThome.this, latest_courses.class);
            startActivity(intent);
        }else if (id == R.id.ict_group) {
            About_ICT ab_ict = About_ICT.newInstance("about1", "about2");
            getSupportFragmentManager().beginTransaction().replace
                    (R.id.fragmentContainer, ab_ict).commit();
        } else if (id == R.id.ict_contact) {
            Intent intent = new Intent(ICThome.this, MapsActivity.class);
            startActivity(intent);
        } else if (id == R.id.review) {
            Intent intent = new Intent(ICThome.this, rate.class);
            startActivity(intent);
        } else if (id == R.id.events) {
            Intent intent = new Intent(ICThome.this, Events.class);
            startActivity(intent);
        } else if (id == R.id.gallery){
            ImageGallery gallery = ImageGallery.newInstance("course1", "course2");
            getSupportFragmentManager().beginTransaction().replace
                    (R.id.fragmentContainer, gallery).commit();
        } else if (id == R.id.nav_home){
            Home home = new Home();
            getSupportFragmentManager().beginTransaction().add
                    (R.id.fragmentContainer, home).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void OnItemSelected(ict_trainings.ictapp.courses.helper.model.Course course) {
        Bundle bundle = course.toBundle();
        Intent intent = new Intent(ICThome.this, CourseDetail.class);
        intent.putExtra(ict_trainings.ictapp.courses.helper.model.Course.COURSE_BUNDLE, bundle);
        startActivity(intent);
        finish();
    }

    @Override
    public void OnItemSelected(Image image) {

    }
}
