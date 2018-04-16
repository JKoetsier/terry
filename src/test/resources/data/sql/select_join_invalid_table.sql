SELECT
  basetable.a,
  basetable.b,
  jointable.d
FROM
  basetable
LEFT JOIN
  jointablee
ON
  basetable.b = jointable.c