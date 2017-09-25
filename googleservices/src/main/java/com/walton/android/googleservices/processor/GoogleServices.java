package com.walton.android.googleservices.processor;

import com.walton.android.googleservices.model.GoogleContactData;
import com.walton.android.googleservices.model.GoogleData;
import com.walton.android.googleservices.model.GooglePhotosData;
import com.google.gdata.client.GoogleService;
import com.google.gdata.client.contacts.ContactsService;
import com.google.gdata.client.photos.PicasawebService;

/**
 * Created by waltonmis on 2017/9/19.
 */

public class GoogleServices {
    private GoogleService googleService;
    private GoogleData googleData;
    private GetToken getToken;
    public GoogleServices(PicasawebService picasawebService){
        googleService = picasawebService;
        googleData = new GooglePhotosData();
        googleData.setService(this);
        getToken = new GetGooglePhotosToken(googleData);
    }
    public GoogleServices(ContactsService contactsService){
        googleService = contactsService;
        googleData = new GoogleContactData();
        googleData.setService(this);
        getToken = new GetGoogleContactToken(googleData);
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
    public void getGoogleToken(){
        getToken.getToken();
    }
}
