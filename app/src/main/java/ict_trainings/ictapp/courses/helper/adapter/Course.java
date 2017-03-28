package ict_trainings.ictapp.courses.helper.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.squareup.okhttp.OkHttpClient;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import ict_trainigs.ictapp.R;

import static ict_trainings.ictapp.courses.helper.fragment.Course.PHOTOS_BASE_URL_ICONS;

/**
 * Created by FaiZi bUtt on 9/23/2016.
 */

public class Course extends ArrayAdapter<ict_trainings.ictapp.courses.helper.model.Course> {

    private Context context;
    private ProgressBar progressBar;
    private List<ict_trainings.ictapp.courses.helper.model.Course> courseList;
    private OkHttpClient okHttpClient = new OkHttpClient();

    public Course(Context context, int resource, List<ict_trainings.ictapp.courses.helper.model.Course>
            objects) {
        super(context, resource, objects);
        this.context = context;
        this.courseList = objects;
    }

    @NonNull
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService
                (Activity.LAYOUT_INFLATER_SERVICE);
        @SuppressLint("ViewHolder") View view =
                inflater.inflate(R.layout.course_grid, parent, false);
        ict_trainings.ictapp.courses.helper.model.Course course = courseList.get(position);

//        progressBar = (ProgressBar) view.findViewById(R.id.pbr);
//        progressBar.setVisibility(View.VISIBLE);
        if (course.getBitmap() != null) {
            TextView textTitle = (TextView) view.findViewById(R.id.course_title);
            textTitle.setText(course.getCourseTitle());
            TextView textDesc = (TextView) view.findViewById(R.id.starting_time);
            textDesc.setText(course.getCourseDes());
        } else {
            TextView textTitle = (TextView) view.findViewById(R.id.course_title);
            textTitle.setText(course.getCourseTitle());
            TextView textDesc = (TextView) view.findViewById(R.id.days);
            textDesc.setText(course.getCourseDes());
            CourseView container = new CourseView();
            container.course = course;
            container.view = view;
            CourseImage loader = new CourseImage();
            loader.execute(container);
        }
        return view;
    }

    class CourseView {
        View view;
        Bitmap bitmap;
        ict_trainings.ictapp.courses.helper.model.Course course;
    }

    private class CourseImage extends AsyncTask<CourseView, Void, CourseView> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected CourseView doInBackground(CourseView... params) {
            CourseView courseVIew = params[0];
            ict_trainings.ictapp.courses.helper.model.Course course = courseVIew.course;
            try {
                // TODO: 3/14/2017 reuse the given link to download banners
                String imageURL =
                        PHOTOS_BASE_URL_ICONS + course.getCourseIcon();
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
            ImageView imageView = (ImageView) courseView.view.findViewById(R.id.course_detail_image_grid);
            imageView.setImageBitmap(courseView.bitmap);
            courseView.course.setBitmap(courseView.bitmap);
//            progressBar.setVisibility(View.INVISIBLE);
        }
    }

}
