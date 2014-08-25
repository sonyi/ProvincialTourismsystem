package com.funo.provincialtourism;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Paint;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

import com.funo.antennatestsystem.R;
import com.funo.provincialtourism.tool.Contants;

public class Map_ScenicSpotActivity extends BaseActivity {

	private ImageView image_jingqu;
	private TextView tv_jingqu1, tv_jingqu3;
	private TextView tv_zl;
	private TextView tv_pm;
	private TextView tv_xz;
	private TextView yv_lz;
	private TextView tv_jia;
	private TextView tv_jianj;
	private ProvincialMyApplicition app;
	private Dialog shareDialog;
	private Dialog shoucDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_yudings);
		app = (ProvincialMyApplicition) getApplication();
		app.addActivity(this);
		initTitle();
		initUI();
		initDate();
		setBottombnt(getBottombnt(), 0, getBottomtv());
	}

	@Override
	protected void onResume() {

		super.onResume();
	}

	/**
	 * 键盘监听事件
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (event.getKeyCode() == event.KEYCODE_BACK) {

			Intent intent = new Intent(Map_ScenicSpotActivity.this,
					Map_CityLocationActivity.class);
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
				Intent intent = new Intent(Map_ScenicSpotActivity.this,
						Map_CityLocationActivity.class);
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
		String cityname = intent.getStringExtra("cityname");
		title.setText(cityname);
	}

	/**
	 * 初始化UI
	 */
	private void initUI() {
		image_jingqu = (ImageView) findViewById(R.id.image_jingqu);// 景区图片
		tv_jingqu1 = (TextView) findViewById(R.id.tv_jingqu1);// 景区名称
		tv_jingqu3 = (TextView) findViewById(R.id.tv_jingqu3);// 景区名称
		tv_zl = (TextView) findViewById(R.id.tv_zl);// 空气质量
		tv_pm = (TextView) findViewById(R.id.tv_pm);// pm值
		tv_xz = (TextView) findViewById(R.id.tv_xz);// 景区现况
		yv_lz = (TextView) findViewById(R.id.yv_lz);// 负氧离子
		tv_jia = (TextView) findViewById(R.id.tv_jia);// 门票价格
		tv_jianj = (TextView) findViewById(R.id.tv_jianj);// 景区简介
		TextView pricehua = (TextView) findViewById(R.id.pricehua);
		pricehua.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
		Button mapbnt_1 = (Button) findViewById(R.id.mapbnt_1);
		mapbnt_1.setSelected(true);

		View sharedialog = getLayoutInflater().inflate(R.layout.sharedialog,
				null);
		View dialog_shoucang = getLayoutInflater().inflate(
				R.layout.dialog_shoucang, null);
		TextView jingq = (TextView) dialog_shoucang
				.findViewById(R.id.textView2);

		shareDialog = new Dialog(this, R.style.Dialog);
		shareDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		shareDialog.setContentView(sharedialog);

		shoucDialog = new Dialog(this, R.style.Dialog);
		shoucDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		shoucDialog.setContentView(dialog_shoucang);
	}

	/**
	 * 初始化数据
	 */
	private void initDate() {
		Intent intent = getIntent();
		int groupPosition = intent.getIntExtra("groupPosition", 0);
		int childPosition = intent.getIntExtra("childPosition", 0);
		Log.e("", "groupPosition:" + groupPosition + ",childPosition:"
				+ childPosition);
		image_jingqu
				.setBackgroundResource(Contants.IMAGES[groupPosition][childPosition]);
		tv_jingqu1.setText(Contants.CITYSS[groupPosition][childPosition]);
		tv_jingqu3.setText(Contants.CITYSS[groupPosition][childPosition]);
		tv_zl.setText("07:30-17:30");
		tv_pm.setText("AAAAA");
		tv_xz.setText("0592-2063094");
		yv_lz.setText("2.50");
		tv_jia.setText("101");
		tv_jianj.setText("        鼓浪屿位于厦门岛西南隅，与厦门市只隔一条宽600米的鹭江，轮渡5分钟可达。岛上气候宜人四季如春，无车马喧嚣，有鸟语花香，素有“海上花园”之誉。小岛完好地保留着许多具有中外各种建筑风格的建筑物，有中国传统的飞檐翘角的庙宇，有小巧玲珑的日本屋舍，也有19世纪欧陆风格的原西方国家的领事馆，有“万国建筑博览会”之誉。鼓浪屿还是音乐人才辈出，钢琴拥有密度居全国之冠，又得美名“钢琴之岛”、“音乐之乡”。...");
		title.setText(Contants.CITYSS[groupPosition][childPosition]);

	}

	/**
	 * 购买按钮
	 * 
	 * @param v
	 */
	public void pay(View v) {

	}

	public void bottoms(View v) {
		Intent intent = null;
		switch (v.getId()) {
		case R.id.bottombnt_2:
			intent = new Intent(Map_ScenicSpotActivity.this,
					Provincial_ClearIndexActivity.class);
			break;
		case R.id.bottombnt_3:
			intent = new Intent(Map_ScenicSpotActivity.this,
					Provincial_TicketActivity.class);
			break;
		case R.id.bottombnt_4:
			intent = new Intent(Map_ScenicSpotActivity.this,
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

	public void share(View v) {
		// shareDialog.dismiss();
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

	public void dialog_click(View v) {
		shoucDialog.dismiss();
	}

	public void clickbnt(View v) {
		int index = 0;
		switch (v.getId()) {
		case R.id.mapbnt_2:
			index = 1;
			break;
		case R.id.mapbnt_3:
			index = 2;
			break;
		case R.id.mapbnt_4:
			index = 3;
			shoucDialog.show();
			break;

		case R.id.mapbnt_5:
			index = 4;
			showShare();
			break;

		case R.id.mapbnt_6:
			index = 5;
			break;
		case R.id.mapbnt_7:
			index = 6;
			break;
		case R.id.mapbnt_8:
			index = 7;
			break;

		}
		if (index == 3 || index == 4) {
			return;
		}
		Intent intent = new Intent(this, Map_ScenicLocationActivity.class);
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
		intent.putExtra("latitude", getIntent().getDoubleExtra("latitude", 0.0));
		intent.putExtra("longitude",
				getIntent().getDoubleExtra("longitude", 0.0));
		intent.putExtra("bntNum", index);
		startActivity(intent);
		finish();
	}

	private void showShare() {
//		shareDialog.show();
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

	@Override
	protected void onDestroy() {
		app.removeActivity(this);
		super.onDestroy();
	}
}
