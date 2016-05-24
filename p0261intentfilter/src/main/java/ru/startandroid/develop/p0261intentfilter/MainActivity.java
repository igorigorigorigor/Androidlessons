package ru.startandroid.develop.p0261intentfilter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnShowDate;
    private Button btnShowTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnShowTime = (Button)findViewById(R.id.btnShowTime);
        btnShowDate = (Button)findViewById(R.id.btnShowDate);
        btnShowDate.setOnClickListener(this);
        btnShowTime.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnShowDate:
                Intent showDate = new Intent("ru.startandroid.intent.action.showdate");
                startActivity(showDate);
                break;
            case R.id.btnShowTime:
                Intent showTime = new Intent("ru.startandroid.intent.action.showtime");
                startActivity(showTime);
                break;
        }
    }
}
