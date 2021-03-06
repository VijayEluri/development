/* h2 에러회피용 
CREATE TABLE IF NOT EXISTS INFORMATION_SCHEMA.LOB_MAP(
    LOB BIGINT NOT NULL,
    SEQ INT NOT NULL,
    POS BIGINT,
    HASH INT,
    BLOCK BIGINT
);
*/

/* 테스트용 Person */
DROP TABLE IF EXISTS PERSON;
CREATE TABLE PERSON(PERSON_ID VARCHAR(50) NOT NULL PRIMARY KEY,PERSON_NAME VARCHAR(50) NOT NULL);
INSERT INTO PERSON(PERSON_ID, PERSON_NAME) VALUES ('p01','seungbeomi');

/* 데이터베이스 메소드 처리 */
DROP TABLE IF EXISTS COM_MESSAGE;
CREATE TABLE COM_MESSAGE (
       ID             NUMERIC(4,0) NOT NULL PRIMARY KEY,
       CODE           VARCHAR(32)  NOT NULL,
       LANGUAGES      VARCHAR(32),
       CONTRY         VARCHAR(32),
       VARIANT        VARCHAR(32),   
       MESSAGE        VARCHAR(256)
);
INSERT INTO COM_MESSAGE(ID, CODE, LANGUAGES, CONTRY, VARIANT, MESSAGE) VALUES (1, 'info.00001', 'en', 'US' , '', 'hello world!');
INSERT INTO COM_MESSAGE(ID, CODE, LANGUAGES, CONTRY, VARIANT, MESSAGE) VALUES (2, 'info.00001', 'ko', 'KR', '', 'seungbeomi!');
/* //데이터베이스 메소드 처리 */

/* CODELIST*/
DROP TABLE IF EXISTS CODELIST;
CREATE TABLE CODELIST(CODE VARCHAR(100) NOT NULL PRIMARY KEY, NAME VARCHAR(100));
INSERT INTO CODELIST VALUES ('1','DB codelist 1');
INSERT INTO CODELIST VALUES ('2','DB codelist 2');
/* //CODELIST*/

/* SPRING SECURITY */
DROP TABLE IF EXISTS USERS;
CREATE TABLE USERS(
      USERNAME VARCHAR(50) NOT NULL PRIMARY KEY,
      PASSWORD VARCHAR(50) NOT NULL,
      ENABLED BOOLEAN NOT NULL,
      DISPLAYNAME VARCHAR(50) NOT NULL);

DROP TABLE IF EXISTS AUTHORITIES;
CREATE TABLE AUTHORITIES (
      USERNAME VARCHAR(50) NOT NULL,
      AUTHORITY VARCHAR(50) NOT NULL,
      CONSTRAINT FK_AUTHORITIES_USERS FOREIGN KEY(USERNAME) REFERENCES USERS(USERNAME));
      CREATE UNIQUE INDEX IX_AUTH_USERNAME ON AUTHORITIES (USERNAME,AUTHORITY);

DROP TABLE IF EXISTS PERSISTENT_LOGINS;      
CREATE TABLE PERSISTENT_LOGINS (
  USERNAME VARCHAR(64) NOT NULL,
  SERIES VARCHAR(64) PRIMARY KEY,
  TOKEN VARCHAR(64) NOT NULL,
  LAST_USED TIMESTAMP NOT NULL);

INSERT INTO USERS VALUES ('dev', 'dev', true, 'SUPER DEVELOPER'); 
INSERT INTO USERS VALUES ('admin', 'admin', true, 'ADMINISTRATOR'); 
INSERT INTO USERS VALUES  ('user', 'user', true, 'USER'); 

INSERT INTO AUTHORITIES VALUES ('dev', 'ROLE_DEVELOPER'); 
INSERT INTO AUTHORITIES VALUES ('dev', 'ROLE_ADMIN'); 
INSERT INTO AUTHORITIES VALUES ('admin', 'ROLE_ADMIN'); 
INSERT INTO AUTHORITIES VALUES ('user', 'ROLE_USER'); 
/* //SPRING SECURITY */

/* 공통코드 */
DROP TABLE IF EXISTS COM_CL_CODE;
CREATE TABLE COM_CL_CODE
(
    CL_CODE              CHAR(3) NOT NULL PRIMARY KEY
  , CL_CODE_NM           VARCHAR(60) NULL
  , CL_CODE_DC           VARCHAR(200) NULL
  , USE_AT               CHAR(1) NULL
  , CREATEDDATE          DATETIME
  , CREATEDBY_ID         VARCHAR(50)  
  , LASTMODIFIEDDATE     DATETIME
  , LASTMODIFIEDBY_ID    VARCHAR(50)
);

DROP TABLE IF EXISTS COM_CODE;   
CREATE TABLE COM_CODE (
    CODE_ID              VARCHAR(6)   NOT NULL PRIMARY KEY
  , CODE_ID_NM           VARCHAR(60)
  , CODE_ID_DC           VARCHAR(200)
  , USE_AT               CHAR(1)
  , CL_CODE              CHAR(3)
  , CREATEDDATE          DATETIME
  , CREATEDBY_ID         VARCHAR(50)  
  , LASTMODIFIEDDATE     DATETIME
  , LASTMODIFIEDBY_ID    VARCHAR(50)
);

DROP TABLE IF EXISTS COM_CODE_DETAIL;   
CREATE TABLE COM_CODE_DETAIL (
    CODE_ID              VARCHAR(6)         NOT NULL
  , CODE                 VARCHAR(15)        NOT NULL
  , CODE_NM              VARCHAR(60)
  , CODE_DC              VARCHAR(200)
  , SORT                 CHAR(3)
  , USE_AT               CHAR(1)
  , CREATEDDATE          DATETIME
  , CREATEDBY_ID         VARCHAR(50)  
  , LASTMODIFIEDDATE     DATETIME
  , LASTMODIFIEDBY_ID    VARCHAR(50)
  , CONSTRAINT  COM_CODE_DETAIL_FK PRIMARY KEY (CODE_ID, CODE)
);
/* //공통코드 */

/* 메뉴 */
CREATE TABLE COM_MENU_INFO
(
  MENU_NO               NUMERIC(20) NOT NULL,
  MENU_NM               VARCHAR(60) NOT NULL,
  PROGRM_FILE_NM        VARCHAR(60) NOT NULL,
  UPPER_MENU_NO         NUMERIC(20) NULL,
  MENU_ORDR             NUMERIC(5) NOT NULL,
  MENU_DC               VARCHAR(250) NULL,
  RELATE_IMAGE_PATH     VARCHAR(100) NULL,
  RELATE_IMAGE_NM       VARCHAR(60) NULL,
   PRIMARY KEY (MENU_NO),
  FOREIGN KEY COM_MENU_INFO_FK2 (PROGRM_FILE_NM) REFERENCES COM_PROGRM_LIST(PROGRM_FILE_NM)
    ON DELETE CASCADE,
  FOREIGN KEY COM_MENU_INFO_FK1 (UPPER_MENU_NO) REFERENCES COM_MENU_INFO(MENU_NO)
    ON DELETE CASCADE
)
;
CREATE UNIQUE INDEX COM_MENU_INFO_PK ON COM_MENU_INFO ( MENU_NO );

CREATE TABLE COM_PROGRM_LIST
(
  PROGRM_FILE_NM        VARCHAR(60) NULL,
  PROGRM_STRE_PATH      VARCHAR(100) NOT NULL,
  PROGRM_KOREAN_NM      VARCHAR(60) NULL,
  PROGRM_DC             VARCHAR(200) NULL,
  URL                   VARCHAR(100) NOT NULL,
   PRIMARY KEY (PROGRM_FILE_NM)
);
CREATE UNIQUE INDEX COMTNPROGRMLIST_PK ON COMTNPROGRMLIST ( PROGRM_FILE_NM );



/* 파일업로드 */
CREATE TABLE COMMON_FILE
(
    FILE_ID               CHAR(20) PRIMARY KEY,
    CREAT_DT              DATE     NOT NULL ,
    USE_AT                CHAR(1)  NULL
);
CREATE TABLE common_file_detail
(
    FILE_ID       CHARACTER(20) NOT NULL,
    FILE_SN       NUMERIC(10,0) NOT NULL,
    FILE_STRE_COURS   CHARACTER VARYING(2000) NOT NULL,
    STRE_FILE_NM    CHARACTER VARYING(255) NOT NULL,
    ORIGNL_FILE_NM    CHARACTER VARYING(255),
    FILE_EXTSN      CHARACTER VARYING(20) NOT NULL,
    FILE_CN       TEXT,
    FILE_SIZE       NUMERIC(8,0),
    CONSTRAINT COMMON_FILE_DETAIL_FK PRIMARY KEY (FILE_ID)
);
/* //파일업로드 */

