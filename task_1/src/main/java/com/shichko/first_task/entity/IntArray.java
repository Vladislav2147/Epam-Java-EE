package com.shichko.first_task.entity;

import java.util.Arrays;
import java.util.Iterator;

public class IntArray implements Iterable<Integer> {
    private int[] array;

    public IntArray(int[] array) {
        this.array = array;
    }

    public int[] getArray() {
        return array;
    }

    public void setArray(int[] array) {
        this.array = array;
    }

    public int get(int index)  { //TODO throws
        return array[index];
    }

    public void set(int index, int value) { //TODO throws
        array[index] = value;
    }

    public int length() {
        return array.length;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IntArray intArray = (IntArray) o;
        return Arrays.equals(array, intArray.array);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(array);
    }

    @Override
    public String toString() {
        return "IntArray{" +
                "array=" + Arrays.toString(array) +
                '}';
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {

            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < array.length;
            }

            @Override
            public Integer next() {
                return array[currentIndex++];
            }
        };
    }

}
