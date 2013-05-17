DELIMITER $$

DROP PROCEDURE IF EXISTS `getLevelsByLabelId`$$

CREATE PROCEDURE `getLevelsByLabelId`(IN p_labelid int, IN isnumeric int, IN iscentral int)
BEGIN
IF iscentral = 1 THEN

	SELECT
	  labelid
	  , factorId
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
	  , factorId
      , levelno
      , lvalue
	FROM 
       v_level
    WHERE (isnumeric AND dtypeid IN (1110, 1117, 1118)
      OR NOT isnumeric AND dtypeid IN (1128, 1120))
      AND labelid = p_labelid
  	ORDER BY levelno DESC
	;


END IF;
END$$

