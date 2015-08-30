ALTER TABLE bhn_rsat 

ADD column	(
	admission_date DATE,
	orientation_date DATE,
	orientation_facility varchar(100),
	program_completion_date DATE,
	no_of_urine_test SMALLINT(3),
	agencies_assisted_client varchar(200)
);