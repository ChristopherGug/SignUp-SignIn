DROP TABLE IF EXISTS customers;

CREATE TABLE customers(
	phoneNumber VARCHAR(15) PRIMARY KEY,
	address VARCHAR(30) NOT  NULL,
	name VARCHAR(30) NOT  NULL
);
ALTER TABLE customers OWNER TO db_user;
INSERT INTO customers(phoneNumber, address, name) VALUES ('123-4567','Toronto','Eleanor');
INSERT INTO customers(phoneNumber, address, name) VALUES ('765-4321','Montreal','Dave');
INSERT INTO customers(phoneNumber, address, name) VALUES ('222-2222','Halifax','Mike');
INSERT INTO customers(phoneNumber, address, name) VALUES ('333-3333','Winnipeg','Brian');
INSERT INTO customers(phoneNumber, address, name) VALUES ('444-4444','Vancouver','Dan');
INSERT INTO customers(phoneNumber, address, name) VALUES ('555-5555','Calgary','JoAnn');

SELECT * FROM customers;