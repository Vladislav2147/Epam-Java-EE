package com.shichko.first_task.validator;

import com.shichko.first_task.entity.IntArray;

public class IntArrayValidator {
    public static boolean isEmpty(IntArray array) {
        return array.length() == 0;
    }
}
