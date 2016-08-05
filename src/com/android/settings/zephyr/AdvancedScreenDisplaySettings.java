package com.android.settings.zephyr;

import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.PreferenceScreen;
import android.preference.SwitchPreference;
import android.provider.Settings;
import com.android.internal.logging.MetricsLogger;

import com.android.internal.util.slim.DeviceUtils;
import com.android.settings.R;
import com.android.settings.SettingsPreferenceFragment;

public class AdvancedScreenDisplaySettings extends SettingsPreferenceFragment implements
	OnPreferenceChangeListener {

                 // shit starts now!

    @Override
    public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);

	addPreferencesFromResource(R.xml.advanced_screen_display_settings);
   }

    @Override
    public boolean onPreferenceChange(Preference preference, Object objValue) {

		// preference changes here
        return false;
    }
    @Override
    public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, Preference preference) {
 		return super.onPreferenceTreeClick(preferenceScreen, preference);
    	}

    @Override
    protected int getMetricsCategory() {
        return MetricsLogger.DONT_TRACK_ME_BRO;
    }
}
