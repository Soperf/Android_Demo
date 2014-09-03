package com.soper.demo.update;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.soper.demo.R;
import com.soper.demo.myAppliction.MyApplication;
import com.umeng.fb.FeedbackAgent;
import com.umeng.update.UmengUpdateAgent;

public class updateFragment extends Fragment {

	private Button updateButton;
	private Context mContext;

	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		this.mContext = activity;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_update, container,
				false);
		
		updateButton = (Button) rootView.findViewById(R.id.update_button);
		updateButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				 UmengUpdateAgent.forceUpdate(MyApplication.context);
				
				
			}
		});
		return rootView;

	}

}
