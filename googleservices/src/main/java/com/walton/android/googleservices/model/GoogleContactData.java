package com.walton.android.googleservices.model;


import com.google.gdata.client.contacts.ContactsService;
import com.walton.android.googleservices.mission.GetContactEntrys;

/**
 * Created by waltonmis on 2017/9/19.
 */

public class GoogleContactData extends GoogleData {

	/**
	 * https://developers.google.com/identity/protocols/googlescopes
	 */
	public String getApiPrefix(){
		return GetContactEntrys.getApiPrefix();
	}

	public ContactsService getContactsService(){
		return (ContactsService)this.getService().getGoogleService();
	}
}
