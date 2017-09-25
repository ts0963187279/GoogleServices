package com.walton.android.example;

import com.walton.android.googleservices.processor.OnBackGroundResult;

import java.util.ArrayList;
import java.util.TreeMap;

/**
 * Created by waltonmis on 2017/9/25.
 */

public class ShowContractList implements OnBackGroundResult{
    @Override
    public void doIt(TreeMap<String, ArrayList<String>> strTreeMap) {
        System.out.println("test");
    }
}
