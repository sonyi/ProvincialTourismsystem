package com.funo.provincialtourism;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.funo.antennatestsystem.R;
import com.funo.provincialtourism.Map_CharacteristicActivity.MyAdapter;
import com.funo.provincialtourism.tool.Contants;

public class Tichket_ListActivity extends BaseActivity {

	private ProvincialMyApplicition app;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_touris);
		app = (ProvincialMyApplicition) getApplication();
		app.addActivity(this);
	}

	@Override
	protected void onResume() {
		initUI();
		initTitle();
		setBottombnt(getBottombnt(), 2,getBottomtv());
		super.onResume();
	}
	public void bottoms(View v) {
		Intent intent = null;
		switch (v.getId()) {
		case R.id.bottombnt_1:
			intent = new Intent(Tichket_ListActivity.this,
					Provincial_MapActivity.class);
			intent.putExtra("acitivity_num3", true);
			break;
		case R.id.bottombnt_2:
			intent = new Intent(Tichket_ListActivity.this,
					Provincial_ClearIndexActivity.class);
			break;
		case R.id.bottombnt_4:
			intent = new Intent(Tichket_ListActivity.this,
					Provincial_SettingActivity.class);
			
			break;
		}
		if (intent != null) {
			intent.putExtra("acitivity_num3", true);
				startActivity(intent);
				overridePendingTransition(R.animator.in_from_right,
						R.animator.out_to_left);
		}

	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (event.getKeyCode() == event.KEYCODE_BACK) {
			startActivity(new Intent(Tichket_ListActivity.this,Provincial_TicketActivity.class));
			overridePendingTransition(R.animator.in_from_right,
					R.animator.out_to_left);
			finish();
		}
		return super.onKeyDown(keyCode, event);
	}
	private int[] images = { R.drawable.qingxin_image0201,
			R.drawable.qingxin_image0202, R.drawable.qingxin_image0203,
			R.drawable.qingxin_image0204, R.drawable.qingxin_image0205,
			R.drawable.qingxin_image0206 };
	private String date[][] = {
			{ "厦门鼓浪屿", "厦门市思明区鼓浪屿" },
			{ "厦门日光岩", "厦门市思明区鼓浪屿" },
			{ "厦门市环岛路", "厦门市思明区环岛路" },
			{ "厦门大学", "厦门市思明区厦门大学" },
			{ "曾厝垵", "厦门市思明区曾厝垵" },
			{ "会展中心", "厦门市思明区会展中心" },
		 };
	private List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>();
	private void initUI() {
		listItems.clear();
		ListView list = (ListView) findViewById(R.id.listView1);
		list.setDivider(getResources().getDrawable(R.color.bottom_line));
		list.setDividerHeight(1);
		// 生成动态数组，加入数据
		for (int i = 0; i < date.length; i++) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("ItemImage", images[i]);// 图像资源的ID
			map.put("ItemTitle", date[i][0]);
			map.put("ItemText", date[i][1]);
			listItems.add(map);
		}
		MyAdapter listItemAdapter = new MyAdapter(Tichket_ListActivity.this, listItems);
		list.setAdapter(listItemAdapter);
	}
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
						R.layout.ticket_listitem, null);
				cache = new ViewCache();
				cache.text1 = (TextView) convertView
						.findViewById(R.id.title);
				cache.text2 = (TextView) convertView
						.findViewById(R.id.location);
				cache.image = (ImageView) convertView
						.findViewById(R.id.image);
				convertView.setTag(cache); // 将要显示的数据对象绑定在convertView上
			} else {
				cache = (ViewCache) convertView.getTag(); // 取出绑定在convertView上的对象
			}

			// 给每个item上的TextView上的text赋值
			cache.text1.setText((String) data.get(position).get("ItemTitle"));
			String texts=(String) data.get(position).get("ItemText");
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
	private void initTitle() {
		initTitlebase();
		title.setText("门票列表");
		bnt_back.setVisibility(0);
		bnt_back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent(Tichket_ListActivity.this,Provincial_TicketActivity.class));
				overridePendingTransition(R.animator.in_from_right,
						R.animator.out_to_left);
				finish();
			}
		});
	}
	@Override
	protected void onDestroy() {
		app.removeActivity(this);
		super.onDestroy();
	}
}
