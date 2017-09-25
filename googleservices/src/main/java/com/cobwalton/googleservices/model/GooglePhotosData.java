package com.cobwalton.googleservices.model;

import com.google.gdata.client.photos.PicasawebService;

/**
 * Created by waltonmis on 2017/8/29.
 */

public class GooglePhotosData extends GoogleData {
    private static final String API_PREFIX
            = "https://picasaweb.google.com/data/feed/api/user/";
    public String getApiPrefix() {
        return API_PREFIX;
    }
    public PicasawebService getPicasawebService(){
        return (PicasawebService)this.getService().getGoogleService();
    }
}
