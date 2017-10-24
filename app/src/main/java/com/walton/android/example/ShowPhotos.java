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
package com.walton.android.example;

import com.google.gdata.client.photos.PicasawebService;
import com.walton.android.googleservices.mission.GetAlbumInfo;
import com.walton.android.googleservices.module.AlbumInfo;
import com.walton.android.googleservices.module.GoogleData;
import com.walton.android.googleservices.module.PhotoInfo;

import java.util.List;
import java.util.Map;
import poisondog.core.Mission;

/**
 * @author Adam Huang
 * @since 2017-09-28
 */
public class ShowPhotos implements Mission<GoogleData> {

	@Override
	public Void execute(final GoogleData data) {
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					GetAlbumInfo mTask = new GetAlbumInfo(data.getSelectedAccount().name);
					List<AlbumInfo> albumInfos = mTask.execute((PicasawebService)data.getService());
					for (AlbumInfo albumInfo : albumInfos) {
						System.out.println("=====" + albumInfo.getAlbumName() + "=====");
						for (PhotoInfo photoInfo : albumInfo.getPhotoInfos()) {
							System.out.println(photoInfo.getPhotoName());
						}
					}
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		});
		t.start();
		return null;
	}
}
