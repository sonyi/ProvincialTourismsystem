package com.funo.provincialtourism;

import java.text.DecimalFormat;
import java.util.ArrayList;

import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.MKGeneralListener;
import com.baidu.mapapi.map.MKEvent;
import com.baidu.navi.location.BDLocation;
import com.baidu.navi.location.BDLocationListener;
import com.baidu.navi.location.LocationClient;
import com.baidu.navi.location.LocationClientOption;
import com.baidu.navisdk.BaiduNaviManager;
import com.baidu.navisdk.BNaviEngineManager.NaviEngineInitListener;
import com.baidu.navisdk.util.verify.BNKeyVerifyListener;
import com.funo.antennatestsystem.R;

import android.app.Activity;
import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.widget.Toast;

public class ProvincialMyApplicition extends Application {
	public boolean m_bKeyRight = true;
	BMapManager mBMapManager = null;
	private ArrayList<Activity> ActivityList = new ArrayList<Activity>();
	private static ProvincialMyApplicition mInstance = null;
	public LocationClient mLocationClient;
	public MyLocationListener mMyLocationListener;
	public double mLatitude;
	public double mLongitude;
	@Override
	public void onCreate() {
		super.onCreate();
		mInstance = this;
		initEngineManager(this);
		mLocationClient = new LocationClient(this);
		mMyLocationListener = new MyLocationListener();
		initLocation();
	}
	/**
	 * 初始化定位
	 */
	private void initLocation() {
		mLocationClient.registerLocationListener(mMyLocationListener);
		LocationClientOption option = new LocationClientOption();
		option.setProdName("通过GPS定位我当前的位置");
		option.setOpenGps(true);// 打开gps
		option.setCoorType("bd09ll"); // 设置坐标类型
		option.setScanSpan(1000);
		option.setAddrType("all");
		option.setPoiNumber(5);
		option.setPoiDistance(1000); // poi查询距离
		option.setPoiExtraInfo(true); // 是否需要POI的电话和地址等详细信息
		option.setPriority(LocationClientOption.GpsFirst);
		mLocationClient.setLocOption(option);
	}
	public void initEngineManager(Context context) {
		if (mBMapManager == null) {
			mBMapManager = new BMapManager(context);
		}

		if (!mBMapManager.init(new MyGeneralListener())) {
			Toast.makeText(
					ProvincialMyApplicition.getInstance()
							.getApplicationContext(), "BMapManager  初始化错误!",
					Toast.LENGTH_LONG).show();
		}
	}

	/**
	 * 实现实位回调监听
	 */
	public class MyLocationListener implements BDLocationListener {

		@Override
		public void onReceiveLocation(BDLocation location) {
			if (location == null) {
				return;
			}

			mLatitude = location.getLatitude();
			mLongitude = location.getLongitude();

		}

		@Override
		public void onReceivePoi(BDLocation location) {
			// TODO Auto-generated method stub

		}

	}

	// 常用事件监听，用来处理通常的网络错误，授权验证错误等
	static class MyGeneralListener implements MKGeneralListener {

		@Override
		public void onGetNetworkState(int iError) {
			if (iError == MKEvent.ERROR_NETWORK_CONNECT) {
				Toast.makeText(
						ProvincialMyApplicition.getInstance()
								.getApplicationContext(), "您的网络出错啦！",
						Toast.LENGTH_LONG).show();
			} else if (iError == MKEvent.ERROR_NETWORK_DATA) {
				Toast.makeText(
						ProvincialMyApplicition.getInstance()
								.getApplicationContext(), "输入正确的检索条件！",
						Toast.LENGTH_LONG).show();
			}
			// ...
		}

		@Override
		public void onGetPermissionState(int iError) {
			// 非零值表示key验证未通过
			if (iError != 0) {
				// 授权Key错误：
				Toast.makeText(
						ProvincialMyApplicition.getInstance()
								.getApplicationContext(),
						"请在 ProvincialMyApplicition.java文件输入正确的授权Key,并检查您的网络连接是否正常！error: "
								+ iError, Toast.LENGTH_LONG).show();
				ProvincialMyApplicition.getInstance().m_bKeyRight = false;
			} else {
				ProvincialMyApplicition.getInstance().m_bKeyRight = true;
				Toast.makeText(
						ProvincialMyApplicition.getInstance()
								.getApplicationContext(), "key认证成功",
						Toast.LENGTH_LONG).show();
			}
		}
	}

	public static ProvincialMyApplicition getInstance() {
		return mInstance;
	}

	public void addActivity(Activity activity) {
		ActivityList.add(activity);
	}

	public void removeActivity(Activity activity) {
		ActivityList.remove(activity);
	}

	public void exit() {
		for (int i = 0; i < ActivityList.size(); i++) {
			ActivityList.get(i).finish();
		}

	}

}