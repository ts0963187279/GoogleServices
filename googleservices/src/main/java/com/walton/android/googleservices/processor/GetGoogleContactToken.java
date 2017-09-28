package com.walton.android.googleservices.processor;

import android.accounts.Account;
import com.walton.android.googleservices.mission.RequestToken;
import com.walton.android.googleservices.model.GoogleData;
import poisondog.core.Mission;

/**
 * Created by waltonmis on 2017/9/19.
 */

public class GetGoogleContactToken implements Mission<GoogleData> {
	private GoogleData contactData;

	public GetGoogleContactToken(GoogleData googleData){
		this.contactData = googleData;
	}

	@Override
	public Void execute(final GoogleData data) {
		Account account = data.getSelectedAccount();
		int requestCode = data.getAuthenticateCode();
		RequestToken mRequest = new RequestToken();
		mRequest.execute(new RequestToken.Parameter(data.getActivity(), account, "cp", requestCode, new Mission<String>() {
			@Override
			public Void execute(String token) {
				data.getService().setUserToken(token);
				GetContactAsyncTask task = new GetContactAsyncTask(data);
				task.execute();
				return null;
			}
		}));
		return null;
	}

}
