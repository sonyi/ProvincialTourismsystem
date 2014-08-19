package com.share.sharedemo;


import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends Activity {
	private int[] images = { R.drawable.yuding_0201, R.drawable.yuding_0202,
			R.drawable.yuding_0203, R.drawable.yuding_0204,
			R.drawable.yuding_0205, R.drawable.yuding_0206,
			R.drawable.yuding_0301, R.drawable.yuding_0302,
			R.drawable.yuding_0303, R.drawable.yuding_0304,
			R.drawable.yuding_0105 };
	private String date[][] = { { "���Ź�����", "λ���չ���������ǰ�ľ�ʯ���и������֡���ʯ��" },
			{ "���Ź�����", "λ���չ���������ǰ�ľ�ʯ���и������֡���ʯ��" }, { "�չ���", "���չ��Ҹ������" },
			{ "�������", "�����ǹ����������Ļ������ľ��;��ۡ��й���ͳ���������ڽ̽���..." },
			{ "С��", "��С�����С����" }, { "���ٲ����", "������֮����������ƮƮ" },
			{ "���ź�������", "����̬����������֪ʶ������ˮ�����ƽ̣��۹���һ��Ĵ��ͺ���ˮ..." },
			{ "���԰", "���������ǡ����ͣ�����Ӱ�Ӿ��ǣ��㶼�������﷢�֣���������..." },
			{ "��ׯ��԰", "��Ϊ˽�˱����ĺ�����԰��Զ���ɼ����ˡ�԰�пɼ�����԰�֡�" },
			{ "֣�ɹ������", "֣�ɹ��������1962��Ϊ����֣�ɹ��ո�̨��300���������..." },
			{ "������ͷ", "��ͬһ�����Ǹ��ٵĸ�����ͷ���Ǹ���֮��ӭ�����˵���㣬̤����..." } };


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		showView();
	}

	//��̬�������ҿؼ�
	private void showView() {
		RelativeLayout rl = (RelativeLayout) findViewById(R.id.rel_view_rellayout);
		int height = dip2Px(this, 150);
		for (int i = 0; i < images.length; i++) {
			if (i % 2 == 0) {// ���
				RelativeLayout.LayoutParams param = new RelativeLayout.LayoutParams(
						ViewGroup.LayoutParams.FILL_PARENT, height);
				param.topMargin = (i / 2) * height;
				View view = getLayoutInflater().inflate(
						R.layout.scenerylistleft, null);
				initLeftWidget(view, i);
				rl.addView(view, param);

			} else {// �ұ�
				RelativeLayout.LayoutParams param = new RelativeLayout.LayoutParams(
						ViewGroup.LayoutParams.FILL_PARENT, height);
				param.topMargin = ((i - 1) / 2) * height + dip2Px(this, 50);
				View view = getLayoutInflater().inflate(
						R.layout.scenerylistright, null);
				initRightWidget(view, i);
				rl.addView(view, param);
			}
		}
	}
	
	//��߿ؼ���ֵ
	private void initLeftWidget(View view,int i){
		ImageView img = (ImageView) view.findViewById(R.id.iv_view_sceneryleft_image);
		TextView title = (TextView) view.findViewById(R.id.tv_view_left_title);
		TextView info = (TextView) view.findViewById(R.id.tv_view_left_info);
		img.setImageResource(images[i]);
		title.setText(date[i][0]);
		info.setText(date[i][1]);
	}
	
	//�ұ߿ؼ���ֵ
	private void initRightWidget(View view,int i){
		ImageView img = (ImageView) view.findViewById(R.id.iv_view_sceneryright_image);
		TextView title = (TextView) view.findViewById(R.id.tv_view_right_title);
		TextView info = (TextView) view.findViewById(R.id.tv_view_right_info);
		img.setImageResource(images[i]);
		title.setText(date[i][0]);
		info.setText(date[i][1]);
	}
	
	//dpת����px
	public static int dip2Px(Context context, float dpValue) {
		float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
