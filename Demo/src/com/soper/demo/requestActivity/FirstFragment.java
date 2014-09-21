package com.soper.demo.requestActivity;

import roboguice.fragment.RoboFragment;
import roboguice.inject.InjectView;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request.Method;
import com.soper.demo.R;
import com.soper.demo.myAppliction.MyApplication;
import com.soper.demo.request.JSONRequest;
import com.soper.demo.requst.build.TestBuild;
import com.umeng.update.UmengUpdateAgent;

public class FirstFragment extends RoboFragment {

	@InjectView(R.id.textView)
	TextView textView;


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_first, container,
				false);

		return rootView;

	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		TestBuild testBuild = new TestBuild(handler);
		JSONRequest.getInstance()
				.requestData(null, Method.GET, null, testBuild);
	}

	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			String jsonString = (String) msg.obj;

			textView.setText(jsonString.toString());
			switch (msg.what) {

			default:
				break;
			}
		};
	};

}
