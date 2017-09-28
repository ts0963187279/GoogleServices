package com.walton.android.example;

import java.util.List;
import java.util.Map;
import poisondog.core.Mission;

/**
 * Created by waltonmis on 2017/9/25.
 */

public class ShowContractList implements Mission<Map<String, List<String>>> {

	@Override
	public Void execute(Map<String, List<String>> strTreeMap) {
		System.out.println("test");
		System.out.println(strTreeMap);
		return null;
	}
}
