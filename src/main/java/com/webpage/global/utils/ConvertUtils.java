package com.webpage.global.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class ConvertUtils {

    public static List<Map<String, Object>> convertToListMap(List<?> list) throws IllegalAccessException {
        if (null == list || list.isEmpty()) {
            return Collections.emptyList();
        }

        List<Map<String, Object>> convertList = new ArrayList<>();
        for (Object obj : list) {
            convertList.add(ConvertUtils.convertToMap(obj));
        }

        return convertList;
    }

    public static <T> List<T> convertToListValueObject(List<Map<String, Object>> list, Class<T> type) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        if (null == list || list.isEmpty()) {
            return Collections.emptyList();
        }

        List<T> convertList = new ArrayList<>();
        for(Map<String, Object> map : list) {
            convertList.add(ConvertUtils.convertToValueObject(map, type));
        }

        return convertList;
    }

    public static Map<String, Object> convertToMap(Object object) throws IllegalAccessException {
        if (null == object) {
            return Collections.emptyMap();
        }

        Map<String, Object> convertMap = new HashMap<>();
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            convertMap.put(field.getName(), field.get(object));
        }

        return convertMap;
    }

    public static <T> T convertToValueObject(Map<String, Object> map, Class<T> type) throws NoSuchMethodException, NullPointerException, InvocationTargetException, InstantiationException, IllegalAccessException {
        if (null == type) {
            throw new NullPointerException("Class cannot be null!");
        }

        T instance = type.getConstructor().newInstance();
        if (null == map || map.isEmpty()) {
            return instance;
        }

        for (Map.Entry<String, Object> entry : map.entrySet()) {
            Field[] fields = type.getDeclaredFields();
            try {
                for (Field field : fields) {
                    field.setAccessible(true);
                    String fieldName = field.getName();

                    boolean isSameType = entry.getValue().getClass().equals(field.getType());
                    boolean isSameName = entry.getKey().equals(fieldName);

                    if (isSameName && isSameType) {
                        field.set(instance, map.get(fieldName));
                    }
                }
            } catch (NullPointerException e) {
                e.getMessage();
            }
        }

        return instance;
    }
}
