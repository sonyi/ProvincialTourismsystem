package com.funo.provincialtourism;

import java.util.ArrayList;

import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.MKGeneralListener;
import com.baidu.mapapi.map.MKEvent;
import com.funo.antennatestsystem.R;

import android.app.Activity;
import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class ProvincialMyApplicition extends Application {
	public boolean m_bKeyRight = true;
	BMapManager mBMapManager = null;
	private ArrayList<Activity> ActivityList = new ArrayList<Activity>();
	private static ProvincialMyApplicition mInstance = null;

	@Override
	public void onCreate() {
		super.onCreate();
		mInstance = this;
		initEngineManager(this);
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