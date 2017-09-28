package com.walton.android.googleservices.processor;

import android.os.AsyncTask;
import com.google.gdata.client.photos.PicasawebService;
import com.google.gdata.util.ServiceException;
import com.google.gdata.util.ServiceForbiddenException;
import com.walton.android.googleservices.mission.GetPhotoMap;
import com.walton.android.googleservices.model.GoogleData;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by waltonmis on 2017/8/30.
 */

public class GetPhotoUrlsAsyncTask extends AsyncTask<Void,Void,Void> {
	private GoogleData mData;
	private Map<String, List<String>> strTreeMap;
	private GetPhotoMap mTask;

	public GetPhotoUrlsAsyncTask(GoogleData googlePhotosData){
		mData = googlePhotosData;
		mTask = new GetPhotoMap(googlePhotosData.getSelectedAccount().name);
	}
	@Override
	protected Void doInBackground(Void... voids) {
		strTreeMap = new TreeMap<String, List<String>>();
		try{
			strTreeMap = mTask.execute((PicasawebService)mData.getService());
		}catch(ServiceForbiddenException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}catch(ServiceException e){
			e.printStackTrace();
		}
		return null;
	}

	protected void onPostExecute(Void test) {
		System.out.println("onPostExecute " + strTreeMap.size());
//		mData.onBackGroundResult().doIt(strTreeMap);
		try {
			mData.getHandler().execute(strTreeMap);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
