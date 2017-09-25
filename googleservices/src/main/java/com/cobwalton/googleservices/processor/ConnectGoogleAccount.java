package com.cobwalton.googleservices.processor;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Intent;


import com.cobwalton.googleservices.model.GoogleData;

import static android.app.Activity.RESULT_OK;

/**
 * Created by waltonmis on 2017/8/29.
 */

public class ConnectGoogleAccount {
    private int requestCode;
    private int resultCode;
    private Intent data;
    public ConnectGoogleAccount(final int requestCode , final int resultCode , final Intent data){
        this.requestCode = requestCode;
        this.resultCode = resultCode;
        this.data = data;
    }
    public void connect(GoogleData googleData){
        Account[] accountList = googleData.getAccountManager().getAccounts();
        int PICK_ACCOUNT_REQUEST = googleData.getPICK_ACCOUNT_REQUEST();
        int REQUEST_AUTHENTICATE = googleData.getREQUEST_AUTHENTICATE();
        if(requestCode == PICK_ACCOUNT_REQUEST && resultCode == RESULT_OK) {
            String accountName = data.getStringExtra(AccountManager.KEY_ACCOUNT_NAME);
            for (Account a : accountList) {
                if (a.name.equals(accountName)) {
                    googleData.setSelectedAccount(a);
                    break;
                }
            }
            googleData.getService().getGoogleToken();
        }
        if(requestCode == REQUEST_AUTHENTICATE && resultCode == RESULT_OK) {
            googleData.getService().getGoogleToken();
        }
    }
}
