package com.funo.provincialtourism;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.map.Geometry;
import com.baidu.mapapi.map.GraphicsOverlay;
import com.baidu.mapapi.map.ItemizedOverlay;
import com.baidu.mapapi.map.LocationData;
import com.baidu.mapapi.map.MapController;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationOverlay;
import com.baidu.mapapi.map.OverlayItem;
import com.baidu.mapapi.map.PopupClickListener;
import com.baidu.mapapi.map.PopupOverlay;
import com.baidu.mapapi.map.Symbol;
import com.baidu.mapapi.map.MyLocationOverlay.LocationMode;
import com.baidu.mapapi.map.Symbol.Color;
import com.baidu.platform.comapi.basestruct.GeoPoint;
import com.funo.antennatestsystem.R;
import com.funo.provincialtourism.tool.BMapUtil;
import com.funo.provincialtourism.tool.Contants;

public class Provincial_MapActivity extends BaseActivity implements
		OnClickListener {
	private ProvincialMyApplicition app;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_provincial_map);
		app = (ProvincialMyApplicition) getApplication();
		app.addActivity(this);
	}

	/**
	 * 设置标题
	 */
	private void initTitle() {
		initTitlebase();
		title.setText("地图");
	}

	@Override
	protected void onResume() {
		initUI();
		initTitle();
		setBottombnt(getBottombnt(), 0,getBottomtv());
		super.onResume();
	}

	/**
	 * 初始化UI
	 */
	private void initUI() {
		TextView bnt_0 = (TextView) findViewById(R.id.bnt_0);
		TextView bnt_1 = (TextView) findViewById(R.id.bnt_1);
		TextView bnt_2 = (TextView) findViewById(R.id.bnt_2);
		TextView bnt_3 = (TextView) findViewById(R.id.bnt_3);
		TextView bnt_4 = (TextView) findViewById(R.id.bnt_4);
		TextView bnt_5 = (TextView) findViewById(R.id.bnt_5);
		TextView bnt_6 = (TextView) findViewById(R.id.bnt_6);
		TextView bnt_7 = (TextView) findViewById(R.id.bnt_7);
		TextView bnt_8 = (TextView) findViewById(R.id.bnt_8);
		TextView bnt_9 = (TextView) findViewById(R.id.bnt_9);
		bnt_1.setOnClickListener(this);
		bnt_2.setOnClickListener(this);
		bnt_3.setOnClickListener(this);
		bnt_4.setOnClickListener(this);
		bnt_5.setOnClickListener(this);
		bnt_6.setOnClickListener(this);
		bnt_7.setOnClickListener(this);
		bnt_8.setOnClickListener(this);
		bnt_9.setOnClickListener(this);
		setDrawableTop(bnt_0, R.drawable.bnt_line0,this);
		setDrawableTop(bnt_1, R.drawable.bnt_line7,this);
		setDrawableTop(bnt_2, R.drawable.bnt_line5,this);
		setDrawableTop(bnt_3, R.drawable.bnt_line8,this);
		setDrawableTop(bnt_4, R.drawable.bnt_line6,this);
		setDrawableTop(bnt_5, R.drawable.bnt_line4,this);
		setDrawableTop(bnt_6, R.drawable.bnt_line1,this);
		setDrawableTop(bnt_7, R.drawable.bnt_line9,this);
		setDrawableTop(bnt_8, R.drawable.bnt_line2,this);
		setDrawableTop(bnt_9, R.drawable.bnt_line3,this);
	}

	@SuppressLint("NewApi")
	public static void setDrawableTop(TextView v, int i,Activity ac) {
		Drawable drawable = ac.getResources().getDrawable(i);
		drawable.setBounds(0, 0, v.getMaxWidth(), drawable.getMinimumHeight());
		v.setCompoundDrawables(null, drawable, null, null);
	}

	public void toLocation(View v) {
		int num = 0;
		switch (v.getId()) {
		case R.id.bnt_11:
			num = 0;
			break;
		case R.id.bnt_21:
			num = 1;
			break;
		case R.id.bnt_31:
			num = 2;
			break;
		case R.id.bnt_41:
			num = 3;
			break;
		case R.id.bnt_51:
			num = 4;
			break;
		case R.id.bnt_61:
			num = 5;
			break;
		case R.id.bnt_71:
			num = 6;
			break;
		case R.id.bnt_81:
			num = 7;
			break;
		case R.id.bnt_91:
			num = 8;
			break;
		default:
			num = 0;
			break;
		}
		Intent intent = new Intent(this, Map_CityLocationActivity.class);
		intent.putExtra("cityname", Contants.CITYNAMES[num]);
		intent.putExtra("latitude", Contants.CITY_LOCATIONS[num][1]);
		intent.putExtra("longitude", Contants.CITY_LOCATIONS[num][0]);
		startActivity(intent);
		overridePendingTransition(R.animator.in_from_right,
				R.animator.out_to_left);
		finish();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
	}

	public void bottoms(View v) {
		Intent intent = null;
		boolean acitivity_num2 = getIntent().getBooleanExtra("acitivity_num2",
				false);
		boolean acitivity_num3 = getIntent().getBooleanExtra("acitivity_num3",
				false);
		switch (v.getId()) {
		case R.id.bottombnt_2:
			intent = new Intent(Provincial_MapActivity.this,
					Provincial_ClearIndexActivity.class);
			break;
		case R.id.bottombnt_3:
			intent = new Intent(Provincial_MapActivity.this,
					Provincial_TicketActivity.class);
			break;
		case R.id.bottombnt_4:
			intent = new Intent(Provincial_MapActivity.this,
					Provincial_SettingActivity.class);
			break;
		}
		if (intent != null) {
			if (!acitivity_num3) {
				intent.putExtra("acitivity_num1", false);
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

	@Override
	public void onClick(View v) {
		int num = 0;
		switch (v.getId()) {
		case R.id.bnt_1:
			num = 0;
			break;
		case R.id.bnt_2:
			num = 1;
			break;
		case R.id.bnt_3:
			num = 2;
			break;
		case R.id.bnt_4:
			num = 3;
			break;
		case R.id.bnt_5:
			num = 4;
			break;
		case R.id.bnt_6:
			num = 5;
			break;
		case R.id.bnt_7:
			num = 6;
			break;
		case R.id.bnt_8:
			num = 7;
			break;
		case R.id.bnt_9:
			num = 8;
			break;
		}
		Intent intent = new Intent(this, Map_CityLocationActivity.class);
		intent.putExtra("cityname", Contants.CITYNAMES[num]);
		intent.putExtra("latitude", Contants.CITY_LOCATIONS[num][1]);
		intent.putExtra("longitude", Contants.CITY_LOCATIONS[num][0]);
		startActivity(intent);
		overridePendingTransition(R.animator.in_from_right,
				R.animator.out_to_left);
		finish();
	}
}
