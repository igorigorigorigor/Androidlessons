package ru.startandroid.develop.p0241twoactivitystate;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btActivityTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("MyLog", "MainActivity: onCreate");

        btActivityTwo = (Button) findViewById(R.id.btActivityTwo);
        btActivityTwo.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("MyLog", "MainActivity: onResume");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("MyLog", "MainActivity: onStart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("MyLog", "MainActivity: onRestart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("MyLog", "MainActivity: onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("MyLog", "MainActivity: onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("MyLog", "MainActivity: onDestroy");
    }

    @Override
    public void onClick(View v) {
        Intent activityTwo = new Intent(this, ActivityTwo.class);
        startActivity(activityTwo);
    }
}
