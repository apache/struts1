create table REGISTRATION (

	ID int not null primary key,
	FIRST_NAME varchar(30) NOT NULL,
	LAST_NAME varchar(60) NOT NULL unique,
	ADDR varchar(120) NULL,
        CITY varchar(50) NULL,
        STATE_PROV varchar(50) NULL,
        ZIP_POSTAL varchar(20) NULL,
        PHONE varchar(30) NULL,
        EMAIL varchar(120) NOT NULL,
        B_ACTIVE NUMBER(1,0) NOT NULL,
	DATE_CREATED datetime NULL,
	DATE_MODIFIED datetime NULL, 
	USER_NAME varchar(50) NULL 

)