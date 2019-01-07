-- Create a table student with a student_id as integer,
-- a name as string and a major as string

CREATE TABLE student(
    student_id INT PRIMARY KEY,
    name VARCHAR(20),
    major VARCHAR(20)
);

-- Create a table student with a student_id  as integer,
-- a name as string (not null) and a major as string (unique)

CREATE TABLE student (
    student_id INT;
    name VARCHAR(20) NOT NULL,
    major VARCHAR(20) UNIQUE,
    PRIMARY KEY(student_id)
);