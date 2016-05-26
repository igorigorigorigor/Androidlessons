package ru.startandroid.develop.p0281intentextras;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        String fname = getIntent().getStringExtra("fname");
        String lname = getIntent().getStringExtra("lname");
        TextView tv = (TextView)findViewById(R.id.tvView);
        tv.setText("Your name is " + fname + " " + lname);
    }
}
