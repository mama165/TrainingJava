-- CREATE DATABASE  TRAINING;


-- Create a table student with a student_id  as integer,
-- a name as string (not null) and a major as string (unique)

CREATE TABLE student (
    student_id INT  AUTO_INCREMENT,
    name VARCHAR(20) UNIQUE NOT NULL,
    major VARCHAR(20) DEFAULT 'undecided',
    PRIMARY KEY(student_id)
);

CREATE TABLE student (student_id INT  AUTO_INCREMENT, name VARCHAR(20) UNIQUE NOT NULL, major VARCHAR(20) DEFAULT 'undecided', PRIMARY KEY(student_id));

-- Add a new row inside the table student
-- Manually adding Ids

 INSERT INTO student VALUES (1, 'Jack', 'Biology');
-- INSERT INTO student VALUES (2, 'Kate', 'Sociology'); -- This line add undecided as major
INSERT INTO student VALUES (3, 'Claire', 'English');
INSERT INTO student VALUES (4, 'Jack', 'Biology');
INSERT INTO student VALUES (5, 'Mike', 'Comp. Sci');

-- Add a new row inside the table student
-- With AUTO_INCREMENT

INSERT INTO student(name, major) VALUES ('Jack', 'Biology');
INSERT INTO student(name, major) VALUES ('Kate', 'Sociology');
INSERT INTO student(name, major) VALUES ('Claire', 'Chemistry');
INSERT INTO student(name, major) VALUES ('Mike', 'Computer Science');

-- Update an attribute
UPDATE student SET major='Chemistry' WHERE major='Biology' OR major='Computer Science';

UPDATE student SET name='Tom', major='Undecided' WHERE major='Biology';

-- Select only the names

SELECT name FROM student;

-- Select name and major  with alphabetic order

SELECT name, major FROM student ORDER BY name;

-- Select with descendant order
SELECT name, major FROM student ORDER BY name DESC;

-- Select all order by major then student_id
-- Explaination : if two student have the same same, well we compare their id
SELECT * FROM student ORDER BY major, student_id;

-- Select all order by major then student_id (in descending order)
SELECT * FROM student ORDER BY major, student_id DESC;

-- Select only 2 results
SELECT * FROM  student LIMIT 2;


-- Select student with Biology
SELECT * FROM student WHERE major='Biology';

-- Select all student with id < 3
SELECT FROM student WHERE student_id < 3;

-- Select all student with id < 3 and name not equal to Jack
SELECT * FROM student WHERE student_id < 4 AND name <> 'Jack';

-- Select all student with name Claire, Kate or Mike
SELECT * FROM student WHERE name IN('Claire', 'Kate', 'Mike');

-- Select all student with name Claire, Kate or Mike and with id > 2
SELECT * FROM student WHERE name IN('Claire', 'Kate', 'Mike') AND student_id > 2;




-- Create table student
-- CREATE TABLE student(student_id INT AUTO_INCREMENT, name VARCHAR(20), major VARCHAR(20), PRIMARY KEY(student_id));

-- Second part
-- Create a database called COMPANY
CREATE DATABASE COMPANY;

-- Create a table Employee
CREATE TABLE Employee(emp_id INT PRIMARY KEY AUTO_INCREMENT, first_name VARCHAR(40), last_name VARCHAR(40), birth_date DATE, sex VARCHAR(1), salary INT, super_id, branch_id);

CREATE TABLE Employee(
    emp_id INT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(40),
    last_name VARCHAR(40),
    birth_date DATE,
    sex VARCHAR(1),
    salary INT ,
    super_id INT,
    branch_id INT
);

-- Create a table Branch
CREATE TABLE branch(branch_id INT, branch_name VARCHAR(20), FOREIGN KEY (mgr_id ), mgr_start_date DATE);

CREATE TABLE branch(
    branch_id INT,
    branch_name VARCHAR(40),
    mgr_id  INT,
    mgr_start_date DATE,
    FOREIGN KEY mgr_id REFERENCES employee(emp_id) ON DELETE SET NULL;
);

-- Create a table client
CREATE TABLE client(client_id INT PRIMARY KEY, client_name VARCHAR(20), FOREIGN KEY(branch_id));

CREATE TABLE client(
    client_id INT PRIMARY KEY,
    client_name VARCHAR(20),
    FOREIGN KEY(branch_id)
);

-- Create a table Works_With (an employee works with a client)

CREATE TABLE works_With()















