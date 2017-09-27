package com.walton.android.googleservices.processor;

import android.app.Activity;
import com.walton.android.googleservices.model.GoogleData;

/**
 * Created by waltonmis on 2017/8/30.
 */

public class SelectGoogleAccount {
	private GoogleData mGoogleData;
	private RequestGoogleAccount mTask;

	public SelectGoogleAccount(GoogleData googleData, Activity activity){
		mGoogleData = googleData;
		mGoogleData.setActivity(activity);

		mTask = new RequestGoogleAccount(activity);
	}

	public void select(){
		mTask.execute(mGoogleData.getPickAccountCode());
	}

}
