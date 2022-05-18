package com.ztool.box.util;

import java.util.List;

/**
 * @Author zhaoj
 */
public class ListUtil {


    public static boolean isEmtry(List list) {
        if (list == null) {
            return true;
        }

        if (list.size() == 0) {
            return true;
        }

        return false;
    }
}
