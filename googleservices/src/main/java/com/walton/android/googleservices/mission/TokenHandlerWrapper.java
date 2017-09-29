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
import android.app.Activity;
import com.walton.android.googleservices.mission.RequestToken;
import com.walton.android.googleservices.model.GoogleData;
import poisondog.core.Mission;

/**
 * Created by waltonmis on 2017/9/19.
 */

public class TokenHandlerWrapper implements Mission<TokenHandlerWrapper.Parameter> {
	private Mission<GoogleData> mHandler;

	/**
	 * Constructor
	 */
	public TokenHandlerWrapper(Mission<GoogleData> handler) {
		mHandler = handler;
	}

	@Override
	public Void execute(TokenHandlerWrapper.Parameter parameter) {
		final GoogleData data = parameter.mData;
		Activity activity = data.getActivity();
		if (activity == null)
			throw new IllegalArgumentException("GoogleData has null Activity !!");
		Account account = data.getSelectedAccount();
		if (account == null)
			throw new IllegalArgumentException("GoogleData has null Account !!");
		String tokenType = data.getTokenType();
		int requestCode = parameter.mRequestCode;
		RequestToken mRequest = new RequestToken();
		mRequest.execute(new RequestToken.Parameter(activity, account, tokenType, requestCode, new Mission<String>() {
			@Override
			public Void execute(String token) throws Exception {
				data.getService().setUserToken(token);
				mHandler.execute(data);
				return null;
			}
		}));
		return null;
	}

	public static class Parameter {
		private int mRequestCode;
		private GoogleData mData;

		/**
		 * Constructor
		 */
		public Parameter(int requestCode, GoogleData data) {
			mRequestCode = requestCode;
			mData = data;
		}
	}

}
