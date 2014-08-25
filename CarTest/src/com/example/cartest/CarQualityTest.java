package com.example.cartest;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

public class CarQualityTest extends Activity implements OnClickListener {

	private ImageView imgBack;
	private TextView tvText;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_car_quality_test);
		initWidget();
	}
	private void initWidget(){
		imgBack=(ImageView) findViewById(R.id.iv_img_back);
		tvText=(TextView) findViewById(R.id.tv_text);
		imgBack.setOnClickListener(this);
		
	}
	
	class GetQualityTestTask extends AsyncTask<String, Void, String>{
		
		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			return null;
		}
		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
		}
	}
	
	@Override
	public void onClick(View v) {
		if(v.getId() == R.id.iv_img_back){
			finish();
		}
	}
}
