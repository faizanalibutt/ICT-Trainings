package ict_trainings.ictapp.courses.course_detail.webview;

import android.content.Intent;
import android.net.Uri;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MyAppWebViewClient extends WebViewClient {

    // handling external links as intents
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {

        if( Uri.parse(url).getHost().endsWith("drive.google.com") ) {
            return false;
        }

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        view.getContext().startActivity(intent);
        return true;
    }

}