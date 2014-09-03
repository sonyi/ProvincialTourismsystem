package com.example.activitylifecycledemo;

import java.util.concurrent.FutureTask;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SecondActivity extends Activity {
	Button mBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.second_activity);
		Log.i("sign", "******SecondActivity----->onCreate******");
		mBtn = (Button) findViewById(R.id.btn2);//获取控件
		mBtn.setOnClickListener(new OnClickListener() {//设置监听事件
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(SecondActivity.this,FirstActivity.class);
				SecondActivity.this.startActivity(intent);
			}
		});
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		Log.i("sign", "******SecondActivity----->onStart******");
	}
	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		Log.i("sign", "******SecondActivity----->onRestart******");
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Log.i("sign", "******SecondActivity----->onResume******");
	}
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		Log.i("sign", "******SecondActivity----->onPause******");
	}
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		Log.i("sign", "******SecondActivity----->onStop******");
	}
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.i("sign", "******SecondActivity----->onDestroy******");
	}
}
