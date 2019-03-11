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
	CREATED TIMESTAMP CONSTRAINT wn_task_nn01 NOT NULL,
	CLOSED TIMESTAMP,
	TEXT VARCHAR2 (3000),
	TASK_PRIORITY VARCHAR2 (6) CONSTRAINT wn_task_nn02 NOT NULL,
	TASK_STATE VARCHAR2 (11) CONSTRAINT wn_task_nn03 NOT NULL,
	CONSTRAINT wn_task_pk PRIMARY KEY (ID));

CREATE SEQUENCE task_sequence START WITH 1000000;