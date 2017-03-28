package ict_trainings.ictapp.courses.helper.json;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by FaiZi bUtt on 9/23/2016.
 */

public class Course {
    public static final String TAG = "Fragment_Course";

    public static List<ict_trainings.ictapp.courses.helper.model.Course> parseCourse(String content){
        try {
            Log.d(TAG, content);
            JSONArray jsonArray = new JSONArray(content);
            List<ict_trainings.ictapp.courses.helper.model.Course> courseList = new
                    ArrayList<>();

            for(int i = 0; i < jsonArray.length(); i++){

                JSONObject jsonObject = jsonArray.getJSONObject(i);
                ict_trainings.ictapp.courses.helper.model.Course course = new
                        ict_trainings.ictapp.courses.helper.model.Course();
                //course.setCourseVersion(jsonObject.getString("courseVersion "));
                course.setCourseTitle(jsonObject.getString("title"));
                course.setStartingDate(jsonObject.getString("starting_date"));
                course.setDays(jsonObject.getString("days"));
                course.setCourseDuration(jsonObject.getString("duration"));
                course.setCourseCharges(jsonObject.getString("charges"));
                course.setCourseIcon(jsonObject.getString("icon"));
                course.setCourseBanner(jsonObject.getString("banner"));
                course.setCourseDes(jsonObject.getString("description"));
                //course.setCourseLink1(jsonObject.getString("courseLink1 "));
                //course.setCourseLink2(jsonObject.getString("courseLink2 "));
                //course.setCourseCurrentBatch(jsonObject.getString("courseCurrentBatch"));
                //course.setCourseNextBatch1(jsonObject.getString("courseNextBatch1 "));
                //course.setCourseNextBatch2(jsonObject.getString("courseNextBatch2 "));

                courseList.add(course);
            }
            return courseList;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}