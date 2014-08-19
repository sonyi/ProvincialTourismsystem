package com.funo.provincialtourism;

import java.util.ArrayList;

import com.funo.antennatestsystem.R;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class BaseActivity extends Activity {
	public static Button[] mBottombnts;
	private boolean b = false;
	public TextView title;
	public Button bnt_meun;
	public Button bnt_back;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		mBottombnts = getBottombnt();
		initTitlebase();
	}

	public void initTitlebase() {
		title = (TextView) findViewById(
				R.id.tv_title);
		bnt_back = (Button) findViewById(
				R.id.bnt_back);
		bnt_meun = (Button) findViewById(
				R.id.bnt_meun);
	}

	public Button[] getBottombnt() {
		Button bottombnt_1 = (Button) findViewById(R.id.bottombnt_1);
		Button bottombnt_2 = (Button) findViewById(R.id.bottombnt_2);
		Button bottombnt_3 = (Button) findViewById(R.id.bottombnt_3);
		Button bottombnt_4 = (Button) findViewById(R.id.bottombnt_4);
		Button bottombnts[] = { bottombnt_1, bottombnt_2, bottombnt_3,
				bottombnt_4 };
		return bottombnts;
	}
	public TextView[] getBottomtv() {
		TextView bottomtv_1 = (TextView) findViewById(R.id.bottomtv_1);
		TextView bottomtv_2 = (TextView) findViewById(R.id.bottomtv_2);
		TextView bottomtv_3 = (TextView) findViewById(R.id.bottomtv_3);
		TextView bottomtv_4 = (TextView) findViewById(R.id.bottomtv_4);
		TextView bottomtvs[] = { bottomtv_1, bottomtv_2, bottomtv_3,
				bottomtv_4 };
		return bottomtvs;
	}
	public void setBottombnt(Button[] bottombnts, int index,TextView[] bottomtvs) {
		ArrayList<Boolean> isnewCheckbot = new ArrayList<Boolean>();
		isnewCheckbot.add(false);
		isnewCheckbot.add(false);
		isnewCheckbot.add(false);
		isnewCheckbot.add(false);
		isnewCheckbot.set(index, true);
		for (int i = 0; i < bottombnts.length; i++) {
			bottombnts[i].setSelected(isnewCheckbot.get(i));
		}
		setBottomtv(bottomtvs,index);
	}

	public void setBottomtv(TextView[] bottomtvs, int index) {
		for (int i = 0; i < bottomtvs.length; i++) {
			if(i==index)
			{
				bottomtvs[i].setBackgroundColor(getResources().getColor(R.color.titlebar_bg));
			}else
			{
				bottomtvs[i].setBackgroundColor(getResources().getColor(R.color.bottom_bg));
			}
		}
	}
}
