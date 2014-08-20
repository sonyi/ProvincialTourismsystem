package com.example.fileiodemo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {
	private Button mReadFromPhone, mReadFromSDCard;
	private TextView mInfo;
	private static String FILE_IN_PHONE_NAME = "file.txt";
	private static String FILE_IN_SDCARD_NAME = "/mnt/sdcard/file.txt";
	private static String DIR = "fileDir";
	private static String FILE_NAME = "file.txt";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mReadFromPhone = (Button) findViewById(R.id.btn_read_phone);
		mReadFromSDCard = (Button) findViewById(R.id.btn_read_sdcard);
		mInfo = (TextView) findViewById(R.id.tv_info);
		mReadFromPhone.setOnClickListener(this);
		mReadFromSDCard.setOnClickListener(this);

		// fileSaveInPhone();

		// fileSaveInSdcard();
		fileSaveInSdcard2();
	}

	private void fileSaveInSdcard2() {
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			File file = new File(Environment.getExternalStorageDirectory()
					.toString()
					+ File.separator
					+ DIR
					+ File.separator
					+ FILE_NAME);
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}

			PrintStream ps = null;
			try {
				ps = new PrintStream(new FileOutputStream(file));
				ps.print("file save in SDCARD");

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if (ps != null) {
					ps.close();
				}
			}

		} else {
			Toast.makeText(this, "sdcard²»´æÔÚ", Toast.LENGTH_SHORT).show();
		}
	}

	private void fileSaveInSdcard() {
		File file = new File(FILE_IN_SDCARD_NAME);
		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		}

		PrintStream ps = null;
		try {
			ps = new PrintStream(new FileOutputStream(file));
			ps.print("file save in SDCARD");

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (ps != null) {
				ps.close();
			}
		}
	}

	private void fileSaveInPhone() {
		FileOutputStream fos = null;
		try {
			fos = openFileOutput(FILE_IN_PHONE_NAME, Activity.MODE_PRIVATE);
			PrintStream ps = new PrintStream(fos);
			ps.println(1001);
			ps.println("file");
			ps.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v.getId() == R.id.btn_read_phone) {
			readInfoFromPhone();
			return;
		} else if (v.getId() == R.id.btn_read_sdcard) {
			readInfoFromSdcard();
		}

	}

	private void readInfoFromPhone() {
		FileInputStream fis = null;
		try {
			fis = openFileInput(FILE_IN_PHONE_NAME);
			Scanner scanner = new Scanner(fis);
			StringBuffer sb = new StringBuffer();
			while (scanner.hasNext()) {
				sb.append(scanner.next() + "\r\n");
			}
			scanner.close();
			mInfo.setText(sb.toString());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void readInfoFromSdcard() {
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			File file = new File(Environment.getExternalStorageDirectory()
					.toString()
					+ File.separator
					+ DIR
					+ File.separator
					+ FILE_NAME);
			if(!file.getParentFile().exists()){
				file.mkdirs();
			}
			Scanner scanner = null;
			try {
				scanner = new Scanner(file);
				StringBuffer sb = new StringBuffer();
				while(scanner.hasNext()){
					sb.append(scanner.next());
					
				}
				mInfo.setText(sb.toString());
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				if(scanner != null){
					scanner.close();
				}
			}
		}
	}
}
