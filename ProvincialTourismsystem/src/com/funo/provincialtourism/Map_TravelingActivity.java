package com.funo.provincialtourism;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.funo.antennatestsystem.R;

public class Map_TravelingActivity extends BaseActivity implements
		OnItemClickListener {
	private ProvincialMyApplicition app;
	private ListView mLstView;
	private String bnts[] = { "机场", "汽车站", "火车站", "旅游咨询", "旅游投资", "旅行社", "潮位预报" };
	List<Map<String, String>> list = new ArrayList<Map<String, String>>();
	private View map_bntvs;
	private boolean b = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_touris);
		app = (ProvincialMyApplicition) this.getApplication();
		app.addActivity(this);
	}

	/**
	 * 键盘监听事件
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (event.getKeyCode() == event.KEYCODE_BACK) {
				Intent intent = new Intent(Map_TravelingActivity.this,
						Map_CityLocationActivity.class);
				intent.putExtra("cityname",
						getIntent().getStringExtra("cityname"));
				intent.putExtra("latitude",
						getIntent().getDoubleExtra("latitude", 0.0));
				intent.putExtra("longitude",
						getIntent().getDoubleExtra("longitude", 0.0));
				startActivity(intent);
				overridePendingTransition(R.animator.in_from_right,
						R.animator.out_to_left);
				finish();
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	protected void onResume() {
		initUI();
		initTitle();
		setBottombnt(getBottombnt(), 0,getBottomtv());
		initData();
		super.onResume();
	}

	private void initData() {
		list.clear();
		for (int i = 0; i < bnts.length; i++) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("bnts", bnts[i]);
			list.add(map);
		}

		SimpleAdapter simpleAdapter = new SimpleAdapter(this, list,
				R.layout.traveling_bntitem, new String[] { "bnts" },
				new int[] { R.id.textView1 });
		mLstView.setAdapter(simpleAdapter);
		mLstView.setOnItemClickListener(this);
	}

	private void initTitle() {
		initTitlebase();
		bnt_back.setVisibility(0);
		bnt_back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
					Intent intent = new Intent(Map_TravelingActivity.this,
							Map_CityLocationActivity.class);
					intent.putExtra("cityname",
							getIntent().getStringExtra("cityname"));
					intent.putExtra("latitude",
							getIntent().getDoubleExtra("latitude", 0.0));
					intent.putExtra("longitude",
							getIntent().getDoubleExtra("longitude", 0.0));
					startActivity(intent);
					overridePendingTransition(R.animator.in_from_right,
							R.animator.out_to_left);
					finish();
			}
		});
		bnt_meun.setVisibility(0);
		title.setText("旅游服务");
		bnt_meun.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (b) {
					map_bntvs.setVisibility(8);
				} else {
					map_bntvs.setVisibility(0);
				}
				b = !b;
			}
		});
	}

	/**
	 * 菜单按钮事件
	 */
	public void mapViewchanges(View v) {
		b = false;
		map_bntvs.setVisibility(8);
	}

	@Override
	protected void onPause() {
		b = false;
		map_bntvs.setVisibility(8);
		super.onPause();
	}

	private void initUI() {
		map_bntvs = (View) findViewById(R.id.map_bntvs);
		mLstView = (ListView) findViewById(R.id.listView1);
		mLstView.setOnItemClickListener(this);
	}
	@Override
	protected void onDestroy() {
		app.removeActivity(this);
		super.onDestroy();
	}

	public void bottoms(View v) {
		Intent intent = null;
		switch (v.getId()) {
		case R.id.bottombnt_2:
			intent = new Intent(Map_TravelingActivity.this,
					Provincial_ClearIndexActivity.class);
			break;
		case R.id.bottombnt_1:
			intent = new Intent(Map_TravelingActivity.this,
					Provincial_MapActivity.class);
			break;
		case R.id.bottombnt_4:
			intent = new Intent(Map_TravelingActivity.this,
					Provincial_SettingActivity.class);
			break;
		}
		if (intent != null) {
			intent.putExtra("acitivity_num1", true);
			startActivity(intent);
			overridePendingTransition(R.animator.in_from_right,
					R.animator.out_to_left);
		}
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		Intent intent = new Intent(Map_TravelingActivity.this,
				Traveling_itemActivity.class);
		intent.putExtra("cityname",getIntent().getStringExtra("cityname"));
		intent.putExtra("latitude", getIntent().getDoubleExtra("latitude",0.0));
		intent.putExtra("longitude", getIntent().getDoubleExtra("longitude",0.0));
		startActivity(intent);
		overridePendingTransition(R.animator.in_from_right,
				R.animator.out_to_left);
		finish();
		
	}
}
