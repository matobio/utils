package com.tobio.tobioutils.utils.objects;

public class TestObject {

    private Integer id;
    private Integer length;
    private Integer width;
    private Double  quantity;


    public TestObject(Integer id) {
        this.id = id;
    }


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