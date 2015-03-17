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
/