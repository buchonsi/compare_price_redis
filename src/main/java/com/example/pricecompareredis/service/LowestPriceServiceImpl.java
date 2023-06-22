package com.example.pricecompareredis.service;

import com.example.pricecompareredis.vo.Keyword;
import com.example.pricecompareredis.vo.Product;
import com.example.pricecompareredis.vo.ProductGrp;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

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

    @Override
    public int setNewProductGrp(ProductGrp newProductGrp) {
        List<Product> productList = newProductGrp.getProductList();

        //@TODO 하나만 있다고 가정? 보완 필요
        String productId = productList.get(0).getProductId();
        int price = productList.get(0).getPrice();

        myProdPriceRedis.opsForZSet().add(newProductGrp.getProdGrpId(), productId, price);

        return getProductGrpCard(newProductGrp);
    }

    @Override
    public int setNewProductGrpToKeyword(String keyword, String prodGrpId, double score) {
        myProdPriceRedis.opsForZSet().add(keyword, prodGrpId, score);
        return getProductRank(keyword, prodGrpId);
    }

    @Override
    public Keyword getLowestPriceProductByKeyword(String keyword) {
        Keyword returnInfo = new Keyword();
        List<ProductGrp> tempProdGrp = new ArrayList<>();

        //Keyword를 통해 ProductGroup을 가져오기(10개)
        tempProdGrp = getProdGrpUsingKeyword(keyword);

        //가져온 정보들을 리턴 할 Object에 넣기
        returnInfo.setKeyword(keyword);
        returnInfo.setProductGrpList(tempProdGrp);

        //해당 Object return
        return returnInfo;
    }

    public List<ProductGrp> getProdGrpUsingKeyword(String keyword) {

        List<ProductGrp> returnInfo = new ArrayList<>();

        //input 받은 keyword로 productGrpId를 조회
        List<String> prodGrpIdList = new ArrayList<>();
        prodGrpIdList =  List.copyOf(myProdPriceRedis.opsForZSet().reverseRange(keyword, 0, 9));    //keyword와 prod

        List<Product> tempProdList = new ArrayList<>();

        //10개 prodGrpId로 loop
        for (final String prodGrpId : prodGrpIdList) {
            //Loop로 ProdGrpId로 Prouduct:price를 가져오기(10개)
            ProductGrp tempProdGrp = new ProductGrp();

            Set prodAndPriceList = new HashSet();
            prodAndPriceList = myProdPriceRedis.opsForZSet().rangeWithScores(prodGrpId, 0, 9);
            Iterator<Object> prodPriceObj = prodAndPriceList.iterator();

            //loop로 product obj에 바인딩 (10개)
            while (prodPriceObj.hasNext()) {
                ObjectMapper objectMapper = new ObjectMapper();
                Product tempProduct = new Product();

                // [{"value":001-11101-10291}, {"score":11000},{}....]
                Map<String, Object> prodPriceMap = objectMapper.convertValue(prodPriceObj.next(), Map.class);

                //Product Obj bind
                tempProduct.setProductId(prodPriceMap.get("value").toString());    //prod_id
                tempProduct.setPrice(Double.valueOf(prodPriceMap.get("score").toString()).intValue());     //es 검색된 score
                tempProduct.setProdGrpId(prodGrpId);

                tempProdList.add(tempProduct);
            }
            
            //10개 product price 입력완료
            tempProdGrp.setProdGrpId(prodGrpId);
            tempProdGrp.setProductList(tempProdList);
            returnInfo.add(tempProdGrp);
        }

        return returnInfo;
    }

    public int getProductRank(Product product) {
        return myProdPriceRedis.opsForZSet().rank(product.getProdGrpId(), product.getProductId()).intValue();
    }

    public int getProductRank(String keyword, String prodGrpId) {
        return myProdPriceRedis.opsForZSet().rank(keyword, prodGrpId).intValue();
    }

    public int getProductGrpCard(ProductGrp productGrp) {
        return myProdPriceRedis.opsForZSet().zCard(productGrp.getProdGrpId()).intValue();
    }
}
