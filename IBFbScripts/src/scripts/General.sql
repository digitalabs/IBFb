delimiter $$

drop procedure if exists `getNextMin`$$

CREATE PROCEDURE `getNextMin`(
IN tableName varchar(255))
begin

SET @sql := CONCAT("select IF(min(",tableName,"_id) is NULL, -1, min(",tableName,"_id) -1)  as id from ",tableName);
PREPARE stmt FROM @sql;
	EXECUTE stmt;
	
end$$

drop procedure if exists `getNextMinReturn`$$

CREATE PROCEDURE `getNextMinReturn`(
IN tableName varchar(255), OUT idnew INT)
begin
set @c1 = 0;
SET @sql := CONCAT("select IF(min(",tableName,"_id) is NULL, -1, min(",tableName,"_id) -1) into @c1 from ",tableName);
PREPARE stmt FROM @sql;
	EXECUTE stmt;
set idnew = @c1; 	
	
end$$

drop procedure if exists `addCvterm`$$

CREATE PROCEDURE `addCvterm`(IN cvidin int, IN cvname varchar(500), IN cvdesc varchar(500))
begin

	call getNextMinReturn('cvterm', @newcvtermid);
	insert into cvterm (cvterm_id, cv_id, name, definition, dbxref_id, is_obsolete, is_relationshiptype) value (@newcvtermid, cvidin, cvname, cvdesc, NULL, 0, 0);


end$$

drop procedure if exists `addCvtermReturnId`$$

CREATE PROCEDURE `addCvtermReturnId`(IN cvidin int, IN cvname varchar(500), IN cvdesc varchar(500), OUT newcvtermidret INT)
begin

	call getNextMinReturn('cvterm', @newcvtermid);
	insert into cvterm (cvterm_id, cv_id, name, definition, dbxref_id, is_obsolete, is_relationshiptype) value (@newcvtermid, cvidin, cvname, cvdesc, NULL, 0, 0);
	set newcvtermidret = @newcvtermid; 

end$$

drop procedure if exists `addCvtermRelationship`$$

CREATE PROCEDURE `addCvtermRelationship`(IN typeid int, IN subjectid int, IN objectid int)
begin

	call getNextMinReturn('cvterm_relationship', @newcvtermrelationshipid);
	insert into cvterm_relationship (cvterm_relationship_id, type_id, subject_id, object_id) value (@newcvtermrelationshipid, typeid, subjectid, objectid);


end$$


drop procedure if exists `updateCvterm`$$

CREATE PROCEDURE `updateCvterm`(IN cvtermid int, IN cvname varchar(500), IN cvdesc varchar(500))
begin

	update cvterm
	set name = cvname,
	definition = cvdesc
	where cvterm_id = cvtermid;


end$$