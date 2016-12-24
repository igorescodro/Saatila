package com.escodro.saatila.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.escodro.saatila.R;
import com.escodro.saatila.fragment.WeatherFragment;

/**
 * Created by IgorEscodro on 17/12/16.
 */

public class MainActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addWeatherFragment();
    }

    private void addWeatherFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fl_container, new WeatherFragment())
                .commit();
    }
}
