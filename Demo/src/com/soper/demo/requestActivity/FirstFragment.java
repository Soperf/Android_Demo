package com.soper.demo.requestActivity;

import org.kymjs.aframe.ui.BindView;
import org.kymjs.aframe.ui.fragment.BaseFragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Request.Method;
import com.soper.demo.R;
import com.soper.demo.request.JSONRequest;
import com.soper.demo.requst.build.TestBuild;

public class FirstFragment extends BaseFragment {

	@BindView(id=R.id.textView)
	TextView textView;



	@Override
	protected View inflaterView(LayoutInflater inflater, ViewGroup container,
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
			IpData jsonString = (IpData) msg.obj;
			
			textView.setText(jsonString.toString());
			switch (msg.what) {

			default:
				break;
			}
		};
	};



}
