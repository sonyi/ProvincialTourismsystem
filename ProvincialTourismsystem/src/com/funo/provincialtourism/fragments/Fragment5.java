package com.funo.provincialtourism.fragments;

import com.funo.antennatestsystem.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Fragment5 extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View layout = inflater.inflate(R.layout.fragment5, null);
		TextView mess = (TextView) layout.findViewById(R.id.mess);
		mess.setText("赵小姐的店更小资情调一些，更浪漫端庄一些。赵小姐的店有二层楼，空间更大一些，不会显得拥挤或怕没有位置坐。室内布置得很雅致，有着或古典或可爱的摆设，有书看看有本记记。食物多元化，味道也是可悦的。其中秘制的烧仙草和手工素馅饼，味道很不错。无论是深蓝色的墙壁，还是红艳艳的皮沙发，白瓷器，绿植物，都能将色彩的美丽发挥到最极致。再看看桌前的流苏台灯，青花餐具，古典书籍，刺绣织品……这里每一样摆设都独具匠心，像走进了闺中小姐的绣房，精美雅致，让人欢喜");
		return layout;
	}

}
