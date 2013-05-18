delimiter $$

drop procedure if exists `getTraitList`$$

CREATE PROCEDURE `getTraitList`()
begin
	SELECT DISTINCT
			cvt.cvterm_id AS tid,
			cvt.cvterm_id AS traitid,
			cvt.name AS trname,
			cvt.definition AS trdesc,
			1 AS nstat, 
			grp.name AS traitgroup
	  FROM cvterm cvt
	 INNER JOIN cvterm_relationship gcvr ON gcvr.subject_id = cvt.cvterm_id
	 INNER JOIN cvterm grp ON grp.cvterm_id = gcvr.object_id
	 WHERE gcvr.type_id = 1225 -- get "is a" relationship to get group name
	   AND cvt.cv_id = 1010
	 ORDER BY traitid;
end$$

delimiter $$

DROP PROCEDURE IF EXISTS `getTraitListByTrait` $$

CREATE PROCEDURE `getTraitListByTrait`(IN tid int, IN traitId int, IN traitName varchar(255), IN traitDescription varchar(255), IN traitGroup varchar(255))
BEGIN

SET @mySQL = 'SELECT DISTINCT
      cvt.cvterm_id AS tid,
      cvt.cvterm_id AS traitId,
      cvt.name AS traitName,
      cvt.definition AS traitDescription,
      1 AS nstat,
      grp.name AS traitGroup
    FROM cvterm cvt
      INNER JOIN cvterm_relationship gcvr ON gcvr.subject_id = cvt.cvterm_id
      INNER JOIN cvterm grp ON grp.cvterm_id = gcvr.object_id
    WHERE gcvr.type_id = 1225 AND cvt.cv_id = 1010';

  IF (tid IS NOT NULL) THEN
    set @mySQL = CONCAT(@myQuery, ' AND cvt.cvterm_id = ', tid);
  END IF;

  IF (traitId IS NOT NULL) THEN
    set @mySQL = CONCAT(@mySQL, ' AND cvt.cvterm_id = ', traitId);
  END IF;

  IF (traitName IS NOT NULL) THEN
    set @mySQL = CONCAT(@mySQL, ' AND cvt.name LIKE \'%', traitName, '%\'');
  END IF;

  IF (traitDescription IS NOT NULL) THEN
    set @mySQL = CONCAT(@mySQL, ' AND cvt.definition LIKE \'%', traitDescription, '%\'');
  END IF;

  IF (traitGroup IS NOT NULL) THEN
    set @mySQL = CONCAT(@mySQL, ' AND grp.name LIKE \'%', traitGroup, '%\'');
  END IF;

  set @mySQL = CONCAT(@mySQL, ' ORDER BY traitId');

  PREPARE stmt FROM @mySQL;
  EXECUTE stmt;
  end$$

