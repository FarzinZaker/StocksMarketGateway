CREATE OR REPLACE FUNCTION ind_lower_than_val_filter
(
    sourceClass VARCHAR,
    sourceParameter VARCHAR,
    targetValue FLOAT

)

RETURN SYS_REFCURSOR

IS

result SYS_REFCURSOR;

BEGIN

    OPEN result FOR
        SELECT s.id
            FROM tse_symbol s
            WHERE
                ind_nth(s.id, sourceClass, sourceParameter, 1) < targetValue;

    RETURN(result);

END ind_lower_than_val_filter;



/
