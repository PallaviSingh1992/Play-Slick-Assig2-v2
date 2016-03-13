# --- !Ups
CREATE TABLE "user" ("id" SERIAL PRIMARY KEY ,"name" varchar(200) NOT NULL,"email" varchar(200) NOT NULL, "mobile" varchar(200) NOT NULL,"password" varchar(200) NOT NULL);

INSERT INTO "user" values (1,'himani', 'himani@knoldus.in','9873215276','himani');
INSERT INTO "user" values (2,'pallavi', 'pallavi@knoldus.in','9873664476','pallavi');

CREATE TABLE "assignment"  ("id" int ,"name" varchar(200),"marks" int NOT NULL,"remarks" varchar(200) NOT NULL );
INSERT INTO "assignment" values (1,'scala',7,'no remark');
INSERT INTO "assignment" values (2,'play',6,'no remark');

CREATE TABLE "award"  ("id" int,"name" varchar(200) ,"details" varchar(200));
INSERT INTO "award" values(1,'best programmer','school level');
INSERT INTO "award" values(2,'best programmer','school level');

CREATE TABLE "language"  ("id" int,"name" varchar(200) ,"fluency" varchar(200));
INSERT INTO "language" values(1,'hindi','advanced');

CREATE TABLE "proglanguage"  ("id" int,"name" varchar(200));
INSERT INTO "proglanguage" values(1,'c++');

#---!Downs
DROP TABLE "user";
