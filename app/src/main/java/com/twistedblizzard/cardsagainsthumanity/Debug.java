package com.twistedblizzard.cardsagainsthumanity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Debug extends Activity {

    public void prefsDebug(View view){
        Intent intent = new Intent(this, Test.class);
        String prefs = getString(R.string.debug_code_prefs);
        intent.putExtra("DEBUG_CODE", prefs);
        startActivity(intent);
    }

    public void settingsDebug(View view){
        Intent intent = new Intent(this, Test.class);
        String settings = getString(R.string.debug_code_settings);
        intent.putExtra("DEBUG_CODE", settings);
        startActivity(intent);
    }

    public void blackUs1Debug(View view){
        Intent intent = new Intent(this, Test.class);
        String bus1 = getString(R.string.debug_code_bus1);
        intent.putExtra("DEBUG_CODE", bus1);
        startActivity(intent);
    }

    public void blackUs2Debug(View view){
        Intent intent = new Intent(this, Test.class);
        String bus2 = getString(R.string.debug_code_bus2);
        intent.putExtra("DEBUG_CODE", bus2);
        startActivity(intent);
    }

    public void blackUs3Debug(View view){
        Intent intent = new Intent(this, Test.class);
        String bus3 = getString(R.string.debug_code_bus3);
        intent.putExtra("DEBUG_CODE", bus3);
        startActivity(intent);
    }

    public void whiteUsDebug(View view){
        Intent intent = new Intent(this, Test.class);
        String wus = getString(R.string.debug_code_wus);
        intent.putExtra("DEBUG_CODE", wus);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debug);
    }
}