
drop table TEMP_TSE_SYMBOL_PRICE;
drop table TEMP_SYMBOL_INDICATOR;


CREATE GLOBAL TEMPORARY TABLE STOCKS.TEMP_TSE_SYMBOL_PRICE
(
  ID                     NUMBER(19),
  SYMBOL                 VARCHAR(512),
  CLOSING_PRICE          FLOAT(126),
  FIRST_TRADE_PRICE      FLOAT(126),
  LAST_TRADE_PRICE       FLOAT(126),
  MAX_PRICE              FLOAT(126),
  MIN_PRICE              FLOAT(126),
  PRICE_CHANGE           FLOAT(126),
  TOTAL_TRADE_COUNT      NUMBER(10),
  TOTAL_TRADE_VALUE      FLOAT(126),
  TOTAL_TRADE_VOLUME     NUMBER(10),
  YESTERDAY_PRICE        FLOAT(126)
) ON COMMIT DELETE ROWS;

CREATE GLOBAL TEMPORARY TABLE STOCKS.TEMP_SYMBOL_INDICATOR
(
  ID                     NUMBER(19),
  INDICATOR_NAME         VARCHAR(512),
  INDICATOR_VALUE        FLOAT(126)
) ON COMMIT DELETE ROWS;

