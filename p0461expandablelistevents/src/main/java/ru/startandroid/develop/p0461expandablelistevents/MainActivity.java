package ru.startandroid.develop.p0461expandablelistevents;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = "MyLog";
    private TextView tvInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ExpandableListView elvMain = (ExpandableListView) findViewById(R.id.elvMain);
        final AdapterHelper adapterHelper = new AdapterHelper(this);
        elvMain.setAdapter(adapterHelper.getAdapter());
        /*Log.d(LOG_TAG, adapterHelper.getGroupAndChildName(0,0));
        Log.d(LOG_TAG, adapterHelper.getGroupAndChildName(1,0));
        Log.d(LOG_TAG, adapterHelper.getGroupAndChildName(2,0));*/

        tvInfo = (TextView) findViewById(R.id.tvInfo);

        elvMain.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                Log.d(LOG_TAG, "Click: groupPosition=" + groupPosition);
                tvInfo.setText("Click: " + adapterHelper.getGroupName(groupPosition));

                if (groupPosition == 1) return true;

                return false;
            }
        });
        elvMain.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Log.d(LOG_TAG, "Click: groupPosition=" + groupPosition + ", childPosition=" + childPosition);
                tvInfo.setText("Click: " + adapterHelper.getGroupAndChildName(groupPosition, childPosition));
                return false;
            }
        });
        elvMain.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                Log.d(LOG_TAG, "Expand: groupPosition=" + groupPosition);
                tvInfo.setText("Expand: " + adapterHelper.getGroupName(groupPosition));
            }
        });
        elvMain.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
                Log.d(LOG_TAG, "Collapse: groupPosition=" + groupPosition);
                tvInfo.setText("Collapse: " + adapterHelper.getGroupName(groupPosition));
            }
        });

    }

    private class AdapterHelper {
        private Context ctx;
        String groups[] = new String[]{"LG", "Sony", "Meizu"};
        String phonesLG[] = new String[]{"Nexus 4", "Nexus 5", "G3", "G4"};
        String phonesSony[] = new String[]{"Xperia A", "Xperia C", "Xperia S", "Xperia X"};
        String phonesMeizu[] = new String[]{"M1", "M2", "M Pro", "M Note"};
        private ArrayList<Map<String, String>> groupData;
        private HashMap<String, String> m;
        private ArrayList<ArrayList<Map<String, String>>> childData;
        private ArrayList<Map<String, String>> chilDataItem;

        public AdapterHelper(Context ctx){
            this.ctx = ctx;
        }

        ExpandableListAdapter getAdapter(){
            groupData = new ArrayList<Map<String, String>>();
            for (String group:groups){
                m = new HashMap<String, String>();
                m.put("groupName", group);
                groupData.add(m);
            }
            String groupFrom[] = new String[]{"groupName"};
            int groupTo[] = new int[]{android.R.id.text1};

            childData = new ArrayList<ArrayList<Map<String, String>>>();
            chilDataItem = new ArrayList<Map<String, String>>();
            for (String phone:phonesLG){
                m = new HashMap<String, String>();
                m.put("phoneName", phone);
                chilDataItem.add(m);
            }
            childData.add(chilDataItem);

            chilDataItem = new ArrayList<Map<String, String>>();
            for (String phone:phonesSony){
                m = new HashMap<String, String>();
                m.put("phoneName", phone);
                chilDataItem.add(m);
            }
            childData.add(chilDataItem);

            chilDataItem = new ArrayList<Map<String, String>>();
            for (String phone:phonesMeizu){
                m = new HashMap<String, String>();
                m.put("phoneName", phone);
                chilDataItem.add(m);
            }
            childData.add(chilDataItem);

            String childFrom[] = new String[]{"phoneName"};
            int childTo[] = new int[]{android.R.id.text1};

            SimpleExpandableListAdapter adapter = new SimpleExpandableListAdapter(
                    ctx,
                    groupData,
                    android.R.layout.simple_expandable_list_item_1,
                    groupFrom,
                    groupTo,
                    childData,
                    android.R.layout.simple_list_item_1,
                    childFrom,
                    childTo
            );
            return adapter;
        }

        String getGroupName(int groupPos){
            return groupData.get(groupPos).get("groupName").toString();
        }
        String getChildName(int groupPos, int childPos){
            return childData.get(groupPos).get(childPos).get("phoneName").toString();
        }
        String getGroupAndChildName(int groupPos, int childPos){
            return getGroupName(groupPos)+ " " + getChildName(groupPos, childPos);
        }
    }
}
