package com.example.userapply;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class XiangmuSQLiteHelper extends SQLiteOpenHelper{

	public XiangmuSQLiteHelper(Context context) {
		super(context, "xiangmu.db", null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE xiangmu" +
				"(" +
					"id INTEGER," +
					"carid VARCHAR(100)," +
					"username VARCHAR(100)," +
					"waiter VARCHAR(100)," +
					"leibie VARCHAR(100)," +
					"xiangmuleibie VARCHAR(100)," +
					"xiangmuname VARCHAR(100)," +
					"xiangmmoney VARCHAR(100)," +
					"youhui VARCHAR(100)," +
					"number INTEGER," +
					"zhekou INTEGER," +
					"zhehoumoney VARCHAR(100)," +
					"lastmoney VARCHAR(100)," +
					"buyType VARCHAR(100)," +
					"combocardID VARCHAR(100)," +
					"combocardmID VARCHAR(100)," +
					"serviceItemID VARCHAR(100)" +
				")");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
	}

}
