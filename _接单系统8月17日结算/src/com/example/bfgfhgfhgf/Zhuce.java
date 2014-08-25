package com.example.bfgfhgfhgf;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

public class Zhuce extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		setContentView(R.layout.activity_zhuce);
		
		TextView title = (TextView) findViewById(R.id.secondtitle_tv_theme);
		title.setText("注册");
		
		ImageView turnback = (ImageView) findViewById(R.id.secondtitle_imgBack);
		turnback.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.secondtitle_imgBack:
			finish();
			break;

		default:
			break;
		}
	}
}
