package ru.startandroid.develop.p0241twoactivitystate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class ActivityTwo extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
        Log.d("MyLog", "ActivityTwo: onCreate");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("MyLog", "ActivityTwo: onResume");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("MyLog", "ActivityTwo: onStart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("MyLog", "ActivityTwo: onRestart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("MyLog", "ActivityTwo: onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("MyLog", "ActivityTwo: onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("MyLog", "ActivityTwo: onDestroy");
    }
}
