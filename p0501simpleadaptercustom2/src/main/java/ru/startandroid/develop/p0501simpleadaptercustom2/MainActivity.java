package ru.startandroid.develop.p0501simpleadaptercustom2;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = "MyLog";
    private final int dataSize = 10;
    private ListView lvMain;
    private String days[];
    private int load[];
    ArrayList data;
    private LinearLayout llMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        data = new ArrayList<Map<String, Object>>();

        days = new String[dataSize];
        load = new int [dataSize];
        Map<String, Object> map;
        for (int i=0; i < days.length; i++){
            Random rand = new Random();
            load[i] =  rand.nextInt(101);
            days[i] = "Day " + i + ". ";
            map = new HashMap<String, Object>();
            map.put("day", days[i]);
            map.put("load", "Load: " + String.valueOf(load[i]) + "%");
            map.put("result", load[i]);
            map.put("layout", load[i]);
            data.add(map);
        }

        String[] from = new String[] {"day", "load", "result", "layout"};
        int[] to = new int[]{R.id.tvItemDay, R.id.tvItemLoad, R.id.pbItemResult, R.id.llItem};

        //llMain = (LinearLayout) getLayoutInflater().inflate(R.layout.item, null);

        SimpleAdapter.ViewBinder viewBinder = new SimpleAdapter.ViewBinder() {
            @Override
            public boolean setViewValue(View view, Object data, String textRepresentation) {
                int value;
                switch (view.getId()){
                    case R.id.tvItemDay:
                    case R.id.tvItemLoad:
                        ((TextView)view).setText(data.toString());
                        break;
                    case R.id.pbItemResult:
                        value = Integer.valueOf(data.toString());
                        ((ProgressBar)view).setProgress(value);
                        break;
                    case R.id.llItem:
                        value = Integer.valueOf(data.toString());
                        if (value >= 0 && value < 30){
                            view.setBackgroundColor(getResources().getColor(R.color.Green));
                        } else if (value >= 30 && value < 70){
                            view.setBackgroundColor(getResources().getColor(R.color.Orange));
                        } else if (value >= 70 && value <= 100){
                            view.setBackgroundColor(getResources().getColor(R.color.Red));
                        }
                        break;
                }

                //((TextView)view).setText(data.toString() + "%");
                return true;
            }
        };
        SimpleAdapter adapter = new SimpleAdapter(this, data, R.layout.item, from, to);
        adapter.setViewBinder(viewBinder);

        lvMain = (ListView) findViewById(R.id.lvMain);
        lvMain.setAdapter(adapter);
    }

}
