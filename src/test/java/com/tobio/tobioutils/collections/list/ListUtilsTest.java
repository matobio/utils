package com.tobio.tobioutils.collections.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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


    @Test
    public void testSort() {

        List<TestObject> list = new ArrayList<>();
        list.add(new TestObject(0, 1, 2, 10d));
        list.add(new TestObject(1, 20, 2, 20d));
        list.add(new TestObject(2, 3, 2, 15d));
        list.add(new TestObject(3, 3, 1, 30d));
        list.add(new TestObject(4, 2, 1, 14d));
        list.add(new TestObject(5, 3, 2, 10d));

        list = ListUtils.sortMultiple(list, TestObject::getLength, TestObject::getQuantity);

        Assert.assertEquals(list.get(0).getId(), Integer.valueOf(0));
        Assert.assertEquals(list.get(1).getId(), Integer.valueOf(4));
        Assert.assertEquals(list.get(2).getId(), Integer.valueOf(5));
        Assert.assertEquals(list.get(3).getId(), Integer.valueOf(2));
        Assert.assertEquals(list.get(4).getId(), Integer.valueOf(3));
        Assert.assertEquals(list.get(5).getId(), Integer.valueOf(1));
    }


    @Test
    public void testGroupMultiple() {

        List<TestObject> list = new ArrayList<>();
        list.add(new TestObject(1, 1, 2, null));
        list.add(new TestObject(2, 20, 2, null));
        list.add(new TestObject(3, 3, 2, null));
        list.add(new TestObject(4, 3, 1, null));
        list.add(new TestObject(5, 2, 1, null));
        list.add(new TestObject(6, 1, 2, null));

        Map<List<Object>, List<TestObject>> map = ListUtils.groupMultiple(list, TestObject::getLength, TestObject::getWidth);

        Assert.assertEquals(5, map.size());
        Assert.assertEquals(2, map.get(Arrays.asList(1, 2)).size());
        Assert.assertEquals(Arrays.asList(1, 6), map.get(Arrays.asList(1, 2)).stream().map(TestObject::getId).collect(Collectors.toList()));
    }

    class TestObject {

        private Integer id;
        private Integer length;
        private Integer width;
        private Double  quantity;


        public TestObject(Integer id, Integer length, Integer width, Double quantity) {
            this.id = id;
            this.length = length;
            this.width = width;
            this.quantity = quantity;
        }


        @Override
        public String toString() {
            return "Id: " + this.id + ", length: " + this.length + ", width: " + this.width + ", quantity: " + this.quantity;
        }


        public String getIdString() {
            return this.id.toString();
        }


        public Integer getId() {
            return this.id;
        }


        public void setId(Integer id) {
            this.id = id;
        }


        public Integer getLength() {
            return this.length;
        }


        public void setLength(Integer length) {
            this.length = length;
        }


        public Integer getWidth() {
            return this.width;
        }


        public void setWidth(Integer width) {
            this.width = width;
        }


        public Double getQuantity() {
            return this.quantity;
        }


        public void setQuantity(Double quantity) {
            this.quantity = quantity;
        }
    }
}
