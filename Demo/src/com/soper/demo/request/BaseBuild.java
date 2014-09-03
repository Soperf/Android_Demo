package com.soper.demo.request;

import java.util.Map;

import org.json.JSONObject;

import android.os.Handler;
import android.os.Message;

public abstract class BaseBuild {

	public static final int Request_Success = 10001;
	public static final int Request_Error = Request_Success + 1;
	public static final int Request_NetWork_Error = Request_Error + 1;

	public Handler handler;

	public BaseBuild(Handler handler) {
		this.handler = handler;
	}

	// 获取请求的url
	public abstract String getRequsetUrl(Map<String, Object> map);

	// 解析Json
	public abstract boolean parseParam(JSONObject json,
			Map<String, String> header);

	public void parse(JSONObject json, Map<String, String> header) {

		int status = json.optInt("status");
		parseParam(json, header);
//		if (status == 200) {
//			parseParam(json, header);
//		} else {
//			String msg = json.optString("msg");
//			Message msgMessage = Message.obtain();
//			msgMessage.what = HttpRequestConstant.CODE_FAILED;
//			msgMessage.arg1 = Request_Error;
//			msgMessage.obj = msg;
//			if (null != handler) {
//				handler.sendMessage(msgMessage);
//			}
//		}
	}

	public void RequestError(String error) {
		Message msgMessage = Message.obtain();
		msgMessage.what = HttpRequestConstant.CODE_FAILED;
		msgMessage.arg1 = Request_NetWork_Error;
		msgMessage.obj = error;
		if (handler != null) {
			handler.sendMessage(msgMessage);
		}
	}

}
