package ru.startandroid.develop.p0331sharedpreferences;

import android.content.SharedPreferences;
import android.preference.Preference;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String SAVED_TEXT = "saved_text";
    private Button btnLoad;
    private Button btnSave;
    private EditText etPref;
    private SharedPreferences sPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLoad = (Button) findViewById(R.id.btnLoad);
        btnSave = (Button) findViewById(R.id.btnSave);
        etPref = (EditText) findViewById(R.id.etPref);
        btnLoad.setOnClickListener(this);
        btnSave.setOnClickListener(this);

        LoadText();
    }

    @Override
    public void onClick(View v) {
     switch (v.getId()){
         case R.id.btnLoad:
             LoadText();
             break;
         case R.id.btnSave:
             SaveText();
             break;
        }
    }

    private void SaveText() {
        sPref = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        ed.putString(SAVED_TEXT, etPref.getText().toString());
        ed.commit();
        Toast.makeText(this, "Text saved", Toast.LENGTH_SHORT).show();
    }

    private void LoadText() {
        sPref = getPreferences(MODE_PRIVATE);
        String savedText = sPref.getString(SAVED_TEXT, "");
        etPref.setText(savedText);
        Toast.makeText(this, "Text loaded", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        SaveText();
    }
}
