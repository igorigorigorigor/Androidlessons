package ru.startandroid.develop.p0321simplebrowser;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import java.net.URL;

public class WebViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        WebView wvBrowser = (WebView) findViewById(R.id.wvBrowser);
        Uri url = getIntent().getData();
        wvBrowser.loadUrl(String.valueOf(url));
    }
}
