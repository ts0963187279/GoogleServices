/*
 * Copyright (C) 2017 RS Wong <ts0963187279@gmail.com>
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
import com.google.gdata.util.ResourceNotFoundException;
import com.google.gdata.util.ServiceException;
import com.walton.android.googleservices.module.AlbumInfo;

import java.io.File;
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
public class GetAlbumInfo implements Mission<PicasawebService> {
	private String mAccount;

	/**
	 * Constructor
	 */
	public GetAlbumInfo(String account) {
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
	public List<AlbumInfo> execute(PicasawebService picasawebService) throws IOException, ServiceException {
		List<AlbumInfo> albumInfos = new ArrayList<AlbumInfo>();
		List<AlbumEntry> albumEntries = null;
		try {
			albumEntries = getAlbums(picasawebService,mAccount);
			for(AlbumEntry albumEntry : albumEntries){
				AlbumInfo albumInfo = new AlbumInfo();
				String albumId = "";
				for(int i = albumEntry.getId().indexOf("albumid")+8;i < albumEntry.getId().length();i++)
					albumId += albumEntry.getId().charAt(i);
				albumInfo.setAlbumId(albumId);
				albumInfo.setAlbumName(albumEntry.getTitle().getPlainText().toString());
				List<PhotoEntry> photoEntries = getPhoto(albumEntry);
				for(PhotoEntry photoEntry: photoEntries){
					String fileName = photoEntry.getTitle().getPlainText().toString();
					URL url = new URL(photoEntry.getMediaContents().get(0).getUrl().toString());
					albumInfo.addPhotoNameAndPhotoURL(fileName,url);
				}
				albumInfos.add(albumInfo);
			}
		} catch (ResourceNotFoundException e) {
			System.out.println("User Name invalid");
			File file = new File("./RefreshTokenStorage.dat");
			file.delete();
		}
		return albumInfos;
	}
}
