package com.example.sharedprefrencestest;

import android.support.v7.app.ActionBarActivity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity implements OnClickListener{
	private Button  mRead;
	private TextView mTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        saveInfo();
        
        mRead = (Button) findViewById(R.id.btn_read);
        mTextView = (TextView) findViewById(R.id.tv_show);
        mRead.setOnClickListener(this);
        
    }


	private void saveInfo() {
		SharedPreferences sp = getSharedPreferences("share.txt", MODE_PRIVATE);
        Editor editor = sp.edit();
        editor.putInt("id", 1001);
        editor.putString("name", "zhangsan");
        editor.commit();
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
		if(v.getId() == R.id.btn_read){
			showInfo();
		}
		
	}

	private void showInfo() {
		// TODO Auto-generated method stub
		SharedPreferences sp = getSharedPreferences("share.txt", this.MODE_PRIVATE);
		int id = sp.getInt("id", 0);
		String name = sp.getString("name", "");
		mTextView.setText(id + "\r\n" + name);
	}
}
