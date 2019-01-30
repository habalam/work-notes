-- Defines functions and other things that must come BEFORE init-schema.sql

-- See: http://www.h2database.com/html/features.html#compatibility and Oracle in the text
SET MODE Oracle;

-- Turning on the case and accent insensitive collation (with this we can't test for exact match
-- but we can test most of the cases where we ignore accents and upper-/lowercase)
-- NOTE: SLOVAK does not work for our purposes, even PRIMARY still distinguishes between c and č.
-- NOTE: This must happen before adding any object/table.
SET COLLATION ENGLISH STRENGTH PRIMARY;

-- Custom functions

CREATE TABLE WN_NOTE (ID INT);