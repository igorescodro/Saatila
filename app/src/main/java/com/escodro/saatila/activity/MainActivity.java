package com.escodro.saatila.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.escodro.saatila.R;
import com.escodro.saatila.fragment.PreferencesFragment;
import com.escodro.saatila.fragment.WeatherFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by IgorEscodro on 17/12/16.
 */

public class MainActivity extends AppCompatActivity implements
        BottomNavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.bn_weather_navigator)
    BottomNavigationView mNavigator;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mNavigator.setOnNavigationItemSelectedListener(this);
        replaceWithFragment(new WeatherFragment());
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        switch (item.getItemId()) {
            case R.id.menu_home:
                fragment = new WeatherFragment();
                break;
            case R.id.menu_preferences:
                fragment = new PreferencesFragment();
                break;
        }
        replaceWithFragment(fragment);
        return true;
    }

    private void replaceWithFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fl_container, fragment)
                    .commit();
        }
    }
}
