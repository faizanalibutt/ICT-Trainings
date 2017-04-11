package ict_trainings.ictapp.home;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

import ict_trainigs.ictapp.R;
import ict_trainings.ictapp.HomeImageAdapter;

public class Home extends Fragment {


    public Home() {
        // Required empty public constructor
    }

    ViewPager mViewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        mViewPager = (ViewPager) view.findViewById(R.id.viewhome);
        HomeImageAdapter adapterView = new HomeImageAdapter(getActivity());
        mViewPager.setAdapter(adapterView);
        pageSwitcher(3);
        return view;
    }

    Timer timer;
    int page = 0;

    public void pageSwitcher(int seconds) {
        timer = new Timer(); // At this line a new Thread will be created
        timer.scheduleAtFixedRate(new RemindTask(), 0, seconds * 1000); // delay in milliseconds
    }

    // this is an inner class...
    private class RemindTask extends TimerTask {
        @Override
        public void run() {
            // As the TimerTask run on a seprate thread from UI thread we have
            // to call runOnUiThread to do work on UI thread.
            getActivity().runOnUiThread(new Runnable() {
                public void run() {
                    if (page > 7) { // In my case the number of pages are 8
                        timer.cancel();
//                        // Showing a toast for just testing purpose
//                        Toast.makeText(getActivity(), "Timer stopped",
//                                Toast.LENGTH_LONG).show();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                page = 0;
                                pageSwitcher(3);
                            }
                        }, 3000);
                    } else {
                        mViewPager.setCurrentItem(++page);
                    }
                }
            });
        }
    }
}
