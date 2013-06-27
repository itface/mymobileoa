package com.example.firstad;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class SettingServiceIpPreferenceActivity extends PreferenceActivity{

	protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    // 所的的值将会自动保存到 SharePreferences
	    addPreferencesFromResource(R.xml.setting_serverip);
	}
}
