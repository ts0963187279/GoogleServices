package com.walton.android.googleservices.processor;

import android.accounts.Account;
import com.google.gdata.client.contacts.ContactsService;
import com.google.gdata.data.contacts.ContactEntry;
import com.google.gdata.util.ServiceException;
import com.walton.android.googleservices.mission.GetContactEntrys;
import com.walton.android.googleservices.mission.RequestToken;
import com.walton.android.googleservices.model.GoogleData;
import java.io.IOException;
import poisondog.core.Mission;

/**
 * Created by waltonmis on 2017/9/19.
 */

public class GetGoogleContactToken implements Mission<GoogleData> {

	@Override
	public Void execute(final GoogleData data) {
		Account account = data.getSelectedAccount();
		int requestCode = data.getAuthenticateCode();
		RequestToken mRequest = new RequestToken();
		mRequest.execute(new RequestToken.Parameter(data.getActivity(), account, "cp", requestCode, new Mission<String>() {
			@Override
			public Void execute(String token) {
				data.getService().setUserToken(token);

				Thread t = new Thread(new Runnable() {
					@Override
					public void run() {
						try {
							for(ContactEntry entry : new GetContactEntrys().execute((ContactsService)data.getService())){
								if(entry.hasName()){
									System.out.println("name: " + entry.getTitle().getPlainText());
								}
							}
						} catch (IOException e) {
							e.printStackTrace();
						} catch (ServiceException e) {
							e.printStackTrace();
						}
					}
				});
				t.start();

				return null;
			}
		}));
		return null;
	}

}
