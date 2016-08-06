package com.settings.android.settings.rizzu;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.IPackageDataObserver;
import android.text.Spannable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.PowerManager;
import android.os.SystemProperties;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceCategory;
import android.preference.PreferenceScreen;
import android.preference.SwitchPreference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.provider.Settings;

import com.android.settings.rizzu.ChildProcess;
import com.android.settings.rizzu.CMDProcessor;
import com.android.settings.rizzu.CommandResult;
import com.android.settings.rizzu.Helpers;
import com.android.settings.rizzu.AbstractAsyncSuCMDProcessor;

import android.os.SystemProperties;
import android.provider.Settings;
import com.android.settings.R;

//import Metrics!
import com.android.internal.logging.MetricsLogger;
import com.android.internal.util.slim.DeviceUtils;
import com.android.settings.R;
import com.android.settings.SettingsPreferenceFragment;
import android.content.ContentResolver;

public class AdvancedScreenResolutionSettings extends SettingsPreferenceFragment implements
	OnPreferenceChangeListener {

    public static final String CUSTOM_RESOLUTION="custom_resolution";
    
    public String customResolution;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.advanced_screen_resolution_settings);

        //imports stock screen resolution from build.prop
        //String currentResolution = SystemProperties.get("ro.wm.screen_res");
        //String resetResolution = SystemProperties.get("ro.wm.screen_res");

        AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
        alert.setTitle(R.string.stream_in_screen_resolution);
        alert.setMessage(R.string.stream_in_screen_resolution_description);

        final EditText input = new EditText(getActivity());
        input.setText("");
        input.setSelection(input.getText().length());
        alert.setView(input);
        alert.setPositiveButton(getString(android.R.string.ok),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        String customResolution = ((Spannable) input.getText()).toString().trim();
                }
            });
            alert.setNegativeButton(getString(android.R.string.cancel), null);
        alert.show();
        
        CMDProcessor.startSuCommand("su wm size " + customResolution);
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
