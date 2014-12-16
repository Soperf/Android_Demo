package com.soper.demo;

import org.kymjs.aframe.ui.activity.KJFragmentActivity;
import org.kymjs.aframe.ui.fragment.BaseFragment;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.ActionBar.OnNavigationListener;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;

import com.soper.demo.myAppliction.MyApplication;
import com.soper.demo.requestActivity.FirstFragment;
import com.soper.demo.update.updateFragment;
import com.umeng.analytics.MobclickAgent;
import com.umeng.fb.FeedbackAgent;
import com.umeng.message.PushAgent;
import com.umeng.update.UmengUpdateAgent;

public class MainActivity extends KJFragmentActivity {

	private OnNavigationListener navigationListener;
	private ActionBar actionBar;

	@Override
	public void setRootView() {
		setContentView(R.layout.activity_main);
	}

	//test codereview
	
	@Override
	public void changeFragment(BaseFragment arg0) {
		changeFragment(R.id.container, arg0);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initUmeng();

		initNavigationListener();
		initActionBar();

		actionBar.setDisplayShowTitleEnabled(false);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);

	}

	private void initUmeng() {
		// 初始化友盟push服务
		PushAgent mPushAgent = PushAgent.getInstance(MyApplication.context);
		mPushAgent.enable();

		// 初始化友盟下载更新服务
		UmengUpdateAgent.setUpdateOnlyWifi(false);
		UmengUpdateAgent.update(MyApplication.context);
		UmengUpdateAgent.setUpdateAutoPopup(true);

	}

	@Override
	protected void onResume() {
		super.onResume();
		MobclickAgent.onResume(this);
	}

	@Override
	protected void onPause() {
		super.onPause();
		MobclickAgent.onPause(this);
	}

	@SuppressLint("NewApi")
	private void initActionBar() {
		actionBar = getActionBar();
		actionBar.setDisplayShowTitleEnabled(false);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
		actionBar.setListNavigationCallbacks(
				new ArrayAdapter<String>(actionBar.getThemedContext(),
						android.R.layout.simple_list_item_1,
						android.R.id.text1, new String[] { "volleyRequst",
								"update", "NO.3", }), navigationListener);

	}

	private void initNavigationListener() {
		navigationListener = new OnNavigationListener() {
			BaseFragment fragment;

			@Override
			public boolean onNavigationItemSelected(int itemPosition,
					long itemId) {
				switch (itemPosition) {
				case 0:
					fragment = new FirstFragment();

					changeFragment(fragment);

					break;

				case 1:
					fragment = new updateFragment();
					changeFragment(fragment);
					break;

				case 2:

					break;

				default:
					break;
				}

				return false;
			}
		};
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			FeedbackAgent agent = new FeedbackAgent(MainActivity.this);
			agent.sync();
			agent.startFeedbackActivity();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
