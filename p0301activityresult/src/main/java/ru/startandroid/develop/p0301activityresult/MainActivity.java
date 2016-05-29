package ru.startandroid.develop.p0301activityresult;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String EXTRA_COLOR = "Color";
    public static final String EXTRA_ALLIGNMENT = "Allignment";
    private Button btnColor;
    private Button btnAllignment;
    public static final String EXTRA_ACTION_NAME = "ru.startandroid.develop.p0301activityresult.action_value";
    public static final int ColorActivityReqCode = 0;
    public static final int AllignActivityReqCode = 1;
    private TextView tvView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvView = (TextView) findViewById(R.id.tvView);
        btnColor = (Button) findViewById(R.id.btnColor);
        btnAllignment = (Button) findViewById(R.id.btnAllignment);
        btnAllignment.setOnClickListener(this);
        btnColor.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i;
        switch (v.getId()) {
            case R.id.btnColor:
                i = new Intent(MainActivity.this, ColorActivity.class);
                startActivityForResult(i, ColorActivityReqCode);
                break;
            case R.id.btnAllignment:
                i = new Intent(MainActivity.this, AllignmentActivity.class);
                startActivityForResult(i, AllignActivityReqCode);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case ColorActivityReqCode:
                    tvView.setTextColor(data.getIntExtra(EXTRA_ACTION_NAME, Color.WHITE));
                    break;
                case AllignActivityReqCode:
                    tvView.setGravity(data.getIntExtra(EXTRA_ACTION_NAME, Gravity.CENTER));
                    break;
            }
        } else if (resultCode == RESULT_CANCELED) {
            Toast.makeText(this, "The action was CANCELED!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "The action returned unexpected result!", Toast.LENGTH_SHORT).show();
        }

    }
}