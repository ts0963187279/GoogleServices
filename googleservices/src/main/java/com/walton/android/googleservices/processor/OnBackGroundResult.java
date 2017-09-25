package com.walton.android.googleservices.processor;

import java.util.ArrayList;
import java.util.TreeMap;

/**
 * Created by waltonmis on 2017/9/4.
 */

public interface OnBackGroundResult {
    void doIt(TreeMap<String, ArrayList<String>> strTreeMap);
}
