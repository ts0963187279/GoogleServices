package com.walton.android.googleservices.processor;

import android.accounts.Account;
import android.accounts.AccountManager;
import com.walton.android.googleservices.mission.RequestToken;
import com.walton.android.googleservices.model.GoogleData;
import com.walton.android.googleservices.model.GooglePhotosData;
import poisondog.core.Mission;

/**
 * Created by waltonmis on 2017/9/19.
 */

public class GetGooglePhotosToken implements GetToken {
	private GooglePhotosData googlePhotosData;
	public GetGooglePhotosToken(GoogleData googleData){
		this.googlePhotosData = (GooglePhotosData)googleData;
	}
	@Override
	public void getToken() {
//		AccountManager am = googlePhotosData.getAccountManager();
//		OnTokenAcquired onTokenAcquired = new OnTokenAcquired(googlePhotosData,new GetPhotoUrlsAsyncTask(googlePhotosData));
//		am.getAuthToken(
//				googlePhotosData.getSelectedAccount(),
//				"lh2",
//				null,
//				googlePhotosData.getActivity(),
//				onTokenAcquired,
//				null);

		Account account = googlePhotosData.getSelectedAccount();
		int requestCode = googlePhotosData.getAuthenticateCode();
		RequestToken mRequest = new RequestToken();
		mRequest.execute(new RequestToken.Parameter(googlePhotosData.getActivity(), account, "lh2", requestCode, new Mission<String>() {
			@Override
			public Void execute(String token) {
				googlePhotosData.getService().setUserToken(token);
				GetPhotoUrlsAsyncTask task = new GetPhotoUrlsAsyncTask(googlePhotosData);
				task.execute();
				return null;
			}
		}));
	}
}
