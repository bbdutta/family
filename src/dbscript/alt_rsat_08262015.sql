USE test;
ALTER TABLE bhn_rsat 

ADD column	(
	have_insurance char(1),
	insurance_type varchar(60)
);

commit;