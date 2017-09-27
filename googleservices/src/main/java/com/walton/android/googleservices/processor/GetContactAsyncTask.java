package com.walton.android.googleservices.processor;

import android.os.AsyncTask;

import com.walton.android.googleservices.model.GoogleContactData;
import com.google.gdata.client.contacts.ContactsService;
import com.google.gdata.data.contacts.ContactEntry;
import com.google.gdata.data.contacts.ContactFeed;
import com.google.gdata.util.ServiceException;

import java.io.IOException;
import java.net.URL;


/**
 * Created by waltonmis on 2017/9/19.
 */

public class GetContactAsyncTask extends AsyncTask<Void,Void,Void> {
	private GoogleContactData googleContactData;
	private ContactsService contactsService;
	public GetContactAsyncTask(GoogleContactData googleContactData){
		this.googleContactData = googleContactData;
		contactsService = googleContactData.getContactsService();
	}
	public <T extends ContactFeed> T getFeed(String feedHref, Class<T> feedClass) throws IOException, ServiceException {
		return contactsService.getFeed(new URL(feedHref), feedClass);
	}
	@Override
	protected Void doInBackground(Void... voids) {
		try {
			ContactFeed contactFeed = getFeed(googleContactData.getApiPrefix(),ContactFeed.class);
			System.out.println(contactFeed.getTitle().getPlainText());
			for(ContactEntry entry : contactFeed.getEntries()){
				if(entry.hasName()){
					System.out.println(entry.getTitle().getPlainText());
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return null;
	}
	protected void onPostExecute(Void test) {
	}
}
