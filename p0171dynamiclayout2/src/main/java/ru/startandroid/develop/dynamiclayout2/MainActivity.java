package ru.startandroid.develop.dynamiclayout2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private final static int wrap_content = LinearLayout.LayoutParams.WRAP_CONTENT;
    private RadioButton rbLeft;
    private RadioButton rbCenter;
    private RadioButton rbRight;
    private EditText etName;
    private Button btnCreate;
    private Button btnClear;
    private LinearLayout llMain;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rbLeft = (RadioButton) findViewById(R.id.rbLeft);
        rbCenter = (RadioButton) findViewById(R.id.rbCenter);
        rbRight = (RadioButton) findViewById(R.id.rbRight);
        etName = (EditText) findViewById(R.id.etName);

        btnCreate = (Button) findViewById(R.id.btnCreate);
        btnCreate.setOnClickListener(this);
        btnClear = (Button) findViewById(R.id.btnClear);
        btnClear.setOnClickListener(this);

        llMain = (LinearLayout) findViewById(R.id.llMain);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnClear:
                etName.setText("");
                llMain.removeAllViews();
                break;
            case R.id.btnCreate:
                Button button = new Button(this);
                if (etName.getText()!= null && etName.getText().length() > 0) {
                    button.setText(etName.getText());
                } else {
                    button.setText("defaultName");
                }

                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(wrap_content,wrap_content);

                if (rbLeft.isChecked()){
                    layoutParams.gravity = Gravity.LEFT;
                } else if (rbCenter.isChecked()){
                    layoutParams.gravity = Gravity.CENTER;
                } else if (rbRight.isChecked()){
                    layoutParams.gravity = Gravity.RIGHT;
                }
                llMain.addView(button, layoutParams);
                break;
        }
    }
}
