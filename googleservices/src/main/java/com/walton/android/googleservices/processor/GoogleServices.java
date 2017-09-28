package com.walton.android.googleservices.processor;

import com.google.gdata.client.contacts.ContactsService;
import com.google.gdata.client.GoogleService;
import com.google.gdata.client.photos.PicasawebService;
import com.walton.android.googleservices.model.GoogleData;

/**
 * Created by waltonmis on 2017/9/19.
 */

public class GoogleServices {
	private GoogleService googleService;
	private GoogleData googleData;
	public GoogleServices(PicasawebService picasawebService){
		googleService = picasawebService;
		googleData = new GoogleData();
		googleData.setService(googleService);
		googleData.setTokenHandler(new GetGooglePhotosToken(googleData));
	}
	public GoogleServices(ContactsService contactsService){
		googleService = contactsService;
		googleData = new GoogleData();
		googleData.setService(googleService);
		googleData.setTokenHandler(new GetGoogleContactToken(googleData));
	}
	public void setUserToken(String token){
		googleService.setUserToken(token);
	}
	public GoogleService getGoogleService(){
		return googleService;
	}
	public GoogleData getGoogleData(){
		return googleData;
	}
}
