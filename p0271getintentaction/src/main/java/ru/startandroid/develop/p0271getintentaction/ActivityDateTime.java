package ru.startandroid.develop.p0271getintentaction;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ActivityDateTime extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_time);
        Log.d("MyLog", "ActivityDateTime.onCreate");

        SimpleDateFormat format;
        switch (getIntent().getAction()){
            case "ru.startandroid.intent.action.showtime":
            default:
                format = new SimpleDateFormat("HH:mm:ss");
                break;
            case "ru.startandroid.intent.action.showdate":
                format = new SimpleDateFormat("dd:MM:yyyy");
                break;
        }
        String time = format.format(new Date(System.currentTimeMillis()));
        TextView tv = (TextView)findViewById(R.id.tv);
        tv.setText(time);
    }
}
