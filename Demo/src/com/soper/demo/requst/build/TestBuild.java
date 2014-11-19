package com.soper.demo.requst.build;

import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.soper.demo.request.BaseBuild;
import com.soper.demo.requestActivity.IpData;

import android.os.Handler;
import android.os.Message;

public class TestBuild extends BaseBuild {

	public TestBuild(Handler handler) {
		super(handler);
	}

	@Override
	public String getRequsetUrl(Map<String, Object> map) {
		return "http://ip.taobao.com/service/getIpInfo.php?ip=42.120.74.106";
	}

	@Override
	public boolean parseParam(JSONObject json, Map<String, String> header) {
		// TODO Auto-generated method stub
		Message msg = Message.obtain();
		msg.what = 0000011;
		
		
		JSONObject dataJObject = json.getJSONObject("data");
		IpData ipData = JSON.parseObject(dataJObject.toJSONString(),
				IpData.class);

		System.out.println("TestBuild.parseParam()----" + ipData.toString());
		msg.obj = ipData;
		handler.sendMessage(msg);
		return true;
	}

}
