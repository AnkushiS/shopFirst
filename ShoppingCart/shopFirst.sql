--------------------------------------------------------
--  File created - Tuesday-September-01-2015   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Table PRODUCTS
--------------------------------------------------------

  CREATE TABLE "TESTDB"."PRODUCTS" 
   (	"PROD_ID" NUMBER, 
	"PROD_NAME" VARCHAR2(50 BYTE), 
	"DESCRIPTION" VARCHAR2(50 BYTE), 
	"PRICE" NUMBER(8,2)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
REM INSERTING into TESTDB.PRODUCTS
SET DEFINE OFF;
Insert into TESTDB.PRODUCTS (PROD_ID,PROD_NAME,DESCRIPTION,PRICE) values (2,'abc','a product called abc',25);
Insert into TESTDB.PRODUCTS (PROD_ID,PROD_NAME,DESCRIPTION,PRICE) values (3,'xyz','a product called xyz',45);
--------------------------------------------------------
--  DDL for Index SYS_C007550
--------------------------------------------------------

  CREATE UNIQUE INDEX "TESTDB"."SYS_C007550" ON "TESTDB"."PRODUCTS" ("PROD_ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  Constraints for Table PRODUCTS
--------------------------------------------------------

  ALTER TABLE "TESTDB"."PRODUCTS" ADD PRIMARY KEY ("PROD_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
--------------------------------------------------------
--  DDL for Trigger prod_trig
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "TESTDB"."prod_trig" 
BEFORE INSERT ON products
FOR EACH ROW
DECLARE
  new_id number;
BEGIN
  SELECT prod_seq.nextval
    INTO new_id
    FROM dual;
  :new.prod_id := new_id;
END;
/
ALTER TRIGGER "TESTDB"."prod_trig" ENABLE;
