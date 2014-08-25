package com.example.cartest;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.MarshalBase64;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.AndroidHttpTransport;
import org.xmlpull.v1.XmlPullParserException;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.model.ConnectionUrl;
import com.example.util.JsonUtil;

public class CarUser extends Activity {

	private TextView tvCar,tvUser;
	private List<String> strList;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_car_user);
		tvCar=(TextView) findViewById(R.id.tv_car_name);
		tvUser=(TextView) findViewById(R.id.tv_car_user_name);
		Bundle bundle = this.getIntent().getExtras();
		String carId = bundle.getString("Id");
		tvCar.setText(carId);
		int UnitID = 37;
		GetUserTask task = new GetUserTask(UnitID);
		task.execute();
		
	}

	class GetUserTask extends AsyncTask<String, Void, List<String>> {

		int UnitID;

		public GetUserTask(int UnitID) {
			this.UnitID = UnitID;
		}

		@Override
		protected List<String> doInBackground(String... params) {
			SoapObject soapObject = new SoapObject(
					ConnectionUrl.targetNameSpace, "GetUser");
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
			List<String> list = null;
			try {
				httpTranstation.call(ConnectionUrl.targetNameSpace + "GetUser",
						envelope);
				// 获取服务器响应返回的SOAP消息
				SoapObject result = (SoapObject) envelope.getResponse();
				String s = result.toString();
				String str = s.substring(s.indexOf("["), s.indexOf(";"));
				list = onResult(str);
				Log.i("========", str + "");
			} catch (IOException e) {
				e.printStackTrace();
			} catch (XmlPullParserException e) {
				e.printStackTrace();
			}

			return list;
		}
		@Override
		protected void onPostExecute(List<String> result) {
			strList = result;
			
			super.onPostExecute(result);
		}
		
		private List<String> onResult(String response){
			List<String> res = new ArrayList<String>();
			ArrayList<String> arrJsonStr = (ArrayList<String>) JsonUtil
					.toJsonStrList(response);
			for(String json:arrJsonStr){
				JSONObject jsonObject;
				try {
					jsonObject = new JSONObject(json);
					String s = jsonObject.getString("EmployeeName");
					res.add(s);
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
			return res;
		}

	}
}
