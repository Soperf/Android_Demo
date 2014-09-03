package com.soper.demo.request;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.NetworkResponse;
import com.android.volley.RequestQueue;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.soper.demo.myAppliction.MyApplication;

public class JSONRequest {

	private static RequestQueue mQueue;
	private static JSONRequest jsonRequest;

	private JSONRequest() {
		mQueue = Volley.newRequestQueue(MyApplication.context);
	}

	public static JSONRequest getInstance() {
		if (null == jsonRequest) {
			jsonRequest = new JSONRequest();
		}
		return jsonRequest;
	}

	public static RequestQueue getQueue() {
		if (null == mQueue) {
			mQueue = Volley.newRequestQueue(MyApplication.context);
		}
		return mQueue;
	}

	public void requestData(Map<String, Object> map, int method,
			String jsonRequest, BaseBuild baseBuild) {

		String url = baseBuild.getRequsetUrl(map);
		JsonObjectRequest request = new JsonObjectRequest(method, url,
				jsonRequest, new ResponseListener(baseBuild),
				new ErrorListener(baseBuild) {
				});
		request.setTag(baseBuild.hashCode());
		JSONRequest.getQueue().add(request);
	}

	class ResponseListener implements Listener<NetworkResponse> {

		private BaseBuild baseBuild;

		public ResponseListener(BaseBuild baseBuild) {
			this.baseBuild = baseBuild;
		}

		@Override
		public void onResponse(NetworkResponse arg0) {
			String jsonString;
			try {
				jsonString = new String(arg0.data,HttpHeaderParser.parseCharset(arg0.headers));
				baseBuild.parse(new JSONObject(jsonString), arg0.headers);
			} catch (JSONException e) {
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	class ErrorListener implements com.android.volley.Response.ErrorListener {

		private BaseBuild build;

		public ErrorListener(BaseBuild baseBuild) {
			this.build = baseBuild;
		}

		@Override
		public void onErrorResponse(VolleyError arg0) {
			if (build != null) {
				build.RequestError("链接出错！");
			}
		}

	}

	public boolean interruptThread(int tag) {
		try {
			if (mQueue != null) {
				mQueue.cancelAll(tag);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
