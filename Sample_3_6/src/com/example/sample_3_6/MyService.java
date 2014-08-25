package com.example.sample_3_6;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;

public class MyService extends Service{
	boolean flag;
	CommandReceiver cmdReceiver;

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		flag = true;
		cmdReceiver = new CommandReceiver();
		super.onCreate();
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		IntentFilter filter = new IntentFilter();
		filter.addAction("com.example.sample_3_6.SERVICE");
		registerReceiver(cmdReceiver, filter);
		doJob();
		return super.onStartCommand(intent, flags, startId);
	}
	
	private void doJob() {
		// TODO Auto-generated method stub
		new Thread(){
			public void run() {
				while (flag) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Intent intent = new Intent();
					intent.setAction("com.example.sample_3_6.MAIN_ACTIVITY");
					intent.putExtra("data", Math.random());
					sendBroadcast(intent);
				}
			};
		}.start();
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		unregisterReceiver(cmdReceiver);
	}
	
	private class CommandReceiver extends BroadcastReceiver{

		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			int cmd = intent.getIntExtra("cmd", -1);
			if(cmd == MainActivity.STOP_SERVICE){
				flag = false;
				stopSelf();
			}
		}
	}
}
