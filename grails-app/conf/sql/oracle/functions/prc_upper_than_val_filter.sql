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



/
