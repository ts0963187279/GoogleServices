package com.walton.android.example;

import com.walton.android.googleservices.processor.OnBackGroundResult;
import java.util.List;
import java.util.Map;

/**
 * Created by waltonmis on 2017/9/25.
 */

public class ShowContractList implements OnBackGroundResult{
    @Override
    public void doIt(Map<String, List<String>> strTreeMap) {
        System.out.println("test");
	System.out.println(strTreeMap);
    }
}
