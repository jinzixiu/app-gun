package com.my.gun;




import android.view.GestureDetector;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class Activity_setup extends Activity {
	private static String [] names = {
		"ÐÞÐÞ°Ö°Ö","ÐÞÐÞÂéÂé","ÐÞÐÞµÜµÜ",
		"ÐÞÐÞÅ®ÓÑ","²éÑ¯»°·Ñ","Èí¼þÉùÃ÷",		
	};
	private static int[] ids = {
		R.drawable.baba,R.drawable.mama,R.drawable.didi,
		R.drawable.niuniu,R.drawable.huafei,R.drawable.shenming,		
	};
	private GridView list_home;
	private MyAdapter adapter;
	private GestureDetector detector;
	
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		detector.onTouchEvent(event);
		return super.onTouchEvent(event);
	}
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home_activity);
		
		
		
		
		list_home=(GridView) findViewById(R.id.list_home);
		adapter = new MyAdapter();
		list_home.setAdapter(adapter);
		list_home.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int position,
					long id) {
				// TODO Auto-generated method stub
				switch (position) {
				case 0:
					call("000000000");
					break;
				case 1:
					call("111111111");
					break;
				case 2:
					call("222222222");
					break;
				case 3:
					call("33333333333");
					break;
				case 4:
					call("4444444444");
					break;									
				case 5:
					Uri uri = Uri.parse("https://help.github.com/");  
					Intent it = new Intent(Intent.ACTION_VIEW, uri);  
					startActivity(it);
					
					break;
					
				default:
					break;
				}
			}

		
		});
		
		
	}
	
	public void call(String number)
	{
		Intent intent = new  Intent();
		intent.setAction(Intent.ACTION_CALL);
		intent.setData(Uri.parse("tel:"+number));
		startActivity(intent);
	}
	
	
	private class MyAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return names.length;
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View view= View.inflate(Activity_setup.this, R.layout.list_item_home, null);
			ImageView iv_item= (ImageView) view.findViewById(R.id.iv_item);
			TextView tv_item = (TextView)view.findViewById(R.id.tv_item);
			tv_item.setText(names[position]);
			iv_item.setImageResource(ids[position]);
			return view;
		}
		
	}

	
	public void next(View view)
	{
		Intent intent = new Intent(this,ActivityCall.class);
		startActivity(intent);
	}
}
