package com.walton.android.googleservices.processor;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.content.Intent;
import com.walton.android.googleservices.model.GoogleData;

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
		int PICK_ACCOUNT_REQUEST = googleData.getPickAccountCode();
		int REQUEST_AUTHENTICATE = googleData.getAuthenticateCode();
		if(requestCode == PICK_ACCOUNT_REQUEST && resultCode == Activity.RESULT_OK) {
			pickAccount(googleData);
		}
		if(requestCode == REQUEST_AUTHENTICATE && resultCode == Activity.RESULT_OK) {
			requestToken(googleData);
		}
	}

	private void pickAccount(GoogleData googleData) {
		ExtractGoogleAccount task = new ExtractGoogleAccount(googleData.getActivity());
		googleData.setSelectedAccount(task.execute(data));
		googleData.getService().getGoogleToken();
	}

	private void requestToken(GoogleData googleData) {
		googleData.getService().getGoogleToken();
	}
}
