package com.debut.ellipsis.freehit;


import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.debut.ellipsis.freehit.IntoSlider.CountryPicker;
import com.debut.ellipsis.freehit.IntoSlider.CountryPickerListener;

import java.io.ByteArrayOutputStream;

import static com.debut.ellipsis.freehit.IntoSlider.WelcomeActivity.MY_PREFS_NAME;
import static com.debut.ellipsis.freehit.IntoSlider.WelcomeActivity.encodeToBase64;

public class CustomSettings extends AppCompatActivity {
    public static final String LOG_TAG = com.debut.ellipsis.freehit.CustomSettings.class.getSimpleName();
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.custom_settings);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ImageView country_flag=(ImageView)findViewById(R.id.country_flag);

        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String name = prefs.getString("country_name", "No name defined");

        TextView country_name=(TextView)findViewById(R.id.country_name);
        country_name.setText(name);

        String flagID=prefs.getString("country_flag","");
        Log.e(LOG_TAG,flagID);


        Bitmap imageB = null;
        if(!flagID.equals("")) {
            imageB = decodeToBase64(flagID);
        }
        country_flag.setImageBitmap(imageB);


    }

    public void select_country(View view) {

        final CountryPicker picker = CountryPicker.newInstance("Select Country");  // dialog title
        picker.setListener(new CountryPickerListener() {
            @Override
            public void onSelectCountry(String name, String code, String dialCode, int flagDrawableResID) {
                // Implement your code here
                TextView country_name=(TextView)findViewById(R.id.country_name);
                country_name.setText(name);

                ImageView before=(ImageView)findViewById(R.id.country_flag);
                before.setImageResource(flagDrawableResID);

                before.buildDrawingCache();
                Bitmap bmap = before.getDrawingCache();
                SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                editor.putString("country_name", name);
                editor.putString("country_flag", encodeToBase64(bmap));
                editor.apply();
                picker.dismiss();

            }
        });
        picker.show(getSupportFragmentManager(), "COUNTRY_PICKER");

    }

    public static Bitmap decodeToBase64(String input) {
        byte[] decodedByte = Base64.decode(input, 0);
        return BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.length);
    }



}