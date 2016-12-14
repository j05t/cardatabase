
--RESET

DROP  USER florian;
DROP  USER josef;

DROP OWNED BY  READER_ROLE      CASCADE;
DROP OWNED BY  WRITER_ROLE      CASCADE;

REVOKE ALL ON SCHEMA public FROM WRITER_ROLE;
REVOKE ALL ON SCHEMA public FROM READER_ROLE;

DROP  ROLE READER_ROLE;
DROP  ROLE WRITER_ROLE;

--REVOKE ALL GRANTS FROM DATABASE

REVOKE CREATE  ON DATABASE postgres  FROM PUBLIC;
REVOKE CREATE  ON SCHEMA   public   FROM PUBLIC;

--CREATE ROLE

CREATE ROLE WRITER_ROLE;
CREATE ROLE READER_ROLE;

GRANT  SELECT ON ALL TABLES    IN SCHEMA public TO   WRITER_ROLE;
GRANT CREATE,USAGE ON SCHEMA public TO WRITER_ROLE;
GRANT CREATE, USAGE ON SCHEMA public TO READER_ROLE;


GRANT  SELECT ON ALL TABLES    IN SCHEMA public TO   WRITER_ROLE;
GRANT  INSERT, UPDATE, DELETE ON ALL TABLES IN SCHEMA public TO   WRITER_ROLE;
GRANT  SELECT, UPDATE ON ALL SEQUENCES IN SCHEMA public TO   WRITER_ROLE;

GRANT  SELECT ON ALL TABLES    IN SCHEMA public TO   READER_ROLE;
GRANT  SELECT ON ALL SEQUENCES IN SCHEMA public TO   READER_ROLE;

--CREATE USERS

CREATE USER florian WITH PASSWORD 'fg1';
CREATE USER josef WITH PASSWORD 'js1';

ALTER USER florian VALID UNTIL 'Dec 31 2018';
ALTER USER josef VALID UNTIL 'Dec 31 2018';

ALTER USER florian CREATEDB;
ALTER USER josef NOCREATEDB;

--ASSIGN ROLE TO USERS

GRANT  WRITER_ROLE TO florian;
GRANT  READER_ROLE TO josef;

