package com.example.baidunavigationdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.baidu.lbsapi.auth.LBSAuthManagerListener;
import com.baidu.navisdk.BNaviEngineManager.NaviEngineInitListener;
import com.baidu.navisdk.BaiduNaviManager;
import com.baidu.navisdk.BaiduNaviManager.OnStartNavigationListener;
import com.baidu.navisdk.comapi.routeplan.RoutePlanParams.NE_RoutePlan_Mode;

public class MainActivity extends Activity {
	Button mBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mBtn = (Button) findViewById(R.id.btn);
		mBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (mIsEngineInitSuccess) {
					launchNavigator();
				}

			}
		});

		BaiduNaviManager.getInstance().initEngine(this, getSdcardDir(),
				mNaviEngineInitListener, mKeyVerifyListener);
	}

	private boolean mIsEngineInitSuccess = false;
	private NaviEngineInitListener mNaviEngineInitListener = new NaviEngineInitListener() {
		public void engineInitSuccess() {
			// ������ʼ�����첽�ģ���ҪһС��ʱ�䣬�������־��ʶ�������Ƿ��ʼ���ɹ���Ϊtrueʱ����ܷ��𵼺�
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

	private LBSAuthManagerListener mKeyVerifyListener = new LBSAuthManagerListener() {

		@Override
		public void onAuthResult(int status, String msg) {
			// TODO Auto-generated method stub
			String str = null;
            if (0 == status) {
                str = "keyУ��ɹ�!";
            } else {
                str = "keyУ��ʧ��, " + msg;
            }
            Toast.makeText(MainActivity.this, str,
                    Toast.LENGTH_LONG).show();
		}
	};


	/**
	 * ����GPS����. ǰ�����������������ʼ���ɹ�
	 */
	private void launchNavigator() {
		// �������һ�����յ�ʾ����ʵ��Ӧ���п���ͨ��POI�������ⲿPOI��Դ�ȷ�ʽ��ȡ���յ�����
		BaiduNaviManager.getInstance().launchNavigator(this, 40.05087,
				116.30142, "�ٶȴ���", 39.90882, 116.39750, "�����찲��",
				NE_RoutePlan_Mode.ROUTE_PLAN_MOD_MIN_TIME, // ��·��ʽ
				true, // ��ʵ����
				BaiduNaviManager.STRATEGY_FORCE_ONLINE_PRIORITY, // �����߲���
				new OnStartNavigationListener() { // ��ת����

					@Override
					public void onJumpToNavigator(Bundle configParams) {
						Intent intent = new Intent(MainActivity.this,
								BNavigatorActivity.class);
						intent.putExtras(configParams);
						startActivity(intent);
					}

					@Override
					public void onJumpToDownloader() {
					}
				});
	}

	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
