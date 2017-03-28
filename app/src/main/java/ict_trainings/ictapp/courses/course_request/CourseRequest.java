package ict_trainings.ictapp.courses.course_request;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

class CourseRequest extends StringRequest {
    private static final String REGISTER_REQUEST_URL = "http://myfoodora.com/php/RequestCourse.php";
    private Map<String, String> params;

    CourseRequest(String fname, String lname, String email,
                  String mobile_number, String course_learn, String course_time,
                  Response.Listener<String> listener) {
        super(Method.POST, REGISTER_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("first_name", fname);
        params.put("last_name", lname );
        params.put("email", email);
        params.put("mobile_number", mobile_number);
        params.put("course_request", course_learn);
        params.put("course_time", course_time);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
