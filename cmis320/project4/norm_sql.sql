/* SQL Normalization SQL File */
SPOOL c:\sdev350\normalization.txt
SET ECHO ON
SET LINESIZE 100

/* Drop Tables in case they already exist */
DROP TABLE courseinfo CASCADE CONSTRAINTS;
DROP TABLE courses CASCADE CONSTRAINTS;
DROP TABLE instructors CASCADE CONSTRAINTS;
DROP TABLE  classes CASCADE CONSTRAINTS;

/* Create tables */
CREATE TABLE courseinfo(coursename   VARCHAR2(20) PRIMARY KEY,
			      credits      NUMBER(1)    NOT NULL,
			      maxclasssize NUMBER(2)    NOT NULL);

CREATE TABLE courses(courseid   CHAR(7)      PRIMARY KEY,
			   coursename VARCHAR2(15) NOT NULL REFERENCES courseinfo);

CREATE TABLE instructors(instructor VARCHAR2(12) PRIMARY KEY,
 instdept   NUMBER(3)    NOT NULL);

CREATE TABLE classes (year       CHAR(7),
			    term       VARCHAR2(6),
			    courseid   CHAR(7) REFERENCES courses,
			    instructor VARCHAR2(12) REFERENCES instructors,
	CONSTRAINT pk_classes PRIMARY KEY (year, term, courseid, instructor));

/* Verify the tables were created */
SELECT table_name
  FROM user_tables;

/* Describe the tables */
DESC courseinfo;
DESC courses;
DESC instructors;
DESC classes;

/* Insert data into Tables */
INSERT INTO courseinfo (coursename, credits, maxclasssize)
                VALUES ('US History', 3, 30);
INSERT INTO courseinfo (coursename, credits, maxclasssize)
                VALUES ('World History', 4, 25);
INSERT INTO courses (courseid, coursename)
		 VALUES ('HIST101', 'US History');
INSERT INTO courses (courseid, coursename)
		 VALUES ('HIST102', 'US History');
INSERT INTO courses (courseid, coursename)
		 VALUES ('HIST103', 'World History');
INSERT INTO instructors (instructor, instdept)
		     VALUES ('Smith', 500);
INSERT INTO instructors (instructor, instdept)
		     VALUES ('Adams', 501);
INSERT INTO instructors (instructor, instdept)
		     VALUES ('Jones', 502);
INSERT INTO classes (year, term, courseid, instructor)
		 VALUES ('2017-18', 'Fall', 'HIST101', 'Smith');
INSERT INTO classes (year, term, courseid, instructor)
		 VALUES ('2018-19', 'Fall', 'HIST101', 'Adams');
INSERT INTO classes (year, term, courseid, instructor)
		 VALUES ('2017-18', 'Spring', 'HIST102', 'Smith');
INSERT INTO classes (year, term, courseid, instructor)
		 VALUES ('2017-18', 'Spring', 'HIST102', 'Jones');
INSERT INTO classes (year, term, courseid, instructor)
		 VALUES ('2018-19', 'Summer', 'HIST103', 'Adams');
INSERT INTO classes (year, term, courseid, instructor)
		 VALUES ('2018-19', 'Summer', 'HIST103', 'Jones');
/* Create a view to retrieve data from all normalized tables */
CREATE OR REPLACE VIEW original_table
AS SELECT c1.term term, c1.courseid courseid, 
          c2.coursename coursename, c2.credits credits, 
    c1.year year, c2.maxclasssize maxclasssize, 
    i.instructor instructor, i.instdept instdept
     FROM classes c1,
	    courseinfo c2,
	    courses c3,
	    instructors i
    WHERE c1.courseid=c3.courseid
      AND c1.instructor=i.instructor
      AND c2.coursename=c3.coursename;


/* Select from the view */
SELECT *
  FROM original_table
 ORDER BY term, year, instructor;

/* Spool off the capture the output */
SPOOL OFF;
