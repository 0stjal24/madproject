package com.example.whitelegg_n.osmdroid1;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


public class MapTypeListActivity extends ListActivity
{
    private String[] names;
    private String[] descriptions;

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        names = new String[] { "Regular Map", "Cycle Map"};
        descriptions = new String[] { "Loads the regular map", "Show Cycle Map"};

        MyAdapter mapAdapter = new MyAdapter();
        setListAdapter(mapAdapter);
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

        // Create a Bundle to store names to be sent back.
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

    class MyAdapter extends ArrayAdapter<String> {

        public MyAdapter() {
            super(MapTypeListActivity.this, android.R.layout.simple_list_item_1, names);
        }

        @Override
        public View getView(int index, View convertView, ViewGroup parent){
            // inflate our poi entry layout
            LayoutInflater inflater;
            inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View view = inflater.inflate(R.layout.poi_entry, parent, false);

            //populate our poi entry with names
            TextView nameTextView = (TextView) view.findViewById(R.id.poi_name);
            nameTextView.setText(names[index]);

            TextView descriptionTextView = (TextView) view.findViewById(R.id.poi_name);
            descriptionTextView.setText(descriptions[index]);

            // return view
            return view;
        }
    }



}
