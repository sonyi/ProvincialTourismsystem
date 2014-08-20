package com.example.sharedpreferencesdemo;

import android.support.v7.app.ActionBarActivity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity implements OnClickListener{
	private EditText mUser,mPsw;
	private TextView mInfo;
	private Button mSave,mRead;
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mUser = (EditText) findViewById(R.id.et_user);
        mPsw = (EditText) findViewById(R.id.et_psw);
        mInfo = (TextView) findViewById(R.id.tv_info);
        mSave = (Button) findViewById(R.id.btn_save);
        mRead = (Button) findViewById(R.id.btn_read);
        mSave.setOnClickListener(this);
        mRead.setOnClickListener(this);

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
		if(v.getId() == R.id.btn_save){
			saveInfo();
			return;
		}else if(v.getId() == R.id.btn_read){
			readInfo();
		}
		
	}
	
	private void saveInfo(){
		SharedPreferences sp = getSharedPreferences("spUser", MODE_PRIVATE);
		Editor editor = sp.edit();
		String userName = mUser.getText().toString().trim();
		String psw = mPsw.getText().toString().trim();
		editor.putInt("id", 1);
		editor.putString("user", userName);
		editor.putString("psw", psw);
		editor.commit();		
	}
	
	private void readInfo(){
		SharedPreferences sp = getSharedPreferences("spUser", MODE_PRIVATE);
		String user = sp.getString("user", "");
		int id = sp.getInt("id", 0);
		String psw = sp.getString("psw", "");
		mInfo.setText("id:" + id + "\r\nuser:" + user + "\r\npsw:" + psw);
	}
}
