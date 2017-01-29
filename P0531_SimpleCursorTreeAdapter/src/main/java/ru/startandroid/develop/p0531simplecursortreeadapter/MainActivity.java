package ru.startandroid.develop.p0531simplecursortreeadapter;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ExpandableListView;
import android.widget.SimpleCursorTreeAdapter;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>{

    private DB mDatabase;
    private SimpleCursorTreeAdapter mSimpleCursorTreeAdapter;
    private HashMap<Integer, Integer> mGroupMap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDatabase = new DB(this);
        mDatabase.open();


        String[] groupFrom = { DB.COMPANY_COLUMN_NAME };
        int[] groupTo = { android.R.id.text1 };

        String[] childFrom = { DB.PHONE_COLUMN_NAME };
        int[] childTo = { android.R.id.text2 };

        mSimpleCursorTreeAdapter = new MyAdapter(this, null, android.R.layout.simple_expandable_list_item_1, groupFrom, groupTo, android.R.layout.simple_expandable_list_item_2, childFrom, childTo);

        ExpandableListView elvMain = (ExpandableListView) findViewById(R.id.elvMain);
        elvMain.setAdapter(mSimpleCursorTreeAdapter);

        mGroupMap = new  HashMap<Integer, Integer>();

        getSupportLoaderManager().initLoader(0, null, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        if (id == 0) {
            return new CompanyLoader(this, mDatabase);
        } else {
            return new PhoneLoader(this, mDatabase, id);
        }
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        if (loader.getId() == 0){
            mSimpleCursorTreeAdapter.setGroupCursor(data);
        } else {
            if (!data.isClosed()) {
                try {
                    mSimpleCursorTreeAdapter.setChildrenCursor(mGroupMap.get(loader.getId()), data);
                    mSimpleCursorTreeAdapter.setChildrenCursor(mGroupMap.get(loader.getId()), data);
                } catch (NullPointerException e){
                    Log.d("onLoadFinished", "NLP");
                }
            }
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDatabase.close();
    }

    private class MyAdapter extends SimpleCursorTreeAdapter{

        public MyAdapter(Context context, Cursor cursor, int groupLayout, String[] groupFrom, int[] groupTo, int childLayout, String[] childFrom, int[] childTo) {
            super(context, cursor, groupLayout, groupFrom, groupTo, childLayout, childFrom, childTo);
        }

        @Override
        protected Cursor getChildrenCursor(Cursor groupCursor) {
            int companyIdColumnIndex = groupCursor.getColumnIndex(DB.COMPANY_COLUMN_ID);
            int companyID = groupCursor.getInt(companyIdColumnIndex);
            mGroupMap.put(companyID, groupCursor.getPosition());
            Loader loader = getSupportLoaderManager().getLoader(companyID);
            //if ( loader != null && loader.isReset() ) {
            if ( loader != null  ) {
                loader.forceLoad();
            } else {
                getSupportLoaderManager().initLoader(companyID, null, MainActivity.this);
            }
            return null;
        }
    }

    static class CompanyLoader extends CursorLoader {
        DB sDB;

        public CompanyLoader(Context context, DB db) {
            super(context);
            sDB = db;
        }

        public Cursor loadInBackground(){
            Cursor cursor = sDB.getCompanyData();
            return cursor;
        }
    }

    static class PhoneLoader extends CursorLoader {
        DB sDB;
        long sCompanyID;

        public PhoneLoader(Context context, DB db, long companyID) {
            super(context);
            sDB = db;
            sCompanyID = companyID;
        }

        public Cursor loadInBackground(){
            Cursor cursor = sDB.getPhoneData(sCompanyID);
            return cursor;
        }
    }
}
