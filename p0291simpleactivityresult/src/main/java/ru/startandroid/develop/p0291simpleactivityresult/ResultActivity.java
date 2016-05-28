package ru.startandroid.develop.p0291simpleactivityresult;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class ResultActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnSubmit;
    private EditText etNameInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(this);
        etNameInput = (EditText) findViewById(R.id.etNameInput);
    }

    @Override
    public void onClick(View v) {
        if (!TextUtils.isEmpty(etNameInput.getText())){
            Intent i = new Intent();
            i.putExtra(MainActivity.nameExtra, etNameInput.getText().toString());
            setResult(RESULT_OK, i);
            finish();
        } else {
            Toast.makeText(this, "Please, fill in your name.", Toast.LENGTH_LONG).show();
        }
    }
}
