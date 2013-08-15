delimiter $$

drop procedure if exists `getNextMin`$$

CREATE PROCEDURE `getNextMin`(
IN tableName varchar(255))
begin

SET @sql := CONCAT("select IF(min(",tableName,"_id) is NULL or min(",tableName,"_id) >= 0, -1, min(",tableName,"_id) -1)  as id from ",tableName);
PREPARE stmt FROM @sql;
	EXECUTE stmt;
	
end$$

drop procedure if exists `getNextMinReturn`$$

CREATE PROCEDURE `getNextMinReturn`(
IN tableName varchar(255), OUT idnew INT)
begin
set @c1 = 0;

SET @sql := CONCAT("select IF(min(",tableName,"_id) is NULL or min(",tableName,"_id) >= 0, -1, min(",tableName,"_id) -1)  into @c1 from ",tableName);
PREPARE stmt FROM @sql;
	EXECUTE stmt;
set idnew = @c1; 	
	
end$$


drop procedure if exists `addCvterm`$$

CREATE PROCEDURE `addCvterm`(IN cvidin int, IN cvname varchar(500), IN cvdesc varchar(500))
begin

	call getNextMinReturn('cvterm', @newcvtermid);

	-- CVTERM unique contraint | NAME, CV_ID, IS_OBSOLETE
	IF NOT EXISTS (SELECT 1 FROM cvterm WHERE name=cvname AND cv_id=cvidin AND is_obsolete=0) THEN
		insert into cvterm (cvterm_id, cv_id, name, definition, dbxref_id, is_obsolete, is_relationshiptype) value (@newcvtermid, cvidin, cvname, cvdesc, NULL, 0, 0);
	END IF;

end$$

drop procedure if exists `addCvtermWithID`$$

CREATE PROCEDURE `addCvtermWithID`(IN cvterm_id_v INT, IN cvidin int, IN cvname varchar(500), IN cvdesc varchar(500))
begin
	
	-- CVTERM unique contraint | NAME, CV_ID, IS_OBSOLETE
	IF NOT EXISTS (SELECT 1 FROM cvterm WHERE name=cvname AND cv_id=cvidin AND is_obsolete=0) THEN
		insert into cvterm (cvterm_id, cv_id, name, definition, dbxref_id, is_obsolete, is_relationshiptype) value (cvterm_id_v, cvidin, cvname, cvdesc, NULL, 0, 0);
	END IF;

end$$

drop procedure if exists `addCvtermReturnId`$$

CREATE PROCEDURE `addCvtermReturnId`(IN cvidin int, IN cvname varchar(500), IN cvdesc varchar(500), OUT newcvtermidret INT)
begin

	call getNextMinReturn('cvterm', @newcvtermid);
	-- CVTERM unique contraint | NAME, CV_ID, IS_OBSOLETE
	IF NOT EXISTS (SELECT 1 FROM cvterm WHERE name=cvname AND cv_id=cvidin AND is_obsolete=0) THEN
		insert into cvterm (cvterm_id, cv_id, name, definition, dbxref_id, is_obsolete, is_relationshiptype) value (@newcvtermid, cvidin, cvname, cvdesc, NULL, 0, 0);
	ELSE
		SELECT cvterm_id INTO @newcvtermid FROM cvterm  WHERE name=cvname AND cv_id=cvidin AND is_obsolete=0;
	END IF;

	set newcvtermidret = @newcvtermid; 

end$$

drop procedure if exists `addCvtermRelationship`$$

CREATE PROCEDURE `addCvtermRelationship`(IN typeid int, IN subjectid int, IN objectid int)
begin

	call getNextMinReturn('cvterm_relationship', @newcvtermrelationshipid);
	-- CVTERM_RELATIONSHIP unique constraint | OBJECT_ID, SUBJECT_ID, TYPE_ID
	IF NOT EXISTS (SELECT 1 FROM cvterm_relationship WHERE object_id=objectid AND subject_id=subjectid AND type_id=typeid) THEN
		insert into cvterm_relationship (cvterm_relationship_id, type_id, subject_id, object_id) value (@newcvtermrelationshipid, typeid, subjectid, objectid);
	END IF;


end$$


drop procedure if exists `updateCvterm`$$

CREATE PROCEDURE `updateCvterm`(IN cvtermid int, IN cvname varchar(500), IN cvdesc varchar(500))
begin

	update cvterm
	set name = cvname,
	definition = cvdesc
	where cvterm_id = cvtermid;


end$$

drop procedure if exists `addNdGeolocation`$$

CREATE PROCEDURE `addNdGeolocation`(IN nd_geolocation_id_v int, IN description_v varchar(500))
begin

/* nd_geolocation_id 	description 	latitude 	longitude 	geodetic_datum 	altitude */
insert into  nd_geolocation (nd_geolocation_id, description, latitude, longitude, geodetic_datum, altitude) value (nd_geolocation_id_v, description_v,NULL,NULL,'',NULL);

end$$

drop procedure if exists `addNdExperimentStock`$$

CREATE PROCEDURE `addNdExperimentStock`(IN nd_experiment_stock_id_v int, IN nd_experiment_id_v int, IN stock_id_v int)
begin

/* nd_geolocation_id 	description 	latitude 	longitude 	geodetic_datum 	altitude */
insert into  nd_experiment_stock (nd_experiment_stock_id,nd_experiment_id,stock_id,type_id) value (nd_experiment_stock_id_v, nd_experiment_id_v, stock_id_v, 1000);

end$$

drop procedure if exists `addStock`$$

CREATE PROCEDURE `addStock`(
IN stock_id_in int,
IN v_uniquename varchar(255),
IN dbxref_id varchar(255),
IN name varchar(255),
IN value varchar(255))
begin

-- STOCK unique constraint | ORGANISM_ID, UNIQUENAME, TYPE_ID
-- IF NOT EXISTS (SELECT 1 FROM stock WHERE organism_id IS NULL AND uniquename=v_uniquename AND type_id=8230) THEN
	insert into stock (stock_id, type_id, uniquename, dbxref_id, name, value, is_obsolete) 
	value (stock_id_in, 8230, v_uniquename, dbxref_id, name, value, 0);
-- END IF;

end$$

drop procedure if exists `addNdExperiment`$$

CREATE PROCEDURE `addNdExperiment`(IN nd_experimentid_id_v int, IN nd_geolocation_id_v int, IN type_id_v INT)
begin

insert into   nd_experiment (nd_experiment_id,nd_geolocation_id,type_id) value (nd_experimentid_id_v, nd_geolocation_id_v, type_id_v);


end$$

DROP VIEW IF EXISTS `v_factor`$$
CREATE VIEW v_factor (projectprop_id, project_id, rank, varid, factorid, storedinid, traitid, dtypeid)
AS
SELECT 
    prop.projectprop_id AS projectprop_id
    , prop.project_id AS project_id
    , prop.rank AS rank
    , prop.value AS varid
    , GROUP_CONCAT(
         CASE
           WHEN stinrel.object_id = 1047 AND mfactors.value = '8230' THEN mfactors.projectprop_id
           WHEN stinrel.object_id IN (1010, 1011, 1012) AND mfactors.value = '8005' THEN mfactors.projectprop_id
           WHEN stinrel.object_id IN (1015, 1016, 1017) AND mfactors.value = '8150' THEN mfactors.projectprop_id
           WHEN stinrel.object_id IN (1040, 1041, 1042, 1046, 1047) AND mfactors.value = '8230' THEN mfactors.projectprop_id
           WHEN stinrel.object_id IN (1020, 1021, 1022, 1023, 1024, 1025) AND mfactors.value = '8170' THEN mfactors.projectprop_id
           WHEN stinrel.object_id = 1030 AND mfactors.value IN ('8200', '8380') THEN mfactors.projectprop_id
         END
      ) AS factorid
    , stinrel.object_id AS storedinid
    , traitrel.object_id AS traitid
    , dtyperel.object_id AS dtypeid
  FROM projectprop prop
  INNER JOIN cvterm_relationship stinrel ON stinrel.subject_id = prop.value and stinrel.type_id = 1044
  INNER JOIN cvterm_relationship traitrel on traitrel.subject_id = prop.value and traitrel.type_id = 1200
  INNER JOIN cvterm_relationship dtyperel ON dtyperel.subject_id = prop.value AND dtyperel.type_id = 1105
  LEFT JOIN projectprop mfactors ON mfactors.project_id = prop.project_id AND mfactors.type_id = 1070 
    AND mfactors.value in ('8005', '8150', '8230', '8170', '8200', '8380')
  WHERE prop.type_id = 1070 
    AND stinrel.object_id NOT IN (1043, 1048)
  GROUP BY prop.projectprop_id
$$


DROP VIEW IF EXISTS `v_level`$$
CREATE VIEW v_level (labelid, factorid, levelno, lvalue, dtypeid, storedinid, nd_experiment_id)
AS
SELECT 
stdvar.projectprop_id AS labelId
, stdvar.factorid AS factorId
, CASE
    WHEN stdvar.storedinid IN (1010, 1011, 1012, 1015, 1016, 1017) THEN p.project_id
    WHEN stdvar.storedinid IN (1020, 1021, 1022, 1023, 1024, 1025) THEN geo.nd_geolocation_id
    WHEN stdvar.storedinid = 1030 THEN eprop.nd_experiment_id
    WHEN stdvar.storedinid IN (1040, 1041, 1042, 1046, 1047) THEN stock.stock_id
  END AS levelno
, CASE stdvar.storedinid 
    WHEN 1010 THEN (pval.value)
    WHEN 1011 THEN (p.name)
    WHEN 1012 THEN (p.description)
    WHEN 1015 THEN (pval.value)
    WHEN 1016 THEN (p.name)
    WHEN 1017 THEN (p.description)
    WHEN 1020 THEN (gprop.value)
    WHEN 1021 THEN (geo.description)
    WHEN 1022 THEN (geo.latitude)
    WHEN 1023 THEN (geo.longitude)
    WHEN 1024 THEN (geo.geodetic_datum)
    WHEN 1025 THEN (geo.altitude)
    WHEN 1030 THEN (eprop.value)
    WHEN 1040 THEN (sprop.value)
    WHEN 1041 THEN (stock.uniquename)
    WHEN 1042 THEN (stock.dbxref_id)
    WHEN 1046 THEN (stock.name)
    WHEN 1047 THEN (stock.value)
  END AS lvalue
, stdvar.dtypeid AS dtypeid
, stdvar.storedinid AS storedinid
, exp.nd_experiment_id AS nd_experiment_id
FROM 
v_factor AS stdvar
INNER JOIN project p ON p.project_id = stdvar.project_id
INNER JOIN nd_experiment_project ep ON ep.project_id = p.project_id
INNER JOIN nd_experiment exp ON exp.nd_experiment_id = ep.nd_experiment_id
LEFT JOIN nd_geolocation geo ON geo.nd_geolocation_id = exp.nd_geolocation_id
LEFT JOIN projectprop pval ON pval.type_id = stdvar.varid AND pval.project_id = p.project_id AND pval.rank = stdvar.rank
LEFT JOIN nd_geolocationprop gprop ON gprop.nd_geolocation_id = geo.nd_geolocation_id AND gprop.type_id = stdvar.varid
LEFT JOIN nd_experimentprop eprop ON eprop.nd_experiment_id = exp.nd_experiment_id AND eprop.type_id = stdvar.varid
LEFT JOIN nd_experiment_stock es ON es.nd_experiment_id = exp.nd_experiment_id
LEFT JOIN stock stock ON stock.stock_id = es.stock_id
LEFT JOIN stockprop sprop ON sprop.stock_id = stock.stock_id AND sprop.type_id = stdvar.varid
$$

DROP PROCEDURE IF EXISTS `searchCVTerm`$$

CREATE PROCEDURE `searchCVTerm` (IN cvtermid int, IN cvname varchar(255), IN cvid INT)
  BEGIN

    SET @myQuery = 'select cvterm_id as cvtermid,name as cvname from cvterm';

    SET @myQuery = CONCAT(@myQuery, ' WHERE cv_id = ', cvid);

    IF (cvtermid is not NULL AND cvtermid <> 0) THEN
      SET @myQuery = CONCAT(@myQuery, ' AND (cvterm_id = ', cvtermid);
    END IF;

    IF (cvname IS NOT NULL) THEN
      IF (cvtermid is not NULL AND cvtermid <> 0) THEN
        SET @myQuery = CONCAT(@myQuery, ' OR ');
      ELSE
        SET @myQuery = CONCAT(@myQuery, ' AND (');
      END IF;
      SET @myQuery = CONCAT(@myQuery, 'name like ''%', cvname, '%''');
    END IF;

    SET @myQuery = CONCAT(@myQuery, ') ORDER BY cvterm_id, name');

    PREPARE stmt FROM @myQuery;
    EXECUTE stmt;

  END;
$$

DROP PROCEDURE IF EXISTS `getCVTermByCvid`$$

CREATE PROCEDURE `getCVTermByCvid` (IN cvid INT)
  BEGIN

    SET @myQuery = 'select cvterm_id as cvtermid,name as cvname from cvterm';

    SET @myQuery = CONCAT(@myQuery, ' WHERE cv_id = ', cvid);

    SET @myQuery = CONCAT(@myQuery, ' ORDER BY cvterm_id, name');

    PREPARE stmt FROM @myQuery;
    EXECUTE stmt;

  END;
$$

DROP PROCEDURE IF EXISTS `getStoredInId`$$

CREATE PROCEDURE `getStoredInId`(IN traitid INT, IN scaleid INT, IN methodid INT)
BEGIN

  SELECT stinrel.object_id AS storedinid
  FROM 
    cvterm_relationship stinrel 
    INNER JOIN cvterm_relationship scalerel ON scalerel.subject_id = stinrel.subject_id AND scalerel.type_id = 1220 
    INNER JOIN cvterm_relationship methodrel ON methodrel.subject_id = stinrel.subject_id AND methodrel.type_id = 1210 
    INNER JOIN cvterm_relationship traitrel ON traitrel.subject_id = stinrel.subject_id AND traitrel.type_id = 1200 
  WHERE stinrel.type_id = 1044
    AND traitrel.object_id = traitid
    AND scalerel.object_id = scaleid
    AND methodrel.object_id = methodid
  LIMIT 1
  ;

END$$
