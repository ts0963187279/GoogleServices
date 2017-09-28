package com.walton.android.googleservices.model;

import com.google.gdata.client.photos.PicasawebService;
import com.walton.android.googleservices.mission.GetPhotoMap;

/**
 * Created by waltonmis on 2017/8/29.
 */

public class GooglePhotosData extends GoogleData {

	public PicasawebService getPicasawebService(){
		return (PicasawebService)this.getService().getGoogleService();
	}
}
