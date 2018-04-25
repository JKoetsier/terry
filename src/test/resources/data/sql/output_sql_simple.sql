SELECT
t1.a AS a,
tn2.b AS b
FROM tableName as t1
LEFT JOIN tableName2 tn2
ON t1.c = tn2.c
WHERE t1.a = 4;