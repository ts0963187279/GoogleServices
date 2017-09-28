package com.walton.android.googleservices.model;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.content.Context;
import com.google.gdata.client.GoogleService;
import com.walton.android.googleservices.mission.TokenHandlerWrapper;
import poisondog.core.Mission;

/**
 * Created by waltonmis on 2017/9/19.
 */

public class GoogleData {
	private int mPickAccountCode = 1;
	private int mAuthenticateCode = 2;
	private Activity mActivity;
	private AccountManager mManager;
	private Account mAccount;
	private GoogleService mService;
	private String mTokenType;
	private Mission<GoogleData> mHandler;

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
	public int getPickAccountCode(){
		return mPickAccountCode;
	}
	public int getAuthenticateCode(){
		return mAuthenticateCode;
	}
	public void setPickAccountCode(int code) {
		mPickAccountCode= code;
	}
	public void setAuthenticateCode(int code) {
		mAuthenticateCode = code;
	}
	public Activity getActivity(){
		return mActivity;
	}
	public void setService(GoogleService service) {
		mService = service;
	}
	public GoogleService getService() {
		return mService;
	}
	public void setTokenHandler(Mission<GoogleData> handler) {
		mHandler = new TokenHandlerWrapper(handler);
	}
	public void executeTokenHandler(GoogleData data) throws Exception {
		mHandler.execute(data);
	}
	public void setTokenType(String type) {
		mTokenType = type;
	}
	public String getTokenType() {
		return mTokenType;
	}
}
