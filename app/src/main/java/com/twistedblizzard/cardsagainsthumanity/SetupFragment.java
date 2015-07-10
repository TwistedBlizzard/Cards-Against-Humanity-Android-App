package com.twistedblizzard.cardsagainsthumanity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;

public class SetupFragment extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.game_setup);

        NumberPickerRounds roundsPref = (NumberPickerRounds) findPreference(getString(R.string.key_setup_rounds));
        roundsPref.setSummary(String.valueOf(roundsPref.getValue()));

        NumberPickerPlayers playersPref = (NumberPickerPlayers) findPreference(getString(R.string.key_setup_players));
        playersPref.setSummary(String.valueOf(playersPref.getValue()));

        Preference button = findPreference(getString(R.string.key_setup_ready));
        button.setOnPreferenceClickListener (new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                Intent intent = new Intent(getActivity(), GameActivity.class);
                startActivity(intent);

                return true;
            }
        });
    }

    public void onStart() {
        super.onStart();
        getPreferenceManager().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    public void onStop() {
        getPreferenceManager().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
        super.onStop();
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (key.equals(getString(R.string.key_setup_rounds))) {
            NumberPickerRounds roundsPref = (NumberPickerRounds) findPreference(key);
            roundsPref.setSummary(String.valueOf(sharedPreferences.getInt(key, 0)));
        }

        if (key.equals(getString(R.string.key_setup_players))) {
            NumberPickerPlayers playersPref = (NumberPickerPlayers) findPreference(key);
            playersPref.setSummary(String.valueOf(sharedPreferences.getInt(key, 0)));
        }
    }
}