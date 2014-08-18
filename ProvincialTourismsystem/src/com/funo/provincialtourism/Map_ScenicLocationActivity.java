package com.funo.provincialtourism;

import java.util.ArrayList;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

import com.baidu.mapapi.map.MapController;
import com.baidu.mapapi.map.MapView;
import com.baidu.platform.comapi.basestruct.GeoPoint;
import com.funo.antennatestsystem.R;
import com.funo.provincialtourism.tool.Contants;
import com.funo.provincialtourism.view.MyDialogBuilder;

public class Map_ScenicLocationActivity extends BaseActivity {

	private MapController mMapController;
	private MapView mMapView;
	private View rliul;
	private Dialog shareDialog;
	private Button[] mapbnts;
	private ProvincialMyApplicition app;
	private View zijingqu;
	private String mCityname;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map_location);
		app = (ProvincialMyApplicition) this.getApplication();
		app.addActivity(this);
		mSp = getSharedPreferences("saveBntnum", 0);
		int ii = getIntent().getIntExtra("bntNum", 1);
		Editor edit = mSp.edit();
		edit.putInt("bntNum", ii);
		edit.commit();
		initUI();
		initTitle();
		setBottombnt(getBottombnt(), 0, getBottomtv());
	}

	/**
	 * 键盘监听事件
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (event.getKeyCode() == event.KEYCODE_BACK) {
			// Intent intent = new Intent(Map_ScenicLocationActivity.this,
			// Map_CityLocationActivity.class);
			// intent.putExtra("cityname",
			// getIntent().getStringExtra("cityname"));
			// intent.putExtra("latitude",
			// getIntent().getDoubleExtra("latitude", 0.0));
			// intent.putExtra("longitude",
			// getIntent().getDoubleExtra("longitude", 0.0));
			// startActivity(intent);
			// overridePendingTransition(R.animator.in_from_right,
			// R.animator.out_to_left);
			// finish();

			Intent intent = new Intent(Map_ScenicLocationActivity.this,
					Map_ScenicSpotActivity.class);
			intent.putExtra("cityname_item",
					getIntent().getStringExtra("cityname_item"));
			intent.putExtra("latitude_item",
					getIntent().getDoubleExtra("latitude_item", 0.0));
			intent.putExtra("longitude_item",
					getIntent().getDoubleExtra("longitude_item", 0.0));
			intent.putExtra("groupPosition",
					getIntent().getIntExtra("groupPosition", 1));
			intent.putExtra("childPosition",
					getIntent().getIntExtra("childPosition", 0));
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

	/**
	 * 初始化标题
	 */
	private void initTitle() {
		initTitlebase();
		bnt_back.setVisibility(0);
		bnt_back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// Intent intent = new Intent(Map_ScenicLocationActivity.this,
				// Map_CityLocationActivity.class);
				// intent.putExtra("cityname",
				// getIntent().getStringExtra("cityname"));
				// intent.putExtra("latitude",
				// getIntent().getDoubleExtra("latitude", 0.0));
				// intent.putExtra("longitude",
				// getIntent().getDoubleExtra("longitude", 0.0));
				// startActivity(intent);
				// overridePendingTransition(R.animator.in_from_right,
				// R.animator.out_to_left);
				// finish();

				Intent intent = new Intent(Map_ScenicLocationActivity.this,
						Map_ScenicSpotActivity.class);
				intent.putExtra("cityname_item",
						getIntent().getStringExtra("cityname_item"));
				intent.putExtra("latitude_item",
						getIntent().getDoubleExtra("latitude_item", 0.0));
				intent.putExtra("longitude_item",
						getIntent().getDoubleExtra("longitude_item", 0.0));
				intent.putExtra("groupPosition",
						getIntent().getIntExtra("groupPosition", 1));
				intent.putExtra("childPosition",
						getIntent().getIntExtra("childPosition", 0));
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
		Intent intent = getIntent();
		mCityname = intent.getStringExtra("cityname_item");
		title.setText(mCityname);
		bnt_meun.setVisibility(8);
	}

	/**
	 * 初始化UI
	 */
	private void initUI() {
		mapbnts = getMapbnt();
		setBntstyl(mapbnts, mSp.getInt("bntNum", 1));
		rliul = (View) findViewById(R.id.rliul);
		mMapView = (MapView) findViewById(R.id.bmapsView);
		mMapController = mMapView.getController();
		mMapController.setZoom(16);
		mMapController.enableClick(true);
		Intent intent = getIntent();
		double latitude = intent.getDoubleExtra("latitude_item", 0.0);
		double longitude = intent.getDoubleExtra("longitude_item", 0.0);
		mMapController.animateTo(new GeoPoint((int) (latitude * 1e6),
				(int) (longitude * 1e6)));
		mMapView.refresh();
		View sharedialog = getLayoutInflater().inflate(R.layout.sharedialog,
				null);
		View dialog_shoucang = getLayoutInflater().inflate(
				R.layout.dialog_shoucang, null);
		TextView jingq = (TextView) dialog_shoucang
				.findViewById(R.id.textView2);
		jingq.setText(getIntent().getStringExtra("cityname"));
		shareDialog = new Dialog(this,R.style.Dialog);
		shareDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		shareDialog.setContentView(sharedialog);
		
		shoucDialog = new Dialog(this,R.style.Dialog);
		shoucDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		shoucDialog.setContentView(dialog_shoucang);

		zijingqu = findViewById(R.id.zijingqu);
		GridView lists_jingqu = (GridView) findViewById(R.id.lists_jingqu);
		lists_jingqu.setAdapter(new JingquAdapter());
		qingxin_views = findViewById(R.id.qingxin_views);
		setView(mSp.getInt("bntNum", 1));
	}

	private void setView(int indext) {
		switch (indext) {
		case 1:
			qingxin_views.setVisibility(0);
			zijingqu.setVisibility(8);
			rliul.setVisibility(8);
			break;
		case 2:
			mMapView.setVisibility(0);
			zijingqu.setVisibility(8);
			rliul.setVisibility(8);
			qingxin_views.setVisibility(8);
			break;
		case 3:
			shoucDialog.show();
			break;

		case 4:
			showShare();
			break;
		case 5:
			zijingqu.setVisibility(0);
			rliul.setVisibility(8);
			qingxin_views.setVisibility(8);
			break;
		case 6:
			break;
		case 7:
			rliul.setVisibility(0);
			zijingqu.setVisibility(8);
			qingxin_views.setVisibility(8);
			break;
		}
	}

	private int[] images = { R.drawable.yuding_0201, R.drawable.yuding_0202,
			R.drawable.yuding_0203, R.drawable.yuding_0204,
			R.drawable.yuding_0205, R.drawable.yuding_0206,
			R.drawable.yuding_0301, R.drawable.yuding_0302,
			R.drawable.yuding_0303, R.drawable.yuding_0304,
			R.drawable.yuding_0105 };
	private String date[][] = { { "厦门鼓浪屿", "位于日光岩莲花庵前的巨石上有概述题字‘岩石’" },
			{ "厦门鼓浪屿", "位于日光岩莲花庵前的巨石上有概述题字‘岩石’" }, { "日光岩", "登日光岩俯瞰鼓浪屿" },
			{ "万国建筑", "这里是鼓浪屿中西文化交流的精萃景观。中国传统建筑，如宗教建筑..." },
			{ "小店", "逛小店感受小清新" }, { "钢琴博物馆", "在音乐之岛聆听琴声飘飘" },
			{ "厦门海底世界", "融生态保护，海洋知识、海洋水产、科教，观光于一体的大型海洋水..." },
			{ "皓月园", "无论是球星、政客，还是影视巨星，你都能在这里发现，馆中塑像都..." },
			{ "菽庄花园", "曾为私人别墅的海滨花园，远眺可见海浪、园中可见秀丽园林。" },
			{ "郑成功纪念馆", "郑成功纪念馆是1962年为纪念郑成功收复台湾300周年而建立..." },
			{ "钢琴码头", "如同一架三角钢琴的钢琴码头，是钢琴之岛迎接游人的起点，踏上码..." } };
	private Dialog shoucDialog;
	private View qingxin_views;
	private SharedPreferences mSp;

	class JingquAdapter extends BaseAdapter {

		@Override
		public int getCount() {

			return images.length;
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

		@Override
		public View getView(int position, View contentView, ViewGroup arg2) {

			View layout = getLayoutInflater().inflate(R.layout.scenerylistleft,
					null);
			TextView title = (TextView) layout.findViewById(R.id.left_title);
			TextView info = (TextView) layout.findViewById(R.id.left_info);
			View viewleft = layout.findViewById(R.id.viewleft);
			View viewright = layout.findViewById(R.id.viewright);
			ImageView image = (ImageView) layout
					.findViewById(R.id.sceneryleft_image);
			ImageView line_right = (ImageView) layout
					.findViewById(R.id.line_right);
			ImageView line_left = (ImageView) layout
					.findViewById(R.id.line_left);
			TextView tops = (TextView) layout.findViewById(R.id.tops);
			TextView bottoms = (TextView) layout.findViewById(R.id.bottoms);
			if (position % 2 == 0) {
				line_left.setVisibility(0);
				line_right.setVisibility(8);
				viewleft.setVisibility(8);
				viewright.setVisibility(0);
				tops.setVisibility(8);
				bottoms.setVisibility(0);
			} else {
				line_left.setVisibility(8);
				viewleft.setVisibility(0);
				viewright.setVisibility(8);
				line_right.setVisibility(0);
				tops.setVisibility(0);
				bottoms.setVisibility(8);
			}

			title.setText(date[position][0]);
			String d = date[position][1];
			if (d.length() > 10) {
				d = d.substring(0, 9) + "..";
			}
			info.setText(d);
			image.setBackgroundResource(images[position]);
			return layout;
		}
	}

	@Override
	protected void onPause() {
		/**
		 * MapView的生命周期与Activity同步，当activity挂起时需调用MapView.onPause()
		 */
		if (mMapView != null)
			mMapView.onPause();
		super.onPause();
	}

	@Override
	protected void onResume() {
		/**
		 * MapView的生命周期与Activity同步，当activity恢复时需调用MapView.onResume()
		 */
		if (mMapView != null)
			mMapView.onResume();
		super.onResume();
	}

	@Override
	protected void onDestroy() {
		app.removeActivity(this);
		if (mMapView != null) {
			mMapView.destroy();
		}
		super.onDestroy();
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		if (mMapView != null)
			mMapView.onSaveInstanceState(outState);

	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		if (mMapView != null)
			mMapView.onRestoreInstanceState(savedInstanceState);
	}

	private void setBntstyl(Button[] mapbnts, int indext) {
		ArrayList<Boolean> isnewcheck = new ArrayList<Boolean>();
		isnewcheck.add(false);
		isnewcheck.add(false);
		isnewcheck.add(false);
		isnewcheck.add(false);
		isnewcheck.add(false);
		isnewcheck.add(false);
		isnewcheck.add(false);
		isnewcheck.add(false);
		isnewcheck.set(indext, true);
		for (int i = 0; i < mapbnts.length; i++) {
			mapbnts[i].setSelected(isnewcheck.get(i));
		}
	}

	public void clickbnt(View v) {
		int indext = 0;
		switch (v.getId()) {
		case R.id.mapbnt_1:
			indext = 0;
			Intent intent = new Intent(Map_ScenicLocationActivity.this,
					Map_ScenicSpotActivity.class);
			intent.putExtra("cityname_item",
					getIntent().getStringExtra("cityname_item"));
			intent.putExtra("latitude_item",
					getIntent().getDoubleExtra("latitude_item", 0.0));
			intent.putExtra("longitude_item",
					getIntent().getDoubleExtra("longitude_item", 0.0));
			intent.putExtra("groupPosition",
					getIntent().getIntExtra("groupPosition", 1));
			intent.putExtra("childPosition",
					getIntent().getIntExtra("childPosition", 0));
			intent.putExtra("cityname", getIntent().getStringExtra("cityname"));
			intent.putExtra("latitude",
					getIntent().getDoubleExtra("latitude", 0.0));
			intent.putExtra("longitude",
					getIntent().getDoubleExtra("longitude", 0.0));
			startActivity(intent);
			overridePendingTransition(R.animator.in_from_right,
					R.animator.out_to_left);
			finish();
			break;
		case R.id.mapbnt_2:
			indext = 1;
			qingxin_views.setVisibility(0);
			zijingqu.setVisibility(8);
			rliul.setVisibility(8);
			break;
		case R.id.mapbnt_3:
			indext = 2;
			mMapView.setVisibility(0);
			zijingqu.setVisibility(8);
			rliul.setVisibility(8);
			qingxin_views.setVisibility(8);
			break;
		case R.id.mapbnt_4:
			indext = mSp.getInt("bntNum", 2);
			shoucDialog.show();

			break;

		case R.id.mapbnt_5:
			indext = 4;
			showShare();
			break;
		case R.id.mapbnt_6:
			indext = 5;
			zijingqu.setVisibility(0);
			rliul.setVisibility(8);
			qingxin_views.setVisibility(8);
			break;
		case R.id.mapbnt_7:
			indext = 6;
			break;
		case R.id.mapbnt_8:
			indext = 7;
			rliul.setVisibility(0);
			zijingqu.setVisibility(8);
			qingxin_views.setVisibility(8);
			break;
		}
		Editor edit = mSp.edit();
		edit.putInt("bntNum", indext);
		edit.commit();
		setBntstyl(mapbnts, indext);
	}

	public void dialog_click(View v) {
		shoucDialog.dismiss();
	}

	private void showShare() {
		shareDialog.show();

		// ShareSDK.initSDK(this);
		// OnekeyShare oks = new OnekeyShare();
		// // 关闭sso授权
		// oks.disableSSOWhenAuthorize();
		//
		// // 分享时Notification的图标和文字
		// oks.setNotification(R.drawable.ic_launcher,
		// getString(R.string.app_name));
		// // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
		// oks.setTitle(getString(R.string.share));
		// // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
		// oks.setTitleUrl("http://sharesdk.cn");
		// // text是分享文本，所有平台都需要这个字段
		// oks.setText("我是分享文本");
		// // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
		// oks.setImagePath("/sdcard/test.jpg");
		// // url仅在微信（包括好友和朋友圈）中使用
		// oks.setUrl("http://sharesdk.cn");
		// // comment是我对这条分享的评论，仅在人人网和QQ空间使用
		// oks.setComment("我是测试评论文本");
		// // site是分享此内容的网站名称，仅在QQ空间使用
		// oks.setSite(getString(R.string.app_name));
		// // siteUrl是分享此内容的网站地址，仅在QQ空间使用
		// oks.setSiteUrl("http://sharesdk.cn");
		//
		// // 启动分享GUI
		// oks.show(this);
	}

	public void share(View v) {
		shareDialog.dismiss();
	}

	private Button[] getMapbnt() {
		Button mapbnt_1 = (Button) findViewById(R.id.mapbnt_1);
		Button mapbnt_2 = (Button) findViewById(R.id.mapbnt_2);
		Button mapbnt_3 = (Button) findViewById(R.id.mapbnt_3);
		Button mapbnt_4 = (Button) findViewById(R.id.mapbnt_4);
		Button mapbnt_5 = (Button) findViewById(R.id.mapbnt_5);
		Button mapbnt_6 = (Button) findViewById(R.id.mapbnt_6);
		Button mapbnt_7 = (Button) findViewById(R.id.mapbnt_7);
		Button mapbnt_8 = (Button) findViewById(R.id.mapbnt_8);
		Button mapbnts[] = { mapbnt_1, mapbnt_2, mapbnt_3, mapbnt_4, mapbnt_5,
				mapbnt_6, mapbnt_7, mapbnt_8 };
		return mapbnts;
	}

	public void bottoms(View v) {
		Intent intent = null;
		switch (v.getId()) {
		case R.id.bottombnt_2:
			intent = new Intent(Map_ScenicLocationActivity.this,
					Provincial_ClearIndexActivity.class);
			break;
		case R.id.bottombnt_3:
			intent = new Intent(Map_ScenicLocationActivity.this,
					Provincial_TicketActivity.class);
			break;
		case R.id.bottombnt_4:
			intent = new Intent(Map_ScenicLocationActivity.this,
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
