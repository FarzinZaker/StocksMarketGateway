CREATE OR REPLACE FUNCTION sym_sel_screener
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
            SELECT
                symbol_id as id,
                symbol,
                closing_price,
                first_trade_price,
                last_trade_price,
                max_price,
                min_price,
                price_change,
                total_trade_count,
                total_trade_value,
                total_trade_volume,
                yesterday_price
            FROM
                (SELECT
                    symbol_id,
                    symbol_persian_name || ''('' || symbol_persian_code || '')'' as symbol,
                    closing_price,
                    first_trade_price,
                    last_trade_price,
                    max_price,
                    min_price,
                    price_change,
                    total_trade_count,
                    total_trade_value,
                    total_trade_volume,
                    yesterday_price,
                    RANK() OVER (PARTITION BY symbol_id ORDER BY dat DESC) rnk
                FROM tse_symbol_daily_trade
                WHERE symbol_id IN (' || idList || '))
           WHERE rnk <= 1
                ';
    EXECUTE IMMEDIATE quer;

    IF cols IS NOT NULL THEN
      quer := '
            INSERT INTO TEMP_SYMBOL_INDICATOR
                SELECT symbol_id as id, REPLACE(REPLACE(class, ''.'', ''_''), ''stocks_indicators_symbol_'') || ''_'' || parameter as indicator_name, value as indicator_value FROM indicators WHERE symbol_id IN (' || idList || ') and day_number = 1';
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
