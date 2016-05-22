package ru.startandroid.develop.dynamiclayout3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener{

    private Button button1;
    private Button button2;
    private LinearLayout.LayoutParams layoutParams1;
    private LinearLayout.LayoutParams layoutParams2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SeekBar sbWeight = (SeekBar) findViewById(R.id.sbWeight);
        sbWeight.setOnSeekBarChangeListener(this);

        button1 = (Button) findViewById(R.id.Button1);
        button2 = (Button) findViewById(R.id.Button2);

        layoutParams1 = (LinearLayout.LayoutParams) button1.getLayoutParams();
        layoutParams2 = (LinearLayout.LayoutParams) button2.getLayoutParams();
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        int leftValue = progress;
        int rightValue = seekBar.getMax() - leftValue;

        layoutParams1.weight = leftValue;
        layoutParams2.weight = rightValue;

        button1.setText(String.valueOf(leftValue));
        button2.setText(String.valueOf(rightValue));
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
