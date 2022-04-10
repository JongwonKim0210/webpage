package com.webpage.global.utils;

import java.util.List;
import java.util.Map;

public class EmptyCheck {

    public static void removeEmptyList(List<?> list) {
        for (int i = 0; i < list.size(); i++) {
            Object o = list.get(i);
            if (checkEmpty(o)) {
                list.remove(i);
            }
        }
    }

    public static void removeEmptyMap(Map<?, ?> map) {
        for (Object key : map.keySet()) {
            Object o = map.get(key);
            if (checkEmpty(o)) {
                map.remove(key);
            }
        }
    }

    private static boolean checkEmpty(Object o) {
        boolean check = false;

        if (o instanceof List) {
            if (((List) o).isEmpty()) check = true;
        } else if (o instanceof Map) {
            if (((Map) o).isEmpty()) check = true;
        }

        return check;
    }
}
