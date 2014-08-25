package com.example.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

/**
 * JSON���ݽ��������࣬���ڽ�JSON�ַ���ת����ָ���Ķ�������List���Լ�Map�����Map��List
 * 
 * @author Li Bin
 */
public class JsonUtil<T> {
	private static final String LOG_JSON_ERROR = "com.imcore.common.util.JsonError";

	private static final String BYTE = "java.lang.Byte";
	private static final String INTEGER = "java.lang.Integer";
	private static final String SHORT = "java.lang.Short";
	private static final String LONG = "java.lang.Long";
	private static final String BOOLEAN = "java.lang.Boolean";
	private static final String CHAR = "java.lang.Character";
	private static final String FLOAT = "java.lang.Float";
	private static final String DOUBLE = "java.lang.Double";

	private static final String VALUE_BYTE = "byte";
	private static final String VALUE_INTEGER = "int";
	private static final String VALUE_SHORT = "short";
	private static final String VALUE_LONG = "long";
	private static final String VALUE_BOOLEAN = "boolean";
	private static final String VALUE_CHAR = "char";
	private static final String VALUE_FLOAT = "float";
	private static final String VALUE_DOUBLE = "double";

	/**
	 * ����key��ȡ������json���ݵ�ֵ
	 * 
	 * @param json
	 *            ������JSON�ַ���
	 * @param key
	 *            ָ����Ҫ��ȡֵ����Ӧ��key
	 * @return ����һ���ַ�������ʾ����ָ����key���õ���ֵ����ȡʧ�ܻ���JSON���������򷵻ؿ��ַ���
	 */
	public static String getJsonValueByKey(String json, String key) {
		String value = "";
		try {
			JSONObject jo = new JSONObject(json);
			value = jo.getString(key);
		} catch (JSONException e) {
			Log.e(LOG_JSON_ERROR, e.getLocalizedMessage());
		}
		return value;
	}

	/**
	 * ��ָ����JSON�ַ���ת����clsָ�������ʵ������
	 * 
	 * @param json
	 *            ������JSON�ַ���
	 * @param cls
	 *            ָ��Ҫת���ɵĶ�������������Classʵ��
	 * @return ����clsָ�����͵Ķ���ʵ��,���е��ֶ���json���ݼ�ֵ��һһ��Ӧ
	 */
	public static <T> T toObject(String json, Class<T> cls) {
		T obj = null;
		try {
			JSONObject jsonObject = new JSONObject(json);
			obj = cls.newInstance();
			Field[] fields = cls.getDeclaredFields();
			for (Field field : fields) {
				if (Modifier.isFinal(field.getModifiers())
						|| Modifier.isPrivate(field.getModifiers())) {
					continue;
				}
				try {
					String key = field.getName();
					if (jsonObject.get(key) == JSONObject.NULL) {
						field.set(obj, null);
					} else {
						Object value = getValue4Field(jsonObject.get(key),
								jsonObject.get(key).getClass().getName());
						field.set(obj, value);
					}
				} catch (Exception e) {
					field.set(obj, null);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			Log.e(LOG_JSON_ERROR, e.getLocalizedMessage());
		}
		return obj;
	}

	/**
	 * ��ָ���Ķ���orginalValueת����typeNameָ�������͵Ķ���
	 * 
	 * @param orginalValue
	 *            ������ת��֮ǰ��ֵ
	 * @param fieldType
	 *            Ҫת������������
	 * @return
	 */
	private static Object getValue4Field(Object orginalValue, String typeName) {
		Log.i("Json_Util", typeName);
		Object value = orginalValue.toString();
		if (typeName.equals(BYTE) || typeName.equals(VALUE_BYTE)) {
			value = Byte.class.cast(orginalValue);
			return value;
		}
		if (typeName.equals(INTEGER) || typeName.equals(VALUE_INTEGER)) {
			value = Integer.class.cast(orginalValue);
			return value;
		}
		if (typeName.equals(SHORT) || typeName.equals(VALUE_SHORT)) {
			value = Short.class.cast(orginalValue);
			return value;
		}
		if (typeName.equals(LONG) || typeName.equals(VALUE_LONG)) {
			value = Long.class.cast(orginalValue);
			return value;
		}
		if (typeName.equals(BOOLEAN) || typeName.equals(VALUE_BOOLEAN)) {
			value = Boolean.class.cast(orginalValue);
			return value;
		}
		if (typeName.equals(CHAR) || typeName.equals(VALUE_CHAR)) {
			value = Character.class.cast(orginalValue);
			return value;
		}
		if (typeName.equals(FLOAT) || typeName.equals(VALUE_FLOAT)) {
			value = Float.class.cast(orginalValue);
			return value;
		}
		if (typeName.equals(DOUBLE) || typeName.equals(VALUE_DOUBLE)) {
			value = Double.class.cast(orginalValue);
			return value;
		}
		return value;
	}

	/**
	 * ��ָ����JSON�ַ���ת���ɰ���clsָ�������͵�ʵ�����List����
	 * 
	 * @param json
	 *            ������JSON�ַ���
	 * @param cls
	 *            ָ��Ҫת���ɵĶ�������������Classʵ��
	 * @return ����һ��List���ϣ����а���json�е�����Ԫ������Ӧ��ʵ�����ʵ��
	 */
	public static <T> List<T> toObjectList(String json, Class<T> cls) {
		List<T> list = new ArrayList<T>();
		try {
			List<String> jsonStrList = toJsonStrList(json);
			for (String jsonStr : jsonStrList) {
				T obj = toObject(jsonStr, cls);
				list.add(obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
			Log.e(LOG_JSON_ERROR, e.getLocalizedMessage());
		}
		return list;
	}

	/**
	 * ��һ��������json�ַ���ת���ɰ�����json�ַ�����List����
	 * 
	 * @param json
	 *            ������JSON�ַ���
	 * @return ����һ��List���ϣ�����һ���ַ�������Ӧ�ڸ���ԭʼJSON������Ԫ�ص��ַ�����ʽ
	 */
	public static List<String> toJsonStrList(String json) {
		List<String> strList = new ArrayList<String>();
		try {
			JSONArray jsonArray = new JSONArray(json);
			for (int i = 0; i < jsonArray.length(); i++) {
				String jsonStr = jsonArray.getString(i);
				strList.add(jsonStr);
			}
		} catch (JSONException e) {
			Log.e(LOG_JSON_ERROR, e.getMessage());
		}
		return strList;
	}

	/**
	 * ��json�ַ���ת��ΪMap
	 * 
	 * @param json
	 * @return
	 */
	public static Map<String, Object> toMap(String json) {
		Map<String, Object> map = null;
		try {
			JSONObject jo = new JSONObject(json);
			map = convertJSONObjectToMap(jo);
		} catch (Exception e) {
			Log.e(LOG_JSON_ERROR, e.getMessage());
		}
		return map;
	}

	/**
	 * ��json�ַ���ת��Ϊ����Map��List����
	 * 
	 * @param json
	 * @return
	 */
	public static List<Map<String, Object>> toMapList(String json) {
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		try {
			JSONArray jsonArray = new JSONArray(json);
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jo = jsonArray.getJSONObject(i);
				Map<String, Object> map = convertJSONObjectToMap(jo);
				mapList.add(map);
			}
		} catch (JSONException e) {
			Log.e(LOG_JSON_ERROR, e.getMessage());
		}
		return mapList;
	}

	/**
	 * ��һ��JSONObject����ת��ΪMap
	 * 
	 * @param jo
	 * @return
	 * @throws JSONException
	 */
	private static Map<String, Object> convertJSONObjectToMap(JSONObject jo)
			throws JSONException {
		Map<String, Object> map = new HashMap<String, Object>();
		JSONObject newJo = mergeJsonNodes(jo);

		JSONArray names = newJo.names();
		for (int i = 0; i < names.length(); i++) {
			String key = names.getString(i);
			Object value = newJo.get(key);
			if ((value != null) && (!value.toString().equals(""))
					&& (!value.toString().equals("null"))) {
				map.put(key, value);
			}
		}
		return map;
	}

	/**
	 * ��JSON����ķ�һ���ӽ����һ�׽��ϲ�
	 * 
	 * @param oldJo
	 *            ������һ�׽���Json����
	 * @return ���غϲ�֮��ģ�ֻ����һ�׽���Json����
	 */
	private static JSONObject mergeJsonNodes(JSONObject oldJo)
			throws JSONException {
		JSONObject newJo = oldJo;
		JSONArray names = newJo.names();
		List<String> delKeys = new ArrayList<String>(); // ��ɾ���ķ�һ�׽���Json�����key

		// �ҳ���Ҫ�ϲ����ӽ���key
		for (int i = 0; i < names.length(); i++) {
			String key = names.getString(i);
			if (newJo.optJSONObject(key) != null) {
				delKeys.add(key);
			}
		}
		// �ϲ��ҵ����ӽ����һ�׽�㣬��ɾ��ԭ�ȵ��ӽ��
		for (String key : delKeys) {
			JSONObject subJo = newJo.getJSONObject(key);
			subJo = mergeJsonNodes(subJo); // �ݹ������ӽ��������ӽ��
			newJo = merge(newJo, subJo);
			newJo.remove(key);
		}
		return newJo;
	}

	/**
	 * �ϲ�����JSON����
	 * 
	 * @param jo1
	 * @param jo2
	 * @return ���غϲ�֮���JSON����
	 */
	private static JSONObject merge(JSONObject jo1, JSONObject jo2)
			throws JSONException {
		JSONObject newJo = jo1;
		JSONArray names = jo2.names();
		for (int i = 0; i < names.length(); i++) {
			String key = names.getString(i);
			newJo.put(key, jo2.get(key));
		}
		return newJo;
	}

	/**
	 * �ж�һ��JSON�ַ����Ƿ��ǿ�����
	 * 
	 * @param json
	 * @return
	 */
	public static boolean isJsonNull(String json) {
		if (json == null || json.equals("") || json.equals("null")
				|| json.equals("{}") || json.equals("[]")) {
			return true;
		} else {
			return false;
		}
	}
}