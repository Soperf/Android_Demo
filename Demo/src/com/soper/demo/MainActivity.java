package com.soper.demo;

import com.soper.demo.myAppliction.MyApplication;
import com.soper.demo.requestActivity.FirstFragment;
import com.soper.demo.update.updateFragment;
import com.umeng.analytics.MobclickAgent;
import com.umeng.fb.FeedbackAgent;
import com.umeng.message.PushAgent;
import com.umeng.update.UmengUpdateAgent;
import com.umeng.update.UmengUpdateListener;
import com.umeng.update.UpdateResponse;
import com.umeng.update.UpdateStatus;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.OnNavigationListener;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

	private OnNavigationListener navigationListener;
	private ActionBar actionBar;

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

	private void initActionBar() {
		actionBar = getSupportActionBar();
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
			Fragment fragment;
			

			@Override
			public boolean onNavigationItemSelected(int itemPosition,
					long itemId) {
				FragmentTransaction fTransaction = getSupportFragmentManager()
						.beginTransaction();
				switch (itemPosition) {
				case 0:
					fragment = new FirstFragment();
					fTransaction.replace(R.id.container, fragment);
					
					break;

				case 1:
					fragment = new updateFragment();
					fTransaction.replace(R.id.container, fragment);
					fTransaction.addToBackStack(null);
					break;

				case 2:

					break;

				default:
					break;
				}
				
				fTransaction.commit();
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
