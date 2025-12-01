CREATE TABLE 회원 (
    회원아이디 VARCHAR2(20) NOT NULL,
    비밀번호 VARCHAR2(20) NOT NULL,
    이름 VARCHAR2(20) NOT NULL,
    나이 NUMBER,
    이메일 VARCHAR2(50),
    등급 VARCHAR2(10) NOT NULL, 
    적립금 NUMBER DEFAULT 0 NOT NULL,
    PRIMARY KEY (회원아이디),
    CHECK(나이 >=0)
);

CREATE TABLE 상품 (
    상품번호 VARCHAR2(20) NOT NULL,
    상품명 VARCHAR2(50) NOT NULL,
    재고량 NUMBER DEFAULT 0 NOT NULL,
    단가 NUMBER DEFAULT 0 NOT NULL,
    CHECK(재고량 >= 0),
    CHECK(단가 >= 0),
    PRIMARY KEY (상품번호)
);

CREATE TABLE 저장 (
    장바구니번호 VARCHAR2(20) NOT NULL,
    상품번호 VARCHAR2(20) NOT NULL,
    상품명 VARCHAR2(50) NOT NULL,
    재고량 NUMBER NOT NULL,
    단가 NUMBER DEFAULT 0 NOT NULL,
    PRIMARY KEY (장바구니번호),
    FOREIGN KEY (상품번호) REFERENCES 상품(상품번호)
);

CREATE TABLE 주문 (
    주문번호 VARCHAR2(20) NOT NULL, 
    배송지 VARCHAR2(50) NOT NULL,
    주문일자 DATE NOT NULL,
    주문수량 NUMBER NOT NULL,
    회원아이디 VARCHAR2(20) NOT NULL,
    상품번호 VARCHAR2(20) NOT NULL,
    PRIMARY KEY (주문번호),
    FOREIGN KEY (회원아이디) REFERENCES 회원(회원아이디),
    FOREIGN KEY (상품번호) REFERENCES 상품(상품번호),
    CHECK (주문수량 >= 0)
);

CREATE TABLE 룩북 (
    룩북번호 VARCHAR2(20) NOT NULL,
    룩북이름 VARCHAR2(20) NOT NULL,
    출시가격 NUMBER NOT NULL,
    PRIMARY KEY (룩북번호),
    CHECK (출시가격 >= 0)
);