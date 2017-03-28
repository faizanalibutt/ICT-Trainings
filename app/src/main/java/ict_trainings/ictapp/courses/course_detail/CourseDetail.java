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

    private OkHttpClient okHttpClient = new OkHttpClient();
    Course course;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_detail);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
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
                Intent home = new Intent(CourseDetail.this, ICThome.class);
                startActivity(home);
                return super.onOptionsItemSelected(item);
        }
        return false;
    }

    class CourseView {
        Bitmap bitmap;
        Course course;
    }

    private class CourseImage extends AsyncTask<CourseView, Void, CourseView> {
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