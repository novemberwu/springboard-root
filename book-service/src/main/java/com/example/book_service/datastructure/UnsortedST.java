package com.example.book_service.datastructure;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

@Component
public class UnsortedST <KEY, VALUE> implements ST<KEY , VALUE>{

    private KEY[] keys;
    private VALUE[] values;
    private int size;
    static private final int DEFAULT_SIZE = 10;

    public UnsortedST(int capacity){
       keys = (KEY[])new Object[capacity];
       values = (VALUE[]) new Object[capacity];
       size = 0;
    }

    public UnsortedST(){
        this(DEFAULT_SIZE);
    }

    private void resize(int capacity) {
        keys = Arrays.copyOf(keys, capacity);
        values = Arrays.copyOf(values, capacity);
    }

    @Override
    public void put(KEY key, VALUE value) {
        for (int i = 0; i < size; i++) {
            if (key.equals(keys[i])) {
                values[i] = value;
                return;
            }
        }
        if (size == keys.length) {
            resize(2 * keys.length);
        }
        keys[size] = key;
        values[size] = value;
        size++;
    }

    @Override
    public VALUE get(KEY key) {
        for (int i = 0; i < size; i++) {
            if (key.equals(keys[i])) {
                return values[i];
            }
        }
        return null;
    }

    @Override
    public boolean contains(KEY key) {
        return get(key) != null;
    }

    @Override
    public void delete(KEY key) {
        for (int i = 0; i < size; i++) {
            if (key.equals(keys[i])) {
                keys[i] = keys[size - 1];
                values[i] = values[size - 1];
                keys[size - 1] = null;
                values[size - 1] = null;
                size--;
                if (size > 0 && size == keys.length / 4) {
                    resize(keys.length / 2);
                }
                return;
            }
        }
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterable<KEY> keys() {
        return () -> new Iterator<KEY>() {
            private int i = 0;

            @Override
            public boolean hasNext() {
                return i < size;
            }

            @Override
            public KEY next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return keys[i++];
            }
        };
    }
}
