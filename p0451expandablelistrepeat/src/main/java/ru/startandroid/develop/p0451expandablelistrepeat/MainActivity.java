package ru.startandroid.develop.p0451expandablelistrepeat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    String groups[] = new String[]{"iPhone", "LG", "Meizu", "Sony"};

    String phonesSony[] = new String[]{"Xperia C", "Xperia U", "Xperia X", "Xperia S"};
    String phonesLG[] = new String[]{"Nexus 4", "Nexus 5", "Nexus 5X", "G3"};
    String phonesMeizu[] = new String[]{"M2", "Note", "Pro", "M1"};
    String phonesiPhones[] = new String[]{"4", "4S", "5", "5S", "5C"};

    ExpandableListView elvMain;
    private ArrayList groupData;
    private Map<String, String> m;
    private ArrayList<ArrayList<Map<String, String>>> childData;
    private ArrayList<Map<String, String>> childDataItem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        groupData = new ArrayList<Map<String, String>>();
        for (String group : groups){
            m = new HashMap<String, String>();
            m.put("groupName", group);
            groupData.add(m);
        }

        String groupFrom[] = new String[]{"groupName"};
        int groupTo[] = new int[]{android.R.id.text1};

        childData = new ArrayList<ArrayList<Map<String, String>>>();
        childDataItem = new ArrayList<Map<String, String>>();
        for (String phoneName : phonesiPhones){
            m = new HashMap<String, String>();
            m.put("phoneName", phoneName);
            childDataItem.add(m);
        }
        childData.add(childDataItem);

        childDataItem = new ArrayList<Map<String, String>>();
        for (String phoneName : phonesLG){
            m = new HashMap<String, String>();
            m.put("phoneName", phoneName);
            childDataItem.add(m);
        }
        childData.add(childDataItem);

        childDataItem = new ArrayList<Map<String, String>>();
        for (String phoneName : phonesMeizu){
            m = new HashMap<String, String>();
            m.put("phoneName", phoneName);
            childDataItem.add(m);
        }
        childData.add(childDataItem);

        childDataItem = new ArrayList<Map<String, String>>();
        for (String phoneName : phonesSony){
            m = new HashMap<String, String>();
            m.put("phoneName", phoneName);
            childDataItem.add(m);
        }
        childData.add(childDataItem);

        String childFrom[] = new String[]{"phoneName"};
        int childTo[] = new int[]{android.R.id.text1};

        SimpleExpandableListAdapter adapter = new SimpleExpandableListAdapter(
                this,
                groupData,
                android.R.layout.simple_expandable_list_item_1,
                groupFrom,
                groupTo,
                childData,
                android.R.layout.simple_list_item_1,
                childFrom,
                childTo
        );

        elvMain = (ExpandableListView) findViewById(R.id.elvMain);
        elvMain.setAdapter(adapter);
    }
}
