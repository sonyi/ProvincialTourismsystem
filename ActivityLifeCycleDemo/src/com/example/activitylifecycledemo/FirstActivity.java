package com.example.activitylifecycledemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class FirstActivity extends Activity {
	Button mBtn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.first_activity);
		mBtn = (Button) this.findViewById(R.id.btn1);//获取按钮
		mBtn.setOnClickListener(new OnClickListener() {//设置监听事件
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(FirstActivity.this,SecondActivity.class);
				FirstActivity.this.startActivity(intent);//执行跳转操作
				
			}
		});
		Log.i("sign", "******FirstActivity----->onCreate******");
	}
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		Log.i("sign", "******FirstActivity----->onStart******");
	}
	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		Log.i("sign", "******FirstActivity----->onRestart******");
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Log.i("sign", "******FirstActivity----->onResume******");
	}
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		Log.i("sign", "******FirstActivity----->onPause******");
	}
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		Log.i("sign", "******FirstActivity----->onStop******");
	}
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.i("sign", "******FirstActivity----->onDestroy******");
	}
}
