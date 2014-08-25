package com.funo.provincialtourism;

import java.util.ArrayList;

import android.app.Activity;
import android.app.Application;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

import com.baidu.mapapi.map.MapController;
import com.baidu.mapapi.map.MapView;
import com.baidu.navisdk.BaiduNaviManager;
import com.baidu.navisdk.BNaviEngineManager.NaviEngineInitListener;
import com.baidu.navisdk.BaiduNaviManager.OnStartNavigationListener;
import com.baidu.navisdk.comapi.routeplan.RoutePlanParams.NE_RoutePlan_Mode;
import com.baidu.navisdk.util.verify.BNKeyVerifyListener;
import com.baidu.platform.comapi.basestruct.GeoPoint;
import com.funo.antennatestsystem.R;
import com.funo.provincialtourism.fragments.Fragment1;
import com.funo.provincialtourism.fragments.Fragment2;
import com.funo.provincialtourism.fragments.Fragment3;
import com.funo.provincialtourism.fragments.Fragment4;

public class Map_ScenicLocationActivity extends BaseActivity {

	private MapController mMapController;
	private MapView mMapView;
	private Dialog shareDialog;
	private Button[] mapbnts;
	private ProvincialMyApplicition app;
	private String mCityname;
	private Dialog shoucDialog;
	private View fragment_content;
	private ArrayList<Boolean> mState = new ArrayList<Boolean>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map_location);
		app = (ProvincialMyApplicition) this.getApplication();
		app.addActivity(this);
		initUI();
		initTitle();
		setBottombnt(getBottombnt(), 0, getBottomtv());
		BaiduNaviManager.getInstance().initEngine(this, getSdcardDir(),
				mNaviEngineInitListener, "我的key", mKeyVerifyListener);
	}
	public static boolean mIsEngineInitSuccess = false;
	private NaviEngineInitListener mNaviEngineInitListener = new NaviEngineInitListener() {
		public void engineInitSuccess() {
			// 导航初始化是异步的，需要一小段时间，以这个标志来识别引擎是否初始化成功，为true时候才能发起导航
			mIsEngineInitSuccess = true;
		}

		public void engineInitStart() {
			
		}

		public void engineInitFail() {
		}
	};

	private String getSdcardDir() {
		if (Environment.getExternalStorageState().equalsIgnoreCase(
				Environment.MEDIA_MOUNTED)) {
			return Environment.getExternalStorageDirectory().toString();
		}
		return null;
	}

	private BNKeyVerifyListener mKeyVerifyListener = new BNKeyVerifyListener() {

		@Override
		public void onVerifySucc() {
			// TODO Auto-generated method stub
//			 Toast.makeText(getActivity(), "key校验成功", Toast.LENGTH_LONG)
//			 .show();
		}

		@Override
		public void onVerifyFailed(int arg0, String arg1) {
			// TODO Auto-generated method stub
//			Toast.makeText(getActivity(), "key校验失败", Toast.LENGTH_LONG)
//					.show();
		}
	};
	Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			int w = msg.what;
			Boolean b = mState.get(w);
			// mState.set(w, !b);

		}
	};
	private Dialog pd;

	/**
	 * 键盘监听事件
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (event.getKeyCode() == event.KEYCODE_BACK) {

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

	private void showCurrentContent(int item) {
		if (!mapbnts[item].isSelected()) {
			fragment_content.setVisibility(0);
			Fragment fragment = null;
			switch (item) {
			case 1:
				fragment = new Fragment1();
				break;
			case 5:
				fragment = new Fragment3();
				break;
			case 6:
				fragment = new Fragment4();
				break;
			case 7:
				fragment = new Fragment2();
				break;
			}
			if (fragment != null) {
				FragmentTransaction ft = getSupportFragmentManager()
						.beginTransaction();
				ft.setCustomAnimations(R.animator.in_from_right,
						R.animator.out_to_left);
				ft.replace(R.id.fragment_content, fragment);
				ft.commit();
			}
		}
	}

	/**
	 * 启动GPS导航. 前置条件：导航引擎初始化成功
	 */
	public static void launchNavigator(final Activity activity) {
		// 这里给出一个起终点示例，实际应用中可以通过POI检索、外部POI来源等方式获取起终点坐标
		ProvincialMyApplicition app = (ProvincialMyApplicition) activity.getApplication();
		double latitde=app.mLatitude;
		double longitude=app.mLongitude;
		BaiduNaviManager.getInstance().launchNavigator(activity, latitde,
				longitude, "目前位置", 24.443974, 118.100077, "厦门大学",
				NE_RoutePlan_Mode.ROUTE_PLAN_MOD_MIN_TIME, // 算路方式
				true, // 真实导航
				BaiduNaviManager.STRATEGY_FORCE_ONLINE_PRIORITY, // 在离线策略
				new OnStartNavigationListener() { // 跳转监听

					@Override
					public void onJumpToNavigator(Bundle configParams) {
						Intent intent = new Intent(activity,
								Map_BNavigatorActivity.class);
						intent.putExtras(configParams);
						activity.startActivity(intent);
					}

					@Override
					public void onJumpToDownloader() {
					}
				});
	}

	/**
	 * 初始化UI
	 */
	private void initUI() {
		mState.clear();
		mState.add(false);
		mState.add(false);
		mState.add(false);
		mapbnts = getMapbnt();

		fragment_content = findViewById(R.id.fragment_content);
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
		shareDialog = new Dialog(this, R.style.Dialog);
		shareDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		shareDialog.setContentView(sharedialog);

		shoucDialog = new Dialog(this, R.style.Dialog);
		shoucDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		shoucDialog.setContentView(dialog_shoucang);
		int ii = getIntent().getIntExtra("bntNum", 1);
		showCurrentContent(ii);
		setBntstyl(mapbnts, ii);

		View dialog_prog = getLayoutInflater()
				.inflate(R.layout.show_prog, null);
		pd = new Dialog(this, R.style.Dialog);
		pd.requestWindowFeature(Window.FEATURE_NO_TITLE);
		pd.setContentView(dialog_prog);

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
		switch (v.getId()) {
		case R.id.mapbnt_1:
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
			showCurrentContent(1);
			setBntstyl(mapbnts, 1);
			break;
		case R.id.mapbnt_3:
			setBntstyl(mapbnts, 2);
			fragment_content.setVisibility(8);
			break;
		case R.id.mapbnt_4:
			shoucDialog.show();
			break;
		case R.id.mapbnt_5:
			showShare();
			break;
		case R.id.mapbnt_6:
			showCurrentContent(5);
			setBntstyl(mapbnts, 5);
			break;
		case R.id.mapbnt_7:
			showCurrentContent(6);
			setBntstyl(mapbnts, 6);
			break;
		case R.id.mapbnt_8:
			showCurrentContent(7);
			setBntstyl(mapbnts, 7);
			break;
		}
	}

	public void dialog_click(View v) {
		shoucDialog.dismiss();
	}

	private void showShare() {
		// shareDialog.show();
		ShareSDK.initSDK(this);
		OnekeyShare oks = new OnekeyShare();
		// 关闭sso授权
		oks.disableSSOWhenAuthorize();
		// 分享时Notification的图标和文字
		oks.setNotification(R.drawable.yuding_0101,
				getString(R.string.app_name));
		// title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
		oks.setTitle(getString(R.string.share));
		// titleUrl是标题的网络链接，仅在人人网和QQ空间使用
		oks.setTitleUrl("http://sharesdk.cn");
		// text是分享文本，所有平台都需要这个字段
		oks.setText("我是分享文本");
		// imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
		oks.setImagePath("/sdcard/test.jpg");
		// url仅在微信（包括好友和朋友圈）中使用
		oks.setUrl("http://sharesdk.cn");
		// comment是我对这条分享的评论，仅在人人网和QQ空间使用
		oks.setComment("我是测试评论文本");
		// site是分享此内容的网站名称，仅在QQ空间使用
		oks.setSite(getString(R.string.app_name));
		// siteUrl是分享此内容的网站地址，仅在QQ空间使用
		oks.setSiteUrl("http://sharesdk.cn");
		oks.setDialogMode();
		// 启动分享GUI
		oks.show(this);
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
