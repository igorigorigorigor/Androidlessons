package ru.startandroid.develop.p0511simpleadapterdata;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {


    private static final String LOG_TAG = "MyLog";
    private static final int CM_DELETE_ID = 1;
    private final String ATTRIBUTE_NAME_TEXT = "text";
    private final String ATTRIBUTE_NAME_IMAGE = "image";
    private final int dataSize = 5;
    private Map map;
    private String[] text;
    private ArrayList<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
    private SimpleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        data = new ArrayList<Map<String, Object>>();

        text = new String[dataSize];
        for (int i = 0; i < text.length; i++){
            text[i] = "sometext" + i;
            map = new HashMap<String, Object>();
            map.put(ATTRIBUTE_NAME_TEXT, text[i]);
            map.put(ATTRIBUTE_NAME_IMAGE, R.drawable.ic_launcher);
            data.add(map);
        }

        String[] from = new String[]{ATTRIBUTE_NAME_TEXT, ATTRIBUTE_NAME_IMAGE};
        int[] to = new int[]{R.id.tvItem, R.id.imgItem};

        adapter = new SimpleAdapter(this, data, R.layout.item, from, to);
        ListView lvMain = (ListView) findViewById(R.id.llMain);
        lvMain.setAdapter(adapter);
        registerForContextMenu(lvMain);

        Button btnAdd = (Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                map = new HashMap<String, Object>();
                map.put(ATTRIBUTE_NAME_TEXT, "sometext" + data.size());
                map.put(ATTRIBUTE_NAME_IMAGE, R.drawable.ic_launcher);
                data.add(map);
                adapter.notifyDataSetChanged();
                Log.d(LOG_TAG, "Add is clicked in listener.");
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0, CM_DELETE_ID, 0, "Delete");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getItemId() == CM_DELETE_ID){
            AdapterView.AdapterContextMenuInfo acmi = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            
            data.remove(acmi.position);
            adapter.notifyDataSetChanged();
        }
        return super.onContextItemSelected(item);
    }
}
