------------------------------------------------------------------
-- 1. menu 테이블 생성
CREATE TABLE HANSOT_MENU (
		menu_id number(10) PRIMARY key
	,	menu_name varchar(50)
	,	menu_price number(10)
	,	category varchar(50)
)
;

SELECT *
FROM hansot_menu;


-- PK 에 대한 시퀀스 생성
CREATE SEQUENCE user_seq
INCREMENT BY 1
START WITH 1
MINVALUE 1
nocycle
;

CREATE SEQUENCE menu_seq
INCREMENT BY 1
START WITH 1
MINVALUE 1
nocycle
;

-- 현재 SEQUENCE 값 조회
SELECT menu_seq.CURRVAL FROM DUAL;


-- Menu 각 컬럼에 데이터 삽입
INSERT INTO HANSOT_MENU (
		menu_id
	,	menu_name
	,	menu_price
	,	category
) VALUES (
		menu_seq.NEXTVAL
	,	'테스트'
	,	'0'
	,	'도시락'
)
;



------------------------------------------------------------------
-- 2. users 테이블 생성
CREATE TABLE hansot_user (
		user_id number(10) PRIMARY key
	,	user_name varchar2(50)
	,	amount_paid number(10)
)
;

SELECT *
FROM HANSOT_USER ;







------------------------------------------------------------------
-- 3. hansot_reservation 테이블 생성

CREATE TABLE hansot_resevation (
		res_id number(10) -- 예약 번호(PK)
	,	res_date DATE   -- 예약일(YY.MM.DD)
	,	res_user varchar(50) -- 예약 담당자명
)
;




