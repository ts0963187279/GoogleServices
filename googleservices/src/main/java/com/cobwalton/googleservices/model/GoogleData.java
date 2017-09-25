package com.cobwalton.googleservices.model;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;

import com.cobwalton.googleservices.processor.GoogleServices;
import com.cobwalton.googleservices.processor.OnBackGroundResult;


/**
 * Created by waltonmis on 2017/9/19.
 */

public class GoogleData {
    private final int PICK_ACCOUNT_REQUEST = 1;
    private final int REQUEST_AUTHENTICATE = 2;
    private AccountManager accountManager;
    private Account selectedAccount;
    private Activity activity;
    private OnBackGroundResult onBackGroundResult;
    private GoogleServices googleServices;
    public void setActivity(Activity activity){
        this.activity = activity;
    }
    public void setSelectedAccount(Account selectedAccount){
        this.selectedAccount = selectedAccount;
    }
    public void setAccountManager(AccountManager accountManager){
        this.accountManager = accountManager;
    }
    public Account getSelectedAccount(){
        return selectedAccount;
    }
    public AccountManager getAccountManager(){
        return accountManager;
    }
    public int getPICK_ACCOUNT_REQUEST(){
        return PICK_ACCOUNT_REQUEST;
    }
    public int getREQUEST_AUTHENTICATE(){
        return REQUEST_AUTHENTICATE;
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
