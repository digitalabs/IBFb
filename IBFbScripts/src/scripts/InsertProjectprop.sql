
delimiter $$
drop procedure if exists `InsertProjectprop`$$
create procedure InsertProjectprop(pid int, label varchar(255), description varchar(255), 
                                    label_value varchar(255), cid int, out new_ppid int, out new_rank int, 
									out retcode int, out error_msg varchar(255))
begin

main:begin

  declare i, max_rank, stored_id, cvid int default 0;
  declare var_type varchar(20);
  declare found boolean default false;

  set new_ppid = 0;
  set new_rank = 0;

  if (isnull(label) or length(label)=0) then
      set retcode = -1;
      set error_msg = concat("Variable name cannot be empty");
      leave main;
  end if;

  set found = (select count(*) from cvterm where cvterm_id = cid);
  if (not found) then
	 set retcode = -1;
     set error_msg = concat("Standard variable (",cid,") not found in ontology");
     leave main;
  end if;

  set cvid = (select cv_id from cvterm where cvterm_id = cid);
  if (cvid != 1040) then
	 set retcode = -1;
     set error_msg = concat("The cvterm_id (",cid,") associated with '",label,"' is not a standard variable (cv = 1040)");
     leave main;
  end if;

  select stored_in, type from standard_variable_details where cvterm_id = cid into stored_id, var_type; 
  if (var_type != 'STUDY' and length(label_value) > 0) then
	 set retcode = -2;
     set error_msg = concat("Non study level labels ('",var_type,"') cannot set a value '",label_value,"' in the PROJECTPROP table");
     leave main;
  end if;

  set max_rank = (select max(rank) from projectprop where project_id = pid);
  set max_rank = max_rank + 1;
  
  IF NOT EXISTS (SELECT 1 FROM projectprop WHERE project_id=pid AND type_id=stored_id AND rank=max_rank) THEN
    insert into projectprop (project_id, type_id, value, rank)
    values (pid, stored_id, trim(label), max_rank);
  END IF;

  IF NOT EXISTS (SELECT 1 FROM projectprop WHERE project_id=pid AND type_id=1060 AND rank=max_rank) THEN
    insert into projectprop (project_id, type_id, value, rank)
    values (pid, 1060, trim(description), max_rank);
  END IF;

  IF NOT EXISTS (SELECT 1 FROM projectprop WHERE project_id=pid AND type_id=1070 AND rank=max_rank) THEN
    insert into projectprop (project_id, type_id, value, rank)
    values (pid, 1070, cid, max_rank);
  END IF;

  IF NOT EXISTS (SELECT 1 FROM projectprop WHERE project_id=pid AND type_id=cid AND rank=max_rank) THEN
    if (var_type = 'STUDY'  and label_value is not null) then
       insert into projectprop (project_id, type_id, value, rank) values (pid, cid, trim(label_value), max_rank);
    end if;
  END IF;

  set new_ppid = last_insert_id();
  set new_rank = max_rank;
  set retcode = 0;
  set error_msg = null;
end;

end$$

/*
delimiter $$

drop procedure if exists `test_InsertProjectprop`$$
create procedure test_InsertProjectprop(pid int, var1 varchar(255), var2 varchar(255), var3 varchar(255), cid int)
begin

# testing stub
# test_InsertPropertyProp(10060, 'my lable name', 'my description', 'my label value', 19020)
# -must supply a valid study or dataset project_id
# -my label value is optional and only applies to studies; otherwise error is raised if label_value is passed in
# -cvterm must exist



  declare ppid int default 0;
  declare retcode int default 0;

  drop table if exists `log`;
  create temporary table log (msg varchar(1000));


  select * from projectprop where project_id = pid order by rank desc;

  insert into log select concat("before insert maximum rank is ",max(rank)) from projectprop where project_id=pid;

  #1 valid insertion
  call InsertProjectprop(pid, var1, var2, var3, cid, @ppid, @rank, @retcode, @error_msg);

  set ppid = last_insert_id();

  if (@retcode = 0) then
     insert into log select concat("Inserted ",var1,". Maximum rank is now ",@rank," (retcode = ",@retcode,")");
  else 
    insert into log select concat("Error: ",@error_msg," (retcode = ",@retcode,")");
  end if;

  select * from projectprop where project_id = pid order by rank desc;
  select * from log;

end$$
*/
delimiter ;

#call test_InsertProjectprop(10060, '', 'my description', null, 8373); #fail
#call test_InsertProjectprop(10060, null, 'my description', null, 8373); #fail
#call test_InsertProjectprop(10060, 'my label1', 'my description1', null, 8373); #work
#call test_InsertProjectprop(10060, 'my_label2', 'my description2', 'cant have value', 8373); #fail
#call test_InsertProjectprop(10060, 'my_label3', 'my description3', 'label3', 8030); #work
#call test_InsertProjectprop(10060, 'my_label4', 'my description4', null, 8030); #work
#call test_InsertProjectprop(10060, 'my_label5', 'my description5', '', 8384); #work
#call test_InsertProjectprop(10060, 'my_label6', 'my description6', 'label6', -999); #fail


