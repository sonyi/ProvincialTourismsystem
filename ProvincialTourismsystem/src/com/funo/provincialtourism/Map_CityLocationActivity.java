package com.funo.provincialtourism;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.ActionBar.OnNavigationListener;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.map.ItemizedOverlay;
import com.baidu.mapapi.map.MapController;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.OverlayItem;
import com.baidu.mapapi.map.PopupClickListener;
import com.baidu.mapapi.map.PopupOverlay;
import com.baidu.platform.comapi.basestruct.GeoPoint;
import com.funo.antennatestsystem.R;
import com.funo.provincialtourism.tool.BMapUtil;
import com.funo.provincialtourism.tool.Contants;

public class Map_CityLocationActivity extends BaseActivity implements
		OnItemClickListener {

	private MapView mMapView = null; // 地图View
	private MapController mMapController = null;
	private ProvincialMyApplicition app;
	private MyOverlay mOverlay;
	private boolean b;
	private boolean b1;
	private View map_bntvs;
	private double mLatitude;
	private double mLongitude;
	private String titles[] = { "福州市", "莆田市", "泉州市", "厦门市", "漳州市", "龙岩市",
			"三明市", "南平市", "宁德市" };
	List<Map<String, String>> cityMap = new ArrayList<Map<String, String>>();
	private ListView citylist;
	private SimpleAdapter simpleAdapter;
	private View city_s;
	private String mCityname;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		app = (ProvincialMyApplicition) this.getApplication();
		if (app.mBMapManager == null) {
			app.mBMapManager = new BMapManager(getApplicationContext());
			/**
			 * 如果BMapManager没有初始化则初始化BMapManager
			 */
			app.mBMapManager
					.init(new ProvincialMyApplicition.MyGeneralListener());
		}
		app.addActivity(this);
		app.mLocationClient.start();
		setContentView(R.layout.activity_location);
		initUI();
		initTitle();
		setBottombnt(getBottombnt(), 0, getBottomtv());
	}

	/**
	 * 初始化标题
	 */
	protected void initTitle() {
		initTitlebase();
		select.setVisibility(0);
		bnt_back.setVisibility(0);
		bnt_back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(Map_CityLocationActivity.this,
						Provincial_MapActivity.class));
				overridePendingTransition(R.animator.in_from_right,
						R.animator.out_to_left);
				finish();
			}
		});
		bnt_meun.setVisibility(0);
		title.setText(mCityname);
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
		title.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (b1) {
					city_s.setVisibility(8);
				} else {
					city_s.setVisibility(0);
				}
				b1 = !b1;
			}
		});
	}

	/**
	 * 菜单按钮事件
	 */
	public void mapViewchanges(View v) {
		b = false;
		map_bntvs.setVisibility(8);
		Intent intent = null;
		switch (v.getId()) {
		case R.id.bnt_mapview1:
			intent = new Intent(Map_CityLocationActivity.this,
					Map_NotyActivity.class);
			break;
		case R.id.bnt_mapview2:
			intent = new Intent(Map_CityLocationActivity.this,
					Map_CharacteristicActivity.class);
			break;
		case R.id.bnt_mapview3:
			intent = new Intent(Map_CityLocationActivity.this,
					Map_TravelingActivity.class);
			break;
		default:
			break;
		}
		if (intent != null) {
			intent.putExtra("cityname", mCityname);
			intent.putExtra("latitude", mLatitude);
			intent.putExtra("longitude", mLongitude);
			startActivity(intent);
			overridePendingTransition(R.animator.in_from_right,
					R.animator.out_to_left);
			finish();
		}
	}

	/**
	 * 初始化UI
	 */
	private void initUI() {

		map_bntvs = (View) findViewById(R.id.map_bntvs);
		// 地图初始化
		mMapView = (MapView) findViewById(R.id.bmapsView);
		mMapController = mMapView.getController();
		mMapController.setZoom(16);
		mMapController.enableClick(true);

		Intent intent = getIntent();
		mCityname = intent.getStringExtra("cityname");
		mLatitude = intent.getDoubleExtra("latitude", 0.0);
		mLongitude = intent.getDoubleExtra("longitude", 0.0);
		mMapController.animateTo(new GeoPoint((int) (mLatitude * 1e6),
				(int) (mLongitude * 1e6)));
		// 创建 弹出泡泡图层
		createPaopao();

		mMapView.refresh();
		city_s = findViewById(R.id.city_s);
		citylist = (ListView) findViewById(R.id.citylist);
		initData();
	}

	private List<Map<String, String>> addCity() {
		for (int i = 0; i < titles.length; i++) {
			Map<String, String> map = new HashMap<String, String>();
			// if (titles[i].equals(cityname)) {
			// continue;
			// }
			map.put("title", titles[i]);
			cityMap.add(map);
		}
		return cityMap;
	}

	private void initData() {
		// String mCityname = getIntent().getStringExtra("cityname");
		cityMap = addCity();
		simpleAdapter = new SimpleAdapter(this, cityMap, R.layout.cityitem,
				new String[] { "title" }, new int[] { R.id.textView1 });
		citylist.setAdapter(simpleAdapter);
		citylist.setOnItemClickListener(this);
	}

	/**
	 * 键盘监听事件
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (event.getKeyCode() == event.KEYCODE_BACK) {
			Intent intent = new Intent(Map_CityLocationActivity.this,
					Provincial_MapActivity.class);
			startActivity(intent);
			overridePendingTransition(R.animator.in_from_right,
					R.animator.out_to_left);
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

	/**
	 * 泡泡的类
	 * 
	 * @author Administrator
	 * 
	 */
	public class MyOverlay extends ItemizedOverlay {

		public MyOverlay(Drawable defaultMarker, MapView mapView) {
			super(defaultMarker, mapView);

		}

		@Override
		public boolean onTap(int index) {
			Intent intent = new Intent(Map_CityLocationActivity.this,
					Map_ScenicSpotActivity.class);
			intent.putExtra("cityname_item", Contants.CITYSS[1][index]);
			intent.putExtra("latitude_item", Contants.XIAMEN_JINGQU[index][1]);
			intent.putExtra("longitude_item", Contants.XIAMEN_JINGQU[index][0]);
			intent.putExtra("groupPosition", 1);
			intent.putExtra("childPosition", index);
			intent.putExtra("cityname", mCityname);
			intent.putExtra("latitude", mLatitude);
			intent.putExtra("longitude", mLongitude);

			startActivity(intent);
			overridePendingTransition(R.animator.in_from_right,
					R.animator.out_to_left);
			finish();
			return true;
		}

		@Override
		public boolean onTap(GeoPoint pt, MapView mMapView) {
			return false;
		}
	}

	/**
	 * 放大缩小按钮事件
	 * 
	 * @param v
	 */
	public void zoomMap(View v) {
		switch (v.getId()) {
		case R.id.zoomIn:
			mMapView.getController().zoomIn();
			break;
		case R.id.zoomOut:
			mMapView.getController().zoomOut();
			break;
		default:
			break;
		}

	}

	/**
	 * 创建弹出泡泡图层
	 */
	public void createPaopao() {
		mOverlay = new MyOverlay(getResources().getDrawable(
				R.drawable.position_a), mMapView);
		/**
		 * 准备overlay 数据
		 */
		for (int i = 0; i < Contants.XIAMEN_JINGQU.length; i++) {
			GeoPoint p = new GeoPoint(
					(int) (Contants.XIAMEN_JINGQU[i][1] * 1E6),
					(int) (Contants.XIAMEN_JINGQU[i][0] * 1E6));
			OverlayItem item = new OverlayItem(p, Contants.CITYSS[1][i], "");
			int imageId = 0;
			int makImage = 0;
			if (i == 0 || i == 1 || i == 5) {
				imageId = R.drawable.area_namebg_red;
				makImage = R.drawable.position_red;
			} else {
				imageId = R.drawable.area_namebg_green;
				makImage = R.drawable.position_green;
			}
			Drawable mDrawable = BMapUtil.LayoutToDrawable(this,
					item.getTitle(), makImage, imageId, (char) ('A' + i));
			item.setMarker(mDrawable);
			mOverlay.addItem(item);
		}
		/**
		 * 将overlay 添加至MapView中
		 */
		mMapView.getOverlays().add(mOverlay);
	}

	@Override
	protected void onPause() {
		/**
		 * MapView的生命周期与Activity同步，当activity挂起时需调用MapView.onPause()
		 */
		b = false;
		map_bntvs.setVisibility(8);
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
//		if (app.mMyLocationListener != null) {
//			app.mLocationClient.unRegisterLocationListener(app.mMyLocationListener);
//		}
		app.mLocationClient.stop();
		// 退出时销毁定位
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

	public void bottoms(View v) {
		Intent intent = null;
		switch (v.getId()) {
		case R.id.bottombnt_2:
			intent = new Intent(Map_CityLocationActivity.this,
					Provincial_ClearIndexActivity.class);
			break;
		case R.id.bottombnt_3:
			intent = new Intent(Map_CityLocationActivity.this,
					Provincial_TicketActivity.class);
			break;
		case R.id.bottombnt_4:
			intent = new Intent(Map_CityLocationActivity.this,
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
		mCityname = cityMap.get(arg2).get("title") + "";
		title.setText(mCityname);
		b1 = false;
		city_s.setVisibility(8);
		mLatitude = Contants.CITY_LOCATIONS[arg2][1];
		mLongitude = Contants.CITY_LOCATIONS[arg2][0];
		mMapController.animateTo(new GeoPoint((int) (mLatitude * 1e6),
				(int) (mLongitude * 1e6)));
		mMapView.refresh();
	}
}
