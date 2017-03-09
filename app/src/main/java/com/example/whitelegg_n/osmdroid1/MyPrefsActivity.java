package com.example.whitelegg_n.osmdroid1;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class MyPrefsActivity extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }

}
