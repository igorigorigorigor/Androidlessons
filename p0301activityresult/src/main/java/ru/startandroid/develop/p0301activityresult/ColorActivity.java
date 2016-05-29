package ru.startandroid.develop.p0301activityresult;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ColorActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnRed;
    private Button btnBlue;
    private Button btnGreen;
    private Button btnBlack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color);

        btnRed = (Button) findViewById(R.id.btnRed);
        btnBlue = (Button) findViewById(R.id.btnBlue);
        btnGreen = (Button) findViewById(R.id.btnGreen);
        btnBlack = (Button) findViewById(R.id.btnBlack);
        btnRed.setOnClickListener(this);
        btnBlue.setOnClickListener(this);
        btnGreen.setOnClickListener(this);
        btnBlack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent();
        switch (v.getId()) {
            case R.id.btnRed:
                i.putExtra(MainActivity.EXTRA_ACTION_NAME, Color.RED);
                break;
            case R.id.btnBlue:
                i.putExtra(MainActivity.EXTRA_ACTION_NAME, Color.BLUE);
                break;
            case R.id.btnGreen:
                i.putExtra(MainActivity.EXTRA_ACTION_NAME, Color.GREEN);
                break;
            case R.id.btnBlack:
                i.putExtra(MainActivity.EXTRA_ACTION_NAME, Color.BLACK);
                break;
        }
        setResult(RESULT_OK, i);
        finish();
    }
}
