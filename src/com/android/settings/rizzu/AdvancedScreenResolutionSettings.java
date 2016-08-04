<!-- Copyright (C) 2016 Sai Krishna <androidwall.nrt@gmail.com>

     Licensed under the Apache License, Version 2.0 (the "License");
     you may not use this file except in compliance with the License.
     You may obtain a copy of the License at

          http://www.apache.org/licenses/LICENSE-2.0

     Unless required by applicable law or agreed to in writing, software
     distributed under the License is distributed on an "AS IS" BASIS,
     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
     See the License for the specific language governing permissions and
     limitations under the License.
-->

package com.settings.android.settings.rizzu;

//import necssary shit for data entry
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.IPackageDataObserver;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.PowerManager;
import android.os.SystemProperties;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.PreferenceScreen;
import android.preference.SwitchPreference;
import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.android.settings.rizzu.ChildProcess;
import com.android.settings.rizzu.CMDProcessor;
import com.android.settings.rizzu.CommandResult;
import com.android.settings.rizzu.Helper;
import com.android.settings.rizzu.AbstractAsyncSuCMDProcessor

//imports necessary shit!
import android.os.SystemProperties;

//import settings provider
import android.provider.Settings;

//import Metrics!
import com.android.internal.logging.MetricsLogger;

import com.android.internal.util.slim.DeviceUtils;
import com.android.settings.R;
import com.android.settings.SettingsPreferenceFragment;

private EditTextPreference mDimesion

public class AdvancedScreenResolutionChanger extends SettingsPreferenceFragment implements
	OnPreferenceChangeListener {
    
    @Override//teh dick ride! fuck yeah! elevation! super!    
    public void onCreate(Bundle SavedInstanceState) {

        //imports stock screen resolution from build.prop
        String currentResolution = SystemProperties.get("ro.wm.screen_res");
        String resetResolution = SystemPropertied.get("ro.wm.screen_res");
 
        addPreferenceFromResource(R.xml.advanced_screen_resolution_settings);

        EditText customResolution = (EditText) findViewById(R.id.customResolution);
        String shellResolution= customResolution.getEditableText().toString();

        resolutionApply();
    }

    @Override
    public void resolutionApply(String customResolution)
     {
      CMDProcessor.startSuCommand("wm size " + customResolution);
     }

     @Override
     protected int getMetricsCategory() {
         return InstrumentedFragment.LOCK_SCREEN;
     }
}

