package com.tobio.tobioutils.collections.list;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ListUtilsTest {

    @Before
    public void setUp() throws Exception {}


    @Test
    public void testSum() {

        Assert.assertEquals(10d, ListUtils.sum(Arrays.asList(1d, 1d, 3d, 5d)).doubleValue(), 000.1);
        Assert.assertEquals(10d, ListUtils.sum(Arrays.asList(1d, 1d, null, 3d, 5d)).doubleValue(), 000.1);
        Assert.assertEquals(10d, ListUtils.sum(Arrays.asList(1d, 1, null, 3d, 5d)).doubleValue(), 000.1);
        Assert.assertEquals(70.5d, ListUtils.sum(Arrays.asList(10d, 10, null, 20d, 30.5)).doubleValue(), 000.1);
    }

}
