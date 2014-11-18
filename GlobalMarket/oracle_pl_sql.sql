CREATE TABLE books (author VARCHAR2 (100));

INSERT INTO books VALUES('James');
INSERT INTO books VALUES('Steven');
INSERT INTO books VALUES('Bill and Steven');
INSERT INTO books VALUES('Chip, Jonathan, and Steven');
INSERT INTO books VALUES('Chip, Jonathan, and Steven');
INSERT INTO books VALUES('Chip, Jonathan, and Steven');
INSERT INTO books VALUES('Steven');
INSERT INTO books VALUES('Steven and Bill');
INSERT INTO books VALUES('Chip and Jonathan');
INSERT INTO books VALUES('Steven');

DECLARE 
	l_book_count INTEGER;
	
BEGIN
	SELECT COUNT(*) 
		INTO l_book_count
		FROM books
		WHERE author LIKE '%Jonathan, and Steven%';
		
	DBMS_OUTPUT.put_line(
		'Steven has written (or co-written)' || l_book_count || ' books'
	);
	
	UPDATE books
		SET author = REPLACE(author, 'Steven', 'STEPHEN')
		WHERE author LIKE '%Jonathan, and Steven%';
END;

CREATE TABLE accounts (id INTEGER, name VARCHAR2 (100));

INSERT INTO accounts VALUES(1, 'James Peterson');
INSERT INTO accounts VALUES(2, 'Radical Peterson');
INSERT INTO accounts VALUES(3, 'Yaner Jonathon');
INSERT INTO accounts VALUES(4, 'Peter James');

CREATE OR REPLACE FUNCTION account_balance (
	account_id_in IN accounts.id%TYPE)
RETURN NUMBER
IS
BEGIN
 RETURN 900;
END;
/

CREATE OR REPLACE PROCEDURE apply_balance(
	account_id_in IN accounts.id%TYPE, balance_in IN NUMBER)
IS
BEGIN
	DBMS_OUTPUT.put_line(
		account_id_in || ' balance has been updated to ' || balance_in
	);
END;
/

CREATE OR REPLACE PROCEDURE pay_out_balance (
	account_id_in IN accounts.id%TYPE)
IS
	l_balance_remaining NUMBER;
BEGIN
	LOOP
		l_balance_remaining := account_balance (account_id_in);
		
		IF l_balance_remaining < 1000
		THEN
			EXIT;
		ELSE
			apply_balance(account_id_in, l_balance_remaining);
		END IF;
	END LOOP;
END pay_out_balance;
/

CREATE OR REPLACE PROCEDURE log_error
IS
BEGIN
	NULL;
END;
/

CREATE OR REPLACE PROCEDURE check_account(
	account_id_in IN accounts.id%TYPE)
IS 
	l_balance_remaining NUMBER;
	l_balance_below_minimum EXCEPTION;
	l_account_name accounts.name%TYPE;
BEGIN
	SELECT name 
		INTO l_account_name
		FROM accounts
	   WHERE id = account_id_in;
	   
	l_balance_remaining := account_balance (account_id_in);
	
	DBMS_OUTPUT.PUT_LINE(
		'Balance for ' || l_account_name ||
		' = ' || l_balance_remaining);
	
	IF l_balance_remaining < 1000
	THEN
		RAISE l_balance_below_minimum;
	END IF;
	
EXCEPTION 
	WHEN NO_DATA_FOUND
	THEN
		-- No account for this ID
		log_error();
		RAISE;
	WHEN l_balance_below_minimum
	THEN 
		log_error();
		RAISE VALUE_ERROR;
END;
/