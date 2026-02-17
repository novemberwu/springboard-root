package com.example.book_service.datastructure;

import org.springframework.stereotype.Component;

public class DummyST< KEY, VALUE> implements ST< KEY, VALUE>{
    @Override
    public void put(KEY key, VALUE value) {

    }

    @Override
    public VALUE get(KEY key) {
        return null;
    }

    @Override
    public boolean contains(KEY key) {
        return false;
    }

    @Override
    public void delete(KEY key) {

    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Iterable<KEY> keys() {
        return null;
    }
}
