package com.example.firstad;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class SettingServiceIpPreferenceActivity extends PreferenceActivity{

	protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    // ���ĵ�ֵ�����Զ����浽 SharePreferences
	    addPreferencesFromResource(R.xml.setting_serverip);
	}
}
