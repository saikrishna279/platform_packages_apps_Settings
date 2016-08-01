package com.android.settings.zephyr;

import android.app.ActivityManagerNative;
import android.app.Activity;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.PreferenceScreen;
import android.preference.SwitchPreference;
import android.preference.PreferenceCategory;
import android.provider.Settings;
import android.content.ContentResolver;

import com.android.internal.logging.MetricsLogger;

import com.android.internal.util.slim.DeviceUtils;
import com.android.settings.R;
import com.android.settings.SettingsPreferenceFragment;

public class StatusBarSettings extends SettingsPreferenceFragment implements
        OnPreferenceChangeListener {

       private static final String ENABLE_TASK_MANAGER = "enable_task_manager";

       private SwitchPreference mEnableTaskManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Activity activity = getActivity();
        final ContentResolver resolver = activity.getContentResolver();

        addPreferencesFromResource(R.xml.status_bar_settings);

        final PreferenceScreen prefScreen = getPreferenceScreen();
        if (!DeviceUtils.deviceSupportsMobileData(getActivity())) {
            removePreference("status_bar_carrier_label_settings");
        }
 mEnableTaskManager = (SwitchPreference) findPreference(ENABLE_TASK_MANAGER);        
 mEnableTaskManager.setChecked((Settings.System.getInt(resolver,
                Settings.System.ENABLE_TASK_MANAGER, 0) == 1));
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
    if  (preference == mEnableTaskManager) {
            boolean checked = ((SwitchPreference)preference).isChecked();
            Settings.System.putInt(getActivity().getContentResolver(),
                    Settings.System.ENABLE_TASK_MANAGER, checked ? 1:0);
            return true;
        }
        return false;
    }
    @Override
    public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, Preference preference) {
 		return super.onPreferenceTreeClick(preferenceScreen, preference);
    	}

    @Override
    protected int getMetricsCategory() {
        return MetricsLogger.STATUS_BAR_SETTINGS;
    }
}

