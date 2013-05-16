delimiter $$

drop procedure if exists `addCvterm`$$

CREATE PROCEDURE `addCvterm`(IN cvidin int, IN cvname varchar(500), IN cvdesc varchar(500))
begin


	insert into cvterm (cv_id, name, definition, dbxref_id, is_obsolete, is_relationshiptype) value (cvidin, cvname, cvdesc, NULL, 0, 0);


end$$

drop procedure if exists `updateCvterm`$$

CREATE PROCEDURE `updateCvterm`(IN cvtermid int, IN cvname varchar(500), IN cvdesc varchar(500))
begin

	update cvterm
	set name = cvname,
	definition = cvdesc
	where cvterm_id = cvtermid


end$$




