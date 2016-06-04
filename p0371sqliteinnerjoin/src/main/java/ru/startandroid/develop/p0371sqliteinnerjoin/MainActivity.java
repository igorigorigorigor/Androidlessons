package ru.startandroid.develop.p0371sqliteinnerjoin;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = "MyLog";

    // данные для таблицы должностей
    int[] position_id = { 1, 2, 3, 4 };
    String[] position_name = { "Директор", "Программер", "Бухгалтер", "Охранник" };
    int[] position_salary = { 15000, 13000, 10000, 8000 };

    // данные для таблицы людей
    String[] people_name = { "Иван", "Марья", "Петр", "Антон", "Даша", "Борис", "Костя", "Игорь" };
    int[] people_posid = { 2, 3, 2, 2, 3, 1, 2, 4 };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DbHelper dbhelper = new DbHelper(this);

        SQLiteDatabase db = dbhelper.getWritableDatabase();
        Log.d(LOG_TAG, "rawQuery");
        logCursor(db.rawQuery("Select people.name as Name, positions.position as Position from people inner join positions on people.pos_id = positions.pos_id;", null));
        Log.d(LOG_TAG, "ordinaryQuery");
        logCursor(db.query("people inner join positions on people.pos_id = positions.pos_id", new String[]{"people.name as Name, positions.position as Position"}, null, null, null, null, null));
        db.close();
        dbhelper.close();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(LOG_TAG, "---OnDelete mydatabase---");
        this.deleteDatabase("mydatabase");
    }

    public void logCursor(Cursor c){
        if  (c !=null && c.moveToFirst()){
            do {
                StringBuilder sb = new StringBuilder();
                for (String colName : c.getColumnNames()) {
                    sb.append(colName + "=" + c.getString(c.getColumnIndex(colName)) + "; ");
                }
                Log.d(LOG_TAG, "Row: " + sb);
            } while (c.moveToNext());
        }
        c.close();
    }

    private class DbHelper extends SQLiteOpenHelper {
        public DbHelper(Context context) {
            super(context, "mydatabase", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            Log.d(LOG_TAG,"---onCreate table for people---");
            db.execSQL("create table people(id integer primary key autoincrement, pos_id integer, name text);");
            ContentValues cv = new ContentValues();
            for (int i = 0; i < people_posid.length; i++){
                cv.put("pos_id", people_posid[i]);
                cv.put("name", people_name[i]);
                db.insert("people", null, cv);
            }
            cv.clear();

            logCursor(db.query("people", null, null, null, null, null, null));

            Log.d(LOG_TAG,"---onCreate table for positions---");
            db.execSQL("create table positions(pos_id integer primary key, position text, salary integer);");
            for (int i = 0; i < position_id.length; i++){
                cv.put("pos_id", position_id[i]);
                cv.put("position", position_name[i]);
                cv.put("salary", position_salary[i]);
                db.insert("positions", null, cv);
            }
            cv.clear();

            logCursor(db.query("positions", null, null, null, null, null, null));
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
}
