package ru.startandroid.develop.p0521simplecursoradapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import static android.widget.AdapterView.*;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = "MyLog";
    private static final int CM_DELETE_ID = 0;
    private final String TABLE_NAME = "testdata";
    private final String ATTRIBUTE_NAME_TEXT = "text";
    private final String ATTRIBUTE_NAME_IMAGE = "image";
    private ListView lvMain;
    private SQLiteDatabase db;
    String[] text;
    private SimpleCursorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        DatabaseHelper dbhelper = new DatabaseHelper(this);
        db = dbhelper.getWritableDatabase();
        Cursor c = db.query(TABLE_NAME, null, null, null, null, null, null);
        startManagingCursor(c);

        String[] from = {ATTRIBUTE_NAME_TEXT, ATTRIBUTE_NAME_IMAGE};
        int[] to = {R.id.tvItem, R.id.imgItem};
        adapter = new SimpleCursorAdapter(this, R.layout.item, c, from, to, 0);

        lvMain = (ListView) findViewById(R.id.lvMain);
        lvMain.setAdapter(adapter);
        registerForContextMenu(lvMain);

        Button btnAdd = (Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor c = db.query(TABLE_NAME, null, null, null, null, null, null);
                ContentValues cv = new ContentValues();
                cv.put(ATTRIBUTE_NAME_TEXT, "sometext" + c.getCount());
                cv.put(ATTRIBUTE_NAME_IMAGE, R.drawable.ic_launcher);
                db.insert(TABLE_NAME, null, cv);
                c = db.query(TABLE_NAME, null, null, null, null, null, null);
                adapter.changeCursor(c);
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0, CM_DELETE_ID, 0, R.string.delete_record);
    }


    @Override
    public boolean onContextItemSelected(MenuItem item) {
        Log.d(LOG_TAG, "item context started;");
        if (item.getItemId()==CM_DELETE_ID){
            AdapterContextMenuInfo acmi = (AdapterContextMenuInfo) item.getMenuInfo();
            db.delete(TABLE_NAME, "_id = ?", new String[]{String.valueOf(acmi.id)});
            Cursor c = db.query(TABLE_NAME, null, null, null, null, null, null);
            adapter.changeCursor(c);
            Log.d(LOG_TAG, "item deleted;");
        }
        return super.onContextItemSelected(item);
    }

    private class DatabaseHelper extends SQLiteOpenHelper{
        public DatabaseHelper(Context context) {
            super(context, "mydatabase", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            Log.d(LOG_TAG, "--- onCreate database ---");
            db.execSQL("create table " + TABLE_NAME + " (_id integer primary key autoincrement, text text, image integer);");
            for (int i = 0; i < 10; i++) {
                ContentValues cv = new ContentValues();
                cv.put(ATTRIBUTE_NAME_TEXT, "sometext" + i);
                cv.put(ATTRIBUTE_NAME_IMAGE, R.drawable.ic_launcher);
                db.insert(TABLE_NAME, null, cv);
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        db.close();
    }
}
