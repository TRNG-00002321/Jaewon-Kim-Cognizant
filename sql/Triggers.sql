CREATE TABLE employees_audit(
    id INT AUTO_INCREMENT PRIMARY KEY,
    employeeNumber INT NOT NULL,
    lastname VARCHAR(50) NOT NULL,
    changedat DATETIME DEFAULT NULL,
    action VARCHAR(50) DEFAULT NULL
);

CREATE TRIGGER defore_empolyee_update
    BEFORE UPDATE ON `Employee`
    FOR EACH ROW 
INSERT INTO employees_audit
SET ACTION ='update',
    employeeNumber = OLD.employeeId,
    lastname = OLD.lastname,
    changedat = NOW();