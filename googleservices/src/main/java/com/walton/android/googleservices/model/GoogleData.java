package com.walton.android.googleservices.model;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.content.Context;
import com.google.gdata.client.contacts.ContactsService;
import com.google.gdata.client.GoogleService;
import com.google.gdata.client.photos.PicasawebService;
import com.walton.android.googleservices.mission.TokenHandlerWrapper;
import com.walton.android.googleservices.model.GoogleData;
import poisondog.core.Mission;

/**
 * Created by waltonmis on 2017/9/19.
 */

public class GoogleData {
	private Activity mActivity;
	private AccountManager mManager;
	private Account mAccount;
	private GoogleService mService;
	private String mTokenType;
	private Mission<TokenHandlerWrapper.Parameter> mHandler;

	/**
	 * Constructor
	 */
	private GoogleData() {
	}

	public static GoogleData getInstance(PicasawebService service) {
		GoogleData instance = new GoogleData();
		instance.setService(service);
		instance.setTokenType("lh2");
		return instance;
	}

	public static GoogleData getInstance(ContactsService service) {
		GoogleData instance = new GoogleData();
		instance.setService(service);
		instance.setTokenType("cp");
		return instance;
	}

	public void setActivity(Activity activity){
		mActivity = activity;
		this.mManager = (AccountManager) activity.getSystemService(Context.ACCOUNT_SERVICE);
	}
	public void setSelectedAccount(Account selectedAccount){
		mAccount = selectedAccount;
	}
	public Account getSelectedAccount(){
		return mAccount;
	}
	public AccountManager getAccountManager(){
		return mManager;
	}
	public Activity getActivity(){
		return mActivity;
	}
	private void setService(GoogleService service) {
		mService = service;
	}
	public GoogleService getService() {
		return mService;
	}
	public void setHandler(Mission<GoogleData> handler) {
		mHandler = new TokenHandlerWrapper(handler);
	}
	public void requestToken(int requestCode) throws Exception {
		mHandler.execute(new TokenHandlerWrapper.Parameter(requestCode, this));
	}
	private void setTokenType(String type) {
		mTokenType = type;
	}
	public String getTokenType() {
		return mTokenType;
	}
}
