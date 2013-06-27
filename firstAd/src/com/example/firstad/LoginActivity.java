package com.example.firstad;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.BitmapDrawable;
import android.media.ExifInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class LoginActivity extends Activity {

	private static final String TAG = "ActivityDemo";  
	public static final String LOGIN = "login";
	public static final String LOGIN_UID = "uid";
	
	private Button loginBtn = null;
	private EditText uidText = null;
	private EditText pwdText = null;
	private RelativeLayout loginLayout = null;
	private ImageView logo = null;
	private Button setIpBtn = null;
	private TelephonyManager tm = null;
	int screenWidth;
	int screenHeight;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.e(TAG, "start onCreate~~~");  
		setContentView(R.layout.login);
		Display display = this.getWindowManager().getDefaultDisplay();
		screenWidth = display.getWidth();
		screenHeight = display.getHeight();
		tm=(TelephonyManager)this.getSystemService(Context.TELEPHONY_SERVICE);
		loginBtn = (Button)this.findViewById(R.id.login);
		setIpBtn = (Button)this.findViewById(R.id.settingIP);
		uidText = (EditText)this.findViewById(R.id.edtuser);
		pwdText = (EditText)this.findViewById(R.id.edtpsd);
		logo = (ImageView)this.findViewById(R.drawable.ui_login_logo);
		loginLayout = (RelativeLayout)this.findViewById(R.layout.login);
		
		initEvent();
		initCheck();
	}

	 protected void onStart(){
		super.onStart();  
	    Log.e(TAG, "start onStart~~~"); 
     }      
     protected void onRestart(){
    	 super.onRestart();  
         Log.e(TAG, "start onRestart~~~");  
     }   
     protected void onResume(){
    	 super.onResume();  
         Log.e(TAG, "start onResume~~~"); 
     }    
     protected void onPause(){
    	 super.onPause();  
         Log.e(TAG, "start onPause~~~");  
     }     
     protected void onStop(){
    	 super.onStop();  
         Log.e(TAG, "start onStop~~~"); 
     }    
     protected void onDestroy(){
    	 super.onDestroy();  
         Log.e(TAG, "start onDestroy~~~"); 
     }  
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private void initEvent(){
		pwdText.setOnEditorActionListener(new TextView.OnEditorActionListener() {			
			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				// TODO Auto-generated method stub
				//按了回事、确定、前往、搜索键（都是一个键，只是名字不一样）
				if(actionId == EditorInfo.IME_ACTION_UNSPECIFIED || actionId == EditorInfo.IME_ACTION_NONE || actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_NEXT || actionId == EditorInfo.IME_ACTION_GO || actionId == EditorInfo.IME_ACTION_SEARCH){
					
				}
				return false;
			}
		});
		loginBtn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
			
		});
		setIpBtn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//Intent intent = new Intent(LoginActivity.this,SettingServiceIPActivity.class);
				
				Intent intent = new Intent(LoginActivity.this,SettingServiceIpPreferenceActivity.class);

				startActivity(intent);
			}
			
		});
	}
	private void initCheck(){
		SharedPreferences sp = this.getSharedPreferences(LOGIN, MODE_PRIVATE);
		if(sp!=null){
			String uid = sp.getString(LOGIN_UID, "");
			uidText.setText(uid);
			pwdText.requestFocus();
		}
		if(checkNet()){
//			AlertDialog.Builder builder = new AlertDialog.Builder(this);
//			builder.setTitle("恭喜");
//			builder.setMessage("登录成功");
//			builder.create().show();
		}else{
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle(R.string.string_login_alertTitle);
			builder.setMessage(R.string.string_login_alertMsg);
			builder.setNegativeButton(R.string.string_login_exit, new android.content.DialogInterface.OnClickListener(){

				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					//exitApp();
					finish();
				}
				
			});
			builder.setPositiveButton(R.string.string_login_settingNet, new android.content.DialogInterface.OnClickListener(){

				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					dialog.dismiss();
					((Context)LoginActivity.this).startActivity(new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS));
				}
			});
			builder.create().show();
		}
	}
	private boolean checkNet(){
		ConnectivityManager cm = (ConnectivityManager)this.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = cm.getActiveNetworkInfo();
		if(networkInfo!=null&&networkInfo.isConnected()&&networkInfo.getState()==NetworkInfo.State.CONNECTED){
			return true;
		}
		return false;
	}
//	private int getPicDegree(String path){
//		int degree = 0;
//		try {/*
//		ExifInterface类主要描述多媒体文件比如JPG格式图片的一些附加信息，比如拍照的设备厂商，当时的日期时间，曝光时间，快门速度等。
//		该类位于android.media.ExifInterface的位置，需要调用APILevel至少为5即2.0SDK。
//		 		
//		 		*
//		 		*
//		 		*/
//			ExifInterface ex = new ExifInterface(path);
//			int orientation = ex.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
//			switch(orientation){
//			case ExifInterface.ORIENTATION_ROTATE_90:
//				degree=80;
//				break;
//			case ExifInterface.ORIENTATION_ROTATE_180:
//				degree=180;
//				break;
//			case ExifInterface.ORIENTATION_ROTATE_270:
//				degree=270;
//				break;
//			}
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return degree;
//	}
//	private void setTheme(){
//		loginBtn.setBackgroundResource(R.drawable.ui_login_btn_blue);
//		logo.setBackgroundResource(R.drawable.ui_login_logo);
//		setIpBtn.setBackgroundResource(R.drawable.ui_login_seeting);
//		String bg_uri = PreferenceManager.getDefaultSharedPreferences(this).getString("settings_select_main_bg", String.valueOf(R.drawable.ui_login_bg_bule));
//		if(bg_uri.indexOf("contect://")>=0){
//			
//			String[] proj = { MediaStore.Images.Media.DATA };
//			//		Cursor cur1 = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, null, selection1, null,null);
//			Cursor c = this.managedQuery(Uri.parse(bg_uri), proj, null, null, null);
//			int index = c.getColumnIndex(MediaStore.Images.Media.DATA);
//			c.moveToFirst();
//			String img_path = c.getString(index);
//			try {
//				Bitmap bitmap = null;
//				InputStream is = this.getContentResolver().openInputStream(Uri.parse(bg_uri));
//				/*
//				在通过BitmapFactory.decodeFile(String path)方法将突破转成Bitmap时，遇到大一些的图片，我们经常会遇到OOM(Out Of Memory)的问题。怎么避免它呢？
//				这就用到了我们上面提到的BitmapFactory.Options这个类。
//				BitmapFactory.Options这个类，有一个字段叫做 inJustDecodeBounds 。SDK中对这个成员的说明是这样的：
//				If set to true, the decoder will return null (no bitmap), but the out…
//				也就是说，如果我们把它设为true，那么BitmapFactory.decodeFile(String path, Options opt)并不会真的返回一个Bitmap给你，它仅仅会把它的宽，高取回来给你，这样就不会占用太多的内存，也就不会那么频繁的发生OOM了。
//				*/
//				BitmapFactory.Options options = new BitmapFactory.Options();
//				options.inJustDecodeBounds=true;
//				BitmapFactory.decodeStream(is,null,options);
//				is.close();
//				int picWidth = options.outWidth;
//				int picHeight = options.outHeight;
//				if(picWidth==screenWidth&&picHeight==screenHeight){
//					// 图片（图片大小=屏幕大小）不做处理，直接读取图片
//					options.inJustDecodeBounds=false;
//					options=new BitmapFactory.Options();
//					is = this.getContentResolver().openInputStream(Uri.parse(bg_uri));
//					bitmap = BitmapFactory.decodeStream(is, null, options);
//					is.close();
//				}else{
//					int s = 1;
//					while ((picWidth / s > screenWidth * 2) || (picHeight / s > screenHeight * 2)) {
//						s *= 2;
//					}
//					options.inJustDecodeBounds=false;
//					options=new BitmapFactory.Options();
//					//减少存储空间,缩小几倍
//					options.inSampleSize=s;
//					is = this.getContentResolver().openInputStream(Uri.parse(bg_uri));
//					Bitmap tempBitmap  = BitmapFactory.decodeStream(is, null, options);
//					is.close();
//					Matrix matrix = new Matrix();
//					int degree = this.getPicDegree(bg_uri);
//					matrix.setRotate(degree);
//					//图片大小
//					int width = tempBitmap.getWidth();
//					int height = tempBitmap.getHeight();
//					float scaleWidth = (float)screenWidth/width;
//					float scaleHeight = (float)screenHeight/height;
//					if((width>height&&screenWidth<screenHeight)||(width<height&&screenWidth>screenHeight)){
//						if (degree == 0 || degree == 180) {
//							// 横向图片统一缩放比
//							scaleHeight = scaleWidth;
//						} else {
//							// 纵向图片拉伸铺满桌面
//							scaleWidth = ((float) screenWidth) / height;
//							scaleHeight = ((float) screenHeight) / width;
//						}
//					}
//					matrix.setScale(scaleWidth, scaleHeight);
//					//重新生成图片
//					bitmap = Bitmap.createBitmap(tempBitmap, 0, 0,width,height,matrix,true);
//					 //回收图片所占的内存
//					tempBitmap.recycle();
//				}
//				BitmapDrawable bd = new BitmapDrawable(bitmap);
//				//设置平铺
//				bd.setTileModeXY(TileMode.REPEAT, TileMode.REPEAT);
//				//设置抖动
//				bd.setDither(true);
//				loginLayout.setBackgroundDrawable(bd);
//			} catch (FileNotFoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}else{
//			loginLayout.setBackgroundResource(R.drawable.ui_login_bg_bule);
//		}
//	}
}
