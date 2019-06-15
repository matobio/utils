package com.tobio.tobioutils.utils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class QuantityDistributorTest {

    @Before
    public void setUp() throws Exception {}


    @Test
    public void testDistributeQuantity() {

        List<QuantityElements> list = Arrays.asList(new QuantityElements(10), new QuantityElements(20), new QuantityElements(30));
        Map<QuantityElements, Number> result = QuantityDistributor.distributeQuantity(10, 30, list, QuantityElements::getQuantity);

        Assert.assertEquals(result.get(list.get(0)), 10d * 10 / 30);
        Assert.assertEquals(result.get(list.get(1)), 20d * 10 / 30);
        Assert.assertEquals(result.get(list.get(2)), 30d * 10 / 30);

        list = Arrays.asList(new QuantityElements(1), new QuantityElements(4), new QuantityElements(4));
        result = QuantityDistributor.distributeQuantity(5, 9, list, QuantityElements::getQuantity);

        Assert.assertEquals(result.get(list.get(0)), 1d * 5 / 9);
        Assert.assertEquals(result.get(list.get(1)), 4d * 5 / 9);
        Assert.assertEquals(result.get(list.get(2)), 4d * 5 / 9);

    }

    class QuantityElements {

        private Number quantity;


        public QuantityElements(Number quantity) {
            this.quantity = quantity;
        }


        public Number getQuantity() {
            return this.quantity;
        }


        public void setQuantity(Number quantity) {
            this.quantity = quantity;
        }
    }

}
