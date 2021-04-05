package com.shichko.task.validator;

import com.shichko.task.entity.IntArray;

public class IntArrayValidator {

    final static String REGEXP = "^(-?\\d+\\s?)+$";

    public static boolean isNullOrEmpty(IntArray array) {
        return array == null || array.length() == 0;
    }

    public static boolean isNull(IntArray array) {
        return array == null;
    }

    public static boolean isEmpty(IntArray array) {
        return array.length() == 0;
    }

    public static boolean isValid(String array) {
        return array.matches(REGEXP);
    }
}
