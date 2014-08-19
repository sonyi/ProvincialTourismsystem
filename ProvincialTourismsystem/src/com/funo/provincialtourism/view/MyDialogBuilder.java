package com.funo.provincialtourism.view;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;

public class MyDialogBuilder extends Builder {

	public MyDialogBuilder(Context getWebServiceMess) {
		super(getWebServiceMess);
	}
	
	@Override
	public AlertDialog create() {
		AlertDialog dialog =  super.create();
		dialog.getWindow().setFlags(0x80000000, 0x80000000);
		dialog.setCanceledOnTouchOutside(true);		
		return dialog;
	}

}
