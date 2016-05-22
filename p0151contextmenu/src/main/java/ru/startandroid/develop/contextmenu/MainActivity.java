package ru.startandroid.develop.contextmenu;

import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class MainActivity extends AppCompatActivity {

    private TextView tvColor;
    private TextView tvSize;

    private static final int MENU_COLOR_RED = 1;
    private static final int MENU_COLOR_GREEN = 2;
    private static final int MENU_COLOR_BLUE = 3;

    private static final int MENU_SIZE_22 = 4;
    private static final int MENU_SIZE_26 = 5;
    private static final int MENU_SIZE_30 = 6;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvColor = (TextView) findViewById(R.id.tvColor);
        tvSize = (TextView) findViewById(R.id.tvSize);

        registerForContextMenu(tvColor);
        registerForContextMenu(tvSize);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menu_context, menu);
        switch (v.getId()) {
            case R.id.tvColor:
                menu.setGroupVisible(R.id.SizeGroup,false);
                /*menu.add(0, MENU_COLOR_RED, 0, "RED");
                menu.add(0, MENU_COLOR_GREEN, 0, "GREEN");
                menu.add(0, MENU_COLOR_BLUE, 0, "BLUE");*/
                break;
            case R.id.tvSize:
                menu.setGroupVisible(R.id.ColorGroup,false);
                /*menu.add(0, MENU_SIZE_22, 0, "Size 22");
                menu.add(0, MENU_SIZE_26, 0, "Size 26");
                menu.add(0, MENU_SIZE_30, 0, "Size 30");*/
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            //case MENU_COLOR_RED:
            case R.id.MENU_COLOR_RED:
                tvColor.setTextColor(Color.RED);
                tvSize.setTextColor(Color.RED);
                tvColor.setText("Text Color is RED");
                break;
            //case MENU_COLOR_GREEN:
            case R.id.MENU_COLOR_GREEN:
                tvColor.setTextColor(Color.GREEN);
                tvSize.setTextColor(Color.GREEN);
                tvColor.setText("Text Color is GREEN");
                break;
            //case MENU_COLOR_BLUE:
            case R.id.MENU_COLOR_BLUE:
                tvColor.setTextColor(Color.BLUE);
                tvSize.setTextColor(Color.BLUE);
                tvColor.setText("Text Color is BLUE");
                break;
            //case MENU_SIZE_22:
            case R.id.MENU_SIZE_22:
                tvColor.setTextSize(22);
                tvSize.setTextSize(22);
                tvSize.setText("Text Size is 22");
                break;
            //case MENU_SIZE_26:
            case R.id.MENU_SIZE_26:
                tvColor.setTextSize(26);
                tvSize.setTextSize(26);
                tvSize.setText("Text Size is 26");
                break;
            //case MENU_SIZE_30:
            case R.id.MENU_SIZE_30:
                tvColor.setTextSize(30);
                tvSize.setTextSize(30);
                tvSize.setText("Text Size is 30");
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://ru.startandroid.develop.contextmenu/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://ru.startandroid.develop.contextmenu/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }}
