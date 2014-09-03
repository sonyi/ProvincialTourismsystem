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
		mSend = (Button) this.findViewById(R.id.btn_send);//��ȡ�ؼ�
		mSend.setOnClickListener(new OnClickListener() {//���ü����¼��������ť����ת���ڶ���activity
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//ʵ����һ��intent����
				Intent intent = new Intent(SendActivity.this, ReceiveActivity.class);
				//��intent�д�������
				intent.putExtra("msg", "����sendActivity���͹���������");
				//����һ��intent���󣬽���activity֮����ת
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
