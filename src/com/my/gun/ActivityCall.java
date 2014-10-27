package com.my.gun;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.gsm.SmsManager;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ActivityCall extends Activity {

	private EditText et_number;
	private EditText et_content;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.call);
		et_number = (EditText) findViewById(R.id.number);
		et_content = (EditText) findViewById(R.id.content);
		
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		 
        if (keyCode == KeyEvent.KEYCODE_BACK
                 && event.getRepeatCount() == 0) {
            //do something...
        	Intent intent0 = new Intent(ActivityCall.this,com.my.gun.Activity_setup.class);
			startActivity(intent0);	        	        	        	                
             return true;
         }
         return super.onKeyDown(keyCode, event);
     }
	
	
	
	public void call(View view)
	{
		String number=et_number.getText().toString();
		if(!TextUtils.isEmpty(number))
		{
			Intent intent = new  Intent();
			intent.setAction(Intent.ACTION_CALL);
			intent.setData(Uri.parse("tel:"+number));
			startActivity(intent);
		}else{
			Toast.makeText(getApplicationContext(), "�绰����Ϊ��", 0).show();
		}			
	}
	@SuppressWarnings("deprecation")
	public void send(View view)
	{
		String number=et_number.getText().toString();
		String content= et_content.getText().toString().trim();
		if(TextUtils.isEmpty(number)&&TextUtils.isEmpty(content))
		{
			Toast.makeText(ActivityCall.this, "�绰�����������Ϊ��", 0).show();
		}else{
			System.out.println("ssssssssssssssssssssssssssss");
			SmsManager sms= SmsManager.getDefault();
			System.out.println("qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq");
			sms.sendTextMessage(number, null, content, null, null);	
			System.out.println("eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");	
			Toast.makeText(ActivityCall.this, "���ͳɹ�!!", 0).show();
		}			
	}
}
