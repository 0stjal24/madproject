package com.example.whitelegg_n.osmdroid1;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class MapTypeListActivity extends ListActivity
{
    String[] data;

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        data = new String[] { "Regular Map", "Cycle Map"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data);
        setListAdapter(adapter);
    }

    public void onListItemClick(ListView lv, View view, int index, long id) {
        // Set the cyclemap boolean variable to false by default.
        boolean cyclemap = false;

        // Get the ID of the button that was pressed. If it's the ID for the cyclemap
        // button, set the cyclemap boolean to true.
        if (index == 1) {
            cyclemap = true;
        }

        // Create an Intent to send information back to the main activity.
        Intent intent = new Intent();

        // Create a Bundle to store data to be sent back.
        Bundle bundle = new Bundle();

        // Add the boolean to the Bundle.
        bundle.putBoolean("com.example.cyclemap", cyclemap);

        // Add the Bundle to the Intent.
        intent.putExtras(bundle);

        // Set an OK result, meaning that everything was successful.
        setResult(RESULT_OK, intent);

        // Close the second activity (this will return to the first)
        finish();

    }



}
