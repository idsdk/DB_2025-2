-- 고명석 고객이 주문한 제품의 고객아이디, 제품명, 단가를 검색하시오.
SELECT c.고객아이디, p.제품명, p.단가 FROM 고객 c, 주문 o, 제품 p 
    WHERE c.고객이름 = '고명석' AND c.고객아이디 = o.주문고객 AND p.제품번호 = o.주문제품;
    
-- 자연조인
-- 나이가 30세 이상인 고객이 주문한 제품의 주문제품과 주문일자를 검색하시오.
SELECT 주문제품, 주문일자 FROM 주문, 고객 WHERE 나이 >= 30 AND 고객아이디 = 주문고객; 

-- 내부조인
SELECT 주문제품, 주문일자 FROM 고객 INNER JOIN 주문 ON 고객아이디 = 주문고객 WHERE 나이 >= 30;
    
-- 외부조인
-- 주문하지 않은 고객도 포함해서 고객이름과 주문제품, 주문일자를 검색하시오.
-- 왼쪽 외부조인 (Left Outer Join)
SELECT 고객이름, 주문제품, 주문일자 FROM 고객 LEFT OUTER JOIN 주문 ON 고객아이디 = 주문고객;
-- 오른쪽 외부조인 (Right Outer Join)
SELECT 고객이름, 주문제품, 주문일자 FROM 주문 RIGHT OUTER JOIN 고객 ON 고객아이디 = 주문고객;

-- SuB Query
-- 달콤비스킷을 생산한 제조업체가 만든 제품들의 제품명과 단가를 검색하시오.
SELECT 제조업체 FROM 제품 WHERE 제품명 = '달콤비스킷';

SELECT 제품명, 단가 FROM 제품 
    WHERE 제조업체 = (SELECT 제조업체 FROM 제품 WHERE 제품명 = '달콤비스킷');
    
-- 주문테이블에서 쿵떡파이를 주문한 주문고객, 주문제품, 수량을 검색하시오.
SELECT * FROM 주문;

SELECT * FROM 제품;

SELECT 주문고객, 주문제품, 수량 FROM 주문, 제품 WHERE 제품명 = '쿵떡파이' AND 주문제품 = 제품번호;

SELECT 주문고객, 주문제품, 수량 FROM 주문 
    WHERE 주문제품 = (SELECT 제품번호 FROM 제품 WHERE 제품명 = '쿵떡파이');

-- 적립금이 가장 많은 고객의 고객이름과 적립금을 검색하시오.
SELECT 고객이름, 적립금 FROM 고객 WHERE 적립금 = (SELECT MAX(적립금) FROM 고객);

-- 적립금이 가장 적은 고객의 고객이름과 적립금을 검색하시오.
SELECT 고객이름, 적립금 FROM 고객 WHERE 적립금 = (SELECT MIN(적립금) FROM 고객);

-- 다중행 결과를 나타내는 Sub Query(비교연산자 사용불가능)
-- banana 고객이 주문한 제품의 제품번호, 제품명, 제조업체를 검색하시오.
SELECT 제품번호, 제품명, 제조업체 FROM 제품 
    WHERE 제품번호 IN (SELECT 주문제품 FROM 주문 WHERE 주문고객 = 'banana');
SELECT * FROM 제품;
SELECT * FROM 주문;
SELECT * FROM 주문 WHERE 주문고객 = 'banana';

-- SubQuery 다중행 결과에 사용하는 IN 연산자
-- 김씨 성을 가진 고객의 고객아이디, 나이, 적립금, 제품명, 단가를 검색하시오.
SELECT * FROM 고객;
SELECT * FROM 제품;
SELECT * FROM 주문;
SELECT 고객아이디, 나이, 적립금, 제품명, 단가 FROM 고객, 제품, 주문 
    WHERE 주문고객 = 고객아이디 
        AND 주문제품 = 제품번호 
            AND 주문고객 IN (SELECT 고객아이디 FROM 고객 WHERE 고객이름 LIKE '김%');

-- SubQuery 다중행 결과에 사용하는 Not IN 연산자
-- banana 고객이 주문하지 않은 제품의 제품명, 제조업체를 검색하시오.
SELECT 제품번호, 제품명, 제조업체 FROM 제품 WHERE 제품번호 NOT IN (SELECT 주문제품 FROM 주문 WHERE 주문고객 = 'banana');

-- 대한식품이 제조한 모든 제품의 단가보다 비싼 제품의 제품명, 단가, 제조업체를 검색하시오.
SELECT 제품명, 단가, 제조업체 FROM 제품 WHERE 단가 > ALL (SELECT 단가 FROM 제품 WHERE 제조업체 = '대한식품');

-- 2022년 3월 15일에 제품을 주문한 고객의 고객이름을 검색해보자.
SELECT 고객이름 FROM 고객, 주문 WHERE 주문고객 = 고객아이디 AND 주문일자 = '2022-3-15'; 

SELECT 고객이름 FROM 고객 
    WHERE 고객아이디 = (SELECT 주문고객 FROM 주문 WHERE 주문일자 = '2022-3-15');

SELECT 고객이름 FROM 고객 
    WHERE EXISTS (SELECT 주문고객 FROM 주문 WHERE 주문일자 = '2022-3-15' AND 주문고객 = 고객아이디);

-- 2022년 1월 1일에 제품을 주문한 고객이 아닌 고객이름을 검색해보자. (여러명인 경우)
SELECT 고객이름 FROM 고객 
    WHERE NOT EXISTS 
        (SELECT 주문고객 FROM 주문 WHERE 주문일자 = '2022-01-01' AND 주문고객 = 고객아이디);
