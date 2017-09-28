package com.walton.android.example;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.google.gdata.client.contacts.ContactsService;
import com.google.gdata.client.photos.PicasawebService;
import com.walton.android.googleservices.model.GoogleData;
import com.walton.android.googleservices.processor.ConnectGoogleAccount;
import com.walton.android.googleservices.processor.GoogleServices;
import com.walton.android.googleservices.processor.SelectGoogleAccount;

public class MainActivity extends AppCompatActivity {
	GoogleData googleData;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(com.walton.android.example.R.layout.activity_main);
//		GoogleServices googleServices = new GoogleServices(new ContactsService("GoogleServices"), new ShowContacts());
		GoogleServices googleServices = new GoogleServices(new PicasawebService("GoogleServices"), new ShowPhotos());
		googleData = googleServices.getGoogleData();
		SelectGoogleAccount selectGoogleAccount = new SelectGoogleAccount(googleData,this);
		selectGoogleAccount.select();
	}
	protected void onActivityResult(final int requestCode,final int resultCode,final Intent data){
		ConnectGoogleAccount connectGoogleAccount = new ConnectGoogleAccount(requestCode,resultCode,data);
		connectGoogleAccount.connect(googleData);
	}
}
