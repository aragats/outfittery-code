package com.outfittery.challenge.util;

import com.outfittery.challenge.model.Stylist;
import org.junit.Assert;

import java.util.List;

public class MyAssert {

    // Theoretically, we can override equals() and hashCode() methods in Model but I do not want to rely on that.
    public static void assertEqualsStylist(Stylist expected, Stylist actual) {
        Assert.assertNotNull(expected);
        Assert.assertNotNull(actual);
        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertEquals(expected.getName(), actual.getName());
        Assert.assertEquals(expected.getState(), actual.getState());

    }


    public static void assertEqualsList(List<String> expected, List<String> actual) {
        Assert.assertNotNull(expected);
        Assert.assertNotNull(actual);
        Assert.assertTrue(expected.size() == actual.size());
        for (int i = 0; i < expected.size(); i++) {
            Assert.assertEquals(expected.get(i), actual.get(i));
        }
    }



}
