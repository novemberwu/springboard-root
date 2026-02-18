package com.example.book_service.datastructure;

import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class SortedST<KEY extends Comparable<KEY>, VALUE> implements ST<KEY,VALUE>{
    private static final int INIT_CAPACITY = 2;
    private KEY[] keys;
    private VALUE[] values;
    private int n = 0;

    public SortedST() {
        this(INIT_CAPACITY);
    }

    public SortedST(int capacity) {
        keys = (KEY[]) new Comparable[capacity];
        values = (VALUE[]) new Object[capacity];
    }

    private void resize(int capacity) {
        KEY[] tempk = (KEY[]) new Comparable[capacity];
        VALUE[] tempv = (VALUE[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
            tempk[i] = keys[i];
            tempv[i] = values[i];
        }
        values = tempv;
        keys = tempk;
    }

    @Override
    public void put(KEY key, VALUE value) {
        if (key == null) throw new IllegalArgumentException("key cannot be null");
        if (value == null) {
            delete(key);
            return;
        }

        int i = rank(key);

        // key is already in table
        if (i < n && keys[i].compareTo(key) == 0) {
            values[i] = value;
            return;
        }

        // insert new key-value pair
        if (n == keys.length) resize(2*keys.length);

        for (int j = n; j > i; j--) {
            keys[j] = keys[j-1];
            values[j] = values[j-1];
        }
        keys[i] = key;
        values[i] = value;
        n++;
    }

    @Override
    public VALUE get(KEY key) {
        if (key == null) throw new IllegalArgumentException("key cannot be null");
        if (isEmpty()) return null;
        int i = rank(key);
        if (i < n && keys[i].compareTo(key) == 0) return values[i];
        return null;
    }

    public int rank(KEY key) {
        if (key == null) throw new IllegalArgumentException("key cannot be null");
        int lo = 0, hi = n-1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int cmp = key.compareTo(keys[mid]);
            if (cmp < 0) hi = mid - 1;
            else if (cmp > 0) lo = mid + 1;
            else return mid;
        }
        return lo;
    }

    @Override
    public boolean contains(KEY key) {
        if (key == null) throw new IllegalArgumentException("key cannot be null");
        return get(key) != null;
    }

    @Override
    public void delete(KEY key) {
        if (key == null) throw new IllegalArgumentException("key cannot be null");
        if (isEmpty()) return;

        int i = rank(key);

        if (i == n || keys[i].compareTo(key) != 0) {
            return;
        }

        for (int j = i; j < n-1; j++) {
            keys[j] = keys[j+1];
            values[j] = values[j+1];
        }

        n--;
        keys[n] = null;
        values[n] = null;

        if (n > 0 && n == keys.length/4) resize(keys.length/2);
    }

    @Override
    public boolean isEmpty() {
        return n == 0;
    }

    @Override
    public int size() {
        return n;
    }

    @Override
    public Iterable<KEY> keys() {
        return Arrays.asList(Arrays.copyOf(keys, n));
    }
}
