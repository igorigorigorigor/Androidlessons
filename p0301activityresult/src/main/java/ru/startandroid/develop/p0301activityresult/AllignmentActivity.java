package ru.startandroid.develop.p0301activityresult;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AllignmentActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnLeft;
    private Button btnCenter;
    private Button btnRight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allignment);

        btnLeft = (Button) findViewById(R.id.btnLeft);
        btnCenter = (Button) findViewById(R.id.btnCenter);
        btnRight = (Button) findViewById(R.id.btnRight);
        btnLeft.setOnClickListener(this);
        btnCenter.setOnClickListener(this);
        btnRight.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent();
        switch (v.getId()){
            case R.id.btnLeft:
                i.putExtra(MainActivity.EXTRA_ACTION_NAME, Gravity.START);
                break;
            case R.id.btnCenter:
                i.putExtra(MainActivity.EXTRA_ACTION_NAME, Gravity.CENTER);
                break;
            case R.id.btnRight:
                i.putExtra(MainActivity.EXTRA_ACTION_NAME, Gravity.END);
                break;
        }
        setResult(RESULT_OK, i);
        finish();
    }
}
