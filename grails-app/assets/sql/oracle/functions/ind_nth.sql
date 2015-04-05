CREATE OR REPLACE FUNCTION ind_nth
  (
    sym_id NUMBER,
    cls VARCHAR,
    para VARCHAR,
    numb NUMBER

  )

  RETURN NUMBER

IS

  result NUMBER;

  BEGIN
    SELECT ind.value INTO result FROM
      (SELECT i.value FROM indicators i WHERE i.symbol_id = sym_id and i.parameter = para and i.class = cls and i.day_number = numb) ind
    WHERE ROWNUM = 1;

    RETURN(result);

  END ind_nth;
/