alter session set nls_date_format = 'yyyy-MM-dd';

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
/

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
 RETURN 1100;
END;
/

CREATE OR REPLACE PROCEDURE apply_balance(
	account_id_in IN accounts.id%TYPE, balance_in IN NUMBER)
IS
BEGIN
	DBMS_OUTPUT.put_line(
		account_id_in || ' balance has been updated to ' || balance_in
	);
	NULL;
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

DECLARE 
	account_id INTEGER;
	balance_remaining NUMBER;
	
	FUNCTION account_balance(id_in IN INTEGER) 
		RETURN NUMBER
	IS
	BEGIN
		RETURN 0;
	END;
	
	PROCEDURE apply_balance(
		id_in IN INTEGER, balance_in IN INTEGER)
	IS
	BEGIN
		NULL;
	END;
BEGIN
	LOOP 
		balance_remaining := account_balance(account_id);
	
		IF balance_remaining < 1000
		THEN 
			DBMS_OUTPUT.PUT_LINE('Exiting');
			EXIT;
		ELSE
			DBMS_OUTPUT.PUT_LINE('Applying balance');
			apply_balance(account_id, balance_remaining);
		END IF;
	END LOOP;
	
	LOOP
		EXIT WHEN balance_remaining < 1000;
		DBMS_OUTPUT.PUT_LINE('Applying balance');
		apply_balance(account_id, balance_remaining);
	END LOOP;
END;
/

DECLARE
	pipename CONSTANT VARCHAR2(12) := 'signaler';
	result INTEGER;
	pipebuf VARCHAR2(64);
	
	PROCEDURE data_gathering_procedure
	IS
	BEGIN
		NULL;
	END;
	
BEGIN
	result := sys.DBMS_PIPE.create_pipe(pipename);
	
	LOOP
		data_gathering_procedure;
		sys.DBMS_LOCK.sleep(10);
		
		IF sys.DBMS_PIPE.receive_message(pipename, 0) = 0
		THEN
			sys.DBMS_PIPE.unpack_message(pipebuf);
			EXIT WHEN pipebuf = 'stop';
		END IF;
	END LOOP;
END;
/

DECLARE
	pipename VARCHAR2(12) := 'signaler';
	result INTEGER := DBMS_OUTPUT.create_pipe(pipename);
BEGIN
	DBMS_PIPE.pack_message('stop');
END;
/

DECLARE
	FOR loop_counter IN REVERSE 1 .. 10
	LOOP
		NULL;
	END LOOP;
END;
/

DROP TABLE occupancy;
/

CREATE TABLE occupancy
(
   pet_id          INTEGER
 , name            VARCHAR2 (200)
 , room_number     INTEGER
 , occupied_dt     DATE
 , checkout_date   DATE
)
/

INSERT INTO occupancy VALUES(1, 'White Dog', 3, '2013-09-23', '2014-09-23');
INSERT INTO occupancy VALUES(2, 'Black Dog', 3, '2013-09-23', '2014-09-23');
INSERT INTO occupancy VALUES(3, 'White Cat', 2, '2013-09-23', '2014-09-23');
INSERT INTO occupancy VALUES(4, 'Blue Yak', 4, '2013-09-23', '2014-09-23');
INSERT INTO occupancy VALUES(5, 'Red Rat', 3, '2013-09-23', '2014-09-23');

CREATE TABLE occupancy_history
(
   pet_id          INTEGER
 , name            VARCHAR2 (100)
 , checkout_date   DATE
)
/

CREATE OR REPLACE PROCEDURE update_bill(
	id_in IN INTEGER, room_in IN INTEGER)
IS
BEGIN
	NULL;
END;
/

CREATE OR REPLACE PACKAGE pets_global
IS
	max_pets NUMBER := 100;
END;
/

DECLARE
	CURSOR occupancy_cur
	IS
		SELECT pet_id, room_number
			FROM occupancy
			WHERE occupied_dt = TRUNC(SYSDATE);
			
	pet_count INTEGER := 0;
BEGIN
	FOR occupancy_rec IN occupancy_cur
	LOOP
		update_bill(occupancy_rec.pet_id, occupancy_rec.room_number);
		pet_count := pet_count + 1;
		EXIT WHEN pet_count >= pets_global.max_pets;
	END LOOP;
	DBMS_OUTPUT.PUT_LINE('Records updated ' || pet_count);
END;
/

DECLARE
	CURSOR checked_out_cur
	IS
		SELECT pet_id, name, checkout_date 
			FROM occupancy
			WHERE checkout_date IS NOT NULL;
BEGIN
	FOR checked_out_rec IN checked_out_cur
	LOOP
		INSERT INTO occupancy_history (pet_id, name, checkout_date)
			VALUES(checked_out_rec.pet_id, 
				checked_out_rec.name, 
				checked_out_rec.checkout_date);
		
		DELETE FROM occupancy 
			WHERE pet_id = checked_out_rec.pet_id;
	END LOOP;
END;
/

SELECT * FROM occupancy_history;

-- Chapter 6 --
CREATE OR REPLACE PACKAGE dynsql
IS
	invalid_table_name EXCEPTION;
		PRAGMA EXCEPTION_INIT (invalid_table_name, -903);
	invalid_identifier EXCEPTION;
		PRAGMA EXCEPTION_INIT (invalid_identifier, -904);
END;
/

CREATE OR REPLACE PROCEDURE proc1
IS
BEGIN
	DBMS_OUTPUT.PUT_LINE('running proc1');
	RAISE NO_DATA_FOUND;
END;
/

CREATE OR REPLACE PROCEDURE proc2
IS
	l_str VARCHAR2(30) := 'calling proc1';
BEGIN
	DBMS_OUTPUT.put_line(l_str);
	proc1;
END;
/

CREATE OR REPLACE PROCEDURE proc3
IS 
BEGIN
	DBMS_OUTPUT.PUT_LINE('calling proc2');
	proc2;
	EXCEPTION
		WHEN OTHERS
		THEN
			DBMS_OUTPUT.PUT_LINE('Error stack at top level:');
			DBMS_OUTPUT.PUT_LINE(DBMS_UTILITY.format_error_backtrace);
END;
/

BEGIN
	DBMS_OUTPUT.PUT_LINE('Proc3 -> Proc2 -> Proc1 backtrace');
	proc3;
END;
/

BEGIN
	DBMS_OUTPUT.PUT_LINE(SQLERRM (-1403));
END;
/

CREATE OR REPLACE FUNCTION sales_percentage_calculation(
	mine_in IN NUMBER, total_in IN NUMBER)
	RETURN NUMBER
IS 
BEGIN
	RETURN NULL;
END;
/

CREATE OR REPLACE FUNCTION return_stuff
	RETURN NUMBER
IS
	total_sales NUMBER;
	my_sales NUMBER := 1000;
BEGIN
	IF total_sales = 0
	THEN
		DBMS_OUTPUT.PUT_LINE('TOTAL SALES IS 0');
		RAISE ZERO_DIVIDE;
	ELSE
		RETURN (sales_percentage_calculation(my_sales, total_sales));
	END IF;
END;
/

SELECT return_stuff FROM DUAL;

BEGIN
	EXCUTE return_stuff;
END;
/

CREATE OR REPLACE PACKAGE bt 
IS
	TYPE error_rt IS RECORD (
		program_owner all_objects.owner%TYPE,
		program_name all_objects.object_name%TYPE,
		line_number PLS_INTEGER
	);
	
	FUNCTION info(backtrace_in IN VARCHAR2)
		RETURN error_rt;
	
	PROCEDURE show_info(backtrace_in IN VARCHAR2);
END bt;
/

BEGIN
   DBMS_OUTPUT.put_line (SQLERRM (-11));
END;
/

BEGIN
   DBMS_OUTPUT.put_line (SQLERRM (-27378));
END;
/

BEGIN
   DBMS_OUTPUT.put_line (SQLERRM (3782));
END;
/

BEGIN
	<<local_block>>
	DECLARE
		case_is_not_made EXCEPTION;
	BEGIN
		NULL;
		RAISE case_is_not_made;
	END local_block;
END;
/