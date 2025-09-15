-- 집계함수

SELECT 제조업체 FROM 제품;

-- 제품 테이블에서 제조 업체의 수를 검색하시오.
SELECT COUNT(DISTINCT 제조업체) AS "제조업체의 수" FROM 제품;

-- 주문 테이블에서 주문고객의 수
SELECT COUNT(*) FROM 주문;

SELECT COUNT(DISTINCT 주문고객) FROM 주문;

SELECT 주문고객 FROM 주문;

-- 그룹별 검색
-- 주문 테이블에서 주문제품별 수량의 합계를 검색하시오.
SELECT 주문제품, SUM(수량) FROM 주문 GROUP BY 주문제품 ORDER BY 주문제품 ASC;

SELECT * FROM 주문;

-- 주문 테이블에서 주문고객별 수량의 평균을 주문고객을 기준으로 내림차순으로 검색하시오. (단, 수량의 평균은 소수점 이하 첫째 자리까지 출력)
SELECT 주문고객, ROUND(AVG(수량), 1) AS "수량평균" FROM 주문 GROUP BY 주문고객 ORDER BY 주문고객 DESC;

-- 고객 테이블에서 등급별 적립금의 평균을 등급을 기준으로 오름차순으로 검색하시오. (단, 적립금의 평균은 소수점 이하 둘째 자리까지 출력)
SELECT 등급, ROUND(AVG(적립금)) AS "적림금 평균" FROM 고객 GROUP BY 등급 ORDER BY 등급;

SELECT * FROM 고객;

-- 제품 테이블에서 제조업체별로 제조한 제품의 개수와 제품 중 가장 비싼 단가를 검색하시오. (단, 제품의 개수는 제품수, 가장 비싼 단가는 최고가라는 컬럼명으로 출력)
SELECT 제조업체, COUNT(*) AS 제품수, MAX(단가) AS 최고가 FROM 제품 GROUP BY 제조업체;

SELECT * FROM 제품;

-- 고객 테이블에서 직업별 나이의 평균과 가장 작은 적립금을 검색하시오. (단, 나이의 평균은 평균나이, 가장 작은 적립금은 최저 적립금이라는 컬럼명으로 출력)
SELECT 직업, ROUND(AVG(나이)) AS "평균나이", MIN(적립금) AS "최저 적립금" FROM 고객 GROUP BY 직업;

SELECT * FROM 고객;

-- 제품 테이블에서 제품을 3개 이상 제조한 제조업체별로 제조한 제품의 개수와 제품 중 가장 비싼 단가를 검색하시오. (단, 제품의 개수는 제품수, 가장 비싼 단가는 최고가라는 컬럼명으로 출력)
SELECT 제조업체, COUNT(*) AS 제품수, MAX(단가) AS 최고가 FROM 제품 GROUP BY 제조업체 HAVING COUNT(*) >= 3;

-- 제품 테이블에서 [제품을 2개 이하 제조한] 제조업체별로 제조한 제품의 개수와 제품 중 가장 비싼 단가 중 4000원 이상인 것을 검색하시오. (단, 제품의 개수는 제품수, 가장 비싼 단가는 최고가라는 컬럼명으로 출력)
SELECT 제조업체, COUNT(*) AS 제품수, MAX(단가) AS 최고가 FROM 제품 GROUP BY 제조업체 HAVING COUNT(*) <= 2 AND MAX(단가) >= 4000;

-- 고객 테이블에서 적립금 평균이 1000원 이상인 등급의 대해 등급별 고객수와 적립금 평균을 검색하시오. (단, 고객수는 고객수로 적립금 평균은 평균 적립금으로 출력)
SELECT 등급, COUNT(*) AS "고객수", ROUND(AVG(적립금)) AS "평균 적립금" FROM 고객 GROUP BY 등급 HAVING ROUND(AVG(적립금)) >= 1000;

-- 주문 테이블에서 각 주문고객이 주문한 제품의 총주문수량을 주문제품별로 검색하시오(단, 주문제품, 주문고객, 총주문수량을 컬럼으로 출력하고 총주문수량이 30개 이상이며 주문 제품이 p01~p03까지의 제품만 출력)
SELECT 주문제품, 주문고객, SUM(수량) AS 총주문수량 FROM 주문 GROUP BY 주문제품, 주문고객 HAVING SUM(수량) >= 30 AND 주문제품 IN('p01', 'p02', 'p03') ORDER BY 주문제품 ASC; 

SELECT * FROM 주문;

-- 여러 테이블을 조인 검색
-- 자연조인(NATURAL JOIN)
-- 주문 테이블과 제품 테이블에서 주문번호, 주문고객, 제품명, 단가, 제조업체를 검색하시오.

SELECT 주문번호, 주문고객, 제품명, 단가, 제조업체 FROM 주문, 제품 WHERE 주문제품 = 제품번호;

-- banana 고객이 주문한 제품의 이름을 검색하시오.
SELECT 제품명 FROM 주문, 제품 WHERE 주문고객 = 'banana' AND 주문제품 = 제품번호;

-- 고객이름, 나이, 직업, 제품명, 배송지 등을 apple 고객이 주문한 정보를 검색하시오.
SELECT 고객이름, 나이, 직업, 제품명, 배송지 FROM 고객, 제품, 주문 WHERE 주문고객 = 'apple' AND 주문고객 = 고객아이디 AND 주문제품 = 제품번호;

-- 나이가 30세 이상인 고객이 주문한 제품의 번호, 제품명, 주문일자를 검색하시오.
SELECT * FROM 고객;
SELECT * FROM 제품;
SELECT * FROM 주문;

SELECT 주문제품, 제품명, 주문일자 FROM 주문, 제품, 고객 WHERE 주문제품 = 제품번호 AND 나이 >= 30 AND 주문고객 = 고객아이디;

-- 
