SELECT
  a,
  b,
  CASE WHEN (c IS NULL) THEN CAST(NULL as int) ELSE 1 END AS c
FROM
  tablename