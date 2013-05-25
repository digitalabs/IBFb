delimiter $$
drop procedure if exists `getListMeasuredIn`$$

CREATE PROCEDURE `getListMeasuredIn` (
IN v_measuredinid int,
IN v_traitid int,
IN v_tmethid int,
IN v_scaleid int)
begin

    -- DOES NOT CHECK v_is_local, no info needed from central db ontology
    SET @sql := CONCAT("SELECT cvt.cvterm_id AS measuredinid, ",
                                            "crp.object_id AS traitid, ",
                                            "crs.object_id AS scaleid, ",
                                            "crm.object_id AS tmethid, ",
                                            "crt.object_id AS storedinid, ",
                                            "if(crt.object_id = 1120, 'C', 'N') AS hasType ",
                                            "FROM cvterm cvt ",
                                            "INNER JOIN cvterm_relationship crp ON crp.subject_id = cvt.cvterm_id ",
                                            "INNER JOIN cvterm_relationship crm ON crm.subject_id = cvt.cvterm_id ",
                                            "INNER JOIN cvterm_relationship crs ON crs.subject_id = cvt.cvterm_id ",
                                            "INNER JOIN cvterm_relationship crt ON crt.subject_id = cvt.cvterm_id ",
                                            "INNER JOIN cvterm_relationship crh ON crh.subject_id = cvt.cvterm_id ",
                                            "WHERE cvt.cv_id = 1040 ",
                                            "AND crp.type_id = 1200 ",
                                            "AND crm.type_id = 1210 ",
                                            "AND crs.type_id = 1220 ",
                                            "AND crt.type_id = 1044 ",
                                            "AND crh.type_id = 1105 ");

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
	, CAST(scalerel.object_id AS CHAR(50)) AS standardscale
	, methrel.object_id AS tmethid
	FROM cvterm var
	INNER JOIN cvterm_relationship traitrel ON traitrel.subject_id = var.cvterm_id AND traitrel.type_id = 1200
	INNER JOIN cvterm_relationship methrel ON methrel.subject_id = var.cvterm_id AND methrel.type_id = 1210
	INNER JOIN cvterm_relationship scalerel ON scalerel.subject_id = var.cvterm_id AND scalerel.type_id = 1220
	WHERE var.cv_id = 1040
	AND traitrel.object_id = traitId
	AND methrel.object_id = tmethId
	AND scalerel.object_id = scaleId
	;

END$$

drop procedure if EXISTS addMeasuredin$$

CREATE PROCEDURE addMeasuredin(
IN v_traitid int,
iN v_tmethid int,
IN v_scaleid int,
IN v_name varchar(255),
IN v_storedinid int,
IN v_hastype varchar(255))
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

        -- insert "has type" relationship
        if(v_hastype = 'C') then
            call addCvtermRelationship(1105, @newcvtermid, 1120);
        else
            call addCvtermRelationship(1105, @newcvtermid, 1110);
        end if;

COMMIT;	
	
end$$


DROP PROCEDURE IF EXISTS `copyCvTermRelationship`$$

CREATE PROCEDURE `copyCvTermRelationship`(IN p_cvTermId int, IN p_type int, IN centralSchema VARCHAR(100))

BEGIN

    IF NOT EXISTS (select 1 FROM cvterm_relationship WHERE subject_id = p_cvTermId AND type_id = p_type) THEN

      SET @sql := CONCAT("INSERT INTO cvterm_relationship (cvterm_relationship_id, type_id, subject_id, object_id)",
	     " SELECT cvterm_relationship_id, type_id, subject_id, object_id ",
         " FROM ", centralSchema, ".cvterm_relationship WHERE subject_id = ", p_cvTermId,
         " AND type_id = ", p_type);

      PREPARE stmt FROM @sql;
      EXECUTE stmt;

    END IF;

END$$

DROP PROCEDURE IF EXISTS `copyMeasuredInFromCentral`$$

CREATE PROCEDURE `copyMeasuredInFromCentral`(IN p_cvTermId int, IN centralSchema VARCHAR(100))

BEGIN

  DECLARE EXIT HANDLER FOR SQLEXCEPTION ROLLBACK; 

  IF p_cvTermId > 0 THEN

    START TRANSACTION;

    -- "has property"
    CALL copyCvTermRelationship(p_cvTermId, 1200, centralSchema);

    -- "has method"
    CALL copyCvTermRelationship(p_cvTermId, 1210, centralSchema);

    -- "has scale"
    CALL copyCvTermRelationship(p_cvTermId, 1220, centralSchema);

    -- "is a"
    CALL copyCvTermRelationship(p_cvTermId, 1225, centralSchema);

    -- "stored in"
    CALL copyCvTermRelationship(p_cvTermId, 1044, centralSchema);

    -- "has type"
    CALL copyCvTermRelationship(p_cvTermId, 1105, centralSchema);

    -- "has value"
    CALL copyCvTermRelationship(p_cvTermId, 1190, centralSchema);

    COMMIT;
  END IF;

END$$


