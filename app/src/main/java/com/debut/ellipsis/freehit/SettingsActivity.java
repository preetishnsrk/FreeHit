package com.debut.ellipsis.freehit;


import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.support.v7.app.AppCompatActivity;

import com.debut.ellipsis.freehit.IntoSlider.CountryPicker;
import com.debut.ellipsis.freehit.IntoSlider.CountryPickerListener;

import static com.debut.ellipsis.freehit.IntoSlider.WelcomeActivity.MY_PREFS_NAME;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);

    }

    public static class FreeHitPreferenceFragment extends PreferenceFragment {

        @Override
        public void onCreate(Bundle savedInstanceState) {
           final Activity a = this.getActivity();

            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.settings_main);

            Preference button = findPreference("team_selection");
            button.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {

                    //code for what you want it to do

                    final CountryPicker picker = CountryPicker.newInstance("Select Country");  // dialog title
                    picker.setListener(new CountryPickerListener() {
                        @Override
                        public void onSelectCountry(String name, String code, String dialCode, int flagDrawableResID) {
                            // Implement your code here

                            //NOTE: have to set image view in settings but trying to resolve this first
                            SharedPreferences.Editor editor = a.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                            editor.putString("country_name", name);
                            editor.apply();
                            picker.dismiss();

                        }
                    });
                    picker.show(getFragmentManager(), "COUNTRY_PICKER");



                    return true;
                }
            });
        }
    }
}

