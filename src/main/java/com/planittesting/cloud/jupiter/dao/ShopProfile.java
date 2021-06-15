package com.planittesting.cloud.jupiter.dao;

import org.openqa.selenium.WebElement;

import java.util.*;

public class ShopProfile {
    private String productName;
    private int productQuantity;
    private Float productPrice;
    public static int count = 0;

    public ShopProfile() {}

    public ShopProfile(String productName, Integer productQuantity, Float productPrice) {
        super();
        this.productName = productName;
        this.productQuantity = productQuantity;
        this.productPrice = productPrice;
        count++;
    }

    public String getProductName(){
        return productName;
    }

    public Integer getProductQuantity(){
        return productQuantity;
    }
    public Float getProductPrice(){
        return productPrice;
    }
}