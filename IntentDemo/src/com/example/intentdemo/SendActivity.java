package com.example.intentdemo;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SendActivity extends ActionBarActivity {
	Button mSend;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_send);
		mSend = (Button) this.findViewById(R.id.btn_send);//获取控件
		mSend.setOnClickListener(new OnClickListener() {//设置监听事件，点击按钮后，跳转到第二个activity
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//实例化一个intent对象
				Intent intent = new Intent(SendActivity.this, ReceiveActivity.class);
				//在intent中传递数据
				intent.putExtra("msg", "这是sendActivity发送过来的数据");
				//启动一个intent对象，进行activity之间跳转
				SendActivity.this.startActivity(intent);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
