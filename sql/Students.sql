-- Name: Christopher Gugelmeier
-- Date: January 25th 2024
-- Description: This sql file creates the Students table

DROP TABLE IF EXISTS Students;

CREATE TABLE Students
(
    id INT PRIMARY KEY,
	programCode CHAR(4) NOT NULL,
	programDescription VARCHAR(50) NOT NULL,
	year CHAR NOT NULL,
	marks int[],
	FOREIGN KEY (id) REFERENCES Users(id)
);

INSERT INTO Students (id, programCode, programdescription, year)
VALUES
(
	100852340,
	'CPPG',
	'Computer Programming',
	'2'
),
(
	100999999,
	'CPPG',
	'Computer Programming',
	'3'
),
(
	100111111,
	'CSTY',
	'Computer System Technology',
	'3'
);