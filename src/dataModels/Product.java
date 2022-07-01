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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
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