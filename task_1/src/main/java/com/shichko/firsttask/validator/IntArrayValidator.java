package com.shichko.firsttask.validator;

import com.shichko.firsttask.entity.IntArray;

public class IntArrayValidator {
    public static boolean isEmpty(IntArray array) {
        return array.length() == 0;
    }
}
