package com.share.sharedemo;


import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class MainActivity extends Activity {
	private int[] images = { R.drawable.yuding_0201, R.drawable.yuding_0202,
			R.drawable.yuding_0203, R.drawable.yuding_0204,
			R.drawable.yuding_0205, R.drawable.yuding_0206,
			R.drawable.yuding_0301, R.drawable.yuding_0302,
			R.drawable.yuding_0303, R.drawable.yuding_0304,
			R.drawable.yuding_0105 };
	private String date[][] = { { "厦门鼓浪屿", "位于日光岩莲花庵前的巨石上有概述题字‘岩石’" },
			{ "厦门鼓浪屿", "位于日光岩莲花庵前的巨石上有概述题字‘岩石’" }, { "日光岩", "登日光岩俯瞰鼓浪屿" },
			{ "万国建筑", "这里是鼓浪屿中西文化交流的精萃景观。中国传统建筑，如宗教建筑..." },
			{ "小店", "逛小店感受小清新" }, { "钢琴博物馆", "在音乐之岛聆听琴声飘飘" },
			{ "厦门海底世界", "融生态保护，海洋知识、海洋水产、科教，观光于一体的大型海洋水..." },
			{ "皓月园", "无论是球星、政客，还是影视巨星，你都能在这里发现，馆中塑像都..." },
			{ "菽庄花园", "曾为私人别墅的海滨花园，远眺可见海浪、园中可见秀丽园林。" },
			{ "郑成功纪念馆", "郑成功纪念馆是1962年为纪念郑成功收复台湾300周年而建立..." },
			{ "钢琴码头", "如同一架三角钢琴的钢琴码头，是钢琴之岛迎接游人的起点，踏上码..." } };


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
