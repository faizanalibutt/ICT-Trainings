package ict_trainings.ictapp.courses.helper.fragment;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import ict_trainigs.ictapp.R;
import ict_trainings.ictapp.HttpManager;

public class Course extends Fragment {

    public static final String PHOTOS_BASE_URL_ICONS =
            "http://myfoodora.com/php/images/ict_course_images/icons/";
    public static final String PHOTOS_BASE_URL_BANNERS =
            "http://myfoodora.com/php/images/ict_course_images/banners/";
    public ProgressBar progressBar;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private List<ict_trainings.ictapp.courses.helper.model.Course> courseList;
    private ListView courseView;

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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: 3/8/2017 hello change link to myfoodora/php/api
        if (isOnline()) {
            requestData("http://myfoodora.com/php/getCourseImagesGrid.php");
        } else
            Toast.makeText(getActivity(), "Network isn't available", Toast.LENGTH_LONG).show();
    }

    protected boolean isOnline() {
        // TODO: 3/9/2017 point to be noted we have to use getActivity to get SystemService
        ConnectivityManager connectivityManager =
                (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnectedOrConnecting();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_course, container, false);
        progressBar = (ProgressBar) view.findViewById(R.id.progressbar);
        progressBar.setVisibility(View.VISIBLE);
        courseView = (ListView) view.findViewById(R.id.listViewfragment);
        courseView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ict_trainings.ictapp.courses.helper.model.Course course = courseList.get(position);
                mListener.OnItemSelected(course);
            }
        });
        return view;
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
    }

    public interface OnFragmentInteractionListener {
        void OnItemSelected(ict_trainings.ictapp.courses.helper.model.Course course);
    }

    public void requestData(String uri) {
        MyTask myTask = new MyTask();
        myTask.execute(uri);
    }

    protected void updateDisplay() {
        ict_trainings.ictapp.courses.helper.adapter.Course courseAdapter = new
                ict_trainings.ictapp.courses.helper.adapter.Course(getActivity(),
                R.layout.dynamic_list,
                courseList);
        courseView.setAdapter(courseAdapter);
    }

    private class MyTask extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... params) {
            return HttpManager.getData(params[0]);
        }

        @Override
        protected void onPostExecute(String courses) {
            courseList = ict_trainings.ictapp.courses.helper.json.Course.parseCourse(courses);
            updateDisplay();
            progressBar.setVisibility(View.INVISIBLE);
        }
    }
}