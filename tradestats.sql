SELECT port.REGION, port.PORT, COALESCE(test_2017.total, 0) as TOTAL_2017  , test_2017.ranking as Ranking,
( (test_2017.total / (SELECT SUM(s_export.FOB)
						FROM FACT_EXPORT s_export
						LEFT JOIN DIM_CALENDAR s_cal 		ON s_export.CALENDAR_KEY 	= s_cal.CALENDAR_KEY
						WHERE s_cal.YEAR = 2017) ) * 100 ) as Share ,
test_2018.total as TOTAL_2018, test_2018.ranking, ( ( (test_2018.total - test_2017.total) / test_2017.total) * 100 ) AS Changes	
						
FROM FACT_EXPORT export
RIGHT JOIN DIM_PORT port 			ON export.PORT_ID 			= port.PORT_ID
LEFT JOIN DIM_CALENDAR cal 		ON export.CALENDAR_KEY 	= cal.CALENDAR_KEY

LEFT JOIN ( 
		SELECT SUM(export1.FOB) as total,  DENSE_RANK() OVER (ORDER BY SUM(export1.FOB) DESC) AS ranking , port1.PORT_ID as PORT_ID
		FROM FACT_EXPORT export1
		RIGHT JOIN DIM_PORT port1 			ON export1.PORT_ID 			= port1.PORT_ID
		LEFT JOIN DIM_CALENDAR cal1 		ON export1.CALENDAR_KEY 	= cal1.CALENDAR_KEY
		WHERE cal1.YEAR = 2017
		GROUP BY port1.PORT_ID ) test_2017 ON test_2017.PORT_ID = port.PORT_ID

LEFT JOIN ( 
		SELECT SUM(export1.FOB) as total,  DENSE_RANK() OVER (ORDER BY SUM(export1.FOB) DESC) AS ranking , port1.PORT_ID as PORT_ID
		FROM FACT_EXPORT export1
		RIGHT JOIN DIM_PORT port1 			ON export1.PORT_ID 			= port1.PORT_ID
		LEFT JOIN DIM_CALENDAR cal1 		ON export1.CALENDAR_KEY 	= cal1.CALENDAR_KEY
		WHERE cal1.YEAR = 2018
		GROUP BY port1.PORT_ID ) test_2018 ON test_2018.PORT_ID = port.PORT_ID


GROUP BY port.PORT_ID, port.REGION, port.PORT, test_2017.total ,test_2017.ranking, test_2018.total, test_2018.ranking
ORDER BY test_2018.total DESC




###
SELECT port.REGION, port.PORT, '$'+CONVERT(varchar, CAST(COALESCE(test_2017.total, 0) AS money), 1)  as TOTAL_2017,
  DENSE_RANK() OVER (ORDER BY  test_2017.total   DESC) AS ranking,  
( (test_2017.total / (SELECT SUM(s_export.FOB)
						FROM FACT_EXPORT s_export
						LEFT JOIN DIM_CALENDAR s_cal 		ON s_export.CALENDAR_KEY 	= s_cal.CALENDAR_KEY
						WHERE s_cal.YEAR = 2017) ) * 100 ) as Share
						
FROM FACT_EXPORT export
RIGHT JOIN DIM_PORT port 			ON export.PORT_ID 			= port.PORT_ID
LEFT JOIN DIM_CALENDAR cal 		ON export.CALENDAR_KEY 	= cal.CALENDAR_KEY

LEFT JOIN ( 
		SELECT SUM(export1.FOB) as total,  port1.PORT_ID as PORT_ID
		FROM FACT_EXPORT export1
		RIGHT JOIN DIM_PORT port1 			ON export1.PORT_ID 			= port1.PORT_ID
		LEFT JOIN DIM_CALENDAR cal1 		ON export1.CALENDAR_KEY 	= cal1.CALENDAR_KEY
		WHERE cal1.YEAR = 2017
		GROUP BY port1.PORT_ID ) test_2017 ON test_2017.PORT_ID = port.PORT_ID

GROUP BY port.PORT_ID, port.REGION, port.PORT, test_2017.total
ORDER BY test_2017.total DESC


###
SELECT port.REGION, port.PORT, '$'+CONVERT(varchar, CAST(COALESCE(test_2017.total, 0) AS money), 1)  as TOTAL_2017,
  DENSE_RANK() OVER (ORDER BY  test_2017.total   DESC) AS ranking,  
 (test_2017.total / (SELECT SUM(s_export.FOB)
						FROM FACT_EXPORT s_export
						LEFT JOIN DIM_CALENDAR s_cal 		ON s_export.CALENDAR_KEY 	= s_cal.CALENDAR_KEY
						WHERE (s_cal.YEAR = 2017 AND s_cal.MONTH_NUM IN(1,2,3,4,5,6,7,8,9,10,11,12)) AND (s_export.PORT_ID IN('1,2,3') OR '1,2,3' = '' )    ) * 100 ) as Share
						
FROM FACT_EXPORT export
RIGHT JOIN DIM_PORT port 			ON export.PORT_ID 			= port.PORT_ID
LEFT JOIN DIM_CALENDAR cal 		ON export.CALENDAR_KEY 	= cal.CALENDAR_KEY

LEFT JOIN ( 
		SELECT SUM(export1.FOB) as total,  port1.PORT_ID as PORT_ID
		FROM FACT_EXPORT export1
		RIGHT JOIN DIM_PORT port1 			ON export1.PORT_ID 			= port1.PORT_ID
		LEFT JOIN DIM_CALENDAR cal1 		ON export1.CALENDAR_KEY 	= cal1.CALENDAR_KEY
		WHERE (cal1.YEAR = 2017 AND cal1.MONTH_NUM IN(1,2,3,4,5,6,7,8,9,10,11)) AND (export1.PORT_ID IN('1,2,3') OR '1,2,3' = '' )  
		GROUP BY port1.PORT_ID ) test_2017 ON test_2017.PORT_ID = port.PORT_ID

GROUP BY port.PORT_ID, port.REGION, port.PORT, test_2017.total
ORDER BY test_2017.total DESC