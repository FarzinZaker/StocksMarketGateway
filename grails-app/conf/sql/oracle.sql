CREATE SEQUENCE "STOCKS"."HIBERNATE_SEQUENCE" NOCYCLE ORDER CACHE 20 NOMAXVALUE MINVALUE 1 INCREMENT BY 1 START WITH 1;


DROP TABLE TEMP_TSE_SYMBOL_PRICE;
DROP TABLE TEMP_SYMBOL_INDICATOR;


CREATE GLOBAL TEMPORARY TABLE STOCKS.TEMP_TSE_SYMBOL_PRICE
(
  ID                 NUMBER(19),
  SYMBOL             VARCHAR(512),
  CLOSING_PRICE      FLOAT(126),
  FIRST_TRADE_PRICE  FLOAT(126),
  LAST_TRADE_PRICE   FLOAT(126),
  MAX_PRICE          FLOAT(126),
  MIN_PRICE          FLOAT(126),
  PRICE_CHANGE       FLOAT(126),
  TOTAL_TRADE_COUNT  NUMBER(10),
  TOTAL_TRADE_VALUE  FLOAT(126),
  TOTAL_TRADE_VOLUME NUMBER(10),
  YESTERDAY_PRICE    FLOAT(126)
) ON COMMIT DELETE ROWS;

CREATE GLOBAL TEMPORARY TABLE STOCKS.TEMP_SYMBOL_INDICATOR
(
  ID              NUMBER(19),
  INDICATOR_NAME  VARCHAR(512),
  INDICATOR_VALUE FLOAT(126)
) ON COMMIT DELETE ROWS;



CREATE OR REPLACE FUNCTION ind_nth
  (
    symbol_id NUMBER,
    class VARCHAR,
    parameter VARCHAR,
    num NUMBER

  )

  RETURN NUMBER

IS

  result NUMBER;

  BEGIN
    SELECT value INTO result FROM
      (SELECT i.value FROM indicators i WHERE i.symbol_id = symbol_id and i.parameter = parameter and i.class = class and i.day_number = num)
    WHERE ROWNUM = 1;

    RETURN(result);

  END ind_nth;

CREATE OR REPLACE FUNCTION prc_nth
  (
    symbol_id NUMBER,
    num NUMBER

  )

  RETURN NUMBER

IS

  result NUMBER;

  BEGIN
    SELECT closing_price INTO result FROM
      (SELECT t.closing_price, RANK() OVER (ORDER BY daily_snapshot DESC) day_rank FROM tse_symbol_daily_trade t WHERE t.symbol_id = symbol_id)
    WHERE day_rank = num;

    RETURN(result);

  END prc_nth;



CREATE OR REPLACE PROCEDURE STOCKS.rate_purge
  (
    name VARCHAR
  )

IS

  quer VARCHAR2(32767);

  BEGIN

    quer := 'UPDATE rate_' || name || '_ev set daily_snapshot = TRUNC(time) WHERE daily_snapshot is not null';
    EXECUTE IMMEDIATE quer;
    quer := 'UPDATE rate_' || name || '_ev set weekly_snapshot = TRUNC(time) WHERE weekly_snapshot is not null';
    EXECUTE IMMEDIATE quer;
    quer := 'UPDATE rate_' || name || '_ev set monthly_snapshot = TRUNC(time) WHERE monthly_snapshot is not null';
    EXECUTE IMMEDIATE quer;

    quer := '
    DELETE FROM rate_' || name || '_ev WHERE id in (
    SELECT id FROM rate_' || name || '_ev e1 WHERE (
        EXISTS
        (SELECT * FROM rate_' || name || '_ev e2 WHERE
                e1.id < e2.id and
                e1.symbol = e2.symbol and
                TRUNC(time) = e2.daily_snapshot)
        OR EXISTS
        (SELECT * FROM rate_' || name || '_ev e2 WHERE
                e1.id <> e2.id and
                e1.symbol = e2.symbol and
                e1.time < e2.time and
                e1.daily_snapshot is null)
        )
    )';

    EXECUTE IMMEDIATE quer;
  END rate_purge;


CREATE OR REPLACE FUNCTION ind_cross_down_ind_filter
  (
    sourceClass     VARCHAR,
    sourceParameter VARCHAR,
    targetClass     VARCHAR,
    targetParameter VARCHAR

  )

  RETURN SYS_REFCURSOR

IS

  result SYS_REFCURSOR;

  BEGIN

    IF targetClass <> 'Price'
    THEN
      OPEN result FOR
      SELECT s.id
      FROM tse_symbol s
      WHERE
        ind_nth(s.id, sourceClass, sourceParameter, 1) < ind_nth(s.id, targetClass, targetParameter, 1)
        AND
        (
          ind_nth(s.id, sourceClass, sourceParameter, 2) > ind_nth(s.id, targetClass, targetParameter, 2)
          OR
          ind_nth(s.id, sourceClass, sourceParameter, 3) > ind_nth(s.id, targetClass, targetParameter, 3)
        );
    ELSE
      OPEN result FOR
      SELECT s.id
      FROM tse_symbol s
      WHERE
        ind_nth(s.id, sourceClass, sourceParameter, 1) < prc_nth(s.id, 1)
        AND
        (
          ind_nth(s.id, sourceClass, sourceParameter, 2) > prc_nth(s.id, 2)
          OR
          ind_nth(s.id, sourceClass, sourceParameter, 3) > prc_nth(s.id, 3)
        );
    END IF;

    RETURN (result);

  END ind_cross_down_ind_filter;

CREATE OR REPLACE FUNCTION ind_cross_down_val_filter
  (
    sourceClass     VARCHAR,
    sourceParameter VARCHAR,
    targetValue     FLOAT

  )

  RETURN SYS_REFCURSOR

IS

  result SYS_REFCURSOR;

  BEGIN

    OPEN result FOR
    SELECT s.id
    FROM tse_symbol s
    WHERE
      ind_nth(s.id, sourceClass, sourceParameter, 1) < targetValue
      AND
      (
        ind_nth(s.id, sourceClass, sourceParameter, 2) > targetValue
        OR
        ind_nth(s.id, sourceClass, sourceParameter, 3) > targetValue
      );

    RETURN (result);

  END ind_cross_down_val_filter;

CREATE OR REPLACE FUNCTION ind_cross_up_ind_filter
  (
    sourceClass     VARCHAR,
    sourceParameter VARCHAR,
    targetClass     VARCHAR,
    targetParameter VARCHAR

  )

  RETURN SYS_REFCURSOR

IS

  result SYS_REFCURSOR;

  BEGIN

    IF targetClass <> 'Price'
    THEN
      OPEN result FOR
      SELECT s.id
      FROM tse_symbol s
      WHERE
        ind_nth(s.id, sourceClass, sourceParameter, 1) > ind_nth(s.id, targetClass, targetParameter, 1)
        AND
        (
          ind_nth(s.id, sourceClass, sourceParameter, 2) < ind_nth(s.id, targetClass, targetParameter, 2)
          OR
          ind_nth(s.id, sourceClass, sourceParameter, 3) < ind_nth(s.id, targetClass, targetParameter, 3)
        );
    ELSE
      OPEN result FOR
      SELECT s.id
      FROM tse_symbol s
      WHERE
        ind_nth(s.id, sourceClass, sourceParameter, 1) > prc_nth(s.id, 1)
        AND
        (
          ind_nth(s.id, sourceClass, sourceParameter, 2) < prc_nth(s.id, 2)
          OR
          ind_nth(s.id, sourceClass, sourceParameter, 3) < prc_nth(s.id, 3)
        );
    END IF;

    RETURN (result);

  END ind_cross_up_ind_filter;


CREATE OR REPLACE FUNCTION ind_cross_up_val_filter
  (
    sourceClass VARCHAR,
    sourceParameter VARCHAR,
    targetValue FLOAT
  )

  RETURN SYS_REFCURSOR

IS

  result SYS_REFCURSOR;

  BEGIN

    OPEN result FOR
    SELECT s.id
    FROM tse_symbol s
    WHERE
      ind_nth(s.id, sourceClass, sourceParameter, 1) > targetValue
      AND
      (
        ind_nth(s.id, sourceClass, sourceParameter, 2) < targetValue
        OR
        ind_nth(s.id, sourceClass, sourceParameter, 3) < targetValue
      );

    RETURN(result);

  END ind_cross_up_val_filter;


CREATE OR REPLACE FUNCTION ind_lower_than_ind_filter
  (
    sourceClass VARCHAR,
    sourceParameter VARCHAR,
    targetClass VARCHAR,
    targetParameter VARCHAR
  )

  RETURN SYS_REFCURSOR

IS

  result SYS_REFCURSOR;

  BEGIN

    IF targetClass <> 'Price' THEN
      OPEN result FOR
      SELECT s.id
      FROM tse_symbol s
      WHERE
        ind_nth(s.id, sourceClass, sourceParameter, 1) < ind_nth(s.id, targetClass, targetParameter, 1);
    ELSE
      OPEN result FOR
      SELECT s.id
      FROM tse_symbol s
      WHERE
        ind_nth(s.id, sourceClass, sourceParameter, 1) < prc_nth(s.id, 1);
    END IF;

    RETURN(result);

  END ind_lower_than_ind_filter;


CREATE OR REPLACE FUNCTION ind_lower_than_val_filter
  (
    sourceClass VARCHAR,
    sourceParameter VARCHAR,
    targetValue FLOAT

  )

  RETURN SYS_REFCURSOR

IS

  result SYS_REFCURSOR;

  BEGIN

    OPEN result FOR
    SELECT s.id
    FROM tse_symbol s
    WHERE
      ind_nth(s.id, sourceClass, sourceParameter, 1) < targetValue;

    RETURN(result);

  END ind_lower_than_val_filter;

CREATE OR REPLACE FUNCTION ind_upper_than_ind_filter
  (
    sourceClass VARCHAR,
    sourceParameter VARCHAR,
    targetClass VARCHAR,
    targetParameter VARCHAR
  )

  RETURN SYS_REFCURSOR

IS

  result SYS_REFCURSOR;

  BEGIN

    IF targetClass <> 'Price' THEN
      OPEN result FOR
      SELECT s.id
      FROM tse_symbol s
      WHERE
        ind_nth(s.id, sourceClass, sourceParameter, 1) > ind_nth(s.id, targetClass, targetParameter, 1);
    ELSE
      OPEN result FOR
      SELECT s.id
      FROM tse_symbol s
      WHERE
        ind_nth(s.id, sourceClass, sourceParameter, 1) > prc_nth(s.id, 1);
    END IF;

    RETURN(result);

  END ind_upper_than_ind_filter;


CREATE OR REPLACE FUNCTION ind_upper_than_val_filter
  (
    sourceClass VARCHAR,
    sourceParameter VARCHAR,
    targetValue FLOAT
  )

  RETURN SYS_REFCURSOR

IS

  result SYS_REFCURSOR;

  BEGIN

    OPEN result FOR
    SELECT s.id
    FROM tse_symbol s
    WHERE
      ind_nth(s.id, sourceClass, sourceParameter, 1) > targetValue;

    RETURN(result);

  END ind_upper_than_val_filter;


CREATE OR REPLACE FUNCTION prc_lower_than_val_filter
  (
    value FLOAT
  )

  RETURN SYS_REFCURSOR

IS

  result SYS_REFCURSOR;

  BEGIN

    OPEN result FOR
    SELECT DISTINCT symbol_id
    FROM tse_symbol_daily_trade
    WHERE closing_price < value and symbol_id is not null and id IN (SELECT max(id) FROM tse_symbol_daily_trade GROUP BY symbol_id);

    RETURN(result);

  END prc_lower_than_val_filter;

CREATE OR REPLACE FUNCTION prc_ncfp_upper_than_val_filter
  (
    percent FLOAT
  )

  RETURN SYS_REFCURSOR

IS

  result SYS_REFCURSOR;

  BEGIN

    OPEN result FOR
    SELECT DISTINCT symbol_id
    FROM tse_symbol_daily_trade
    WHERE (first_trade_price - closing_price)/first_trade_price >= percent and symbol_id is not null and id IN (SELECT max(id) FROM tse_symbol_daily_trade GROUP BY symbol_id);

    RETURN(result);

  END prc_ncfp_upper_than_val_filter;

CREATE OR REPLACE FUNCTION STOCKS.prc_ncpd_upper_than_val_filter
  (
    percent FLOAT
  )

  RETURN SYS_REFCURSOR

IS

  result SYS_REFCURSOR;

  BEGIN

    OPEN result FOR
    SELECT DISTINCT symbol_id
    FROM tse_symbol_daily_trade
    WHERE (yesterday_price - closing_price)/yesterday_price >= percent and symbol_id is not null and id IN (SELECT max(id) FROM tse_symbol_daily_trade GROUP BY symbol_id);

    RETURN(result);

  END prc_ncpd_upper_than_val_filter;


CREATE OR REPLACE FUNCTION STOCKS.prc_pcfp_upper_than_val_filter
  (
    percent FLOAT
  )

  RETURN SYS_REFCURSOR

IS

  result SYS_REFCURSOR;

  BEGIN

    OPEN result FOR
    SELECT DISTINCT symbol_id
    FROM tse_symbol_daily_trade
    WHERE (closing_price - first_trade_price)/first_trade_price >= percent and symbol_id is not null and id IN (SELECT max(id) FROM tse_symbol_daily_trade GROUP BY symbol_id);

    RETURN(result);

  END prc_pcfp_upper_than_val_filter;


CREATE OR REPLACE FUNCTION STOCKS.prc_pcpd_upper_than_val_filter
  (
    percent FLOAT
  )

  RETURN SYS_REFCURSOR

IS

  result SYS_REFCURSOR;

  BEGIN

    OPEN result FOR
    SELECT DISTINCT symbol_id
    FROM tse_symbol_daily_trade
    WHERE (closing_price - yesterday_price)/yesterday_price >= percent and symbol_id is not null and id IN (SELECT max(id) FROM tse_symbol_daily_trade GROUP BY symbol_id);

    RETURN(result);

  END prc_pcpd_upper_than_val_filter;



CREATE OR REPLACE FUNCTION prc_upper_than_val_filter
  (
    value FLOAT
  )

  RETURN SYS_REFCURSOR

IS

  result SYS_REFCURSOR;

  BEGIN

    OPEN result FOR
    SELECT DISTINCT symbol_id
    FROM tse_symbol_daily_trade
    WHERE closing_price > value and symbol_id is not null and id IN (SELECT max(id) FROM tse_symbol_daily_trade GROUP BY symbol_id);

    RETURN(result);

  END prc_upper_than_val_filter;


CREATE OR REPLACE FUNCTION STOCKS.sym_sel_screener
  (
    idList VARCHAR,
    cols VARCHAR
  )

  RETURN SYS_REFCURSOR

IS

  quer VARCHAR2(32767);
  result SYS_REFCURSOR;

  BEGIN

    quer := '
        INSERT INTO TEMP_TSE_SYMBOL_PRICE
            SELECT t.symbol_id as id,
                t.symbol_persian_name || '' ('' || t.symbol_persian_code || '')'' as symbol,
                t.closing_price as closingPrice,
                t.first_trade_price as firstTradePrice,
                t.last_trade_price as lastTradePrice,
                t.max_price as maxPrice,
                t.min_price as minPrice,
                t.price_change as priceChange,
                t.total_trade_count as totalTradeCount,
                t.total_trade_value as totalTradeValue,
                t.total_trade_volume as totalTradeVolume,
                t.yesterday_price as yesterdayPrice
                FROM
                    (SELECT * FROM tse_symbol_daily_trade WHERE is_last = 1 and id IN (SELECT max(id) FROM tse_symbol_daily_trade WHERE symbol_id IN (' || idList || ') GROUP BY symbol_id)) t';
    EXECUTE IMMEDIATE quer;

    IF cols <> '' THEN
      quer := '
            INSERT INTO TEMP_SYMBOL_INDICATOR
                SELECT symbol_id as id, REPLACE(class, ''.'', ''_'') + ''_'' + parameter as indicator, value as indicatorValue FROM indicators WHERE symbol_id IN (' || idList || ') and day_number = 1';
      EXECUTE IMMEDIATE quer;

      quer := q'[
            SELECT * FROM
                (SELECT * FROM TEMP_SYMBOL_INDICATOR
                            PIVOT(SUM(indicator_value) FOR indicator_name IN (]' || cols || q'[)) i) piv
            INNER JOIN TEMP_TSE_SYMBOL_PRICE tot
            ON tot.id = piv.id]';
      OPEN result FOR quer;
    ELSE
      OPEN result FOR
      SELECT * FROM TEMP_TSE_SYMBOL_PRICE;
    END IF;


    RETURN(result);

  END sym_sel_screener;



CREATE OR REPLACE FUNCTION prc_lower_than_val_filter
  (
    value FLOAT
  )

  RETURN SYS_REFCURSOR

IS

  result SYS_REFCURSOR;

  BEGIN

    OPEN result FOR
    SELECT DISTINCT symbol_id
    FROM tse_symbol_daily_trade
    WHERE total_trade_volume < value and symbol_id is not null and id IN (SELECT max(id) FROM tse_symbol_daily_trade GROUP BY symbol_id);

    RETURN(result);

  END vol_lower_than_val_filter;


CREATE OR REPLACE FUNCTION STOCKS.vol_nca_upper_than_val_filter
  (
    percent FLOAT,
    days NUMBER
  )

  RETURN SYS_REFCURSOR

IS

  result SYS_REFCURSOR;
  startDate DATE;
  endDate DATE;

  BEGIN

    startDate := SYSDATE - (days + 1);
    endDate := SYSDATE - 1;
    OPEN result FOR
    SELECT DISTINCT symbol_id
    FROM tse_symbol_daily_trade t
    WHERE 1 - (total_trade_volume / (SELECT avg(total_trade_volume)
                                     FROM tse_symbol_daily_trade t2
                                     WHERE symbol_id = t.symbol_id AND
                                           dat >= startDate AND
                                           dat <= endDate)) >= percent
          AND symbol_id is not null
          AND id IN (SELECT max(id) FROM tse_symbol_daily_trade GROUP BY symbol_id);

    RETURN(result);

  END vol_nca_upper_than_val_filter;



CREATE OR REPLACE FUNCTION STOCKS.vol_pca_upper_than_val_filter
  (
    percent FLOAT,
    days NUMBER
  )

  RETURN SYS_REFCURSOR

IS

  result SYS_REFCURSOR;
  startDate DATE;
  endDate DATE;

  BEGIN

    startDate := SYSDATE - (days + 1);
    endDate := SYSDATE - 1;
    OPEN result FOR
    SELECT DISTINCT symbol_id
    FROM tse_symbol_daily_trade t
    WHERE (total_trade_volume / (SELECT avg(total_trade_volume)
                                 FROM tse_symbol_daily_trade t2
                                 WHERE symbol_id = t.symbol_id and
                                       dat >= startDate and
                                       dat <= endDate)) - 1 >= percent
          and symbol_id is not null
          and id IN (SELECT max(id) FROM tse_symbol_daily_trade GROUP BY symbol_id);

    RETURN(result);

  END vol_pca_upper_than_val_filter;



CREATE OR REPLACE FUNCTION vol_upper_than_val_filter
  (
    value FLOAT
  )

  RETURN SYS_REFCURSOR

IS

  result SYS_REFCURSOR;

  BEGIN

    OPEN result FOR
    SELECT DISTINCT symbol_id
    FROM tse_symbol_daily_trade
    WHERE total_trade_volume > value and symbol_id is not null and id IN (SELECT max(id) FROM tse_symbol_daily_trade GROUP BY symbol_id);

    RETURN(result);

  END vol_upper_than_val_filter;



