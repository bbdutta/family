DROP TABLE user;
DROP TABLE role;
DROP TABLE program;
DROP TABLE user_role;
DROP TABLE user_program;
DROP TABLE client;
DROP TABLE client_program;
DROP TABLE service;
DROP TABLE goal;
DROP TABLE client_goal_service;
DROP TABLE goal_service;
DROP TABLE client_employment;

CREATE TABLE user (
	user_id INT(11) NOT NULL AUTO_INCREMENT, 
	username VARCHAR(20), 
	password VARCHAR(20), 
	first_name VARCHAR(20), 
	middle_name VARCHAR(20),
	last_name VARCHAR(20), 
	phone_number VARCHAR(20), 
	email VARCHAR(100),
	PRIMARY KEY(user_id),
	UNIQUE(username)
);

CREATE TABLE role (
	role_id INT(11) NOT NULL AUTO_INCREMENT, 
	role_name VARCHAR(20),
	PRIMARY KEY(role_id),
	UNIQUE(role_name)
);

CREATE TABLE program (
	program_id INT(11) NOT NULL AUTO_INCREMENT, 
	program_name VARCHAR(100),
	PRIMARY KEY(program_id),
	UNIQUE(program_name)
);

CREATE TABLE user_role (
	user_id INT(11), 
	role_id INT(11)
);

CREATE TABLE user_program (
	user_id INT(11), 
	program_id INT(11)
);

CREATE TABLE client (
	client_id INT(11) NOT NULL AUTO_INCREMENT, 
	inmate_number INT(11), 
	first_name VARCHAR(20), 
	middle_name VARCHAR(20),
	last_name VARCHAR(20), 
	gender CHAR(1), 
	dob DATE,
	race VARCHAR(50),
	educational_level VARCHAR(100),
	PRIMARY KEY(client_id),
	UNIQUE(inmate_number)
);

CREATE TABLE client_program (
	client_id INT(11), 
	program_id INT(11), 
	date_admitted DATE,
	date_discharged DATE,
	tanf_eligible CHAR(1), 
	referral_source VARCHAR(60),
	length_of_stay INT(11),
	attended_all_appts CHAR(1),
	client_housing VARCHAR(60),
	num_drug_test INT(11),
	drug_test_monthly CHAR(1),
	num_pos_durg_test INT(11),
	discharge_reason VARCHAR(60),
	dismissal_reason VARCHAR(60),
	discharge_plan CHAR(1),
	community_linkages CHAR(1),
	educational_level VARCHAR(100),
	treatment_modality VARCHAR(100),
	medication_assisted VARCHAR(100),
	validated_treatment CHAR(1),
	PRIMARY KEY(client_id, program_id)
);

CREATE TABLE service (
	service_id INT(11) NOT NULL AUTO_INCREMENT, 
	service_name VARCHAR(100),
	PRIMARY KEY(service_id)
);

CREATE TABLE goal (
	goal_id INT(11) NOT NULL AUTO_INCREMENT, 
	goal_name VARCHAR(100),
	PRIMARY KEY(goal_id),
	UNIQUE(goal_name)
);


CREATE TABLE client_goal_service (
	client_id INT(11), 
	program_id INT(11),
	goal_id INT(11), 
	service_id INT(11),
	PRIMARY KEY(client_id, program_id, goal_id, service_id)
);

CREATE TABLE goal_service (
	goal_service_id INT(11), 
	goal_id INT(11), 
	service_id INT(11),
	PRIMARY KEY(goal_service_id),
	UNIQUE(goal_id, service_id)
);
/*
CREATE TABLE client_goal_service (
	client_employment_goal_service_id INT(11),
	client_employment_id INT(11),
	goal_service_id INT(11), 
	PRIMARY KEY(client_employment_goal_service_id)
);
*/
CREATE TABLE client_employment (
	client_employment_id INT(11) NOT NULL AUTO_INCREMENT, 
	client_id INT(11), 
	program_id INT(11),
	needed_benefits CHAR(1),
	applied_benefits CHAR(1),
	received_benefits CHAR(1),
	needed_id CHAR(1),
	applied_id CHAR(1),
	received_id CHAR(1),
	employer_name varchar(50),
	is_employed CHAR(2),
	date_of_hire DATE,
	is_fulltime CHAR(1),
	is_parttime CHAR(1),
	hourly_rate DECIMAL(7,2),
	job_retention_30 CHAR(1),
	job_retention_60 CHAR(1),
	job_retention_90 CHAR(1),
	job_retention_90_plus CHAR(1),
	social_reunification CHAR(1),
	enrolled_job_readiness CHAR(1),
	completed_job_rediness CHAR(1),
	PRIMARY KEY(client_employment_id),
	UNIQUE(client_id, program_id)
);

insert into user (username, password, first_name, last_name) values ('admin', 'admin', 'Admin', 'Admin');
insert into user (username, password, first_name, last_name) values ('fs', 'fs', 'Fresh', 'Start');
insert into user (username, password, first_name, last_name) values ('bhn', 'bhn', 'Behavior', 'Health');
insert into user (username, password, first_name, last_name) values ('rpt', 'rpt', 'Report', 'View');

insert into role (role_name) values ('ADMIN');
insert into role (role_name) values ('DATA_ENTRY');
insert into role (role_name) values ('REPORT');

insert into user_role values (1, 1);
insert into user_role values (2, 2);
insert into user_role values (3, 2);
insert into user_role values (4, 3);

insert into program (program_name) values ('Fresh Start');
insert into program (program_name) values ('Bhehavior Health Network');

insert into user_program values (2, 1);
insert into user_program values (3, 2);


INSERT INTO goal (goal_name) VALUES ( 'Safe & Securing Housing');
INSERT INTO goal (goal_name) VALUES ( 'Sobriety');
INSERT INTO goal (goal_name) VALUES ( 'Mental Health');
INSERT INTO goal (goal_name) VALUES ( 'Employment');
INSERT INTO goal (goal_name) VALUES ( 'Education');
INSERT INTO goal (goal_name) VALUES ( 'test/Social Supports');
INSERT INTO goal (goal_name) VALUES ( 'Self-Sufficiency/Basic needs');


INSERT INTO service (service_id,service_name) VALUES (1, 'FS Enterprise House'); 
INSERT INTO service (service_id,service_name) VALUES (2, 'Housing Search Assistance');
INSERT INTO service (service_id,service_name) VALUES (3, 'Referral to Transitional/Sober Housing'); 
INSERT INTO service (service_id,service_name) VALUES (4, 'Rental Assistance/Subsidies');
INSERT INTO service (service_id,service_name) VALUES (5, 'Housing Placement');
INSERT INTO service (service_id,service_name) VALUES (6, 'Other/Write-In');

INSERT INTO service (service_id,service_name) VALUES (7, 'Referred to BHN');
INSERT INTO service (service_id,service_name) VALUES (8, 'Substance Abuse Assessment');
INSERT INTO service (service_id,service_name) VALUES (9, 'IOP (Referral)');
INSERT INTO service (service_id,service_name) VALUES (10, 'In-Patient (Referral)');
INSERT INTO service (service_id,service_name) VALUES (11, 'NA/AA');
INSERT INTO service (service_id,service_name) VALUES (12, 'Relapse Prevention Groups'); 
INSERT INTO service (service_id,service_name) VALUES (13, 'Urinalysis');
INSERT INTO service (service_id,service_name) VALUES (14, 'Med. Assisted Treatment');
INSERT INTO service (service_id,service_name) VALUES (15, 'Other/Write-In');

INSERT INTO service (service_id,service_name) VALUES (16, 'Referred to BHN');
INSERT INTO service (service_id,service_name) VALUES (17, 'Mental Health Evaluation');
INSERT INTO service (service_id,service_name) VALUES (18, 'Psychiatric Evaluation (Referral)');
INSERT INTO service (service_id,service_name) VALUES (19, 'Anger Management');
INSERT INTO service (service_id,service_name) VALUES (20, 'Individual Counseling');
INSERT INTO service (service_id,service_name) VALUES (21, 'Couples Counseling');
INSERT INTO service (service_id,service_name) VALUES (22, 'Group Treatment');
INSERT INTO service (service_id,service_name) VALUES (23, 'Other/Write-In');

INSERT INTO service (service_id,service_name) VALUES (24, 'Community Service');
INSERT INTO service (service_id,service_name) VALUES (25, 'Employability Skills Training');
INSERT INTO service (service_id,service_name) VALUES (26, 'Vocational Training (Referral)');
INSERT INTO service (service_id,service_name) VALUES (27, 'Employment Group');
INSERT INTO service (service_id,service_name) VALUES (28, 'One-Stop Orientation');
INSERT INTO service (service_id,service_name) VALUES (29, 'Resume Development');
INSERT INTO service (service_id,service_name) VALUES (30, 'Mock Interviewing');
INSERT INTO service (service_id,service_name) VALUES (31, 'Job Search/Job Development'); 
INSERT INTO service (service_id,service_name) VALUES (32, 'Job Retention Support');
INSERT INTO service (service_id,service_name) VALUES (33, 'Career Interest Assessment'); 
INSERT INTO service (service_id,service_name) VALUES (34, 'Other/Write-In');

INSERT INTO service (service_id,service_name) VALUES (35, 'Literacy & Numeracy Assessment');
INSERT INTO service (service_id,service_name) VALUES (36, 'Literacy/Numeracy Remediation');
INSERT INTO service (service_id,service_name) VALUES (37, 'GED Tutoring and Testing');
INSERT INTO service (service_id,service_name) VALUES (38, 'Other Academic Tutoring');
INSERT INTO service (service_id,service_name) VALUES (39, 'Assistance with College Apps & Financial Aid'); 
INSERT INTO service (service_id,service_name) VALUES (40, 'Other/Write-In');

INSERT INTO service (service_id,service_name) VALUES (41, 'Beacon Mentoring/Support Group'); 
INSERT INTO service (service_id,service_name) VALUES (42, 'Mentoring Services for Children');
INSERT INTO service (service_id,service_name) VALUES (43, 'Life Skills Group');
INSERT INTO service (service_id,service_name) VALUES (44, 'Fatherhood/Parenting Groups'); 
INSERT INTO service (service_id,service_name) VALUES (45, 'Connecting to Faith Communities'); 
INSERT INTO service (service_id,service_name) VALUES (46, 'Connecting to Positive Social/Recreational Opportunities');
INSERT INTO service (service_id,service_name) VALUES (47, 'Other/Write-In');

INSERT INTO service (service_id,service_name) VALUES (48, 'Support in Obtaining Full Identification');
INSERT INTO service (service_id,service_name) VALUES (49, 'Assistance with Entitlement/Benefits Applications');
INSERT INTO service (service_id,service_name) VALUES (50, 'Child Support Modifications');
INSERT INTO service (service_id,service_name) VALUES (51, 'Cell Phone/Computer/Email Access and Usage'); 
INSERT INTO service (service_id,service_name) VALUES (52, 'Other/Write-In');

INSERT INTO goal_service(goal_service_id,goal_id,service_id) VALUES (1,1,1);
INSERT INTO goal_service(goal_service_id,goal_id,service_id) VALUES (2,1,2);
INSERT INTO goal_service(goal_service_id,goal_id,service_id) VALUES (3,1,3);
INSERT INTO goal_service(goal_service_id,goal_id,service_id) VALUES (4,1,4);
INSERT INTO goal_service(goal_service_id,goal_id,service_id) VALUES (5,1,5);
INSERT INTO goal_service(goal_service_id,goal_id,service_id) VALUES (6,1,6);
INSERT INTO goal_service(goal_service_id,goal_id,service_id) VALUES (7,2,7);
INSERT INTO goal_service(goal_service_id,goal_id,service_id) VALUES (8,2,8);
INSERT INTO goal_service(goal_service_id,goal_id,service_id) VALUES (9,2,9);
INSERT INTO goal_service(goal_service_id,goal_id,service_id) VALUES (10,2,10);
INSERT INTO goal_service(goal_service_id,goal_id,service_id) VALUES (11,2,11);
INSERT INTO goal_service(goal_service_id,goal_id,service_id) VALUES (12,2,12);
INSERT INTO goal_service(goal_service_id,goal_id,service_id) VALUES (13,2,13);
INSERT INTO goal_service(goal_service_id,goal_id,service_id) VALUES (14,2,14);
INSERT INTO goal_service(goal_service_id,goal_id,service_id) VALUES (15,2,15);
INSERT INTO goal_service(goal_service_id,goal_id,service_id) VALUES (16,3,16);
INSERT INTO goal_service(goal_service_id,goal_id,service_id) VALUES (17,3,17);
INSERT INTO goal_service(goal_service_id,goal_id,service_id) VALUES (18,3,18);
INSERT INTO goal_service(goal_service_id,goal_id,service_id) VALUES (19,3,19);
INSERT INTO goal_service(goal_service_id,goal_id,service_id) VALUES (20,3,20);
INSERT INTO goal_service(goal_service_id,goal_id,service_id) VALUES (21,3,21);
INSERT INTO goal_service(goal_service_id,goal_id,service_id) VALUES (22,3,22);
INSERT INTO goal_service(goal_service_id,goal_id,service_id) VALUES (23,3,23);
INSERT INTO goal_service(goal_service_id,goal_id,service_id) VALUES (24,4,24);
INSERT INTO goal_service(goal_service_id,goal_id,service_id) VALUES (25,4,25);
INSERT INTO goal_service(goal_service_id,goal_id,service_id) VALUES (26,4,26);
INSERT INTO goal_service(goal_service_id,goal_id,service_id) VALUES (27,4,27);
INSERT INTO goal_service(goal_service_id,goal_id,service_id) VALUES (28,4,28);
INSERT INTO goal_service(goal_service_id,goal_id,service_id) VALUES (29,4,29);
INSERT INTO goal_service(goal_service_id,goal_id,service_id) VALUES (30,4,30);
INSERT INTO goal_service(goal_service_id,goal_id,service_id) VALUES (31,4,31);
INSERT INTO goal_service(goal_service_id,goal_id,service_id) VALUES (32,4,32);
INSERT INTO goal_service(goal_service_id,goal_id,service_id) VALUES (33,4,33);
INSERT INTO goal_service(goal_service_id,goal_id,service_id) VALUES (34,4,34);
INSERT INTO goal_service(goal_service_id,goal_id,service_id) VALUES (35,5,35);
INSERT INTO goal_service(goal_service_id,goal_id,service_id) VALUES (36,5,36);
INSERT INTO goal_service(goal_service_id,goal_id,service_id) VALUES (37,5,37);
INSERT INTO goal_service(goal_service_id,goal_id,service_id) VALUES (38,5,38);
INSERT INTO goal_service(goal_service_id,goal_id,service_id) VALUES (39,5,39);
INSERT INTO goal_service(goal_service_id,goal_id,service_id) VALUES (40,5,40);
INSERT INTO goal_service(goal_service_id,goal_id,service_id) VALUES (41,6,41);
INSERT INTO goal_service(goal_service_id,goal_id,service_id) VALUES (42,6,42);
INSERT INTO goal_service(goal_service_id,goal_id,service_id) VALUES (43,6,43);
INSERT INTO goal_service(goal_service_id,goal_id,service_id) VALUES (44,6,44);
INSERT INTO goal_service(goal_service_id,goal_id,service_id) VALUES (45,6,45);
INSERT INTO goal_service(goal_service_id,goal_id,service_id) VALUES (46,6,46);
INSERT INTO goal_service(goal_service_id,goal_id,service_id) VALUES (47,6,47);
INSERT INTO goal_service(goal_service_id,goal_id,service_id) VALUES (48,7,48);
INSERT INTO goal_service(goal_service_id,goal_id,service_id) VALUES (49,7,49);
INSERT INTO goal_service(goal_service_id,goal_id,service_id) VALUES (50,7,50);
INSERT INTO goal_service(goal_service_id,goal_id,service_id) VALUES (51,7,51);
INSERT INTO goal_service(goal_service_id,goal_id,service_id) VALUES (52,7,52);

