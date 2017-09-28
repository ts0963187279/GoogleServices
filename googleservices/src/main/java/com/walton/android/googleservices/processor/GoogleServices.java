package com.walton.android.googleservices.processor;

import com.google.gdata.client.contacts.ContactsService;
import com.google.gdata.client.GoogleService;
import com.google.gdata.client.photos.PicasawebService;
import com.walton.android.googleservices.model.GoogleData;
import poisondog.core.Mission;

/**
 * Created by waltonmis on 2017/9/19.
 */

public class GoogleServices {
	private GoogleService googleService;
	private GoogleData googleData;
	public GoogleServices(PicasawebService picasawebService, Mission<GoogleData> handler){
		googleService = picasawebService;
		googleData = new GoogleData();
		googleData.setService(googleService);
		googleData.setTokenType("lh2");
		googleData.setTokenHandler(handler);
	}
	public GoogleServices(ContactsService contactsService, Mission<GoogleData> handler){
		googleService = contactsService;
		googleData = new GoogleData();
		googleData.setService(googleService);
		googleData.setTokenType("cp");
		googleData.setTokenHandler(handler);
	}
	public GoogleData getGoogleData(){
		return googleData;
	}
}
