delimiter $$

drop procedure if exists `addOrUpdateStudy`$$

CREATE PROCEDURE `addOrUpdateStudy`(
IN studyid int,
IN sname varchar(50), 
IN title varchar(255), 
IN objectiv varchar(255),
IN investid int,
IN stype varchar(1),
IN sdate int,
IN edate int,
IN userid int,
IN sstatus int,
IN shierarchy int)
begin

/*INSERT INTO project(project_id,name,description)
VALUES(studyid,sname,title);*/

end$$
