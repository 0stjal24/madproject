package com.example.whitelegg_n.osmdroid1;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.OverlayItem;

import java.util.ArrayList;

public class MainActivity extends Activity {
    MapView mv;
    ItemizedIconOverlay<OverlayItem> items;
    ItemizedIconOverlay.OnItemGestureListener<OverlayItem> markerGestureListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //important! set your user agent to prevent getting banned from the osm servers
        Configuration.getInstance().load(this, PreferenceManager.getDefaultSharedPreferences(this));

        setContentView(R.layout.activity_main);
        mv = (MapView) findViewById(R.id.map1);
        mv.getController().setZoom(14);
        mv.getController().setCenter(new GeoPoint(40.1, 22.5));

        markerGestureListener = new ItemizedIconOverlay.OnItemGestureListener<OverlayItem>() {

            public boolean onItemSingleTapUp(int i, OverlayItem item) {
                Toast.makeText(MainActivity.this, item.getSnippet(), Toast.LENGTH_SHORT).show();
                return true;
            }

            public boolean onItemLongPress(int index, OverlayItem item) {
                Toast.makeText(MainActivity.this, item.getSnippet(), Toast.LENGTH_LONG);
                return true;
            }
        };

        items = new ItemizedIconOverlay<OverlayItem>(this, new ArrayList<OverlayItem>(), null);
        OverlayItem ocean_village = new OverlayItem("Ocean Village", " Village by the ocean", new GeoPoint(50.8973, -1.3896));

      //  ocean_village.setMarker(getResources().getDrawable(R.drawable.marker));
        items.addItem(ocean_village);
        mv.getOverlays().add(items);
    }



    // onCreateOptionsMenu()
    // Runs automatically on startup, loads the menu XML file into a Menu object.
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_hello_map, menu);
        return true;
    }

    // onOptionsItemSelected()
    // Responds to menu items.
    public boolean onOptionsItemSelected(MenuItem item)

    {
        // If the menu item was the one with the ID of "choosemap"...
        if (item.getItemId() == R.id.choosemap) {
            // Create an Intent to launch the MapChooseActivity
            Intent intent = new Intent(this, MapChooseActivity.class);
            startActivityForResult(intent, 0);

            return true;
        }
        if (item.getItemId() == R.id.setlocation) {
            // Create an Intent to launch the MapChooseActivity
            Intent intent = new Intent(this, SetLocationActivity.class);
            startActivityForResult(intent, 1);

            return true;
        }

        return false;
    }

    // onActivityResult()
    // Runs when we get a response back from the secondary activity.
    // Parameters:
    // - requestCode: the request code used to launch the secondary activity (see above)
    // - responseCode: the response code sent back from the secondary activity (e.g. RESULT_OK)
    // - intent: the Intent sent back from the secondary activity

    public void onActivityResult(int requestCode, int responseCode, Intent intent) {
        // If the secondary activity returned a result of RESULT_OK...
        if (responseCode == RESULT_OK) {
            // If the request code used to launch the secondary activity was 0, it means we launched
            // MapChooseActivity (see above).
            if (requestCode == 0) {
                // Get the Bundle from the Intent.
                Bundle bundle = intent.getExtras();

                // Get the cyclemap entry from the Bundle
                boolean cyclemap = bundle.getBoolean("com.example.cyclemap");

                // Set the map to either the cycle map, or the default map (MAPNIK) depending
                // on the value of the boolean retrieved from the Bundle.
                if (cyclemap == true) {
                    mv.setTileSource(TileSourceFactory.CYCLEMAP);
                } else {
                    mv.setTileSource(TileSourceFactory.MAPNIK);
                }


            } else if (requestCode == 1) {
                if (responseCode == RESULT_OK) {
                    Bundle latlongBundle = intent.getExtras();
                    double latitude = latlongBundle.getDouble("com.whitelegg_n.latitude");
                    double longitude = latlongBundle.getDouble("com.whitelegg_n.longitude");

                    mv.getController().setCenter(new GeoPoint(longitude, latitude));
                }
            }
        }
    }
    public void onStart()
    {
        super.onStart();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        double lat = Double.parseDouble ( prefs.getString("lat", "50.9") );
        double lon = Double.parseDouble ( prefs.getString("lon", "-1.4") );
        int zoom = Integer.parseInt( prefs.getString("zoom", "14") );

        mv.getController().setZoom(zoom);
        mv.getController().setCenter(new GeoPoint (lat,lon));



    }

}
