package com.walton.android.googleservices.processor;

import android.net.Uri;
import android.os.AsyncTask;


import com.walton.android.googleservices.model.GooglePhotosData;
import com.google.gdata.client.photos.PicasawebService;
import com.google.gdata.data.photos.AlbumEntry;
import com.google.gdata.data.photos.AlbumFeed;
import com.google.gdata.data.photos.GphotoEntry;
import com.google.gdata.data.photos.GphotoFeed;
import com.google.gdata.data.photos.PhotoEntry;
import com.google.gdata.data.photos.UserFeed;
import com.google.gdata.util.ServiceException;
import com.google.gdata.util.ServiceForbiddenException;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * Created by waltonmis on 2017/8/30.
 */

public class GetPhotoUrlsAsyncTask extends AsyncTask<Void,Void,Void> {
	private String selectedAccountName;
	private GooglePhotosData googlePhotosData;
	private TreeMap<String,ArrayList<String>> strTreeMap;
	private PicasawebService picasawebService;
	public GetPhotoUrlsAsyncTask(GooglePhotosData googlePhotosData){
		this.googlePhotosData = googlePhotosData;
		picasawebService = googlePhotosData.getPicasawebService();
	}
	public <T extends GphotoFeed> T getFeed(String feedHref, Class<T> feedClass) throws IOException, ServiceException {
		return picasawebService.getFeed(new URL(feedHref), feedClass);
	}

	public List<AlbumEntry> getAlbums(String userID) throws IOException, ServiceException {
		String albumUrl = googlePhotosData.getApiPrefix() + userID;
		UserFeed userFeed = getFeed(albumUrl, UserFeed.class);
		List<GphotoEntry> entries = userFeed.getEntries();
		List<AlbumEntry> albums = new ArrayList<>();
		for (GphotoEntry gp : entries) {
			AlbumEntry ae = new AlbumEntry(gp);
			albums.add(ae);
		}
		return albums;
	}

	public List<PhotoEntry> getPhoto(AlbumEntry album) throws IOException, ServiceException {
		AlbumFeed feed = album.getFeed();
		List<PhotoEntry> photos = new ArrayList<>();
		for (GphotoEntry entry : feed.getEntries()) {
			PhotoEntry pe = new PhotoEntry(entry);
			photos.add(pe);
		}
		return photos;
	}
	@Override
	protected Void doInBackground(Void... voids) {
		List<AlbumEntry> albums;
		strTreeMap = new TreeMap<>();
		try{
			selectedAccountName = googlePhotosData.getSelectedAccount().name;
			albums = getAlbums(selectedAccountName);
			for(AlbumEntry mAlbum : albums){
				List<PhotoEntry> mPhotos = getPhoto(mAlbum);
				ArrayList<String> imageUrls = new ArrayList<>();
				for(PhotoEntry mPhoto : mPhotos) {
					imageUrls.add(Uri.parse(mPhoto.getMediaContents().get(0).getUrl()).toString());
				}
				if(imageUrls.size() != 0)
					strTreeMap.put(mAlbum.getTitle().getPlainText().toString(),imageUrls);
			}
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
		googlePhotosData.onBackGroundResult().doIt(strTreeMap);
	}
}
