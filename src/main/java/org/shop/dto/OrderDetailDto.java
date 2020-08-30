package org.shop.dto;

/**
 * feel free to add any code to this class
 */
public class OrderDetailDto {
    private long id;
    private String name;
    private double price;

    public OrderDetailDto() {
    }

    public OrderDetailDto(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public OrderDetailDto(long id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
