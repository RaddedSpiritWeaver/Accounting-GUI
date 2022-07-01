package dataModels;

import dataModels.enums.ProductType;

public class Product {

    private String name;
    private ProductType productType;
    private int price;

    public Product(String name, ProductType productType, int price) {
        this.name = name;
        this.productType = productType;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", productType=" + productType +
                ", price=" + price +
                '}';
    }
}