DELIMITER $$

DROP PROCEDURE IF EXISTS `getLevelsByLabelId`$$

CREATE PROCEDURE `getLevelsByLabelId`(IN p_labelid int, IN isnumeric int, IN iscentral int)
BEGIN
IF iscentral = 1 THEN

    SELECT
      labelid
      , factorid
      , levelno
      , lvalue
    FROM 
       v_level
    WHERE (isnumeric AND dtypeid IN (1110, 1117, 1118)
      OR NOT isnumeric AND dtypeid IN (1128, 1120))
      AND labelid = p_labelid
    ORDER BY levelno ASC
    ;

ELSE

    SELECT
      labelid
      , factorid
      , levelno
      , lvalue
    FROM 
       v_level
    WHERE (isnumeric AND dtypeid IN (1110, 1117, 1118, 1125)
      OR NOT isnumeric AND dtypeid IN (1128, 1120))
      AND labelid = p_labelid
    ORDER BY levelno DESC
    ;


END IF;
END$$


DROP PROCEDURE IF EXISTS `searchLevels`$$

CREATE PROCEDURE `searchLevels`(
  IN labelid int
  , IN levelno int
  , IN factorid int
  , IN lvalue1 double
  , IN lvalue2 varchar(255)
  , IN iscentral int)

BEGIN

  SET @sql := CONCAT("SELECT labelid, factorid, levelno, lvalue from v_level WHERE 1=1 ");

  IF (labelid IS NOT NULL) THEN
    SET @sql = CONCAT(@sql, " AND labelid = ", labelid);
  END IF;

  IF (levelno IS NOT NULL) THEN
    SET @sql = CONCAT(@sql, " AND levelno = ", levelno);
  END IF;

  IF (factorid IS NOT NULL) THEN
    SET @sql = CONCAT(@sql, " AND factorid = ", factorid);
  END IF;
  
  IF (lvalue1 IS NOT NULL) THEN
    SET @sql = CONCAT(@sql, " AND lvalue = ", lvalue1);
  END IF;

  IF (lvalue2 IS NOT NULL) THEN
    SET @sql = CONCAT(@sql, " AND lvalue = ", lvalue2);
  END IF;

  IF iscentral = 1 THEN
    SET @sql = CONCAT(@sql, " ORDER BY labelid, levelno ASC");
  ELSE
    SET @sql = CONCAT(@sql, " ORDER BY labelid, levelno DESC");
  END IF;

  PREPARE stmt FROM @sql;
  EXECUTE stmt;


END$$



