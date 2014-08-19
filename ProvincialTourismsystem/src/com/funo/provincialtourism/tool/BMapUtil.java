package com.funo.provincialtourism.tool;

import java.util.UUID;

import com.funo.antennatestsystem.R;
import com.funo.provincialtourism.view.MyDialogBuilder;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.DialogInterface.OnClickListener;
import android.content.SharedPreferences.Editor;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.MeasureSpec;
import android.widget.ImageView;
import android.widget.TextView;


public class BMapUtil {
	/**
	 * 从view 得到图片
	 * 
	 * @param view
	 * @return
	 */
	public static Bitmap getBitmapFromView(View view) {
		view.destroyDrawingCache();
		view.measure(View.MeasureSpec.makeMeasureSpec(0,
				View.MeasureSpec.UNSPECIFIED), View.MeasureSpec
				.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
		view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
		view.setDrawingCacheEnabled(true);
		Bitmap bitmap = view.getDrawingCache(true);
		return bitmap;
	}

	/**
	 * 判断GPS
	 */
	public static void toggleGPS(final Activity activity) {
		boolean gpsEnabled = Settings.Secure.isLocationProviderEnabled(
				activity.getContentResolver(), LocationManager.GPS_PROVIDER);
		if (!gpsEnabled) {
			toggle(activity);
		}
	}

	/**
	 * 打开GPS
	 */
	public static void toggle(final Activity activity) {
		Dialog dialog = new MyDialogBuilder(activity).setTitle("提示")
				.setMessage("手机没有开启GPS功能，您是否要开启？")
				.setPositiveButton("设置", new OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						activity.startActivity(new Intent(
								Settings.ACTION_LOCATION_SOURCE_SETTINGS));
					}
				}).setNegativeButton("取消", new OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) 
					{

					}
				}).create();
		dialog.show();

	}
	/**
	 * 判断网络状态
	 */
	public static boolean checkNetworkState(Activity activity) {
		boolean flag = false;
		// 得到网络连接信息
		ConnectivityManager manager = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
		// 去进行判断网络是否连接
		if (manager.getActiveNetworkInfo() != null) {
			flag = manager.getActiveNetworkInfo().isAvailable();
		}
		return flag;
	}
	/**
	 * 获取版本
	 * @param activity
	 * @return
	 */
	public static String getCurrnversion(Context activity)
    {
        PackageManager packageManager = activity.getPackageManager();
        try
        {
            PackageInfo pi = packageManager.getPackageInfo(activity.getPackageName(), 0);
            return pi.versionName;
        }
        catch (NameNotFoundException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "";
    }
	/**
	 * 计数器
	 */
	public static void currenLeng(Context context,String currenName) {
		String currnversion = getCurrnversion(context);
		SharedPreferences sp = context.getSharedPreferences("AntennaSave", context.MODE_PRIVATE);
		int starttime = sp.getInt(currnversion + "_"+currenName, 0);
		if(starttime >= 0)
		{
			starttime++;
			Editor edit = sp.edit();
			edit.putInt(currnversion + "_"+currenName,starttime);
			Log.e(currnversion + "_"+currenName,"次数："+starttime);
			edit.commit();
		}
	}
	/**
	 * 获取UUID
	 * @return
	 */
	
	public static String getMyUUID(Context context) {
		@SuppressWarnings("static-access")
		TelephonyManager tm = (TelephonyManager) context.getSystemService(
				context.TELEPHONY_SERVICE);
		String tmDevice = "" + tm.getDeviceId();
		String tmSerial = "" + tm.getSimSerialNumber();
		String androidId = ""
				+ android.provider.Settings.Secure.getString(
						context.getContentResolver(),
						android.provider.Settings.Secure.ANDROID_ID);
		UUID deviceUuid = new UUID(androidId.hashCode(),((long) tmDevice.hashCode() << 32) | tmSerial.hashCode());
		String uniqueId = deviceUuid.toString();
		return uniqueId;

//		UUID uuid = UUID.randomUUID();
//	    String uniqueId = uuid.toString();
//	    Log.i("yun","----->UUID"+uuid);
//	    return uniqueId;
	}
	/**
	 * 获取手机mac地址<br/>
	 * 错误返回12个0
	 */
	public static String getMacAddress(Context context) {
		// 获取mac地址：
		String macAddress = "000000000000";
		try {
			WifiManager wifiMgr = (WifiManager) context
					.getSystemService(Context.WIFI_SERVICE);
			WifiInfo info = (null == wifiMgr ? null : wifiMgr
					.getConnectionInfo());
			if (null != info) {
				if (!TextUtils.isEmpty(info.getMacAddress()))
					macAddress = info.getMacAddress().replace(":", "");
				else
					return macAddress;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return macAddress;
		}
		return macAddress;
	}
	
	/**
	 * 将布局转换成图片
	 * 
	 * @param title
	 * @param imageId
	 * @param textBg
	 * @return
	 */
	public static Drawable LayoutToDrawable(Activity activity,String title, int imageId, int textBg,char c) {
		View viewCache = activity.getLayoutInflater().inflate(R.layout.custom_text_view,
				null);
		View lat = viewCache.findViewById(R.id.lat);
		TextView popupText = (TextView) viewCache.findViewById(R.id.textcache);
		TextView maks = (TextView) viewCache.findViewById(R.id.maks);
		maks.setBackgroundResource(imageId);
		maks.setText(c+"");
		popupText.setText(title);
		lat.setBackgroundResource(textBg);
		Bitmap snapshot = getBitmapFromView(viewCache);
		Drawable drawable = (Drawable) new BitmapDrawable(snapshot);
		return drawable;
	}
	/**
	 * 将View转换成图片
	 * 
	 * @param view
	 * @return
	 */
	public static Bitmap convertViewToBitmap(View view) {
		view.measure(MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED),
				MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
		view.layout(0, 0, 190, 60); // 根据字符串的长度显示view的宽度
		view.buildDrawingCache();
		Bitmap bitmap = view.getDrawingCache();
		return bitmap;
	}
}