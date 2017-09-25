package com.cobwalton.googleservices.model;


import com.google.gdata.client.contacts.ContactsService;

/**
 * Created by waltonmis on 2017/9/19.
 */

public class GoogleContactData extends GoogleData {
    private static final String API_PREFIX
            = "https://www.google.com/m8/feeds/contacts/default/full";
    public String getApiPrefix(){
        return API_PREFIX;
    }
    public ContactsService getContactsService(){
        return (ContactsService)this.getService().getGoogleService();
    }
}
