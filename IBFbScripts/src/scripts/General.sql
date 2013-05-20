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

drop procedure if exists `addNdGeolocation`$$

CREATE PROCEDURE `addNdGeolocation`(IN nd_geolocation_id_v int)
begin

/* nd_geolocation_id 	description 	latitude 	longitude 	geodetic_datum 	altitude */
insert into  nd_geolocation (nd_geolocation_id, description, latitude, longitude, geodetic_datum, altitude) value (nd_geolocation_id_v, '',NULL,NULL,'',NULL);

end$$

drop procedure if exists `addStock`$$

CREATE PROCEDURE `addStock`(IN stock_id_in int)
begin

insert into stock (stock_id, type_id, is_obsolete) value (stock_id_in, 8300,0);

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
  GROUP BY prop.projectprop_id
$$


DROP VIEW IF EXISTS `v_level`$$
CREATE VIEW v_level (labelid, factorid, levelno, lvalue, dtypeid, storedinid)
AS
SELECT DISTINCT
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
FROM 
v_factor AS stdvar
INNER JOIN project p ON p.project_id = stdvar.project_id
INNER JOIN nd_experiment_project ep ON ep.project_id = p.project_id
INNER JOIN nd_experiment exp ON exp.nd_experiment_id = ep.nd_experiment_id
INNER JOIN nd_geolocation geo ON geo.nd_geolocation_id = exp.nd_geolocation_id
LEFT JOIN projectprop pval ON pval.type_id = stdvar.varid AND pval.project_id = p.project_id AND pval.rank = stdvar.rank
LEFT JOIN nd_geolocationprop gprop ON gprop.nd_geolocation_id = geo.nd_geolocation_id AND gprop.type_id = stdvar.varid
LEFT JOIN nd_experimentprop eprop ON eprop.nd_experiment_id = exp.nd_experiment_id AND eprop.type_id = stdvar.varid
LEFT JOIN nd_experiment_stock es ON es.nd_experiment_id = exp.nd_experiment_id
LEFT JOIN stock stock ON stock.stock_id = es.stock_id
LEFT JOIN stockprop sprop ON sprop.stock_id = stock.stock_id AND sprop.type_id = stdvar.varid
$$