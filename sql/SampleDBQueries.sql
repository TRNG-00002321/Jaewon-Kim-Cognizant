SELECT * FROM payments ORDER BY `paymentDate` DESC;
SELECT * FROM payments WHERE amount > 30000;
SELECT * FROM employees WHERE (`lastName` LIKE '%son' AND `jobTitle` LIKE 'Sale%') OR `jobTitle` Like 'VP%s';
SELECT * FROM employees WHERE (`jobTitle` LIKE 'Sale%') OR `jobTitle` Like 'VP%s';
Select lastname FROM employees ORDER BY `lastName`;
SELECT DISTINCT `lastName` FROM employees ORDER BY `lastName`;
SELECT * FROM offices WHERE country IN ('USA', 'FRANCE');
SELECT * FROM products WHERE `buyPrice` BETWEEN 90 AND 100;
SELECT * FROM employees WHERE `firstName` LIKE 'T_M';
SELECT * FROM customers ORDER BY `creditLimit` DESC;
SELECT * FROM customers WHERE `salesRepEmployeeNumber` IS NULL ORDER BY `customerName`;
SELECT orderNUmber `Order no.`, SUM(`priceEach` * quantityOrdered) total FROM orderdetails GROUP BY `Order no.` HAVING total>60000;
SELECT e.firstName, e.lastName FROM employees e ORDER BY e.`firstName`;
SELECT
    c.`customerNumber`,
    c.`contactFirstName`,
    c.`contactLastName`,
    o.status,
    o.comments
    FROM customers c
    INNER JOIN orders o ON c.`customerNumber`=o.`customerNumber`;

SELECT
    c.`customerNumber`,
    c.`contactFirstName`,
    c.`contactLastName`,
    o.status,
    o.comments
    FROM customers c
    LEFT JOIN orders o ON c.`customerNumber`=o.`customerNumber`;
SELECT
    c.`customerNumber`,
    c.`contactFirstName`,
    c.`contactLastName`,
    o.status,
    o.comments
    FROM customers c
    RIGHT JOIN orders o ON c.`customerNumber`=o.`customerNumber`;
--REVIEW SELF JOIN!
SELECT
    CONCAT() AS Manager,
    CONCAT() AS 'Direct report'
    FROM employees e
    INNER JOIN employees
--Having is for filtering groups like where filters rows
SELECT 
    YEAR(`orderDate`) AS year, 
    SUM(quantityOrdered * priceEach) AS total 
    FROM orders 
    INNER JOIN orderdetails USING (orderNumber)
    WHERE status = 'Shipped'
    GROUP BY year
    Having year > 2003;
CREATE VIEW view_example
AS
SELECT 
    YEAR(`orderDate`) AS year, 
    SUM(quantityOrdered * priceEach) AS total 
    FROM orders 
    INNER JOIN orderdetails USING (orderNumber)
    WHERE status = 'Shipped'
    GROUP BY year
    Having year > 2003;

select * from view_example