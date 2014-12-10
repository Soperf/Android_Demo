package com.soper.demo.update;

import org.kymjs.aframe.ui.BindView;
import org.kymjs.aframe.ui.fragment.BaseFragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.soper.demo.R;
import com.soper.demo.myAppliction.MyApplication;
import com.umeng.update.UmengUpdateAgent;

public class updateFragment extends BaseFragment {

	@BindView(id= R.id.update_button)
	Button updateButton;

	@Override
	protected View inflaterView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_update, container,
				false);

		return rootView;

	}

	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		updateButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				UmengUpdateAgent.forceUpdate(MyApplication.context);

			}
		});
	}

}
