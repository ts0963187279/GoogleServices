package com.walton.android.example;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.google.gdata.client.contacts.ContactsService;
import com.google.gdata.client.photos.PicasawebService;
import com.walton.android.googleservices.mission.ExtractGoogleAccount;
import com.walton.android.googleservices.mission.RequestGoogleAccount;
import com.walton.android.googleservices.model.GoogleData;

public class MainActivity extends AppCompatActivity {
	private GoogleData googleData;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(com.walton.android.example.R.layout.activity_main);

//		googleData = GoogleData.getInstance(new ContactsService("GoogleServices"));
//		googleData.setTokenHandler(new ShowContacts());
		googleData = GoogleData.getInstance(new PicasawebService("GoogleServices"));
		googleData.setTokenHandler(new ShowPhotos());

		googleData.setActivity(this);

		RequestGoogleAccount request = new RequestGoogleAccount(this);
		request.execute(googleData.getPickAccountCode());
	}

	protected void onActivityResult(final int requestCode,final int resultCode,final Intent data){
		int PICK_ACCOUNT_REQUEST = googleData.getPickAccountCode();
		int REQUEST_AUTHENTICATE = googleData.getAuthenticateCode();
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
			googleData.executeTokenHandler(googleData.getAuthenticateCode());
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
