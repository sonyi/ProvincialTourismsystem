package com.example.cartest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.MarshalBase64;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.AndroidHttpTransport;
import org.xmlpull.v1.XmlPullParserException;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.model.ConnectionUrl;
import com.example.model.UserMessage;

public class LoginActivity extends Activity implements OnClickListener {

	private boolean isFirst = true;
	private EditText etUserName, etPassword;
	private Button btnLogin;
	ProgressDialog mDialog;
	ArrayList<UserMessage> list = new ArrayList<UserMessage>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		initWidget();
	}

	private void initWidget() {
		etUserName = (EditText) findViewById(R.id.et_user_name);
		etPassword = (EditText) findViewById(R.id.et_password);
		btnLogin = (Button) findViewById(R.id.btn_login);
		btnLogin.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_login:
			String user = etUserName.getText().toString();
			String pass = etPassword.getText().toString();
			if (user == null || "".equals(user)) {
				etUserName.setError("账户不能为空");
				return;
			}
			if (pass == null || "".equals(pass)) {
				etPassword.setError("密码不能为空");
				return;
			}
			if (isFirst) {
				login();
				isFirst = false;
			}
			break;
		}
	}

	private void login() {
		mDialog = ProgressDialog.show(LoginActivity.this, "登录中", "正在玩命加载中...",
				true);
		String user = etUserName.getText().toString().trim();
		String pass = etPassword.getText().toString().trim();
		LoginAsynctask loginTask = new LoginAsynctask(user, pass);
		loginTask.execute();
	}

	private class LoginAsynctask extends
			AsyncTask<String, Void, List<UserMessage>> {
		String user;
		String pass;

		public LoginAsynctask(String user, String pass) {
			this.pass = pass;
			this.user = user;
		}

		@Override
		protected List<UserMessage> doInBackground(String... params) {
			SoapObject soapObject = new SoapObject(
					ConnectionUrl.serviceNameSpace, "Loadmethod");
			soapObject.addProperty("UserName", user);
			soapObject.addProperty("Pwd", pass);
			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
					SoapEnvelope.VER11);
			envelope.dotNet = true;
			envelope.setOutputSoapObject(soapObject);
			envelope.bodyOut = soapObject;
			envelope.dotNet = true;
			(new MarshalBase64()).register(envelope);
			AndroidHttpTransport httpTranstation = new AndroidHttpTransport(
					ConnectionUrl.serviceURL);
			httpTranstation.debug = true;
			try {
				httpTranstation.call(ConnectionUrl.serviceNameSpace
						+ "Loadmethod", envelope);
				if (envelope.getResponse() != null) {
					SoapObject result = (SoapObject) envelope.getResponse();
					SoapObject so1 = (SoapObject) result.getProperty(0);
					if (!so1.toString().equals("anyType{}")) {
						String unitid = so1.getProperty("unitid").toString();
						String userid = so1.getProperty("userid").toString();
						UserMessage usermessage = new UserMessage(unitid,
								userid);
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
			if (result.size() != 0) {
				Intent intent = new Intent(LoginActivity.this,
						CarConstruct.class);
				intent.putExtra("unitid", result.get(0).unitid);
				intent.putExtra("userid", result.get(0).userid);
				startActivity(intent);

			} else {
				Toast.makeText(LoginActivity.this, "账户或密码错误",
						Toast.LENGTH_SHORT).show();
			}
			isFirst = true;
			mDialog.cancel();
		}
	}
}
