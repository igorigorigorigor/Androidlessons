package ru.startandroid.develop.p0231oneactivitystate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("MyLog", "onCreate");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("MyLog", "onResume");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("MyLog", "onStart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("MyLog", "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("MyLog", "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("MyLog", "onDestroy");
    }
}
