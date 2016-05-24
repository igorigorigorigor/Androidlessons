package ru.startandroid.develop.p0261intentfilter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ActivityDateEx extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date);

        SimpleDateFormat format = new SimpleDateFormat("EEE, MMM d, yyyy");
        String date = format.format(new Date(System.currentTimeMillis()));
        TextView tv = (TextView)findViewById(R.id.tvDate);
        tv.setText(date);
    }
}
