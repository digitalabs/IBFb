delimiter $$

drop procedure if exists `getTraitGroups`$$

CREATE PROCEDURE `getTraitGroups`()
begin
	SELECT DISTINCT trg.name AS traitgroup
	  FROM cvterm trg
	 WHERE EXISTS (
			SELECT NULL
			  FROM cvterm cvt
	         INNER JOIN cvterm_relationship isa ON isa.subject_id = cvt.cvterm_id
			 WHERE cvt.cv_id = 1010
	           AND isa.type_id = 1225
	           AND isa.object_id = trg.cvterm_id
		);
end$$

drop procedure if exists `getTraitsById`$$

CREATE PROCEDURE `getTraitsById` (IN v_traitid int)
begin
	SELECT DISTINCT
			cvt.cvterm_id AS tid,
			cvt.cvterm_id AS traitid,
			cvt.name AS trname,
			cvt.definition AS trdesc,
			1 AS tnstat, 
			grp.name AS traitgroup
	  FROM cvterm cvt
	 INNER JOIN cvterm_relationship gcvr ON gcvr.subject_id = cvt.cvterm_id
	 INNER JOIN cvterm grp ON grp.cvterm_id = gcvr.object_id
	 WHERE gcvr.type_id = 1225 -- get "is a" relationship to get group name
	   AND cvt.cv_id = 1010
	   AND cvt.cvterm_id = v_traitid
	 ORDER BY traitid;
end$$

DROP PROCEDURE IF EXISTS `updateTraits`$$

CREATE PROCEDURE `updateTraits` (
IN v_tid int,
IN v_trname varchar(200),
IN v_definition varchar(255),
IN v_nstat int,
IN v_traitgroup varchar(200)
)

begin

UPDATE cvterm cvt
INNER JOIN cvterm_relationship cvr ON cvr.object_id = cvt.cvterm_id
INNER JOIN cvterm_relationship gcvr ON gcvr.subject_id = cvt.cvterm_id
INNER JOIN cvterm grp ON grp.cvterm_id = gcvr.object_id
SET
cvt.name = v_trname,
cvt.definition = v_definition,
grp.name = v_traitgroup
WHERE gcvr.type_id = 1225 -- get "is a" relationship to get group name
AND cvr.type_id = 1200 -- get "has property" relationships
AND cvt.cvterm_id = v_tid;

end$$


drop procedure if exists `addTraits`$$

CREATE PROCEDURE `addTraits` (IN trname varchar(255), IN trdesc varchar(255), IN traitgroup varchar(255))
begin
	-- add cvterm - IN cvidin int, IN cvname varchar(500), IN cvdesc varchar(500), OUT newcvtermidret INT
	call addCvtermReturnId(1010, trname, trdesc, @newcvtermid);
	call addCvtermReturnId(1000, traitgroup, traitgroup, @newcvtermidgroup); --group
	
	-- add cvterm relationship --IN typeid int, IN subjectid int, IN objectid int
	--call addCvtermRelationship(1200,?subjectId?,@newcvtermid);
	-- add cvterm relationship	
	--call addCvtermRelationship(1225,@newcvtermid,@newcvtermidgroup);
end$$