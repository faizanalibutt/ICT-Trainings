package ict_trainings.ictapp.courses.course.fragment;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import ict_trainigs.ictapp.R;
import ict_trainings.ictapp.HttpManager;

public class Course extends ListFragment {

    public static final String API_LINK = "muhammadtayyab.info";

    public static final String PHOTOS_BASE_IMAGE_GALLERY =
            "http://" + API_LINK + "/php/images/ict_gallery/";
    public static final String PHOTOS_BASE_URL_ICONS =
            "http://" + API_LINK + "/php/images/ict_course_images/icons/";
    public static final String PHOTOS_BASE_URL_BANNERS =
            "http://" + API_LINK + "/php/images/ict_course_images/banners/";


    private ProgressBar progressBar;
    private TextView loader_text;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private List<ict_trainings.ictapp.courses.course.model.Course> courseList;

    private OnFragmentInteractionListener mListener;

    public Course() {
    }

    public static Course newInstance(String param1, String param2) {
        Course fragment = new Course();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_course, container, false);
        requestData("http://"+ API_LINK + "/php/getCourseImages.php");
        loader_text = (TextView) view.findViewById(R.id.list_load);
        progressBar = (ProgressBar) view.findViewById(R.id.progressbar);
        progressBar.setVisibility(View.VISIBLE);
        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        ict_trainings.ictapp.courses.course.model.Course course = courseList.get(position);
        if(course.getCourseIcon().equalsIgnoreCase("android.jpg")){
            Toast.makeText(getActivity(), "Android detail open", Toast.LENGTH_SHORT).show();
        }
        mListener.OnItemSelected(course);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
        Log.d("fragment", "detached");
    }

    public interface OnFragmentInteractionListener {
        void OnItemSelected(ict_trainings.ictapp.courses.course.model.Course course);
    }

    public void requestData(String uri) {
        MyTask myTask = new MyTask();
        myTask.execute(uri);
    }

    protected void updateDisplay() {
        ict_trainings.ictapp.courses.course.adapter.Course courseAdapter = new
                ict_trainings.ictapp.courses.course.adapter.Course(getActivity(),
                R.layout.dynamic_list, courseList);
        setListAdapter(courseAdapter);
    }

    private class MyTask extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... params) {
            return HttpManager.getData(params[0]);
        }

        @Override
        protected void onPostExecute(String courses) {
            courseList = ict_trainings.ictapp.courses.course.json.Course.parseCourse(courses);
            updateDisplay();
            progressBar.setVisibility(View.INVISIBLE);
            loader_text.setVisibility(View.INVISIBLE);
        }
    }
}