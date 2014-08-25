package com.example.util;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

/**
 * Toast������ʾ�򹤾��࣬���ڼ�Toast��ʾ����
 * 
 * @author Li Bin
 */
public class ToastUtil {

	/**
	 * Ĭ�Ϸ�ʽ��ʾToast
	 * 
	 * @param context
	 *            ָ����Contextʵ��
	 * @param message
	 *            Ҫͨ��Toast��ʾ���ı���Ϣ
	 */
	public static void showToast(Context context, String message) {
		Toast toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
		toast.setGravity(Gravity.CENTER, 0, 0);
		toast.show();
	}

	/**
	 * Ĭ�Ϸ�ʽ��ʾToast
	 * 
	 * @param context
	 *            ָ����Contextʵ��
	 * @param resId
	 *            Ҫͨ��Toast��ʾ���ı���Ϣ�ַ�����Դ
	 */
	public static void showToast(Context context, int resId) {
		Toast toast = Toast.makeText(context, resId, Toast.LENGTH_SHORT);
		toast.setGravity(Gravity.CENTER, 0, 0);
		toast.show();
	}

	/**
	 * ��ʾToast
	 * 
	 * @param context
	 *            ָ����Contextʵ��
	 * @param resId
	 *            Ҫͨ��Toast��ʾ���ı���Ϣ�ַ�����Դ
	 * @param time
	 *            ����Toastͣ����ʱ��
	 */
	public static void showToast(Context context, int resId, int time) {
		Toast toast = Toast.makeText(context, resId, time);
		toast.setGravity(Gravity.CENTER, 0, 0);
		toast.show();
	}
}