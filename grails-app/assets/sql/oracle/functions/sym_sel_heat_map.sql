CREATE OR REPLACE FUNCTION sym_sel_heat_map

  RETURN SYS_REFCURSOR

IS

  result SYS_REFCURSOR;

  BEGIN

    OPEN result FOR
    SELECT
      industry_group_id,
      industry_group,
      symbol_id,
      symbol_code,
      symbol_name,
      closing_price,
      total_trade_value,
      price_change
    FROM
      (SELECT grp.id industry_group_id,
              grp.name industry_group,
              symbol.id symbol_id,
              symbol.persian_code symbol_code,
              symbol.persian_name symbol_name,
              trade.closing_price closing_price,
              trade.total_trade_value total_trade_value,
              trade.price_change price_change,
              RANK() OVER (PARTITION BY symbol.industry_group_id, symbol.id ORDER BY trade.dat DESC) AS rnk
       FROM tse_symbol_daily_trade trade
         INNER JOIN
         (SELECT *
          FROM tse_symbol sym
          WHERE
            NOT REGEXP_LIKE(sym.persian_code, '(*)[0-9]$') AND
            NOT REGEXP_LIKE(sym.persian_code, '^ج (*)') AND
            NOT REGEXP_LIKE(sym.persian_name, '^ح (*)') AND
            NOT REGEXP_LIKE(sym.persian_code, '(*)ح$') AND
            sym.typ IN ('300', '400', '309', '404') ) symbol
           ON trade.symbol_id = symbol.id
         INNER JOIN tse_industry_group grp
           ON symbol.industry_group_id = grp.id)
    WHERE rnk < 2;


    RETURN(result);

  END sym_sel_heat_map;
/
