DELIMITER $$

CREATE TABLE IF NOT EXISTS `dmsattr` (
  `dmsatid` int(11) NOT NULL DEFAULT '0',
  `dmsatype` int(11) DEFAULT '0',
  `dmsatab` varchar(10) NOT NULL DEFAULT '-',
  `dmsatrec` int(11) DEFAULT '0',
  `dmsatval` varchar(255) NOT NULL DEFAULT '-',
  PRIMARY KEY (`dmsatid`),
  KEY `dmsattr_idx01` (`dmsatid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8$$

DROP PROCEDURE IF EXISTS `exportProjectPropEntriesToDmsattr`$$

CREATE PROCEDURE `exportProjectPropEntriesToDmsattr`()
BEGIN


-- START TRANSACTION;

    INSERT INTO dmsattr(dmsatid, dmsatype, dmsatab, dmsatrec, dmsatval)
    SELECT  DISTINCT ppStandardVar.projectprop_id AS dmsatid
          , ppType.type AS dmsatype
        ,ppType.tab AS dmsatab
        ,ppStandardVar.value as dmsatrec   -- factor id / variate id
        ,ppValue.value as dmsatval
    FROM (SELECT project_id, rank, CASE type_id 
            WHEN 1043 THEN 802
            WHEN 1048 THEN 802
            ELSE 801
            END AS type
            ,CASE type_id 
            WHEN 1043 THEN 'VARIATE'
            WHEN 1048 THEN 'VARIATE'
            ELSE 'FACTOR'
            END AS tab
            FROM projectprop) as ppType
        INNER JOIN projectprop ppStandardVar on ppType.project_id = ppStandardVar.project_id 
                        AND ppType.rank = ppStandardVar.rank
                        AND ppStandardVar.type_id = 1070 
        INNER JOIN projectprop ppValue ON ppType.project_id = ppValue.project_id 
                        AND ppType.rank = ppValue.rank 
                        AND ppValue.type_id = 1060
    ;

-- COMMIT;

END$$

DROP PROCEDURE IF EXISTS `getDmsattrByDmsatrecAndDmsatype`$$

CREATE PROCEDURE `getDmsattrByDmsatrecAndDmsatype`(
         IN v_dmsatrec INT
        ,IN v_dmsatype INT
        ,IN v_islocal INT
        )
/*  v_dmsatrec = cvterm_id, v_dmsatype = 801 for factor, 802 for variate 
    Tested in central using: 
        CALL getDmsattrByDmsatrecAndDmsatype(8010, 801, 0);
        CALL getDmsattrByDmsatrecAndDmsatype(21746, 802, 0);
*/
BEGIN

SET @v_dmsatrec = v_dmsatrec;

SET @type = (SELECT CASE 
                        WHEN v_dmsatype = 801 THEN 'FACTOR'
                        WHEN v_dmsatype = 802 THEN 'VARIATE'
                END);

IF (v_islocal = 1) THEN
    SET @sortOrder =  ' ASC ';
ELSE 
    SET @sortOrder = ' DESC ';
END IF; 

SET @sql = CONCAT("
	SELECT  DISTINCT ppStandardVar.projectprop_id AS dmsatid "
    ,",",v_dmsatype
    ,"    ,ppType.type AS dmsatab
        ,ppStandardVar.value as dmsatrec   -- factor id / variate id
        ,ppValue.value as dmsatval
    FROM (SELECT project_id, rank, CASE type_id 
            WHEN 1043 THEN 'VARIATE'
            WHEN 1048 THEN 'VARIATE'
            ELSE 'FACTOR'
            END AS type
            FROM projectprop) as ppType
        INNER JOIN projectprop ppStandardVar on ppType.project_id = ppStandardVar.project_id 
                        AND ppType.rank = ppStandardVar.rank
                        AND ppStandardVar.type_id = 1070 
        INNER JOIN projectprop ppValue ON ppType.project_id = ppValue.project_id 
                        AND ppType.rank = ppValue.rank 
                        AND ppValue.type_id = 1060
        WHERE ppStandardVar.value = ?
                        AND ppType.type = ?
        ORDER BY dmsatid "
    , @sortOrder
    ,"LIMIT 1");

	PREPARE stmt FROM @sql;
	EXECUTE stmt USING @v_dmsatrec, @type;

END$$

DELIMITER ;


