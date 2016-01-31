DROP TABLE NEW_TSE_SYMBOL_ADJ_DAILY_TRADE;

CREATE TABLE NEW_TSE_SYMBOL_ADJ_DAILY_TRADE
  AS (
SELECT * FROM TSE_SYMBOL_ADJ_DAILY_TRADE WHERE ROWID IN
    (
        SELECT rid FROM
        (
            SELECT ROWID rid, row_number() OVER(PARTITION BY SYMBOL_ID, ADJUSTMENT_TYPE ORDER BY DAT DESC) rn
                FROM TSE_SYMBOL_ADJ_DAILY_TRADE
        )
        where rn <= 5
    ));

BEGIN
  FOR c IN
  (SELECT c.owner, c.table_name, c.constraint_name
   FROM user_constraints c, user_tables t
   WHERE c.table_name = t.table_name
   AND c.status = 'ENABLED'
   ORDER BY c.constraint_type DESC)
  LOOP
    dbms_utility.exec_ddl_statement('alter table "' || c.owner || '"."' || c.table_name || '" disable constraint ' || c.constraint_name);
  END LOOP;
END;

TRUNCATE TABLE TSE_SYMBOL_ADJ_DAILY_TRADE;

BEGIN
  FOR c IN
  (SELECT c.owner, c.table_name, c.constraint_name
   FROM user_constraints c, user_tables t
   WHERE c.table_name = t.table_name
   AND c.status = 'DISABLED'
   ORDER BY c.constraint_type)
  LOOP
    dbms_utility.exec_ddl_statement('alter table "' || c.owner || '"."' || c.table_name || '" enable constraint ' || c.constraint_name);
  END LOOP;
END;

INSERT INTO TSE_SYMBOL_ADJ_DAILY_TRADE SELECT * FROM NEW_TSE_SYMBOL_ADJ_DAILY_TRADE;