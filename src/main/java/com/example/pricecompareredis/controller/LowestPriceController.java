package com.example.pricecompareredis.controller;

import com.example.pricecompareredis.service.LowestPriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
}
