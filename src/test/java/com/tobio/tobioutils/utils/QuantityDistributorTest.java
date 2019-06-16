package com.tobio.tobioutils.utils;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class QuantityDistributorTest {

    @Before
    public void setUp() throws Exception {}


    @Test
    public void testDistributeQuantity() {

        try {

            List<QuantityElements> list = Arrays.asList(new QuantityElements(10), new QuantityElements(20), new QuantityElements(30));
            QuantityDistributor.distributeQuantity(10, 30, list, QuantityElements::getQuantity, QuantityElements::setQuantity);

            Assert.assertEquals(list.get(0).getQuantity(), 10d * 10 / 30);
            Assert.assertEquals(list.get(1).getQuantity(), 20d * 10 / 30);
            Assert.assertEquals(list.get(2).getQuantity(), 30d * 10 / 30);

            list = Arrays.asList(new QuantityElements(1), new QuantityElements(4), new QuantityElements(4));
            QuantityDistributor.distributeQuantity(5, 9, list, QuantityElements::getQuantity, QuantityElements::setQuantity);

            Assert.assertEquals(list.get(0).getQuantity(), 1d * 5 / 9);
            Assert.assertEquals(list.get(1).getQuantity(), 4d * 5 / 9);
            Assert.assertEquals(list.get(2).getQuantity(), 4d * 5 / 9);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
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


        @Override
        public String toString() {
            return this.quantity.toString();
        }
    }

}
