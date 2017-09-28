package com.walton.android.googleservices.processor;

import android.os.AsyncTask;
import com.google.gdata.client.contacts.ContactsService;
import com.google.gdata.data.contacts.ContactEntry;
import com.google.gdata.data.contacts.ContactFeed;
import com.google.gdata.util.ServiceException;
import com.walton.android.googleservices.mission.GetContactEntrys;
import com.walton.android.googleservices.model.GoogleData;
import java.io.IOException;
import java.net.URL;

/**
 * Created by waltonmis on 2017/9/19.
 */

public class GetContactAsyncTask extends AsyncTask<Void,Void,Void> {
	private ContactsService contactsService;
	public GetContactAsyncTask(GoogleData googleContactData){
		contactsService = (ContactsService)googleContactData.getService();
	}

	public <T extends ContactFeed> T getFeed(String feedHref, Class<T> feedClass) throws IOException, ServiceException {
		return contactsService.getFeed(new URL(feedHref), feedClass);
	}

	@Override
	protected Void doInBackground(Void... voids) {
		try {
			for(ContactEntry entry : new GetContactEntrys().execute(contactsService)){
				if(entry.hasName()){
					System.out.println("name: " + entry.getTitle().getPlainText());
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
