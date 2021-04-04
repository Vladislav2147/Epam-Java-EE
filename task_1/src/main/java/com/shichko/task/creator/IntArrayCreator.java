package com.shichko.task.creator;

import com.shichko.task.entity.IntArray;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class IntArrayCreator {
    final static Logger logger= LogManager.getLogger();

    public IntArray createIntArray(int[] elements) {
        IntArray array = new IntArray(elements);
        logger.log(Level.INFO, "IntArray was created: " + array);
        return array;
    }
}
