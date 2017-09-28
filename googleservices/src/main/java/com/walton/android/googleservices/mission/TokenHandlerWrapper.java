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

public class TokenHandlerWrapper implements Mission<GoogleData> {
	private Mission<GoogleData> mHandler;

	/**
	 * Constructor
	 */
	public TokenHandlerWrapper(Mission<GoogleData> handler) {
		mHandler = handler;
	}

	@Override
	public Void execute(final GoogleData data) {
		Activity activity = data.getActivity();
		Account account = data.getSelectedAccount();
		String tokenType = data.getTokenType();
		int requestCode = data.getAuthenticateCode();
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

}
