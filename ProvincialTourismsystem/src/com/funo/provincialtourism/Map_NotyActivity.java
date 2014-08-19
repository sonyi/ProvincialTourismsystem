package com.funo.provincialtourism;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;

import com.funo.antennatestsystem.R;

public class Map_NotyActivity extends BaseActivity implements
		OnItemClickListener {
	ListView listView = null;
	private boolean b = false;
	private String date[][] = {
			{ "2014-08-01", "轮渡公司关于钢琴码头重启的通知", "轮渡公司钢琴码头将于2014年9月2号正式启用..." },
			{ "2014-08-02", "鼓浪屿小贴士", "在鼓浪屿，建议步行，慢慢游览各个景点，才能真正感受到“花园城市”的魅力。" },
			{ "2014-08-03", "鼓浪屿小贴士",
					"鼓浪屿上有很多龙头路、漳州路，所以千万要动嘴巴去问岛上的人，否则会走很多冤枉路。" },
			{ "2014-08-04", "鼓浪屿小贴士",
					"在鼓浪屿看到朋友推荐的卖特产的店时，千万别一时兴奋而忘了后面还有很多景点，负重旅行可不是我们想要的。或者，可以先寄放在店家那里，走完所有景点之后再回去取。" },
			{ "2014-08-05", "鼓浪屿小贴士", "到海边礁石上玩，一定要注意潮水涨落，要在潮涨前上岸，安全第一" },
			{ "2014-08-06", "鼓浪屿小贴士", "初次去鼓浪屿建议花一元钱登上渡船的二楼，观赏厦门海景。" },
			{ "2014-08-07", "鼓浪屿小贴士", "鼓浪屿的公共场所是禁止吸烟的，违反的可能会受到惩罚；" },
			{ "2014-08-08", "鼓浪屿小贴士", "在鼓浪屿乱丢垃圾、破坏市容整洁的，会依据情节轻重受到处罚" },
			{ "2014-08-09", "最佳旅游时间",
					"7、8、9月份台风季节，海边的船只全部停开，不能欣赏到优美的海景，其他时间去鼓浪屿旅游都很好。不过节假日期间游人也很多。" }, };
	List<Map<String, String>> listAdapter = new ArrayList<Map<String, String>>();
	private View map_bntvs;
	private ProvincialMyApplicition app;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tese);
		app = (ProvincialMyApplicition) this.getApplication();
		app.addActivity(this);
	}

	/**
	 * 键盘监听事件
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (event.getKeyCode() == event.KEYCODE_BACK) {
			Intent intent = new Intent(Map_NotyActivity.this,
					Map_CityLocationActivity.class);
			intent.putExtra("cityname", getIntent().getStringExtra("cityname"));
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
		setBottombnt(getBottombnt(), 0, getBottomtv());
		super.onResume();
	}

	@Override
	protected void onDestroy() {
		app.removeActivity(this);
		super.onDestroy();
	}

	/**
	 * 初始化标题
	 */
	private void initTitle() {
		initTitlebase();
		bnt_back.setVisibility(0);
		bnt_back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Map_NotyActivity.this,
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
		title.setText("最新通知");
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

	/**
	 * 初始化UI
	 */
	private void initUI() {
		map_bntvs = (View) findViewById(R.id.map_bntvs);
		listView = (ListView) super.findViewById(R.id.listView1);
		listView.setAdapter(new MyAdapter());
		listView.setOnItemClickListener(this);
	}

	/**
	 * 适配器
	 * 
	 * @author Administrator
	 * 
	 */
	class MyAdapter extends BaseAdapter {
		class MyViewHolder {
			public TextView date, title, content;

		}

		@Override
		public int getCount() {
			return date.length;
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			MyViewHolder viewHolder = null;
			if (convertView == null) {
				viewHolder = new MyViewHolder();
				convertView = getLayoutInflater().inflate(
						R.layout.notify_info_list, null);
				viewHolder.date = (TextView) convertView
						.findViewById(R.id.date);
				viewHolder.title = (TextView) convertView
						.findViewById(R.id.title);
				viewHolder.content = (TextView) convertView
						.findViewById(R.id.content);
				convertView.setTag(viewHolder);
			} else {
				viewHolder = (MyViewHolder) convertView.getTag();
			}
			viewHolder.date.setText(date[position][0]);
			viewHolder.title.setText(date[position][1]);
			String conts = date[position][2];
			if (conts.length() > 22) {
				conts = conts.substring(0, 21) + "..";
			}
			viewHolder.content.setText(conts);
			return convertView;
		}
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		Intent intent = new Intent(Map_NotyActivity.this,
				Traveling_itemActivity.class);
		intent.putExtra("cityname",getIntent().getStringExtra("cityname"));
		intent.putExtra("latitude", getIntent().getDoubleExtra("latitude",0.0));
		intent.putExtra("longitude", getIntent().getDoubleExtra("longitude",0.0));
		intent.putExtra("Noty", true);
		startActivity(intent);
		overridePendingTransition(R.animator.in_from_right,
				R.animator.out_to_left);
		finish();
	}

	public void bottoms(View v) {
		Intent intent = null;
		switch (v.getId()) {
		case R.id.bottombnt_2:
			intent = new Intent(Map_NotyActivity.this,
					Provincial_ClearIndexActivity.class);
			break;
		case R.id.bottombnt_3:
			intent = new Intent(Map_NotyActivity.this,
					Provincial_TicketActivity.class);
			break;
		case R.id.bottombnt_4:
			intent = new Intent(Map_NotyActivity.this,
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
}
