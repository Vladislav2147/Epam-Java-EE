package com.shichko.first_task.entity;

import com.shichko.first_task.exception.ArrayException;

import java.util.Arrays;
import java.util.Iterator;

public class IntArray implements Iterable<Integer> {
    private int[] elements;

    public IntArray(int[] elements) {
        this.elements = Arrays.copyOf(elements, elements.length);
    }

    public int[] getElements() {
        return Arrays.copyOf(elements, length());
    }

    public void setElements(int[] elements) {
        this.elements = elements;
    }

    public int get(int index) throws ArrayException  {
        if (index < 0 || index >= length()) {
            throw new ArrayException("Index is out of range. Value: " + index + ", length: " + length());
        }
        return elements[index];
    }

    public void set(int index, int value) throws ArrayException {
        if (index < 0 || index >= length()) {
            throw new ArrayException("Index is out of range. Value: " + index + ", length: " + length());
        }
        elements[index] = value;
    }

    public int length() {
        return elements.length;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        IntArray intArray = (IntArray) o;
        return Arrays.equals(elements, intArray.elements);
    }

    @Override
    public int hashCode() {
        if (elements == null) {
            return 0;
        }
        int result = 1;
        for (int item: elements) {
            result = 31 * result + item;
        }
        return result;
    }

    @Override
    public String toString() {
        return "IntArray: " + Arrays.toString(elements);
    }

    @Override
    public Iterator<Integer> iterator() {
        return new IntArrayIterator();
    }

    private class IntArrayIterator implements Iterator<Integer> {

        private int currentIndex = 0;

        @Override
        public boolean hasNext() {
            return currentIndex < elements.length;
        }

        @Override
        public Integer next() {
            return elements[currentIndex++];
        }
    }
}
