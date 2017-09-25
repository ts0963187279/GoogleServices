package com.walton.android.googleservices.processor;

import android.accounts.AccountManager;
import android.app.Activity;
import android.content.Intent;

import com.walton.android.googleservices.model.GoogleData;
import com.google.android.gms.common.AccountPicker;

import static android.content.Context.ACCOUNT_SERVICE;

/**
 * Created by waltonmis on 2017/8/30.
 */

public class SelectGoogleAccount {
    Activity activity;
    AccountManager accountManager;
    private GoogleData mGoogleData;
    public SelectGoogleAccount(GoogleData googleData, Activity activity){
        this.activity = activity;
	mGoogleData = googleData;
        accountManager = (AccountManager) activity.getSystemService(ACCOUNT_SERVICE);
        googleData.setActivity(activity);
        googleData.setAccountManager(accountManager);
    }
    public void select(){
        Intent intent = AccountPicker.newChooseAccountIntent(null,null,new String[]{"com.google"},false,null,null,null,null);
        activity.startActivityForResult(intent,mGoogleData.getPickAccountCode());
    }
}
