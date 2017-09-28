package com.walton.android.googleservices.processor;

import android.accounts.Account;
import com.google.gdata.client.photos.PicasawebService;
import com.walton.android.googleservices.mission.GetPhotoMap;
import com.walton.android.googleservices.mission.RequestToken;
import com.walton.android.googleservices.model.GoogleData;
import java.util.List;
import java.util.Map;
import poisondog.core.Mission;

/**
 * Created by waltonmis on 2017/9/19.
 */

public class GetGooglePhotosToken implements Mission<GoogleData> {

	@Override
	public Void execute(final GoogleData data) {
		Account account = data.getSelectedAccount();
		int requestCode = data.getAuthenticateCode();
		RequestToken mRequest = new RequestToken();
		mRequest.execute(new RequestToken.Parameter(data.getActivity(), account, "lh2", requestCode, new Mission<String>() {
			@Override
			public Void execute(String token) {
				data.getService().setUserToken(token);

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
		}));
		return null;
	}

}
