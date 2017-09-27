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

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import poisondog.core.Mission;

/**
 * @author Adam Huang
 * @since 2017-09-27
 */
public class ExtractGoogleAccount implements Mission<Intent> {
	private AccountManager mManager;

	/**
	 * Constructor
	 */
	public ExtractGoogleAccount(Activity activity) {
		mManager = (AccountManager) activity.getSystemService(Context.ACCOUNT_SERVICE);
	}

	@Override
	public Account execute(Intent data) {
		String accountName = data.getStringExtra(AccountManager.KEY_ACCOUNT_NAME);
		for (Account a : mManager.getAccounts()) {
			if (a.name.equals(accountName)) {
				return a;
			}
		}
		return null;
	}
}
