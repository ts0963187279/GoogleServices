package com.walton.android.googleservices.model;


import com.google.gdata.client.contacts.ContactsService;
import com.walton.android.googleservices.mission.GetContactEntrys;

/**
 * Created by waltonmis on 2017/9/19.
 */

public class GoogleContactData extends GoogleData {

	public ContactsService getContactsService(){
		return (ContactsService)this.getService().getGoogleService();
	}
}
