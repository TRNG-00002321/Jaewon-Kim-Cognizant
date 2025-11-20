--DML commands
insert into contacts (name, email) VALUES
('alex', 'alex_email'),
('alex', 'alex_email_2P');
select * from contacts;
UPDATE contacts SET name="Jaboody", email="jaboodyPlays@gmail.com" WHERE id=2;
delete from contacts WHERE id=2;
TRUNCATE Table contacts;

--DDL commands
drop Table contacts;
CREATE Table contacts(
    id INT PRIMARY KEY AUTO_INCREMENT,
    email VARCHAR(256) UNIQUE NOT NULL,
    name VARCHAR(256) NOT NULL
);
ALTER Table contacts
add COLUMN phone_number VARCHAR(15);
ALTER Table contacts
drop COLUMN phone_number;
ALTER Table contacts
MODIFY phone_number VARCHAR(25);
ALTER TABLE contacts RENAME TO new_table_name;
ALTER TABLE new_table_name RENAME TO contacts;
ALTER TABLE contacts CHANGE phone_number cheese VARCHAR(1000);

CREATE TABLE gamers(
    id int AUTO_INCREMENT PRIMARY Key,
    name VARCHAR(50) NOT NULL,
    platform VARCHAR(50) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL CHECK(email LIKE '%_@_%._%')
);

TRUNCATE TABLE games;
DROP TABLE games;
TRUNCATE TABLE gamers;
DROP TABLE gamers;

CREATE TABLE games(
    id int AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    cost DECIMAL(10,2) NOT NULL,
    gamer_id int REFERENCES gamers(id) ON DELETE CASCADE
);

INSERT INTO gamers(name, platform, email) VALUES
("Jimmy", "Steam", "JimmyJohn@gmail.com"),
("Jimbo", "Xbox", "JimboJombo@gmail.com"),
("Jamanthal", "PlayStation", "Jamanthal@gmail.com"),
("Jeter", "Switch", "JeterJmith@gmail.com"),
("Jimmy TWO", "Steam", "JimmyJohn2@gmail.com");
SELECT * FROM gamers

INSERT INTO games(name, cost, gamer_id) VALUES
("COD:FISH FIGHT 2", 100000.10, 1),
("COD:FISH FIGHT 3", 300000.10, 2),
("COD:FISH FIGHT 4", 2000.10, 3),
("COD:FISH FIGHT 5", 10.10, 4);

ALTER Table gamers
add COLUMN phone_number VARCHAR(15);
ALTER Table gamers
drop COLUMN phone_number;
ALTER Table gamers
MODIFY phone_number VARCHAR(25);
ALTER TABLE gamers RENAME TO ultra_gamers;
ALTER TABLE ultra_gamers RENAME TO gamers;
ALTER TABLE gamers CHANGE phone_number cheese VARCHAR(1000);