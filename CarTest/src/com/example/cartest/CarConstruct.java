package com.example.cartest;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

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
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.PopupMenu.OnMenuItemClickListener;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cartest.CarConstruct.WaitListViewAdapter.BeginListViewAdapter;
import com.example.model.ConnectionUrl;
import com.example.model.WaitConstruct;
import com.example.model.BeginConstruct;
import com.example.util.ExitApplication;
import com.example.util.JsonUtil;

public class CarConstruct extends Activity implements OnClickListener {

	private ArrayList<WaitConstruct> mWaitConsreuctList;
	private ArrayList<BeginConstruct> mBeginConsreuctList;
	private ListView mWaitListView, mBeginListView;
	private ImageView ivBack;
	private Button btnSee;
	private WaitListViewAdapter waitAdapter;
	private BeginListViewAdapter beginAdapter;
	private String CurState = "待施工";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_car_construct);
		initWidget();
		ExitApplication.getInstance().addActivity(this);
	}

	private void initWidget() {
		ivBack = (ImageView) findViewById(R.id.iv_imgBack);
		ivBack.setOnClickListener(this);
		mWaitListView = (ListView) findViewById(R.id.lv_car_construct_wait);
		mBeginListView = (ListView) findViewById(R.id.lv_car_construct_begin);
		//btnSee=(Button) findViewById(R.id.btn_see_test);
		//btnSee.setOnClickListener(this);
		mWaitConsreuctList = new ArrayList<WaitConstruct>();
		mBeginConsreuctList = new ArrayList<BeginConstruct>();
		int UnitID = 37;
		GetConstructAsyncTask task = new GetConstructAsyncTask(CurState, UnitID);
		task.execute();
		mWaitListView.setOnItemClickListener(new ItemClickListener());
	}
	
	private class ItemClickListener implements OnItemClickListener{

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			Intent intent = new Intent(CarConstruct.this,CarUser.class);
			String carId = mWaitConsreuctList.get(position).CarID;
			Bundle bundle = new Bundle();
			bundle.putString("Id", carId);
			intent.putExtras(bundle);
			startActivity(intent);
		}
	}
	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.iv_imgBack) {
			ExitApplication.getInstance().exit();
		}
//		if(v.getId() == R.id.btn_see_test){
//			Intent intent = new Intent(CarConstruct.this,CarQualityTest.class);
//			startActivity(intent);
//		}
		
	}

	class GetConstructAsyncTask extends
			AsyncTask<String, Void, ArrayList<WaitConstruct>> {

		String CurState;
		int UnitID;

		public GetConstructAsyncTask(String CurState, int UnitID) {
			this.CurState = CurState;
			this.UnitID = UnitID;
		}

		@Override
		protected ArrayList<WaitConstruct> doInBackground(String... params) {
			SoapObject soapObject = new SoapObject(
					ConnectionUrl.targetNameSpace, "GetWorksCurstate");
			soapObject.addProperty("CurState", CurState);
			soapObject.addProperty("UnitID", UnitID);
			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
					SoapEnvelope.VER11);
			envelope.dotNet = true;
			envelope.setOutputSoapObject(soapObject);
			envelope.bodyOut = soapObject;
			envelope.dotNet = true;
			(new MarshalBase64()).register(envelope);
			AndroidHttpTransport httpTranstation = new AndroidHttpTransport(
					ConnectionUrl.fastConstruct);
			httpTranstation.debug = true;
			ArrayList<WaitConstruct> res = null;
			try {
				httpTranstation.call(ConnectionUrl.targetNameSpace
						+ "GetWorksCurstate", envelope);
				// 获取服务器响应返回的SOAP消息
				SoapObject result = (SoapObject) envelope.getResponse();
				String s = result.toString();
				String str = s.substring(s.indexOf("["), s.indexOf("]"));// 获取子字符串（包头不包尾）
				res = onResult(str + "]");

			} catch (IOException e) {
				e.printStackTrace();
			} catch (XmlPullParserException e) {
				e.printStackTrace();
			}
			return res;
		}

		@Override
		protected void onPostExecute(ArrayList<WaitConstruct> result) {
			mWaitConsreuctList = result;
			mWaitListView.setAdapter(new WaitListViewAdapter());
		}
	}

	private ArrayList<WaitConstruct> onResult(String response) {
		ArrayList<WaitConstruct> result = new ArrayList<WaitConstruct>();
		ArrayList<String> arrJsonStr = (ArrayList<String>) JsonUtil
				.toJsonStrList(response);
		for (String json : arrJsonStr) {
			JSONObject jsonObject;
			try {
				jsonObject = new JSONObject(json);
				WaitConstruct waitConstruct = new WaitConstruct();
				waitConstruct.CarID = jsonObject.getString("CarID");
				waitConstruct.WorksDT = jsonObject.getString("WorksDT");
				waitConstruct.WorksMID = jsonObject.getString("WorksMID");
				waitConstruct.WorkUser = jsonObject.getString("WorkUser");
				result.add(waitConstruct);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	class WaitListViewAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return mWaitConsreuctList.size();
		}

		@Override
		public Object getItem(int position) {
			return mWaitConsreuctList.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(final int position, View convertView,
				ViewGroup parent) {
			View view = convertView;
			ViewHolder vh = null;
			if (view == null) {
				view = getLayoutInflater().inflate(
						R.layout.view_car_construct_wait, null);
				vh = new ViewHolder();
				vh.tvName = (TextView) view.findViewById(R.id.tv_car_name_wait);
				vh.tvDate = (TextView) view
						.findViewById(R.id.tv_construct_date_wait);
				vh.tvState = (TextView) view
						.findViewById(R.id.tv_construct_state_wait);
				vh.btnMore = (Button) view.findViewById(R.id.btn_more_wait);

				view.setTag(vh);
			} else {
				vh = (ViewHolder) view.getTag();
			}
			vh.tvName.setText(mWaitConsreuctList.get(position).CarID);
			String s1 = mWaitConsreuctList.get(position).WorksDT;
			String s2 = s1.substring(6, s1.length() - 2);
			long l = Long.parseLong(s2);
			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			Calendar calendar = Calendar.getInstance();
			calendar.setTimeInMillis(l);
			vh.tvDate.setText(formatter.format(calendar.getTime()));
			vh.tvState.setText(CurState);
			vh.btnMore.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {//开始施工
//					String CurState = "施工中";
//					int UnitID = 37;
//					String WorksMID = "3e438064-7cb3-4465-a0db-cff3e7c0f78b";
//					String WorkUser = null;
//					ChangeConstruct changeConctruct = new ChangeConstruct(
//							CurState, UnitID, WorksMID, WorkUser);
//					changeConctruct.execute();
					Toast.makeText(CarConstruct.this, "待施工", Toast.LENGTH_SHORT).show();
				}
			});
			return view;
		}

		class ViewHolder {
			TextView tvName;
			TextView tvDate;
			TextView tvState;
			Button btnMore;
		}

		//删除
		public void delItem(final int position) {
			mWaitConsreuctList.remove(position);
			mWaitListView.setAdapter(new WaitListViewAdapter());
		}

		private class ChangeConstruct extends
				AsyncTask<String, Void, ArrayList<BeginConstruct>> {

			String CurState;
			int UnitID;
			String WorksMID;
			String WorkUser;

			public ChangeConstruct(String curState, int unitID,
					String worksMID, String workUser) {
				super();
				CurState = curState;
				UnitID = unitID;
				WorksMID = worksMID;
				WorkUser = workUser;
			}

			@Override
			protected ArrayList<BeginConstruct> doInBackground(String... params) {
				SoapObject soapObject = new SoapObject(
						ConnectionUrl.targetNameSpace, "SetWorksCurstate");
				soapObject.addProperty("WorksMID", WorksMID);
				soapObject.addProperty("WorkUser", WorkUser);
				soapObject.addProperty("CurState", CurState);
				soapObject.addProperty("UnitID", UnitID);
				SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
						SoapEnvelope.VER11);
				envelope.dotNet = true;
				envelope.setOutputSoapObject(soapObject);
				envelope.bodyOut = soapObject;
				envelope.dotNet = true;
				(new MarshalBase64()).register(envelope);
				AndroidHttpTransport httpTranstation = new AndroidHttpTransport(
						ConnectionUrl.fastConstruct);
				httpTranstation.debug = true;
				ArrayList<BeginConstruct> res = null;
				try {
					httpTranstation.call(ConnectionUrl.targetNameSpace
							+ "SetWorksCurstate", envelope);
					// 获取服务器响应返回的SOAP消息
					SoapObject result = (SoapObject) envelope.getResponse();
					String s = result.toString();
					String str = s.substring(s.indexOf("1"), s.indexOf(";"));// 获取子字符串（包头不包尾）
					int i = Integer.parseInt(str);
					res = toBeginList(i);
					Log.i("-----------", str);
				} catch (IOException e) {
					e.printStackTrace();
				} catch (XmlPullParserException e) {
					e.printStackTrace();
				}
				return res;
			}

			@Override
			protected void onPostExecute(ArrayList<BeginConstruct> result) {
				mBeginConsreuctList = result;
				mBeginListView.setAdapter(new BeginListViewAdapter());
			}
		}

		//待施工传到施工中
		private ArrayList<BeginConstruct> toBeginList(int position) {
			ArrayList<BeginConstruct> arrayList = new ArrayList<BeginConstruct>();
				BeginConstruct beginConstruct = new BeginConstruct();
				beginConstruct.CarID = mWaitConsreuctList.get(position).CarID;
				beginConstruct.WorksDT = mWaitConsreuctList.get(position).WorksDT;
				arrayList.add(beginConstruct);
			
			return arrayList;
		}

		class BeginListViewAdapter extends BaseAdapter {

			@Override
			public int getCount() {
				return mBeginConsreuctList.size();
			}

			@Override
			public Object getItem(int position) {
				return mBeginConsreuctList.get(position);
			}

			@Override
			public long getItemId(int position) {
				return position;
			}

			@Override
			public View getView(final int position, View convertView, ViewGroup parent) {
				View view = convertView;
				final ViewHolder vh ;
				if (view == null) {
					view = getLayoutInflater().inflate(
							R.layout.view_car_construct_begin, null);
					vh = new ViewHolder();
					vh.tvName = (TextView) view
							.findViewById(R.id.tv_car_name_begin);
					vh.tvDate = (TextView) view
							.findViewById(R.id.tv_construct_date_begin);
					vh.tvState = (TextView) view
							.findViewById(R.id.tv_construct_state_begin);
					vh.btnMore = (Button) view
							.findViewById(R.id.btn_more_begin);
					view.setTag(vh);
				} else {
					vh = (ViewHolder) view.getTag();
				}
				vh.tvName.setText(mBeginConsreuctList.get(position).CarID);
				SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");  
				String date = sDateFormat.format(new java.util.Date()); 
				vh.tvDate.setText(date);
				vh.tvState.setText("施工中");
				vh.btnMore.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						PopupMenu menu = new PopupMenu(CarConstruct.this, v);
						menu.inflate(R.menu.main_state_finish);
						menu.setOnMenuItemClickListener(new OnMenuItemClickListener(){

							@Override
							public boolean onMenuItemClick(MenuItem item) {
								switch(item.getItemId()){
								case R.id.action_finish:
									vh.tvState.setText("完成施工");
									break;
								case R.id.action_cancel://取消施工
//									mWaitConsreuctList = toWaitList(position);
//									mWaitListView.setAdapter(new WaitListViewAdapter());
//									if(waitAdapter != null){
//										waitAdapter.notifyDataSetChanged();
//									}
									
									//cancel(position);
									break;
								}
								return false;
							}
							
						});
						menu.show();
					}
				});
				return view;
			}
			//施工中到待施工
			private ArrayList<WaitConstruct> toWaitList(int position) {
				ArrayList<WaitConstruct> arrayList = new ArrayList<WaitConstruct>();
					WaitConstruct waitConstruct = new WaitConstruct();
					waitConstruct.CarID = mBeginConsreuctList.get(position).CarID;
					waitConstruct.WorksDT = mBeginConsreuctList.get(position).WorksDT;
					Log.i("00000000", waitConstruct.WorksDT);
					arrayList.add(waitConstruct);
				
				return arrayList;
			}
			private void cancel(int position){
				mBeginConsreuctList.remove(position);
				mBeginListView.setAdapter(new BeginListViewAdapter());
			}

			class ViewHolder {
				TextView tvName;
				TextView tvDate;
				TextView tvState;
				Button btnMore;
			}
		}
	}
}
