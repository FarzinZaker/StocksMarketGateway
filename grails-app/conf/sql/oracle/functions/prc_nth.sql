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



/
