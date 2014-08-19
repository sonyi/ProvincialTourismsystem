package com.funo.provincialtourism;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.funo.antennatestsystem.R;
import com.funo.provincialtourism.tool.Contants;

public class Map_CharacteristicActivity extends BaseActivity implements OnItemClickListener {
	private boolean b = false;
	private View map_bntvs;
	private ProvincialMyApplicition app;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tese);
		app = (ProvincialMyApplicition) this.getApplication();
		app.addActivity(this);
	}

	@Override
	protected void onResume() {
		initUI();
		initTitle();
		setBottombnt(getBottombnt(), 0,getBottomtv());
		super.onResume();
	}

	/**
	 * 键盘监听事件
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (event.getKeyCode() == event.KEYCODE_BACK) {
			Intent intent=new Intent(Map_CharacteristicActivity.this, Map_CityLocationActivity.class);
			intent.putExtra("cityname", getIntent().getStringExtra("cityname"));
			intent.putExtra("latitude", getIntent().getDoubleExtra("latitude",0.0));
			intent.putExtra("longitude", getIntent().getDoubleExtra("longitude",0.0));
			startActivity(intent);
			 overridePendingTransition(R.animator.in_from_right, R.animator.out_to_left); 
			finish();
		}
		return super.onKeyDown(keyCode, event);
	}

	/**
	 * 触屏监听事件
	 */
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_UP:
			b = false;
			map_bntvs.setVisibility(8);
			break;
		}
		return super.onTouchEvent(event);
	}

	@Override
	protected void onPause() {
		b = false;
		map_bntvs.setVisibility(8);
		super.onPause();
	}

	@Override
	protected void onDestroy() {
		app.removeActivity(this);
		super.onDestroy();
	}

	/**
	 * 设置标题
	 */
	private void initTitle() {
		initTitlebase();
		bnt_back.setVisibility(0);
		bnt_back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent=new Intent(Map_CharacteristicActivity.this, Map_CityLocationActivity.class);
				intent.putExtra("cityname", getIntent().getStringExtra("cityname"));
				intent.putExtra("latitude", getIntent().getDoubleExtra("latitude",0.0));
				intent.putExtra("longitude", getIntent().getDoubleExtra("longitude",0.0));
				startActivity(intent);
				 overridePendingTransition(R.animator.in_from_right, R.animator.out_to_left); 
				finish();
			}
		});
		bnt_meun.setVisibility(0);
		title.setText("本地特色");
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
	private int[] images = { R.drawable.qingxin_image0201,
			R.drawable.qingxin_image0202, R.drawable.qingxin_image0203,
			R.drawable.qingxin_image0204, R.drawable.qingxin_image0205,
			R.drawable.qingxin_image0206 };
	private String date[][] = {
			{ "中秋博冰", "博饼时，桌上放一大海碗，每人轮流抓取六个骰子，掷入碗里，发出叮叮当当的脆响" },
			{ "家庭音乐会", "它像一朵朵瑰丽的鲜花，在小岛上四处开放。" },
			{ "泡咖啡馆和猫文化", "厦门人原本嗜茶，厦门有中国最好的茶馆" },
			{ "厦门国际马拉松赛", "创办于2003年。举办时间为每年元旦后的第一个星期六。与创办于1981年的北京国际马拉松赛成“一南一北、春秋交替”之势" },
			{ "凤凰花旅游节", "时间：凤凰花开的季节（5～6月），每两年举办一次，与海洋文化旅游节交替进行。" },
			{ "海洋文化旅游节", "时间：10月国庆期间，每两年举办一次，与凤凰花旅游节交替进行。" },
		 };
	/**
	 * 初始化UI
	 */
	private void initUI() {
		map_bntvs = (View) findViewById(R.id.map_bntvs);
		ListView list = (ListView) findViewById(R.id.listView1);
		// 生成动态数组，加入数据
		List<Map<String, Object>> listItem = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < date.length; i++) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("ItemImage", images[i]);// 图像资源的ID
			map.put("ItemTitle", date[i][0]);
			map.put("ItemText", date[i][1]);
			listItem.add(map);
		}
		// 生成适配器的Item和动态数组对应的元素
		MyAdapter listItemAdapter = new MyAdapter(this, listItem);

		// 添加并且显示
		list.setAdapter(listItemAdapter);
		list.setOnItemClickListener(this);
	}


	/**
	 * 适配器
	 * 
	 * @author Administrator
	 * 
	 */
	class MyAdapter extends BaseAdapter {

		private Context context; // 上下文
		private List<Map<String, Object>> data;
		Map<String, Boolean> states = new HashMap<String, Boolean>();// 用于记录每个RadioButton的状态，并保证只可选一个

		public MyAdapter() {
			super();
			// TODO Auto-generated constructor stub
		}

		public MyAdapter(Context context, List<Map<String, Object>> data) {
			super();
			this.context = context;
			this.data = data;
		}

		/**
		 * 数据集合的大小
		 */
		@Override
		public int getCount() {
			return data.size();
		}

		/**
		 * 获取数据集中与指定索引对应的数据项
		 */
		@Override
		public Object getItem(int position) {
			return data.get(position);
		}

		/**
		 * 获取列表中与指定索引对应行的id
		 */
		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(final int position, View convertView,
				ViewGroup parent) {
			ViewCache cache = null;

			if (convertView == null) {
				convertView = LayoutInflater.from(context).inflate(
						R.layout.activity_tese_item1, null);
				cache = new ViewCache();
				cache.text1 = (TextView) convertView
						.findViewById(R.id.textView1);
				cache.text2 = (TextView) convertView
						.findViewById(R.id.textView2);
				cache.image = (ImageView) convertView
						.findViewById(R.id.imageView1);
				convertView.setTag(cache); // 将要显示的数据对象绑定在convertView上
			} else {
				cache = (ViewCache) convertView.getTag(); // 取出绑定在convertView上的对象
			}

			// 给每个item上的TextView上的text赋值
			cache.text1.setText((String) data.get(position).get("ItemTitle"));
			String texts=(String) data.get(position).get("ItemText");
			if(texts.length()>37)
			{
				texts=texts.substring(0, 37)+"..";
			}
			cache.text2.setText(texts);
			// 给每个item上的ImageView赋值
			cache.image.setBackgroundResource(Contants.JQ_IMAGES[2][position]);

			return convertView;
		}

		private class ViewCache {
			private TextView text1;
			private TextView text2;
			private ImageView image;
		}
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		Intent intent = new Intent(Map_CharacteristicActivity.this,
				Traveling_itemActivity.class);
		intent.putExtra("cityname",getIntent().getStringExtra("cityname"));
		intent.putExtra("latitude", getIntent().getDoubleExtra("latitude",0.0));
		intent.putExtra("longitude", getIntent().getDoubleExtra("longitude",0.0));
		intent.putExtra("Char", true);
		startActivity(intent);
		overridePendingTransition(R.animator.in_from_right,
				R.animator.out_to_left);
		finish();
	}

	public void bottoms(View v) {
		Intent intent = null;
		switch (v.getId()) {
		case R.id.bottombnt_2:
			intent = new Intent(Map_CharacteristicActivity.this, Provincial_ClearIndexActivity.class);
			break;
		case R.id.bottombnt_3:
			intent = new Intent(Map_CharacteristicActivity.this, Provincial_TicketActivity.class);
			break;
		case R.id.bottombnt_4:
			intent = new Intent(Map_CharacteristicActivity.this, Provincial_SettingActivity.class);
			break;
		}
		if (intent != null)
		{
			intent.putExtra("acitivity_num1", true);
			startActivity(intent);
			overridePendingTransition(R.animator.in_from_right, R.animator.out_to_left); 
		}
	}

}
