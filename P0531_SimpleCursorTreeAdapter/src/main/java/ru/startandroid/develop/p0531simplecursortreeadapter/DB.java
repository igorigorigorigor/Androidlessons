package ru.startandroid.develop.p0531simplecursortreeadapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Freeman on 24.01.2017.
 */

public class DB {
    private static final String DB_NAME = "mydb";
    private static final int DB_VERSION = 1;

    private static final String COMPANY_TABLE = "company";
    private static final String COMPANY_COLUMN_ID = "_id";
    private static final String COMPANY_COLUMN_NAME = "name";
    private static final String COMPANY_TABLE_CREATE = "create table " + COMPANY_TABLE + " (" + COMPANY_COLUMN_ID + " primary key integer, " + COMPANY_COLUMN_NAME + " text);";

    private static final String PHONE_TABLE = "phone";
    private static final String PHONE_COLUMN_ID = "_id";
    private static final String PHONE_COLUMN_NAME = "name";
    private static final String PHONE_COLUMN_COMPANY = "company";
    private static final String PHONE_TABLE_CREATE = "create table " + PHONE_TABLE + " (" + PHONE_COLUMN_ID + " primary key integer, " + PHONE_COLUMN_NAME + " text, " + PHONE_COLUMN_COMPANY + " integer);";

    private Context mContext;
    private DBHelper mDBHelper;
    private SQLiteDatabase mDatabase;


    public DB(Context mContext) {
        this.mContext = mContext;
    }

    public void open(){
        mDBHelper = new DBHelper(mContext, DB_NAME, null, DB_VERSION);
        mDatabase = mDBHelper.getWritableDatabase();
    }

    public void close() {
        if (mDBHelper != null){
            mDBHelper.close();
        }
    }

    public Cursor getCompanyData(){
        return mDatabase.query(COMPANY_TABLE, null, null, null, null, null, null);
    }

    public Cursor getPhoneData(long companyID){
        return mDatabase.query(PHONE_TABLE, null, PHONE_COLUMN_COMPANY + " = " + companyID, null, null, null, null);
    }

    private class DBHelper extends SQLiteOpenHelper {

        public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            ContentValues cv = new ContentValues();

            sqLiteDatabase.execSQL(COMPANY_TABLE_CREATE);
            String[] company_names = new String[]{"LG", "Samsung", "Apple"};
            for (int i = 0; i < company_names.length; i++ ){
                cv.put(COMPANY_COLUMN_NAME, company_names[i]);
                sqLiteDatabase.insert(COMPANY_TABLE, null, cv);
            };
            cv.clear();

            sqLiteDatabase.execSQL(PHONE_TABLE_CREATE);
            String[] lg_names = new String[]{"Nexus 4", "Nexus 5", "Nexus 5X"};
            for (int i = 0; i < lg_names.length; i++ ){
                cv.put(PHONE_COLUMN_NAME, lg_names[i]);
                cv.put(PHONE_COLUMN_COMPANY, 1);
                sqLiteDatabase.insert(PHONE_TABLE, null, cv);
            };
            cv.clear();

            String[] samsung_names = new String[]{"Galaxy 6", "Galaxy 7", "Galaxy Note"};
            for (int i = 0; i < samsung_names.length; i++ ){
                cv.put(PHONE_COLUMN_NAME, samsung_names[i]);
                cv.put(PHONE_COLUMN_COMPANY, 2);
                sqLiteDatabase.insert(PHONE_TABLE, null, cv);
            };
            cv.clear();

            String[] apple_names = new String[]{"iPhone 5", "iPhone 6", "iPhone 7"};
            for (int i = 0; i < samsung_names.length; i++ ){
                cv.put(PHONE_COLUMN_NAME, apple_names[i]);
                cv.put(PHONE_COLUMN_COMPANY, 3);
                sqLiteDatabase.insert(PHONE_TABLE, null, cv);
            };
            cv.clear();

        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        }
    }
}
