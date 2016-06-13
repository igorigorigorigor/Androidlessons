package ru.startandroid.develop.p0481simpleadapter;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    final String ATTRIBUTE_NAME_TEXT = "text";
    final String ATTRIBUTE_NAME_CHECKED = "checked";
    final String ATTRIBUTE_NAME_IMAGE = "image";
    private ListView lvMain;
    private Map m;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] texts = {"Some text 1","Some text 2", "Some text 3", "Some text 4", "Some text 5", "Some text 6", "Some text 7"};
        Boolean[] checkboxes = {true, false, false, true, false, true, true};
        int[] imgs = {R.mipmap.ic_launcher};

        String[] from = {"text", "checked", "img", "text"};
        int[] to = {R.id.tvItem, R.id.chbItem, R.id.imgItem, R.id.chbItem};

        ArrayList textData = new ArrayList<Map<String, Object>>();
        for (int i=0; i<texts.length; i++) {
            m = new HashMap<String, Object>();
            m.put("text", texts[i]);
            m.put("checked", checkboxes[i]);
            m.put("img", imgs[0]);
            textData.add(m);
        }

        SimpleAdapter adapter = new SimpleAdapter(this, textData, R.layout.item, from, to);
        lvMain = (ListView) findViewById(R.id.lvMain);
        lvMain.setAdapter(adapter);
    }
}
