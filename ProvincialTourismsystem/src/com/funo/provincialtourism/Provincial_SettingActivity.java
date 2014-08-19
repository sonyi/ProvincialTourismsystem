package com.funo.provincialtourism;

import com.funo.antennatestsystem.R;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Provincial_SettingActivity extends BaseActivity {

	private ProvincialMyApplicition app;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting);
		app = (ProvincialMyApplicition) getApplication();
		app.addActivity(this);
	}

	private void initTitle() {
		initTitlebase();
		title.setText("设置");
	}

	private void initUI() {

	}

	@Override
	protected void onResume() {
		initUI();
		initTitle();
		setBottombnt(getBottombnt(), 3,getBottomtv());
		super.onResume();
	}

	public void bottoms(View v) {
		Intent intent = null;
		boolean acitivity_num1 = getIntent().getBooleanExtra("acitivity_num1",
				false);
//		boolean acitivity_num2 = getIntent().getBooleanExtra("acitivity_num2",
//				false);
		boolean acitivity_num3 = getIntent().getBooleanExtra("acitivity_num3",
				false);
		switch (v.getId()) {
		case R.id.bottombnt_1:
			intent = new Intent(Provincial_SettingActivity.this,
					Provincial_MapActivity.class);
			break;
		case R.id.bottombnt_2:
			intent = new Intent(Provincial_SettingActivity.this,
					Provincial_ClearIndexActivity.class);
			break;
		case R.id.bottombnt_3:
			intent = new Intent(Provincial_SettingActivity.this,
					Provincial_TicketActivity.class);
		}
		if (intent != null) {
			if (!(acitivity_num1 || acitivity_num3)){
				if(acitivity_num1)
				{
					intent.putExtra("acitivity_num1", acitivity_num1);
				}
				if(acitivity_num3)
				{
					intent.putExtra("acitivity_num3", acitivity_num3);
				}
				startActivity(intent);
				overridePendingTransition(R.animator.in_from_right,
						R.animator.out_to_left);
			} 
			
			finish();
		}

	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (event.getKeyCode() == event.KEYCODE_BACK) {
			app.exit();
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	protected void onDestroy() {
		app.removeActivity(this);
		super.onDestroy();
	}
}
