package com.example.book_service.datastructure;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class UnsortedSTTest {

    private UnsortedST<String, Integer> st;

    @BeforeEach
    public void setUp() {
        st = new UnsortedST<>();
    }

    @Test
    public void testPutAndGet() {
        st.put("one", 1);
        st.put("two", 2);
        assertEquals(1, st.get("one"));
        assertEquals(2, st.get("two"));
        assertNull(st.get("three"));
    }

    @Test
    public void testPutUpdate() {
        st.put("one", 1);
        st.put("one", 11);
        assertEquals(11, st.get("one"));
        assertEquals(1, st.size());
    }

    @Test
    public void testDelete() {
        st.put("one", 1);
        st.put("two", 2);
        st.delete("one");
        assertNull(st.get("one"));
        assertEquals(2, st.get("two"));
        assertEquals(1, st.size());
    }

    @Test
    public void testDeleteFromEmpty() {
        st.delete("one");
        assertEquals(0, st.size());
    }

    @Test
    public void testDeleteNonExistent() {
        st.put("one", 1);
        st.delete("two");
        assertEquals(1, st.size());
    }

    @Test
    public void testContains() {
        st.put("one", 1);
        assertTrue(st.contains("one"));
        assertFalse(st.contains("two"));
    }

    @Test
    public void testIsEmpty() {
        assertTrue(st.isEmpty());
        st.put("one", 1);
        assertFalse(st.isEmpty());
    }

    @Test
    public void testSize() {
        assertEquals(0, st.size());
        st.put("one", 1);
        st.put("two", 2);
        assertEquals(2, st.size());
    }

    @Test
    public void testKeys() {
        st.put("one", 1);
        st.put("two", 2);
        st.put("three", 3);
        List<String> keys = new ArrayList<>();
        for (String key : st.keys()) {
            keys.add(key);
        }
        assertEquals(3, keys.size());
        assertTrue(keys.contains("one"));
        assertTrue(keys.contains("two"));
        assertTrue(keys.contains("three"));
    }

    @Test
    public void testResize() {
        // Test put to trigger resize
        for (int i = 0; i < 20; i++) {
            st.put("key" + i, i);
        }
        assertEquals(20, st.size());
        assertEquals(19, st.get("key19"));

        // Test delete to trigger resize
        for (int i = 0; i < 15; i++) {
            st.delete("key" + i);
        }
        assertEquals(5, st.size());
        assertNull(st.get("key14"));
    }
}
