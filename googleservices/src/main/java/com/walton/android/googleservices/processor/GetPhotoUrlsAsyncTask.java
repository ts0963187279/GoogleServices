package com.walton.android.googleservices.processor;

import android.os.AsyncTask;
import com.google.gdata.util.ServiceException;
import com.google.gdata.util.ServiceForbiddenException;
import com.walton.android.googleservices.mission.GetPhotoMap;
import com.walton.android.googleservices.model.GooglePhotosData;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by waltonmis on 2017/8/30.
 */

public class GetPhotoUrlsAsyncTask extends AsyncTask<Void,Void,Void> {
	private GooglePhotosData googlePhotosData;
	private Map<String, List<String>> strTreeMap;
	private GetPhotoMap mTask;

	public GetPhotoUrlsAsyncTask(GooglePhotosData googlePhotosData){
		this.googlePhotosData = googlePhotosData;
		mTask = new GetPhotoMap(googlePhotosData.getSelectedAccount().name);
	}
	@Override
	protected Void doInBackground(Void... voids) {
		try{
			strTreeMap = mTask.execute(googlePhotosData.getPicasawebService());
		}catch(ServiceForbiddenException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}catch(ServiceException e){
			e.printStackTrace();
		}
		return new TreeMap<String, List<String>>();
	}

	protected void onPostExecute(Void test) {
		System.out.println("onPostExecute " + strTreeMap.size());
		googlePhotosData.onBackGroundResult().doIt(strTreeMap);
	}
}
