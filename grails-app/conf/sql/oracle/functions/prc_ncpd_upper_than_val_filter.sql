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
/