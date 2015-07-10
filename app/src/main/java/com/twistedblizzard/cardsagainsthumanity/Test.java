package com.twistedblizzard.cardsagainsthumanity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class Test extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String debugCode = bundle.getString("DEBUG_CODE");


        ListView listView1 = (ListView) findViewById(R.id.listView1);

        if (debugCode.equals("bus1")) {
            Resources res = getResources();
            String[] test = res.getStringArray(R.array.black_us_1);
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                    android.R.layout.simple_list_item_1, test);
            listView1.setAdapter(adapter);
        }

        if (debugCode.equals("bus2")) {
            Resources res = getResources();
            String[] test = res.getStringArray(R.array.black_us_2);
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                    android.R.layout.simple_list_item_1, test);
            listView1.setAdapter(adapter);
        }

        if (debugCode.equals("bus3")) {
            Resources res = getResources();
            String[] test = res.getStringArray(R.array.black_us_3);
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                    android.R.layout.simple_list_item_1, test);
            listView1.setAdapter(adapter);
        }

        if (debugCode.equals("wus")) {
            Resources res = getResources();
            String[] test = res.getStringArray(R.array.white_us);
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                    android.R.layout.simple_list_item_1, test);
            listView1.setAdapter(adapter);
        }

        if (debugCode.equals("prefs")) {
            SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
            String rounds = getString(R.string.rounds) + ": " + String.valueOf(sharedPref.getInt("SETUP_ROUNDS", 20));
            String players = getString(R.string.players) + ": " +  String.valueOf(sharedPref.getInt("SETUP_PLAYERS", 4));
            String[] test = {
                    rounds,
                    players,
            };
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                    android.R.layout.simple_list_item_1, test);
            listView1.setAdapter(adapter);
        }

        if (debugCode.equals("settings")) {
            SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
            String edition = getString(R.string.edition) + sharedPref.getString("SETTINGS_EDITION", null);
            String[] test = {
                    edition,
            };
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                    android.R.layout.simple_list_item_1, test);
            listView1.setAdapter(adapter);
        }
    }
}
