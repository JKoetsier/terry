SELECT
  basetable.a,
  basetable.b,
  jointable.d,
  jointable2.e,
  jointable3.g
FROM
  basetable
LEFT JOIN
  jointable
ON
  basetable.b = jointable.c
RIGHT JOIN
  jointable2
ON
  basetable.b = jointable2.e
INNER JOIN
  jointable3
ON
  basetable.b = jointable3.f
FULL JOIN
  jointable4
ON
  basetable.b = jointable4.h
WHERE
  basetable.b = 34
