package ict_trainings.ictapp.courses.course_detail;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.squareup.okhttp.OkHttpClient;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import ict_trainigs.ictapp.R;
import ict_trainings.ictapp.ICThome;
import ict_trainings.ictapp.courses.helper.model.Course;

import static ict_trainings.ictapp.courses.helper.fragment.Course.PHOTOS_BASE_URL_BANNERS;


public class CourseDetail extends AppCompatActivity {
    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    private OkHttpClient okHttpClient = new OkHttpClient();
    Course course;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_detail);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
//            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        Bundle bundle = getIntent().getBundleExtra
                (Course.COURSE_BUNDLE);
        if (bundle != null && bundle.containsKey
                (Course.COURSE_CHARGES)) {
            course = new Course(bundle);
        }

        if (course != null) {
            progressBar = (ProgressBar) findViewById(R.id.course_detail_pb);
            progressBar.setVisibility(View.VISIBLE);
            if (course.getBitmap() != null) {
//                TextView textTitle = (TextView) findViewById(R.id.title);
//                textTitle.setText(course.getCourseTitle());
//                TextView textDesc = (TextView) findViewById(R.id.desc);
//                textDesc.setText(course.getCourseDes());
                Toast.makeText(this, "loading....", Toast.LENGTH_SHORT).show();
            } else {
//                TextView textTitle = (TextView) findViewById(R.id.title);
//                textTitle.setText(course.getCourseTitle());
//                TextView textDesc = (TextView) findViewById(R.id.desc);
//                textDesc.setText(course.getCourseDes());
                CourseView container = new CourseView();
                container.course = course;
                CourseImage loader = new CourseImage();
                loader.execute(container);
            }
        }

        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.lvExp);

        // preparing list data
        prepareListData();

        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);

        // Listview Group click listener
        expListView.setOnGroupClickListener(new OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                // Toast.makeText(getApplicationContext(),
                // "Group Clicked " + listDataHeader.get(groupPosition),
                // Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        // Listview Group expanded listener
        expListView.setOnGroupExpandListener(new OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {

            }
        });

        // Listview Group collasped listener
        expListView.setOnGroupCollapseListener(new OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {


            }
        });

        // Listview on child click listener
        expListView.setOnChildClickListener(new OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                // TODO Auto-generated method stub

                return false;
            }
        });
    }

    /*
         * Preparing the list data
         */
    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("The Basics");
        listDataHeader.add("Fundamentals of Java");
        listDataHeader.add("Unit Testing and Debugging");
        listDataHeader.add("Android User Interface");
        listDataHeader.add("Styling Widgets");
        listDataHeader.add("Layout");
        listDataHeader.add("Advanced Widgets");
        listDataHeader.add("Storing and Retrieving Data");
        listDataHeader.add("Content Providers");
        listDataHeader.add("Asynchronous Tasks");
        listDataHeader.add("Location Services and Maps");
        listDataHeader.add("WebView");
        listDataHeader.add("Best Practices");
        listDataHeader.add("Final Project");

        // Adding child data
        List<String> a = new ArrayList<String>();
        a.add("What is Android?");
        a.add("Architecture Explained");
        a.add("Role of Java");


        List<String> b = new ArrayList<String>();
        b.add("What is Java?");
        b.add("Fundamentals of Java");
        b.add("Android SDK");
        b.add("Eclipse IDE Plugin");
        b.add("Device Emulator");
        b.add("Profiling Tools");
        b.add("Hello World Application");

        List<String> c = new ArrayList<String>();
        c.add("Creating Unit Tests");
        c.add("Android Development Tools (ADT)");
        c.add("Using the Emulator");
        c.add("Analyzing the Heap");

        List<String> d = new ArrayList<String>();
        d.add("View Hierarchy");
        d.add("Menus");
        d.add("Fragments");

        List<String> e = new ArrayList<String>();
        e.add("Defining Styles");
        e.add("Applying Styles to the UI");
        e.add("Platform Styles and Themes");

        List<String> f = new ArrayList<String>();
        f.add("Layout Containers");
        f.add("Weight and Gravity");
        f.add("Layout TechniquesHandling Events");

        List<String> g = new ArrayList<String>();
        g.add("Scroll View");
        g.add("ViewPager");
        g.add("TabView");
        g.add("Custom Views");

        List<String> h = new ArrayList<String>();
        h.add("Internal and External Storage ");
        h.add("Preferences");

        List<String> i = new ArrayList<String>();
        i.add("Querying Content Providers  ");
        i.add("Modifying Data ");
        i.add("Creating a Content Provider ");

        List<String> j = new ArrayList<String>();
        j.add("Main UI Thread  ");
        j.add("Using AsyncTask  ");

        List<String> k = new ArrayList<String>();
        k.add("Location Services  ");
        k.add("Mock Location Data  ");
        k.add("Google Map Libraries  ");

        List<String> l = new ArrayList<String>();
        l.add("Web Apps Overview ");
        l.add("Target Screens from Web Apps ");
        l.add("WebView  ");
        l.add("Debugging Web Apps  ");
        l.add("Best Practices for web apps  ");

        List<String> m = new ArrayList<String>();
        m.add("Compatibility ");
        m.add("Supporting multiple screens ");
        m.add("Optimizing for Other Android Versions ");

        List<String> n = new ArrayList<String>();
        n.add("Final Project ");

        listDataChild.put(listDataHeader.get(0), a);
        listDataChild.put(listDataHeader.get(1), b);
        listDataChild.put(listDataHeader.get(2), c);
        listDataChild.put(listDataHeader.get(3), d);
        listDataChild.put(listDataHeader.get(4), e);
        listDataChild.put(listDataHeader.get(5), f);
        listDataChild.put(listDataHeader.get(6), g);
        listDataChild.put(listDataHeader.get(7), h);
        listDataChild.put(listDataHeader.get(8), i);
        listDataChild.put(listDataHeader.get(9), j);
        listDataChild.put(listDataHeader.get(10),k);
        listDataChild.put(listDataHeader.get(11),l);
        listDataChild.put(listDataHeader.get(12),m);
        listDataChild.put(listDataHeader.get(13),n);

    }

    @Override
    public void onBackPressed() {
        finish();
        Intent intent = new Intent(CourseDetail.this, ICThome.class);
        startActivity(intent);
        super.onBackPressed();
    }

    //    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.menu_network, menu);
//        return super.onCreateOptionsMenu(menu);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int i = item.getItemId();
//        switch (i) {
//            case android.R.id.home:
//                finish();
//                Intent home = new Intent(CourseDetail.this, ICThome.class);
//                startActivity(home);
//                return super.onOptionsItemSelected(item);
//        }
//        return false;
//    }

    class CourseView {
        Bitmap bitmap;
        Course course;
    }

    public class CourseImage extends AsyncTask<CourseView, Void, CourseView> {
        @Override
        protected CourseView doInBackground(CourseView... params) {
            CourseView courseVIew = params[0];
            Course course = courseVIew.course;
            try {
                // TODO: 3/14/2017 reuse the given link to download banners
                String imageURL =
                        PHOTOS_BASE_URL_BANNERS + course.getCourseBanner();
                Log.d(ict_trainings.ictapp.courses.helper.json.Course.TAG, imageURL);
                HttpURLConnection httpURLConnection = okHttpClient.open(new URL(imageURL));
                InputStream inputStream = httpURLConnection.getInputStream();
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                course.setBitmap(bitmap);
                inputStream.close();
                courseVIew.bitmap = bitmap;
                return courseVIew;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(CourseView courseView) {
            super.onPostExecute(courseView);
            ImageView imageView = (ImageView) findViewById(R.id.course_detail_image);
            imageView.setImageBitmap(courseView.bitmap);
            progressBar = (ProgressBar) findViewById(R.id.course_detail_pb);
            progressBar.setVisibility(View.INVISIBLE);
            courseView.course.setBitmap(courseView.bitmap);
        }
    }
}



