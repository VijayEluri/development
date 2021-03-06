DROP TABLE IF EXISTS EMPLOYMENT;

CREATE TABLE EMPLOYMENT (
  ID BIGINT GENERATED BY DEFAULT AS IDENTITY(START WITH 1),
  KOR_SURNAME VARCHAR(50),
  KOR_NAME VARCHAR(50),
  ENG_SURNAME VARCHAR(50),
  ENG_NAME VARCHAR(50),
  FAMILY_ORIGIN VARCHAR(50),
  PRIMARY KEY(ID)
);