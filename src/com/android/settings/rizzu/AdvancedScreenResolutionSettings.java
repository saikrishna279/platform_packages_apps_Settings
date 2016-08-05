/*   Copyright (C) 2016 Sai Krishna <androidwall.nrt@gmail.com>

     Licensed under the Apache License, Version 2.0 (the "License");
     you may not use this file except in compliance with the License.
     You may obtain a copy of the License at

          http://www.apache.org/licenses/LICENSE-2.0

     Unless required by applicable law or agreed to in writing, software
     distributed under the License is distributed on an "AS IS" BASIS,
     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
     See the License for the specific language governing permissions and
     limitations under the License.
*/

package com.settings.android.settings.rizzu;

//import necssary shit for data entry
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

//imports necessary shit!
import android.os.SystemProperties;

//import settings provider
import android.provider.Settings;
import com.android.settings.R;

//import Metrics!
import com.android.internal.logging.MetricsLogger;

import com.android.internal.util.slim.DeviceUtils;
import com.android.settings.R;
import com.android.settings.SettingsPreferenceFragment;

public class AdvancedScreenResolutionSettings extends SettingsPreferenceFragment implements
	OnPreferenceChangeListener {

    public static final String CUSTOM_RESOLUTION="custom_resolution";
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.advanced_screen_resolution_settings);

        //imports stock screen resolution from build.prop
        String currentResolution = SystemProperties.get("ro.wm.screen_res");
        String resetResolution = SystemProperties.get("ro.wm.screen_res");

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
                        CUSTOM_RESOLUTION = customResolution;
                }
            });
            alert.setNegativeButton(getString(android.R.string.cancel), null);
        alert.show();
        //CMDProcessor.runSuCommand("wm size " + customResolution);
        
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object objValue) {

		// preference changes here
        return false;
    }

    public static void thug() {
        CMDProcessor.startSuCommand("su wm size " + CUSTOM_RESOLUTION);
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

