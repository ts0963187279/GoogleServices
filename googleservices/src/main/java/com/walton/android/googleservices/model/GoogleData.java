package com.walton.android.googleservices.model;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.content.Context;
import com.walton.android.googleservices.processor.GoogleServices;
import com.walton.android.googleservices.processor.OnBackGroundResult;

/**
 * Created by waltonmis on 2017/9/19.
 */

public class GoogleData {
	private int mPickAccountCode = 1;
	private int mAuthenticateCode = 2;
	private AccountManager accountManager;
	private Account selectedAccount;
	private Activity activity;
	private OnBackGroundResult onBackGroundResult;
	private GoogleServices googleServices;
	public void setActivity(Activity activity){
		this.activity = activity;
		this.accountManager = (AccountManager) activity.getSystemService(Context.ACCOUNT_SERVICE);
	}
	public void setSelectedAccount(Account selectedAccount){
		this.selectedAccount = selectedAccount;
	}
//	public void setAccountManager(AccountManager accountManager){
//		this.accountManager = accountManager;
//	}
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
	public void setOnBackGroundResult(OnBackGroundResult onBackGroundResult){
		this.onBackGroundResult = onBackGroundResult;
	}
	public OnBackGroundResult onBackGroundResult(){
		return onBackGroundResult;
	}
	public void setService(GoogleServices service){
		this.googleServices = service;
	}
	public GoogleServices getService(){
		return googleServices;
	}
}
