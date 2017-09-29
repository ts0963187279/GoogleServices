/*
 * Copyright (C) 2017 RS Wong <ts0963187279@gmail.com>
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

import android.app.Activity;
import android.content.Intent;
import com.google.android.gms.common.AccountPicker;
import poisondog.core.Mission;

/**
 * @author Adam Huang
 * @since 2017-09-27
 */
public class RequestGoogleAccount implements Mission<Integer> {
	private Activity mActivity;

	/**
	 * Constructor
	 */
	public RequestGoogleAccount(Activity activity) {
		mActivity = activity;
	}

	@Override
	public Void execute(Integer requestCode) {
		Intent intent = AccountPicker.newChooseAccountIntent(null,null,new String[]{"com.google"},false,null,null,null,null);
		mActivity.startActivityForResult(intent, requestCode);
		return null;
	}
}
