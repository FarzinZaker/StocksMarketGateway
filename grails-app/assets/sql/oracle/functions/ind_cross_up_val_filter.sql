CREATE OR REPLACE FUNCTION ind_cross_up_val_filter
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
                ind_nth(s.id, sourceClass, sourceParameter, 1) > targetValue
                AND
                (
                   ind_nth(s.id, sourceClass, sourceParameter, 2) < targetValue
                   OR
                   ind_nth(s.id, sourceClass, sourceParameter, 3) < targetValue
                );

    RETURN(result);

END ind_cross_up_val_filter;



/
