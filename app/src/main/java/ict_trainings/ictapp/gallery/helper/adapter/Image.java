package ict_trainings.ictapp.gallery.helper.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
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

public class Image extends ArrayAdapter<ict_trainings.ictapp.gallery.helper.model.Image> {

    private Context context;
//    private ProgressBar progressBar;
    private List<ict_trainings.ictapp.gallery.helper.model.Image> imageList;
    private OkHttpClient okHttpClient = new OkHttpClient();

    public Image(Context context, int resource, List<ict_trainings.ictapp.gallery.helper.model.Image>
            objects) {
        super(context, resource, objects);
        this.context = context;
        this.imageList = objects;
    }

    @NonNull
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService
                (Activity.LAYOUT_INFLATER_SERVICE);
        @SuppressLint("ViewHolder") View view =
                inflater.inflate(R.layout.image_layout, parent, false);
        ict_trainings.ictapp.gallery.helper.model.Image image = imageList.get(position);

//        progressBar = (ProgressBar) view.findViewById(R.id.pbr);
//        progressBar.setVisibility(View.VISIBLE);
        if (image.getBitmap() != null) {
            TextView textTitle = (TextView) view.findViewById(R.id.image_name);
            textTitle.setText(image.getImageTitle());
        } else {
            Image_View container = new Image_View();
            container.image = image;
            container.view = view;
            Image_Gallery loader = new Image_Gallery();
            loader.execute(container);
            TextView textTitle = (TextView) view.findViewById(R.id.image_name);
            textTitle.setText(image.getImageTitle());
        }
        return view;
    }

    class Image_View {
        View view;
        Bitmap bitmap;
        ict_trainings.ictapp.gallery.helper.model.Image image;
    }

    private class Image_Gallery extends AsyncTask<Image_View, Void, Image_View> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Image_View doInBackground(Image_View... params) {
            Image_View courseVIew = params[0];
            ict_trainings.ictapp.gallery.helper.model.Image image = courseVIew.image;
            try {
                // TODO: 3/14/2017 reuse the given link to download banners
                String imageURL = PHOTOS_BASE_URL_ICONS + image.getImageTitle();
                HttpURLConnection httpURLConnection = okHttpClient.open(new URL(imageURL));
                InputStream inputStream = httpURLConnection.getInputStream();
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                image.setBitmap(bitmap);
                inputStream.close();
                courseVIew.bitmap = bitmap;
                return courseVIew;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(Image_View courseView) {
            super.onPostExecute(courseView);
            ImageView imageView = (ImageView) courseView.view.findViewById(R.id.imagegallery);
            imageView.setImageBitmap(courseView.bitmap);
            courseView.image.setBitmap(courseView.bitmap);
//            progressBar.setVisibility(View.INVISIBLE);
        }
    }
}
