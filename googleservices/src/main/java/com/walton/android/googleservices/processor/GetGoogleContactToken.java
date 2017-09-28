package com.walton.android.googleservices.processor;

import android.accounts.Account;
import com.walton.android.googleservices.mission.RequestToken;
import com.walton.android.googleservices.model.GoogleContactData;
import com.walton.android.googleservices.model.GoogleData;
import poisondog.core.Mission;

/**
 * Created by waltonmis on 2017/9/19.
 */

public class GetGoogleContactToken implements GetToken {
	private GoogleContactData contactData;

	public GetGoogleContactToken(GoogleData googleData){
		this.contactData = (GoogleContactData)googleData;
	}

	@Override
	public void getToken() {
		Account account = contactData.getSelectedAccount();
		int requestCode = contactData.getAuthenticateCode();
		RequestToken mRequest = new RequestToken();
		mRequest.execute(new RequestToken.Parameter(contactData.getActivity(), account, "cp", requestCode, new Mission<String>() {
			@Override
			public Void execute(String token) {
				contactData.getService().setUserToken(token);
				GetContactAsyncTask task = new GetContactAsyncTask(contactData);
				task.execute();
				return null;
			}
		}));
	}
}
