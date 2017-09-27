/*
 * Copyright (C) 2017 Adam Huang <poisondog@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.walton.android.googleservices.mission;

import android.net.Uri;
import com.google.gdata.client.photos.PicasawebService;
import com.google.gdata.data.photos.AlbumEntry;
import com.google.gdata.data.photos.AlbumFeed;
import com.google.gdata.data.photos.GphotoEntry;
import com.google.gdata.data.photos.GphotoFeed;
import com.google.gdata.data.photos.PhotoEntry;
import com.google.gdata.data.photos.UserFeed;
import com.google.gdata.util.ServiceException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import poisondog.core.Mission;

/**
 * @author Adam Huang
 * @since 2017-09-27
 */
public class GetPhotoMap implements Mission<PicasawebService> {
	private String mAccount;

	/**
	 * Constructor
	 */
	public GetPhotoMap(String account) {
		mAccount = account;
	}

	/**
	 * https://developers.google.com/identity/protocols/googlescopes
	 */
	public static String getApiPrefix() {
		return "https://picasaweb.google.com/data/feed/api/user/";
	}

	private <T extends GphotoFeed> T getFeed(PicasawebService picasawebService, String feedHref, Class<T> feedClass) throws IOException, ServiceException {
		return picasawebService.getFeed(new URL(feedHref), feedClass);
	}

	private List<AlbumEntry> getAlbums(PicasawebService picasawebService, String userID) throws IOException, ServiceException {
		String albumUrl = getApiPrefix() + userID;
		UserFeed userFeed = getFeed(picasawebService, albumUrl, UserFeed.class);
		List<GphotoEntry> entries = userFeed.getEntries();
		List<AlbumEntry> albums = new ArrayList<>();
		for (GphotoEntry gp : entries) {
			AlbumEntry ae = new AlbumEntry(gp);
			albums.add(ae);
		}
		return albums;
	}

	private List<PhotoEntry> getPhoto(AlbumEntry album) throws IOException, ServiceException {
		AlbumFeed feed = album.getFeed();
		List<PhotoEntry> photos = new ArrayList<>();
		for (GphotoEntry entry : feed.getEntries()) {
			PhotoEntry pe = new PhotoEntry(entry);
			photos.add(pe);
		}
		return photos;
	}

	@Override
	public Map<String, List<String>> execute(PicasawebService service) throws IOException, ServiceException {
		TreeMap<String, List<String>> strTreeMap = new TreeMap<>();
		for(AlbumEntry mAlbum : getAlbums(service, mAccount)){
			List<PhotoEntry> mPhotos = getPhoto(mAlbum);
			ArrayList<String> imageUrls = new ArrayList<>();
			for(PhotoEntry mPhoto : mPhotos) {
				imageUrls.add(Uri.parse(mPhoto.getMediaContents().get(0).getUrl()).toString());
			}
			if(!imageUrls.isEmpty())
				strTreeMap.put(mAlbum.getTitle().getPlainText().toString(), imageUrls);
		}
		return strTreeMap;
	}
}
