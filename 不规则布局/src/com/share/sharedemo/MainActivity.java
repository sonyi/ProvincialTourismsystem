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

		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
