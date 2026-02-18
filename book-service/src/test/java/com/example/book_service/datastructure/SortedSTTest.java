package com.example.book_service.datastructure;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class SortedSTTest {

    private SortedST<String, Integer> st;

    @BeforeEach
    public void setUp() {
        st = new SortedST<>();
    }

    @Test
    public void testPutAndGet() {
        st.put("c", 3);
        st.put("a", 1);
        st.put("b", 2);

        assertEquals(1, st.get("a"));
        assertEquals(2, st.get("b"));
        assertEquals(3, st.get("c"));
    }

    @Test
    public void testUpdateValue() {
        st.put("a", 1);
        st.put("a", 10);
        assertEquals(10, st.get("a"));
    }

    @Test
    public void testSizeAndIsEmpty() {
        assertTrue(st.isEmpty());
        assertEquals(0, st.size());

        st.put("a", 1);
        assertFalse(st.isEmpty());
        assertEquals(1, st.size());
    }

    @Test
    public void testDelete() {
        st.put("c", 3);
        st.put("a", 1);
        st.put("b", 2);

        st.delete("b");
        assertNull(st.get("b"));
        assertEquals(2, st.size());
        assertEquals(1, st.get("a"));
        assertEquals(3, st.get("c"));
    }

    @Test
    public void testContains() {
        st.put("a", 1);
        assertTrue(st.contains("a"));
        assertFalse(st.contains("b"));
    }

    @Test
    public void testRank() {
        st.put("c", 3);
        st.put("a", 1);
        st.put("e", 5);

        assertEquals(0, st.rank("a"));
        assertEquals(1, st.rank("c"));
        assertEquals(2, st.rank("e"));
        assertEquals(1, st.rank("b")); // Should be the index where 'b' would be inserted
    }

    @Test
    public void testResize() {
        // Test resizing up
        for (int i = 0; i < 10; i++) {
            st.put("key" + i, i);
        }
        assertEquals(10, st.size());
        assertEquals(9, st.get("key9"));

        // Test resizing down
        for (int i = 0; i < 8; i++) {
            st.delete("key" + i);
        }
        assertEquals(2, st.size());
    }

    @Test
    public void testKeys() {
        st.put("c", 3);
        st.put("a", 1);
        st.put("b", 2);

        List<String> keys = new ArrayList<>();
        st.keys().forEach(keys::add);

        assertEquals(List.of("a", "b", "c"), keys);
    }

    @Test
    public void testNullKey() {
        assertThrows(IllegalArgumentException.class, () -> st.put(null, 1));
        assertThrows(IllegalArgumentException.class, () -> st.get(null));
    }
}