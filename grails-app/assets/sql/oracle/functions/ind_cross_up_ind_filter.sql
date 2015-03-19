CREATE OR REPLACE FUNCTION ind_cross_up_ind_filter
(
    sourceClass VARCHAR,
    sourceParameter VARCHAR,
    targetClass VARCHAR,
    targetParameter VARCHAR

)

RETURN SYS_REFCURSOR

IS

result SYS_REFCURSOR;

BEGIN

    IF targetClass <> 'Price' THEN
        OPEN result FOR
            SELECT s.id
                FROM tse_symbol s
                WHERE
                    ind_nth(s.id, sourceClass, sourceParameter, 1) > ind_nth(s.id, targetClass, targetParameter, 1)
                    AND
                    (
                       ind_nth(s.id, sourceClass, sourceParameter, 2) < ind_nth(s.id, targetClass, targetParameter, 2)
                       OR
                       ind_nth(s.id, sourceClass, sourceParameter, 3) < ind_nth(s.id, targetClass, targetParameter, 3)
                    );
    ELSE
        OPEN result FOR
            SELECT s.id
                FROM tse_symbol s
                WHERE
                    ind_nth(s.id, sourceClass, sourceParameter, 1) > prc_nth(s.id, 1)
                    AND
                    (
                       ind_nth(s.id, sourceClass, sourceParameter, 2) < prc_nth(s.id, 2)
                       OR
                       ind_nth(s.id, sourceClass, sourceParameter, 3) < prc_nth(s.id, 3)
                    );
    END IF;

    RETURN(result);

END ind_cross_up_ind_filter;



/
