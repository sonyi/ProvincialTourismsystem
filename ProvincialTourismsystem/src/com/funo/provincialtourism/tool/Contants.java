package com.funo.provincialtourism.tool;

import com.funo.antennatestsystem.R;

public interface Contants {
	public static final String CITYS[] = { "福州", "厦门", "泉州", "漳州" };
	public static final String CITYNAMES[] = { "福州市", "莆田市", "泉州市", "厦门市","漳州市","龙岩市","三明市", "南平市","宁德市"};
	
	public static final double[][] CITY_LOCATIONS = {
			{ 119.304637, 26.079062 }, { 119.014347, 25.459281 },
			{ 118.682862, 24.880412 }, {118.078185,24.452921 },
			{ 117.656945, 24.52101 }, { 117.022453, 25.08091 },
			{ 117.645212, 26.268956 }, { 118.184326, 26.647113 },
			{ 119.55279, 26.671572 } };
	
	public static final double[][] XIAMEN_JINGQU={
		{118.078185,24.452921},
		{118.109446,24.44279},
		{118.078042,24.448777},
		{118.136288,24.431078},
		{118.078904,24.444994},
		{118.141893,24.42824}
	};
	
	
	public static final int[] PMS = { 26, 28, 39, 21 };// 各个市的pm值
	public static final int[] YDS = { 3, 5, 4, 3 };// 各个市的拥堵数
	public static final String[] WD = { "晴 32°~35°", "晴 31°~34°", "晴 30°~35°",
			"晴 32°~35°" };// 各个市的天气情况

	public static final String[] CITY1 = { "三坊七巷", "永泰云顶", "皇帝洞", "森林公园",
			"福清石竹山", "永泰天门山" };// 福州市景区
	public static final String[] CITY2 = { "鼓浪屿", "厦门大学", "日光岩", "曾厝垵", "菽庄花园",
			"环岛路" }; // 厦门市景区
	public static final String[] CITY3 = { "晋江", "崇武", "开元寺", "牛姆林", "安平桥",
			"洛阳桥" }; // 泉州市景区
	public static final String[] CITY4 = { "东山岛", "田螺坑土楼群", "云水谣古镇", "云洞岩",
			"赵家堡", "南山寺" }; // 漳州市景区
	public static final String[][] CITYSS = { CITY1, CITY2, CITY3, CITY4 };

	public static final String[] NUM1 = { "9582", "8218", "8018", "7983",
			"6588", "6051" }; // 福州市景区人数
	public static final String[] NUM2 = { "10982", "9208", "8423", "8022",
			"7598", "7308" }; // 厦门市景区人数
	public static final String[] NUM3 = { "8023", "6238", "5923", "5283",
			"4321", "4009" }; // 泉州市景区人数
	public static final String[] NUM4 = { "8263", "7253", "7012", "6283",
			"6012", "5361" };// 漳州市景区人数
	public static final String[][] NUMS = { NUM1, NUM2, NUM3, NUM4 };

	public static final String[] QINGXIN1 = { "拥挤", "拥挤", "拥挤", "舒适", "舒适",
			"舒适" };// 福州市景区清新情况
	public static final String[] QINGXIN2 = { "拥挤", "拥挤", "拥挤", "拥挤", "拥挤",
			"舒适" };// 厦门市景区清新情况
	public static final String[] QINGXIN3 = { "拥挤", "拥挤", "拥挤", "拥挤", "舒适",
			"舒适" };// 泉州市景区清新情况
	public static final String[] QINGXIN4 = { "拥挤", "拥挤", "拥挤", "舒适", "舒适",
			"舒适" };// 漳州市景区清新情况
	public static final String[][] QINGXINS = { QINGXIN1, QINGXIN2, QINGXIN3,
			QINGXIN4 };

	public static final int[] IMAGE1 = { R.drawable.yuding_0101,
			R.drawable.yuding_0102, R.drawable.yuding_0103,
			R.drawable.yuding_0104, R.drawable.yuding_0105,
			R.drawable.yuding_0106 };// 福州市景区照片
	public static final int[] IMAGE2 = { R.drawable.yuding_0201,
			R.drawable.yuding_0202, R.drawable.yuding_0203,
			R.drawable.yuding_0204, R.drawable.yuding_0205,
			R.drawable.yuding_0206 };// 厦门市景区照片
	public static final int[] IMAGE3 = { R.drawable.yuding_0301,
			R.drawable.yuding_0302, R.drawable.yuding_0303,
			R.drawable.yuding_0304, R.drawable.qingxin_image0305,
			R.drawable.qingxin_image0306 };// 泉州市景区照片
	public static final int[] IMAGE4 = { R.drawable.yuding_0401,
			R.drawable.yuding_0402, R.drawable.yuding_0403,
			R.drawable.qingxin_image0404, R.drawable.yuding_0405,
			R.drawable.yuding_0406 };// 漳州市景区照片
	public static final int[][] IMAGES = { IMAGE1, IMAGE2, IMAGE3, IMAGE4 };

	public static final int[] JQ_IMAGE1 = { R.drawable.qingxin_image0101,
			R.drawable.qingxin_image0102, R.drawable.qingxin_image0103,
			R.drawable.qingxin_image0104, R.drawable.qingxin_image0105,
			R.drawable.qingxin_image0106 };// 福州市景区照片
	public static final int[] JQ_IMAGE2 = { R.drawable.qingxin_image0201,
			R.drawable.qingxin_image0202, R.drawable.qingxin_image0203,
			R.drawable.qingxin_image0204, R.drawable.qingxin_image0205,
			R.drawable.qingxin_image0206 };// 厦门市景区照片
	public static final int[] JQ_IMAGE3 = { R.drawable.qingxin_image0301,
			R.drawable.qingxin_image0302, R.drawable.qingxin_image0303,
			R.drawable.qingxin_image0304, R.drawable.qingxin_image0305,
			R.drawable.qingxin_image0306 };// 泉州市景区照片
	public static final int[] JQ_IMAGE4 = { R.drawable.qingxin_image0401,
			R.drawable.qingxin_image0402, R.drawable.qingxin_image0403,
			R.drawable.qingxin_image0404, R.drawable.qingxin_image0405,
			R.drawable.qingxin_image0406 };// 漳州市景区照片
	public static final int[][] JQ_IMAGES = { JQ_IMAGE1, JQ_IMAGE2, JQ_IMAGE3, JQ_IMAGE4 };

}
