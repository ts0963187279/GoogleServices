/*
 * Copyright (C) 2017 Adam Huang <poisondog@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.walton.android.googleservices.processor;

import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import com.walton.android.googleservices.model.GoogleData;

/**
 * Created by waltonmis on 2017/8/29.
 */

public class OnTokenAcquired implements AccountManagerCallback<Bundle> {
	private Activity activity;
	private final int REQUEST_AUTHENTICATE;
	private GoogleServices googleServices;
	private AsyncTask<Void,Void,Void> asyncTask;

	public OnTokenAcquired(GoogleData googleData, AsyncTask<Void,Void,Void> asyncTask) {
		activity = googleData.getActivity();
		googleServices = googleData.getService();
		this.asyncTask = asyncTask;
		REQUEST_AUTHENTICATE = googleData.getAuthenticateCode();
	}

	@Override
	public void run(AccountManagerFuture<Bundle> result) {
		try{
			Bundle bundle = result.getResult();
			if(bundle.containsKey(AccountManager.KEY_INTENT)){
				Intent intent = bundle.getParcelable(AccountManager.KEY_INTENT);
//				int flags = intent.getFlags();
//				intent.setFlags(flags);
				activity.startActivityForResult(intent, REQUEST_AUTHENTICATE);
				return;
			}
			if(bundle.containsKey(AccountManager.KEY_AUTHTOKEN)){
				final String authToken = bundle.getString(AccountManager.KEY_AUTHTOKEN);
				googleServices.setUserToken(authToken);
				asyncTask.execute();
			}
		}catch (Exception e){
			System.out.println(e);
		}
	}

}
