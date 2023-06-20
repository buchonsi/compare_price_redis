package com.example.pricecompareredis.vo;

/**
 * Redis Data Type
 *
 * String : 1:1 관계 (key : value)
 * Lists : 순서존재, Queue와 Stack 으로 사용됨
 * Sets : 순서와 관계없이 저장, 중복 불가
 * Sorted Sets : ZSET 이라고 불리며 Score의 개념이 존재. Set과 같은데 정렬이 필요한 곳에 씀
 * Hashes : Field:Value 여러 커플이 존재. RDB의 Table 개념으로 많이 사용
 *
 * ---명령어 ---
 * ZADD : 입력 (데이터를 넣음)
 * ZCARD : Count ( key의 맴버가 몇 개 있는지 개수 조회)
 * ZRANGE : 정렬 순서로 조회
 * ZRANGEBYSCORE : score를 함께 조회
 * ZRAM : 삭제
 * ZSCORE : 특정 member의 score를 조회
 * ZRANK : 특정 momber의 rank를 조회
 */
public class RedisType {
}
