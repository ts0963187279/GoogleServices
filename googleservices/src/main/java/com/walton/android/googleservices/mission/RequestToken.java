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
package com.walton.android.googleservices.mission;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import poisondog.core.Mission;

/**
 * @author Adam Huang
 * @since 2017-09-27
 */
public class RequestToken implements Mission<RequestToken.Parameter> {

	@Override
	public Void execute(RequestToken.Parameter parameter) {
		AccountManager manager = (AccountManager) parameter.mActivity.getSystemService(Context.ACCOUNT_SERVICE);
		manager.getAuthToken(parameter.mAccount, parameter.mTokenType, null, parameter.mActivity, new TokenHandler(parameter), null);
		return null;
	}

	class TokenHandler implements AccountManagerCallback<Bundle> {
		private RequestToken.Parameter mParameter;

		/**
		 * Constructor
		 */
		public TokenHandler(RequestToken.Parameter parameter) {
			mParameter = parameter;
		}

		@Override
		public void run(AccountManagerFuture<Bundle> result) {
			try{
				Bundle bundle = result.getResult();
				if(bundle.containsKey(AccountManager.KEY_INTENT)){
					Intent intent = bundle.getParcelable(AccountManager.KEY_INTENT);
					mParameter.mActivity.startActivityForResult(intent, mParameter.mRequestCode);
					return;
				}
				if(bundle.containsKey(AccountManager.KEY_AUTHTOKEN)){
					final String authToken = bundle.getString(AccountManager.KEY_AUTHTOKEN);
					mParameter.mHandler.execute(authToken);
				}
			}catch (Exception e){
				e.printStackTrace();
			}
		}
	}

	public static class Parameter {
		private Activity mActivity;
		private Account mAccount;
		private String mTokenType;
		private int mRequestCode;
		private Mission<String> mHandler;

		/**
		 * Constructor
		 * @param tokenType example: "cp", "lh2". Reference site: https://wtanaka.com/node/8045
		 */
		public Parameter(Activity activity, Account account, String tokenType, int requestCode, Mission<String> handler) {
			mActivity = activity;
			mAccount = account;
			mTokenType = tokenType;
			mRequestCode = requestCode;
			mHandler = handler;
		}
	}
}
