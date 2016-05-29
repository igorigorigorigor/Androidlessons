package ru.startandroid.develop.p0311simpleintents;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnWeb;
    private Button btnMap;
    private Button btnCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnWeb = (Button) findViewById(R.id.btnWeb);
        btnMap = (Button) findViewById(R.id.btnMap);
        btnCall = (Button) findViewById(R.id.btnCall);
        btnWeb.setOnClickListener(this);
        btnMap.setOnClickListener(this);
        btnCall.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i;
        switch (v.getId()){
            case R.id.btnWeb:
                i = new Intent(Intent.ACTION_VIEW, Uri.parse("http://developer.android.com"));
                startActivity(i);
                break;
            case R.id.btnMap:
                i = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:55.780836,49.125402"));
                startActivity(i);
                break;
            case R.id.btnCall:
                i = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:79377729117"));
                startActivity(i);
                break;
        }
    }
}
