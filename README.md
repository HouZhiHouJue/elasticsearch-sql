Elasticsearch-SQ-For-ElasticSearch 1.7.6


## SQL Usage

* Query

        SELECT * FROM bank WHERE age >30 AND gender = 'm'

* Aggregation

        select COUNT(*),SUM(age),MIN(age) as m, MAX(age),AVG(age)
        FROM bank GROUP BY gender ORDER BY SUM(age), m DESC

* Delete

        DELETE FROM bank WHERE age >30 AND gender = 'm'


> ###Beyond sql

* Search

        SELECT address FROM bank WHERE address = matchQuery('880 Holmes Lane') ORDER BY _score DESC LIMIT 3
        

* Aggregations

	+ range age group 20-25,25-30,30-35,35-40

			SELECT COUNT(age) FROM bank GROUP BY range(age, 20,25,30,35,40)

	+ range date group by day

			SELECT online FROM online GROUP BY date_histogram(field='insert_time','interval'='1d')

	+ range date group by your config

			SELECT online FROM online GROUP BY date_range(field='insert_time','format'='yyyy-MM-dd' ,'2014-08-18','2014-08-17','now-8d','now-7d','now-6d','now')

* ES Geographic
		
		SELECT * FROM locations WHERE GEO_BOUNDING_BOX(fieldname,100.0,1.0,101,0.0)

* Select type

        SELECT * FROM indexName/type


## SQL Features

*  SQL Select
*  SQL Delete
*  SQL Where
*  SQL Order By
*  SQL Group By
*  SQL AND & OR
*  SQL Like
*  SQL COUNT distinct
*  SQL In
*  SQL Between
*  SQL Aliases
*  SQL Not Null
*  SQL(ES) Date
*  SQL avg()
*  SQL count()
*  SQL last()
*  SQL max()
*  SQL min()
*  SQL sum()
*  SQL Nulls
*  SQL isnull()
*  SQL now()

## Beyond sql features

*  ES TopHits
*  ES MISSING
*  ES STATS
*  ES GEO_INTERSECTS
*  ES GEO_BOUNDING_BOX
*  ES GEO_DISTANCE
*  ES GEOHASH_GRID aggregation



