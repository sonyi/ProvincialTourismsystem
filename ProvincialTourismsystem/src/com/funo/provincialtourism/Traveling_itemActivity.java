package com.funo.provincialtourism;

import java.util.ArrayList;

import com.funo.antennatestsystem.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Traveling_itemActivity extends BaseActivity implements
		OnClickListener {
	private View map_bntvs;
	private boolean b = false;
	private ProvincialMyApplicition app;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_traveling_item);
		app = (ProvincialMyApplicition) this.getApplication();
		app.addActivity(this);

	}

	@Override
	protected void onResume() {
		initUi();
		initTitles();
		setBottombnt(getBottombnt(), 0, getBottomtv());
		super.onResume();
	}

	private void initTitles() {
		initTitlebase();
		title.setText("旅游服务");
		bnt_back.setVisibility(0);
		bnt_meun.setVisibility(0);
		bnt_back.setOnClickListener(this);
		bnt_meun.setOnClickListener(this);
	}

	/**
	 * 键盘监听事件
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (event.getKeyCode() == event.KEYCODE_BACK) {
			Intent intent = null;
			if (getIntent().getBooleanExtra("Noty", false)) {
				intent = new Intent(Traveling_itemActivity.this,
						Map_NotyActivity.class);
			} else if(getIntent().getBooleanExtra("Char", false)){
				intent = new Intent(Traveling_itemActivity.this,
						Map_CharacteristicActivity.class);
			}else
			{
				intent = new Intent(Traveling_itemActivity.this,
						Map_TravelingActivity.class);
			}
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

	private void initUi() {
		map_bntvs = (View) findViewById(R.id.map_bntvs);
		TextView messages = (TextView) findViewById(R.id.message);
		messages.setText("        每年中秋节厦门要举行中秋博饼的传统活动。 "+"\n"
				+ "据说在 300多年前郑成功据厦抗清，郑的部将洪旭为了宽释士兵愁绪，激励鼓舞士气，利于驱逐荷兰殖民者收复台湾，于是与当年驻扎在今洪本部33～44号的后部衙堂属员，经过一番推敲，巧妙设计中秋会饼，让全体将士在凉爽的中秋夜晚欢快拼搏。\n\n\t"
				+ "戏饼以“会”计算，一般一“会”以四五人为宜。“会”饼模仿科举制，设状元饼(最大的)一个、对堂(榜眼)饼二个、三红(探花)饼四个、四进(进士)饼八个、二举(举人)饼16个、一秀(秀才)饼32个。这是象征古代四级科举考试。古代府级考试及第的童生称秀才；\n\n\t"
				+ "乡试(省级)考中者称举人；在京师礼部会度及第者称贡生；由皇帝亲自主持的殿度及第者称进士，其中又分三甲：一甲三名，即状元、榜眼、探花，俗称三鼎甲或三及第；二甲名额较多，三甲就更多了。古代皇帝点状元，既看才，又看貌，还要推敲，考究姓氏和名字，\n\n\t"
				+ "如永乐二十二年(1424年)甲辰科殿试，状元为孙曰恭，明成祖觉得曰恭合在一起是“暴”字，不吉利，将他降为第三名，将第三名的邢宽易改为状元。这说明状元不一定是“才高八斗，貌若潘安”之辈，而第三名是有真才实学，所以厦门会饼中的“三红”质量特别好，\n\n\t"
				+ "寓意在此。一套会饼共63块，是根据“三多九如”而来的，三和九是我国民间的吉利数。源于厦门的“博饼”，随郑成功收复台湾， 300年多来台湾也很盛行。");

	}

	public void returns() {
		Intent intent = null;
		if (getIntent().getBooleanExtra("Noty", false)) {
			intent = new Intent(Traveling_itemActivity.this,
					Map_NotyActivity.class);
		} else if(getIntent().getBooleanExtra("Char", false)){
			intent = new Intent(Traveling_itemActivity.this,
					Map_CharacteristicActivity.class);
		}else
		{
			intent = new Intent(Traveling_itemActivity.this,
					Map_TravelingActivity.class);
		}
		intent.putExtra("cityname", getIntent().getStringExtra("cityname"));
		intent.putExtra("latitude", getIntent().getDoubleExtra("latitude", 0.0));
		intent.putExtra("longitude",
				getIntent().getDoubleExtra("longitude", 0.0));
		startActivity(intent);
		overridePendingTransition(R.animator.in_from_right,
				R.animator.out_to_left);
		finish();
	}

	public void showmenu() {
		if (b) {
			map_bntvs.setVisibility(8);
		} else {
			map_bntvs.setVisibility(0);
		}
		b = !b;
	}

	public void bottoms(View v) {
		Intent intent = null;
		switch (v.getId()) {
		case R.id.bottombnt_2:
			intent = new Intent(Traveling_itemActivity.this,
					Provincial_ClearIndexActivity.class);
			break;
		case R.id.bottombnt_3:
			intent = new Intent(Traveling_itemActivity.this,
					Provincial_SettingActivity.class);
			break;
		case R.id.bottombnt_4:
			intent = new Intent(Traveling_itemActivity.this,
					Provincial_SettingActivity.class);
			break;
		}
		if (intent != null) {
			startActivity(intent);
			overridePendingTransition(R.animator.in_from_right,
					R.animator.out_to_left);
		}
	}

	@Override
	protected void onDestroy() {
		app.removeActivity(this);
		super.onDestroy();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bnt_back:
			returns();
			break;
		case R.id.bnt_meun:
			showmenu();
			break;
		default:
			break;
		}
	}
}
