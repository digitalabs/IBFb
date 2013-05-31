delimiter $$

drop procedure if exists `getEffectidsByStudyid`$$

CREATE PROCEDURE `getEffectidsByStudyid`(IN studyid int, IN iscentral int)
begin



IF iscentral = 1 then

	select pr.subject_project_id as effectid
	from project_relationship pr where pr.type_id = 1150
	and pr.object_project_id = studyid
	order by effectid asc;

else

	select pr.subject_project_id as effectid
	from project_relationship pr where pr.type_id = 1150
	and pr.object_project_id = studyid
	order by effectid desc;

	
end if;
end$$

drop procedure if exists `getListSteffect`$$

CREATE PROCEDURE `getListSteffect`(IN studyid int)
begin
	select pr.subject_project_id as effectid, pr.object_project_id as studyid, "" as effectname
	from project_relationship pr where pr.type_id = 1150
	and pr.object_project_id = studyid;

end$$

drop procedure if exists addSteffect$$

CREATE PROCEDURE addSteffect(
IN v_effectid int,
IN v_studyid int,
IN v_effectname varchar(50))
begin

DECLARE v_project_relationship_id int;
DECLARE EXIT HANDLER FOR SQLEXCEPTION ROLLBACK; 

START TRANSACTION;

CALL getNextMinReturn('project',v_effectid);

INSERT INTO project(project_id,name,description)
VALUES(v_effectid,v_effectname,v_effectname);

CALL getNextMinReturn('project_relationship',v_project_relationship_id);

INSERT INTO project_relationship(project_relationship_id,type_id,object_project_id,subject_project_id)
VALUE(v_project_relationship_id,1150,v_studyid,v_effectid);
	
COMMIT;

SELECT v_effectid;

end$$


