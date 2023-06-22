package com.example.pricecompareredis.controller;

import com.example.pricecompareredis.service.LowestPriceService;
import com.example.pricecompareredis.vo.Keyword;
import com.example.pricecompareredis.vo.Product;
import com.example.pricecompareredis.vo.ProductGrp;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequiredArgsConstructor
public class LowestPriceController {

    private final LowestPriceService myLowestPriceService;

    @GetMapping("/getZSETValue")
    public Set getZsetValue(String key) {
        return myLowestPriceService.getZsetValue(key);
    }

    @PutMapping("/product")
    public int setNewProduct(@RequestBody Product newProduct) {
        return myLowestPriceService.setNewProduct(newProduct);
    }

    @PutMapping("/productGroup")
    public int setNewProduct(@RequestBody ProductGrp newProductGrp) {
        return myLowestPriceService.setNewProductGrp(newProductGrp);
    }

    @GetMapping("/productPrice/lowest")
    public Keyword getLowestPriceProductByKeyword(String keyword) {
        return myLowestPriceService.getLowestPriceProductByKeyword(keyword);
    }

    @PutMapping("/productGrpToKeyword")
    public int setNewProduct(String keyword, String prodGrpId, double score) {
        return myLowestPriceService.setNewProductGrpToKeyword(keyword, prodGrpId, score);
    }
}
