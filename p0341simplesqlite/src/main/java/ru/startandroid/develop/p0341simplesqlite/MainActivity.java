package ru.startandroid.develop.p0341simplesqlite;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText etName;
    private EditText etEmail;
    private Button btnAdd;
    private Button btnRead;
    private Button btnClear;

    final String LOG_TAG = "MyLog";
    DBHelper dbHelper;
    private Button btnUpdateID;
    private Button btnDeleteID;
    private EditText edID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = (EditText) findViewById(R.id.etName);
        etEmail = (EditText) findViewById(R.id.etEmail);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnRead = (Button) findViewById(R.id.btnRead);
        btnClear = (Button) findViewById(R.id.btnClear);
        btnUpdateID = (Button) findViewById(R.id.btnUpdateID);
        btnDeleteID = (Button) findViewById(R.id.btnDeleteID);
        edID = (EditText) findViewById(R.id.edID);

        btnAdd.setOnClickListener(this);
        btnRead.setOnClickListener(this);
        btnClear.setOnClickListener(this);
        btnUpdateID.setOnClickListener(this);
        btnDeleteID.setOnClickListener(this);

        dbHelper = new DBHelper(this);
    }

    @Override
    public void onClick(View v) {
        ContentValues cv = new ContentValues();
        String name = etName.getText().toString();
        String email = etEmail.getText().toString();
        String id = edID.getText().toString();
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        switch (v.getId()){
            case R.id.btnAdd:
                Log.d(LOG_TAG, "---Insert in mytable: ---");
                cv.put("name", name);
                cv.put("email", email);
                long rowID = db.insert("mytable", null, cv);
                Log.d(LOG_TAG, "row inserted, ID = " + rowID);
                break;
            case R.id.btnRead:
                Log.d(LOG_TAG, "---Rows in mytable: ---");
                Cursor c = db.query("mytable", null, null, null, null, null, null);
                if (c.moveToFirst()){
                    int idColIndex = c.getColumnIndex("id");
                    int nameColIndex = c.getColumnIndex("name");
                    int emailColIndex = c.getColumnIndex("email");
                    do {
                        Log.d(LOG_TAG, "ID = " + c.getInt(idColIndex) + ", name = " + c.getString(nameColIndex) + ", email = " + c.getString(emailColIndex));
                    } while (c.moveToNext());
                } else {
                    Log.d(LOG_TAG, "0 rows");
                }
                c.close();
                break;
            case R.id.btnClear:
                Log.d(LOG_TAG, "---Clear mytable: ----");
                int clearCount = db.delete("mytable", null, null);
                Log.d(LOG_TAG, "deleted rows count = " + clearCount);
                break;
            case R.id.btnUpdateID:
                if (TextUtils.isEmpty(id)){
                    break;
                }
                Log.d(LOG_TAG, "---Update mytable: ----");
                cv.put("name", name);
                cv.put("email", email);
                int updateCount = db.update("mytable", cv, "id = ?", new String [] {id});
                Log.d(LOG_TAG, "Updated rows count = " + updateCount);
                break;
            case R.id.btnDeleteID:
                if (TextUtils.isEmpty(id)){
                    break;
                }
                Log.d(LOG_TAG, "---Delete from mytable: ----");
                int delCount = db.delete("mytable", "id = " + id, null);
                Log.d(LOG_TAG, "Deleted rows count = " + delCount);
                break;
        }
        dbHelper.close();
    }

    private class DBHelper extends SQLiteOpenHelper {
        public DBHelper(Context context) {
            super(context, "MyDB", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            Log.d(LOG_TAG, "---onCreate database---");
            db.execSQL("create table mytable (id integer primary key autoincrement, name text, email text);");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
}
