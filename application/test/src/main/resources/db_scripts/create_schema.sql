-- Defines functions and other things that must come BEFORE init-schema.sql

-- See: http://www.h2database.com/html/features.html#compatibility and Oracle in the text
SET MODE Oracle;

-- Turning on the case and accent insensitive collation (with this we can't test for exact match
-- but we can test most of the cases where we ignore accents and upper-/lowercase)
-- NOTE: SLOVAK does not work for our purposes, even PRIMARY still distinguishes between c and č.
-- NOTE: This must happen before adding any object/table.
SET COLLATION ENGLISH STRENGTH PRIMARY;

-- Custom functions

CREATE TABLE WN_TASK (
	ID INT,
	WN_TASK_THEME_ID INT,
	CREATED TIMESTAMP CONSTRAINT wn_task_nn01 NOT NULL,
	CLOSED TIMESTAMP,
	TEXT VARCHAR2 (3000),
	TASK_PRIORITY VARCHAR2 (6) CONSTRAINT wn_task_nn02 NOT NULL,
	TASK_STATE VARCHAR2 (11) CONSTRAINT wn_task_nn03 NOT NULL,
	CONSTRAINT wn_task_pk PRIMARY KEY (ID));

ALTER TABLE WN_TASK ADD CONSTRAINT wn_task_cc01 CHECK (TASK_PRIORITY in ('NONE', 'LOW', 'MEDIUM', 'HIGH', 'TOP'));
ALTER TABLE WN_TASK ADD CONSTRAINT wn_task_cc02 CHECK (TASK_STATE IN ('OPENED', 'IN_PROGRESS', 'PENDING', 'CLOSED'));

CREATE TABLE WN_TASK_THEME (
	WN_TASK_THEME_ID  INT,
	NAME VARCHAR2 (200) CONSTRAINT wn_task_theme_nn01 NOT NULL,
	DESCRIPTION CLOB,
	STATE VARCHAR2(7) CONSTRAINT wn_task_theme_nn02 NOT NULL,
	CREATED TIMESTAMP CONSTRAINT wn_task_theme_nn03 NOT NULL,
	CLOSED TIMESTAMP,
	CONSTRAINT wn_task_theme_pk PRIMARY KEY (WN_TASK_THEME_ID)
);

ALTER TABLE WN_TASK_THEME ADD CONSTRAINT wn_task_theme_cc01 CHECK (STATE in ('VALID', 'INVALID'));

ALTER TABLE WN_TASK ADD CONSTRAINT wn_task_fk01 FOREIGN KEY (WN_TASK_THEME_ID) REFERENCES WN_TASK_THEME (WN_TASK_THEME_ID);

CREATE SEQUENCE task_sequence START WITH 1000000;
CREATE SEQUENCE task_theme_sequence START WITH 1000000;