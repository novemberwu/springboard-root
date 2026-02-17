package com.example.book_service.datastructure;

public interface ST <KEY extends Comparable<KEY>, VALUE>{

    public void put(KEY key, VALUE value);
    public VALUE get(KEY key);
    public boolean contains(KEY key);
    public void delete(KEY key);
    public boolean isEmpty();
    public int size();
    public Iterable<KEY> keys();
}
