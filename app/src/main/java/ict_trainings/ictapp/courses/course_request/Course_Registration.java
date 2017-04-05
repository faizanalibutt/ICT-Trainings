package ict_trainings.ictapp.courses.course_request;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.ButterKnife;
import butterknife.Bind;
import ict_trainigs.ictapp.R;
// TODO: 3/27/2017 must see here any error may occur

public class Course_Registration extends AppCompatActivity {
    private static final String TAG = "Register Image";

    @Bind(R.id.input_name1)
    EditText _nameText1;
    @Bind(R.id.input_name2)
    EditText _nameText2;
    @Bind(R.id.input_email)
    EditText _emailText;
    @Bind(R.id.input_mobile)
    EditText _mobileText;
    @Bind(R.id.input_course)
    EditText _nameTextCourse;
    @Bind(R.id.input_time)
    EditText _nameText;
    @Bind(R.id.btn_register)
    Button _signupButton;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);
        ButterKnife.bind(this);

        _signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });
    }


    public void signup() {
        Log.d(TAG, "Register");

        if (!validate()) {
            onSignupFailed();
            return;
        }

        _signupButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(Course_Registration.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Image Requesting...");
        progressDialog.show();

        String fname = _nameText1.getText().toString();
        String lname = _nameText2.getText().toString();
        String email = _emailText.getText().toString();
        String mobile = _mobileText.getText().toString();
        String course = _nameTextCourse.getText().toString();
        String course_time = _nameText.getText().toString();

        // TODO: Implement your own signup logic here.
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");
                    if (success) {
                        // TODO: 3/8/2017 make change here for new activity
                        Toast.makeText(Course_Registration.this, "Image Registered Successfully",
                                Toast.LENGTH_LONG).show();
                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(
                                Course_Registration.this);
                        builder.setMessage("Registertration Failed")
                                .setNegativeButton("Retry", null)
                                .create()
                                .show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        
        CourseRequest registerRequest = new CourseRequest(
                fname, lname, email, mobile, course, course_time, responseListener);
        RequestQueue queue = Volley.newRequestQueue(Course_Registration.this);
        queue.add(registerRequest);

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onSignupSuccess or onSignupFailed
                        // depending on success
                        onSignupSuccess();
                        // onSignupFailed();
                        progressDialog.dismiss();
                    }
                }, 3000);
    }


    public void onSignupSuccess() {
        _signupButton.setEnabled(true);
        setResult(RESULT_OK, null);
        finish();
    }

    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "Login Failed", Toast.LENGTH_LONG).show();
        _signupButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String name1 = _nameText1.getText().toString();
        String name2 = _nameText2.getText().toString();
        String email = _emailText.getText().toString();
        String course = _nameTextCourse.getText().toString();
        String time = _nameText.getText().toString();

        if (name1.isEmpty() || name1.length() < 3) {
            _nameText1.setError("At least 3 characters");
            valid = false;
        } else {
            _nameText1.setError(null);
        }
        if (name2.isEmpty() || name2.length() < 3) {
            _nameText2.setError("At least 3 characters");
            valid = false;
        } else {
            _nameText2.setError(null);
        }

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError("Enter a valid email address");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (course.isEmpty()) {
            _nameTextCourse.setError("Enter a valid course");
            valid = false;
        } else {
            _emailText.setError(null);
        }


        if (time.isEmpty()) {
            _nameText.setError("Enter a valid time");
            valid = false;
        } else {
            _nameText.setError(null);
        }

        return valid;
    }
}