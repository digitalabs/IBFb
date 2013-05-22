delimiter $$

drop procedure if exists addOindex$$

CREATE PROCEDURE addOindex(
IN v_factorid int,
IN v_levelno int)
begin

DECLARE v_nd_experiment_project_id int;
DECLARE v_project_id int;
DECLARE v_nd_experiment_id int;
DECLARE v_etype varchar(10);
DECLARE EXIT HANDLER FOR SQLEXCEPTION ROLLBACK; 

START TRANSACTION;

SELECT project_id INTO v_project_id
FROM projectprop pp
WHERE pp.projectprop_id = v_factorid;

SELECT DISTINCT
CASE
    WHEN stdvar.storedinid IN (1010, 1011, 1012, 1015, 1016, 1017) THEN 'STUDY AND DATASET'
    WHEN stdvar.storedinid IN (1020, 1021, 1022, 1023, 1024, 1025) THEN 'TRIAL ENVIRONMENT'
    WHEN stdvar.storedinid = 1030 THEN 'TRIAL DESIGN'
    WHEN stdvar.storedinid IN (1040, 1041, 1042, 1046, 1047) THEN 'GERMPLASM ENTRY' 
END AS etype INTO v_etype 
FROM v_factor stdvar
WHERE stdvar.factorid = v_factorid
AND stdvar.project_id = v_project_id;

IF ('TRIAL ENVIRONMENT') THEN
	SELECT nd_experiment_id INTO v_nd_experiment_id 
	FROM nd_experiment
	WHERE nd_geolocation_id = v_levelno;
ELSEIF ('TRIAL DESIGN') THEN
	SELECT v_levelno INTO v_nd_experiment_id;
ELSEIF ('GERMPLASM ENTRY') THEN
	SELECT nd_experiment_id INTO v_nd_experiment_id 
	FROM nd_experiment_stock 
	WHERE stock_id = v_levelno;    
ELSE
	CALL getNextMinReturn('nd_experiment',v_nd_experiment_id);
END IF;

CALL getNextMinReturn('nd_experiment_project',v_nd_experiment_project_id);

INSERT INTO nd_experiment_project(nd_experiment_project_id,project_id,nd_experiment_id)
VALUES(v_nd_experiment_project_id,v_project_id,v_nd_experiment_id);

COMMIT;

end$$