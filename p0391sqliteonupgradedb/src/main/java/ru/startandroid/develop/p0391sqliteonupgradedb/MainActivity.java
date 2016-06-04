package ru.startandroid.develop.p0391sqliteonupgradedb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    public static final String MYDATABASE = "mydatabase";
    private static final String table1 = "people";
    private static final String table2 = "positions";
    private static final String LOG_TAG = "MyLog";
    final int DB_VERSION = 2;

    String[] people_name = { "Иван", "Марья", "Петр", "Антон", "Даша",
            "Борис", "Костя", "Игорь" };
    String[] people_positions = { "Программер", "Бухгалтер",
            "Программер", "Программер", "Бухгалтер", "Директор",
            "Программер", "Охранник" };
    private DbHelper dbhelper;

    int[] position_id = { 1, 2, 3, 4 };
    String[] position_name = { "Директор", "Программер",
            "Бухгалтер", "Охранник" };
    int[] position_salary = { 15000, 13000, 10000, 8000 };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbhelper = new DbHelper(this);
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        Log.d(LOG_TAG, "DB version: " + db.getVersion());
        writeStuff(db);
    }

    private void writeStuff(SQLiteDatabase db){
        Cursor c = db.query(table1, null,null,null,null,null,null);
        logCursor(c, table1);
        c.close();

        c = db.rawQuery("select * from positions", null);
        logCursor(c, table2);
        c.close();

        String sqlQuery = "select PL.name as Name, PS.position as Position from people as PL inner join positions as PS on PL._id = PS._id";
        c = db.rawQuery(sqlQuery, null);
        logCursor(c, "inner join");
        c.close();
    }

    private void logCursor(Cursor cursor, String tablename) {
        Log.d(LOG_TAG, "Reading a table: " + tablename);
        if (cursor.moveToFirst()){
            int i = 0;
            do{
                StringBuilder sb = new StringBuilder();
                sb.append("Row #" + i + ": ");
                for (String colName : cursor.getColumnNames()){
                    sb.append(colName + "=" + cursor.getString(cursor.getColumnIndex(colName))+ "; ");
                }
                Log.d(LOG_TAG, sb.toString());
                i++;
            }while (cursor.moveToNext());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbhelper.close();
    }

    private class DbHelper extends SQLiteOpenHelper{
        public DbHelper(Context context) {
            super(context, MYDATABASE, null, DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            Log.d(LOG_TAG, "---Creating table: " + table1 + "---");
            db.execSQL("create table " + table1 + "(_id integer primary key autoincrement, name text, position text);");

            Log.d(LOG_TAG, "---Adding values: " + table1 + "---");
            for (int i = 0; i < people_name.length; i++){
                ContentValues cv = new ContentValues();
                cv.put("name", people_name[i]);
                cv.put("position", people_positions[i]);
                db.insert(table1, null, cv);
            }

            db.execSQL("create table " + table2 + "(_id integer primary key, position text, salary text);");
            for (int i = 0; i < position_id.length; i++){
                ContentValues cv = new ContentValues();
                cv.put("_id", position_id[i]);
                cv.put("position", position_name[i]);
                cv.put("salary", position_salary[i]);
                db.insert(table2, null, cv);
            }

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.d(LOG_TAG, "--- Upgrading database from " + oldVersion + " to " + newVersion+  " version --- ");

            if (oldVersion == 1 && newVersion == 2){


                try{
                    db.beginTransaction();

                    db.execSQL("alter table " + table1 + " add column posid integer");

                    for (int i = 0; i<position_id.length;i++){
                        ContentValues cv = new ContentValues();
                        cv.put("posid", position_id[i]);
                        db.update(table1, cv, "position = ?", new String[]{position_name[i]});
                    }

                    db.execSQL("create temporary table people_tmp(id integer, name text, position text, posid integer);");

                    db.execSQL("insert into people_tmp select _id, name, position, posid from people");
                    db.execSQL("drop table people");

                    db.execSQL("create table people(_id integer primary key autoincrement, name text, posid integer);");

                    db.execSQL("insert into people select id, name, posid from people_tmp");
                    db.execSQL("drop table people_tmp");

                    db.setTransactionSuccessful();
                }finally {
                    db.endTransaction();
                }
            }
        }
    }
}
