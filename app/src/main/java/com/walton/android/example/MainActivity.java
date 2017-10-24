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
package com.walton.android.example;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.gdata.client.photos.PicasawebService;
import com.walton.android.googleservices.mission.ExtractGoogleAccount;
import com.walton.android.googleservices.mission.RequestGoogleAccount;
import com.walton.android.googleservices.module.GoogleData;

public class MainActivity extends AppCompatActivity {
	private final int PICK_ACCOUNT_REQUEST = 1;
	private final int REQUEST_AUTHENTICATE = 2;
	private GoogleData googleData;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(com.walton.android.example.R.layout.activity_main);

//		googleData = GoogleData.getInstance(new ContactsService("GoogleServices"));
//		googleData.setHandler(new ShowContacts());
		googleData = GoogleData.getInstance(new PicasawebService("GoogleServices"));
		googleData.setHandler(new ShowPhotos());

		googleData.setActivity(this);

		RequestGoogleAccount request = new RequestGoogleAccount(this);
		request.execute(PICK_ACCOUNT_REQUEST);
	}

	protected void onActivityResult(final int requestCode,final int resultCode,final Intent data){
		if(requestCode == PICK_ACCOUNT_REQUEST && resultCode == Activity.RESULT_OK) {
			ExtractGoogleAccount task = new ExtractGoogleAccount(googleData.getActivity());
			googleData.setSelectedAccount(task.execute(data));
			requestToken(googleData);
		}
		if(requestCode == REQUEST_AUTHENTICATE && resultCode == Activity.RESULT_OK) {
			requestToken(googleData);
		}
	}

	private void requestToken(GoogleData googleData) {
		try {
			googleData.requestToken(REQUEST_AUTHENTICATE);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
