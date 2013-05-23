delimiter $$
drop procedure if exists `getListMeasuredIn`$$

CREATE PROCEDURE `getListMeasuredIn` (
IN v_measuredinid int,
IN v_traitid int,
IN v_tmethid int,
IN v_scaleid int,
IN v_central_db_name varchar(20),
IN v_is_local int)
begin

    -- DOES NOT CHECK v_is_local, no info needed from central db ontology
    SET @sql := CONCAT("SELECT cvt.cvterm_id AS measuredinid, ",
                                            "crp.object_id AS traitid, ",
                                            "crs.object_id AS scaleid, ",
                                            "crm.object_id AS tmethid ",
                                            "FROM cvterm cvt ",
                                            "INNER JOIN cvterm_relationship crp ON crp.subject_id = cvt.cvterm_id ",
                                            "INNER JOIN cvterm_relationship crm ON crm.subject_id = cvt.cvterm_id ",
                                            "INNER JOIN cvterm_relationship crs ON crs.subject_id = cvt.cvterm_id ",
                                            "WHERE cvt.cv_id = 1040 ",
                                            "AND crp.type_id = 1200 ",
                                            "AND crm.type_id = 1210 ",
                                            "AND crs.type_id = 1220 ");

    IF (v_measuredinid IS NOT NULL) THEN
            SET @sql = CONCAT(@sql," AND cvt.cvterm_id = ",v_measuredinid);
    END IF;

    IF (v_traitid IS NOT NULL) THEN
            SET @sql = CONCAT(@sql," AND crp.object_id = ",v_traitid);
    END IF;

    IF (v_tmethid IS NOT NULL) THEN
            SET @sql = CONCAT(@sql," AND crm.object_id = ",v_tmethid);
    END IF;

    IF (v_scaleid IS NOT NULL) THEN
            SET @sql = CONCAT(@sql," AND crs.object_id = ",v_scaleid);
    END IF;
		
	
    SET @sql = CONCAT(@sql, " ORDER BY measuredinid; ");
	
    PREPARE stmt FROM @sql;
    EXECUTE stmt;

end$$

drop procedure if exists `getMeasuredinByTraitidScaleidTmethid`$$

CREATE PROCEDURE `getMeasuredinByTraitidScaleidTmethid`(IN traitId int, IN scaleId int, IN tmethId int)
BEGIN

	SELECT
	var.cvterm_id AS measuredinid
	, traitrel.object_id AS traitid
	, scalerel.object_id AS scaleid
	, scalerel.object_id AS standardscale
        , NULL AS report
        , NULL AS formula
	, methrel.object_id AS tmethid
	FROM cvterm var
	INNER JOIN cvterm_relationship traitrel ON traitrel.subject_id = var.cvterm_id AND traitrel.type_id = 1200
	INNER JOIN cvterm_relationship methrel ON methrel.subject_id = var.cvterm_id AND methrel.type_id = 1210
	INNER JOIN cvterm_relationship scalerel ON scalerel.subject_id = var.cvterm_id AND scalerel.type_id = 1220
	WHERE var.cv_id = 1040
	AND traitrel.object_id = traitId
	AND methrel.object_id = tmethId
	AND scalerel.object_id = scaleId
	limit 1;

END$$

drop procedure if EXISTS addMeasuredin$$

CREATE PROCEDURE addMeasuredin(
IN v_traitid int,
iN v_tmethid int,
IN v_scaleid int,
IN v_name varchar(255),
IN v_storedinid int)
begin

DECLARE EXIT HANDLER FOR SQLEXCEPTION ROLLBACK; 

START TRANSACTION;
	-- insert standard variable
	call addCvtermReturnId(1040, v_name, v_name, @newcvtermid);

	-- insert "stored in" relationship
	call addCvtermRelationship(1044, @newcvtermid, v_storedinid);

	-- insert "has property" relationship
	call addCvtermRelationship(1200, @newcvtermid, v_traitid);

	-- insert "has method" relationship
	call addCvtermRelationship(1210, @newcvtermid, v_tmethid);

	-- insert "has scale" relationship
	call addCvtermRelationship(1220, @newcvtermid, v_scaleid);

COMMIT;	
	
end$$
