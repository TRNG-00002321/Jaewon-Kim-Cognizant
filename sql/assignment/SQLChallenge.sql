-- Active: 1763658951365@@127.0.0.1@3306@Chinook
-- Parking Lot*******
-- *                *
-- *                *
--- *****************

-- SETUP:
-- Create a database server (docker)
-- $ docker run --name some-postgres -e POSTGRES_PASSWORD=mysecretpassword -p 5432:5432 -d postgres 
--In our case it is MySQL
-- Connect to the server (Azure Data Studio / Database extension)
-- Test your connection with a simple query (like a select)
-- Execute the Chinook database (from the Chinook_pg.sql file to create Chinook resources in your server)

SELECT * FROM actor;

-- Comment can be done single line with --
-- Comment can be done multi line with /* */

/*
DQL - Data Query Language
Keywords:

SELECT - retrieve data, select the columns from the resulting set
FROM - the table(s) to retrieve data from
WHERE - a conditional filter of the data
GROUP BY - group the data based on one or more columns
HAVING - a conditional filter of the grouped data
ORDER BY - sort the data
*/



-- BASIC CHALLENGES
-- List all customers (full name, customer id, and country) who are not in the USA
SELECT `FirstName`, `CustomerId`, `Country`
FROM `Customer` WHERE NOT `Country`='USA';

-- List all customers from Brazil
SELECT `FirstName`, `CustomerId`, `Country`
FROM `Customer` WHERE `Country`='Brazil';

-- List all sales agents
-- SELECT * FROM employee WHERE title LIKE '%Agent%;
SELECT * FROM `Employee` WHERe `Title` LIKE('%Agent%');

-- Retrieve a list of all countries in billing addresses on invoices
SELECT `BillingCountry` FROM `Invoice` GROUP BY `BillingCountry` ORDER BY `BillingCountry` ASC;

-- Retrieve how many invoices there were in 2009, and what was the sales total for that year?
SELECT
    YEAR(`InvoiceDate`) as year,
    SUM(`Total`)
    FROM `Invoice`
    GROUP BY year
    HAVING year=2021;

-- (challenge: find the invoice count sales total for every year using one query)
WITH TEMP AS(
    SELECT
        YEAR(i.`InvoiceDate`) as year,
        il.`Quantity` as quant,
        il.`UnitPrice`*il.`Quantity` as tot
        FROM `Invoice` i
        INNER JOIN `InvoiceLine` il ON i.`InvoiceId` = il.`InvoiceId`
)
SELECT
    year,
    COUNT(*) as sales_count,
    SUM(quant) as units,
    SUM(tot) as total_sales
    FROM TEMP
    GROUP BY year;

-- how many line items were there for invoice #37
SELECT
    COUNT(`InvoiceId`)
FROM `Invoice`
WHERE `InvoiceId`=37

-- how many invoices per country? BillingCountry  # of invoices -
SELECT
    `BillingCountry` as Country,
    COUNT(*) as Invoices
    FROM `Invoice`
    GROUP BY `BillingCountry`;

-- Retrieve the total sales per country, ordered by the highest total sales first.
SELECT
    `BillingCountry` as Country,
    SUM(`Total`) as sales_total
    FROM `Invoice`
    GROUP BY `BillingCountry`
    ORDER BY sales_total DESC;

-- JOINS CHALLENGES
-- Every Album by Artist
SELECT
    ar.`Name` as Artist,
    al.`Title` as Album
FROM `Artist` ar
INNER JOIN `Album` al on ar.`ArtistId` = al.`ArtistId`;

-- (inner keyword is optional for inner join)
-- All songs of the rock genre
SELECT
    t.`Name` as rock_songs
    FROM `Track` t 
    INNER JOIN `Genre` g ON g.`GenreId` = t.`GenreId`
    WHERE g.`Name` LIKE('Rock');


-- Show all invoices of customers from brazil (mailing address not billing)
SELECT
    i.*
    FROM `Customer` c 
    INNER JOIN `Invoice` i on i.`CustomerId` = c.`CustomerId`
    WHERE c.`Country` = 'Brazil'; 

-- Show all invoices together with the name of the sales agent for each one
--FOR finding refences to a table!
SELECT 
    TABLE_NAME,
    COLUMN_NAME,
    CONSTRAINT_NAME,
    REFERENCED_TABLE_NAME,
    REFERENCED_COLUMN_NAME
FROM 
    information_schema.KEY_COLUMN_USAGE
WHERE 
    REFERENCED_TABLE_NAME = 'Employee'
    AND TABLE_SCHEMA = DATABASE();

SELECT
    i.*,
    e.`FirstName`
    FROM `Invoice` i 
    INNER JOIN `Customer` c ON i.`CustomerId` = c.`CustomerId`
    INNER JOIN `Employee` e ON e.`EmployeeId` = c.`SupportRepId`
    WHERE e.`Title` LIKE('%SALE%');

-- Which sales agent made the most sales in 2009?
    SELECT
        YEAR(i.`InvoiceDate`) as year,
        e.`FirstName` as name,
        COUNT(i.`InvoiceId`) as number_of_sales
        FROM `Invoice` i 
        INNER JOIN `Customer` c ON i.`CustomerId` = c.`CustomerId`
        INNER JOIN `Employee` e ON e.`EmployeeId` = c.`SupportRepId`
        WHERE e.`Title` LIKE('%SALE%')
        GROUP BY  name, year
        HAVING year = 2021
        ORDER BY number_of_sales DESC
        LIMIT 1;
-- How many customers are assigned to each sales agent?
SELECT
    e.`FirstName` as employee_name,
    COUNT(DISTINCT c.`CustomerId`) as unique_customers
    FROM `Invoice` i 
    INNER JOIN `Customer` c ON i.`CustomerId` = c.`CustomerId`
    INNER JOIN `Employee` e ON e.`EmployeeId` = c.`SupportRepId`
    WHERE e.`Title` LIKE '%SALE%'
    GROUP BY e.`FirstName`;

-- Which track was purchased the most in 2010?
SELECT
    t.`Name` as name,
    YEAR(i.`InvoiceDate`) as year,
    SUM(il.`Quantity`) as sales
    FROM `Track` t
    INNER JOIN `Album` al on t.`AlbumId` = al.`AlbumId`
    INNER JOIN `InvoiceLine` il on il.`TrackId` = t.`TrackId`
    INNER JOIN `Invoice` i on i.`InvoiceId` = il.`InvoiceId`
    GROUP BY name, year
    HAVING year = 2024
    ORDER BY sales DESC
    LIMIT 1;
    

-- Show the top three best selling artists.
SELECT
    a.`Name`,
    SUM(il.`Quantity`) as sales
    FROM `Artist` a 
    INNER JOIN `Album` al on al.`ArtistId` = a.`ArtistId`
    INNER JOIN `Track` t on t.`AlbumId` = al.`AlbumId`
    INNER JOIN `InvoiceLine` il on il.`TrackId` = t.`TrackId`
    GROUP BY a.`Name`
    ORDER BY sales DESC
    LIMIT 3;

-- Which customers have the same initials as at least one other customer?
SELECT
    c1.`CustomerId`,
    CONCAT(LEFT(c1.`FirstName`,1),LEFT (c1.`LastName`,1)) as intials,
    COUNT(c2.`CustomerId`) as count_share_initals
    FROM `Customer` c1
    INNER JOIN `Customer`c2 on CONCAT(LEFT(c1.`FirstName`,1),LEFT (c1.`LastName`,1))=CONCAT(LEFT(c2.`FirstName`,1),LEFT (c2.`LastName`,1))
    WHERE c1.`CustomerId` != c2.`CustomerId`
    GROUP by c1.`CustomerId`,CONCAT(LEFT(c1.`FirstName`,1),LEFT (c1.`LastName`,1))
    HAVING count_share_initals >=1;

-- Which countries have the most invoices?
SELECT
    `BillingCountry`,
    COUNT(*) as invoice_count
    FROM `Invoice`
    GROUP BY `BillingCountry`
    ORDER BY invoice_count DESC;

-- Which city has the customer with the highest sales total?
SELECT
    c.`City`,
    SUM(i.`Total`) as sales_total
    FROM `Customer` c 
    INNER JOIN `Invoice` i on c.`CustomerId` = i.`CustomerId`
    GROUP BY c.`City`
    ORDER BY sales_total DESC
    LIMIT 1;
    

-- Who is the highest spending customer?
SELECT
    c.`CustomerId`,
    c.`FirstName`,
    c.`LastName`,
    SUM(i.`Total`) as sales_total
    FROM `Customer` c 
    INNER JOIN `Invoice` i on c.`CustomerId` = i.`CustomerId`
    GROUP BY c.`FirstName`, c.`LastName`, c.`CustomerId`
    ORDER BY sales_total DESC
    LIMIT 1;

-- Return the email and full name of of all customers who listen to Rock.
SELECT DISTINCT
        c.`Email`,
        CONCAT(c.`FirstName`," ",c.`LastName`)
    FROM `Customer` c 
    INNER JOIN `Invoice` i on c.`CustomerId` = i.`CustomerId`
    INNER JOIN `InvoiceLine` il on il.`InvoiceId` = i.`InvoiceId`
    INNER JOIN `Track` t on t.`TrackId` = il.`TrackId`
    INNER JOIN `Genre` g on t.`GenreId` = g.`GenreId`
    WHERE g.`Name` = 'Rock';

-- Which artist has written the most Rock songs?
SELECT
    a.`Name`,
    COUNT(t.`TrackId`) as rock_songs
    FROM `Artist` a 
    INNER JOIN `Album` al ON al.`ArtistId` = a.`ArtistId`
    INNER JOIN `Track` t ON t.`AlbumId` = al.`AlbumId`
    INNER JOIN `Genre` g ON g.`GenreId`=t.`GenreId`
    WHERE g.`Name`= 'Rock'
    GROUP BY a.`Name`
    ORDER BY rock_songs DESC
    LIMIT 1;

-- Which artist has generated the most revenue?
SELECT
    a.`Name`,
    COUNT() as rock_songs
    FROM `Artist` a 
    INNER JOIN `Album` al ON al.`ArtistId` = a.`ArtistId`
    INNER JOIN `Track` t ON t.`AlbumId` = al.`AlbumId`
    INNER JOIN `InvoiceLine` il ON il
    WHERE g.`Name`= 'Rock'
    GROUP BY a.`Name`
    ORDER BY rock_songs DESC
    LIMIT 1;



-- ADVANCED CHALLENGES
-- solve these with a mixture of joins, subqueries, CTE, and set operators.
-- solve at least one of them in two different ways, and see if the execution
-- plan for them is the same, or different.

-- 1. which artists did not make any albums at all?


-- 2. which artists did not record any tracks of the Latin genre?


-- 3. which video track has the longest length? (use media type table)




-- 4. boss employee (the one who reports to nobody)


-- 5. how many audio tracks were bought by German customers, and what was
--    the total price paid for them?



-- 6. list the names and countries of the customers supported by an employee
--    who was hired younger than 35.




-- DML exercises

-- 1. insert two new records into the employee table.

-- 2. insert two new records into the tracks table.

-- 3. update customer Aaron Mitchell's name to Robert Walter

-- 4. delete one of the employees you inserted.

-- 5. delete customer Robert Walter.