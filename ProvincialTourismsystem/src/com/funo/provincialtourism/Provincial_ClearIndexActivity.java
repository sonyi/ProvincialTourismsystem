package com.funo.provincialtourism;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.funo.antennatestsystem.R;
import com.funo.provincialtourism.tool.Contants;
import com.funo.provincialtourism.view.MyGridView;

public class Provincial_ClearIndexActivity extends BaseActivity implements
		OnGroupExpandListener {

	private ItemAdapter itemadapter;
	private ItemAdapter[] itms;
	private ExpandableListView mExpandableListView;
	private ProvincialMyApplicition app;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_provincial_tourism_main);
		app = (ProvincialMyApplicition) getApplication();
		app.addActivity(this);
	}

	private void initTitle() {
		initTitlebase();
		title.setText("清新指数");
	}

	@Override
	protected void onResume() {
		initUI();
		initTitle();
		setBottombnt(getBottombnt(), 1, getBottomtv());
		initData();
		super.onResume();
	}

	private void initData() {
		ItemAdapter adapter0 = new ItemAdapter(0);
		ItemAdapter adapter1 = new ItemAdapter(1);
		ItemAdapter adapter2 = new ItemAdapter(2);
		ItemAdapter adapter3 = new ItemAdapter(3);
		itms = new ItemAdapter[] { adapter0, adapter1, adapter2, adapter3 };
	}

	/**
	 * 初始化UI
	 */
	private void initUI() {
		itemadapter = new ItemAdapter();
		mExpandableListView = (ExpandableListView) findViewById(R.id.expandableListView1);
		Mybase mybase = new Mybase();
		mExpandableListView.setAdapter(mybase);
		mExpandableListView.setGroupIndicator(null);
		mExpandableListView.setOnGroupExpandListener(this);

	}

	/**
	 * 父级View的缓存类
	 * 
	 * @author Administrator
	 * 
	 */
	class MyHoldView {
		public TextView city, showLine;
		// 温度
		public TextView wealth;
		// pm
		public TextView pm;
		// 拥堵数
		public TextView yd;
		public ImageView parentImageViw;
	}

	/**
	 * 子级View的缓存类
	 * 
	 * @author Administrator
	 * 
	 */
	class MyHoldViewchild {
		// 图标
		public ImageView imageView1;
		// 名称
		public TextView name;
		// 清新的文本
		public TextView tv_qingxin;
		// 中间那个|
		public TextView gg;
		// 拥挤的文本
		public TextView tv_yongj;
		// 人数
		public TextView num;
	}

	/**
	 * 二级下拉列表的适配器
	 * 
	 * @author Administrator
	 * 
	 */
	class Mybase extends BaseExpandableListAdapter {
		@Override
		public int getGroupCount() {
			return Contants.CITYS.length;
		}

		@Override
		public int getChildrenCount(int groupPosition) {
			return 1;
		}

		/**
		 * 父级
		 */
		@Override
		public View getGroupView(int groupPosition, boolean isExpanded,
				View convertView, ViewGroup parent) {
			MyHoldView hold = null;
			if (convertView == null) {
				hold = new MyHoldView();
				convertView = getLayoutInflater().inflate(
						R.layout.activity_qingxin_group, null);
				// 城市名称
				hold.city = (TextView) convertView.findViewById(R.id.city);
				// 温度
				hold.wealth = (TextView) convertView.findViewById(R.id.wealth);
				// pm
				hold.pm = (TextView) convertView.findViewById(R.id.pm);
				// 拥堵数
				hold.yd = (TextView) convertView.findViewById(R.id.yd);
				hold.parentImageViw = (ImageView) convertView
						.findViewById(R.id.parentImageViw);
				hold.showLine = (TextView) convertView
						.findViewById(R.id.showLine);
				convertView.setTag(hold);

			} else {
				hold = (MyHoldView) convertView.getTag();
			}

			hold.city.setText(Contants.CITYS[groupPosition]);
			hold.wealth.setText(Contants.WD[groupPosition]);
			hold.pm.setText(Contants.PMS[groupPosition] + "");
			hold.yd.setText(Contants.YDS[groupPosition] + "");
			if (isExpanded) {
				hold.parentImageViw.setBackgroundResource(R.drawable.up_icon);
				hold.showLine.setVisibility(8);
			} else {
				hold.parentImageViw.setBackgroundResource(R.drawable.down_icon);
				hold.showLine.setVisibility(0);
			}
			return convertView;
		}

		/**
		 * 子级
		 */
		@Override
		public View getChildView(int groupPosition, int childPosition,
				boolean isLastChild, View convertView, ViewGroup parent) {

			View layout = getLayoutInflater().inflate(
					R.layout.activity_qingxin_goupitem, null);
			MyGridView gridView1 = (MyGridView) layout
					.findViewById(R.id.gridView1);
			gridView1.setAdapter(itms[groupPosition]);
			/**
			 * 子级点击事件
			 */
			// gridView1.setOnItemClickListener(new OnItemClickListener() {
			//
			// @Override
			// public void onItemClick(AdapterView<?> arg0, View arg1,
			// int childPosition, long arg3) {
			// Intent intent = new Intent(
			// Provincial_ClearIndexActivity.this,
			// Map_ScenicSpotActivity.class);
			// intent.putExtra("groupPosition", itemadapter.groposition);
			// intent.putExtra("childPosition", childPosition);
			// // startActivity(intent);
			// // overridePendingTransition(R.animator.in_from_right,
			// // R.animator.out_to_left);
			// }
			// });

			return layout;
		}

		@Override
		public Object getGroup(int groupPosition) {
			return null;
		}

		@Override
		public Object getChild(int groupPosition, int childPosition) {
			return null;
		}

		@Override
		public boolean isChildSelectable(int groupPosition, int childPosition) {
			return true;
		}

		@Override
		public boolean hasStableIds() {
			return true;
		}

		@Override
		public long getGroupId(int groupPosition) {
			return groupPosition;
		}

		@Override
		public long getChildId(int groupPosition, int childPosition) {
			return childPosition;
		}
	}

	/**
	 * 子级的适配器
	 * 
	 * @author Administrator
	 * 
	 */
	class ItemAdapter extends BaseAdapter {
		public int groposition;

		public ItemAdapter() {
		}

		public ItemAdapter(int groposition) {
			this.groposition = groposition;
		}

		@Override
		public int getCount() {
			return 6;
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
			MyHoldViewchild holdViewchild = null;
			if (convertView == null) {
				holdViewchild = new MyHoldViewchild();
				convertView = getLayoutInflater().inflate(
						R.layout.activity_qingxin_children, null);
				// 图标
				holdViewchild.imageView1 = (ImageView) convertView
						.findViewById(R.id.imageView1);
				// 名称
				holdViewchild.name = (TextView) convertView
						.findViewById(R.id.name);
				// 清新的文本
				holdViewchild.tv_qingxin = (TextView) convertView
						.findViewById(R.id.tv_qingxin);
				// 中间那个|
				holdViewchild.gg = (TextView) convertView.findViewById(R.id.gg);
				// 拥挤的文本
				holdViewchild.tv_yongj = (TextView) convertView
						.findViewById(R.id.tv_yongj);
				// 人数
				holdViewchild.num = (TextView) convertView
						.findViewById(R.id.num);
				convertView.setTag(holdViewchild);
			} else {
				holdViewchild = (MyHoldViewchild) convertView.getTag();
			}
			holdViewchild.imageView1
					.setBackgroundResource(Contants.JQ_IMAGES[groposition][position]);
			String names = Contants.CITYSS[groposition][position];
			if (names.length() > 4) {
				names = names.substring(0, 4);
			}
			holdViewchild.name.setText(names);
			holdViewchild.tv_yongj
					.setText(Contants.QINGXINS[groposition][position]);
			int color = getResources().getColor(R.color.titlebar_bg);
			if ("舒适".equals(Contants.QINGXINS[groposition][position])) {
				holdViewchild.tv_yongj.setTextColor(color);
			}
			holdViewchild.num.setText(Contants.NUMS[groposition][position]);
			if (position == 5) {
				holdViewchild.tv_qingxin.setVisibility(8);
				holdViewchild.gg.setVisibility(8);
			} else {
				holdViewchild.tv_qingxin.setVisibility(0);
				holdViewchild.gg.setVisibility(0);
			}
			return convertView;
		}
	}

	/**
	 * 父级点击伸展事件
	 */
	@Override
	public void onGroupExpand(int groupPosition) {
		for (int i = 0, count = mExpandableListView.getExpandableListAdapter()
				.getGroupCount(); i < count; i++) {
			if (groupPosition != i) {// 关闭其他分组
				mExpandableListView.collapseGroup(i);
			}
		}
	}

	public void bottoms(View v) {
		Intent intent = null;
		boolean acitivity_num1 = getIntent().getBooleanExtra("acitivity_num1",
				false);
		boolean acitivity_num3 = getIntent().getBooleanExtra("acitivity_num3",
				false);
		boolean acitivity_num4 = getIntent().getBooleanExtra("acitivity_num4",
				false);
		switch (v.getId()) {
		case R.id.bottombnt_1:
			intent = new Intent(Provincial_ClearIndexActivity.this,
					Provincial_MapActivity.class);
			break;
		case R.id.bottombnt_3:
			intent = new Intent(Provincial_ClearIndexActivity.this,
					Provincial_TicketActivity.class);
			break;
		case R.id.bottombnt_4:
			intent = new Intent(Provincial_ClearIndexActivity.this,
					Provincial_SettingActivity.class);
			break;
		}
		if (intent != null) {
			if (!(acitivity_num1 || acitivity_num3)) {
				if (acitivity_num1) {
					intent.putExtra("acitivity_num1", acitivity_num1);
				}
				if (acitivity_num3) {
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
