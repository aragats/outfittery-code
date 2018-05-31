package com.outfittery.challenge.util;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

public class CollectionsUtilTest {

    @Test
    public void testNullToLowerCase() {
        Set<String> set = CollectionsUtil.toLowerCase(null);
        assertNotNull(set);
        assertTrue(set.isEmpty());
    }

    @Test
    public void testEmptyToLowerCase() {
        Set<String> set = CollectionsUtil.toLowerCase(new HashSet<>());
        assertNotNull(set);
        assertTrue(set.isEmpty());
    }

    @Test
    public void testToLowerCase() {
        List<String> strings = Arrays.asList("A", "B", "C", "D", "E");
        Set<String> set = CollectionsUtil.toLowerCase(new HashSet<>(strings));
        assertNotNull(set);
        assertFalse(set.isEmpty());
        assertTrue(set.size() == strings.size());
        for (String s : strings) {
            assertTrue(set.contains(s.toLowerCase()));
        }
    }
}
