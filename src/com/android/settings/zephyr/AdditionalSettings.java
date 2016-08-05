package com.android.settings.zephyr;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.provider.Settings;
import android.preference.Preference;
import android.preference.PreferenceCategory;
import android.preference.PreferenceManager;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.PreferenceScreen;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.settings.zephyr.StatusBarSettings;
import com.android.settings.zephyr.NavigationBarSettings;
import com.android.settings.zephyr.LockScreenSettings;
import com.android.settings.zephyr.PagerSlidingTabStrip;
import com.android.settings.zephyr.AdvancedScreenDisplaySettings;
import com.android.settings.cyanogenmod.NotificationDrawerSettings;
import com.android.settings.R;
import com.android.settings.Utils;
import com.android.settings.SettingsPreferenceFragment;

import com.android.internal.logging.MetricsLogger;

import java.util.ArrayList;
import java.util.List;

public class AdditionalSettings extends SettingsPreferenceFragment {

    ViewPager mViewPager;
    String titleString[];
    ViewGroup mContainer;
    PagerSlidingTabStrip mTabs;

    static Bundle mSavedState;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mContainer = container;
	final ActionBar actionBar = getActivity().getActionBar();
        actionBar.setIcon(R.drawable.ic_vortex);

        View view = inflater.inflate(R.layout.additional_settings, container, false);
        mViewPager = (ViewPager) view.findViewById(R.id.pager);
	mTabs = (PagerSlidingTabStrip) view.findViewById(R.id.tabs);

        StatusBarAdapter StatusBarAdapter = new StatusBarAdapter(getFragmentManager());
        mViewPager.setAdapter(StatusBarAdapter);
       
	mTabs.setViewPager(mViewPager);
        setHasOptionsMenu(true);
        return view;
    }

   @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // After confirming PreferenceScreen is available, we call super.
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle saveState) {
        super.onSaveInstanceState(saveState);
    }

    @Override
    public void onResume() {
        super.onResume();
 	if (!Utils.isPhone(getActivity())) {
            mContainer.setPadding(0, 0, 0, 0);
        }
    }

    class StatusBarAdapter extends FragmentPagerAdapter {
        String titles[] = getTitles();
        private Fragment frags[] = new Fragment[titles.length];

        public StatusBarAdapter(FragmentManager fm) {
            super(fm);
            frags[0] = new AdvancedScreenDisplaySettings();
	    frags[1] = new StatusBarSettings();
            frags[2] = new NavigationBarSettings();
            frags[3] = new LockScreenSettings();      
	    frags[4] = new NotificationDrawerSettings();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }

        @Override
        public Fragment getItem(int position) {
            return frags[position];
        }

        @Override
        public int getCount() {
            return frags.length;
        }
    }

    private String[] getTitles() {
        String titleString[];
        titleString = new String[]{
                    getString(R.string.advanced_screen_display_settings_title),
		    getString(R.string.status_bar_title),
                    getString(R.string.navigation_bar_title),
                    getString(R.string.lock_screen_title),
		    getString(R.string.notification_drawer_title)};
        return titleString;
    }

    @Override
    protected int getMetricsCategory() {
        return MetricsLogger.ADDITIONAL_SETTINGS;
    }
}

