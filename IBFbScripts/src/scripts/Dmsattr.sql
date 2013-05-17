DELIMITER $$

DROP PROCEDURE IF EXISTS `getDmsattrByDmsatrecAndDmsatype`$$

CREATE PROCEDURE `getDmsattrByDmsatrecAndDmsatype`(IN dmsatrec int, IN dmsatyp int)
/*  Assumption: DUDFLDS table is retained in the new schema
    dmsatrec = cvterm_id, dmsatyp = 801 for factor, 802 for variate 
    Tested using: 
        CALL getDmsattrByDmsatrecAndDmsatype(8010, 801);
        CALL getDmsattrByDmsatrecAndDmsatype(21746, 802);
*/
BEGIN

    SET @factorDmsAType =  (SELECT fldNo FROM DUDFLDS WHERE ftable = 'dmsattr' AND ftype = 'DMSATYPE' AND fcode = 'FACDES'); -- 801
    SET @variateDmsAType = (SELECT fldNo FROM DUDFLDS WHERE ftable = 'dmsattr' AND ftype = 'DMSATYPE' AND fcode = 'VARDESC'); -- 802

    SET @factorDmsATab = 'FACTOR';
    SET @variateDmsATab = 'VARIATE';

    SET @observationVariate = 1043;
    SET @categoricalVariate = 1048;

    SET @type = (SELECT CASE 
                            WHEN dmsatyp = @factorDmsAType THEN @factorDmsATab
                            WHEN dmsatyp = @variateDmsAType THEN @variateDmsATab
                    END);

    SELECT  DISTINCT null AS dmsatid
            ,dmsatyp
            ,ppType.type AS dmsatab
            ,ppStandardVar.value as dmsatrec   -- factor id / variate id
            ,ppValue.value as dmsatval 
    FROM (SELECT project_id, rank, CASE type_id 
                WHEN  @observationVariate THEN @variateDmsATab
                WHEN @categoricalVariate THEN @variateDmsATab
                ELSE @factorDmsATab
                END AS type
            FROM projectprop) as ppType
            INNER JOIN projectprop ppStandardVar on ppType.project_id = ppStandardVar.project_id 
                            and ppType.rank = ppStandardVar.rank
                            and ppStandardVar.type_id = 1070 
            INNER JOIN projectprop ppValue ON ppType.project_id = ppValue.project_id 
                            and ppType.rank = ppValue.rank 
                            and ppValue.type_id = 1060
            AND ppStandardVar.value = dmsatrec
            AND ppType.type = @type
    ;

END$$



DELIMITER ;
