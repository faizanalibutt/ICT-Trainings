package ict_trainings.ictapp.home;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import ict_trainigs.ictapp.R;

/**
 * Created by Faizan Ali on 4/5/2017.
 */

public class HomeImageAdapter extends PagerAdapter {
    private Context context;

    public HomeImageAdapter(Context context){
        this.context = context;
    }
    @Override
    public int getCount() {
        return sliderImagesId.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((ImageView) object);
    }

    private int[] sliderImagesId = new int[]{
            R.drawable.b, R.drawable.a, R.drawable.c,
            R.drawable.d, R.drawable.e
    };

    @Override
    public Object instantiateItem(ViewGroup container, int i) {
        ImageView mImageView = new ImageView(context);
        mImageView.setScaleType(ImageView.ScaleType.FIT_XY);
        mImageView.setImageResource(sliderImagesId[i]);
        container.addView(mImageView, 0);
        return mImageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int i, Object obj) {
        container.removeView((ImageView) obj);
    }
}
