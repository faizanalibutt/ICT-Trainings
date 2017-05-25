package ict_trainings.ictapp.gallery.helper.fragment;

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
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import ict_trainigs.ictapp.R;
import ict_trainings.ictapp.HttpManager;
import ict_trainings.ictapp.gallery.helper.model.Image;

import static ict_trainings.ictapp.courses.course.fragment.Course.API_LINK;

public class ImageGallery extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private List<Image> imageList;
    private GridView image_View;
    ProgressBar pBar;

    private OnFragmentInteractionListener mListener;

    public ImageGallery() {
        // Required empty public constructor
    }


    public static ImageGallery newInstance(String param1, String param2) {
        ImageGallery fragment = new ImageGallery();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (isOnline()) {
            requestData("http://"+ API_LINK + "/php/getImageGallery.php");
        } else
            Toast.makeText(getActivity(), "Network isn't available",
                    Toast.LENGTH_LONG).show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_image_gallery, container, false);
        pBar = (ProgressBar) view.findViewById(R.id.gallery_bar);
        pBar.setVisibility(View.VISIBLE);
        image_View = (GridView) view.findViewById(R.id.galleryView);
        image_View.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Image image= imageList.get(position);
                mListener.OnItemSelected(image);
            }
        });
        return view;
    }

    protected boolean isOnline() {
        // TODO: 3/9/2017 point to be noted we have to use getActivity to get SystemService
        ConnectivityManager connectivityManager =
                (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnectedOrConnecting();
    }

    public void requestData(String uri) {
        ImageGallery.MyTask myTask = new ImageGallery.MyTask();
        myTask.execute(uri);
    }

    protected void updateDisplay() {
        ict_trainings.ictapp.gallery.helper.adapter.Image courseAdapter = new
                ict_trainings.ictapp.gallery.helper.adapter.Image(getActivity(),
                R.layout.image_layout,
                imageList);
        image_View.setAdapter(courseAdapter);
    }

    private class MyTask extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... params) {
            return HttpManager.getData(params[0]);
        }

        @Override
        protected void onPostExecute(String images) {
            imageList = ict_trainings.ictapp.gallery.helper.json.Image.parseImage(images);
            updateDisplay();
            pBar.setVisibility(View.INVISIBLE);
        }
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
        // TODO: Update argument type and name
        void OnItemSelected(Image image);
    }
}
