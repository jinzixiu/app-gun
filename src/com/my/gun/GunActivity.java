package com.my.gun;

import com.my.gun.R;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class GunActivity extends Activity {
    /** Called when the activity is first created. */

		
	private SharedPreferences sp;
    @Override	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        sp = getSharedPreferences("passward", Context.MODE_PRIVATE);
        handler.postDelayed(new Runnable() {
			
			@Override
			public void run() {
				//进入主页面
				enterHome();
				
			}

			
		}, 2000);
        
        AlphaAnimation aa = new AlphaAnimation(0.2f, 1.0f);
		aa.setDuration(500);
		findViewById(R.id.rl_root_splash).startAnimation(aa);
        
    }
    
    private Handler handler= new Handler();
    private void enterHome() {
		// TODO Auto-generated method stub 
		if(isSetupPwd()){
			//已经设置密码了，弹出的是输入对话框			
			showEnterDialog();			
			return;
		}else{
			//没有设置密码，弹出的是设置密码对话框						
			showSetupPwdDialog();
			return ;
		}	
	}
    EditText et_setup_pwd;
    EditText et_setup_confirm;
	private Button ok;
	private Button cancel;
	private Dialog dialog;
	private EditText et_enter_pwd;
	private void showSetupPwdDialog() {
		// TODO Auto-generated method stub
		AlertDialog.Builder builder = new Builder(GunActivity.this);
		View view = View.inflate(GunActivity.this, R.layout.dialog_setup_password, null);
		et_setup_pwd = (EditText)view.findViewById(R.id.et_setup_pwd);
		et_setup_confirm = (EditText)view.findViewById(R.id.et_setup_confirm);
		ok=(Button)view.findViewById(R.id.ok);
		cancel=(Button)view.findViewById(R.id.cancel);
		cancel.setOnClickListener(new OnClickListener() {			
			

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dialog.dismiss();
			}
		});
		ok.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//取出密码
				String password = et_setup_pwd.getText().toString().trim();
				String password_confirm= et_setup_confirm.getText().toString().trim();
				if(TextUtils.isEmpty(password)||TextUtils.isEmpty(password_confirm))
				{
					Toast.makeText(getApplicationContext(), "密码为空", 0).show();
					return;
				}
				//判断是否一致
				if(password.equals(password_confirm))
				{
					Editor edit	=sp.edit();
					edit.putString("password", password);
					edit.commit();
					dialog.dismiss();
					System.out.println("一致就保存密码.把对话框消掉,进入页面");
					Intent intent0 = new Intent(GunActivity.this,com.my.gun.Activity_setup.class);
					startActivity(intent0);	
				}else
				{
					Toast.makeText(getApplicationContext(), "密码不一致", 0).show();
					return;
				}
			}
		});
		dialog = builder.create();
		((AlertDialog) dialog).setView(view, 0, 0, 0, 0);
		dialog.show();
	}
	
	private void showEnterDialog() {
		// TODO Auto-generated method stub
		AlertDialog.Builder builder = new Builder(GunActivity.this);
		// 自定义一个布局文件
		View view = View.inflate(GunActivity.this, R.layout.dialog_enter_password, null);
		et_enter_pwd = (EditText) view.findViewById(R.id.et_enter_pwd);
		ok = (Button) view.findViewById(R.id.ok);
		cancel = (Button) view.findViewById(R.id.cancel);
		cancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//把这个对话框取消掉
				dialog.dismiss();
			}
		});
		ok.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//  取出密码
				String password = et_enter_pwd.getText().toString().trim();
				String savePassword = sp.getString("password", "");//取出加密后的
				if(TextUtils.isEmpty(password)){
					Toast.makeText(GunActivity.this, "密码为空", 1).show();
					return;
				}
				
				if((password).equals(savePassword)){
					//输入的密码是我之前设置的密码
					//把对话框消掉，进入主页面；
					dialog.dismiss();					
					Intent intent = new Intent(GunActivity.this,Activity_setup.class);
					startActivity(intent);
					
				}else{
					Toast.makeText(GunActivity.this, "密码错误", 1).show();
					et_setup_pwd.setText("");
					return;
				}
				
				
				
			}
		});
		dialog = builder.create();
		((AlertDialog) dialog).setView(view, 0, 0, 0, 0);
		dialog.show();
		
	}	
	private boolean isSetupPwd() {
		// TODO Auto-generated method stub
		String password = sp.getString("password", null);
		if(TextUtils.isEmpty(password))
		{
			return false;
		}else
		{
			return true;
		}		
	}
}