package ru.startandroid.develop.dinamiclayout;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams layoutParamsForLayout = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        LinearLayout.LayoutParams layoutParamsForViews = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParamsForViews.leftMargin = 50;


        TextView tv = new TextView(this);
        tv.setText("TextView");
        linearLayout.addView(tv, layoutParamsForViews);

        Button button = new Button(this);
        button.setText("Button");
        linearLayout.addView(button, layoutParamsForViews);

        LinearLayout.LayoutParams centerGravityParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        centerGravityParams.gravity = Gravity.CENTER;

        Button button1 = new Button(this);
        button1.setText("Button1");
        linearLayout.addView(button1, centerGravityParams);

        setContentView(linearLayout, layoutParamsForLayout);

        LinearLayout.LayoutParams rightGravityParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        rightGravityParams.gravity = Gravity.RIGHT;
        Button button2 = new Button(this);
        button2.setText("Button2");
        linearLayout.addView(button2, rightGravityParams);
    }
}
