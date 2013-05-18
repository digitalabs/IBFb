delimiter $$

drop procedure if exists addOindex$$

CREATE PROCEDURE addOindex(
IN v_ounitid int,
IN v_factorid int,
IN v_levelno int,
IN v_represno int)
begin

DECLARE v_nd_experiment_project_id int;
DECLARE v_project_id int;
DECLARE EXIT HANDLER FOR SQLEXCEPTION ROLLBACK; 

START TRANSACTION;

SELECT project_id INTO v_project_id
FROM projectprop pp
WHERE pp.projectprop_id = v_factorid; 

CALL getNextMinReturn('nd_experiment_project',v_nd_experiment_project_id);

INSERT INTO nd_experiment_project(nd_experiment_project_id,project_id,nd_experiment_id)
VALUES(v_nd_experiment_project_id,v_project_id,v_ounitid);

COMMIT;

end$$



