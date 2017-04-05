package ict_trainings.ictapp.gallery.helper.json;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by FaiZi bUtt on 9/23/2016.
 */

public class Image {
    public static final String TAG = "Fragment_Image";

    public static List<ict_trainings.ictapp.gallery.helper.model.Image> parseImage(String content){
        try {
            Log.d(TAG, content);
            JSONArray jsonArray = new JSONArray(content);
            List<ict_trainings.ictapp.gallery.helper.model.Image> imageList = new
                    ArrayList<>();

            for(int i = 0; i < jsonArray.length(); i++){

                JSONObject jsonObject = jsonArray.getJSONObject(i);
                ict_trainings.ictapp.gallery.helper.model.Image image = new
                        ict_trainings.ictapp.gallery.helper.model.Image();
                //image.setImageVersion(jsonObject.getString("courseVersion "));
                image.setImageTitle(jsonObject.getString("title"));
                image.setImageDes(jsonObject.getString("description"));
//                image.setStartingDate(jsonObject.getString("starting_date"));
//                image.setDays(jsonObject.getString("days"));
//                image.setImageDuration(jsonObject.getString("duration"));
//                image.setImageCharges(jsonObject.getString("charges"));
//                image.setImageIcon(jsonObject.getString("icon"));
//                image.setImageBanner(jsonObject.getString("banner"));
                //image.setImageLink1(jsonObject.getString("courseLink1 "));
                //image.setImageLink2(jsonObject.getString("courseLink2 "));
                //image.setImageCurrentBatch(jsonObject.getString("courseCurrentBatch"));
                //image.setImageNextBatch1(jsonObject.getString("courseNextBatch1 "));
                //image.setImageNextBatch2(jsonObject.getString("courseNextBatch2 "));

                imageList.add(image);
            }
            return imageList;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}