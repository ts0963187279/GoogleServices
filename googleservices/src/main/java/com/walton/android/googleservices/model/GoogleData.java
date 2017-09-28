package com.walton.android.googleservices.model;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.content.Context;
import com.google.gdata.client.GoogleService;
import java.util.List;
import java.util.Map;
import poisondog.core.Mission;
import poisondog.core.NoMission;

/**
 * Created by waltonmis on 2017/9/19.
 */

public class GoogleData {
	private int mPickAccountCode = 1;
	private int mAuthenticateCode = 2;
	private AccountManager accountManager;
	private Account selectedAccount;
	private Activity activity;
	private GoogleService googleService;
//	private Mission<Map<String, List<String>>> mHandler;
	private Mission<GoogleData> getToken;

	/**
	 * Constructor
	 */
	public GoogleData() {
//		mHandler = new NoMission<Map<String, List<String>>>();
	}

	public void setActivity(Activity activity){
		this.activity = activity;
		this.accountManager = (AccountManager) activity.getSystemService(Context.ACCOUNT_SERVICE);
	}
	public void setSelectedAccount(Account selectedAccount){
		this.selectedAccount = selectedAccount;
	}
	public Account getSelectedAccount(){
		return selectedAccount;
	}
	public AccountManager getAccountManager(){
		return accountManager;
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
		return activity;
	}
//	private void setHandler(Mission<Map<String, List<String>>> handler) {
//		mHandler = handler;
//	}
//	private Mission<Map<String, List<String>>> getHandler() {
//		return mHandler;
//	}
	public void setService(GoogleService service) {
		googleService = service;
	}
	public GoogleService getService() {
		return googleService;
	}
	public void setTokenHandler(Mission<GoogleData> handler) {
		getToken = handler;
	}
	public void executeTokenHandler(GoogleData data) throws Exception {
		getToken.execute(data);
	}
}
