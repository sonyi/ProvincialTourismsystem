package com.example.bfgfhgfhgf;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.MarshalBase64;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.AndroidHttpTransport;
import org.xmlpull.v1.XmlPullParserException;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.userapply.Combo;
import com.example.userapply.ConnectionUrl;
import com.example.userapply.WashSQLiteHelper;

public class CarSure extends Activity implements OnClickListener {

	boolean ispopFirst = true;
	ArrayList<String> caridlist = new ArrayList<String>();
	ArrayList<People> mData = new ArrayList<People>();
	ArrayList<Combo> list_combo = new ArrayList<Combo>();
	boolean isFirst = true;
	private EditText carnumber;
	private RelativeLayout progress;
	private String unitid;
	private String userid;
	private String employeeName;
	private ListView getsqllistview;
	private SQLiteDatabase mDB;
	private String isrule;
	private TextView sure;
	private ListView getcarlistview;
	private ImageView returnback;
	private TextAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		setContentView(R.layout.activity_car_sure);
		list_combo.clear();
		Intent intents = getIntent();
		unitid = intents.getStringExtra("unitid");
		userid = intents.getStringExtra("userid");
		isrule = intents.getStringExtra("isrule");
		employeeName = intents.getStringExtra("employeeName");
		TextView title = (TextView) findViewById(R.id.secondtitle_tv_theme);
		title.setText("输入车牌号");
		
		returnback = (ImageView) findViewById(R.id.secondtitle_imgBack);
		returnback.setOnClickListener(this);
		getsqllistview = (ListView) findViewById(R.id.getsqllistview);
		getcarlistview = (ListView) findViewById(R.id.getcarlistview);
		carnumber = (EditText) findViewById(R.id.carnumber);
		carnumber.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				TexchangeAsytask textchang = new TexchangeAsytask();
				textchang.execute("");
				
				getcarlistview.setOnItemClickListener(new OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						String employeeNames = caridlist.get(arg2);
						carnumber.setText(employeeNames);
					}
				});
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				
			}
		});
		
		
		
		sure = (TextView) findViewById(R.id.sure);
		progress = (RelativeLayout) findViewById(R.id.progressbar1);
		sure.setOnClickListener(this);
		
//		WashSQLiteHelper helper = new WashSQLiteHelper(CarSure.this);
//		mDB = helper.getWritableDatabase();
		
//		query();
//		MySqladapter adapter = new MySqladapter();
//		getsqllistview.setAdapter(adapter);
	}


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.sure:
			String str = carnumber.getText().toString();
			if(str==null||"".equals(str)||str.equals("")){
				Toast.makeText(CarSure.this, "请输入车牌", Toast.LENGTH_SHORT).show();
			}else{
				String substring = str.substring(2, str.length());
				
				Pattern pattern = Pattern.compile("[\u4e00-\u9fa5]");
				Matcher matcher = pattern.matcher(str);
				boolean thefirst = matcher.find(0);
				
				Pattern pattern1 = Pattern.compile("[a-zA-Z]");
				Matcher matcher1 = pattern1.matcher(str);
				boolean thesecond = matcher1.find(1);
				
				Pattern pattern2 = Pattern.compile("[\u4e00-\u9fa5a-zA-Z\\d]");
				Matcher matcher2 = pattern2.matcher(substring);
				boolean others = matcher2.find();
				
				
				
				if(carnumber.length()<7||carnumber.length()>7){
					carnumber.setError("请输入正确的车牌号");
					return;
				}
				
				if(thefirst&&thesecond&&others){
				}else{
					carnumber.setError("请输入正确的车牌号");
					return;
				}
				
				if(isFirst){
					sure(str);
					isFirst = false;
				}
			}
			break;
			
		case R.id.secondtitle_imgBack:
			finish();
			break;
			
		default:
			break;
		}
	}

	private void sure(String str) {
		progress.setVisibility(View.VISIBLE);
		Intent intent = getIntent();           
		String unitid = intent.getStringExtra("unitid");
		String employeeName = intent.getStringExtra("employeeName");
		MySureasytask sureasytask = new  MySureasytask(unitid,str,employeeName);
		sureasytask.execute("");
	}

	class MySureasytask extends AsyncTask<String, Void , String>{

		String unitid;
		String carid;
		private String string;
		String employeeName;
		public MySureasytask(String unitid, String carid, String employeeName) {
			this.unitid = unitid;
			this.carid = carid;
			this.employeeName = employeeName;
		}

		@Override
		protected String doInBackground(String... params) {
			
			Integer unitids = Integer.valueOf(unitid);
			
			SoapObject soapObject=new SoapObject(ConnectionUrl.fastNameSpace,"SelectCar");
			soapObject.addProperty("UnitID", unitids);
			soapObject.addProperty("CarID", carid);
	        SoapSerializationEnvelope envelope=new SoapSerializationEnvelope(SoapEnvelope.VER11);
	        envelope.dotNet=true;
	        envelope.setOutputSoapObject(soapObject);
	        envelope.bodyOut=soapObject;
	        envelope.dotNet = true;
	        (new MarshalBase64()).register(envelope);
	        AndroidHttpTransport httpTranstation=new AndroidHttpTransport(ConnectionUrl.fastURL);
	        httpTranstation.debug=true;
	        try {
				httpTranstation.call(ConnectionUrl.fastNameSpace+"SelectCar", envelope);
				if(envelope.getResponse()!=null){
					SoapObject result = (SoapObject)envelope.getResponse();
					string = result.getProperty("string").toString();
				}
			} catch (IOException e) {
				e.printStackTrace();
			} catch (XmlPullParserException e) {
				e.printStackTrace();
			}
			return string;
		}
		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			GetUserBasemes getusermess = new GetUserBasemes(result,employeeName);
			getusermess.execute("");
		}
	}
	
	class GetUserBasemes extends AsyncTask<String, Void, String>{

		String carid = carnumber.getText().toString();
		private String usermessage;
		private String cardid;
		String employeeName;

		public GetUserBasemes(String result, String employeeName) {
			this.cardid = result;
			this.employeeName = employeeName;
		}

		@Override
		protected String doInBackground(String... params) {
			Intent intent = getIntent();
			String unitid = intent.getStringExtra("unitid");
			SoapObject soapObject=new SoapObject(ConnectionUrl.fastNameSpace,"GetCoujbzl");
			soapObject.addProperty("unitid", unitid);
			soapObject.addProperty("carid", carid);
	        SoapSerializationEnvelope envelope=new SoapSerializationEnvelope(SoapEnvelope.VER11);
	        envelope.dotNet=true;
	        envelope.setOutputSoapObject(soapObject);
	        envelope.bodyOut=soapObject;
	        envelope.dotNet = true;
	        (new MarshalBase64()).register(envelope);
	        AndroidHttpTransport httpTranstation=new AndroidHttpTransport(ConnectionUrl.fastURL);
	        httpTranstation.debug=true;
	        try {
				httpTranstation.call(ConnectionUrl.fastNameSpace+"GetCoujbzl", envelope);
				if(envelope.getResponse()!=null){
					SoapObject result = (SoapObject)envelope.getResponse();
					if(result.toString()!="anyType{}"){
						usermessage = result.getProperty(0).toString();
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			} catch (XmlPullParserException e) {
				e.printStackTrace();
			}
			return usermessage;
		}
		
		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			Intent intent2 = getIntent();
			String isrule = intent2.getStringExtra("isrule");
			Intent intent = new Intent(CarSure.this,MainActivity.class);
			intent.putExtra("cardid", cardid);//卡号
			intent.putExtra("unitid", unitid);//店号
			intent.putExtra("carid", carid);//车牌号
			intent.putExtra("isrule", isrule);//车牌号
			intent.putExtra("usermessage", result);//用户基本信息
			intent.putExtra("employeeName", employeeName);
			startActivity(intent);
			progress.setVisibility(View.GONE);
			isFirst = true;
			finish();
		}
		
	}
	
	public void query() {
		Cursor cursor = mDB.query("wash", null, null, null,
				null, null, null);
		boolean hasNext = cursor.moveToFirst();
		while (hasNext) {
			String carid = cursor.getString(cursor
					.getColumnIndex("carid"));
			String username = cursor.getString(cursor
					.getColumnIndex("username"));
			String waiter = cursor.getString(cursor
					.getColumnIndex("waiter"));
			People people = new People(carid, username, waiter);
			mData.add(people);
			hasNext = cursor.moveToNext();
		}
		cursor.close();
	}
	
	class People {
		String carid;
		String username;
		String waiter;
		public People(String carid, String username, String waiter) {
			super();
			this.carid = carid;
			this.username = username;
			this.waiter = waiter;
		}
	}
	
	class MySqladapter extends BaseAdapter{

		@Override
		public int getCount() {
			return mData.size();
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View inflate = getLayoutInflater().inflate(R.layout.getsqwash, null);
			TextView carids = (TextView) inflate.findViewById(R.id.carids);
			TextView waiters = (TextView) inflate.findViewById(R.id.waiter);
			TextView usernames = (TextView) inflate.findViewById(R.id.username);
			TextView xuhao = (TextView) inflate.findViewById(R.id.xuhao);
			
			People people = mData.get(position);
			String carid = people.carid;
			String usernamess = people.username;
			String waiter = people.waiter;
			carids.setText(carid);
			waiters.setText(waiter);
			usernames.setText(usernamess);
			xuhao.setText(""+position);
			return inflate;
		}
		
	}
	
	class TexchangeAsytask extends AsyncTask<String, Void, String>{
		int count = 10;
		private String property;
		@Override
		protected String doInBackground(String... params) {
			Intent intent = getIntent();
			String unitid = intent.getStringExtra("unitid");
			caridlist.clear();
			String text = carnumber.getText().toString();
			SoapObject soapObject=new SoapObject(ConnectionUrl.fastNameSpace,"GetCar");
			soapObject.addProperty("CarID", text);
			soapObject.addProperty("count", count);
			soapObject.addProperty("UnitID", unitid);
	        SoapSerializationEnvelope envelope=new SoapSerializationEnvelope(SoapEnvelope.VER11);
	        envelope.dotNet=true;
	        envelope.setOutputSoapObject(soapObject);
	        envelope.bodyOut=soapObject;
	        envelope.dotNet = true;
	        (new MarshalBase64()).register(envelope);
	        AndroidHttpTransport httpTranstation=new AndroidHttpTransport(ConnectionUrl.fastURL);
	        httpTranstation.debug=true;
	        try {
				httpTranstation.call(ConnectionUrl.fastNameSpace+"GetCar", envelope);
				if(envelope.getResponse()!=null){
					SoapObject response = (SoapObject) envelope.getResponse();
					property = response.getProperty(0).toString();
				}
			} catch (IOException e) {
				e.printStackTrace();
			} catch (XmlPullParserException e) {
				e.printStackTrace();
			}
			return property;
		}
		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			try {
				JSONArray jsonArray = new JSONArray(result);
				for (int i = 0; i < jsonArray.length(); i++) {
					JSONObject jsonObject = jsonArray.getJSONObject(i);
					String carid = jsonObject.getString("CarID");
					caridlist.add(carid);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			adapter = new TextAdapter();
			getcarlistview.setAdapter(adapter);
			adapter.notifyDataSetChanged();
		}
	}
	
	
	class TextAdapter extends BaseAdapter{
		
		private View inflate;

		@Override
		public int getCount() {
			return caridlist.size();
		}
		
		@Override
		public Object getItem(int position) {
			return null;
		}
		
		@Override
		public long getItemId(int position) {
			return 0;
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			inflate = getLayoutInflater().inflate(R.layout.employeenamelayout, null);
			if(caridlist.size()!=0){
				TextView employename = (TextView) inflate.findViewById(R.id.employeeNamelist);
				String employeeName = caridlist.get(position);
				employename.setText(employeeName);
			}
			return inflate;
		}
	}
}
