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
/