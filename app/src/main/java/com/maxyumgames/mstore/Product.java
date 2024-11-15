package com.maxyumgames.mstore;

public class Product {
    public int product_id;
    public int image_id;
    public String product_name;
    public String product_description;
    public double product_price;
    public Product(int product_id, int image_id, String product_name, String product_description, double product_price) {
        this.product_id = product_id;
        this.image_id = image_id;
        this.product_name = product_name;
        this.product_description = product_description;
        this.product_price = product_price;
    }
}
