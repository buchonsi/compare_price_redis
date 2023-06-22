package com.example.pricecompareredis.controller;

import com.example.pricecompareredis.service.LowestPriceService;
import com.example.pricecompareredis.vo.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
}
