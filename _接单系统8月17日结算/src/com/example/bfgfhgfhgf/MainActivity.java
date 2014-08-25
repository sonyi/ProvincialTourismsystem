package com.example.bfgfhgfhgf;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.SlidingDrawer;
import android.widget.TextView;
import android.widget.Toast;

import com.example.userapply.ConnectionUrl;
import com.example.userapply.GetTaocanXiaoFei;
import com.example.userapply.GetXiangmuXiaoFei;
import com.example.userapply.WashSQLiteHelper;
import com.example.userapply.XiangmuSQLiteHelper;
import com.example.userapply.XiaofeiXiangmu;

public class MainActivity extends Activity implements OnClickListener {

	private int mYear;  
	private int mMonth;  
	private int mDay;
	private static final int DATE_DIALOG_ID = 1;
	private static final int DATE_DIALOG_ID2 = 2;
	private static final int SHOW_DATAPICK = 0;
	HashMap<Integer, Boolean> isSelect = new HashMap<Integer, Boolean>();
	private ArrayList<String> employeeNamelist = new ArrayList<String>();//获取项目消费
	private ArrayList<GetXiangmuXiaoFei> getxiangmulist = new ArrayList<GetXiangmuXiaoFei>();//获取项目消费
	private ArrayList<GetXiangmuXiaoFei> getpeijianlist = new ArrayList<GetXiangmuXiaoFei>();//获取配件消费
	private ArrayList<GetTaocanXiaoFei> gettaocanlist = new ArrayList<GetTaocanXiaoFei>();//获取配件消费
	private ArrayList<XiaofeiXiangmu>  getxclist = new ArrayList<XiaofeiXiangmu>();//本店洗车消费消费项目
	private ArrayList<XiaofeiXiangmu> xiangmulist = new ArrayList<XiaofeiXiangmu>();//放于项目类表中的容器,headview
	private HashMap<Integer,XiaofeiXiangmu> submitxiangmulist = new HashMap<Integer,XiaofeiXiangmu>();//放于项目类表中的容器,headview
	private ArrayList<XiaofeiXiangmu> getproxiangmulist = new ArrayList<XiaofeiXiangmu>();//项目消费的容器,（项目消费，配件消费，套餐消费共用的容器）
	private HashMap<Integer,XiaofeiXiangmu> submitproxiangmulist = new HashMap<Integer,XiaofeiXiangmu>();//项目消费的容器,（项目消费，配件消费，套餐消费共用的容器）
	private RelativeLayout hehe;
	private SlidingDrawer mDrawer;
	private TextView ssssss;
	private ListView goutonglistview;
	private SlidingDrawer mDrawer2;
	private ImageView more;
	private boolean isFirst = true;
	private boolean ispopFirst = true;
	private RelativeLayout customermessage;
	private RelativeLayout jibenmessage;
	private RelativeLayout carmessage;
	ArrayList<GetRecordMessage> getrecordlist = new ArrayList<MainActivity.GetRecordMessage>();
	private EditText carid;
	private EditText cartype;
	private EditText curmileage;
	private EditText safelimitdt;
	private EditText cardnumber;
	private EditText customername;
	private EditText driverphone;
	private EditText carid1;
	private EditText position;
	private EditText waiter;
	private EditText premoney;
	private EditText zhekou;
	private String cardid;
	private ListView costlistvView;
	private String carids;
	private String unitid;
	private GridView gridview;
	private String format;
	private ListView xiangmulistview;
	private View headview;
	private EditText edixiangmuxiaofei;
	private ListView xiangmulistview2;
	private XiangmuAdapter xiangmuadapter;
	private TextView washcar;
	private ListView taocanxiche_listview;
	private Dialog taocanzidongbofangDialog;
	private LinearLayout headviewwash;
	private String carname = null;
	private String carType = null;
	private String curMileage = null;
	private String safeLimitDT = null;
	private String cardNum = null;
	private String customerName = null;
	private String mobile = null;
	private String cardName = null;
	private String Position = null;
	private String cardInfID = null;
	private String unitID = null;
	private String carInfoID = null;
	private String cusUnitInfID = null;
	private String addData1 = null;
	private EditText custom_name;
	private EditText custom_othertelephone;
	private EditText custom_come;
	private EditText custom_bitrhday;
	private EditText custom_mobile;
	private EditText custom_otherpeople;
	private EditText custom_fax;
	private EditText cus1tom_workplace;
	private EditText custom_workphone;
	private EditText custom_address;
	private EditText custom_telephone;
	private EditText custom_zip;
	private EditText custom_remark;
	private EditText car_carid;
	private EditText car_carframnum;
	private EditText car_motornum;
	private EditText car_driverPhone;
	private EditText car_cartype;
	private EditText car_driver;
	private EditText car_buydt;
	private EditText car_yearcheckinte;
	private Button commitallmessage;
	private String employeeName;
	private TextView title_lastmoney;
	private String mark;
	private Button jiesuan;
	private String carframNum;
	private String isrule;
	
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
//		从前面获取到对应的数据
		Intent intent = getIntent();
		cardid = intent.getStringExtra("cardid");
		caridss = intent.getStringExtra("carid");
		unitid = intent.getStringExtra("unitid");
		isrule = intent.getStringExtra("isrule");
		employeeName = intent.getStringExtra("employeeName");
		
		Intent intentss = getIntent();
		String usermessage = intentss.getStringExtra("usermessage");
		if(usermessage==null||"".equals(usermessage)||usermessage.equals("")){
		}else{
			Getbasemessage(usermessage);
		}
		
		FindView();
		if(usermessage==null||"".equals(usermessage)||usermessage.equals("")){
			
			carid.setText(caridss);
		}else{
			Log.e("usermessage", usermessage);
			SetBaseMessage();
		}
		
		if("".equals(cardid)||cardid.equals("")||cardid.equals("00000000-0000-0000-0000-000000000000")){
		}else{
//			获取沟通记录
			Record getrecord = new Record();
			getrecord.execute("");
		}
		
		hehe = (RelativeLayout) findViewById(R.id.heheh);
		mDrawer = (SlidingDrawer) findViewById(R.id.c96_drawer);
		mDrawer2 = (SlidingDrawer) findViewById(R.id.slidingDrawer1);
		
//		mmDrawer2的事件
		mDrawer2.setOnDrawerOpenListener(new SlidingDrawer.OnDrawerOpenListener()
		{
			public void onDrawerOpened() {
				if("".equals(cardid)||cardid.equals("")||cardid.equals("00000000-0000-0000-0000-000000000000")){
				}else{
//					获取消费记录
					GetConsuming getconsuming = new GetConsuming();
					getconsuming.execute("");
				}
			}
		});
		
//		判断有没有卡，有卡才显示套餐，没卡直接显示本店洗车
//		返回套餐洗车
//		if("".equals(cardid)||cardid.equals("")){
		GetXCItem getxcItem = new GetXCItem();
    	getxcItem.execute("");
//		}else{
//			GetCardPackagewash getcardpackage = new GetCardPackagewash();
//			getcardpackage.execute("");
//		}
		
		WaiterAsytask waiterasytask = new WaiterAsytask();
		waiterasytask.execute("");
		
	}

	private void SetBaseMessage() {
		if(carids==null||"".equals(carids)){
			carid.setText(carids);
		}else{
			carid.setText(caridss);
		}
		if(carType.equals("null")){
			cartype.setText("");
		}else{
			cartype.setText(carType);
		}
		if(curMileage.equals("null")){
			curmileage.setText("");
		}else{
			curmileage.setText(curMileage);
		}
		
		safelimitdt.setText(format);
		
		if(cardNum.equals("null")){
			cardnumber.setText("");
		}else{
			cardnumber.setText(cardNum);
		}
		if(customerName.equals("null")){
			customername.setText("");
		}else{
			customername.setText(customerName);
		}
		if(mobile.equals("null")){
			driverphone.setText("");
		}else{
			driverphone.setText(mobile);
		}
		if(cardName.equals("null")){
			carid1.setText("");
		}else{
			carid1.setText(cardName);
		}
		if(Position.equals("null")){
			position.setText("");
		}else{
			position.setText(Position);
		}
		if(addData1.equals("null")){
			zhekou.setText("");
		}else{
			zhekou.setText(addData1);
		}
		
	}

	private String GetBasedataMessage() {
		//		获取基本资料
		
				String carids = carid.getText().toString();
				String cartypes = cartype.getText().toString();
				String curmileages = curmileage.getText().toString();
				String safelimitdts = safelimitdt.getText().toString();
				String cardnumbers = cardnumber.getText().toString();
				String customernames = customername.getText().toString();
				String driverphones = driverphone.getText().toString();
				String cardName = carid1.getText().toString();
				String positions = position.getText().toString();
				String zhekous = zhekou.getText().toString();
				String carmotornum = car_motornum.getText().toString();
				String isRule = isrule;
				String waiters = waiter.getText().toString();
				String basedata = "{\"CarID\":\""+carids+"\",\"CarType\":\""+cartypes+"\"," +
						"\"Name\":\""+carname+"\",\"CurMileage\":\""+curmileages+"\"," +
						"\"SafeLimitDT\":\""+safelimitdts+"\",\"CardNum\":\""+cardnumbers+"\"," +
						"\"CustomerName\":\""+customernames+"\",\"Mobile\":\""+driverphones+"\",\"CardName\":\""+cardName+"\"," +
						"\"Position\":\""+positions+"\"," +
						"\"CardInfID\":\""+cardInfID+"\"," +"\"MotorNum\":\""+carmotornum+"\","+
						"\"UnitID\":\""+unitid+"\",\"CarInfoID\":\""+carInfoID+"\"," +
						"\"CusUnitInfID\":\""+cusUnitInfID+"\",\"AddData1\":\""+zhekous+"\"," +
						"\"CarFramNum\":\""+waiters+"\","+"\"IsRule\":\""+isRule+"\"}";
				return basedata;
	}

	private void Getbasemessage(String usermessage) {
		try {
			JSONArray jsonArray = new JSONArray(usermessage);
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				carids = jsonObject.getString("CarID");
				carname = jsonObject.getString("Name");
				carType = jsonObject.getString("CarType");
				curMileage = jsonObject.getString("CurMileage");
				safeLimitDT = jsonObject.getString("SafeLimitDT");
				cardNum = jsonObject.getString("CardNum");
				customerName = jsonObject.getString("CustomerName");
				mobile = jsonObject.getString("Mobile");
				cardName = jsonObject.getString("CardName");
				Position = jsonObject.getString("Position");
				cardInfID = jsonObject.getString("CardInfID");
				unitID = jsonObject.getString("UnitID");
				carInfoID = jsonObject.getString("CarInfoID");
				cusUnitInfID = jsonObject.getString("CusUnitInfID");
				addData1 = jsonObject.getString("AddData1");
				carframNum = jsonObject.getString("CarFramNum");
			}
			if(safeLimitDT=="null"||"".equals(safeLimitDT)||safeLimitDT.equals("")){
				format = "";
			}else{
				int first = safeLimitDT.indexOf("(");
				int last = safeLimitDT.lastIndexOf(")");
				String time = safeLimitDT.substring(first+1,last);
				Date date = new Date(Long.valueOf(time));
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				format = formatter.format(date).substring(0, 10);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	private void FindView() {
		customermessage = (RelativeLayout) this.findViewById(R.id.customermessagelayout);
		jibenmessage = (RelativeLayout) this.findViewById(R.id.jibenmessage);
		carmessage = (RelativeLayout) this.findViewById(R.id.carmessage);
		
		carmessage.setVisibility(View.GONE);
		customermessage.setVisibility(View.GONE);
		
		more = (ImageView) findViewById(R.id.more);
		more.setImageResource(R.drawable.down);
		more.setOnClickListener(this);
		
		carid = (EditText) findViewById(R.id.carid);
		carid.setEnabled(false);
		cartype = (EditText) findViewById(R.id.cartype);
		curmileage = (EditText) findViewById(R.id.curmileage);
		safelimitdt = (EditText) findViewById(R.id.safelimitdt);
		safelimitdt.setOnClickListener(new DateButtonOnClickListener());  
		safelimitdt.setFocusable(false);
		
		
		cardnumber = (EditText) findViewById(R.id.cardnumber);
		cardnumber.setEnabled(false);
		customername = (EditText) findViewById(R.id.customername);
		driverphone = (EditText) findViewById(R.id.driverphone);
		carid1 = (EditText) findViewById(R.id.carid1);
		position = (EditText) findViewById(R.id.position);
		position.setEnabled(false);
		zhekou = (EditText) findViewById(R.id.addData1);
		zhekou.setEnabled(false);
		
		washcar = (TextView) findViewById(R.id.washcar);
		
		xiangmulistview = (ListView) findViewById(R.id.xiangmulist);//项目列表详情
		
		waiter = (EditText) findViewById(R.id.waiter);
		waiter.setOnClickListener(this);
		waiter.setFocusable(false);
		
		premoney = (EditText) findViewById(R.id.premoney);
		title_lastmoney = (TextView) findViewById(R.id.title_lastmoney);
		Button zancun = (Button) findViewById(R.id.zancun);
		zancun.setOnClickListener(this);
		
		
//		获取消费记录
		costlistvView = (ListView) findViewById(R.id.getcost_listview);
//		获取沟通记录
		goutonglistview = (ListView) findViewById(R.id.goutonglistview);
		
		
		
//		共用listview的适配器
		headview = getLayoutInflater().inflate(R.layout.xiangmulayout, null);
		headviewwash = (LinearLayout) headview.findViewById(R.id.headview_wash);
		headview.setVisibility(View.GONE);
		ImageView xiangmujishi = (ImageView) headview.findViewById(R.id.xiangmujishi);
		xiangmujishi.setOnClickListener(this);
		
		xiangmulistview.addHeaderView(headview);
		
		gridview = (GridView) findViewById(R.id.gridView1);
		gridview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				
				XiaofeiXiangmu xiaofeiXiangmu = getxclist.get(arg2);
				xiangmulist.add(0,xiaofeiXiangmu);
				headview.setVisibility(View.VISIBLE);
				LayoutParams lp =headviewwash.getLayoutParams();
				lp.height=50;//设置 LinearLayout的高度
				
				final TextView leibie = (TextView) headview.findViewById(R.id.leibie);
				final TextView xiangmuleibie = (TextView) headview.findViewById(R.id.xiangmuleibie);
				final TextView xiangmunames = (TextView) headview.findViewById(R.id.xiangmumingcheng);
				final EditText xiangmumoneys = (EditText) headview.findViewById(R.id.xiangmumoney);
				final EditText youhuimoney = (EditText) headview.findViewById(R.id.youhuimoney);
				final EditText numbers = (EditText) headview.findViewById(R.id.number);
				final EditText xiangmuzhekou = (EditText) headview.findViewById(R.id.xiangmuzhekou);
				final TextView zhehoumoney = (TextView) headview.findViewById(R.id.zhekoumoney);
				final TextView lastmoney = (TextView) headview.findViewById(R.id.lastmoney);
				
				String leibie2 = xiangmulist.get(0).getLeibie();
				String xiangmuleibie2 = xiangmulist.get(0).getXiangmuleibie();
				String xiangmuname = xiangmulist.get(0).getXiangmuname();
				float xiangmmoney = xiangmulist.get(0).getXiangmmoney();
				float youhui = xiangmulist.get(0).getYouhui();
				int number = xiangmulist.get(0).getNumber();
				int zhekou2 = xiangmulist.get(0).getZhekou();
				String zhehoumoney2 = xiangmulist.get(0).getZhehoumoney();
				String lastmoney2 = xiangmulist.get(0).getLastmoney();
				
				leibie.setText(leibie2);
				xiangmuleibie.setText(xiangmuleibie2);
				xiangmunames.setText(xiangmuname);
				xiangmumoneys.setText(""+xiangmmoney);
				youhuimoney.setText(""+youhui);
				numbers.setText(""+number);
				xiangmuzhekou.setText(""+zhekou2);
				zhehoumoney.setText(""+zhehoumoney2);
				lastmoney.setText(""+lastmoney2);
				
				youhuimoney.addTextChangedListener(new TextWatcher() {

					@Override
					public void afterTextChanged(Editable s) {
					}

					@Override
					public void beforeTextChanged(CharSequence s, int start,
							int count, int after) {
						
					}

					@Override
					public void onTextChanged(CharSequence s, int start,
							int before, int count) {
						String youhuimoneyss = youhuimoney.getText().toString();
						String numberss = numbers.getText().toString();
						String xiangmuzhekouss = xiangmuzhekou.getText().toString();
						
						Pattern pattern = Pattern.compile("[0-9]]");
						Matcher matcher = pattern.matcher(youhuimoneyss);
						Matcher matcher2 = pattern.matcher(numberss);
						Matcher matcher3 = pattern.matcher(xiangmuzhekouss);
						boolean others = matcher.find();
						boolean others2 = matcher2.find();
						boolean others3 = matcher3.find();
						if(others&&others2&&others3){
						}else{
							Toast.makeText(MainActivity.this, "请输入数字", Toast.LENGTH_SHORT).show();
							return;
						}
						
						if("".equals(youhuimoneyss)){
							youhuimoneyss = "0";
						}
						if("".equals(numberss)){
							numberss = "0";
						}
						if("".equals(xiangmuzhekouss)){
							xiangmuzhekouss = "0";
						}
						float intyouhui = Float.valueOf(youhuimoneyss);
						float intnumber = Float.valueOf(numberss);
						float intzhekou = Float.valueOf(xiangmuzhekouss);
						float intmoney = intyouhui*intnumber*intzhekou/100;
						String zhekoumoneyss = new java.text.DecimalFormat("#.00").format(intmoney);
						zhehoumoney.setText(""+zhekoumoneyss);
						
						String serviceItemID = getxclist.get(0).getServiceItemID();
						String leibieq = leibie.getText().toString();
						String comboNameq = xiangmuleibie.getText().toString();
						String itemNameq = xiangmunames.getText().toString();
						String xiangmmoneyq = xiangmumoneys.getText().toString();
						String zhehoumoneyq = zhehoumoney.getText().toString();
						String serviceTimesq = lastmoney.getText().toString();
						float xiangmmoneyp = Float.valueOf(xiangmmoneyq);
						float youhuip = Float.valueOf(youhuimoneyss);
						int numberp = Integer.valueOf(numberss);
						int zhekoup = Integer.valueOf(xiangmuzhekouss);
						
//						更新容器中的数据
						XiaofeiXiangmu xiaofeiXiangmu = new XiaofeiXiangmu(); 
						xiaofeiXiangmu.setLeibie(leibieq);
						xiaofeiXiangmu.setXiangmuleibie(comboNameq);
						xiaofeiXiangmu.setXiangmuname(itemNameq);
						xiaofeiXiangmu.setXiangmmoney(xiangmmoneyp);
						xiaofeiXiangmu.setYouhui(youhuip);
						xiaofeiXiangmu.setNumber(numberp);
						xiaofeiXiangmu.setZhekou(zhekoup);
						xiaofeiXiangmu.setZhehoumoney(zhehoumoneyq);
						xiaofeiXiangmu.setLastmoney(serviceTimesq);
						xiaofeiXiangmu.setServiceItemID(serviceItemID);
						submitxiangmulist.put(0,xiaofeiXiangmu);
					}
					
				});
				
				numbers.addTextChangedListener(new TextWatcher() {

					@Override
					public void afterTextChanged(Editable s) {
					}

					@Override
					public void beforeTextChanged(CharSequence s, int start,
							int count, int after) {
						
					}

					@Override
					public void onTextChanged(CharSequence s, int start,
							int before, int count) {
						String youhuimoneyss = youhuimoney.getText().toString();
						String numberss = numbers.getText().toString();
						String xiangmuzhekouss = xiangmuzhekou.getText().toString();
						
						Pattern pattern = Pattern.compile("[0-9]");
						Matcher matcher = pattern.matcher(youhuimoneyss);
						Matcher matcher2 = pattern.matcher(numberss);
						Matcher matcher3 = pattern.matcher(xiangmuzhekouss);
						boolean others = matcher.find();
						boolean others2 = matcher2.find();
						boolean others3 = matcher3.find();
						if(others&&others2&&others3){
						}else{
							Toast.makeText(MainActivity.this, "请输入数字", Toast.LENGTH_SHORT).show();
							return;
						}
						if("".equals(youhuimoneyss)){
							youhuimoneyss = "0";
						}
						if("".equals(numberss)){
							numberss = "0";
						}
						if("".equals(xiangmuzhekouss)){
							xiangmuzhekouss = "0";
						}
						float intyouhui = Float.valueOf(youhuimoneyss);
						float intnumber = Float.valueOf(numberss);
						float intzhekou = Float.valueOf(xiangmuzhekouss);
						
						float intmoney = intyouhui*intnumber*intzhekou/100;
						String zhekoumoneyss = new java.text.DecimalFormat("#.00").format(intmoney);
						zhehoumoney.setText(""+zhekoumoneyss);
						
						String serviceItemID = getxclist.get(0).getServiceItemID();
						String leibieq = leibie.getText().toString();
						String comboNameq = xiangmuleibie.getText().toString();
						String itemNameq = xiangmunames.getText().toString();
						String xiangmmoneyq = xiangmumoneys.getText().toString();
						String zhehoumoneyq = zhehoumoney.getText().toString();
						String serviceTimesq = lastmoney.getText().toString();
						
						float xiangmmoneyp = Float.valueOf(xiangmmoneyq);
						float youhuip = Float.valueOf(youhuimoneyss);
						int numberp = Integer.valueOf(numberss);
						int zhekoup = Integer.valueOf(xiangmuzhekouss);
						
//						更新容器中的数据
						XiaofeiXiangmu xiaofeiXiangmu = new XiaofeiXiangmu(); 
						xiaofeiXiangmu.setLeibie(leibieq);
						xiaofeiXiangmu.setXiangmuleibie(comboNameq);
						xiaofeiXiangmu.setXiangmuname(itemNameq);
						xiaofeiXiangmu.setXiangmmoney(xiangmmoneyp);
						xiaofeiXiangmu.setYouhui(youhuip);
						xiaofeiXiangmu.setNumber(numberp);
						xiaofeiXiangmu.setZhekou(zhekoup);
						xiaofeiXiangmu.setZhehoumoney(zhehoumoneyq);
						xiaofeiXiangmu.setLastmoney(serviceTimesq);
						xiaofeiXiangmu.setServiceItemID(serviceItemID);
						submitxiangmulist.put(0,xiaofeiXiangmu);
						
					}
					
				});
				
				xiangmuzhekou.addTextChangedListener(new TextWatcher() {

					@Override
					public void afterTextChanged(Editable s) {
					}

					@Override
					public void beforeTextChanged(CharSequence s, int start,
							int count, int after) {
						
					}

					@Override
					public void onTextChanged(CharSequence s, int start,
							int before, int count) {
						String youhuimoneyss = youhuimoney.getText().toString();
						String numberss = numbers.getText().toString();
						String xiangmuzhekouss = xiangmuzhekou.getText().toString();
						
						Pattern pattern = Pattern.compile("[0-9]");
						Matcher matcher = pattern.matcher(youhuimoneyss);
						Matcher matcher2 = pattern.matcher(numberss);
						Matcher matcher3 = pattern.matcher(xiangmuzhekouss);
						boolean others = matcher.find();
						boolean others2 = matcher2.find();
						boolean others3 = matcher3.find();
						if(others&&others2&&others3){
						}else{
							Toast.makeText(MainActivity.this, "请输入数字", Toast.LENGTH_SHORT).show();
							return;
						}
						
						if("".equals(youhuimoneyss)){
							youhuimoneyss = "0";
						}
						if("".equals(numberss)){
							numberss = "0";
						}
						if("".equals(xiangmuzhekouss)){
							xiangmuzhekouss = "0";
						}
						float intyouhui = Float.valueOf(youhuimoneyss);
						float intnumber = Float.valueOf(numberss);
						float intzhekou = Float.valueOf(xiangmuzhekouss);
						
						
						float intmoney = intyouhui*intnumber*intzhekou/100;
						String zhekoumoneyss = new java.text.DecimalFormat("#.00").format(intmoney);
						zhehoumoney.setText(""+zhekoumoneyss);
						
						String serviceItemID = getxclist.get(0).getServiceItemID();
						String leibieq = leibie.getText().toString();
						String comboNameq = xiangmuleibie.getText().toString();
						String itemNameq = xiangmunames.getText().toString();
						String xiangmmoneyq = xiangmumoneys.getText().toString();
						String zhehoumoneyq = zhehoumoney.getText().toString();
						String serviceTimesq = lastmoney.getText().toString();
						
						float xiangmmoneyp = Float.valueOf(xiangmmoneyq);
						float youhuip = Float.valueOf(youhuimoneyss);
						int numberp = Integer.valueOf(numberss);
						int zhekoup = Integer.valueOf(xiangmuzhekouss);
						
//						更新容器中的数据
						XiaofeiXiangmu xiaofeiXiangmu = new XiaofeiXiangmu(); 
						xiaofeiXiangmu.setLeibie(leibieq);
						xiaofeiXiangmu.setXiangmuleibie(comboNameq);
						xiaofeiXiangmu.setXiangmuname(itemNameq);
						xiaofeiXiangmu.setXiangmmoney(xiangmmoneyp);
						xiaofeiXiangmu.setYouhui(youhuip);
						xiaofeiXiangmu.setNumber(numberp);
						xiaofeiXiangmu.setZhekou(zhekoup);
						xiaofeiXiangmu.setZhehoumoney(zhehoumoneyq);
						xiaofeiXiangmu.setLastmoney(serviceTimesq);
						xiaofeiXiangmu.setServiceItemID(serviceItemID);
						submitxiangmulist.put(0,xiaofeiXiangmu);
					}
					
				});
			}
			
		});
		
		
		jiesuan = (Button) findViewById(R.id.jiesuan);
		jiesuan.setOnClickListener(this);
		
		LinearLayout taocanxiaofei = (LinearLayout) findViewById(R.id.taocanxiaofei);
		LinearLayout xiangmuxiaofei = (LinearLayout) findViewById(R.id.xiangmuxiaofei);
		LinearLayout peijianxiaofei = (LinearLayout) findViewById(R.id.peijianxiaofei);
		taocanxiaofei.setOnClickListener(this);
		xiangmuxiaofei.setOnClickListener(this);
		peijianxiaofei.setOnClickListener(this);
//		项目列表
        xiangmuadapter = new XiangmuAdapter();
		xiangmulistview.setAdapter(xiangmuadapter);
		
//		获取客户详细信息
		custom_name = (EditText) findViewById(R.id.custom_name);
		custom_othertelephone = (EditText) findViewById(R.id.custom_othertelephone);
		custom_come = (EditText) findViewById(R.id.custom_come);
		custom_bitrhday = (EditText) findViewById(R.id.custom_bitrhday);
		custom_bitrhday.setOnClickListener(new DateBirthdayOnClickListener());
		custom_bitrhday.setFocusable(false);
		custom_mobile = (EditText) findViewById(R.id.custom_mobile);
		custom_otherpeople = (EditText) findViewById(R.id.custom_otherpeople);
		custom_fax = (EditText) findViewById(R.id.custom_fax);
		cus1tom_workplace = (EditText) findViewById(R.id.custom_workplace);
		custom_workphone = (EditText) findViewById(R.id.custom_workphone);
		custom_address = (EditText) findViewById(R.id.custom_address);
		custom_telephone = (EditText) findViewById(R.id.custom_telephone);
		custom_zip = (EditText) findViewById(R.id.custom_zip);
		custom_remark = (EditText) findViewById(R.id.custom_remark);
		
		car_carid = (EditText) findViewById(R.id.car_carid);
		car_carframnum = (EditText) findViewById(R.id.car_carframnum);
		car_motornum = (EditText) findViewById(R.id.car_motornum);
		car_driverPhone = (EditText) findViewById(R.id.car_driverPhone);
		car_cartype = (EditText) findViewById(R.id.car_cartype);
		car_driver = (EditText) findViewById(R.id.car_driver);
		car_buydt = (EditText) findViewById(R.id.car_buydt);
		car_yearcheckinte = (EditText) findViewById(R.id.car_yearcheckinte);
		commitallmessage = (Button) findViewById(R.id.commitallmessage);
		commitallmessage.setOnClickListener(this);
	}

	public class Myadapter extends BaseAdapter{

		@Override
		public int getCount() {
			if(getrecordlist.size()==0){
				return 1;
			}else{
				return getrecordlist.size();
			}
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
			View inflate = getLayoutInflater().inflate(R.layout.openclass_menu_childplayout2, null);
			TextView name = (TextView) inflate.findViewById(R.id.child);
			TextView content = (TextView) inflate.findViewById(R.id.contetn);
			TextView date = (TextView) inflate.findViewById(R.id.time);
			
			if(getrecordlist.size()==0){
				name.setText("");
				content.setText("暂无数据");
				date.setText("");
			}else{
				String userName = getrecordlist.get(position).userName;
				String conTent = getrecordlist.get(position).content;
				String operData = getrecordlist.get(position).operData;
				name.setText(userName);
				content.setText(conTent);
				date.setText(operData);
			}
			return inflate;
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.taocanxiaofei:
			TaocanXiaofei();
			break;
			
		case R.id.xiangmuxiaofei:
			XiangmuXiaofei();
			break;
			
		case R.id.peijianxiaofei:
			PeijianXiaofei();
			break;
		
		case R.id.more:
			MethodMore();
			break;	
			
		case R.id.taocan_submit:
			Submit();
			break;	
			
		case R.id.xiangmujishi:
			Delete();
			break;	
		case R.id.commitallmessage:
			CommitallMessage();
			break;	
		case R.id.jiesuan:
			JieSuan();
			break;	

		case R.id.zancun:
			Zancun();
			break;
		case R.id.waiter:
			WaiterAsytask waiterasytask = new WaiterAsytask();
			waiterasytask.execute("");
			break;
		default:
			break;
		}
	}


	private void Waiter() {
		if (ispopFirst) {
			View inflate = getLayoutInflater().inflate(R.layout.waiterl, null);
			ListView waiterlistviwe = (ListView) inflate.findViewById(R.id.waiterlistviwe);
			WaiterAdapter adapter = new WaiterAdapter();
			waiterlistviwe.setAdapter(adapter);
			waiterlistviwe.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					String employeeNames = employeeNamelist.get(arg2);
					waiter.setText(employeeNames);
					pop.dismiss();
				}
			});
			
			int width = getWindowManager().getDefaultDisplay().getWidth();
			int height = getWindowManager().getDefaultDisplay().getHeight();
			pop = new PopupWindow(inflate, width*3/4, height*3/4);
//		点击外部区域pop消失，不能直接在new PopupWindow(view, 200, 200， true)中设置
			pop.setTouchable(true);
			pop.setOutsideTouchable(true);
			pop.setBackgroundDrawable(new BitmapDrawable());
			pop.setFocusable(true);
			pop.update();
//		在view控件的正下方不偏移
			pop.showAsDropDown(waiter);
			ispopFirst = false;
		}else{
			if(pop!=null){
				pop.dismiss();
				ispopFirst = true;
			}
		}
	}

	private void Zancun() {
		
		String isup = "0"; 
		if(xiangmulist.size()==0){
			Toast.makeText(MainActivity.this, "无项目信息", Toast.LENGTH_SHORT).show();
		}else{
			boolean xiangmucontainsKey = submitxiangmulist.containsKey(0);//如果为true则有修改
			if(xiangmucontainsKey){
				isup = "1";//是否有修改
				String serviceItemID = xiangmulist.get(0).getServiceItemID();
				String leibie = xiangmulist.get(0).getLeibie();
				String combocardmID = xiangmulist.get(0).getCombocardmID();
				String combocardID = xiangmulist.get(0).getCombocardID();
				XiaofeiXiangmu xiaofeiXiangmu = submitxiangmulist.get(0);
				
				if(leibie.equals("套餐")||leibie.equals("套餐洗车")){
					xiaofeiXiangmu.setIsup(isup);
					xiaofeiXiangmu.setCombocardID(combocardID);
					xiaofeiXiangmu.setCombocardmID(combocardmID);
				}else{
					xiaofeiXiangmu.setServiceItemID(serviceItemID);
				}
				
				xiangmulist.remove(0);
				xiangmulist.add(0, xiaofeiXiangmu);
			}else{
				XiaofeiXiangmu xiaofeiXiangmu = xiangmulist.get(0);
				xiangmulist.remove(0);
				xiaofeiXiangmu.setIsup(isup);
				xiangmulist.add(0,xiaofeiXiangmu);
			}
			insertwash(xiangmulist);
			Toast.makeText(MainActivity.this, "暂存成功", Toast.LENGTH_SHORT).show();
		}
		
		if(getproxiangmulist.size()==0){
		}else{
			for (int i = 0; i < getproxiangmulist.size(); i++) {
				boolean containsKey = submitproxiangmulist.containsKey(i);
				if(containsKey){
					isup = "1";//是否有修改
					String serviceItemID = getproxiangmulist.get(i).getServiceItemID();
					String leibie = getproxiangmulist.get(i).getLeibie();
					String combocardmID = getproxiangmulist.get(i).getCombocardmID();
					String combocardID = getproxiangmulist.get(i).getCombocardID();
					XiaofeiXiangmu xiaofeiXiangmu = submitproxiangmulist.get(i);
					
					if(leibie.equals("套餐")||leibie.equals("套餐洗车")){
						xiaofeiXiangmu.setIsup(isup);
						xiaofeiXiangmu.setCombocardID(combocardID);
						xiaofeiXiangmu.setCombocardmID(combocardmID);
					}else{
						xiaofeiXiangmu.setServiceItemID(serviceItemID);
					}
					getproxiangmulist.remove(i);
					getproxiangmulist.add(i, xiaofeiXiangmu);
					
				}else{
					XiaofeiXiangmu xiaofeiXiangmu = getproxiangmulist.get(i);
					getproxiangmulist.remove(i);
					xiaofeiXiangmu.setIsup(isup);
					getproxiangmulist.add(i,xiaofeiXiangmu);
				}
			}
			insertxiangmu(getproxiangmulist);
		}
		
	}

	private void JieSuan() {
		String isup = "0";
		if(getproxiangmulist.size()==0){
			if(xiangmulist.size()==0){
				Toast.makeText(MainActivity.this, "无项目信息", Toast.LENGTH_SHORT).show();
				return ;
			}else{
				boolean containswashKey = submitxiangmulist.containsKey(0);
				if(containswashKey){
					isup = "1";
					XiaofeiXiangmu xiaofeiXiangmu = submitxiangmulist.get(0);
					xiaofeiXiangmu.setIsup(isup);
					xiangmulist.remove(0);
					xiangmulist.add(xiaofeiXiangmu);
					submitxiangmulist.clear();
				}else{
					XiaofeiXiangmu xiaofeiXiangmu = xiangmulist.get(0);
					xiangmulist.remove(0);
					xiaofeiXiangmu.setIsup(isup);
					xiangmulist.add(0,xiaofeiXiangmu);
				}
			}
		}else{
			
			if(xiangmulist.size()!=0){
				boolean containswashKey = submitxiangmulist.containsKey(0);
				if(containswashKey){
					isup = "1";
					XiaofeiXiangmu xiaofeiXiangmu = submitxiangmulist.get(0);
					xiaofeiXiangmu.setIsup(isup);
					xiangmulist.remove(0);
					xiangmulist.add(xiaofeiXiangmu);
					submitxiangmulist.clear();
				}else{
					XiaofeiXiangmu xiaofeiXiangmu = xiangmulist.get(0);
					xiangmulist.remove(0);
					xiaofeiXiangmu.setIsup(isup);
					xiangmulist.add(0,xiaofeiXiangmu);
				}
			}
			
			for (int i = 0; i < getproxiangmulist.size(); i++) {
				boolean containsKey = submitproxiangmulist.containsKey(i);
				if(containsKey){
					isup = "1";//是否有修改
					String serviceItemID = getproxiangmulist.get(i).getServiceItemID();
					String leibie = getproxiangmulist.get(i).getLeibie();
					String combocardmID = getproxiangmulist.get(i).getCombocardmID();
					String combocardID = getproxiangmulist.get(i).getCombocardID();
					XiaofeiXiangmu xiaofeiXiangmu = submitproxiangmulist.get(i);
					
					if(leibie.equals("套餐")||leibie.equals("套餐洗车")){
						xiaofeiXiangmu.setIsup(isup);
						xiaofeiXiangmu.setCombocardID(combocardID);
						xiaofeiXiangmu.setCombocardmID(combocardmID);
					}else{
						xiaofeiXiangmu.setServiceItemID(serviceItemID);
					}
					getproxiangmulist.remove(i);
					getproxiangmulist.add(i, xiaofeiXiangmu);
					
				}else{
					XiaofeiXiangmu xiaofeiXiangmu = getproxiangmulist.get(i);
					getproxiangmulist.remove(i);
					xiaofeiXiangmu.setIsup(isup);
					getproxiangmulist.add(i,xiaofeiXiangmu);
				}
			}
		}
		
		String data = "[";
		
		if(xiangmulist.size()!=0){
			XiaofeiXiangmu xiaofeiXiangmu = xiangmulist.get(0);
			String leibie = xiaofeiXiangmu.getLeibie();
			String xiangmuleibie = xiaofeiXiangmu.getXiangmuleibie();
			String xiangmuname = xiaofeiXiangmu.getXiangmuname();
			float xiangmmoney = xiaofeiXiangmu.getXiangmmoney();
			float youhuimoneny = xiaofeiXiangmu.getYouhui();
			float number = xiaofeiXiangmu.getNumber();
			float zhekou = xiaofeiXiangmu.getZhekou();
			String isups = xiaofeiXiangmu.getIsup();
			String zhehoumoney = xiaofeiXiangmu.getZhehoumoney();
			
			if(leibie.equals("套餐")||leibie.equals("套餐洗车")){
				mark = xiaofeiXiangmu.getCombocardmID()+","+xiaofeiXiangmu.getCombocardID()
						+","+xiaofeiXiangmu.getBuyType();
			}else{
				mark = xiaofeiXiangmu.getServiceItemID();
			}
			
			data = data + "{\"type\":\""+leibie+"\",\"protype\":\""+xiangmuleibie+"\"," +
				"\"proname\":\""+xiangmuname+"\",\"alsemoney\":\""+xiangmmoney+"\"," +
				"\"isup\":\""+isups+"\",\"money\":\""+youhuimoneny+"\",\"count\":\""+number+"\"," +
				"\"zk\":\""+zhekou+"\",\"amount\":\""+zhehoumoney+"\",\"czr\":\""+""+"\"," +
				"\"mark\":\""+mark+"\"},";
		}
		
		if(getproxiangmulist.size()!=0){
			for (int j = 0; j < getproxiangmulist.size(); j++) {
				XiaofeiXiangmu xiaofeiXiangmu = getproxiangmulist.get(j);
				String leibie = xiaofeiXiangmu.getLeibie();
				String xiangmuleibie = xiaofeiXiangmu.getXiangmuleibie();
				String xiangmuname = xiaofeiXiangmu.getXiangmuname();
				float xiangmmoney = xiaofeiXiangmu.getXiangmmoney();
				float youhuimoneny = xiaofeiXiangmu.getYouhui();
				float number = xiaofeiXiangmu.getNumber();
				float zhekou = xiaofeiXiangmu.getZhekou();
				String isups = xiaofeiXiangmu.getIsup();
				
				if(leibie.equals("套餐")||leibie.equals("套餐洗车")){
					mark = xiaofeiXiangmu.getCombocardmID()+","+xiaofeiXiangmu.getCombocardID()
							+","+xiaofeiXiangmu.getBuyType();
				}else{
					mark = xiaofeiXiangmu.getServiceItemID();
				}
				
				
				String zhehoumoney = xiaofeiXiangmu.getZhehoumoney();
				data = data + "{\"type\":\""+leibie+"\",\"protype\":\""+xiangmuleibie+"\"," +
						"\"proname\":\""+xiangmuname+"\",\"alsemoney\":\""+xiangmmoney+"\"," +
						"\"isup\":\""+isups+"\",\"money\":\""+youhuimoneny+"\",\"count\":\""+number+"\"," +
						"\"zk\":\""+zhekou+"\",\"amount\":\""+zhehoumoney+"\",\"czr\":\""+""+"\"," +
						"\"mark\":\""+mark+"\"},";
			}
		}
		int lastIndexOf = data.lastIndexOf(",");
//		项目表格中的数据
		String table = data.substring(0,lastIndexOf)+"]";
//		用户基本数据
		String database = GetBasedataMessage();
//		提交数据
		SaveorderAsytask saveorder = new SaveorderAsytask(table, database);
		saveorder.execute("");
	}

	private void CommitallMessage() {
//		保存客户详细信息
		SaveCusInfoAsytask savecusinfo = new SaveCusInfoAsytask();
		savecusinfo.execute("");
		
//		保存车辆详细信息
		SaveCarInfoAsytask savecarinfo = new SaveCarInfoAsytask();
		savecarinfo.execute("");
	}

	private void Delete() {
		xiangmulist.clear();
		headview.setVisibility(View.GONE);
		LayoutParams lp =headviewwash.getLayoutParams();
		lp.height=0;//设置 LinearLayout的高度
		xiangmuadapter.notifyDataSetChanged();
	}

	private void Submit() {
//		把选择的数据传给项目容器
		for (int i = 0; i < gettaocanlist.size(); i++) {
			Boolean isselect = isSelect.get(i);
			if(isselect){
				GetTaocanXiaoFei getTaocanXiaoFei = gettaocanlist.get(i);
				String comboName = getTaocanXiaoFei.getComboName();
				String itemName = getTaocanXiaoFei.getItemNam();
				String serviceTimes = getTaocanXiaoFei.getServiceTimes();
				String leibie = "套餐";
				String buyType = getTaocanXiaoFei.getBuyType();
				String combocardID = getTaocanXiaoFei.getCombocardID();
				String combocardmID = getTaocanXiaoFei.getCombocardmID();
				
				float xiangmmoney = 0;
				float youhui = 0;
				int number = 1;
				int zhekou = 0;
				String zhehoumoney= "0";
				
				XiaofeiXiangmu xiaofeiXiangmu = new XiaofeiXiangmu(); 
				xiaofeiXiangmu.setLeibie(leibie);
				xiaofeiXiangmu.setXiangmuleibie(comboName);
				xiaofeiXiangmu.setXiangmuname(itemName);
				xiaofeiXiangmu.setXiangmmoney(xiangmmoney);
				xiaofeiXiangmu.setYouhui(youhui);
				xiaofeiXiangmu.setNumber(number);
				xiaofeiXiangmu.setZhekou(zhekou);
				xiaofeiXiangmu.setZhehoumoney(zhehoumoney);
				xiaofeiXiangmu.setLastmoney(serviceTimes);
				xiaofeiXiangmu.setBuyType(buyType);
				xiaofeiXiangmu.setCombocardID(combocardID);
				xiaofeiXiangmu.setCombocardmID(combocardmID);
				
				getproxiangmulist.add(xiaofeiXiangmu);
			}
		}
		xiangmuadapter.notifyDataSetChanged();
		taocanzidongbofangDialog.dismiss();
	}

	private void PeijianXiaofei() {
		final Dialog zidongbofangDialog = SetDialogMethod("配件消费");
//		Dialog中获取控件的方法----使用dialog的类去.findViewById
		edixiangmuxiaofei = (EditText) zidongbofangDialog.findViewById(R.id.edixiangmuxiaofei);
		xiangmulistview2 = (ListView) zidongbofangDialog.findViewById(R.id.xiangmulistView1);
		
		edixiangmuxiaofei.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				getpeijianlist.clear();
				String string = edixiangmuxiaofei.getText().toString();
				GetSelfittingAsytask peijian = new GetSelfittingAsytask(string);
				peijian.execute("");
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}
			
			@Override
			public void afterTextChanged(Editable s) {
			}
		});
		
		xiangmulistview2.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
//				获取配件
				String serviceItemID = getpeijianlist.get(arg2).getServiceItemID();
				GetfittingAsyTask getfittingasytask = new GetfittingAsyTask(serviceItemID);
				getfittingasytask.execute("");
				zidongbofangDialog.dismiss();
			}
		});
		
	}

	private void XiangmuXiaofei() {
        final Dialog zidongbofangDialog = SetDialogMethod("项目消费");
//		Dialog中获取控件的方法----使用dialog的类去.findViewById
		edixiangmuxiaofei = (EditText) zidongbofangDialog.findViewById(R.id.edixiangmuxiaofei);
		xiangmulistview2 = (ListView) zidongbofangDialog.findViewById(R.id.xiangmulistView1);
		
		edixiangmuxiaofei.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				getxiangmulist.clear();
				String string = edixiangmuxiaofei.getText().toString();
				GetSelProAsytask xiangmu = new GetSelProAsytask(string);
				xiangmu.execute("");
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}
			
			@Override
			public void afterTextChanged(Editable s) {
			}
		});
		
		xiangmulistview2.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
//				获取项目
				String serviceItemID = getxiangmulist.get(arg2).getServiceItemID();
				GetProAsyTask getproasytask = new GetProAsyTask(serviceItemID);
				getproasytask.execute("");
				zidongbofangDialog.dismiss();
			}
		});
		
	}
//通过项目的id获取项目内容
	class GetProAsyTask extends AsyncTask<String, Void, String>{

		String proid;
		private String string;
		public GetProAsyTask(String serviceItemID) {
			this.proid = serviceItemID;
		}

		@Override
		protected String doInBackground(String... params) {
			SoapObject soapObject=new SoapObject(ConnectionUrl.fastNameSpace,"GetPro");
			soapObject.addProperty("Proid", proid);
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
				httpTranstation.call(ConnectionUrl.fastNameSpace+"GetPro", envelope);
				if(envelope.getResponse()!=null){
					SoapObject result = (SoapObject) envelope.getResponse();
					string = result.getProperty(0).toString();
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
			JSONObject jsonObject;
			try {
				jsonObject = new JSONObject(result);
				String itemName = jsonObject.getString("ItemName");
				String itemType = jsonObject.getString("ItemType");
				float servicePrice = Float.valueOf(jsonObject.getString("ServicePrice"));
				String saleAmount = jsonObject.getString("SaleAmount");
				int number = 1;
				String leibie = "项目";
				int xiangmuzhekou = 0;
				String zhekous = zhekou.getText().toString();
				if("null".equals(zhekous)||"".equals(zhekous)||zhekous.equals("")){
					xiangmuzhekou = 100;
				}else{
					xiangmuzhekou = Integer.valueOf(zhekous);
				}
				String jishi = "";
				
				float zhehoumoney = xiangmuzhekou/100*servicePrice*number;
				String zhekoumoney = new java.text.DecimalFormat("#.00").format(zhehoumoney);
				XiaofeiXiangmu xiaofeixiangmu = new XiaofeiXiangmu();
				xiaofeixiangmu.setLeibie(leibie);
				xiaofeixiangmu.setXiangmuleibie(itemType);
				xiaofeixiangmu.setXiangmuname(itemName);
				xiaofeixiangmu.setXiangmmoney(servicePrice);
				xiaofeixiangmu.setYouhui(servicePrice);
				xiaofeixiangmu.setNumber(number);
				xiaofeixiangmu.setZhekou(xiangmuzhekou);
				xiaofeixiangmu.setZhehoumoney(zhekoumoney);
				xiaofeixiangmu.setJishi(jishi);
				xiaofeixiangmu.setLastmoney(saleAmount);
				xiaofeixiangmu.setServiceItemID(proid);
				
				getproxiangmulist.add(xiaofeixiangmu);
			    xiangmuadapter.notifyDataSetChanged();
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	private Dialog SetDialogMethod(String str) {
		//		弹出对话框，在对话框中显示
				DisplayMetrics dm = new DisplayMetrics();
				getWindowManager().getDefaultDisplay().getMetrics(dm);
				int widthPixels = dm.widthPixels*2/3;//宽度
				int height = dm.heightPixels*2/3;
				
				Dialog zidongbofangDialog = new Dialog(MainActivity.this); 
				zidongbofangDialog.setTitle(str);
				zidongbofangDialog.show(); 
				zidongbofangDialog.getWindow().setGravity(Gravity.CENTER); 
				zidongbofangDialog.getWindow().setLayout(widthPixels, height); 
				zidongbofangDialog.getWindow().setContentView(R.layout.manhua_dialog_zidongbofang);
		return zidongbofangDialog;
	}

	private void TaocanXiaofei() {
//		弹出对话框，在对话框中显示
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		int widthPixels = dm.widthPixels*2/3;//宽度
		int height = dm.heightPixels*2/3;
		
		taocanzidongbofangDialog = new Dialog(MainActivity.this); 
		taocanzidongbofangDialog.setTitle("套餐消费");
		taocanzidongbofangDialog.show(); 
		taocanzidongbofangDialog.getWindow().setGravity(Gravity.CENTER); 
		taocanzidongbofangDialog.getWindow().setLayout(widthPixels, height); 
		taocanzidongbofangDialog.getWindow().setContentView(R.layout.taocanxiche_layout);
		
		taocanxiche_listview = (ListView) taocanzidongbofangDialog.findViewById(R.id.taocanxiche_listview);
		Button taocan_submit = (Button) taocanzidongbofangDialog.findViewById(R.id.taocan_submit);
		taocan_submit.setOnClickListener(this);
		taocanxiche_listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
//				如果选择框被勾选  则把数据放在容器中
				CheckBox checkbox = (CheckBox) arg1.findViewById(R.id.checkBox1);
				if(isSelect.get(arg2) == true){
					checkbox.setChecked(false);
					isSelect.put(arg2, false);
				}else{
					checkbox.setChecked(true);
					isSelect.put(arg2, true);
				}
			}
		});
		
		
		GetCardPackage getcardpackage = new GetCardPackage();
		getcardpackage.execute("");
		
	}

	private void MethodMore() {
		
//		获取客户详细信息
		GetCusInfoAsyncTask getcusInfo = new GetCusInfoAsyncTask();
		getcusInfo.execute("");
		
		
//		获取车辆详细信息
		GetCarInfoAsyncTask getcarInfo = new GetCarInfoAsyncTask();
		getcarInfo.execute("");
		
		LayoutParams p = jibenmessage.getLayoutParams();
		if (isFirst) {
			p.height = 700;
			
			carmessage.setVisibility(View.VISIBLE);
			customermessage.setVisibility(View.VISIBLE);
			more.setImageResource(R.drawable.up);
			isFirst = false;
		}else{
			p.height = 250;
			
			carmessage.setVisibility(View.GONE);
			customermessage.setVisibility(View.GONE);
			more.setImageResource(R.drawable.down);
			isFirst = true;
		}
		jibenmessage.setLayoutParams(p);
	}
	
//	通过拼音获取消费项目
	class GetSelProAsytask extends AsyncTask<String, Void, String>{

		String itemNamePY;
		int count = 10;
		private String property;
		public GetSelProAsytask(String string) {
			this.itemNamePY = string;
		}

		@Override
		protected String doInBackground(String... params) {
			SoapObject soapObject=new SoapObject(ConnectionUrl.fastNameSpace,"GetSelPro");
			soapObject.addProperty("ItemNamePY", itemNamePY);
			soapObject.addProperty("count", count);
			soapObject.addProperty("Unitid", unitid);
	        SoapSerializationEnvelope envelope=new SoapSerializationEnvelope(SoapEnvelope.VER11);
	        envelope.dotNet=true;
	        envelope.setOutputSoapObject(soapObject);
	        envelope.bodyOut=soapObject;
	        envelope.dotNet = true;
	        (new MarshalBase64()).register(envelope);
	        AndroidHttpTransport httpTranstation=new AndroidHttpTransport(ConnectionUrl.fastURL);
	        httpTranstation.debug=true;
	        try {
				httpTranstation.call(ConnectionUrl.fastNameSpace+"GetSelPro", envelope);
				if(envelope.getResponse()!=null){
					SoapObject result = (SoapObject) envelope.getResponse();
					property = result.getProperty(0).toString();
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
				for (int j = 0; j < jsonArray.length(); j++) {
					JSONObject jsonObject = jsonArray.getJSONObject(j);
					String serviceItemID = jsonObject.getString("ServiceItemID");
					String itemName = jsonObject.getString("ItemName");
					String itemType = jsonObject.getString("ItemType");
					String servicePrice = jsonObject.getString("ServicePrice");
					String serviceItemID1 = jsonObject.getString("ServiceItemID1");
					
					GetXiangmuXiaoFei getxiangmu = new GetXiangmuXiaoFei();
					getxiangmu.setServiceItemID(serviceItemID);
					getxiangmu.setItemName(itemName);
					getxiangmu.setItemType(itemType);
					getxiangmu.setServicePrice(servicePrice);
					getxiangmu.setServiceItemID1(serviceItemID1);
					getxiangmulist.add(getxiangmu);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			XiangmuListadapter adapter = new XiangmuListadapter(getxiangmulist);
			xiangmulistview2.setAdapter(adapter);
		}
	}
	
	class XiangmuListadapter extends BaseAdapter{
		
		ArrayList<GetXiangmuXiaoFei> getxiangmulist ;
		public XiangmuListadapter(ArrayList<GetXiangmuXiaoFei> getxiangmulist) {
			this.getxiangmulist = getxiangmulist;
		}

		@Override
		public int getCount() {
			return getxiangmulist.size();
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
			View inflate = getLayoutInflater().inflate(R.layout.xiangmuxiaofei_listview, null);
			TextView xiangmutextView1 = (TextView) inflate.findViewById(R.id.xiangmutextView1);
			GetXiangmuXiaoFei getXiangmuXiaoFei = getxiangmulist.get(position);
			String itemName = getXiangmuXiaoFei.getItemName();
			xiangmutextView1.setText(itemName);
			return inflate;
		}
		
	}
	
//	获取沟通记录
	class Record extends AsyncTask<String, Void, List<GetRecordMessage>>{

		private String userName;
		private String content;
		private String operData;
		private String serverDT;
		private String yearCheckDT;
		private String safeLimitDT;
		private String buyDT;
		
		@Override
		protected List<GetRecordMessage> doInBackground(String... params) {
			getrecordlist.clear();
			Intent intent = getIntent();
			String unitid = intent.getStringExtra("unitid");
			String carid = intent.getStringExtra("carid");
			SoapObject soapObject=new SoapObject(ConnectionUrl.serviceNameSpace,"GetRecord");
			soapObject.addProperty("unitid", unitid);
			soapObject.addProperty("cardid", carid);
	        SoapSerializationEnvelope envelope=new SoapSerializationEnvelope(SoapEnvelope.VER11);
	        envelope.dotNet=true;
	        envelope.setOutputSoapObject(soapObject);
	        envelope.bodyOut=soapObject;
	        envelope.dotNet = true;
	        (new MarshalBase64()).register(envelope);
	        AndroidHttpTransport httpTranstation=new AndroidHttpTransport(ConnectionUrl.serviceURL);
	        httpTranstation.debug=true;
	        try {
				httpTranstation.call(ConnectionUrl.serviceNameSpace+"GetRecord", envelope);
				if(envelope.getResponse()!=null){
					SoapObject result = (SoapObject) envelope.getResponse();
					if(result.getProperty(0).toString()!="anyType{}"){
						for (int i = 0; i < result.getPropertyCount(); i++) {
							SoapObject so2 = (SoapObject) result.getProperty(i);
							if(!so2.toString().equals("anyType{}")){
								if(so2.hasProperty("UserName")){
									if(so2.getProperty("UserName")==null||"".equals(so2.getProperty("UserName").toString())){
										userName = "暂无数据";
									}else{
										userName = so2.getProperty("UserName").toString();
									}
								}
								
								if(so2.hasProperty("Content")){
									if(so2.getProperty("Content")==null||"".equals(so2.getProperty("Content").toString())){
										content = "暂无数据";
									}else{
										content = so2.getProperty("Content").toString();
									}
								}
								if(so2.hasProperty("OperData")){
									if(so2.getProperty("OperData")==null||"".equals(so2.getProperty("OperData").toString())){
										operData = "暂无数据";
									}else{
										operData = so2.getProperty("OperData").toString();
									}
								}
								GetRecordMessage getrecord = new GetRecordMessage(userName, content, operData);
								getrecordlist.add(getrecord);
							}
							
						}
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			} catch (XmlPullParserException e) {
				e.printStackTrace();
			}
	        
			return getrecordlist;
		}
		
		@Override
		protected void onPostExecute(List<GetRecordMessage> result) {
		super.onPostExecute(result);
		
			Myadapter adapter = new Myadapter();
			goutonglistview.setAdapter(adapter);
			
		}
	}
//	沟通记录的数据处理
	class GetRecordMessage{
		String userName;
		String content;
		String operData;
		public GetRecordMessage(String userName, String content, String operData) {
			super();
			this.userName = userName;
			this.content = content;
			this.operData = operData;
		}
	}
	
//	
	class RecordAdapetr extends BaseAdapter{

		private View inflate;
		private TextView costname;
		private TextView costcost;
		private TextView costtime;
		private List<BuyRecords> buyTypes;
		
		public RecordAdapetr(List<BuyRecords> result) {
			this.buyTypes = result;
		}

		@Override
		public int getCount() {
			if (buyTypes.size()==0) {
				return 1;
			}else{
				return buyTypes.size();
			}
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
			inflate = getLayoutInflater().inflate(R.layout.zhangguirecord, null);
			costname = (TextView) inflate.findViewById(R.id.costrecord_name);
			costcost = (TextView) inflate.findViewById(R.id.costrecord_cost);
			costtime = (TextView) inflate.findViewById(R.id.costdrecord_money);
			
			if(buyTypes.size()==0){
				costcost.setText("暂无数据");
			}else{
				String buytypesname = buyTypes.get(position).itemName;
				String buytypescost = buyTypes.get(position).money;
				String balanceDt = buyTypes.get(position).buyDate;
				
				costname.setText(buytypesname);
				costcost.setText(buytypescost);
				costtime.setText(balanceDt.substring(0, 10));
			}
			return inflate;
		}
		
	}
	
	class GetConsuming extends AsyncTask<String, Void, String>{

		private ArrayList<BuyRecords> buylist = new ArrayList<BuyRecords>();
		private String buytypes;
		private String sumAmount;
		private String balanceDt;
		private String results;
		private String format;

		@Override
		protected String doInBackground(String... params) {
			SoapObject soapObject=new SoapObject(ConnectionUrl.fastNameSpace,"GetBalanceHistory");
			soapObject.addProperty("carid", carids);
			soapObject.addProperty("unitid", unitid);
	        SoapSerializationEnvelope envelope=new SoapSerializationEnvelope(SoapEnvelope.VER11);
	        envelope.dotNet=true;
	        envelope.setOutputSoapObject(soapObject);
	        envelope.bodyOut=soapObject;
	        envelope.dotNet = true;
	        (new MarshalBase64()).register(envelope);
	        AndroidHttpTransport httpTranstation=new AndroidHttpTransport(ConnectionUrl.fastURL);
	        httpTranstation.debug=true;
	        try {
				httpTranstation.call(ConnectionUrl.fastNameSpace+"GetBalanceHistory", envelope);
				if(envelope.getResponse()!=null){
					SoapObject result = (SoapObject) envelope.getResponse();
					if(result.toString()!="anyType{}"){
						String property = result.getProperty(0).toString();
						results = property.toString();
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			} catch (XmlPullParserException e) {
				e.printStackTrace();
			}
			return results;
		}
		
		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			try {
				JSONArray jsonArray = new JSONArray(result);
				for (int j = 0; j < jsonArray.length(); j++) {
					JSONObject jsonObject = jsonArray.getJSONObject(j);
					String worksDT = jsonObject.getString("WorksDT");
					String itemName = jsonObject.getString("ItemName");
					String money = jsonObject.getString("Money");
					
					if(worksDT.equals("null")||"".equals(worksDT)||worksDT.equals("")){
					}else{
//					json时间格式的转换
						int first = worksDT.indexOf("(");
						int last = worksDT.lastIndexOf(")");
						String time = worksDT.substring(first+1,last);
						
						Date date = new Date(Long.valueOf(time));
						SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						format = formatter.format(date).substring(0, 10);
					}
					BuyRecords buyrecord = new BuyRecords(format, itemName, money); 
					buylist.add(buyrecord);
					
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			RecordAdapetr costrecord = new RecordAdapetr(buylist);
			costlistvView.setAdapter(costrecord);
		}
	}

	class BuyRecords {
		String buyDate ;
		String itemName ;
		String money ;
		public BuyRecords(String buyDate, String itemName, String money) {
			super();
			this.buyDate = buyDate;
			this.itemName = itemName;
			this.money = money;
		}
	}
	
//	获取套餐洗车 
	class GetCardPackagewash extends AsyncTask<String, Void, String>{

		private String results;

		@Override
		protected String doInBackground(String... params) {
			int unitids = Integer.valueOf(unitid);
			SoapObject soapObject=new SoapObject(ConnectionUrl.fastNameSpace,"GetCardPackagewash");
			soapObject.addProperty("CardInfIDStr", cardid);
			soapObject.addProperty("UnitID", unitids);
	        SoapSerializationEnvelope envelope=new SoapSerializationEnvelope(SoapEnvelope.VER11);
	        envelope.dotNet=true;
	        envelope.setOutputSoapObject(soapObject);
	        envelope.bodyOut=soapObject;
	        envelope.dotNet = true;
	        (new MarshalBase64()).register(envelope);
	        AndroidHttpTransport httpTranstation=new AndroidHttpTransport(ConnectionUrl.fastURL);
	        httpTranstation.debug=true;
	        try {
	        	httpTranstation.call(ConnectionUrl.fastNameSpace+"GetCardPackagewash", envelope);
				if(envelope.getResponse()!=null){
					SoapObject result = (SoapObject) envelope.getResponse();
					results = result.getProperty(0).toString();
				}
			} catch (IOException e) {
				e.printStackTrace();
			} catch (XmlPullParserException e) {
				e.printStackTrace();
			}
			return results;
		}
		@Override
		protected void onPostExecute(String result) {
		super.onPostExecute(result);
//		如果套餐有洗车项则不显示快速洗车
//		如果套餐没有洗车项则显示快速洗车
		    if(result == null||"".equals(result)||result.equals("")){
		    	GetXCItem getxcItem = new GetXCItem();
		    	getxcItem.execute("");
		    }else{
		    	try {
					JSONArray jsonArray = new JSONArray(result);
					for (int i = 0; i < jsonArray.length(); i++) {
						JSONObject jsonObject = jsonArray.getJSONObject(i);
						String itemName = jsonObject.getString("ItemName");
						String itemType = jsonObject.getString("ComboName");
						float servicePrice = 0;
						String saleAmount = jsonObject.getString("ServiceTimes");
						String combocardID = jsonObject.getString("ComboCardID");
						String combocardmID = jsonObject.getString("ComboCardMID");
						String buyType = jsonObject.getString("BuyType");
						
						int number = 1;
						String leibie = "套餐洗车";
						int xiangmuzhekou = 0;
						String jishi = "";
						
						float zhehoumoney = xiangmuzhekou/100*servicePrice*number;
						String zhekoumoney = new java.text.DecimalFormat("#.00").format(zhehoumoney);
						XiaofeiXiangmu xiaofeixiangmu = new XiaofeiXiangmu();
						xiaofeixiangmu.setLeibie(leibie);
						xiaofeixiangmu.setXiangmuleibie(itemType);
						xiaofeixiangmu.setXiangmuname(itemName);
						xiaofeixiangmu.setXiangmmoney(servicePrice);
						xiaofeixiangmu.setYouhui(servicePrice);
						xiaofeixiangmu.setNumber(number);
						xiaofeixiangmu.setZhekou(xiangmuzhekou);
						xiaofeixiangmu.setZhehoumoney(zhekoumoney);
						xiaofeixiangmu.setJishi(jishi);
						xiaofeixiangmu.setLastmoney(saleAmount);
						xiaofeixiangmu.setBuyType(buyType);
						xiaofeixiangmu.setCombocardmID(combocardmID);
						xiaofeixiangmu.setCombocardID(combocardID);
						getxclist.add(xiaofeixiangmu);
					}
				} catch (JSONException e) {
					e.printStackTrace();
				} 
		    	washcar.setText("套餐洗车项");
		    	GridViewAdapterss adapter = new GridViewAdapterss(getxclist);
				gridview.setAdapter(adapter);
		    }
		}
	}
	
	
	class GetXCItem extends AsyncTask<String, Void, String>{

		private String property;
		@Override
		protected String doInBackground(String... params) {
			SoapObject soapObject=new SoapObject(ConnectionUrl.fastNameSpace,"GetXCItem");
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
				httpTranstation.call(ConnectionUrl.fastNameSpace+"GetXCItem", envelope);
				if(envelope.getResponse()!=null){
					SoapObject result = (SoapObject) envelope.getResponse();
					property = result.getProperty(0).toString();
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
					String itemName = jsonObject.getString("ItemName");
					String itemType = jsonObject.getString("ItemType");
					float servicePrice = Float.valueOf(jsonObject.getString("ServicePrice"));
					String saleAmount = jsonObject.getString("SaleAmount");
					String serviceItemID = jsonObject.getString("ServiceItemID");
					int number = 1;
					String leibie = "洗车";
					int xiangmuzhekou = 0;
					String zhekous = zhekou.getText().toString();
					if("null".equals(zhekous)||"".equals(zhekous)||zhekous.equals("")){
						xiangmuzhekou = 100;
					}else{
						xiangmuzhekou = Integer.valueOf(zhekous);
					}
					String jishi = "";
					
					float zhehoumoney = xiangmuzhekou/100*servicePrice*number;
					String zhekoumoney = new java.text.DecimalFormat("#.00").format(zhehoumoney);
					XiaofeiXiangmu xiaofeixiangmu = new XiaofeiXiangmu();
					xiaofeixiangmu.setLeibie(leibie);
					xiaofeixiangmu.setXiangmuleibie(itemType);
					xiaofeixiangmu.setXiangmuname(itemName);
					xiaofeixiangmu.setXiangmmoney(servicePrice);
					xiaofeixiangmu.setYouhui(servicePrice);
					xiaofeixiangmu.setNumber(number);
					xiaofeixiangmu.setZhekou(xiangmuzhekou);
					xiaofeixiangmu.setZhehoumoney(zhekoumoney);
					xiaofeixiangmu.setJishi(jishi);
					xiaofeixiangmu.setLastmoney(saleAmount);
					xiaofeixiangmu.setServiceItemID(serviceItemID);
					getxclist.add(xiaofeixiangmu);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			} 
			GridViewAdapterss adapter = new GridViewAdapterss(getxclist);
			gridview.setAdapter(adapter);
		}
	}
	
	class GridViewAdapterss extends BaseAdapter{
		
		ArrayList<XiaofeiXiangmu> list ;
		public GridViewAdapterss(ArrayList<XiaofeiXiangmu> getxclist) {
			this.list=getxclist;
		}

		@Override
		public int getCount() {
			return list.size();
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
			View inflate = getLayoutInflater().inflate(R.layout.gridview_get, null);
			TextView itemnames = (TextView) inflate.findViewById(R.id.itemnames);
			XiaofeiXiangmu xiaofeiXiangmu = list.get(position);
			String itemname = xiaofeiXiangmu.getXiangmuname();
			itemnames.setText(itemname);
			return inflate;
		}
		
	}
//	项目列表的显示
	class XiangmuAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			return getproxiangmulist.size();
			
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
		public View getView(final int position, View convertView, ViewGroup parent) {
			View inflate = getLayoutInflater().inflate(R.layout.xiangmulayout2, null);
			final TextView leibie = (TextView) inflate.findViewById(R.id.leibie);
			final TextView xiangmuleibie = (TextView) inflate.findViewById(R.id.xiangmuleibie);
			final TextView xiangmunames = (TextView) inflate.findViewById(R.id.xiangmumingcheng);
			final EditText xiangmumoneys = (EditText) inflate.findViewById(R.id.xiangmumoney);
			final EditText youhuimoney = (EditText) inflate.findViewById(R.id.youhuimoney);
			final EditText numbert = (EditText) inflate.findViewById(R.id.number);
			final EditText xiangmuzhekou = (EditText) inflate.findViewById(R.id.xiangmuzhekou);
			final TextView zhehoumoney = (TextView) inflate.findViewById(R.id.zhekoumoney);
			final TextView lastmoney = (TextView) inflate.findViewById(R.id.lastmoney);
			ImageView xiangmu_imageview = (ImageView) inflate.findViewById(R.id.xiangmu_imageview);
			
			xiangmu_imageview.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					getproxiangmulist.remove(position);
					xiangmuadapter.notifyDataSetChanged();
				}
			});
			
			String leibie2 = getproxiangmulist.get(position).getLeibie();
			if(leibie2.equals("套餐")){
				xiangmumoneys.setEnabled(false);
				youhuimoney.setEnabled(false);
				numbert.setEnabled(false);
				xiangmuzhekou.setEnabled(false);
				zhehoumoney.setEnabled(false);
			}
			
			String xiangmuleibie2 = getproxiangmulist.get(position).getXiangmuleibie();
			String xiangmuname = getproxiangmulist.get(position).getXiangmuname();
			float xiangmmoney = getproxiangmulist.get(position).getXiangmmoney();
			float youhui = getproxiangmulist.get(position).getYouhui();
			int number = getproxiangmulist.get(position).getNumber();
			int zhekou2 = getproxiangmulist.get(position).getZhekou();
			String zhehoumoney2 = getproxiangmulist.get(position).getZhehoumoney();
			String lastmoney2 = getproxiangmulist.get(position).getLastmoney();
			
			leibie.setText(leibie2);
			xiangmuleibie.setText(xiangmuleibie2);
			xiangmunames.setText(xiangmuname);
			xiangmumoneys.setText(""+xiangmmoney);
			youhuimoney.setText(""+youhui);
			numbert.setText(""+number);
			xiangmuzhekou.setText(""+zhekou2);
			zhehoumoney.setText(""+zhehoumoney2);
			lastmoney.setText(""+lastmoney2);
			
			
			youhuimoney.addTextChangedListener(new TextWatcher() {

				@Override
				public void afterTextChanged(Editable s) {
				}

				@Override
				public void beforeTextChanged(CharSequence s, int start,
						int count, int after) {
					
				}

				@Override
				public void onTextChanged(CharSequence s, int start,
						int before, int count) {
					String youhuimoneyss = youhuimoney.getText().toString();
					String numberss = numbert.getText().toString();
					String xiangmuzhekouss = xiangmuzhekou.getText().toString();
					if("".equals(youhuimoneyss)){
						youhuimoneyss = "0";
					}
					if("".equals(numberss)){
						numberss = "0";
					}
					if("".equals(xiangmuzhekouss)){
						xiangmuzhekouss = "0";
					}
					float intyouhui = Float.valueOf(youhuimoneyss);
					float intnumber = Float.valueOf(numberss);
					float intzhekou = Float.valueOf(xiangmuzhekouss);
					float intmoney = intyouhui*intnumber*intzhekou/100;
					String zhekoumoneyss = new java.text.DecimalFormat("#.00").format(intmoney);
					zhehoumoney.setText(""+zhekoumoneyss);
					
					
					String leibieq = leibie.getText().toString();
					String comboNameq = xiangmuleibie.getText().toString();
					String itemNameq = xiangmunames.getText().toString();
					String xiangmmoneyq = xiangmumoneys.getText().toString();
					String zhehoumoneyq = zhehoumoney.getText().toString();
					String serviceTimesq = lastmoney.getText().toString();
					if("".equals(xiangmmoneyq)||xiangmmoneyq.equals("")){
						xiangmmoneyq = "0";
						xiangmumoneys.setText(xiangmmoneyq);
					}
					float xiangmmoneyp = Float.valueOf(xiangmmoneyq);
					float youhuip = Float.valueOf(youhuimoneyss);
					int numberp = Integer.valueOf(numberss);
					int zhekoup = Integer.valueOf(xiangmuzhekouss);
					
//					更新容器中的数据
					XiaofeiXiangmu xiaofeiXiangmu = new XiaofeiXiangmu(); 
					xiaofeiXiangmu.setLeibie(leibieq);
					xiaofeiXiangmu.setXiangmuleibie(comboNameq);
					xiaofeiXiangmu.setXiangmuname(itemNameq);
					xiaofeiXiangmu.setXiangmmoney(xiangmmoneyp);
					xiaofeiXiangmu.setYouhui(youhuip);
					xiaofeiXiangmu.setNumber(numberp);
					xiaofeiXiangmu.setZhekou(zhekoup);
					xiaofeiXiangmu.setZhehoumoney(zhehoumoneyq);
					xiaofeiXiangmu.setLastmoney(serviceTimesq);
					submitproxiangmulist.put(position,xiaofeiXiangmu);
				}
				
			});
			
			numbert.addTextChangedListener(new TextWatcher() {

				@Override
				public void afterTextChanged(Editable s) {
				}

				@Override
				public void beforeTextChanged(CharSequence s, int start,
						int count, int after) {
					
				}

				@Override
				public void onTextChanged(CharSequence s, int start,
						int before, int count) {
					String youhuimoneyss = youhuimoney.getText().toString();
					String numberss = numbert.getText().toString();
					String xiangmuzhekouss = xiangmuzhekou.getText().toString();
					if("".equals(youhuimoneyss)){
						youhuimoneyss = "0";
					}
					if("".equals(numberss)){
						numberss = "0";
					}
					if("".equals(xiangmuzhekouss)){
						xiangmuzhekouss = "0";
					}
					float intyouhui = Float.valueOf(youhuimoneyss);
					float intnumber = Float.valueOf(numberss);
					float intzhekou = Float.valueOf(xiangmuzhekouss);
					
					float intmoney = intyouhui*intnumber*intzhekou/100;
					String zhekoumoneyss = new java.text.DecimalFormat("#.00").format(intmoney);
					zhehoumoney.setText(""+zhekoumoneyss);
					
					String leibieq = leibie.getText().toString();
					String comboNameq = xiangmuleibie.getText().toString();
					String itemNameq = xiangmunames.getText().toString();
					String xiangmmoneyq = xiangmumoneys.getText().toString();
					String zhehoumoneyq = zhehoumoney.getText().toString();
					String serviceTimesq = lastmoney.getText().toString();
					if("".equals(xiangmmoneyq)||xiangmmoneyq.equals("")){
						xiangmmoneyq = "0";
					}
					float xiangmmoneyp = Float.valueOf(xiangmmoneyq);
					float youhuip = Float.valueOf(youhuimoneyss);
					int numberp = Integer.valueOf(numberss);
					int zhekoup = Integer.valueOf(xiangmuzhekouss);
					
//					更新容器中的数据
					XiaofeiXiangmu xiaofeiXiangmu = new XiaofeiXiangmu(); 
					xiaofeiXiangmu.setLeibie(leibieq);
					xiaofeiXiangmu.setXiangmuleibie(comboNameq);
					xiaofeiXiangmu.setXiangmuname(itemNameq);
					xiaofeiXiangmu.setXiangmmoney(xiangmmoneyp);
					xiaofeiXiangmu.setYouhui(youhuip);
					xiaofeiXiangmu.setNumber(numberp);
					xiaofeiXiangmu.setZhekou(zhekoup);
					xiaofeiXiangmu.setZhehoumoney(zhehoumoneyq);
					xiaofeiXiangmu.setLastmoney(serviceTimesq);
					submitproxiangmulist.put(position,xiaofeiXiangmu);
				}
				
			});
			
			xiangmuzhekou.addTextChangedListener(new TextWatcher() {

				@Override
				public void afterTextChanged(Editable s) {
				}

				@Override
				public void beforeTextChanged(CharSequence s, int start,
						int count, int after) {
					
				}

				@Override
				public void onTextChanged(CharSequence s, int start,
						int before, int count) {
					String youhuimoneyss = youhuimoney.getText().toString();
					String numberss = numbert.getText().toString();
					String xiangmuzhekouss = xiangmuzhekou.getText().toString();
					if("".equals(youhuimoneyss)){
						youhuimoneyss = "0";
					}
					if("".equals(numberss)){
						numberss = "0";
					}
					if("".equals(xiangmuzhekouss)){
						xiangmuzhekouss = "0";
					}
					float intyouhui = Float.valueOf(youhuimoneyss);
					float intnumber = Float.valueOf(numberss);
					float intzhekou = Float.valueOf(xiangmuzhekouss);
					
					
					float intmoney = intyouhui*intnumber*intzhekou/100;
					String zhekoumoneyss = new java.text.DecimalFormat("#.00").format(intmoney);
					zhehoumoney.setText(""+zhekoumoneyss);
					
					String leibieq = leibie.getText().toString();
					String comboNameq = xiangmuleibie.getText().toString();
					String itemNameq = xiangmunames.getText().toString();
					String xiangmmoneyq = xiangmumoneys.getText().toString();
					String zhehoumoneyq = zhehoumoney.getText().toString();
					String serviceTimesq = lastmoney.getText().toString();
					if("".equals(xiangmmoneyq)||xiangmmoneyq.equals("")){
						xiangmmoneyq = "0";
					}
					float xiangmmoneyp = Float.valueOf(xiangmmoneyq);
					float youhuip = Float.valueOf(youhuimoneyss);
					int numberp = Integer.valueOf(numberss);
					int zhekoup = Integer.valueOf(xiangmuzhekouss);
					
//					更新容器中的数据
					XiaofeiXiangmu xiaofeiXiangmu = new XiaofeiXiangmu(); 
					xiaofeiXiangmu.setLeibie(leibieq);
					xiaofeiXiangmu.setXiangmuleibie(comboNameq);
					xiaofeiXiangmu.setXiangmuname(itemNameq);
					xiaofeiXiangmu.setXiangmmoney(xiangmmoneyp);
					xiaofeiXiangmu.setYouhui(youhuip);
					xiaofeiXiangmu.setNumber(numberp);
					xiaofeiXiangmu.setZhekou(zhekoup);
					xiaofeiXiangmu.setZhehoumoney(zhehoumoneyq);
					xiaofeiXiangmu.setLastmoney(serviceTimesq);
					submitproxiangmulist.put(position,xiaofeiXiangmu);
				}
				
			});
			
			return inflate;
		}
	}
//	获取配件消费(拼音)
	class GetSelfittingAsytask extends AsyncTask<String, Void, String>{
		
		String itemNamePY;
		int count = 10;
		private String property;
		public GetSelfittingAsytask(String string) {
			this.itemNamePY = string;
		}

		@Override
		protected String doInBackground(String... params) {
			SoapObject soapObject=new SoapObject(ConnectionUrl.fastNameSpace,"GetSelfitting");
			soapObject.addProperty("ItemNamePY", itemNamePY);
			soapObject.addProperty("count", count);
			soapObject.addProperty("Unitid", unitid);
	        SoapSerializationEnvelope envelope=new SoapSerializationEnvelope(SoapEnvelope.VER11);
	        envelope.dotNet=true;
	        envelope.setOutputSoapObject(soapObject);
	        envelope.bodyOut=soapObject;
	        envelope.dotNet = true;
	        (new MarshalBase64()).register(envelope);
	        AndroidHttpTransport httpTranstation=new AndroidHttpTransport(ConnectionUrl.fastURL);
	        httpTranstation.debug=true;
	        try {
				httpTranstation.call(ConnectionUrl.fastNameSpace+"GetSelfitting", envelope);
				if(envelope.getResponse()!=null){
					SoapObject result = (SoapObject) envelope.getResponse();
					property = result.getProperty(0).toString();
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
				for (int j = 0; j < jsonArray.length(); j++) {
					JSONObject jsonObject = jsonArray.getJSONObject(j);
					String prodName = jsonObject.getString("ProdName");
					String prodid = jsonObject.getString("prodid");
					String itemType = "";
					String servicePrice = "";
					String serviceItemID1 = "";
					GetXiangmuXiaoFei getxiangmu = new GetXiangmuXiaoFei();
					getxiangmu.setServiceItemID(prodid);
					getxiangmu.setItemName(prodName);
					getxiangmu.setItemType(itemType);
					getxiangmu.setServicePrice(servicePrice);
					getxiangmu.setServiceItemID1(serviceItemID1);
					getpeijianlist.add(getxiangmu);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			XiangmuListadapter adapter = new XiangmuListadapter(getpeijianlist);
			xiangmulistview2.setAdapter(adapter);
		}
	}
	
//	获取配件消费
	class GetfittingAsyTask extends AsyncTask<String, Void, String>{

		String proid;
		private String string;
		public GetfittingAsyTask(String serviceItemID) {
			this.proid = serviceItemID;
		}

		@Override
		protected String doInBackground(String... params) {
			SoapObject soapObject=new SoapObject(ConnectionUrl.fastNameSpace,"Getfitting");
			soapObject.addProperty("Proid", proid);
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
				httpTranstation.call(ConnectionUrl.fastNameSpace+"Getfitting", envelope);
				if(envelope.getResponse()!=null){
					SoapObject result = (SoapObject) envelope.getResponse();
					string = result.getProperty(0).toString();
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
		
			JSONObject jsonObject;
			try {
				jsonObject = new JSONObject(result);
				String itemName = jsonObject.getString("ProdName");
				String itemType = jsonObject.getString("ProdType");
				float servicePrice = Float.valueOf(jsonObject.getString("CostPrice"));
				String saleAmount = jsonObject.getString("SumAmount");
				int number = 1;
				String leibie = "配件";
				int xiangmuzhekou = 0;
				String zhekous = zhekou.getText().toString();
				if("null".equals(zhekous)||"".equals(zhekous)||zhekous.equals("")){
					xiangmuzhekou = 100;
				}else{
					xiangmuzhekou = Integer.valueOf(zhekous);
				}
				String jishi = "";
				
				float zhehoumoney = xiangmuzhekou/100*servicePrice*number;
				String zhekoumoney = new java.text.DecimalFormat("#.00").format(zhehoumoney);
				XiaofeiXiangmu xiaofeixiangmu = new XiaofeiXiangmu();
				xiaofeixiangmu.setLeibie(leibie);
				xiaofeixiangmu.setXiangmuleibie(itemType);
				xiaofeixiangmu.setXiangmuname(itemName);
				xiaofeixiangmu.setXiangmmoney(servicePrice);
				xiaofeixiangmu.setYouhui(servicePrice);
				xiaofeixiangmu.setNumber(number);
				xiaofeixiangmu.setZhekou(xiangmuzhekou);
				xiaofeixiangmu.setZhehoumoney(zhekoumoney);
				xiaofeixiangmu.setJishi(jishi);
				xiaofeixiangmu.setLastmoney(saleAmount);
				xiaofeixiangmu.setServiceItemID(proid);
				getproxiangmulist.add(xiaofeixiangmu);
			    xiangmuadapter.notifyDataSetChanged();
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
	}
//	获取套餐信息
	class GetCardPackage extends AsyncTask<String, Void, String>{

		private String string;

		@Override
		protected String doInBackground(String... arg0) {
			gettaocanlist.clear();
			SoapObject soapObject=new SoapObject(ConnectionUrl.fastNameSpace,"GetCardPackage");
			soapObject.addProperty("CardInfIDStr", cardid);
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
				httpTranstation.call(ConnectionUrl.fastNameSpace+"GetCardPackage", envelope);
				if(envelope.getResponse()!=null){
					SoapObject result = (SoapObject) envelope.getResponse();
					string = result.getProperty(0).toString();
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
			if(result==null||"".equals(result)||result.equals("")){
//				如果为空则不做处理
			}else{
				try {
					JSONArray jsonArray = new JSONArray(result);
					for (int j = 0; j < jsonArray.length(); j++) {
						JSONObject jsonObject = jsonArray.getJSONObject(j);
						String comboName = jsonObject.getString("ComboName");
						String buyType = jsonObject.getString("BuyType");
						String itemName = jsonObject.getString("ItemName");
						String serviceTimes = jsonObject.getString("ServiceTimes");
						String combocardmID = jsonObject.getString("ComboCardMID");
						String combocardID = jsonObject.getString("ComboCardID");
						
						
						GetTaocanXiaoFei taocanxiaofei = new GetTaocanXiaoFei();
						taocanxiaofei.setComboName(comboName);
						taocanxiaofei.setBuyType(buyType);
						taocanxiaofei.setItemNam(itemName);
						taocanxiaofei.setServiceTimes(serviceTimes);
						taocanxiaofei.setCombocardmID(combocardmID);
						taocanxiaofei.setCombocardID(combocardID);
						
						gettaocanlist.add(taocanxiaofei);
						isSelect.put(j, false);
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
			
//			套餐列表的显示
			TaocanXicheAdapter adapter = new TaocanXicheAdapter(gettaocanlist);
			taocanxiche_listview.setAdapter(adapter);
		}
	}
	
	class TaocanXicheAdapter extends BaseAdapter{

		ArrayList<GetTaocanXiaoFei> gettaocanlist;
		public TaocanXicheAdapter(ArrayList<GetTaocanXiaoFei> gettaocanlist) {
			this.gettaocanlist = gettaocanlist;
		}

		@Override
		public int getCount() {
			return gettaocanlist.size();
		}

		@Override
		public Object getItem(int arg0) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View inflate = getLayoutInflater().inflate(R.layout.taocanxiaofei_listview, null);
			TextView taocanname = (TextView) inflate.findViewById(R.id.taocan_name);
			TextView taocaleibie = (TextView) inflate.findViewById(R.id.taocan_leibie);
			TextView taocanxiangmu = (TextView) inflate.findViewById(R.id.taocan_xiangmu);
			TextView taocanserviertimes = (TextView) inflate.findViewById(R.id.taocan_serviertimes);
			String comboName = gettaocanlist.get(position).getComboName();
			String itemNam = gettaocanlist.get(position).getItemNam();
			String buyType = gettaocanlist.get(position).getBuyType();
			String serviceTimes = gettaocanlist.get(position).getServiceTimes();
			taocanname.setText(comboName);
			taocaleibie.setText(buyType);
			taocanxiangmu.setText(itemNam);
			taocanserviertimes.setText(serviceTimes);
			
			return inflate;
		}
		
	}
//	获取客户详细信息
	class GetCusInfoAsyncTask extends AsyncTask<String, Void, String>{

		private String result;
		private String birthdays;

		@Override
		protected String doInBackground(String... params) {
			SoapObject soapObject=new SoapObject(ConnectionUrl.fastNameSpace,"GetCusInfo");
			soapObject.addProperty("CusUnitInfID", cusUnitInfID);
	        SoapSerializationEnvelope envelope=new SoapSerializationEnvelope(SoapEnvelope.VER11);
	        envelope.dotNet=true;
	        envelope.setOutputSoapObject(soapObject);
	        envelope.bodyOut=soapObject;
	        envelope.dotNet = true;
	        (new MarshalBase64()).register(envelope);
	        AndroidHttpTransport httpTranstation=new AndroidHttpTransport(ConnectionUrl.fastURL);
	        httpTranstation.debug=true; 
	        try {
	        	httpTranstation.call(ConnectionUrl.fastNameSpace+"GetCusInfo", envelope);
				if(envelope.getResponse()!=null){
					SoapObject response = (SoapObject) envelope.getResponse();
					result = response.getProperty(0).toString();
				}
			} catch (IOException e) {
				e.printStackTrace();
			} catch (XmlPullParserException e) {
				e.printStackTrace();
			}
			return result;
		}
		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			if(result == null|| "".equals(result)||result.equals("")){
			}else{
				try {
					JSONObject jsonObject = new JSONObject(result);
					String customerName = jsonObject.getString("CustomerName");
					String customerOrigin = jsonObject.getString("CustomerOrigin");
					String mobile = jsonObject.getString("Mobile");
					String birthday = jsonObject.getString("Birthday");
					if(birthday.equals("null")||"".equals(birthday)||birthday.equals("")){
					}else{
//					json时间格式的转换
						int first = birthday.indexOf("(");
						int last = birthday.lastIndexOf(")");
						String time = birthday.substring(first+1,last);
						
						Date date = new Date(Long.valueOf(time));
						SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						birthdays = formatter.format(date).substring(0, 10);
					}
					
					String telephone = jsonObject.getString("Telephone");
					String otherContact = jsonObject.getString("OtherContact");
					String otherPhone = jsonObject.getString("OtherPhone");
					String workAddress = jsonObject.getString("WorkAddress");
					String workTelephone = jsonObject.getString("WorkTelephone");
					String fax = jsonObject.getString("Fax");
					String zip = jsonObject.getString("Zip");
					String remark = jsonObject.getString("Remark");
					String address = jsonObject.getString("Address");
					
					if(customerName == null||"".equals(customerName)||customerName.equals("")){
						customerName = "";
					}
					if(customerOrigin == null||"".equals(customerOrigin)||customerOrigin.equals("")){
						customerOrigin = "";
					}
					if(mobile == null||"".equals(mobile)||mobile.equals("")){
						mobile = "";
					}
					if(birthday == null||"".equals(birthday)||birthday.equals("")){
						birthday = "";
					}
					if(telephone == null||"".equals(telephone)||telephone.equals("")){
						telephone = "";
					}
					if(otherContact == null||"".equals(otherContact)||otherContact.equals("")){
						otherContact = "";
					}
					if(otherPhone == null||"".equals(otherPhone)||otherPhone.equals("")){
						otherPhone = "";
					}
					if(workAddress == null||"".equals(workAddress)||workAddress.equals("")){
						workAddress = "";
					}
					if(workTelephone == null||"".equals(workTelephone)||workTelephone.equals("")){
						workTelephone = "";
					}
					if(fax == null||"".equals(fax)||fax.equals("")){
						fax = "";
					}
					if(zip == null||"".equals(zip)||zip.equals("")){
						zip = "";
					}
					if(remark == null||"".equals(remark)||remark.equals("")){
						remark = "";
					}
					if(address == null||"".equals(address)||address.equals("")){
						address = "";
					}
					
					custom_name.setText(customerName);
					custom_othertelephone.setText(otherPhone);
					custom_come.setText(customerOrigin);
					custom_bitrhday.setText(birthdays);
					custom_mobile.setText(telephone);
					custom_otherpeople.setText(otherContact);
					custom_fax.setText(fax);
					cus1tom_workplace.setText(workAddress);
					custom_workphone.setText(workTelephone);
					custom_address.setText(address);
					custom_telephone.setText(mobile);
					custom_zip.setText(zip);
					custom_remark.setText(remark);
					
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		}
	}
//	获取车辆信息
	class GetCarInfoAsyncTask extends AsyncTask<String, Void, String>{

		private String result;
		private String buyDTss;

		@Override
		protected String doInBackground(String... params) {
			SoapObject soapObject=new SoapObject(ConnectionUrl.fastNameSpace,"GetCarInfo");
			soapObject.addProperty("CarInfoID", carInfoID);
	        SoapSerializationEnvelope envelope=new SoapSerializationEnvelope(SoapEnvelope.VER11);
	        envelope.dotNet=true;
	        envelope.setOutputSoapObject(soapObject);
	        envelope.bodyOut=soapObject;
	        envelope.dotNet = true;
	        (new MarshalBase64()).register(envelope);
	        AndroidHttpTransport httpTranstation=new AndroidHttpTransport(ConnectionUrl.fastURL);
	        httpTranstation.debug=true; 
	        try {
				httpTranstation.call(ConnectionUrl.fastNameSpace+"GetCarInfo", envelope);
				if(envelope.getResponse()!=null){
					SoapObject response = (SoapObject) envelope.getResponse();
					result = response.getProperty(0).toString();
				}
			} catch (IOException e) {
				e.printStackTrace();
			} catch (XmlPullParserException e) {
				e.printStackTrace();
			}
			return result;
		}
		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			if("".equals(result)||result.equals("")){
			}else{
				try {
					JSONObject jsonObject = new JSONObject(result);
					String carID = jsonObject.getString("CarID");
					String carFramNum = jsonObject.getString("CarFramNum");
					String motorNum = jsonObject.getString("MotorNum");
					String driverPhone = jsonObject.getString("DriverPhone");
					String carType = jsonObject.getString("CarType");
					String driver = jsonObject.getString("Driver");
					String yearCheckInte = jsonObject.getString("YearCheckInte");
					String buyDT = jsonObject.getString("BuyDT");
					if(buyDT.equals("null")||"".equals(buyDT)||buyDT.equals("")){
					}else{
//					json时间格式的转换
						int first = buyDT.indexOf("(");
						int last = buyDT.lastIndexOf(")");
						String time = buyDT.substring(first+1,last);
						
						Date date = new Date(Long.valueOf(time));
						SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						buyDTss = formatter.format(date).substring(0, 10);
					}
					
					if(carID == null||"".equals(carID)||carID.equals("")){
						carID = "";
					}
					if(carFramNum == null||"".equals(carFramNum)||carFramNum.equals("")){
						carFramNum = "";
					}
					if(motorNum == null||"".equals(motorNum)||motorNum.equals("")){
						motorNum = "";
					}
					if(driverPhone == null||"".equals(driverPhone)||driverPhone.equals("")){
						driverPhone = "";
					}
					if(carType == null||"".equals(carType)||carType.equals("")){
						carType = "";
					}
					if(driver == null||"".equals(driver)||driver.equals("")){
						driver = "";
					}
					if(yearCheckInte == null||"".equals(yearCheckInte)||yearCheckInte.equals("")){
						yearCheckInte = "";
					}
					if(buyDT == null||"".equals(buyDT)||buyDT.equals("")){
						buyDT = "";
					}
					
					
					car_carid.setText(carID);
					car_carframnum.setText(carFramNum);
					car_motornum.setText(motorNum);
					car_driverPhone.setText(driverPhone);
					car_cartype.setText(carType);
					car_driver.setText(driver);
					car_buydt.setTag(buyDTss);
					car_yearcheckinte.setText(yearCheckInte);
					
					
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		}
	}
//	保存卡信息
	class SaveCarInfoAsytask extends AsyncTask<String, Void, String>{

		String data = "{\"CarID\":\""+car_carid.getText().toString()+"\",\"CarFramNum\":\""+car_carframnum.getText().toString()+"\"," +
				"\"MotorNum\":\""+car_motornum.getText().toString()+"\",\"DriverPhone\":\""+car_driverPhone.getText().toString()+"\"," +
				"\"CarType\":\""+car_cartype.getText().toString()+"\",\"Driver\":\""+car_driver.getText().toString()+"\",\"BuyDT\":\""+car_buydt.getText().toString()+"\"," +
				"\"YearCheckInte\":\""+car_yearcheckinte.getText().toString()+"\"}";
		private String property;
		@Override
		protected String doInBackground(String... params) {
			SoapObject soapObject=new SoapObject(ConnectionUrl.fastNameSpace,"SaveCarInfo");
			soapObject.addProperty("CarInfoID", carInfoID);
			soapObject.addProperty("data", data);
	        SoapSerializationEnvelope envelope=new SoapSerializationEnvelope(SoapEnvelope.VER11);
	        envelope.dotNet=true;
	        envelope.setOutputSoapObject(soapObject);
	        envelope.bodyOut=soapObject;
	        envelope.dotNet = true;
	        (new MarshalBase64()).register(envelope);
	        AndroidHttpTransport httpTranstation=new AndroidHttpTransport(ConnectionUrl.fastURL);
	        httpTranstation.debug=true; 
	        try {
				httpTranstation.call(ConnectionUrl.fastNameSpace+"SaveCarInfo", envelope);
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
			if(result.equals("true")){
				Toast.makeText(MainActivity.this, "客户详细信息保存成功", Toast.LENGTH_SHORT).show();
			}else{
				Toast.makeText(MainActivity.this, "客户详细信息保存失败", Toast.LENGTH_SHORT).show();
			}
		}
	}
//	保存用户信息
	class SaveCusInfoAsytask extends AsyncTask<String, Void, String>{

		String data = "{\"CustomerNo\":\""+""+"\"," +
				"\"CustomerName\":\""+custom_name.getText().toString()+"\"," +
				"\"CustomerOrigin\":\""+custom_come.getText().toString()+"\"," +
				"\"Mobile\":\""+custom_telephone.getText().toString()+"\"," +
				"\"Birthday\":\""+custom_bitrhday.getText().toString()+"\"," +
				"\"Telephone\":\""+custom_mobile.getText().toString()+"\"," +
				"\"OtherContact\":\""+custom_otherpeople.getText().toString()+"\"," +
				"\"OtherPhone\":\""+custom_othertelephone.getText().toString()+"\"," +
				"\"Fax\":\""+custom_fax.getText().toString()+"\"," +
				"\"WorkAddress\":\""+cus1tom_workplace.getText().toString()+"\"," +
				"\"WorkTelephone\":\""+custom_workphone.getText().toString()+"\"," +
				"\"Zip\":\""+custom_zip.getText().toString()+"\"," +
				"\"Address\":\""+custom_address.getText().toString()+"\"," +
				"\"Remark\":\""+custom_remark.getText().toString()+"\"}";
		private String property;
		@Override
		protected String doInBackground(String... params) {
			SoapObject soapObject=new SoapObject(ConnectionUrl.fastNameSpace,"SaveCusInfo");
			soapObject.addProperty("CusUnitInfID", cusUnitInfID);
			soapObject.addProperty("data", data);
			soapObject.addProperty("CarInfoID", carInfoID);
			soapObject.addProperty("EmployeeName", employeeName);
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
				httpTranstation.call(ConnectionUrl.fastNameSpace+"SaveCusInfo", envelope);
				if(envelope.getResponse()!=null){
					SoapObject response = (SoapObject) envelope.getResponse();
					property = response.getProperty(0).toString();
				}
			} catch (IOException e) {
				e.printStackTrace();
			} catch (XmlPullParserException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return property;
		}
		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			String customernames = custom_name.getText().toString();
			String customermobiles = custom_telephone.getText().toString();
			customername.setText(customernames);
			driverphone.setText(customermobiles);
		}
	}
//	提交所有信息
	class SaveorderAsytask extends AsyncTask<String, Void, String>{

		String table;
		String database;
		private String string;
		public SaveorderAsytask(String table, String database) {
			this.table = table;
			this.database = database;
		}

		@Override
		protected String doInBackground(String... params) {
			SoapObject soapObject=new SoapObject(ConnectionUrl.fastNameSpace,"Saveorder");
			soapObject.addProperty("table", table);
			soapObject.addProperty("basedata", database);
			soapObject.addProperty("UnitID", unitID);
			soapObject.addProperty("EmployeeName", employeeName);
	        SoapSerializationEnvelope envelope=new SoapSerializationEnvelope(SoapEnvelope.VER11);
	        envelope.dotNet=true;
	        envelope.setOutputSoapObject(soapObject);
	        envelope.bodyOut=soapObject;
	        envelope.dotNet = true;
	        (new MarshalBase64()).register(envelope);
	        AndroidHttpTransport httpTranstation=new AndroidHttpTransport(ConnectionUrl.fastURL);
	        httpTranstation.debug=true; 
	        try {
				httpTranstation.call(ConnectionUrl.fastNameSpace+"Saveorder", envelope);
				if(envelope.getResponse()!=null){
					SoapObject response = (SoapObject) envelope.getResponse();
					string = response.getProperty(0).toString();
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
		    if(result==null||"".equals(result)||result.equals("")){
		    	Toast.makeText(MainActivity.this, "订单提交失败", Toast.LENGTH_SHORT).show();
		    }else{
		    	Toast.makeText(MainActivity.this, "订单提交成功", Toast.LENGTH_SHORT).show();
		    	Intent intent = new Intent(MainActivity.this,CarSure.class);
		    	intent.putExtra("unitid", unitid);
		    	intent.putExtra("isrule", isrule);
		    	intent.putExtra("employeeName", employeeName);
		    	startActivity(intent);
		    	finish();
		    }
		}
	}
	
	public void insertwash(List<XiaofeiXiangmu> list) {
		WashSQLiteHelper helper = new WashSQLiteHelper(MainActivity.this);
		SQLiteDatabase mDB = helper.getWritableDatabase();
		
		 Cursor cursor = mDB.query("wash", new String[]
					{"id"}, "id" + "=?", 
					new String[]{""+1},
					null, null, null);
		if (cursor.getCount()==0) {
			Setsqldata(list, mDB);
		}else{
			mDB.delete("wash", "id" + "=?", new String[]{""+1});
			Setsqldata(list, mDB);
		}
		
	}

	private void Setsqldata(List<XiaofeiXiangmu> list, SQLiteDatabase mDB) {
		String leibie2 = list.get(0).getLeibie();
		String xiangmuleibie2 = list.get(0).getXiangmuleibie();
		String xiangmuname = list.get(0).getXiangmuname();
		String xiangmmoney = String.valueOf(list.get(0).getXiangmmoney());
		String youhui = String.valueOf(list.get(0).getYouhui());
		int number = list.get(0).getNumber();
		int zhekou2 = list.get(0).getZhekou();
		String zhehoumoney2 = list.get(0).getZhehoumoney();
		String lastmoney2 = list.get(0).getLastmoney();
		String buyType = list.get(0).getBuyType();
		String combocardID = list.get(0).getCombocardID();
		String combocardmID = list.get(0).getCombocardmID();
		String serviceItemID = list.get(0).getServiceItemID();
		
		ContentValues values = new ContentValues();
		values.put("id", 1);
		values.put("carid", carid.getText().toString());//通过车牌号辨认是谁
		values.put("username", customername.getText().toString());//用户
		values.put("waiter", waiter.getText().toString());//接车员
		values.put("leibie", leibie2);
		values.put("xiangmuleibie", xiangmuleibie2);
		values.put("xiangmuname", xiangmuname);
		values.put("xiangmmoney", xiangmmoney);
		values.put("youhui", youhui);
		values.put("number", number);
		values.put("zhekou", zhekou2);
		values.put("zhehoumoney", zhehoumoney2);
		values.put("lastmoney", lastmoney2);
		values.put("buyType", buyType);
		values.put("combocardID", combocardID);
		values.put("combocardmID", combocardmID);
		values.put("serviceItemID", serviceItemID);
		mDB.insert("wash", null, values);
	}
	
	public void insertxiangmu(List<XiaofeiXiangmu> list) {
		XiangmuSQLiteHelper helper = new XiangmuSQLiteHelper(MainActivity.this);
		SQLiteDatabase mDB = helper.getWritableDatabase();
		for (int i = 0; i < list.size(); i++) {
			Cursor cursor = mDB.query("xiangmu", new String[]
					{"carid"}, "carid" + "=?", 
					new String[]{""+i},
					null, null, null);
			if (cursor.getCount() == 0)
			{
				String leibie2 = list.get(i).getLeibie();
				String xiangmuleibie2 = list.get(i).getXiangmuleibie();
				String xiangmuname = list.get(i).getXiangmuname();
				String xiangmmoney = String.valueOf(list.get(i).getXiangmmoney());
				String youhui = String.valueOf(list.get(i).getYouhui());
				int number = list.get(i).getNumber();
				int zhekou2 = list.get(i).getZhekou();
				String zhehoumoney2 = list.get(i).getZhehoumoney();
				String lastmoney2 = list.get(i).getLastmoney();
				String buyType = list.get(i).getBuyType();
				String combocardID = list.get(i).getCombocardID();
				String combocardmID = list.get(i).getCombocardmID();
				String serviceItemID = list.get(i).getServiceItemID();
				
				ContentValues values = new ContentValues();
				values.put("id", ""+i);
				values.put("carid", carid.getText().toString());
				values.put("username", customername.getText().toString());//用户
				values.put("waiter", waiter.getText().toString());//接车员
				values.put("leibie", leibie2);
				values.put("xiangmuleibie", xiangmuleibie2);
				values.put("xiangmuname", xiangmuname);
				values.put("xiangmmoney", xiangmmoney);
				values.put("youhui", youhui);
				values.put("number", number);
				values.put("zhekou", zhekou2);
				values.put("zhehoumoney", zhehoumoney2);
				values.put("lastmoney", lastmoney2);
				values.put("buyType", buyType);
				values.put("combocardID", combocardID);
				values.put("combocardmID", combocardmID);
				values.put("serviceItemID", serviceItemID);
				mDB.insert("xiangmu", null, values);
			}
		}
		
	}
	
	
	class DateButtonOnClickListener implements  
	  
    android.view.View.OnClickListener {  

	@Override  
	
	public void onClick(View v) {  
	
	    Message msg = new Message();  
	
	    if (safelimitdt.equals((EditText) v)) {  
	
	       msg.what = MainActivity.SHOW_DATAPICK;  
	
	    }  
	
	    MainActivity.this.saleHandler.sendMessage(msg);  
	
	}  
	
	}  
	Handler saleHandler = new Handler() {  
		  
	       @Override  
	  
	       public void handleMessage(Message msg) {  
	  
	           switch (msg.what) {  
	  
	           case MainActivity.SHOW_DATAPICK:  
	  
	              showDialog(DATE_DIALOG_ID);  
	  
	              break;  
	  
	           }  
	  
	       }  
	  
	    };
	    
	    @Override  
		  
	    protected Dialog onCreateDialog(int id) {  
	  
	       switch (id) {  
	  
	       case DATE_DIALOG_ID:  
	  
	           return new DatePickerDialog(this, mDateSetListener, mYear, mMonth,  
	  
	                  mDay);  
	  
	       case DATE_DIALOG_ID2:  
	    	   
	    	   return new DatePickerDialog(this, mDateSetListener2, mYear, mMonth,  
	    			   
	    			   mDay);  
	       }  
	       
	       return null;  
	  
	    }  
	  
	   
	  
	    @Override  
	  
	    protected void onPrepareDialog(int id, Dialog dialog) {  
	  
	       switch (id) {  
	  
	       case DATE_DIALOG_ID:  
	  
	           ((DatePickerDialog) dialog).updateDate(mYear, mMonth, mDay);  
	  
	           break; 
	       case DATE_DIALOG_ID2:  
	    		  
	           ((DatePickerDialog) dialog).updateDate(mYear, mMonth, mDay);  
	  
	           break; 
	  
	       }  
	  
	    } 
	    
	    private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {  
			  
	       public void onDateSet(DatePicker view, int year, int monthOfYear,  
	  
	              int dayOfMonth) {  
	  
	           mYear = year;  
	  
	           mMonth = monthOfYear;  
	  
	           mDay = dayOfMonth;  
	  
	           updateDisplay();  
	  
	       }  
	  
	    };  
	    
	    private void updateDisplay() {  
			  
		       safelimitdt.setText(new StringBuilder().append(mYear+"-").append(  
		  
		              (mMonth + 1) < 10 ? "0" + (mMonth + 1+"-") : (mMonth + 1)).append(  
		  
		              (mDay < 10) ? "0" + mDay : mDay));  
		  
		} 
		    
	    class DateBirthdayOnClickListener implements  
		  
	    android.view.View.OnClickListener {  

		@Override  
		
		public void onClick(View v) {  
		
		    Message msg = new Message();  
		
		    if (custom_bitrhday.equals((EditText) v)) {  
		
		       msg.what = MainActivity.SHOW_DATAPICK;  
		
		    }  
		
		    MainActivity.this.bithHandler.sendMessage(msg);  
		
		}  
		
		}  
		Handler bithHandler = new Handler() {  
			  
		       @Override  
		  
		       public void handleMessage(Message msg) {  
		  
		           switch (msg.what) {  
		  
		           case MainActivity.SHOW_DATAPICK:  
		  
		              showDialog(DATE_DIALOG_ID2);  
		  
		              break;  
		  
		           }  
		  
		       }  
		  
		    };
		    
		    
		    private DatePickerDialog.OnDateSetListener mDateSetListener2 = new DatePickerDialog.OnDateSetListener() {  
				  
		       public void onDateSet(DatePicker view, int year, int monthOfYear,  
		  
		              int dayOfMonth) {  
		  
		           mYear = year;  
		  
		           mMonth = monthOfYear;  
		  
		           mDay = dayOfMonth;  
		  
		           updateDisplay2();  
		  
		       }  
		  
		    };
			private PopupWindow pop;
			private String caridss;  
		    
		    private void updateDisplay2() {  
				  
			       custom_bitrhday.setText(new StringBuilder().append(mYear+"-").append(  
			  
			              (mMonth + 1) < 10 ? "0" + (mMonth + 1+"-") : (mMonth + 1)).append(  
			  
			              (mDay < 10) ? "0" + mDay : mDay));  
			  
			}    
		    
    class WaiterAsytask extends AsyncTask<String, Void, String>{

		private String string;
		@Override
		protected String doInBackground(String... params) {
			employeeNamelist.clear();
			SoapObject soapObject=new SoapObject(ConnectionUrl.fastNameSpace,"GetUser");
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
				httpTranstation.call(ConnectionUrl.fastNameSpace+"GetUser", envelope);
				if(envelope.getResponse()!=null){
					SoapObject response = (SoapObject) envelope.getResponse();
					string = response.getProperty(0).toString();
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
    		try {
				JSONArray jsonArray = new JSONArray(result);
				for (int j = 0; j < jsonArray.length(); j++) {
					JSONObject jsonObject = jsonArray.getJSONObject(j);
					String employeeName = jsonObject.getString("EmployeeName");
					employeeNamelist.add(employeeName);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
    		if(employeeNamelist.size()!=0){
    			waiter.setText(employeeNamelist.get(0));
    		}
    		Waiter();
    	}
    }
		    
    class WaiterAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			return employeeNamelist.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View inflate = getLayoutInflater().inflate(R.layout.employeenamelayout, null);
			TextView employename = (TextView) inflate.findViewById(R.id.employeeNamelist);
			String employeeName = employeeNamelist.get(position);
			employename.setText(employeeName);
			return inflate;
		}
    	
    }
}
