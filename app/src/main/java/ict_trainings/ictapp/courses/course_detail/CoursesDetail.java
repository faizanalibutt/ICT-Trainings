package ict_trainings.ictapp.courses.course_detail;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ProgressBar;
import android.widget.Toast;

import ict_trainigs.ictapp.R;
import ict_trainings.ictapp.ICThome;
import ict_trainings.ictapp.courses.course_detail.webview.MyAppWebViewClient;
import ict_trainings.ictapp.courses.course.model.Course;

import static ict_trainings.ictapp.courses.course.model.Course.COURSE_BUNDLE;

public class CoursesDetail extends AppCompatActivity {

    public WebView webView;
    private ProgressBar progressBar;
    private ValueCallback<Uri[]> mFilePathCallback;
    private static final String TAG = CoursesDetail.class.getSimpleName();
    Course course;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses_detail);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        Bundle bundle = getIntent().getBundleExtra
                (COURSE_BUNDLE);
        if (bundle != null && bundle.containsKey
                (Course.COURSE_CHARGES)) {
            course = new Course(bundle);
        }
        WebViewURL();
    }

    private void WebViewURL() {
        String webViewUrl = course.getLink_pdf();
        Toast.makeText(this, webViewUrl, Toast.LENGTH_LONG).show();
        Log.d(TAG, webViewUrl);
        webView = (WebView) findViewById(R.id.webView1);
        webView.getSettings().setJavaScriptEnabled(true);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        webView.getSettings().setAllowFileAccess(true);
        webView.setWebViewClient(new MyAppWebViewClient());
        webView.loadUrl(webViewUrl);
        webView.setWebChromeClient(new WebChromeClient() {

            public void onProgressChanged(WebView view, int progress) {
                if (progress < 100 && progressBar.getVisibility() == ProgressBar.GONE) {
                    progressBar.setVisibility(ProgressBar.VISIBLE);
                }
                progressBar.setProgress(progress);
                if (progress == 100) {
                    progressBar.setVisibility(ProgressBar.GONE);
                }
            }


            public boolean onShowFileChooser(
                    WebView webView, ValueCallback<Uri[]> filePathCallback,
                    WebChromeClient.FileChooserParams fileChooserParams) {
                if (mFilePathCallback != null) {
                    mFilePathCallback.onReceiveValue(null);
                }
                mFilePathCallback = filePathCallback;

                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                }


                return false;
            }
        });
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_network, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int i = item.getItemId();
        switch (i) {
            case android.R.id.home:
                finish();
                Intent home = new Intent(CoursesDetail.this, ICThome.class);
                home.putExtra(COURSE_BUNDLE, "Home");
                startActivity(home);
                return super.onOptionsItemSelected(item);
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        finish();
        Intent intent = new Intent(CoursesDetail.this, ICThome.class);
        intent.putExtra(COURSE_BUNDLE, "Home" );
        startActivity(intent);
    }
}
