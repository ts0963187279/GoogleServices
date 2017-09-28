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
package com.walton.android.example;

import com.google.gdata.client.photos.PicasawebService;
import com.walton.android.googleservices.mission.GetPhotoMap;
import com.walton.android.googleservices.model.GoogleData;
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
					GetPhotoMap mTask = new GetPhotoMap(data.getSelectedAccount().name);
					Map<String, List<String>> strTreeMap = mTask.execute((PicasawebService)data.getService());
					for (String key : strTreeMap.keySet()) {
						System.out.println("=====" + key + "=====");
						for (String obj : strTreeMap.get(key)) {
							System.out.println(obj);
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
