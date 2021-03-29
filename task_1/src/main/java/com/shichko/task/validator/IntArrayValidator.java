package com.shichko.task.validator;

import com.shichko.task.entity.IntArray;

public class IntArrayValidator {
    public static boolean isEmpty(IntArray array) {
        return array.length() == 0;
    }
}
