package ru.startandroid.develop.p0491simpleadaptercustom1;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Map<String, Object>> data;
    private Map m;
    private ListView lvMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] deltas = new String[]{"9", "4", "-3", "2", "-5", "0", "3", "-6"};
        String[] days = new String[]{"Day 1", "Day 2", "Day 3", "Day 4","Day 5", "Day 6", "Day 7", "Day 8"};

        String[] from = new String[]{"delta", "day", "img"};
        int[] to = new int[]{R.id.chbItem, R.id.tvItem, R.id.imgItem};

        data = new ArrayList<Map<String, Object>>();

        for (int i = 0; i < deltas.length; i++){
            m = new HashMap<String, Object>();
            m.put("delta", deltas[i]);
            m.put("day", days[i]);
            m.put("img", deltas[i]);
            data.add(m);
        }

        lvMain = (ListView) findViewById(R.id.lvMain);

        SimpleAdapter adapter = new DeltaSimpleAdapter(this, data, R.layout.item, from, to);
        lvMain.setAdapter(adapter);
    }

    private class DeltaSimpleAdapter extends SimpleAdapter {
        /**
         * Constructor
         *
         * @param context  The context where the View associated with this SimpleAdapter is running
         * @param data     A List of Maps. Each entry in the List corresponds to one row in the list. The
         *                 Maps contain the data for each row, and should include all the entries specified in
         *                 "from"
         * @param resource Resource identifier of a view layout that defines the views for this list
         *                 item. The layout file should include at least those named views defined in "to"
         * @param from     A list of column names that will be added to the Map associated with each
         *                 item.
         * @param to       The views that should display column in the "from" parameter. These should all be
         *                 TextViews. The first N views in this list are given the values of the first N columns
         */
        public DeltaSimpleAdapter(Context context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to) {
            super(context, data, resource, from, to);

        }

        @Override
        public void setViewText(TextView v, String text) {
            super.setViewText(v, text);

            if (v.getId() == R.id.chbItem) {
                int num = Integer.valueOf(text);
                if (num < 0) {
                    v.setTextColor(Color.RED);
                } else if (num == 0) {
                    v.setTextColor(Color.GRAY);
                } else {
                    v.setTextColor(Color.GREEN);
                }
            }
        }

        @Override
        public void setViewImage(ImageView v, String value) {
            if (v.getId()==R.id.imgItem){
                int i = Integer.valueOf(value);
                if (i < 0) {
                    v.setImageResource(R.drawable.arrow_down);
                    //v.setBackgroundColor(Color.GREEN);
                } else if(i == 0){
                    v.setImageResource(R.drawable.none);
                    //v.setBackgroundColor(Color.GRAY);
                } else {
                    v.setImageResource(R.drawable.arrow_up);
                    //v.setBackgroundColor(Color.RED);
                }
            } else {
                super.setViewImage(v, value);
            }
        }
    }
}
