# redis를 이용한 가격비교 api 연습

## 프로젝트 목적
* eCommerce에서 자주 사용되는 제품을 검색하면 관련 상품이 나오고, 가격비교를 할 수 있는 Api를 실습해본다.
* Caching 목적으로 사용하는 Redis와 Spring을 연동하여 DB에 접근해본다.
* DB 장애를 대비하여 Redis를 Master, slave로 분산하고 Sentinel을 이용해 HA 설계를 해본다.
* Swagger를 사용하여 데이터 테스트를 해본다.
* AWS 서버에 Redis를 올리고 통신하면서 클라우드 서버에 친숙해져본다

## 작업 내용
* 인프라
  * AWS EC2 인스턴스 3대 설치 (프리티어 구성, t2.micro)
  * Os 환경 : Ubuntu
  * Redis 설치, master, slave 구성
  * sentinel 설치, 로드밸런싱
  * HA 구성 확인

* API
  * Keyword - 제품그룹 - 제품으로 구성된 응답값 생성 
  * https://github.com/buchonsi/compare_price_redis/blob/496868507cc99c0eae66458c494217f2eec1020b/src/main/java/com/example/pricecompareredis/vo/Keyword.java#L9-L10
  * Swagger 설정
  * Redis 통신
  
## 결론
* Redis와 Aws에 친숙해지기 위한 프로젝트였다.
* Redis는 Session storge로 사용하는 것만 알고 있었는데 Cache 저장소로 다양한 서비스에서 사용된다는 것을 알게 되었다.
* 아쉬운 점은 Aws를 프리티어로 사용하고 있었는데 이미지 생성, 데이터 입출력으로 사용량이 쭉쭉 올라가서 무서워서 테스트를 많이 못했다. 
* 다음에는 도커에 올려서 속도 측정, 부하 테스트 등도 실습해 볼 예정이다.

