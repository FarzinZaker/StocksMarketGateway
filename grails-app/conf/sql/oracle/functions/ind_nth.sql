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



/
