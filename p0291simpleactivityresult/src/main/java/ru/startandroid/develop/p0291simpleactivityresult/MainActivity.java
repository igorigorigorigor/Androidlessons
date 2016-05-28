package ru.startandroid.develop.p0291simpleactivityresult;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnInputName;
    private TextView tvView;
    public static final String nameExtra = "ru.startandroid.develop.p0291simpleactivityresult.name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnInputName = (Button)findViewById(R.id.btnInputName);
        btnInputName.setOnClickListener(this);
        tvView = (TextView)findViewById(R.id.tvNameResult);
    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent (MainActivity.this, ResultActivity.class);
        startActivityForResult(i, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null){
            Toast.makeText(this, "Activity Result is null.", Toast.LENGTH_LONG).show();
            return;
        }
        tvView.setText(data.getStringExtra(nameExtra));
    }
}
