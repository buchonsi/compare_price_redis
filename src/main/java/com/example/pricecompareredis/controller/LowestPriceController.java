package com.example.pricecompareredis.controller;

import com.example.pricecompareredis.exception.NotFoundException;
import com.example.pricecompareredis.service.LowestPriceService;
import com.example.pricecompareredis.vo.Keyword;
import com.example.pricecompareredis.vo.Product;
import com.example.pricecompareredis.vo.ProductGrp;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequiredArgsConstructor
public class LowestPriceController {

    private final LowestPriceService myLowestPriceService;

    @GetMapping("/getZSETValue")
    public Set getZsetValue(String key) {
        return myLowestPriceService.getZsetValue(key);
    }
//    @TODO 동작확인
    @GetMapping("product1")
    public Set getZsetValueWithStatus(String key) {
        try {
            return myLowestPriceService.getZsetValueWithStatus(key);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }
    //    @TODO 동작확인
    @GetMapping("product2")
    public Set getZsetValueUsingExController(String key) throws Exception {
        try {
            return myLowestPriceService.getZsetValueWithStatus(key);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }
    //    @TODO 동작확인
    @GetMapping("product3")
    public ResponseEntity<Set> getZsetValueUsingExControllerWithSpecificException(String key) throws Exception {
        Set<String> mySet = new HashSet<>();
        try {
            mySet = myLowestPriceService.getZsetValueWithSpecificException(key);
        } catch (NotFoundException e) {
            throw new Exception(e);
        }

        HttpHeaders responseHeaders = new HttpHeaders();

        return new ResponseEntity<>(mySet, responseHeaders, HttpStatus.OK);
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
