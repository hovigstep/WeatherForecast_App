package com.weatherforecast_app.weatherforecast_app;

import android.os.Bundle;
import android.support.annotation.Nullable;
import com.takisoft.fix.support.v7.preference.PreferenceFragmentCompat;

public class SettinggsFragment extends PreferenceFragmentCompat {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings);
    }
    @Override
    public void onCreatePreferencesFix(@Nullable Bundle savedInstanceState, String rootKey) {

    }
}
