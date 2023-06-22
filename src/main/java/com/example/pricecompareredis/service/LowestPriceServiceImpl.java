package com.example.pricecompareredis.service;

import com.example.pricecompareredis.vo.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class LowestPriceServiceImpl implements LowestPriceService {

    private final RedisTemplate myProdPriceRedis;

    @Override
    public Set getZsetValue(String key) {
        Set myTempSet = new HashSet();
        myTempSet = myProdPriceRedis.opsForZSet().rangeWithScores(key, 0, 9);
        return myTempSet;
    }

    @Override
    public int setNewProduct(Product newProduct) {
        myProdPriceRedis.opsForZSet().add(newProduct.getProdGrpId(), newProduct.getProductId(), newProduct.getPrice());
        return getProductRank(newProduct);
    }

    public int getProductRank(Product product) {
        return myProdPriceRedis.opsForZSet().rank(product.getProdGrpId(), product.getProductId()).intValue();
    }
}
