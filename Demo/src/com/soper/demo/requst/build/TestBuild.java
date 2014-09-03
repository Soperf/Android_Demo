package com.soper.demo.requst.build;

import java.util.Map;

import org.json.JSONObject;

import com.soper.demo.request.BaseBuild;

import android.os.Handler;
import android.os.Message;

public class TestBuild extends BaseBuild {

	public TestBuild(Handler handler) {
		super(handler);
	}

	@Override
	public String getRequsetUrl(Map<String, Object> map) {
		return "http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=json&ip=218.4.255.255";
	}

	@Override
	public boolean parseParam(JSONObject json, Map<String, String> header) {
		// TODO Auto-generated method stub
		Message msg = Message.obtain();
		msg.what = 0000011;
		msg.obj = json.toString();
		handler.sendMessage(msg);
		return true;
	}

}
