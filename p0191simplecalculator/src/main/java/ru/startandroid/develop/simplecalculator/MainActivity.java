package ru.startandroid.develop.simplecalculator;

import android.content.Context;
import android.support.annotation.StringDef;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText etFirstOperand;
    private EditText etSecondOperand;
    private Button btnPlus;
    private Button btnMinus;
    private Button btnMultiply;
    private Button btnDivide;
    private TextView tvOutput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etFirstOperand = (EditText)findViewById(R.id.etFirstOperand);
        etSecondOperand = (EditText)findViewById(R.id.etSecondOperand );
        etFirstOperand.setFocusableInTouchMode(true);
        etFirstOperand.requestFocus();
        btnPlus = (Button) findViewById(R.id.btnPlus);
        btnMinus = (Button) findViewById(R.id.btnMinus);
        btnMultiply = (Button) findViewById(R.id.btnMultiply);
        btnDivide = (Button) findViewById(R.id.btnDivide);

        btnPlus.setOnClickListener(this);
        btnMinus.setOnClickListener(this);
        btnMultiply.setOnClickListener(this);
        btnDivide.setOnClickListener(this);

        tvOutput = (TextView) findViewById(R.id.tvOutput);
    }

    @Override
    public void onClick(View v) {
        float firstOperand = 0;
        float secondOperand = 0;
        float result = 0;

        if (TextUtils.isEmpty(etFirstOperand.getText())){
            Toast.makeText(this, "First operand is empty", Toast.LENGTH_SHORT).show();
            return;
        } else if (TextUtils.isEmpty(etFirstOperand.getText())){
            Toast.makeText(this, "Second operand is empty", Toast.LENGTH_SHORT).show();
            return;
        }
        firstOperand = Float.valueOf(etFirstOperand.getText().toString());
        secondOperand = Float.valueOf(etSecondOperand.getText().toString());

        String operator = "";
        switch (v.getId()){
            case R.id.btnPlus:
                result = firstOperand + secondOperand;
                operator = "+";
                break;
            case R.id.btnMinus:
                result = firstOperand - secondOperand;
                operator = "-";
                break;
            case R.id.btnMultiply:
                result = firstOperand * secondOperand;
                operator = "*";
                break;
            case R.id.btnDivide:
                if (Float.valueOf(etSecondOperand.getText().toString()) == 0){
                    Toast.makeText(this, "Second operand is ZERO", Toast.LENGTH_SHORT).show();
                    return;
                }
                result = firstOperand / secondOperand;
                operator = "/";
                break;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(firstOperand);
        sb.append(" ");
        sb.append(operator);
        sb.append(" ");
        sb.append(secondOperand);
        sb.append(" = ");
        sb.append(result);
        tvOutput.setText(String.valueOf(sb.toString()));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.MENU_CLEAR:
                etFirstOperand.setText("");
                etSecondOperand.setText("");
                tvOutput.setText("");
                break;
            case R.id.MENU_EXIT:
                finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
