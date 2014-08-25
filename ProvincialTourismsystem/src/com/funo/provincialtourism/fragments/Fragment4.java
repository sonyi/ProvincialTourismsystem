package com.funo.provincialtourism.fragments;

import com.baidu.navisdk.BaiduNaviManager;
import com.baidu.navisdk.BNaviEngineManager.NaviEngineInitListener;
import com.baidu.navisdk.BaiduNaviManager.OnStartNavigationListener;
import com.baidu.navisdk.comapi.routeplan.RoutePlanParams.NE_RoutePlan_Mode;
import com.baidu.navisdk.util.verify.BNKeyVerifyListener;
import com.funo.antennatestsystem.R;
import com.funo.provincialtourism.Map_BNavigatorActivity;
import com.funo.provincialtourism.Map_ScenicLocationActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Fragment4 extends Fragment implements OnItemClickListener {
	private int images[] = { R.drawable.neraby_icon01,
			R.drawable.neraby_icon01, R.drawable.neraby_icon02,
			R.drawable.neraby_icon02, R.drawable.neraby_icon03,
			R.drawable.neraby_icon04, R.drawable.neraby_icon05,
			R.drawable.neraby_icon06, R.drawable.neraby_icon06,
			R.drawable.neraby_icon06 };
	private String titles[] = { "零食、咖啡", "零食、咖啡", "租车", "租车", "导游、景点导览",
			"住宿、宾馆", "特产", "娱乐、大自然", "娱乐、大自然", "娱乐、大自然" };
	private String titlenames[] = { "1.赵小姐的店", "2.哈利波特奶茶", "1.金龙旅游客车租赁",
			"2.地中海自行车租赁", "1.自由行旅游组织", "1.如家酒店", "1.厦门特产专门", "1.海洋馆", "2.动物园",
			"3.植物园" };
	private String address[] = { "鼓浪屿49号", "鼓浪屿49号", "环岛南路102号", "环岛南路32号",
			"金尚路100号", "吕岭路23号", "中山路9号", "鼓浪屿11号", "鼓浪屿14号", "鼓浪屿34号" };
	private android.widget.Gallery.LayoutParams mParam;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		
		
		View layout = inflater.inflate(R.layout.fragment4, null);
		// int height = Fragment3.dip2Px(acitvity, 100);
		// mParam = new Gallery.LayoutParams(LayoutParams.WRAP_CONTENT, height);
		ListView listView1 = (ListView) layout.findViewById(R.id.listView1);
		listView1.setAdapter(new ZhoubianAdapter());
		listView1.setOnItemClickListener(this);
		return layout;
	}
	class ZhoubianAdapter extends BaseAdapter {

		@Override
		public int getCount() {

			return titles.length;
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return 0;
		}

		@SuppressWarnings("deprecation")
		@Override
		public View getView(int position, View arg1, ViewGroup arg2) {
			View layout = getActivity().getLayoutInflater().inflate(
					R.layout.zhoubian_listitem, null);
			TextView titlename = (TextView) layout.findViewById(R.id.titlename);
			TextView title = (TextView) layout.findViewById(R.id.title);
			TextView addess = (TextView) layout.findViewById(R.id.addess);
			ImageView image = (ImageView) layout.findViewById(R.id.images);
			Button item_go = (Button) layout.findViewById(R.id.item_go);
			Button bnt_gomap = (Button) layout.findViewById(R.id.bnt_gomap);
			Button bnt_gophont = (Button) layout.findViewById(R.id.bnt_gophont);
			titlename.setText(titlenames[position] + "");
			title.setText(titles[position] + "");
			addess.setText(address[position] + "");
			image.setBackgroundResource(images[position]);
			if (position == 1 || position == 3 || position == 8
					|| position == 9) {
				image.setVisibility(8);
			} else {
				image.setVisibility(0);
			}
			item_go.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					Log.e("onClick", "item_go");
					item_go();
				}
			});
			bnt_gophont.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					Log.e("onClick", "bnt_gophont");
//					item_go();
					//用intent启动拨打电话  
					String number="18650139647";
	                Intent intent = new Intent(Intent.ACTION_CALL,Uri.parse("tel:"+number));  
	                getActivity().startActivity(intent);
				}
			});
			bnt_gomap.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					Log.e("onClick", "bnt_gomap");
					// item_go();
					if (Map_ScenicLocationActivity.mIsEngineInitSuccess) {
						launchNavigator();
					}

				}
			});
			// LinearLayout seconds_item = (LinearLayout)
			// layout.findViewById(R.id.seconds_item);
			// if(seconds_item.getChildCount()>0)
			// {
			// seconds_item.removeAllViewsInLayout();;
			// }
			// for(int i=1;i<titlenames[position].length;i++)
			// {
			// RelativeLayout itemchilren = (RelativeLayout)
			// acitvity.getLayoutInflater().inflate(R.layout.zhoubian_itemchilren,
			// null);
			// TextView titlename1 = (TextView)
			// itemchilren.findViewById(R.id.titlename1);
			// TextView title1 = (TextView)
			// itemchilren.findViewById(R.id.title1);
			// TextView addess1 = (TextView)
			// itemchilren.findViewById(R.id.addess1);
			// titlename1.setText(titlenames[position][i]+"");
			// title1.setText(titles[position]+"");
			// addess1.setText(address[position]+"");
			//
			// seconds_item.addView(itemchilren, mParam);
			// }

			return layout;
		}

	}

	/**
	 * 启动GPS导航. 前置条件：导航引擎初始化成功
	 */
	private void launchNavigator() {
		// 这里给出一个起终点示例，实际应用中可以通过POI检索、外部POI来源等方式获取起终点坐标
		BaiduNaviManager.getInstance().launchNavigator(getActivity(),
				24.431835, 118.129551, "曾厝垵", 24.443974, 118.100077, "厦门大学",
				NE_RoutePlan_Mode.ROUTE_PLAN_MOD_MIN_TIME, // 算路方式
				true, // 真实导航
				BaiduNaviManager.STRATEGY_FORCE_ONLINE_PRIORITY, // 在离线策略
				new OnStartNavigationListener() { // 跳转监听

					@Override
					public void onJumpToNavigator(Bundle configParams) {
						Log.e("","goto");
						Intent intent = new Intent(getActivity(),
								Map_BNavigatorActivity.class);
						intent.putExtras(configParams);
						getActivity().startActivity(intent);
					}

					@Override
					public void onJumpToDownloader() {
					}
				});
	}

	public void item_go() {
		Fragment fragment = new Fragment5();
		FragmentTransaction ft = getActivity().getSupportFragmentManager()
				.beginTransaction();
		ft.setCustomAnimations(R.animator.in_from_right, R.animator.out_to_left);
		ft.replace(R.id.fragment_content, fragment);
		ft.commit();
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

		Log.e("tt", "onItemClick");
	}
}
