--매니저
CREATE TABLE manager_tb (
    mg_id VARCHAR2(30) PRIMARY KEY,
    mg_pw VARCHAR2(30) NOT NULL,
    mg_name VARCHAR2(30) NOT NULL,
    mg_num  VARCHAR2(30) NOT NULL
);
INSERT INTO manager_tb VALUES('heehee01', 'heehee0000', '곽승환', '사원');
INSERT INTO manager_tb VALUES('heehee02', 'heehee0000', '오규탁', '사원');
INSERT INTO manager_tb VALUES('heehee03', 'heehee0000', '홍진성', '사원');
commit;

select * from manager_tb;

--공지
SELECT * FROM NOTICE; --공지사항페이지 조회
CREATE TABLE NOTICE( --공지사항페이지 만들기
    NT_NO NUMBER(6) NOT NULL, --번호
    NT_SUBJECT VARCHAR2(100) NOT NULL, -- 제목
    NT_CONTENT VARCHAR2(1000) NOT NULL, --내용
    NT_IMAGE VARCHAR2(30) NOT NULL, -- 이미지
    NT_DATE DATE NOT NULL, -- 작성일
    MG_ID VARCHAR2(30) NOT NULL, --매니저
    CONSTRAINT notice_fk FOREIGN KEY(mg_id) REFERENCES manager_tb(mg_id)
);
CREATE SEQUENCE NOTICE_SEQ; --공지사항시퀀스 생성
INSERT INTO NOTICE (NT_NO, NT_SUBJECT, NT_CONTENT, NT_IMAGE, NT_DATE, MG_ID) VALUES (NOTICE_SEQ.NEXTVAL, '희희쇼핑몰 운영정책', '따뜻한 거래문화를 만들기위해 우리모두의 관심과 노력이 필요해요. 운영정책은 따뜻하고 믿을수 있는 거래문화를 만들고 고객님들을 보호하기위한 최소한의 장치입니다.', '01.jpg', '2022-02-28', 'heehee01');
INSERT INTO NOTICE (NT_NO, NT_SUBJECT, NT_CONTENT, NT_IMAGE, NT_DATE, MG_ID) VALUES (NOTICE_SEQ.NEXTVAL, '개인정보처리방침', '희희쇼핑몰을 이용해주시는 고객님들께 감사드리며 희희쇼핑몰 이용약관과 개인정보처리방침에 대해 안내말씀드립니다.', '02.jpg', '2022-03-01', 'heehee02');
INSERT INTO NOTICE (NT_NO, NT_SUBJECT, NT_CONTENT, NT_IMAGE, NT_DATE, MG_ID) VALUES (NOTICE_SEQ.NEXTVAL, '나눔시행안내', '소중한 내 물건이 꼭 필요한 주인을 찾아갔으면 좋겠다는 생각을해보셨나요?', '03.jpg', '2022-03-02', 'heehee03');
INSERT INTO NOTICE (NT_NO, NT_SUBJECT, NT_CONTENT, NT_IMAGE, NT_DATE, MG_ID) VALUES (NOTICE_SEQ.NEXTVAL, '댓글기능이용안내', '댓글기능을 이용할때에는 폭언욕설을 삼가바랍니다.', '04.jpg', '2022-03-03', 'heehee01');
INSERT INTO NOTICE (NT_NO, NT_SUBJECT, NT_CONTENT, NT_IMAGE, NT_DATE, MG_ID) VALUES (NOTICE_SEQ.NEXTVAL, '개선사항', '추가업데이트 및 개선사항', '05.jpg', '2022-03-04', 'heehee01');
commit;

--member테이블
DROP TABLE member_tb CASCADE CONSTRAINTS;
CREATE TABLE member_tb (
    m_id VARCHAR2(30) PRIMARY KEY,
    m_pw VARCHAR2(30) NOT NULL,
    m_nickname VARCHAR2(30) NOT NULL UNIQUE,
    m_name VARCHAR2(30) NOT NULL,
    m_phone VARCHAR2(30) NOT NULL UNIQUE,
    m_gender VARCHAR2(30) NOT NULL,
    m_email VARCHAR2(30) NOT NULL UNIQUE,
    m_birth VARCHAR2(30) NOT NULL,
    m_registdate DATE NOT NULL,
    m_role VARCHAR2(30) NOT NULL
);

--user
INSERT INTO member_tb (m_id, m_pw, m_nickname, m_name, m_phone, m_gender, m_email, m_birth, m_registdate, m_role) VALUES ('guswjdals', '1234', '정민', '현정민', '010-5066-2573', '남', 'hjm_1935@naver.com', '1992-07-28', '2022-02-01', 'USER');
INSERT INTO member_tb (m_id, m_pw, m_nickname, m_name, m_phone, m_gender, m_email, m_birth, m_registdate, m_role) VALUES ('dltnstls', '1234', 'sunsin', '이순신', '010-1234-5678', '남', 'sunsin@naver.com', '1991-01-23', '2022-02-02', 'USER');
INSERT INTO member_tb (m_id, m_pw, m_nickname, m_name, m_phone, m_gender, m_email, m_birth, m_registdate, m_role) VALUES ('rkdrkacks01', '1234', 'chan', '강감찬', '010-1111-2222', '남', 'chanchan@hanmail.com', '1998-03-12', '2022-02-03', 'USER');
INSERT INTO member_tb (m_id, m_pw, m_nickname, m_name, m_phone, m_gender, m_email, m_birth, m_registdate, m_role) VALUES ('princess', '1234', '자칭공주', '정효선', '010-2505-6511', '여', 'hyosun@naver.com', '1996-01-01', '2022-02-04', 'USER');
INSERT INTO member_tb (m_id, m_pw, m_nickname, m_name, m_phone, m_gender, m_email, m_birth, m_registdate, m_role) VALUES ('jinjin01', '1234', '집이가까운', '홍진성', '010-7743-0237', '남', 'jinseong@naver.com', '1993-01-02', '2022-02-05', 'USER');
INSERT INTO member_tb (m_id, m_pw, m_nickname, m_name, m_phone, m_gender, m_email, m_birth, m_registdate, m_role) VALUES ('tmdghks', '1234', '팀장킬러', '곽승환', '010-4876-3038', '남', 'kwak11@naver.com', '1995-01-10', '2022-02-06', 'USER');
INSERT INTO member_tb (m_id, m_pw, m_nickname, m_name, m_phone, m_gender, m_email, m_birth, m_registdate, m_role) VALUES ('taktak36', '2134', '마곡동불주먹', '오규탁', '010-3425-2363', '남', 'taktak01@naver.com', '1992-03-03', '2022-02-07', 'USER');
INSERT INTO member_tb (m_id, m_pw, m_nickname, m_name, m_phone, m_gender, m_email, m_birth, m_registdate, m_role) VALUES ('chan0329', '1234', '아프지말아요', '최찬희', '010-3321-5687', '남', 'sick@naver.com', '1995-09-23', '2022-02-08', 'USER');
INSERT INTO member_tb (m_id, m_pw, m_nickname, m_name, m_phone, m_gender, m_email, m_birth, m_registdate, m_role) VALUES ('gyuhui89', '1234', '대장로', '곽유희', '010-3573-3462', '여', 'gyugyuhui89@naver.com', '1989-01-08', '2022-02-09', 'USER');
INSERT INTO member_tb (m_id, m_pw, m_nickname, m_name, m_phone, m_gender, m_email, m_birth, m_registdate, m_role) VALUES ('down37', '1234', '정겨운', '정다운', '010-8547-1257', '남', 'download92@naver.com', '1992-07-13', '2022-02-01', 'USER');
INSERT INTO member_tb (m_id, m_pw, m_nickname, m_name, m_phone, m_gender, m_email, m_birth, m_registdate, m_role) VALUES ('light92', '1234', 'huni', '이광훈', '010-1112-3462', '남', 'sheen@naver.com', '1992-11-08', '2022-02-11', 'USER');

--admin
insert INTO member_tb VALUES ('heehee01', 'heehee0000', '어드민', '곽승환', '010-1231-1231', '남', 'www@naver.com', '92/07/28', '22/02/01', 'ADMIN');
insert INTO member_tb VALUES ('heehee02', 'heehee0000', '어드민1', '오규탁', '010-1231-1232', '남', 'www@ndaver.com', '92/07/28', '22/02/01', 'ADMIN');
insert INTO member_tb VALUES ('heehee03', 'heehee0000', '어드민2', '홍진성', '010-1231-1233', '남', 'www@naver.net', '92/07/28', '22/02/01', 'ADMIN');



commit;
select * from member_tb;
DELETE FROM member_tb WHERE m_id='12345';

--상품 관련 테이블들(product, product_kind, product_stafe, product_way product_staus)
drop table PRODUCT_KIND;
CREATE TABLE PRODUCT_KIND(
p_part VARCHAR2(20) PRIMARY KEY,
pk_name VARCHAR (30)
);
SELECT *FROM PRODUCT_KIND;
INSERT INTO PRODUCT_KIND(p_part,pk_name) VALUES('1', '수유,위생,케어용품');
INSERT INTO PRODUCT_KIND(p_part,pk_name) VALUES('2', '의류잡화');
INSERT INTO PRODUCT_KIND(p_part,pk_name) VALUES('3', '도서,장난감');
INSERT INTO PRODUCT_KIND(p_part,pk_name) VALUES('4', '유모차,관련용품');
commit;

DROP TABLE PRODUCT_STATE;
CREATE TABLE PRODUCT_STATE(
p_condition VARCHAR2(30) PRIMARY KEY,
ps_name VARCHAR2 (30)
);
SELECT * FROM PRODUCT_STATE;
INSERT INTO PRODUCT_STATE(p_condition,ps_name) VALUES('A', '새상품,미개봉');
INSERT INTO PRODUCT_STATE(p_condition,ps_name) VALUES('B', '사용감 별로 없음');
INSERT INTO PRODUCT_STATE(p_condition,ps_name) VALUES('C', '사용감 있음');
commit;

--test
select * from product order by p_no desc;
DELETE FROM product WHERE p_no=1;

DROP TABLE product CASCADE CONSTRAINTS;
select * from product;
DROP TABLE  product CASCADE CONSTRAINTS;

CREATE TABLE product(
p_no NUMBER PRIMARY KEY,
p_title VARCHAR2 (30 CHAR) NOT NULL,
p_name VARCHAR2(30 CHAR) NOT NULL,
p_part VARCHAR2 (20 CHAR) NOT NULL,
p_staus VARCHAR2 ( 10 CHAR) NOT NULL,
p_way VARCHAR2(10 CHAR) NOT NULL,
p_date DATE,
p_price NUMBER (10) NOT NULL,
p_pro VARCHAR2 (20 CHAR),
p_condition VARCHAR2 (30 CHAR) NOT NULL,
p_file1 VARCHAR2 (100 CHAR) NOT NULL,
p_file2 VARCHAR2 (100 CHAR),
p_file3 VARCHAR2 (100 CHAR),
p_detail VARCHAR2 (500 CHAR) NOT NULL,
p_nickname VARCHAR2 (30) NOT NULL,
CONSTRAINT product_nickname_fk FOREIGN KEY(p_nickname) REFERENCES member_tb(m_nickname),
CONSTRAINT product_part_fk FOREIGN KEY(p_part) REFERENCES PRODUCT_KIND(p_part),
CONSTRAINT product_condition_fk FOREIGN KEY(p_condition) REFERENCES PRODUCT_STATE(p_condition)
);
DROP TABLE product_seq CASCADE CONSTRAINTS;
DROP SEQUENCE product_seq;
CREATE SEQUENCE product_seq;

SELECT * FROM product;
commit;

-- p_part상품 분류 1 =수유*위생* 스킨 2 = 의류잡화 3 = 장난감*도서 4 = 유모차관련용품
-- p_condition상품 상태 A = 새상품&미개봉 B= 사용감 조금있음 C= 사용감 있음
-- p_way거래 방식 0 = 직거래  00 = 택배  000 = 둘다 가능 
-- p_status 거래가능 Y or 거래완료 N

INSERT INTO product(p_no, p_title, p_name, p_part, p_staus, p_way, p_date, p_price, p_pro, p_condition, p_file1, p_file2, p_file3, p_detail,p_nickname)
        VALUES(product_seq.NEXTVAL,'안전한 유아치약 팝니다!!','레드씰 키즈치약', '1','Y', '0', SYSDATE, 4000,'레드씰',
        'A','a1-1.JPG','a1-2.JPG','a1-3.JPG','미개봉새제품, 24년 3월까지 사용기간 넉넉 !  4천원 2개 : 7천원 ','sunsin' ); 

INSERT INTO product(p_no, p_title, p_name, p_part,p_staus, p_way, p_date, p_price, p_pro, p_condition, p_file1, p_file2, p_file3, p_detail,p_nickname)
        VALUES(product_seq.NEXTVAL,'기저귀 발진 크림 두개팔아요! ','무스텔라 비타민 베리얼 크림', '1','Y','0', SYSDATE, 17000,'무스텔라',
        'A','a2-1.JPG','a2-2.JPG','a2-3.JPG','신생아부터 쓸 수 있고! 발진 난 부분이나 팔 다리 빨갛게 접혀서'|| CHR(13) || CHR(10) ||
        '붉게 올라오는 곳에 발라주면 좋아요~ 봄, 여름에 유용하게 썼어요! '|| CHR(13) || CHR(10) ||
        '약국에서 12500원에 판대되고 있어요~ ','chan' ); 

INSERT INTO product(p_no, p_title, p_name, p_part,p_staus, p_way, p_date, p_price, p_pro, p_condition, p_file1, p_file2, p_file3, p_detail,p_nickname)
        VALUES(product_seq.NEXTVAL,'밴드형 기저귀 판매해요~!','베이비드라이 밴드현 기저귀', '1','Y', '00', SYSDATE, 35000,'팸퍼스',
        'A','a3-1.JPG','a3-2.JPG','','아이가 급히 팬티형으로 갈아타며 밴드형이 많이 남아서 판매합니다~','huni' ); 

INSERT INTO product(p_no, p_title, p_name, p_part,p_staus, p_way, p_date, p_price, p_pro, p_condition, p_file1, p_file2, p_file3, p_detail,p_nickname)
        VALUES(product_seq.NEXTVAL,'수유용품, 산후복대, 육아책 팔아요','수유용품', '1','Y','000' , SYSDATE, 9000,'파마라스틱',
        'C','a4-1.JPG','','','산후 복대:파마라스틱 복대, 직수입 이태리 찍찍이로 허리길이 조절가능'|| CHR(13) || CHR(10) ||
        '수유팩 1개, 아기구강 멸균티슈 1개 아기 낳고나면 바로 모유수유할때 필요함'|| CHR(13) || CHR(10) ||
        '아기마스크1개, 아기손수건 1장쓴거 아니고 깨끗하게 보관한 새거 입니다.'|| CHR(13) || CHR(10) ||
        '아기마스크는 가제손수건 재질로 만들어졌어요~','정겨운' ); 


INSERT INTO product(p_no, p_title, p_name, p_part,p_staus, p_way, p_date, p_price, p_pro, p_condition, p_file1, p_file2, p_file3, p_detail,p_nickname)
        VALUES(product_seq.NEXTVAL,'산후 관리 로션팔아요^.^','파머스마사지오일,바디워시,코코넛로션', '1','Y','0' , SYSDATE, 27000,'파머스',
        'A','a5-1.JPG','a5-2.JPG','a5-3.JPG','출산후 선물로 산후관리 크림을 많이 받아서~ 팔아요 '|| CHR(13) || CHR(10) ||'산후 관리및 수유후 피부탄력관리 제품','대장로' ); 

INSERT INTO product(p_no, p_title, p_name, p_part,p_staus, p_way, p_date, p_price, p_pro, p_condition, p_file1, p_file2, p_file3, p_detail,p_nickname)
        VALUES(product_seq.NEXTVAL,'쌍둥이 수유쿠션 팔아요 1+1','얀니허그 수유쿠션', '1','Y', '00', SYSDATE, 5000,'얀니허그',
        'B','a6-1.JPG','','','쌍둥이나 연년생 키우시는 분들께 필요한 셀프 수유쿠션입니다.'|| CHR(13) || CHR(10) ||
        '매일은 안쓰셔도 가지고 있으면 한명 먹이고 다른 한명 케어할때 유용하게 쓰실 수 있어요~!'|| CHR(13) || CHR(10) ||
        '둥이맘 특히 강추입니다!!👍'|| CHR(13) || CHR(10) ||
        '수유쿠션으로 쓰고 나서도 돌지나서까지 짱구베개로도 엄청 잘 썼어요~!! ','정겨운' ); 

INSERT INTO product(p_no, p_title, p_name, p_part, p_staus, p_way, p_date, p_price, p_pro, p_condition, p_file1, p_file2, p_file3, p_detail,p_nickname)
        VALUES(product_seq.NEXTVAL,'유아용 소독티슈~ 팔아요','그린핑거 소독티슈', '1', 'Y','0', SYSDATE, 20000,'그린핑거',
        'A','a7-1.JPG','','',' 유아용품 소독티슈 1팩에 6000원 ! 4팩에 2만원에 가져가세요~ '|| CHR(13) || CHR(10) ||
        '자극적이지 않아서 안심하고 쓸 수 있어요~  ' ,'집이가까운'); 
        
INSERT INTO product(p_no, p_title, p_name, p_part, p_staus,p_way, p_date, p_price, p_pro, p_condition, p_file1, p_file2, p_file3, p_detail,p_nickname)
        VALUES(product_seq.NEXTVAL,'마스크필수인 코시국, 아동용마스크','애니가드 소형마스크','1','Y', '0', SYSDATE, 10000,'애니가드',
        'A','a8-1.JPG','a8-2.JPG','','총 12장 입니다~ 코시국에 ㅜㅜ 답답하지만  '|| CHR(13) || CHR(10) ||
        ' 안쓸수 없잖아요. 아이가 많이 답답해 하지 않는 것 같아요! ','아프지말아요' ); 



INSERT INTO product(p_no, p_title, p_name, p_part,p_staus, p_way, p_date, p_price, p_pro, p_condition, p_file1, p_file2, p_file3, p_detail,p_nickname)
        VALUES(product_seq.NEXTVAL,'저자극 아기로션,샴푸 거래해요! ','암쉐이 스왕떼르말 베르가닉 로션,샴푸', '1','Y', '0', SYSDATE, 35000,'암웨이',
        'A','a9-1.JPG','a9-2.JPG','a9-4.JPG','아기 피부가 예민해서 겨우 쓰는 제품이 정해져있는데, 선물이 들어와서 내놔요!'|| CHR(13) || CHR(10) || 
        '로션은 박스개봉도 안하고, 샴푸는 개봉만 했어요~ '|| CHR(13) || CHR(10) || 
        '혹시 각각 필요하시면 로션 , 샴푸 각각 2만원씩에 팔아요! ','마곡동불주먹' ); 

INSERT INTO product(p_no, p_title, p_name, p_part,p_staus, p_way, p_date, p_price, p_pro, p_condition, p_file1, p_file2, p_file3, p_detail,p_nickname)
        VALUES(product_seq.NEXTVAL,'분유케이스*저장팩*모유저장팩','실리만 분유케이스', '1','Y', '0', SYSDATE, 10000,'실리만',
        'B','a10-1.JPG','a10-2.JPG','a10-3.JPG','분유통, 분유저장통은 사용감있지만  소독 다했어요~ '|| CHR(13) || CHR(10) ||
        '**구매하시는 분 일회용 분유저장팩, 모유저장팩 그냥 드릴게요! '|| CHR(13) || CHR(10) || 
        '모유저장팩은 이유식할때 유용했구요~'|| CHR(13) || CHR(10) || 
        '저장팩은 일회용이어서 위생적이고 편했어요! ' ,'팀장킬러'); 


-------------------------------------------------------------------------------------------------------------------
INSERT INTO product(p_no, p_title, p_name, p_part,p_staus, p_way, p_date, p_price, p_pro, p_condition, p_file1, p_file2, p_file3, p_detail,p_nickname)
        VALUES(product_seq.NEXTVAL,'버버리키즈 외투팝니다!','버버리키즈코트', '2','Y', '0', SYSDATE, 65000,'버버리',
        'B','c1-1.JPG','c1-2.JPG','c1-3.JPG','선물받았는데 아이가 훌쩍커서 몇번 안입었어요~ '|| CHR(13) || CHR(10) ||
        '사이즈는 5y -110 이구요~ 저희애는 4살에 입혔어요 ~','자칭공주'); 

INSERT INTO product(p_no, p_title, p_name, p_part,p_staus, p_way, p_date, p_price, p_pro, p_condition, p_file1, p_file2, p_file3, p_detail,p_nickname)
        VALUES(product_seq.NEXTVAL,'젤리슈즈팔아요!','미니멜리사 젤리슈즈', '2','Y', '000', SYSDATE, 30000,'미니멜리사',
        'A','c2-1.JPG','c2-2.JPG','c2-3.JPG',' size는 140에 풀박상태입니다!!'|| CHR(13) || CHR(10) ||'완전 새상품이에요 신겨놓으면 더 귀여워요~','정민');         
        
INSERT INTO product(p_no, p_title, p_name, p_part,p_staus, p_way, p_date, p_price, p_pro, p_condition, p_file1, p_file2, p_file3, p_detail,p_nickname)
        VALUES(product_seq.NEXTVAL,'브랜드 아동운동화 팔아요!','내셔널지오그래픽운동화', '2','Y', '00', SYSDATE, 75000,'내셔널지오그래픽',
        'A','c3-1.JPG','c3-2.JPG','c3-3.JPG','사이즈는 220이에요 !'|| CHR(13) || CHR(10) || '작게 나와서 210인 아이부터 착용가능할 것 같아요!','집이가까운');         
 
INSERT INTO product(p_no, p_title, p_name, p_part,p_staus, p_way, p_date, p_price, p_pro, p_condition, p_file1, p_file2, p_file3, p_detail,p_nickname)
        VALUES(product_seq.NEXTVAL,'키즈바람막이 팔아요~','나이키바람막이', '2','Y', '0', SYSDATE, 65000,'나이키',
        'A','c4-1.JPG','c4-2.JPG','c4-3.JPG','사이즈는 M이구요 10살아이에게 잘 맞아요~ !'|| CHR(13) || CHR(10) ||
        '상태 양호하구요 ! 정품맞아요!! ', '아프지말아요');
        
INSERT INTO product(p_no, p_title, p_name, p_part,p_staus, p_way, p_date, p_price, p_pro, p_condition, p_file1, p_file2, p_file3, p_detail,p_nickname)
        VALUES(product_seq.NEXTVAL,'핑크 비키니세트팔아요~','유아비키니수영복', '2','Y', '000', SYSDATE, 18000,'스타일노리터',
        'A','c5-1.JPG','c5-2.JPG','c5-3.JPG','핫핑크색의 쓰리피스 수영복이에요'|| CHR(13) || CHR(10) ||'한번 입혔구요 ! XS 사이즈에요~ '|| CHR(13) || CHR(10) ||
        '2~3살 아이한테 맞아요! 개월수에 비해 큰 아이는 착용 어려울 수 있어요 ! ','huni');
        
INSERT INTO product(p_no, p_title, p_name, p_part,p_staus, p_way, p_date, p_price, p_pro, p_condition, p_file1, p_file2, p_file3, p_detail,p_nickname)
        VALUES(product_seq.NEXTVAL,'보들보들 도톰한 후리스','후리스', '2','Y', '0', SYSDATE, 5000,'',
        'B','c6-1.JPG','c6-2.JPG','c6-3.JPG',' 가볍게 입고 나갈 수 있는 외투에요~ 저렴하게 가져가세요'|| CHR(13) || CHR(10) ||'세탁완료 ! 하자없음 ! 12~24개월 정도 입을 수 있어요 ! ','자칭공주');
        
INSERT INTO product(p_no, p_title, p_name, p_part,p_staus, p_way, p_date, p_price, p_pro, p_condition, p_file1, p_file2, p_file3, p_detail,p_nickname)
        VALUES(product_seq.NEXTVAL,'키즈잠옷세트 팝니다','수면잠옷세트', '2','Y', '0', SYSDATE, 10000,'이브니에',
        'A','c7-1.JPG','c7-2.JPG','c7-3.JPG','유아용잠옷 (3~4세) 입을 수 있구요 사이즈는 60입니다 '|| CHR(13) || CHR(10) ||'이브니에유아 잠옷은 백화점입점 브랜드에요~'|| CHR(13) || CHR(10) ||
        '선물 받았는데 아이한테 안맞아서 필요하신 분 가져가세요~','정민');
        
INSERT INTO product(p_no, p_title, p_name, p_part,p_staus, p_way, p_date, p_price, p_pro, p_condition, p_file1, p_file2, p_file3, p_detail,p_nickname)
        VALUES(product_seq.NEXTVAL,'남자아이 정장세트 팔아요!','키즈보타이 정장세트', '2','Y', '00', SYSDATE, 10000,'',
        'B','c8-1.JPG','c8-2.JPG','c8-3.JPG','새것 같지는 않지만 가성비 만족하실거에요~ '|| CHR(13) || CHR(10) ||'특별한 컨셉사진계획 중이시라면 !추천드려요!'|| CHR(13) || CHR(10) ||
        '정말 귀여워요~ 꼬마신랑같이 귀여워요! ','chan');
        
INSERT INTO product(p_no, p_title, p_name, p_part,p_staus, p_way, p_date, p_price, p_pro, p_condition, p_file1, p_file2, p_file3, p_detail,p_nickname)
        VALUES(product_seq.NEXTVAL,'귀여운 뽀글가방있어요~','미아방지가방', '2','Y', '0', SYSDATE, 23000,'',
        'A','c9-1.JPG','c9-2.JPG','c9-3.JPG','S 스몰 사이즈 (돌전후부터 3, 4세 적합) '|| CHR(13) || CHR(10) ||'카멜 - 민트 컬러'|| CHR(13) || CHR(10) ||
        '가로 21센치 / 폭 8센치 / 높이 23센치'|| CHR(13) || CHR(10) ||'미아방지 끈 걸이 있음'|| CHR(13) || CHR(10) ||'택 부착, 비닐 포장 안 뜯어본 상태고, 더스트백 박스포장채 그대로 있어서 선물용으로도 좋아요','자칭공주');
        
INSERT INTO product(p_no, p_title, p_name, p_part,p_staus, p_way, p_date, p_price, p_pro, p_condition, p_file1, p_file2, p_file3, p_detail,p_nickname)
        VALUES(product_seq.NEXTVAL,'패션모자팔아요-캉골','키즈모자', '2','Y', '000', SYSDATE, 30000,'캉골',
        'A','c10-1.JPG','c10-2.JPG','',' 캉골 모자 각각 2만원에 팝니다!  택배비는 별도로 3천원이요~  '|| CHR(13) || CHR(10) ||'두개 같이 하시면 택배비 무료로 해드릴게요 !!','sunsin');        


--------------------------------------------------------------------------------------------------------------------------------------
 INSERT INTO product(p_no, p_title, p_name, p_part,p_staus, p_way, p_date, p_price, p_pro, p_condition, p_file1, p_file2, p_file3, p_detail,p_nickname)
        VALUES(product_seq.NEXTVAL,'벤츠 AMG GT 저소음 유아 붕붕카 팔아요~','벤츠 AMG GT 저소음 유아 붕붕카', '3','Y', '0', SYSDATE, 40000,'하나완구',
        'B','b1-1.PNG','b1-2.PNG','','벤츠 붕붕카를 판매합니다.'|| CHR(13) || CHR(10) ||
        '아이가 사용을하다가 새로 선물을 받게되어 깨끗한 제품을 판매하게 되었어요!','마곡동불주먹');
 INSERT INTO product(p_no, p_title, p_name, p_part,p_staus, p_way, p_date, p_price, p_pro, p_condition, p_file1, p_file2, p_file3, p_detail,p_nickname)
        VALUES(product_seq.NEXTVAL,'새로운 육아 잇템 키즈 방방이!','키즈 트램폴린', '3','Y', '00', SYSDATE, 140000,'스몰럭셔리',
        'B','b2-1.PNG','b2-2.PNG','','아이가 뛰어놀며 아주 재밋게 즐길 수있는 방방이를 판매해요.'|| CHR(13) || CHR(10) ||
        '옆에 보호할 수있게 되어있어 안전하게 즐길 수 있어요!','팀장킬러');
 INSERT INTO product(p_no, p_title, p_name, p_part,p_staus, p_way, p_date, p_price, p_pro, p_condition, p_file1, p_file2, p_file3, p_detail,p_nickname)
        VALUES(product_seq.NEXTVAL,'내친구 콩이 강아지 판매해요!','내친구콩이', '3','Y', '0', SYSDATE, 26000,'주영이앤씨',
        'A','b3-1.PNG','b3-2.PNG','','1주일전에 선물받은 내친구 콩이 강아지인형'|| CHR(13) || CHR(10) ||
        '아이가 남자아이라서 사용하지않아 새제품 중고가로 판매합니다!','자칭공주');
 INSERT INTO product(p_no, p_title, p_name, p_part,p_staus, p_way, p_date, p_price, p_pro, p_condition, p_file1, p_file2, p_file3, p_detail,p_nickname)
        VALUES(product_seq.NEXTVAL,'코끼리 모양의 유아용 미끄럼틀~','유아용 로켓 미끄럼틀', '3','Y', '0', SYSDATE, 20000,'쿠쿠토이즈',
        'C','b4-1.PNG','b4-2.PNG','','4개월정도 사용을하여 밝은색이여서 조금 떄가있지만 사용하기엔 괜찮습니다.'|| CHR(13) || CHR(10) ||
        '크기가있어 가져가실 수 있는분 구매해주세요!','chan');
INSERT INTO product(p_no, p_title, p_name, p_part,p_staus, p_way, p_date, p_price, p_pro, p_condition, p_file1, p_file2, p_file3, p_detail,p_nickname)
        VALUES(product_seq.NEXTVAL,'한글쓰기 10권세트','홈스쿨 워크북 만4세 한글쓰기 10권세트', '3','Y', '00', SYSDATE, 10000,'',
        'A','b5-1.PNG','b5-2.PNG','b5-3.PNG','사은품으로 받았지만 아이가 한글을 이미 쓸줄알아서 판매합니다.'|| CHR(13) || CHR(10) ||
        '만 4세용으로 한글교육에 도움이 되는 책입니다.','아프지말아요');
 INSERT INTO product(p_no, p_title, p_name, p_part,p_staus, p_way, p_date, p_price, p_pro, p_condition, p_file1, p_file2, p_file3, p_detail,p_nickname)
        VALUES(product_seq.NEXTVAL,'튤립 사운드북 판매해요~~','오감놀이 동요+아기 똑똑 동요(튤립 사운드북 2종)', '3','Y', '0', SYSDATE, 15000,'스마트베어',
        'B','b6-1.PNG','b6-2.PNG','b6-3.PNG','3개월전 구매하여 사용감은 있습니다. 아이가 이제는 가지고 놀지않아 판매를 하려고합니다.'|| CHR(13) || CHR(10) ||
        '사운드북이라서 아기가 참 잘가지고 놀아요!','자칭공주');
INSERT INTO product(p_no, p_title, p_name, p_part,p_staus, p_way, p_date, p_price, p_pro, p_condition, p_file1, p_file2, p_file3, p_detail,p_nickname)
        VALUES(product_seq.NEXTVAL,'컬러풀 매직보드 판매합니다!','와이드컬러매직보드', '3','Y', '000', SYSDATE, 12000,'(주)동명',
        'B','b7-1.PNG','b7-2.PNG','','아이 선물로 2개월전에 받은 것으로 사용감이 거의 없어요~'|| CHR(13) || CHR(10) ||
        '아이들이 그림그리고 놀기에 좋아요!!','집이가까운');
INSERT INTO product(p_no, p_title, p_name, p_part,p_staus, p_way, p_date, p_price, p_pro, p_condition, p_file1, p_file2, p_file3, p_detail,p_nickname)
        VALUES(product_seq.NEXTVAL,'빙글빙글 낚시 놀이!','베비몽 빙글빙글 하마낚시놀이', '3','Y', '0', SYSDATE, 25000,'(주)원더코퍼레이션',
        'B','b8-1.PNG','b8-2.PNG','','3개월 사용한 제품이지만 깨끗해서 사용하기 좋아요.'|| CHR(13) || CHR(10) ||
        '아이가 가지고 놀기에 좋습니다!!','huni');
INSERT INTO product(p_no, p_title, p_name, p_part,p_staus, p_way, p_date, p_price, p_pro, p_condition, p_file1, p_file2, p_file3, p_detail,p_nickname)
        VALUES(product_seq.NEXTVAL,'스티커 놀이북 새상품 판매!','스티커놀이북', '3','Y', '000', SYSDATE, 15000,'',
        'A','b9-1.PNG','b9-2.PNG','','새상품 스티커북 판매해요!'|| CHR(13) || CHR(10) ||
        '새상품 싸게 판매합니다!!','정겨운');
INSERT INTO product(p_no, p_title, p_name, p_part,p_staus, p_way, p_date, p_price, p_pro, p_condition, p_file1, p_file2, p_file3, p_detail,p_nickname)
        VALUES(product_seq.NEXTVAL,'놀이텐트 판매해요~~','키즈스페이스 놀이텐트', '3','Y', '0', SYSDATE, 20000,'',
        'B','b10-1.PNG','b10-2.PNG','','3개월 사용해서 사용감 적은 골판지로된 놀이텐트 판매해요.'|| CHR(13) || CHR(10) ||
        '사용감이 적어서 사용하기 좋은 거의 새상품입니다. 아이방에 두기 좋아요!','sunsin');

------------------------------------------------------------------------------------------------------------------------
INSERT INTO product(p_no, p_title, p_name, p_part,p_staus, p_way, p_date, p_price, p_pro, p_condition, p_file1, p_file2, p_file3, p_detail,p_nickname)
        VALUES(product_seq.NEXTVAL,'유모차 판매합니다!','리안 솔로 절충형 유모차', '4','Y', '00', SYSDATE, 300000, '리안',
        'B','d1-1.PNG','d1-2.PNG','d1-3.PNG','2개월정도 사용한 유모차 판매합니다. 사용감은 적습니다.'|| CHR(13) || CHR(10) ||
        '햇빛을 차단해주는 막이 있어서 사용하기 좋아요','자칭공주');
INSERT INTO product(p_no, p_title, p_name, p_part,p_staus, p_way, p_date, p_price, p_pro, p_condition, p_file1, p_file2, p_file3, p_detail,p_nickname)
        VALUES(product_seq.NEXTVAL,'아이를 바로 바라볼수있는 유모차 판매합니다.', 'G5 디럭스 아기 신생아 명품 유모차', '4','Y', '00', SYSDATE, 800000, 'G5',
        'B','d2-1.PNG','d2-2.PNG','','여러 기능이 있어서 사용하기 편리한 유모차 판매합니다.'|| CHR(13) || CHR(10) ||
        '가격은 좀있지만 사용용도가 좋습니다. 새로 유모차가 생겨서 판매하게 되었어요.','sunsin');
INSERT INTO product(p_no, p_title, p_name, p_part,p_staus, p_way, p_date, p_price, p_pro, p_condition, p_file1, p_file2, p_file3, p_detail,p_nickname)
        VALUES(product_seq.NEXTVAL,'유모차 대용 벤츠 손잡이 자동차 판매합니다.','전동푸쉬카 벤츠 GL63 AMG', '4','Y', '0', SYSDATE, 200000, '벤츠',
        'B','d3-1.PNG','d3-2.PNG','','벤츠 전동푸쉬카 손잡이가 있어서 유모차 대용으로 좋습니다.'|| CHR(13) || CHR(10) ||
        '아이가 크면 손잡이없이도 사용할 수있어서 사용하기 좋아요.','마곡동불주먹');
INSERT INTO product(p_no, p_title, p_name, p_part,p_staus, p_way, p_date, p_price, p_pro, p_condition, p_file1, p_file2, p_file3, p_detail,p_nickname)
        VALUES(product_seq.NEXTVAL,'폴딩 유모차 판매해요~~!!','치코 구디 절충형 유모차', '4','Y', '000', SYSDATE, 50000, '구디',
        'A','d4-1.PNG','d4-2.PNG','','폴딩형 유모차를 유모차가 있는데 얻게되어 판매해요.'|| CHR(13) || CHR(10) ||
        '폴딩식으로 접을수있고 가격도 저렴합니다.','집이가까운');
INSERT INTO product(p_no, p_title, p_name, p_part,p_staus, p_way, p_date, p_price, p_pro, p_condition, p_file1, p_file2, p_file3, p_detail,p_nickname)
        VALUES(product_seq.NEXTVAL,'고급 유모차 판매합니다!!!!','잉글레시나 클래식 디럭스 유모차', '4','Y', '0', SYSDATE, 1000000, '잉글레시나',
        'B','d5-1.PNG','d5-2.PNG','','사용하던 고급유모차 아이가 커서 사용할 수없어 판매해요! 사용감이 조금 있지만'|| CHR(13) || CHR(10) ||
        '원래 가격이 비싼 유모차여서 가격이 좀있는것이고 바닐라 색으로 이쁜 유모차예요~','정민');
INSERT INTO product(p_no, p_title, p_name, p_part,p_staus, p_way, p_date, p_price, p_pro, p_condition, p_file1, p_file2, p_file3, p_detail,p_nickname)
        VALUES(product_seq.NEXTVAL,'스마트 유모차 판매해요!','콤비 오라스타 스마트 유모차', '4','Y', '000', SYSDATE, 300000, '오리스타',
        'B','d6-1.PNG','d6-2.PNG','','오토 스탑 기능이 있는 안전한 유모차예요~'|| CHR(13) || CHR(10) ||
        '사용감이 없는 물건이예요 연락주세요!','huni');
INSERT INTO product(p_no, p_title, p_name, p_part,p_staus, p_way, p_date, p_price, p_pro, p_condition, p_file1, p_file2, p_file3, p_detail,p_nickname)
        VALUES(product_seq.NEXTVAL,'포크레인 붕붕카 판매해요!','포크레인 자동차 붕붕카', '4','Y', '0', SYSDATE, 80000,'',
        'B','d7-1.PNG','d7-2.PNG','','아이가 커서 판매하게 되었어요.'|| CHR(13) || CHR(10) ||
        '포크레인모양으로 남자아이가 좋아해요.','정겨운');         
INSERT INTO product(p_no, p_title, p_name, p_part,p_staus, p_way, p_date, p_price, p_pro, p_condition, p_file1, p_file2, p_file3, p_detail,p_nickname)
        VALUES(product_seq.NEXTVAL,'카시트 겸용 유모차 판매해요~~!!','두나 카시트 겸용 유모차', '4','Y', '000', SYSDATE, 350000, '두나',
        'B','d8-1.PNG','d8-2.PNG','','카시트 겸용으로 사용할 수있는 유모차예요. 색상은 블랙이예요!'|| CHR(13) || CHR(10) ||
        '사용감이 조금있지만 깔끔하게 사용했습니다.','아프지말아요');
INSERT INTO product(p_no, p_title, p_name, p_part,p_staus, p_way, p_date, p_price, p_pro, p_condition, p_file1, p_file2, p_file3, p_detail,p_nickname)
        VALUES(product_seq.NEXTVAL,'유모차를 판매해요!!','마이트랙스 플렉스 시그니처 절충형 유모차', '4','Y', '00', SYSDATE, 150000, '밤비노',
        'B','d9-1.PNG','d9-2.PNG','d9-3.PNG','사용감이 조금있는 유모차 판매합니다.'|| CHR(13) || CHR(10) ||
        '아이가 커서 판매하게되어 싸게 판매해요.','팀장킬러');
INSERT INTO product(p_no, p_title, p_name, p_part,p_staus, p_way, p_date, p_price, p_pro, p_condition, p_file1, p_file2, p_file3, p_detail,p_nickname)
        VALUES(product_seq.NEXTVAL,'유모차를 판매합니다','엑스렌더 절충형 유모차', '4','Y', '000', SYSDATE, 300000, '(주)코엔코',
        'A','d10-1.PNG','d10-2.PNG','','접이식이여서 보관하기 좋은 유모차 예요.'|| CHR(13) || CHR(10) ||
        '유모차 개봉후에 사용을 한적이 없어서 새제품과 다름 없습니다.','sunsin');         
        
                
commit;

SELECT * FROM product ORDER BY p_no;

--상품 댓글
DROP table product_reply;
CREATE TABLE product_reply(
reply_no number(6) PRIMARY KEY,
reply_content varchar2(500) NOT NULL,
reply_date TIMESTAMP NOT NULL,
reply_nickname varchar2(30) NOT NULL,
CONSTRAINT reply_nickname_fk FOREIGN KEY (reply_nickname) REFERENCES member_tb(m_nickname),
p_no NUMBER NOT NULL
CONSTRAINT product_reply_fk REFERENCES product(p_no)
);


-- SELECT * FROM product_reply WHERE p_no=10 ORDER BY reply_no DESC;
drop sequence product_reply_reply_no;
create sequence product_reply_reply_no
 start with 1
    increment by 1
    maxvalue 10000;

 INSERT into product_reply values(product_reply_reply_no.nextval,
 '어머 인형이 너무 귀엽네요 제가 사고싶은데 chan12로 카톡 주세요!!','2022-03-11 11:20','chan',23);
 INSERT into product_reply values(product_reply_reply_no.nextval,
 '헉쓰... 늦었네요.. 다음에도 귀여운 인형 올리시면 그땐 제가 먼저 사고싶네요ㅎㅎ','2022-03-11 21:20','정민',23);
 INSERT into product_reply values(product_reply_reply_no.nextval,
 '혹시 직거래 가능하실까요? mins12 로 카톡 주세요','2022-03-12 11:20','정민',1);
  INSERT into product_reply values(product_reply_reply_no.nextval,
 '앗 혹시 이미 팔렸을까요? 안팔렸다면 제가 사고 싶은데 댓글 주세요~','2022-03-12 16:28','정겨운',1);
  INSERT into product_reply values(product_reply_reply_no.nextval,
 '어머 전에 저랑 거래했던 분 맞죠?ㅎㅎ 카톡 드렸어요~~ 그때보니 같은 동네던데 이번에도 직거래합시다!','2022-03-12 11:20','정민',9);
 INSERT into product_reply values(product_reply_reply_no.nextval,
 '혹시 직거래 가능하실까요? home11 로 카톡 주세요','2022-03-13 11:20','집이가까운',11 );
  INSERT into product_reply values(product_reply_reply_no.nextval,
 '아 저희 아이한테 완전 딱일거 같은데 ㅠㅠ 팔렸겠죠...? 호오오오옥시나 안팔렸다면 sunsinjjang으로 카톡 주세요!','2022-03-13 18:27','sunsin',11);
  INSERT into product_reply values(product_reply_reply_no.nextval,
 '어머 구성 괜찮네요 strong11로 카톡 주시겠어요?','2022-03-13 11:11','아프지말아요',7);
  INSERT into product_reply values(product_reply_reply_no.nextval,
 '저 당장 필요해서 그러는데 제가 댁 앞으로 찾아가겠습니다. kill 로 카톡 주세요','2022-03-14 10:20','팀장킬러',16);
  INSERT into product_reply values(product_reply_reply_no.nextval,
 '혹시 사이즈가 어떻게 되나요?? 제가 바로 칼입금 해드릴테니 ace 로 카톡 주세요','2022-03-14 09:21','마곡동불주먹',18);
  INSERT into product_reply values(product_reply_reply_no.nextval,
 '저희집 꼬마가 지금 당장 들고싶다고 난리인데 혹시.. 오늘 거래 괜찮으실까요? 괜찮으시다면 sunsinjjang으로 톡 부탁드립니다^_^*','2022-03-15 11:20','sunsin',1);
  INSERT into product_reply values(product_reply_reply_no.nextval,
 '어머 차 상태 깔끔하니 딱 우리아이 차네요 ㅎㅎ princess 로 카톡 주세요','2022-03-15 12:20','자칭공주',1);
  INSERT into product_reply values(product_reply_reply_no.nextval,
 '혹시 거래 때 남편이 나가도 괜찮을까요? king으로 카톡 부탁드릴게요','2022-03-12 11:20','대장로',24);
  INSERT into product_reply values(product_reply_reply_no.nextval,
 'mins12로 카톡 주세요! 근무 중이라 확인이 조금 늦을 수 있는 점 미리 양해 구합니다','2022-03-17 10:20','정민',25);
  INSERT into product_reply values(product_reply_reply_no.nextval,
 '혹시 직거래 가능하실까요? chan12 로 톡 남주세요','2022-03-20 11:20','chan',40);
  INSERT into product_reply values(product_reply_reply_no.nextval,
 '혹시 직거래 가능하실까요? strong11 로 카톡 주세요','2022-03-22 13:20','아프지말아요',37);
  INSERT into product_reply values(product_reply_reply_no.nextval,
 '혹시 직거래 가능하실까요? king 로 카톡 주세요','2022-03-24 11:20','대장로',32);
  INSERT into product_reply values(product_reply_reply_no.nextval,
 '혹시 직거래 가능하실까요? kill 로 카톡 주세요','2022-03-24 11:20','팀장킬러',30);
 commit;
 select * from product_reply;
 
 --커뮤니티(육아소통) 테이블
 
 DROP TABLE community_tb CASCADE CONSTRAINTS;
 create table community_tb(
c_no number primary key,
c_subject varchar2(50 CHAR) not null,
c_content varchar2(1000 CHAR) not null,
c_image varchar2(50 CHAR) null,
c_date date not null,
m_nickname varchar2(30 CHAR) null
    CONSTRAINT community_tb_fk REFERENCES member_tb(m_nickname)
);
DROP SEQUENCE community_tb_c_no;
create sequence community_tb_c_no
    start with 1
    increment by 1
    maxvalue 10000;

insert into community_tb values(COMMUNITY_TB_C_NO.nextval,'육아휴직 아시는 분 계신가요?', '아이 낳고 출산휴가만 쓰고 복직해서 육아휴직 그대로 살아있거든요.' || CHR(13) || CHR(10) || '그런데 올해부터 육아휴직하면 한달에 150만원 (이전은 최대 90) 받는다고 해서' || CHR(13) || CHR(10) || '율해 출생아부터인지 아니면 올해부터 쓰는 사람에게 적용인건지 궁금합니다.' || CHR(13) || CHR(10) || '혹시 아시는 분 계실까요?' || CHR(13) || CHR(10) || CHR(13) || CHR(10)|| CHR(13) || CHR(10),'','2021-02-12','정겨운');
insert into community_tb values(COMMUNITY_TB_C_NO.nextval, '지금 다니는 곳이 굳센카드가 안 되는 곳인데..', '지금 굳센카드 사용하던 수업이 종결 되면서 ' || CHR(13) || CHR(10) || '다른 치료 받던 곳에 문의해보니 굳센카드가 전부 안되네요 ㅠㅠ ' || CHR(13) || CHR(10) || '굳센카드 사용을 위해서라도.. 새로운 치료 추가를 해야할까요?' || CHR(13) || CHR(10) || CHR(13) || CHR(10) || CHR(13) || CHR(10) ,'', '2021-02-27', '정겨운');
insert into community_tb values(COMMUNITY_TB_C_NO.nextval, '육아 스트레스 장난 아니네요..', '요즘따라 육아 스트레스를 너무 많이 받아요 ㅠㅠ ' || CHR(13) || CHR(10) || '어디 데리고 나가지도 못하고 집에만 있으니 더 스트레스 받더라구요 ' || CHR(13) || CHR(10) || '육아 고수니들 조언 부탁드려요' || CHR(13) || CHR(10) || CHR(13) || CHR(10) || CHR(13) || CHR(10) , '', '2021-03-05', '집이가까운');
insert into community_tb values(COMMUNITY_TB_C_NO.nextval,'신혼 때 맞춘 반지가 손에 꽉껴서..ㅠㅠㅋㅋㅋ', '종로에서 다시 맞췄어요 백화점가서 살까 했는데 ' || CHR(13) || CHR(10) || '종로금방에서 금 몇돈씩 해서 맞추는게 나중에라도 돈 될듯해서' || CHR(13) || CHR(10) || ' 다시 맞췄는데 오늘 오라하네요 얼른 끼고 싶어요 ㅎㅎ' || CHR(13) || CHR(10) || CHR(13) || CHR(10) || CHR(13) || CHR(10) , '', '2021-03-05', '자칭공주');
insert into community_tb values(COMMUNITY_TB_C_NO.nextval, '신혼 때 가구 언제 버리셨나요?', '울 엄니가 그 때 일일드라마 속에' || CHR(13) || CHR(10) || ' 나오던 가구 예쁘다고 그걸로 신혼가구 해주셨는데..' || CHR(13) || CHR(10) || ' 왜이리 멀쩡한지 유행도 안타는 디자인.. ' || CHR(13) || CHR(10) || '심지어 지금봐도 좀 이쁨 ㅋㅋ 혹시나 하고 검색했더니 지금도 팔고 있네요 ' || CHR(13) || CHR(10) || '저 결혼한지 이십년 넘어가는데 ㅜㅜ 다들 언제 바꾸셨나요?' || CHR(13) || CHR(10) || CHR(13) || CHR(10) ||  CHR(13) || CHR(10) ,'','2021-03-16','대장로');
insert into community_tb values(COMMUNITY_TB_C_NO.nextval, '책육아 노하우', '아이책을 정리하다가 조언이 필요해 글 써봅니다.' || CHR(13) || CHR(10) || ' 저는 책읽기를 좋아하는 편입니다. ' || CHR(13) || CHR(10) || '푹 빠져서 읽기도 하고 뒤적뒤적 가볍게 보기도 좋아합니다.' || CHR(13) || CHR(10) || ' 20살 이후부터 좋아하게 되었지요~^^' || CHR(13) || CHR(10) || ' 아이도 책육아를 하고 싶은데 방법을 모르겠어요 ' || CHR(13) || CHR(10) || '현재 4살 남아 입니다. 책을 자주자주 바꿔주면 좋다고 하던데 ' || CHR(13) || CHR(10) || '책가격도 만만찮고.. 아이가 시큰둥한 책도 있어서 어렵네요 ㅠㅠ ' || CHR(13) || CHR(10) || '육아선배님들~ 조언부탁드립니다~♡♡♡' || CHR(13) || CHR(10) || CHR(13) || CHR(10) ||  CHR(13) || CHR(10) , '', '2021-03-18', '정민');
insert into community_tb values(COMMUNITY_TB_C_NO.nextval, '이유식 의자','이유식을 다음주부터 시작하려고 하는데 ' || CHR(13) || CHR(10) || '아직 하나도 준비를 못했어요 ㅠㅠ ' || CHR(13) || CHR(10) || '일단 이유식 의자 먼저 사야할까요?' || CHR(13) || CHR(10) || CHR(13) || CHR(10) || CHR(13) || CHR(10) , '', '2021-03-21','마곡동불주먹');
insert into community_tb values(COMMUNITY_TB_C_NO.nextval, '이유식 브랜드요~', '3갤 지나 이제 이유식을 알아보려 하는데요 ' || CHR(13) || CHR(10) || '배달 이유식 브랜드 좀 알려주세요~ ' || CHR(13) || CHR(10) || '뭐가뭔지 하나도 모르겠네요ㅠㅠ' || CHR(13) || CHR(10) || CHR(13) || CHR(10) || CHR(13) || CHR(10) , '', '2021-03-21', 'huni');
insert into community_tb values(COMMUNITY_TB_C_NO.nextval, '남편이 코로나 확진이네요ㅠㅠ', '평소 집-회사만 오가던 남편인데..' || CHR(13) || CHR(10) || ' 지난 주 수욜에 몸이 안좋다고 해서 혹시몰라 자가키트를 사서' || CHR(13) || CHR(10) || ' 검사해보니 양성이라 pcr검사 추가로 하고 양성판정 받았네요..ㅠㅠ ' || CHR(13) || CHR(10) || '다들 코로나 조심하세요' || CHR(13) || CHR(10) || CHR(13) || CHR(10) || CHR(13) || CHR(10) , '', '2021-03-29','아프지말아요');
insert into community_tb values(COMMUNITY_TB_C_NO.nextval, '부천사시는 분 계신가요~?', '요즘 집에만 있느라 넘 답답해서 오랜만에 가족끼리 외식하려고 하는데 ' || CHR(13) || CHR(10) || '요즘 어디가 핫하나요~? 다들 추천~~ 부탁해요~!' || CHR(13) || CHR(10) || CHR(13) || CHR(10) || CHR(13) || CHR(10) ||, '', '2021-04-02', 'chan');
insert into community_tb values(COMMUNITY_TB_C_NO.nextval, '벌써 봄이네요', '시리던 겨울이 바로 엊그제 같은데 어느 새 봄이 훌쩍 다가왔네요..' || CHR(13) || CHR(10) || ' 육아에 지쳐 한동안 지쳐있었는데 거리의 핀 꽃들을 보니 힘이 납니다.' || CHR(13) || CHR(10) || ' 다들 오늘도 화이팅 하세요~' || CHR(13) || CHR(10) || CHR(13) || CHR(10) || CHR(13) || CHR(10),'','2021-04-03', '팀장킬러');
insert into community_tb values(COMMUNITY_TB_C_NO.nextval, '이 놈의 미세먼지 콱 그냥 때려주고 싶네요', '날이 풀리면 뭐하나요...' || CHR(13) || CHR(10) || ' 미세먼지 때문에 환기도 마음 껏 못하는데 아휴..' || CHR(13) || CHR(10) || ' 오늘도 갑갑하게 문 다 닫아놓고 공청기만 돌리고 있으니' || CHR(13) || CHR(10) || ' 심란하네요ㅠㅠ 우리애들 푸른하늘 보는게 왜 이리 힘든지..' || CHR(13) || CHR(10) || ' 중국놈들 콱 그냥 줘패면 소원이 없겠어요' || CHR(13) || CHR(10) || CHR(13) || CHR(10) || CHR(13) || CHR(10) , '', '2021-04-06','마곡동불주먹');
insert into community_tb values(COMMUNITY_TB_C_NO.nextval, '다들 아이에게 과자 먹이시나요?', '저희 집은 4갤 아이와 3살 된 아이 이렇게 둘인데' || CHR(13) || CHR(10) || ' 최대한 아이들한테 과자 안먹이려고 하는데' || CHR(13) || CHR(10) || ' 다들 유난이라고 하시니 너무 힘드네요..' || CHR(13) || CHR(10) || ' 제가 유난인건가요...ㅠㅠ?' || CHR(13) || CHR(10) || CHR(13) || CHR(10) || CHR(13) || CHR(10) , '', '2021-04-11', 'sunsin');
insert into community_tb values(COMMUNITY_TB_C_NO.nextval, '간만에 가족 나들이 다녀왔어요~', '오늘 날이 너무 좋아서 근처로 꽃놀이 겸 다녀왔네요~' || CHR(13) || CHR(10) || ' 활짝 핀 꽃들과 울 딸랑구들 신나서 뛰어다니는 모습 보니' || CHR(13) || CHR(10) || ' 너무 좋더라구요 이런게 소확행이겠죠?' || CHR(13) || CHR(10) || ' 다들 주말 잘 보내세요~^^' || CHR(13) || CHR(10) || CHR(13) || CHR(10) || CHR(13) || CHR(10), '', '2021-04-12', '정민');
insert into community_tb values(COMMUNITY_TB_C_NO.nextval, '아내 생일선물 추천부탁드려요~', '곧 아내 생일인데,' || CHR(13) || CHR(10) || ' 뭐 갖고싶냐 물어봐도 괜찮다는 말만 해서 고민이 많네요ㅠㅠ' || CHR(13) || CHR(10) || ' 깜짝 서프라이즈로 놀라게 해주고 싶은데 뭐 좋은 방법 없을까요~?' || CHR(13) || CHR(10) || CHR(13) || CHR(10) || CHR(13) || CHR(10) , '', '2021-04-16', '팀장킬러');
insert into community_tb values(COMMUNITY_TB_C_NO.nextval, '남표니랑 요리학원 다니는 분 계신가요~', '남편이 오늘 제 생일이라고 미역국을 해줬는데 웬걸..' || CHR(13) || CHR(10) || ' 이게 미역국인지 소금 국인지.... 에휴 해준 정성이 있긴 한데' || CHR(13) || CHR(10) || ' 너무 끔찍한 맛이라 못먹겠더라구요..' || CHR(13) || CHR(10) || ' 그래서 이참에 같이 요리학원 다녀볼까 하는데 어떨까요?' || CHR(13) || CHR(10) || CHR(13) || CHR(10) || CHR(13) || CHR(10)  ,'', '2021-04-20', '아프지말아요' );
insert into community_tb values(COMMUNITY_TB_C_NO.nextval, '다들 어린이날에 뭐하시나요?', '이제 곧 어린이날이라 애들이랑 근처라도 다녀올까 하는데' || CHR(13) || CHR(10) || ' 서울근교로 다녀오기 좋은 곳 있을까요~?' || CHR(13) || CHR(10) || CHR(13) || CHR(10) || CHR(13) || CHR(10) , '', '2021-04-23', '유후');
insert into community_tb values(COMMUNITY_TB_C_NO.nextval, '어린이날 아이 선물 뭐가 좋을까요?', '울 딸랑구 엘사를 너무너무 좋아해서' || CHR(13) || CHR(10) || ' 엘사 드레스를 사줄까 했더니 품절이네요..ㅡㅡ 아놔..' || CHR(13) || CHR(10) || ' 다들 빠르시네요...' || CHR(13) || CHR(10) || ' 이거 말곤 생각을 안해뒀는데 다들 뭐 준비하시나요?' || CHR(13) || CHR(10) || CHR(13) || CHR(10) || CHR(13) || CHR(10) , '', '2021-04-26', '팀장킬러');
insert into community_tb values(COMMUNITY_TB_C_NO.nextval, '용산 카페 추천해요', '이번에 어프로치?라는 카페를 다녀왔는데 요즘 가기 딱 좋더라구요~' || CHR(13) || CHR(10) || ' 용산역에서 좀 걸어야 하긴 하지만, 햇빛이 촥~ 들어오니 너무 좋더라구요~' || CHR(13) || CHR(10) || ' 커피도 맛있고 브런치 종류도 다 맛나네요' || CHR(13) || CHR(10) || ' 근처 사시는 분들 함 가보세요' || CHR(13) || CHR(10) || CHR(13) || CHR(10) || CHR(13) || CHR(10) , '', '2021-05-01', '대장로');
insert into community_tb values(COMMUNITY_TB_C_NO.nextval, '다들 놀이공원 가시나요?', '코로나라 아이들이랑 한번도 안갔는데' || CHR(13) || CHR(10) || ' 어디서 놀이공원 영상을 보고 왔는지 꼭 가보고 싶다고 하네요...' || CHR(13) || CHR(10) || ' 에휴.. 시국이 시국인지라 고민이 많습니다ㅠㅠ' || CHR(13) || CHR(10) || ' 다들 어떻게 하시고 계신지요...' || CHR(13) || CHR(10) || CHR(13) || CHR(10) ||  CHR(13) || CHR(10) , '', '2021-05-02', '자칭공주');
insert into community_tb values(COMMUNITY_TB_C_NO.nextval, '어버이날 선물로 카네이션을 만들어 왔네요', '얼집에서 만들어 왔는데 세상에 이 고사리 같은 손으로' || CHR(13) || CHR(10) || ' 저희를 위해 꼬물꼬물 만들었을 생각을 하니 주책맞게도 눈물이 다 나네요ㅠㅠ' || CHR(13) || CHR(10) || ' 우리 부모님도 이런 마음이셨겠죠?' || CHR(13) || CHR(10) || CHR(13) || CHR(10) || CHR(13) || CHR(10) , '', '2021-05-08', '정민');
insert into community_tb values(COMMUNITY_TB_C_NO.nextval, '울 아들램 엉뚱한거 보세요~ㅋㅋ', '세상에 어버이날 선물로 아빠 비상금을 찾아와서 저한테 주네요~^^' || CHR(13) || CHR(10) || ' 역시 울 아들램 엉뚱똑똑하네요 ㅋㅋㅋ' || CHR(13) || CHR(10) || ' 저도 몰랐는데 이걸 어디서 찾아 온건지~~' || CHR(13) || CHR(10) || ' 아직 울 신랑은 이 사실을 모른다는~~~' || CHR(13) || CHR(10) || CHR(13) || CHR(10) || CHR(13) || CHR(10) , '', '2021-05-08','마곡동불주먹');
insert into community_tb values(COMMUNITY_TB_C_NO.nextval, '장모님 생신선물 뭐가 좋을까요?', '곧 장모님 생신이신데 센스있는 사위가 되려면 어떤게 좋을까요??' || CHR(13) || CHR(10) || ' 다들 추천 하나씩 부탁드려요' || CHR(13) || CHR(10) || CHR(13) || CHR(10) || CHR(13) || CHR(10) , '', '2021-05-22', '으라차차대디');
insert into community_tb values(COMMUNITY_TB_C_NO.nextval, '우리 둘째 오늘 돌잡이에서 마이크 잡았네요', '첫째는 연필 잡아서 둘째는 연필이나 돈 잡길 바랬는데 웬걸 마이크를' || CHR(13) || CHR(10) || ' 아주 소중히 잡더라구요?' || CHR(13) || CHR(10) || ' 저희 아내가 노래를 참 좋아하는데 벌써부터 엄마끼를 닮나봅니다 ㅎㅎ' || CHR(13) || CHR(10) || CHR(13) || CHR(10) || CHR(13) || CHR(10) , '', '2021-06-02', 'huni');
insert into community_tb values(COMMUNITY_TB_C_NO.nextval, '소아비만인 저희 아이 식단관리를 어떻게 해야할까요?', '이번에 병원가보니 소아비만이라' || CHR(13) || CHR(10) || ' 식단 관리와 운동을 꼭 시키라고 하더라구요ㅠㅠ' || CHR(13) || CHR(10) || ' 너무 놀래서 아이가 좋아하던 과자랑 초콜릿은 다 버리고' || CHR(13) || CHR(10) || ' 야채 위주로 주니 먹기싫다고 난리난리.. 에휴.. ' || CHR(13) || CHR(10) || '다 큰 어른도 야채 먹기 싫으니 오죽하겠냐만은..' || CHR(13) || CHR(10) || ' 참 힘드네요ㅠㅠ 선배님들 조언 부탁드려요' || CHR(13) || CHR(10) || CHR(13) || CHR(10) || CHR(13) || CHR(10) , '', '2021-06-14', '정겨운');
insert into community_tb values(COMMUNITY_TB_C_NO.nextval, '용인에 거주하시는 분 계신가요?', '저희 아이 쓰던 장난감 무나할까 하는데~~' || CHR(13) || CHR(10) || ' 계시면 댓글 남겨주세요~' || CHR(13) || CHR(10) || CHR(13) || CHR(10) || CHR(13) || CHR(10) , '', '2021-07-04', 'chan');
insert into community_tb values(COMMUNITY_TB_C_NO.nextval, '가족들이랑 가평으로 여름휴가 다녀왔어요~', '원래 동해로 갈까 했는데 너무 멀기도 하고' || CHR(13) || CHR(10) || ' 아이들이 바다보단 계곡을 더 좋아해서 가평으로 다녀왔네요~' || CHR(13) || CHR(10) || ' 계곡에서 놀다가 뜨끈한 백숙 먹으니 어우~' || CHR(13) || CHR(10) || ' 너무 좋더라구요ㅎㅎ 다들 여름휴가 조심히 다녀오세용' || CHR(13) || CHR(10) || CHR(13) || CHR(10) || CHR(13) || CHR(10) , '', '2021-07-28', '정민');
insert into community_tb values(COMMUNITY_TB_C_NO.nextval, '영유 보낸 분 계신가요?', '저희 아이도 영유 보내고 싶은데 초보맘이라' || CHR(13) || CHR(10) || ' 뭐부터 알아봐야 할지 모르겠네요ㅠㅠ' || CHR(13) || CHR(10) || ' 선배님들 조언 부탁드려요' || CHR(13) || CHR(10) || CHR(13) || CHR(10) || CHR(13) || CHR(10) , '', '2021-08-12', '집이가까운');
insert into community_tb values(COMMUNITY_TB_C_NO.nextval, '아이 아토피가 너무 심해요', '저희 아이가 아토피가 너무 심해서 병원도 다녀보고,' || CHR(13) || CHR(10) || ' 아토피에 좋다는 것도 다 해봤는데 소용이 없네요ㅠㅠㅠ' || CHR(13) || CHR(10) || ' 그나마 건너건너 아시는 분이 하시는 말로는 도시 공기가 안좋아 그런거니' || CHR(13) || CHR(10) || ' 시골로 가보라고 하시더라구요ㅠㅠ 저랑 신랑 둘 다 서울로 직장생활 중인데' || CHR(13) || CHR(10) || ' 너무 고민입니다... 어떻게 해야 좋을까요' || CHR(13) || CHR(10) || CHR(13) || CHR(10) || CHR(13) || CHR(10) , '', '2021-09-13', '대장로');
insert into community_tb values(COMMUNITY_TB_C_NO.nextval, '어쩔티비가 뭔가요?', '오늘 아이와 아이 친구들이 저희 집에 와서 놀았는데 어쩔티비?' || CHR(13) || CHR(10) || ' 이런 말을 계속 쓰더라구요??' || CHR(13) || CHR(10) || ' 왜 계속 티비를 찾는지..?' || CHR(13) || CHR(10) || ' 요즘 유행어인가요~~?' || CHR(13) || CHR(10) || CHR(13) || CHR(10) || CHR(13) || CHR(10) , '','2021-05-11', '자칭공주');
commit;

SELECT * FROM community_tb;
SELECT * FROM member_tb;


--커뮤니티 게시판 댓글
CREATE TABLE community_reply(
community_no number(6) PRIMARY KEY,
community_content varchar2(500) NOT NULL,
community_date TIMESTAMP NOT NULL,
community_nickname varchar2(30) NOT NULL,
CONSTRAINT community_nickname_fk FOREIGN KEY(community_nickname) REFERENCES member_tb(m_nickname),
c_no NUMBER NOT NULL
    CONSTRAINT community_reply_fk REFERENCES community_tb(c_no)
);
select * from community_tb order by c_no;

drop sequence community_reply_community_no;
create sequence community_reply_community_no
 start with 1
    increment by 1
    maxvalue 10000;

insert into community_reply values(community_reply_community_no.nextval, 
'출산휴가 2달은 회사 고용부 합쳐서 백퍼 마지막달은 200만원 한도요~ 육아휴직은 한도 따로 있는데 사후지급금은 떼고 나오구요 자세한 사항은 홈페이지 참고하셔요', 
'2021-02-13 21:22','sunsin','1');
insert into community_reply values(community_reply_community_no.nextval, 
'에휴~ 요즘 코시국이라 어디 다니지도 못하고 많이 답답하죠ㅠㅠ 전 그냥 마스크 잘끼고 동네 산책하는거로 그나마 스트레스 풀고 있어요..', 
'2021-03-08 12:43','자칭공주','3');
insert into community_reply values(community_reply_community_no.nextval, 
'저는 그래서 유투브로 뜨개질 배우고 있어요~ 소소한 취미거리 만들어 보는건 어떨까요?', 
'2021-03-13 15:11','sunsin','3');
insert into community_reply values(community_reply_community_no.nextval, 
'잘하셨어요~ 아무래도 백화점 브랜드보단 종로에서 맞추는게 더 좋긴하죠~', 
'2021-03-21 20:45','chan','4');
insert into community_reply values(community_reply_community_no.nextval, 
'요즘은 원하는 브랜드 반지 디자인으로 맞춰주기도 한다네요~? 다음에 참고해보셔요', 
'2021-03-22 09:21','마곡동불주먹','4');
insert into community_reply values(community_reply_community_no.nextval, 
'아무래도 좋은곳에서 맞추면 오래 쓰지요~~ 가구 디자인이 질리신거면 요즘 리폼?해주는 곳 있던데 함 알아보세요', 
'2021-03-16 21:34','정민','5');
insert into community_reply values(community_reply_community_no.nextval, 
'윗분 말처럼 리폼업체 알아보시거나, 가구배치 새로 하시면서 집안에 작은 소품들 바꿔줘보세요~ 그거만으로 새로운 느낌이 나더라구요~~ㅎㅎ', 
'2021-03-21 13:11','huni','5');
insert into community_reply values(community_reply_community_no.nextval, 
'책 좋지요~ 사시는곳 근처 도서관 이용하시거나~ 지자체에서 지원하는 프로그램 있는지 확인해보세요~ 은근 이런저런 프로그램 많아서 좋더라구요', 
'2021-03-19 09:12','자칭공주','6');
insert into community_reply values(community_reply_community_no.nextval, 
'요즘 여기저기서 많이 나오더라고요ㅠㅠ 우리 아프지말아요님은 괜찮으신가요??', 
'2021-03-29 12:32','팀장킬러','9');
insert into community_reply values(community_reply_community_no.nextval, 
'팀장킬러님 감사해요~ 저는 아직 증상은 없는데 결과가 안나와서 좀 떨리긴 하네요ㅠㅠ 코로나 조심하세요', 
'2021-03-29 16:11','아프지말아요','9');
insert into community_reply values(community_reply_community_no.nextval, 
'작동쪽에 오리고기 가게 많은데 거기 다 괜찮아요~', 
'2021-04-02','대장로','10');
insert into community_reply values(community_reply_community_no.nextval, 
'그러네요~ 미세먼지가 많긴 하지만, 활짝 핀 꽃을 보니 기분이 좋네요ㅎㅎ', 
'2021-04-03 17:21','정겨운','11');
insert into community_reply values(community_reply_community_no.nextval, 
'정말요~ 거리에 가득 핀 꽃들이랑 살랑살랑 불어오는 바람 덕분에 기분이 너무 좋아요~', 
'2021-04-03 18:11','정민','11');
insert into community_reply values(community_reply_community_no.nextval, 
'아무래도 가방이 좋죠^^ 백화점 다녀오시는거 추천할게요~', 
'2021-04-17 11:12','chan','15');
insert into community_reply values(community_reply_community_no.nextval, 
'chan님 말처럼 가방도 좋은데, 예쁜 디자인 고를 자신 없으면 현금도 나쁘지 않죻ㅎㅎㅎ', 
'2021-04-17 12:21','자칭공주','15');
insert into community_reply values(community_reply_community_no.nextval, 
'빵종류 좋아하시면 언덕다방 추천해요~ 용산역에서 좀 더 가면 있어요~', 
'2021-05-01 17:21','정겨운','18');
commit;
select * from community_reply;

CREATE TABLE review(
r_no NUMBER(6) PRIMARY KEY,
r_subject VARCHAR2(100) NOT NULL,
r_content VARCHAR2(1000) NOT NULL,
r_image VARCHAR2(30) NULL,
r_date date NOT NULL,
r_score VARCHAR2(100) NOT NULL,
p_no number NOT NULL
    CONSTRAINT review_fk REFERENCES product(p_no),
r_nickname varchar2(30) NOT NULL,
CONSTRAINT review_nickname_fk FOREIGN KEY(r_nickname) REFERENCES member_tb(m_nickname)
);

create sequence review_r_no
    start with 1
    increment by 1
    maxvalue 10000;

insert into review values
(REVIEW_r_no.nextval, '강아지 인형 후기요','마침 저희 아이가 강아지 인형을 찾고 있었는데, 좋은 제품이 있어서 거래했네요~ 받아보니 정말 새제품이라 아이도 좋아하고 저도 뿌듯합니니다 ㅎㅎ ','','2022-03-11','★★★★★','23','chan' );
insert into review values
(REVIEW_r_no.nextval, '기저귀 발진 크림 좋네요~','저희 아이가 다리 접히는 부분에 빨갛게 발진이 올라와서 걱정이 많았는데, 주변 분들의 추천으로 발진크림을 사서 발라봤더니 일주일만에 좀 가라앉았네요~', '','2022-03-12','★★★★★','1','자칭공주');
insert into review values
(REVIEW_r_no.nextval, '저희도 암쉐이 시작했어요~','몰랐는데 암쉐이 알음알음 유명하더라고요? 좋다는건 다 써보는 타입이라 찾아보다가 여기에 판매하시는 분 있길래 바로 연락드려서 가져왔어요 ㅎㅎ! 샴푸는 개봉 상품이라 로션만 구매했는데, 구매자님도 친절하시고 제품도 좋아서 기분이 좋네요~ 다들 암쉐이 하세요~~', '','2022-03-13','★★★★★','9','정민');
insert into review values
(REVIEW_r_no.nextval, '뽀대와방나는 버버리 get했네요','요즘 부모님들 애기 옷을 너무 예쁘게 잘 입히더라고요? 우리 부부 패션 센스는 지못미라...ㅜ 센터에서 혼자 칙칙한 울 아이 때문에 많이 미안했는데 좋은 상품 발견해서 우리아이 뽀대 작살나게 해줬네요~ ', '','2022-03-13','★★★★★','11','집이가까운');
insert into review values
(REVIEW_r_no.nextval, '우리집 세균 박살낼게요','요즘 시국이 시국인지라 다들 방역 예민하잖아요ㅠㅠ 근데 아이 키우는 집이라 자극 쎈 소독 용품 사용하기는 힘들고~~ 그래서 저자극 아이용 소독티슈 사서 아이 장난감들 쓱싹쓱싹 열심히 닦아주고 있네요ㅎㅎ 제품은 괜찮은거 같아요~~~ 다들 코시국 소독용품 장만하세요^^', '','2022-03-13','★★★★★','7','아프지말아요');
insert into review values
(REVIEW_r_no.nextval, '꽃샘추위 대비용으로 후리스 장만했어요','이제 봄날씨 될 줄 알고 두꺼운 옷들은 정리해서 넣어놨더니 웬걸 왜 다시 추워지죠...^^? 아놔.. 이미 다 넣은거 다시 꺼내기 힘들어서 한철 입을 생각으로 후리스 장만했네요~ 보온력은 괜찮은거 같은데.. 소매 부분에 얼룩이 있어서 별은 하나 뺏어용^^', '','2022-03-14','★★★★','16','팀장킬러');
insert into review values
(REVIEW_r_no.nextval, '우리집 꼬마신사님한테 정장 맞춰줬네요~~','저희 아이가 절 닮아서 이목구비가 올망올망한게 너~~무 예쁘거든요~~ 남자아이인데도 다들 여자아이로 보실만큼~~ㅎ 아무튼 그런 멋진 우리 왕자님 다음달에 화동 부탁받아서 의상을 어찌할지 하다가 한번만 입고 말려고 여기서 정장 구입했네요~~ 사이즈 걱정했는데 다행히 딱! 맞지만 상태가 조~~금...^^ 그래도 우리 왕자님 얼굴로 다 소화하시네요~~ㅎㅎㅎ ', '','2022-03-14','★★★★','18','마곡동불주먹');
insert into review values
(REVIEW_r_no.nextval, '울집 꼬마 양님','우리집 꼬마 공주님~ 세상에 어디서 뭘 봤는지 전에는 엘사 공주님 옷으로 풀 착장 하시더니, 이번엔 꼬마 양이 되겠다고 난리난리~ 옷은 다 사줬는데 소품이 없다며 엉엉 울길래 뽀글이 가방 발견하자 마자 후딱 거래했답니다~ 다행히 우리 공주님도 마음에 들어 하는듯하네요~~? ', '','2022-03-15','★★★★★','19','sunsin');
insert into review values
(REVIEW_r_no.nextval, '우리집도 이제 벤츠가 두대?!','이제 우리집도 벤츠가 2대네요~~ 하나는 제차, 하나는 울 아이차~ 세상에 절 닮았는지 차에 관심이 많더라고요?? 동네 공원에서 원없이 달려보라고 울 공주도 벤츠 하나 뽑아줬어요^^ㅋ 저소음이라지만 어느정도 소음은 있는데 괜찮네용~~', '','2022-03-15','★★★★★','21','자칭공주');
insert into review values
(REVIEW_r_no.nextval, '부수는 놈 따로 사는놈 따로네요 ㅎㅎ','우리집 웬수 남편님이^^ 저번주에 술먹고 새벽에 들어와서 아이 미끄럼틀을 굳이굳이~~~ 타야겠다고 난리난리 치더니 결국 그 좁은 틈에 낑겨서 한바탕 난리 치다가 다 부수고 아이는 울고불고~~ 이 웬수놈 등짝 오백대 때리고 새 미끄럼틀 또 구매하기엔 부담스러워서 여기서 중고로 구매했네요~~ 오늘도 지가 가서 받아오라니까 바쁘다고 해서 제가 직접 가지러 나가고ㅎㅎㅎ 미끄럼틀은 상태 괜찮네요~ 아이도 좋아해서 그나마 화가 좀 풀립니다~ 좋은 물건 땡큐요~ ', '','2022-03-12','★★★★★','24','대장로');
insert into review values
(REVIEW_r_no.nextval, '댇신사임당이 되보렵니다~','요즘 글로~~벌 시대잖아요? 우리 아이 얼른 한글떼고 외국어 공부 시키려고 제가 여기저기 알아보다가 이 한글쓰기 책 괜찮아 보여 장만했습니다~~ 이거로 난 떡을 썰테니~ 넌 글씨를 쓰거라~~~ 하며 공부 시키려구요ㅎㅎ 우리 아들 화이팅', '','2022-03-17','★★★★★','25','정민');
insert into review values
(REVIEW_r_no.nextval, '새제품이라 했는데 살짝 까임 있네요ㅠ','유모차가 좀 비싸기도 해서 중고로 구매했던게 잘못일까요ㅠㅠ? 새제품이라 했는데 왜 손잡이 부분에 살짝 까진게 보이는지..ㅠ 작은 기스라 그냥 중고니까 그러려니 하고 넘어가긴 했는데 좀 속상하긴 하네요..ㅠ', '','2022-03-20','★★★','40','chan');
insert into review values
(REVIEW_r_no.nextval, '붕붕카 후기요','포크레인 붕붕카를 최근에 구매했는데 아이도 너무 좋아하고 판매자분 집앞으로 간다고 하니까 만원 깍아주시고 넘 좋네요~~^^ 좋은 물건 싸게 잘 샀습니다~~ 감사용 울 판매자님 복 많이~♥', '','2022-03-22','★★★★★','37','아프지말아요');
insert into review values
(REVIEW_r_no.nextval, '유모차가 너무 좋네요~','사실~ 유모차가 한두푼 하는거 아니잖아요~~ 그래서 고민고민 하다가 여기서 구매했는데 웬걸~ 걱정했던거랑 다르게 너무 좋아요~ㅎㅎ 그리고 아이랑 마주보게 되어 있어서 조금 더 정서적 교감이 생기는 기분~~~이랄까요~ㅎㅎ 좋은 상품 잘 구매했네요~', '','2022-03-24','★★★★★','32','대장로');
insert into review values
(REVIEW_r_no.nextval, '게시글 잘 보고 살걸 그랬네요ㅠㅠㅠ','>놀이텐트< 이 단어만 보고 나머지 글을 제대로 안 본 상태로 택배거래로 구매하고 지금 열어보니 골판지로 된 놀이텐트네요..ㅎ 아놔~~ 소재가 골판지라 조~~~~~금 당황스럽지만, 제 실수니 어쩔 수 없지요ㅠㅠ 다른 분들도 거래하실 때 꼼꼼하게 글 확인 후 거래하세요', '','2022-03-24','★★★★★','30','팀장킬러');
commit;
select * from review;

--리뷰 댓글
DROP TABLE review_reply;
CREATE TABLE review_reply(
review_reply_no number(6) PRIMARY KEY,
review_reply_content varchar2(500) NOT NULL,
review_reply_date TIMESTAMP NOT NULL,
review_reply_nickname varchar2(30) NOT NULL,
CONSTRAINT review_reply_nickname_fk FOREIGN KEY(review_reply_nickname) REFERENCES member_tb(m_nickname),
r_no NUMBER NOT NULL
    CONSTRAINT review_reply_fk REFERENCES review(r_no)
);
create sequence review_reply_review_reply_no
 start with 1
    increment by 1
    maxvalue 10000;

insert into review_reply values(review_reply_review_reply_no.nextval, 
'역시 아이들은 인형을 좋아하나 보네요~~', '2022-03-11 10:28','자칭공주','1');
insert into review_reply values(review_reply_review_reply_no.nextval, 
'헐~~ 우리 아이도 발진 생겼는데 함 써봐야겠네요~', '2022-03-12 20:12','정민','2');
insert into review_reply values(review_reply_review_reply_no.nextval, 
'발진에 알로에도 좋은데~~ 써보셨나요?', '2022-03-14 22:35','huni','2');
insert into review_reply values(review_reply_review_reply_no.nextval, 
'wow~~버버리~ 그 동네 꼬마들 중 제일 패쇼니스타겠어요~~~ ', '2022-03-11 11:23','자칭공주','4');
insert into review_reply values(review_reply_review_reply_no.nextval, 
'으악~~ 옷 다 정리했는데 다시 꺼내기 와방 귀찮죠 ㅡㅡ 구매 잘하셨네용', '2022-03-15 21:39','대장로','6');
insert into review_reply values(review_reply_review_reply_no.nextval, 
'푸항~~ 아이들은 다 그런가봐요~ 울 아들은 스파이더맨으로 풀착장~~ 미쳐버려~', '2022-03-21 17:14','팀장킬러','8');
insert into review_reply values(review_reply_review_reply_no.nextval, 
'다들 그런거 아니겠어요~~^^ 남이 보면 막입힌줄 알텐데 사실 울 딸랑구가 다 선택한..^^', '2022-03-22 14:28','sunsin','8');
insert into review_reply values(review_reply_review_reply_no.nextval, 
'아이 정서에도 인형이 참 좋다고 하더라구요~~? 우리 아이도 하나 사줘야겠어요~', '2022-03-23 19:20','정겨운','1');
insert into review_reply values(review_reply_review_reply_no.nextval, 
'코시국에 소독은 필수죠~~ 아이가 건강하겠어요~ 화이팅~', '2022-03-13','huni','5');
insert into review_reply values(review_reply_review_reply_no.nextval, 
'하이고야~ 술먹고 거길 왜 들어가셨는지~~~~ 고생 많으셨네요~ 오늘 맛있는거 드시고 기분 푸시길 바래용~', '2022-03-13','아프지말아요','10');
insert into review_reply values(review_reply_review_reply_no.nextval, 
'오마나~~ 댓글 고마워요~ 안그래도 주말에 남편카드로 외식하기로 했네요~~^^ㅎ 아프지말아요님 좋은하루되세요~~', '2022-03-13','대장로','10');
insert into review_reply values(review_reply_review_reply_no.nextval, 
'서칭하다가 들어왔는데 댓글이 훈~훈~~하네요~~ 여기만 벌써 봄인줄?? 우리 희희 회원님들 참 다정하셔~~', '2022-03-14','마곡동불주먹','10');
insert into review_reply values(review_reply_review_reply_no.nextval, 
'허거덩 ㅡㅡ; 가끔 그럴때 있지요~~ 그래도 이왕 구매한거 아이가 잘쓰길 바래요~', '2022-03-25','자칭공주','15');
insert into review_reply values(review_reply_review_reply_no.nextval, 
'오마나~ 암쉐이가 유명한가요?? 몰랐네요~ 함 찾아봐야겠어요~~', '2022-03-15','대장로','3');
commit;
select * from review_reply;


--cs_service 테이블

DROP TABLE customer_service;
CREATE TABLE customer_service(
cs_no NUMBER PRIMARY KEY,
cs_subject VARCHAR2(100 CHAR) NOT NULL,
cs_content VARCHAR2(1000 CHAR) NOT NULL,
cs_image VARCHAR2(30 CHAR),
cs_date TIMESTAMP NOT NULL,
m_nickname VARCHAR2(30) NOT NULL,
   CONSTRAINT customer_service_fk FOREIGN KEY(m_nickname) REFERENCES member_tb(m_nickname)
);
select * from customer_service;

DROP SEQUENCE customer_service_seq;
CREATE SEQUENCE customer_service_seq;

INSERT INTO customer_service (cs_no, cs_subject, cs_content, cs_image, cs_date, m_nickname) 
    VALUES(customer_service_seq.NEXTVAL, '면봉류 보관에 대해서 특별히 주의할 사항이 있나요?', '아이들 면봉류를 구매하였는데 보관은 어떻게 하는지 궁금합니다.', 'image01.jpg', '2022-03-06 10:31:51', '정민');
INSERT INTO customer_service (cs_no, cs_subject, cs_content, cs_image, cs_date, m_nickname) 
    VALUES(customer_service_seq.NEXTVAL, '육아 용품 세정하는 방법이 따로 있나요?', '육아용품을 세정하려는데 어떻게 해야하는지 궁금합니다.', '1', '2022-03-05 15:02:33', 'sunsin');
INSERT INTO customer_service (cs_no, cs_subject, cs_content, cs_image, cs_date, m_nickname) 
    VALUES(customer_service_seq.NEXTVAL, '아이들 손톱깍기는 언제부터 하나요?', '이제 곧 분만 준비중인 예비맘 입니다. 아기 손톱깍기 사용은 언제부터 사용하는게 맞나요???', '1', '2022-03-05 11:31:25', '자칭공주');
INSERT INTO customer_service (cs_no, cs_subject, cs_content, cs_image, cs_date, m_nickname) 
    VALUES(customer_service_seq.NEXTVAL, '노리개젖꼭지는 중고용품으로 사도 될까요?', '아이들 위생관련해서 노리개젖꼭지에 대하여 중고품을 사용해도 되는지 의구심이 듭니다. 사용 가능하다면 사용 방법도 같이 알려주시면 감사하겠습니다.', '1', '2022-03-03 09:22:51', '집이가까운');
INSERT INTO customer_service (cs_no, cs_subject, cs_content, cs_image, cs_date, m_nickname) 
    VALUES(customer_service_seq.NEXTVAL, '아기용 세제/유연제는 아기가 몇살이 될 때까지 사용하나요?', '아이 옷만 따로 빨고 있는데, 언제까지 아이들 옷에 유연제를 뿌려야하는 걸까요??', '1', '2022-03-01 14:45:02', '팀장킬러');
commit;

SELECT * FROM customer_service;

-- 답변CS_comment 테이블 

DROP TABLE cs_comment;
CREATE TABLE cs_comment(
col5 NUMBER(6) PRIMARY KEY,
col VARCHAR2(100 CHAR) NOT NULL,
col2 VARCHAR2(1000 CHAR) NOT NULL,
col3 DATE NOT NULL,
mg_id VARCHAR(30) NOT NULL,
cs_no NUMBER NOT NULL,
    CONSTRAINT cs_comment_fk1 FOREIGN KEY(mg_id) REFERENCES manager_tb(mg_id),
    CONSTRAINT cs_comment_fk2 FOREIGN KEY(cs_no) REFERENCES customer_service(cs_no)
);

DROP SEQUENCE cs_comment_seq;
CREATE SEQUENCE cs_comment_seq;

INSERT INTO cs_comment (col5, col, col2, col3, mg_id, cs_no) VALUES (cs_comment_seq.NEXTVAL, '육아용 면봉류 보관 사항입니다.', '면봉류 보관은 밀봉하여 상온에서 보관하시면 됩니다.', '2022-03-06', 'heehee01', 1);
INSERT INTO cs_comment (col5, col, col2, col3, mg_id, cs_no) VALUES (cs_comment_seq.NEXTVAL, '육아 용품 세정하는 방법입니다.', '뜨거운 물에 소독하여 사용하는 방법 이외에도 많은 방법들이 있습니다.', '2022-03-05', 'heehee01', 2);
INSERT INTO cs_comment (col5, col, col2, col3, mg_id, cs_no) VALUES (cs_comment_seq.NEXTVAL, '아이들 손톱깍기관련 내용입니다.', '아이용 손톱깍기는 따로 판매하고 있습니다. 10개월 이후 사용하시면 됩니다.', '2022-03-05', 'heehee02', 3);
INSERT INTO cs_comment (col5, col, col2, col3, mg_id, cs_no) VALUES (cs_comment_seq.NEXTVAL, '노리개젖꼭지 관련 내용입니다.', '노리개젖꼭지는 항시 소독 후 사용하시기 바랍니다. 중고품을 따로 사용해도 상관없습니다.', '2022-03-03', 'heehee02', 4);
INSERT INTO cs_comment (col5, col, col2, col3, mg_id, cs_no) VALUES (cs_comment_seq.NEXTVAL, '아기용 세제/유연제 사용 연령', '아기용 세제 및 유연제는 4살 이후 바꿔주시면 될 것 같습니다.', '2022-03-01', 'heehee01', 5);
commit;

SELECT * FROM cs_comment;

-- faq 게시판 테이블
DROP TABLE faq_notice CASCADE CONSTRAINTS;
CREATE TABLE faq_notice(
    faq_no NUMBER(6) NOT NULL,
    faq_title VARCHAR2 (100 CHAR) PRIMARY KEY,
    faq_image VARCHAR2 (30 CHAR),
    faq_content VARCHAR2(1000 CHAR) NOT NULL,
    mg_id VARCHAR2(30), CONSTRAINT faq_notice_fk FOREIGN KEY(mg_id) REFERENCES manager_tb(mg_id)
 );
 
 DROP SEQUENCE faq_seq;
 CREATE SEQUENCE faq_seq;
 
 -- faq no 생성하기 위한 시퀀스
 CREATE SEQUENCE faq_number START WITH 1 INCREMENT BY 1 MAXVALUE 9999 NOCYCLE NOCACHE;
 DROP SEQUENCE faq_number;
 
 SELECT * FROM faq_notice;
 
 INSERT INTO faq_notice (faq_no, faq_title, faq_image, faq_content, mg_id) 
 VALUES (FAQ_NUMBER.nextval, '커뮤니티 가이드라인', 'heehee.jpg', 
 '희희는 부모들의 따뜻하고 활발한 교류를 만들기 위해 노력하고 있어요. <p><br></p>아이를 향한 당신의 관심과 애정으로 우리 사이트를 사용하는 이웃 모두가 가이드라인을 지키며 커뮤니티를 따뜻하게 함께 만들어요. <p><br></p>혼자 힘으로는 할 수 없지만, 우리가 함께라면 할 수 있어요! <p><br></p>우리는 신뢰와, 존중을 서비스의 중요한 가치라고 생각해요. 우리는 이 가치를 지키기 위해 언제나 최선을 다할 거예요. 더 가깝고 따뜻한 커뮤니티를 위해 동참해주세요. <p><br></p><strong>신뢰</strong><p><br></p>모든 거래는 신뢰할 수 있어야해요. 우리는 이웃과의 만남이 따뜻함이 될 수 있도록 노력해요.<p><br></p><strong>존중</strong> <p><br></p>모든 사용자는 한 아이의 부모라는 걸 기억해주세요. 우리는 다양성을 존중하고, 배려하기로 약속해요. 희희는 누구나 존중받는 따뜻한 커뮤니티를 지향하고 있어요. 우리 서로 존중해요. <p><br></p>따뜻한 커뮤니티를 만들기 위해서는 우리 모두의 관심과 노력이 필요해요. <p><br></p>부적절한 게시글이나 사용자를 발견하면 적극적으로 신고해주세요. <p><br></p>우리가 함께 지켜나갈 신뢰와 존중이라는 가치를 누구나 볼 수 있도록 이 페이지도 계속해서 다듬고 업데이트할 거예요. 우리 함께 소중한 가치들을 지켜내요.', 'heehee02');

 INSERT INTO faq_notice (faq_no, faq_title, faq_image, faq_content, mg_id) 
 VALUES  (FAQ_NUMBER.nextval, '중고거래 운영정책', 'heehee.jpg', 
 '<Strong>가까운 이웃과의 만남이 따뜻함이 될 수 있도록 거래매너를 지켜요.</strong> <p><br></p>구매자, 판매자가 함께 노력하여 신뢰를 만들어요. <p><br></p>ㆍ구매할 때 미리 신중하게 고민한 뒤 판매자와 거래 약속을 잡아요. <p><br></p>ㆍ지나치게 가격을 깎지 않아요. 판매자의 가격 책정을 존중해요<p><br></p>ㆍ나눔을 받으면 따뜻한 감사인사로 마무리해요. <p><br><br></p>ㆍ판매할 때 직접 촬영한 사진으로 판매글을 작성해요.<p><br></p>ㆍ물품에 대한 설명을 자세히 써요. 특히, 주요 하자에 대한 내용은 꼭 명시하기로 해요. <p><br></p>ㆍ예약한 이웃이 있다면 그 분에게 판매해요. 예약 파기는 서로의 신뢰를 무너뜨리는 행동이에요.<p><br></p>ㆍ직거래할 때 누구나 찾기 쉽고 안전한 공공장소에서 만나요. 밝고 사람이 많은 장소에서 거래해요. <p><br></p>ㆍ판매중인 물건도 여전히 판매자의 소중한 물품이에요. 구매자는 판매자를 배려하며 물건 상태를 조심히 확인해요.<p><br></p>ㆍ높은 가격의 물품은 판매자, 구매자 모두 더 신중하게 거래해요. <p><br></p>ㆍ지금 이 거래가 상대방의 첫 중고거래일 수도 있어요. 중고거래에 익숙하지 않더라도 배려하며 거래해요. 첫 기억을 따뜻하게 만들어주세요. <p><br></p>ㆍ온라인으로 연결되어 만났지만, 동네 어디서나 마주칠 수 있는 이웃이라는 사실을 기억해요', 'heehee02');

 INSERT INTO faq_notice (faq_no, faq_title, faq_image, faq_content, mg_id) 
 VALUES  (FAQ_NUMBER.nextval, '지켜야 할 매너','heehee.jpg', 
 '여러분의 작은 친절이 이웃에게 큰 감동을 줄 수 있어요. 모든 사용자가 우리 아이의 친구 부모님이라는 점을 기억해 주세요. <p><br></p><strong>기본 매너</strong><p><br></p>기본적으로 지켜야 하는 매너에는 무엇이 있을까요? 기본 매너인 만큼 꼭 지켜 주실 거라고 믿어요 <p><br></p>ㆍ서로 존중해요. 우리 존댓말로 대화해요.<p><br></p>ㆍ모두의 시간은 소중합니다. 시간 약속을 꼭 지켜주세요. <p><br></p>ㆍ절대로 중간에 연락 끊기는 일이 없도록 해요. (잠수는 절대 안 돼요!)<p><br></p>ㆍ늦은 시간 연락은 부담스러울 수 있어요. 특히 새벽 시간에는 연락을 자제해 주세요. <p><br></p><strong>구매자 매너</strong><p><br></p>이웃의 상품을 구매하는 구매자로서 따뜻함을 나눠주세요:)<p><br></p>ㆍ신중하게 고민한 뒤 판매자와 확실하게 거래 약속을 잡아요.<p><br></p>ㆍ질문하기 전에 판매글을 꼼꼼히 읽어 주세요.<p><br></p>ㆍ지나치게 가격을 깎지 말아 주세요. 가격제안 할 수 있는 경우에만 가격제안 해주세요. (정말 구매할 마음이 있을 때만 해주실 거죠?)<p><br></p><strong>판매자 매너</strong><p><br></p>개인 간의 거래인 만큼 신뢰할 수 있는 매너를 보여주세요:) <p><br></p>ㆍ직접 촬영한 사진으로 판매글을 작성해 주세요.<p><br></p>ㆍ물품에 대한 설명을 자세하게 써주세요. 특히, 주요 하자에 대한 내용은 꼭 명시해 주세요. (명시되지 않은 하자는 환불 사유가 돼요.)<p><br></p>ㆍ예약한 분이 있다면 그분에게 판매해 주세요.', 'heehee02');
 
 
 INSERT INTO faq_notice (faq_no, faq_title, faq_image, faq_content, mg_id) 
 VALUES  (FAQ_NUMBER.nextval, '커뮤니티 매너','heehee.jpg', 
 '<strong>따뜻한 동네생활 커뮤니티를 만들기 위한 약속을 지켜주세요.</strong> <p><br></p>ㆍ항상 솔직하게 대화해요. <p><br></p>ㆍ서로 배려하며, 약속을 잘 지켜요. <p><br></p>ㆍ이웃들이 잘 이해할 수 있도록 자세하고 정확한 정보를 제공해요. <p><br></p>ㆍ게시판은 우리 이웃을 위한 공간이에요. 다양한 글을 작성해 주세요. <p><br></p>ㆍ광고/홍보 등의 게시글은 금지해요. <p><br></p>ㆍ거래는 제품 게시판을 이용해 주세요.<p><br></p>• 친분을 과시하지 않기로 해요. 혹시라도 다른 이웃들이 소외되지 않도록 도와주세요.<p><br></p>ㆍ남녀노소 다양한 이웃이 함께하는 공간이에요. 누군가에게 불편할 수 있는 글들은 올리지 않기로 해요.', 'heehee02');
 
 INSERT INTO faq_notice (faq_no, faq_title, faq_image, faq_content, mg_id) 
 VALUES  (FAQ_NUMBER.nextval, '판매 금지 물품','heehee.jpg', 
 'ㆍ가품∙이미테이션 (상표권, 저작권 침해 물품, 특정 브랜드의 스타일을 모방한 물품) <p><br></p>ㆍ주류(무알콜 주류 포함), 담배, 전자담배, 모의총포 및 그 부속품 일체 <p><br></p>ㆍ유류 거래<p><br></p>ㆍ생명이 있는 동물·곤충 <p><br></p>ㆍ한약∙의약품∙의료기기∙마약류 <p><br></p>ㆍ도수 있는 안경∙선글라스, 콘택트 렌즈, 써클 렌즈 <p><br></p>ㆍ핸드메이드 제품 <p><br></p>ㆍ화장품 샘플 <p><br></p>ㆍ헌혈증<p><br></p>ㆍ안전확인∙안전인증표시 없는 전기용품 및 단전지 또는 공산품<p><br></p>ㆍUSD 1000달러 이상의 외환 거래나 매매차익을 목적으로 하는 반복적인 외환 거래 <p><br></p>ㆍ나라미, 정부 지원 생필품, 지역상품권, 문화누리카드 등 법률에 의해 재판매 할 수 없는 물품<p><br></p>ㆍ통신사 데이터, 인터넷 상품<p><br></p>ㆍ반입한 날로부터 1년 이상 경과하지 않은 전파인증이 면제된 해외직구 전자제품을 판매하는 행위 <p><br></p>ㆍ암표매매 행위<p><br></p>ㆍ100만 원 이상 금 제품 (골드바, 금괴, 금으로 제작된 목걸이, 팔찌, 귀걸이 등)<p><br></p>ㆍ이외 법률을 위반하는 모든 물품 <p><br><br></p>*판매 금지 물품은 <strong>나눔, 교환, 삽니다 게시글 또한 허용되지 않습니다.</strong><p><br></p>* 판매 자격을 갖췄더라도 개인 간 거래를 지향하는 희희에서는 해당 물품을 판매할 수 없습니다.<p><br></p>*현행법을 위반할 경우 처벌 받을 수 있습니다. 이점 유의해 주시길 바랍니다.<p><br><br></p>다함께 공정하고 따뜻한 거래 문화를 만들어요. :)', 'heehee02');
 
 INSERT INTO faq_notice (faq_no, faq_title, faq_image, faq_content, mg_id) 
 VALUES  (FAQ_NUMBER.nextval, '게시판 거래 및 환불 정책 - 판매자','heehee.jpg', 
 '희희의 모든 거래는 기본적으로 거래 당사자들끼리 자유롭게 진행할 수 있어요. 가급적 희희는 거래에 개입하지 않아요.<p><br></p>그래서 거래 중에 이견이 있어도 <strong>거래 당사자들끼리 직접 대화하여 해결하는 것을 권장해요.</strong><p><br></p>거래 중에 분쟁이 발생하더라도 기본매너를 지키는 건 잊지 말아 주세요. 휴대폰 너머 나와 똑같은 사람이 있다는 것을 기억해 주세요. <p><br></p><strong>• 판매자에게는 이런 권리가 있어요.</strong><p><br></p>판매자는 소중한 아이의 부모이자 여러분의 이웃이에요. 우리는 모두 판매자이면서 구매자예요.<p><br></p>구매자의 <u>무리한 요구는 거절할 권리</u>가 있어요. 매너없는 이야기에 답하지 않을 권리도 있어요.<p><br></p>환불 관련 문제가 생겼을 때, 판매자의 실수나 잘못이 없는 상황이라면 거부할 권리가 있습니다.<p><br><br></p><strong>• 판매자에게는 이런 의무도 있어요.</strong><p><br></p>우리는 모두 당근마켓의 주민이에요. 당근마켓의 거래매너를 지켜주세요.<p><br></p>판매를 원활히 하기 위해서는 자세한 설명과 사진을 첨부해주세요. 신경 쓰이는 부분이 있다면 꼭 사진을 찍거나 설명에 적어주세요.<p><br></p>거래 약속 후 취소하는 것을 지양해주세요. 내 시간이 소중한 만큼, 상대방의 시간도 소중히 여겨주세요.<p><br></p>판매자의 잘못이 명백한 상황에서는 환불을 해주셔야 해요.', 'heehee02');
 
 INSERT INTO faq_notice (faq_no, faq_title, faq_image, faq_content, mg_id) 
 VALUES  (FAQ_NUMBER.nextval, '게시판 거래 및 환불 정책 - 구매자','heehee.jpg', 
 '희희의 모든 거래는 기본적으로 거래 당사자들끼리 자유롭게 진행할 수 있어요. 가급적 희희는 거래에 개입하지 않아요.<p><br></p>그래서 거래 중에 이견이 있어도 <strong>거래 당사자들끼리 직접 대화하여 해결하는 것을 권장해요.</strong><p><br></p>거래 중에 분쟁이 발생하더라도 기본매너를 지키는 건 잊지 말아 주세요. 휴대폰 너머 나와 똑같은 사람이 있다는 것을 기억해 주세요. <p><br></p><strong>• 구매자에게는 이런 권리가 있어요.</strong><p><br></p>구매자 또한, 당근마켓의 소중한 주민이자 여러분의 이웃이에요.<p><br></p>판매자의 <u>무리한 요구는 거절할 권리</u>가 있어요. 매너없는 이야기에 답하지 않을 권리도 있어요.<p><br></p>무리한 요구나 비매너 행위를 지속한다면 고객센터를 통해서 알려주세요.<p><br></p>거래 관련 문제가 생겼을 때, 판매자의 의무에 명시된 상황에 해당하면 환불을 요구할 권리가 있어요.<p><br></p><strong>• 구매자에게는 이런 의무도 있어요.</strong><p><br></p>거래매너를 지켜주세요. 채팅을 시작할 때는 가벼운 인사를 해보는 건 어떨까요?<p><br></p>구매를 원활히 하기 위해서 <u>구매 의사가 명확한 경우에만</u> 약속을 잡아주세요. 거래 약속 후 취소하는 것은 판매자와 구매자 모두에게 손해예요. 내 시간이 소중한 만큼, 상대방의 시간도 소중히 여겨주세요. <p><br></p>거래 관련 문제가 생겼다고 해서 거래와 관련 없는 욕설/비방을 삼가세요. 거래와 관련 없는 욕설/비방 등의 비매너 행위를 할 경우, 제재당할 수 있어요. <p><br></p>물건을 받고 돈을 지불하지 않았다면, 물건의 값은 1일 이내에 지급해주세요. 입금을 미룬다면 거래 불이행 사유로 이용 제한될 수 있습니다.', 'heehee02');

 INSERT INTO faq_notice (faq_no, faq_title, faq_image, faq_content, mg_id) 
 VALUES  (FAQ_NUMBER.nextval, '커뮤니티 운영정책', 'heehee.jpg', 
 '<h3>따뜻한 동네생활 커뮤니티를 만들기 위한 약속을 지켜주세요.</h3><p><br></p>ㆍ게시판은 우리 이웃을 위한 공간이에요.<p><br></p>ㆍ닉네임 등으로 간접적으로 홍보하는 게시글은 올릴 수 없어요.<p><br></p>ㆍ홍보성 이벤트도 게시판에 올릴 수 없어요.<p><br></p>ㆍ중고거래는 상품 게시판을 이용해 주세요.<p><br></p>ㆍ중고거래 금지 품목은 동네생활에도 올릴 수 없어요.<p><br></p>ㆍ중고거래 관련 문의 및 신고는 고객센터에 남겨 주세요. 고객센터에 문의를 해주셔야 빠르게 문제를 해결할 수 있어요.<p><br></p>ㆍ생명이 있는 반려동물의 분양/교배 관련 게시글은 올릴 수 없어요.<p><br></p>ㆍ게시글이나 댓글로 친분을 과시하지 말아 주세요. 다른 이웃이 소외되지 않도록 배려해 주세요.<p><br></p>ㆍ게시판은 육아와 관련된 이야기나 관심사를 나누는 공간이에요. 너무 개인적인 이야기를 반복적으로 올리는 것은 지양해주세요.<p><br></p>ㆍ셀카나 타인의 사진 등 초상권 침해 우려가 있는 사진은 올릴 수 없어요.<p><br></p>ㆍ개인정보 게시/교환은 주의해주세요.<p><br></p>ㆍ특정 주제의 도배글이나 내용 없는 글은 안 돼요.<p><br></p>ㆍ청소년도 함께하는 공간이기에 청소년에게 유해할 수 있는 게시글은 지양해주세요. 술, 담배 등 청소년에게 유해가 될 수 있는 요소의 내용은 삭제될 수 있어요. <p><br></p>ㆍ남녀노소 다양한 이웃이 함께하는 공간이에요. 내가 작성하려는 글이 누군가에는 불편한 이야기일 수 있어요.<p><br></p>ㆍ정치, 종교 등 개인적인 신념, 가치관이 드러나 분쟁이나 논란이 될 수 있는 글은 삭제될 수 있어요.<p><br></p>ㆍ단순 후기가 아닌 비방 목적의 글이나 업체를 특정할 수 있는 부정적 후기는 삭제될 수 있어요.<p><br></p>ㆍ불법적인 내용 혹은 불법적인 상업 활동과 연관된 내용은 삭제될 수 있어요.', 'heehee02');
 
 INSERT INTO faq_notice (faq_no, faq_title, faq_image, faq_content, mg_id) 
 VALUES  (FAQ_NUMBER.nextval, '서비스 이용제한', 'heehee.jpg', 
 '<strong>아래의 경우라면 정상적으로 당근마켓을 사용하는 사용자 보호를 위해 사전 안내 없이 서비스 이용이 한시적 또는 영구적으로 제한될 수 있어요.</strong><p><br></p>ㆍ외부 관계법령을 위반한 경우<p><br></p>ㆍ시스템 및 다른 사용자의 정상적인 서비스 이용을 방해하는 활동을 한 경우<p><br><br></p>운영정책은 즐겁고 따뜻한 공간을 만들기 위한 최소한의 장치예요. <p><br><br></p>앞으로도 계속 찾게 되고, 오래 사용하고 싶은 서비스가 되도록 더욱 노력하겠습니다.', 'heehee02');
 
 INSERT INTO faq_notice (faq_no, faq_title, faq_image, faq_content, mg_id) 
 VALUES  (FAQ_NUMBER.nextval, '당신을 위한 노력','heehee.jpg', 
 '<h3>지금 이 순간에도 당신을 위해 노력하고 있어요!</h3><p><br></p><strong>🔦 사기꾼은 실시간으로 제재해요</strong> <p><br></p>관리자는 다양한 사기 사례들을 확인하고 비슷한 수법의 범죄가 재발하지 않도록 모니터링하여 실시간으로 제재하고 있어요.<p><br><br></p> <strong>🔍 게시글을 확인해요</strong><p><br></p>사기꾼의 게시글을 포함 판매 금지 물품이나 전문판매업자들의 판매글, 광고글, 중복게시글 등은 삭제하고 있어요. 신뢰할 수 있는 개인들의 마켓을 만들기 위해 노력하고 있어요.<p><br><br></p><strong>🚔 수사기관과 함께 해요</strong>사기 등 거래 관련하여 문제가 발생하면 빠르게 해결될 수 있도록 관리자도 함께 노력해요. 사기 발생 시에는 신고하는 법을 알려드리고, 신고를 권장하고 있어요. 수사기관에서 공문 등이 도착하면 적극적으로 협조하고 있어요.', 'heehee02');
 
 commit;
 SELECT * FROM faq_notice;
 
 
 SELECT * FROM (SELECT faq_no, faq_title, faq_image, faq_content, mg_id FROM (SELECT * FROM faq_notice ORDER BY faq_no DESC))  WHERE faq_no >= 1 AND faq_no <= 5;
 
 SELECT * FROM faq_notice WHERE faq_no=1;