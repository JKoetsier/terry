SELECT
  basetable.a,
  basetable.b,
  jointable.d
FROM
  basetable
LEFT JOIN
  jointable
ON
  basetable.b = jointable.e