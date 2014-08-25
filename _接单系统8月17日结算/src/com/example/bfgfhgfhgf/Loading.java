package com.example.bfgfhgfhgf;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.MarshalBase64;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.AndroidHttpTransport;
import org.xmlpull.v1.XmlPullParserException;

import com.example.userapply.ConnectionUrl;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Loading extends Activity implements OnClickListener {

	boolean isFirst = true;
	ArrayList<UserMessage> list = new ArrayList<UserMessage>();
	private EditText username;
	private EditText password;
	private TextView login;
	private RelativeLayout progress;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_loading);
		TextView title = (TextView) findViewById(R.id.secondtitle_tv_theme);
		title.setText("欢迎使用车掌柜客户端");
		
		TextView zhuce = (TextView) findViewById(R.id.zhuce);
		zhuce.setText("注册");
		zhuce.setOnClickListener(this);
		
		ImageView iv=(ImageView) this.findViewById(R.id.secondtitle_imgBack);
		iv.setVisibility(View.INVISIBLE);
		
		username = (EditText) findViewById(R.id.username);
		password = (EditText) findViewById(R.id.password);
		progress = (RelativeLayout) findViewById(R.id.progressbar);
		login = (TextView) findViewById(R.id.login);
		login.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.login:
			String user = username.getText().toString();
			String pass = password.getText().toString();
			if(user ==null || "".equals(user)){
				username.setError("账户不能为空");
				return;
			}
			if(pass ==null || "".equals(pass)){
				password.setError("密码不能为空");
				return;
			}
			if(isFirst){
				login();
				isFirst = false;
			}
			break;
		case R.id.zhuce:
			zhuce();
		default:
			break;
		}
	}

	private void zhuce() {
		Intent intent = new Intent(Loading.this, Zhuce.class);
		startActivity(intent);
	}

	private void login() {
		progress.setVisibility(View.VISIBLE);
		String user = username.getText().toString();
		String pass = password.getText().toString();
		Myasytask myasytask = new Myasytask(user,pass);
		myasytask.execute("");
	}

	class Myasytask extends AsyncTask<String, Void, List<UserMessage>>{

		String user;
		String pass;
		private String unitid;
		private String userid;
		private String employeeName;
		private String defaultCard;
		private String isrule;
		public Myasytask(String user, String pass) {
			this.pass = pass;
			this.user = user;
		}

		@Override
		protected List<UserMessage> doInBackground(String... params) {
			list.clear();
			SoapObject soapObject=new SoapObject(ConnectionUrl.serviceNameSpace,"Loadmethod");
			soapObject.addProperty("UserName", user);
			soapObject.addProperty("Pwd", pass);
	        SoapSerializationEnvelope envelope=new SoapSerializationEnvelope(SoapEnvelope.VER11);
	        envelope.dotNet=true;
	        envelope.setOutputSoapObject(soapObject);
	        envelope.bodyOut=soapObject;
	        envelope.dotNet = true;
	        (new MarshalBase64()).register(envelope);
	        AndroidHttpTransport httpTranstation=new AndroidHttpTransport(ConnectionUrl.serviceURL);
	        httpTranstation.debug=true;
	        try {
				httpTranstation.call(ConnectionUrl.serviceNameSpace + "Loadmethod", envelope);
				if(envelope.getResponse()!=null){
					SoapObject result=(SoapObject)envelope.getResponse();
					SoapObject so1=(SoapObject) result.getProperty(0);
					if(!so1.toString().equals("anyType{}")){
						if(so1.hasProperty("unitid")){
							unitid = so1.getProperty("unitid").toString();
						}
						if(so1.hasProperty("userid")){
							userid = so1.getProperty("userid").toString();
						}
						if(so1.hasProperty("EmployeeName")){
							employeeName = so1.getProperty("EmployeeName").toString();
						}
						if(so1.hasProperty("DefaultCard")){
							defaultCard = so1.getProperty("DefaultCard").toString();
						}
						if(so1.hasProperty("IsRule")){
							isrule = so1.getProperty("IsRule").toString();
						}
						UserMessage usermessage = new UserMessage(unitid, userid, employeeName, defaultCard, isrule);
						list.add(usermessage);
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			} catch (XmlPullParserException e) {
				e.printStackTrace();
			}
			return list;
		}
		
		@Override
		protected void onPostExecute(List<UserMessage> result) {
			super.onPostExecute(result);
			if(result.size()==0){
				Toast.makeText(Loading.this, "网络连接异常", Toast.LENGTH_SHORT).show();
				return ;
			}else{
				String unitid = result.get(0).unitid;
				if(unitid==null||"".equals(unitid)||unitid.equals("")){
					Toast.makeText(Loading.this, "账户或密码错误", Toast.LENGTH_SHORT).show();
				}else{
					Intent intent = new Intent(Loading.this,CarSure.class);
					intent.putExtra("unitid", unitid);
					intent.putExtra("userid", result.get(0).userid);
					intent.putExtra("employeeName", result.get(0).employeeName);
					intent.putExtra("isrule", result.get(0).isrule);
					startActivity(intent);
				}
				isFirst = true;
				progress.setVisibility(View.GONE);
			}
		}
		
		
	}
	
	class UserMessage {
		String unitid;
		String userid;
		String employeeName;
		String defaultCard;
		String isrule;
		public UserMessage(String unitid, String userid, String employeeName, String defaultCard, String isrule) {
			super();
			this.isrule = isrule;
			this.unitid = unitid;
			this.userid = userid;
			this.employeeName = employeeName;
			this.defaultCard =defaultCard;
		}
	}

}
