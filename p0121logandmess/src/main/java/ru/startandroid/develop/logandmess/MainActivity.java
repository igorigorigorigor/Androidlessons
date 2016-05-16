package ru.startandroid.develop.logandmess;

import android.net.wifi.p2p.WifiP2pManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    private TextView tvOut;
    private Button btnOk;
    private Button btnCancel;
    private static final String TAG = "myLogs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        Log.d(TAG, "Достаем View-объекты");
        tvOut = (TextView) findViewById(R.id.tvOut);
        btnOk = (Button) findViewById(R.id.btnOk);
        btnCancel = (Button) findViewById(R.id.btnCancel);

        Log.d(TAG, "Устанавливаем listener'ы для View-объектов");
        btnOk.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        return true;
    }

    @Override
    public void onClick(View v) {
        Log.d(TAG, "по id определяем кнопку, вызвавшую этот обработчик");
        switch (v.getId()) {
            case R.id.btnOk:
                Log.d(TAG, "кнопка ОК");
                tvOut.setText("Нажата кнопка ОК");
                Toast.makeText(MainActivity.this, "Нажата кнопка ОК", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnCancel:
                Log.d(TAG, "кнопка Cancel");
                tvOut.setText("Нажата кнопка Cancel");
                Toast.makeText(MainActivity.this, "Нажата кнопка Cancel", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
