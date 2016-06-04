package ru.startandroid.develop.p0381sqlitetransaction;

import android.annotation.TargetApi;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = "MyLog";
    public static final String dbName = "mydatabase";
    private DbHelper dbhelper;
    private SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbhelper = new DbHelper(this);

        myActions();
    }


    private void myActions(){
        db = dbhelper.getWritableDatabase();
        try{
            db.beginTransaction();
            delete(db, "mytable");
            insert(db, "mytable", "val", "val1");
            insert(db, "mytable", "val", "val2");
            read(db, "mytable");
            db.setTransactionSuccessful();
        }finally {
            db.endTransaction();
        }
    }

    private void read(SQLiteDatabase db, String tablename) {
        Log.d(LOG_TAG, "Reading from a table: "  + tablename);
        logCursor(db.query(tablename, null,  null,  null, null, null, null));
    }

    private  void logCursor(Cursor c){
        if (c.moveToFirst()){
            int i = 1;
            do {
                StringBuilder sb = new StringBuilder();
                sb.append("Row #" + i + ": ");
                for (String colName : c.getColumnNames()){
                    sb.append(colName + "=" + c.getString(c.getColumnIndex(colName)) + "; ");
                }
                Log.d(LOG_TAG, sb.toString());
                i++;
            }while (c.moveToNext());
        }

    }

    private void insert(SQLiteDatabase db, String tablename, String key, String value) {
        Log.d(LOG_TAG, "Inserting <" + key + ", " + value + "> to a table: "  + tablename);
        ContentValues cv = new ContentValues();
        cv.put(key, value);
        db.insert(tablename, null, cv);
    }

    private void delete(SQLiteDatabase db, String tablename) {
        Log.d(LOG_TAG, "Deleting a table: "  + tablename);
        db.delete(tablename, null, null);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(LOG_TAG, "---Deleting database: " + dbName + "---");
        this.deleteDatabase(dbName);
        dbhelper.close();
    }

    private class DbHelper extends SQLiteOpenHelper{
        public DbHelper(Context context) {
            super(context, dbName, null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            Log.d(LOG_TAG, "---Creating database: "  + dbName+ "---");
            db.execSQL("create table mytable(id integer primary key autoincrement, val text);");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
}
