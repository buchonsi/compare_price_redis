package com.example.pricecompareredis.vo;

import lombok.Data;

import java.util.List;

@Data
public class ProductGrp {
    private String prodGrpId;   // FPG0001
    private List<Product> productList;  //[{d1fc1031-dalc-40da-9cd1-e9fef3f2a336, 25000}, {}....]
}
