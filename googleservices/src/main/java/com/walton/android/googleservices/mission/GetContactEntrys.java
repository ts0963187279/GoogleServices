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

import com.google.gdata.client.contacts.ContactsService;
import com.google.gdata.data.contacts.ContactEntry;
import com.google.gdata.data.contacts.ContactFeed;
import com.google.gdata.util.ServiceException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import poisondog.core.Mission;

/**
 * @author Adam Huang
 * @since 2017-09-27
 */
public class GetContactEntrys implements Mission<ContactsService> {

	/**
	 * https://developers.google.com/identity/protocols/googlescopes
	 */
	public static String getApiPrefix(){
		return "https://www.google.com/m8/feeds/contacts/default/full";
	}

	@Override
	public List<ContactEntry> execute(ContactsService service) throws MalformedURLException, IOException, ServiceException {
		ContactFeed contactFeed = service.getFeed(new URL(getApiPrefix()), ContactFeed.class);
		System.out.println("feed: " + contactFeed.getTitle().getPlainText());
		return contactFeed.getEntries();
	}

}
