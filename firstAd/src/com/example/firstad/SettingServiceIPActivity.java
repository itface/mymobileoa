package com.example.firstad;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class SettingServiceIPActivity extends Activity{

	private final String SETTING_SERVER = "setting_server";
	private final String SETTING_SERVER_IP = "setting_server_ip";
	
	private ImageButton backBtn;
	private ImageButton saveBtn;
	private EditText serverIp;
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		
		//this.requestWindowFeature(Window.FEATURE_LEFT_ICON);
		//this.requestWindowFeature(Window.FEATURE_RIGHT_ICON);
		this.requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		this.setContentView(R.layout.setting_serverip);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.setting_serverip_title);
		//getWindow().setFeatureDrawableResource(Window.FEATURE_LEFT_ICON, R.drawable.ui_title_back_btn_blue);
		//getWindow().setFeatureDrawableResource(Window.FEATURE_RIGHT_ICON, R.drawable.ui_title_save_btn_normal_blue);

		
		
		
		backBtn=(ImageButton)this.findViewById(R.id.setting_serverip_back);
		saveBtn=(ImageButton)this.findViewById(R.id.setting_serverip_save);
		serverIp=(EditText)this.findViewById(R.id.server_ip);
		init();
		initEvent();
		
	}
	private void init(){
		SharedPreferences preference = this.getSharedPreferences(SETTING_SERVER, MODE_PRIVATE);
		String server_ip = preference.getString(SETTING_SERVER_IP, "");
		serverIp.setText(server_ip);
		
		
	}
	private void initEvent(){
		backBtn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SettingServiceIPActivity.this.finish();
			}
		});
		saveBtn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SharedPreferences preference = getSharedPreferences(SETTING_SERVER, MODE_PRIVATE);
				SharedPreferences.Editor edit = preference.edit();
				edit.putString(SETTING_SERVER_IP,serverIp.getText().toString());
				edit.commit();
			}
		});
	}
}
