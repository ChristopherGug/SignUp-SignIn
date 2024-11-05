-- Name: Christopher Gugelmeier
-- Course Code: INFT2201
-- Date: January 25th 2024

DROP TABLE IF EXISTS Users CASCADE;
CREATE EXTENSION IF NOT EXISTS pgcrypto;

CREATE TABLE Users
(
    id INT PRIMARY KEY,
	password CHAR(40) NOT NULL,
    firstName VARCHAR(128) NOT NULL,
    lastName VARCHAR(128) NOT NULL,
	emailAddress VARCHAR(255) NOT NULL,
    lastAccess VARCHAR(99) NOT NULL,
    enrolDate VARCHAR(99) NOT NULL,
    enabled BOOLEAN NOT NULL,
    type CHAR NOT NULL
);

INSERT INTO Users(id, password, firstName, lastName, emailaddress, lastaccess, enrolDate, enabled, type)
VALUES
(
    100852340,
	ENCODE(DIGEST('password123','sha1'), 'hex'),
	'Christopher',
	'Gugelmeier',
	'christopher.gugelmeier@dcmail.ca',
	'2024-02-16',
	'2024-02-16',
	true,
	's'
),
(
    100999999,
	ENCODE(DIGEST('password456','sha1'), 'hex'),
	'John',
	'Smith',
	'john.smith@dcmail.ca',
	'2024-02-16',
	'2024-02-16',
	true,
	's'
),
(
    100111111,
	ENCODE(DIGEST('password','sha1'), 'hex'),
	'Mike',
	'Jones',
	'mike.jones@dcmail.ca',
	'2024-02-16',
	'2024-02-16',
	true,
	's'
),
(
    100333333,
	ENCODE(DIGEST('password111','sha1'), 'hex'),
	'Bob',
	'Jimmy',
	'bob.jimmy@dcmail.ca',
	'2024-02-16',
	'2024-02-16',
	true,
	'f'
),
(
    100444444,
	ENCODE(DIGEST('password999','sha1'), 'hex'),
	'Facu',
	'Lty',
	'facu.lty@dcmail.ca',
	'2024-02-16',
	'2024-02-16',
	true,
	'f'
),
(
    100777777,
	ENCODE(DIGEST('password222','sha1'), 'hex'),
	'Billy',
	'Bob',
	'billy.bob@dcmail.ca',
	'2024-02-16',
	'2024-02-16',
	true,
	'f'
);

SELECT * FROM Users
JOIN Students ON Students.id = Users.id;