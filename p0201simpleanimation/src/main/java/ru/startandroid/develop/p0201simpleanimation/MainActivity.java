package ru.startandroid.develop.p0201simpleanimation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = (TextView) findViewById(R.id.tv);
        tv.setOnCreateContextMenuListener(this);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menu_context, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        Animation anim = null;
        switch (item.getItemId()){
            case R.id.myalpha:
                anim = AnimationUtils.loadAnimation(this, R.anim.myalpha);
                break;
            case R.id.mycombo:
                anim = AnimationUtils.loadAnimation(this, R.anim.mycombo);
                break;
            case R.id.myrotate:
                anim = AnimationUtils.loadAnimation(this, R.anim.myrotate);
                break;
            case R.id.myscale:
                anim = AnimationUtils.loadAnimation(this, R.anim.myscale);
                break;
            case R.id.mytrans:
                anim = AnimationUtils.loadAnimation(this, R.anim.mytrans);
                break;
        }
        tv.startAnimation(anim);
        return super.onContextItemSelected(item);
    }
}
