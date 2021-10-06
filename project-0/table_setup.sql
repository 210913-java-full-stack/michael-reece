#Make new database
DROP DATABASE IF EXISTS projectZero;
CREATE DATABASE projectZero;

USE projectZero;

DROP TABLE IF EXISTS user_info;
DROP TABLE IF EXISTS accounts;
DROP TABLE IF EXISTS user_accounts;

CREATE TABLE user_accounts 
(
	junction_id INT AUTO_INCREMENT,
	account_id 	INT NOT NULL,
	username 	VARCHAR(100) NOT NULL,
	INDEX (account_id),
	INDEX (username),
	CONSTRAINT user_accounts_fk PRIMARY KEY (junction_id)
);

CREATE TABLE accounts
(
    account_id 		INT,
    account_type	VARCHAR(100),
    balance 		DECIMAL (10, 2),
    CONSTRAINT accounts_pk PRIMARY KEY (account_id), 
    CONSTRAINT accounts_user_accounts_fk FOREIGN KEY (account_id) REFERENCES user_accounts (account_id)
);

CREATE TABLE user_info
(
    username 	VARCHAR(100) NOT NULL,
    password	VARCHAR(20),
    first_name 	VARCHAR(20),
    last_name	VARCHAR(20),
    CONSTRAINT users_pk PRIMARY KEY (username), 
    CONSTRAINT users_user_accounts_fk FOREIGN KEY (username) REFERENCES user_accounts (username)
);

##Fill with some dummy data
#INSERT INTO accounts_customers (customer_id, account_id) VALUES (0001, 900001);
INSERT INTO user_accounts (username,account_id) VALUES ("johnsmith1", 900000);
INSERT INTO user_info (username, password, first_name, last_name) VALUES ("johnsmith1", "p@ssword", "John","Smith");
INSERT INTO accounts (account_id, account_type, balance) VALUES (900000, "Savings", 100.00);

INSERT INTO user_accounts (username, account_id) VALUES ("micool", 900001);
INSERT INTO user_info (username, password, first_name, last_name) VALUES ("micool", "badPassword", "Michael","Reece");
INSERT INTO accounts (account_id, account_type, balance) VALUES (900001, "Checking", 256.45);

SELECT * from user_info ui 
SELECT * FROM accounts