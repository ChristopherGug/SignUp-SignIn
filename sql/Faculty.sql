-- Name: Christopher Gugelmeier
-- Date: January 25th 2024
-- Description: This sql file creates the Faculty table

DROP TABLE IF EXISTS Faculty;

CREATE TABLE Faculty
(
    id INT PRIMARY KEY,
	schoolCode VARCHAR(4) NOT NULL,
	schoolDescription VARCHAR(70) NOT NULL,
	office CHAR(5) NOT NULL,
	extension CHAR(4) NOT NULL,
	FOREIGN KEY (id) REFERENCES Users(id)
);

INSERT INTO Faculty (id, schoolCode, schoolDescription, office, extension)
VALUES
(
	100333333,
	'SEIT',
	'Faculty of Science, Engineering & Information Technology',
	'H-140',
	'1234'
),
(
	100444444,
	'SEIT',
	'Faculty of Science, Engineering & Information Technology',
	'H-141',
	'5678'
),
(
	100777777,
	'SEIT',
	'Faculty of Science, Engineering & Information Technology',
	'H-142',
	'9123'
)
SELECT * from faculty;