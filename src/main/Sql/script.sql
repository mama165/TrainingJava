-- CREATE DATABASE  training;


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
