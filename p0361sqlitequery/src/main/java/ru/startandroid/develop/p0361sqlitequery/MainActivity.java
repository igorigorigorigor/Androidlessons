package ru.startandroid.develop.p0361sqlitequery;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnAllRecords;
    private Button btnFunction;
    private EditText etFunction;
    private Button btnPopulationMore;
    private EditText etPopulationMore;
    private Button btnPopulationRegion;
    private Button btnPopulationRegionMore;
    private EditText etPopulationRegionMore;
    private Button btnSort;
    private RadioButton rbName;
    private RadioButton rbPopulation;
    private RadioButton rbRegion;
    private static final String LOG_TAG = "MyLog";

    String name[] = { "Китай", "США", "Бразилия", "Россия", "Япония",
            "Германия", "Египет", "Италия", "Франция", "Канада" };
    int population[] = { 1400, 311, 195, 142, 128, 82, 80, 60, 66, 35 };
    String region[] = { "Азия", "Америка", "Америка", "Европа", "Азия",
            "Европа", "Африка", "Европа", "Европа", "Америка" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAllRecords = (Button) findViewById(R.id.btnAllRecords);
        btnFunction = (Button) findViewById(R.id.btnFunction);
        etFunction = (EditText) findViewById(R.id.etFunction);
        btnPopulationMore = (Button) findViewById(R.id.btnPopulationMore);
        etPopulationMore = (EditText) findViewById(R.id.etPopulationMore);
        btnPopulationRegion = (Button) findViewById(R.id.btnPopulationRegion);
        btnPopulationRegionMore = (Button) findViewById(R.id.btnPopulationRegionMore);
        etPopulationRegionMore = (EditText) findViewById(R.id.etPopulationRegionRow);
        btnSort = (Button) findViewById(R.id.btnSort);
        rbName = (RadioButton) findViewById(R.id.rbName);
        rbPopulation = (RadioButton) findViewById(R.id.rbPopulation);
        rbRegion = (RadioButton) findViewById(R.id.rbRegion);

        btnAllRecords.setOnClickListener(this);
        btnFunction.setOnClickListener(this);
        etFunction.setOnClickListener(this);
        btnPopulationMore.setOnClickListener(this);
        etPopulationMore.setOnClickListener(this);
        btnPopulationRegion.setOnClickListener(this);
        btnPopulationRegionMore.setOnClickListener(this);
        etPopulationRegionMore.setOnClickListener(this);
        btnSort.setOnClickListener(this);
        rbName.setOnClickListener(this);
        rbPopulation.setOnClickListener(this);
        rbRegion.setOnClickListener(this);

        DbHelper dbhelper = new DbHelper(this);

        SQLiteDatabase db = dbhelper.getWritableDatabase();
        Cursor c = db.query("mytable", null, null, null, null, null, null);
        if (c.getCount() == 0) {
            for (int i = 0; i < name.length; i++) {
            ContentValues cv = new ContentValues();
                cv.put("name", name[i]);
                cv.put("region", region[i]);
                cv.put("population", population[i]);
                db.insert("mytable", null, cv);
            }
        }
        c.close();
        db.close();
        dbhelper.close();
    }


    @Override
    public void onClick(View v) {
        DbHelper dbhelper = new DbHelper(this);

        SQLiteDatabase db = dbhelper.getWritableDatabase();
        Cursor c = null;
        switch (v.getId()){
            case R.id.btnAllRecords:
                Log.d(LOG_TAG, "---All countries---");
                c = db.query("mytable", null, null, null, null, null, null);
                break;
            case R.id.btnFunction:
                String function = etFunction.getText().toString();
                Log.d(LOG_TAG, "---All countries, using funtion - " + function + "---");
                c = db.query("mytable", new String[]{function}, null, null, null, null, null);
                break;
            case R.id.btnPopulationMore:
                String populationCondition = etPopulationMore.getText().toString();
                Log.d(LOG_TAG, "---Countries, where population > " + populationCondition + "---");
                c = db.query("mytable", null, "population > ?", new String[]{populationCondition}, null, null, null);
                break;
            case R.id.btnPopulationRegion:
                Log.d(LOG_TAG, "---Countries sorted by Region---");
                c = db.query("mytable", new String[]{"region", "sum(population) as population"}, null,null,  "region", null, null);
                break;
            case R.id.btnPopulationRegionMore:
                String regionCondition = etPopulationRegionMore.getText().toString();
                Log.d(LOG_TAG, "---Countries sorted by Region, where population > " + regionCondition + "---");
                c = db.query("mytable",new String[]{"region", "sum(population) as population"}, null ,null, "region", "sum(population) > " + regionCondition, null);
                break;
            case R.id.btnSort:
                if (rbName.isChecked()){
                    Log.d(LOG_TAG, "---Countries sorted by name---");
                    c = db.query("mytable",null, null, null, null, null, "name");
                } else if (rbPopulation.isChecked()){
                    Log.d(LOG_TAG, "---Countries sorted by population---");
                    c = db.query("mytable",null, null, null, null, null, "population");
                } else if (rbRegion.isChecked()){
                    Log.d(LOG_TAG, "---Countries sorted by region---");
                    c = db.query("mytable",null, null, null, null, null, "region");
                }
                break;
        }

        if  (c !=null && c.moveToFirst()){
            do {
                StringBuilder sb = new StringBuilder();
                for (String colName : c.getColumnNames()) {
                    sb.append(colName + "=" + c.getString(c.getColumnIndex(colName)) + "; ");
                }
                Log.d(LOG_TAG, "Row: " + sb);
            } while (c.moveToNext());
        }
        db.close();
        dbhelper.close();
    }

    private class DbHelper extends SQLiteOpenHelper{
        public DbHelper(Context context) {
            super(context, "mytable", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            Log.d(LOG_TAG, "---OnCreate:mytable---");
            db.execSQL("create table mytable(id integer primary key autoincrement, name text, population int, region text);");
            Log.d(LOG_TAG, "mytable created.");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
}
