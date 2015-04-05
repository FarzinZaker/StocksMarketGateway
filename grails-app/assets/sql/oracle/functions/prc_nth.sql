CREATE OR REPLACE FUNCTION prc_nth
  (
    sym_id NUMBER,
    numb NUMBER

  )

  RETURN NUMBER

IS

  result NUMBER;

  BEGIN
    SELECT closing_price INTO result FROM
      (SELECT t.closing_price, RANK() OVER (ORDER BY t.dat DESC) day_rank FROM tse_symbol_daily_trade t WHERE t.symbol_id = sym_id)
    WHERE day_rank = numb;

    RETURN(result);

  END prc_nth;
/
