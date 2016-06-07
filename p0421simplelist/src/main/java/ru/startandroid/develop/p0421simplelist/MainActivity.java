package ru.startandroid.develop.p0421simplelist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    String[] names = { "Иван", "Марья", "Петр", "Антон", "Даша", "Борис",
            "Костя", "Игорь", "Анна", "Денис", "Андрей" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView lvStuff = (ListView) findViewById(R.id.lvStuff);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.my_list_item, names);

        lvStuff.setAdapter(adapter);
    }
}
