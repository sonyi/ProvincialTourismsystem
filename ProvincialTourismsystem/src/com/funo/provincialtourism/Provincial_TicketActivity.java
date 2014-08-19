package com.funo.provincialtourism;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.funo.antennatestsystem.R;

public class Provincial_TicketActivity extends BaseActivity implements
		OnItemClickListener {
	private ProvincialMyApplicition app;
	private String bnts[] = { "厦门观音山水上乐园", "鼓浪屿门票5折特惠", "园博园热门景点年度套票",
			"0元玩转三坊七巷", "厦门国际马拉松赛", "泡咖啡馆和猫文化" };
	List<Map<String, String>> list = new ArrayList<Map<String, String>>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_provincial__ticket);
		app = (ProvincialMyApplicition) getApplication();
		app.addActivity(this);
	}

	private void initTitle() {
		initTitlebase();
		title.setText("门票预定");
	}

	private void initUI() {
		ListView ticket_list = (ListView) findViewById(R.id.ticket_list);
		list.clear();
		for (int i = 0; i < bnts.length; i++) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("bnts", bnts[i]);
			list.add(map);
		}

		SimpleAdapter simpleAdapter = new SimpleAdapter(this, list,
				R.layout.ticket_bntitem, new String[] { "bnts" },
				new int[] { R.id.textView1 });
		ticket_list.setAdapter(simpleAdapter);
		ticket_list.setOnItemClickListener(this);
	}

	@Override
	protected void onResume() {
		initUI();
		initTitle();
		setBottombnt(getBottombnt(), 2, getBottomtv());
		super.onResume();
	}

	public void bottoms(View v) {
		Intent intent = null;
		boolean acitivity_num1 = getIntent().getBooleanExtra("acitivity_num1",
				false);
		// boolean acitivity_num2 =
		// getIntent().getBooleanExtra("acitivity_num2",
		// false);
		// boolean acitivity_num4 =
		// getIntent().getBooleanExtra("acitivity_num4",
		// false);
		switch (v.getId()) {
		case R.id.bottombnt_1:
			intent = new Intent(Provincial_TicketActivity.this,
					Provincial_MapActivity.class);
			intent.putExtra("acitivity_num3", true);
			break;
		case R.id.bottombnt_2:
			intent = new Intent(Provincial_TicketActivity.this,
					Provincial_ClearIndexActivity.class);
			break;
		case R.id.bottombnt_4:
			intent = new Intent(Provincial_TicketActivity.this,
					Provincial_SettingActivity.class);

			break;
		}
		if (intent != null) {
			if (!acitivity_num1) {
				intent.putExtra("acitivity_num3", false);
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

	public void tickList(View v) {
		toList();
	}

	private void toList() {
		startActivity(new Intent(Provincial_TicketActivity.this,
				Tichket_ListActivity.class));
		overridePendingTransition(R.animator.in_from_right,
				R.animator.out_to_left);
		finish();
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		toList();
	}
}
