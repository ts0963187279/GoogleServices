package com.walton.android.googleservices.processor;

import android.accounts.AccountManager;

import com.walton.android.googleservices.model.GoogleContactData;
import com.walton.android.googleservices.model.GoogleData;


/**
 * Created by waltonmis on 2017/9/19.
 */

public class GetGoogleContactToken implements GetToken {
    GoogleContactData contactData;
    public GetGoogleContactToken(GoogleData googleData){
        this.contactData = (GoogleContactData)googleData;
    }
    @Override
    public void getToken() {
        AccountManager am = contactData.getAccountManager();
        OnTokenAcquired onTokenAcquired = new OnTokenAcquired(contactData,new GetContactAsyncTask(contactData));
        am.getAuthToken(
                contactData.getSelectedAccount(),
                "cp",
                null,
                contactData.getActivity(),
                onTokenAcquired,
                null);
    }
}
