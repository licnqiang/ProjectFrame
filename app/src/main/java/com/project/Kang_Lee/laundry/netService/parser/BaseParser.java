package com.project.Kang_Lee.laundry.netService.parser;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.json.JSONException;
import org.json.JSONObject;
import android.os.Handler;
import android.os.Message;
import com.project.Kang_Lee.laundry.netService.data.BaseReseponseInfo;
import com.project.Kang_Lee.laundry.netService.http.HttpConnector;
import com.project.Kang_Lee.laundry.netService.http.UrlConfig;
import com.project.Kang_Lee.laundry.util.FileUtil;
import com.project.Kang_Lee.laundry.util.Log;
import com.project.Kang_Lee.laundry.util.MD5Utils;
import com.project.Kang_Lee.laundry.base.BaseApplication;

/**
 * 基础解析类
 * 
 * @author jigsaw
 */
public abstract class BaseParser<T extends BaseReseponseInfo> extends Thread {
	public final static String MSG_ERRO = "网络太差, 连接失败了";

	public final static String MSG_NULL = "暂无";

	protected JSONObject mJson;

	protected static String CLASS_NAME;

	protected final static String TAG = "parser";

	public final static int WHAT_SUCCESS = 0;// 成功

	public final static int WHAT_ERROR = 1;// 失败

	private String urlBody;// 请求URL

	private HashMap<String, Object> parameters;// 需要提交的参数

	private String requestMethod;// 请求方法

	private String requestContentType;// Body体类型

	private T returnInfo;// 返回的数据结构

	protected boolean isTest;// 是否是测试数据

	protected String testFileName;// 本地模拟数据名称
	protected HashMap<String, String> httpHeadInfo = new HashMap<String, String>();// 本地模拟数据名称

	public void addHttpHeadInfo(String key, String value) {
		this.httpHeadInfo.put(key, value);
	}

	public void setUrlBody(String urlBody) {
		this.urlBody = urlBody;
	}

	public void setParameters(HashMap<String, Object> parameters) {
		this.parameters = parameters;
	}

	public void setRequestMethod(String requestMethod) {
		this.requestMethod = requestMethod;
	}

	public void setRequestContentType(String requestContentType) {
		this.requestContentType = requestContentType;
	}

	public void setReturnInfo(T returnInfo) {
		this.returnInfo = returnInfo;
	}

	public void setTest(boolean isTest) {
		this.isTest = isTest;
	}

	public void setTestFileName(String testFileName) {
		this.testFileName = testFileName;
	}

	public BaseParser() {
		CLASS_NAME = this.getClass().getName();
	}

	@Override
	public void run() {

		byte[] b = new byte[0];
		try {
			b = CreatPost();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		String url = UrlConfig.URL_PREFIX + urlBody;
		Log.e("http", "http--url==" + url);
		Log.e("http", "http--requestMethod==" + requestMethod);

		if (requestMethod == HttpConnector.METHOD_GET) {
			url = url + "?" + CreatPostString();
		}
		BaseReseponseInfo mBaseResponseInfo = getBaseReseponseInfo(url, b);

		if (mBaseResponseInfo.getFlag() == BaseReseponseInfo.SUCCESS) {
			mHandler.sendEmptyMessage(WHAT_SUCCESS);
		} else {
			mHandler.sendEmptyMessage(WHAT_ERROR);
		}

	}

	protected Handler mHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case WHAT_SUCCESS:
				Success();
				break;

			case WHAT_ERROR:
				Error();
				break;
			}
		}

	};

	/**
	 * post参数为byte数组
	 * 
	 * @param url
	 * @param postData
	 */
	protected BaseReseponseInfo getBaseReseponseInfo(String url, byte[] postData) {

		T mInfo = returnInfo;
		if (mInfo == null) {
			mInfo = (T) new BaseReseponseInfo();
		}
		String result = "";
		if (isTest) {
			InputStream in = null;
			try {
				in = BaseApplication.ApplicationContext.getAssets().open(
						testFileName);
				result = FileUtil.ToString(in);
				Log.e("http", "Http本地--" + result);
			} catch (IOException e) {
				Log.e("http", "BaseParser--e--local==" + e);
			}
		} else {
			HttpConnector mHttpConnector = new HttpConnector();
			mHttpConnector.setRequestMethod(requestMethod);

			String token = BaseApplication.token;
			//String postData2=new String(postData).replace("\\", "");
			//Log.e("postData2", postData2);
			String check = MD5Utils.MD5Encode("ym"+BaseApplication.token,"UTF-8");
//			Log.e("postData", new String(postData));
			Log.e("check", check);
			mHttpConnector.setRequestProperty("check", check);

			Iterator iter = httpHeadInfo.entrySet().iterator();
			while (iter.hasNext()) {
				Map.Entry entry = (Map.Entry) iter.next();
				String key = entry.getKey().toString();
				String val = entry.getValue().toString();
				mHttpConnector.setRequestProperty(key, val);
			}

			if (requestContentType != null && requestContentType.length() > 0) {
				mHttpConnector.setRequestContentType(requestContentType);
			}
			int tag = mHttpConnector.connect(url, postData);
			if (tag != HttpConnector.CONNECT_OK) {
				Log.e("http", "Http响应-ERROR--" + tag);
				mInfo.setInfo(MSG_ERRO);
				return mInfo;
			}
			result = mHttpConnector.getDataString();
			Log.e("http", "Http响应--" + result);
		}

		try {
			mJson = new JSONObject(result);
			int flag = mJson.optInt("code");
			String info = mJson.optString("msg");
			mInfo.setFlag(flag);
			mInfo.setInfo(info);
		} catch (JSONException e) {
			Log.e("http", "BaseParser--e==" + e);
		}

		int flagI = mInfo.getFlag();
		if (flagI == BaseReseponseInfo.SUCCESS) {
			try {
				// 解析
				parser();
			} catch (Exception e) {
				Log.e("http", "解析错误--e==" + e);
				Log.e("http", "解析错误--e==" + e);
				Log.e("http", "解析错误--e==" + e);
			}
		} else {
			Log.e("http", "BaseParser==code:" + flagI);
		}
		return mInfo;

	}

	// 解析
	protected abstract void parser() throws Exception;

	// 获取数据成功
	protected abstract void Success();

	// 获取数据失败
	protected abstract void Error();

//	protected byte[] CreatPost() {
//		HashMap<String, Object> mMap = parameters;
//
//		String token = BaseApplication.token;
//
//		if (token != null && token.length() > 0) {
//			mMap.put("token", token);
//		}
//		mMap.put("OS", BaseApplication.os);
//		mMap.put("version", BaseApplication.version);
//		Iterator iter = mMap.entrySet().iterator();
//		JSONObject mObject = new JSONObject();
//		while (iter.hasNext()) {
//			Map.Entry entry = (Map.Entry) iter.next();
//			String key = entry.getKey().toString();
//			Object value = entry.getValue();
//
//			try {
//				mObject.put(key, value);
//			} catch (JSONException e) {
//				e.printStackTrace();
//			}
//		}
//
//		byte[] b = null;
//		String s = mObject.toString();
//		Log.e("http", "http请求参数==" + s);
//		if (s != null && s.length() > 0) {
//			b = s.getBytes();
//		}
//
//		return b;
//
//	}


	protected byte[] CreatPost() throws UnsupportedEncodingException {
		HashMap<String, Object> mMap = parameters;
		String token = BaseApplication.token;
		if (token != null && token.length() > 0) {
			mMap.put("token", token);
		}
		mMap.put("OS", BaseApplication.os);
		mMap.put("version", BaseApplication.version);
		// 遍历mao,把键值对拼接为参数字符串
		StringBuilder sb = new StringBuilder();
		Set<String> keys = mMap.keySet();
		Iterator<String> ite = keys.iterator();
		while (ite.hasNext()) {
			String key = ite.next();
			String val = (String) mMap.get(key);
			sb.append(key + "=" + val + "&");

		}
		if(null==sb) {
			// 把最后一个&字符移除
			sb.deleteCharAt(sb.length() - 1);
		}
		byte[] b = null;
		String s = sb.toString();

		if (s != null && s.length() > 0) {
			b = s.getBytes();
		}
		Log.e("http请求参数==",""+s);
		return b;

	}

	protected String CreatPostString() {
		HashMap<String, Object> mMap = parameters;
		String token = BaseApplication.token;

		if (token != null && token.length() > 0) {
			mMap.put("token", token);
		}
		mMap.put("OS", BaseApplication.os);
		mMap.put("version", BaseApplication.version);
		Iterator iter = mMap.entrySet().iterator();
		JSONObject mObject = new JSONObject();
		while (iter.hasNext()) {
			Map.Entry entry = (Map.Entry) iter.next();
			String key = entry.getKey().toString();
			Object value = entry.getValue();

			try {
				mObject.put(key, value);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}

		byte[] b = null;
		String s = mObject.toString();
		Log.e("http", "http请求参数==" + s);

		return s;

	}
}
