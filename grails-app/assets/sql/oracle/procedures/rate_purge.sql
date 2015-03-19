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
/