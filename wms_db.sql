create table  if not exists T_WMS_MENU(
 id VARCHAR(40) comment '主键ID',
  menu_name VARCHAR(200) NOT NULL comment '菜单名称',
  menu_code VARCHAR(200) NOT NULL comment '菜单代码',
  parent_id VARCHAR(40)  NOT NULL comment '父ID',
  level_path VARCHAR(500) comment '菜单层级路径',
  level_no int(2) NOT NULL comment '菜单层级',
  url VARCHAR(1000) comment '菜单路径',
  seq int(10) comment '展示顺序',
  type VARCHAR(2) comment '资源类型 1 菜单 2 按钮',
  del_flag VARCHAR(1) default '1' comment '是否删除',
  create_by VARCHAR(200) comment '创建人',
  create_time TIMESTAMP  DEFAULT CURRENT_TIMESTAMP comment '创建时间',
  update_by VARCHAR(200)   comment '最后更新人',
  update_time TIMESTAMP  DEFAULT CURRENT_TIMESTAMP comment '最后更新时间',
  PRIMARY KEY ( id )
 )comment='菜单表' ENGINE=InnoDB DEFAULT CHARSET=utf8;

 ALTER TABLE T_WMS_MENU ADD status VARCHAR(1);
 alter table T_WMS_MENU modify status VARCHAR(1) comment '状态  1 启用 0 停用';


 create table  if not exists T_WMS_ROLE(
   id VARCHAR(40) comment '主键ID',
   role_name VARCHAR(40) NOT NULL comment '角色名称',
   role_code VARCHAR(40) NOT NULL comment '角色代码',
   type VARCHAR(2)  NOT NULL comment '角色类型',
   del_flag VARCHAR(1) default '1' comment '是否删除',
   create_by VARCHAR(200) comment '创建人',
   create_time TIMESTAMP  DEFAULT CURRENT_TIMESTAMP comment '创建时间',
   update_by VARCHAR(200)   comment '最后更新人',
   update_time TIMESTAMP  DEFAULT CURRENT_TIMESTAMP comment '最后更新时间',
   PRIMARY KEY ( id )
  )comment='角色表' ENGINE=InnoDB DEFAULT CHARSET=utf8;



 create table  if not exists T_WMS_ROLE_MENU(
   role_id VARCHAR(40) NOT NULL comment '角色ID',
   menu_id VARCHAR(40) NOT NULL comment '菜单ID',
   del_flag VARCHAR(1) default '1' comment '是否删除',
   create_by VARCHAR(200) comment '创建人',
   create_time TIMESTAMP  DEFAULT CURRENT_TIMESTAMP comment '创建时间',
   update_by VARCHAR(200)   comment '最后更新人',
   update_time TIMESTAMP  DEFAULT CURRENT_TIMESTAMP comment '最后更新时间'
  )comment='角色菜单表' ENGINE=InnoDB DEFAULT CHARSET=utf8;

   create table  if not exists T_WMS_USER(
     id VARCHAR(40) comment '主键ID',
     user_name VARCHAR(40) NOT NULL comment '用户名称',
     role_code VARCHAR(40) NOT NULL comment '角色代码',
     user_pwd VARCHAR(500) NOT NULL comment '用户密码',
     dept_name VARCHAR(100)  comment '部门名称',
     card_no VARCHAR(40)  comment '工号',
     phone VARCHAR(20)  comment '电话',
     education VARCHAR(100)  comment '教育 ',
     del_flag VARCHAR(1) default '1' comment '是否删除',
     create_by VARCHAR(200) comment '创建人',
     create_time TIMESTAMP  DEFAULT CURRENT_TIMESTAMP comment '创建时间',
     update_by VARCHAR(200)   comment '最后更新人',
     update_time TIMESTAMP  DEFAULT CURRENT_TIMESTAMP comment '最后更新时间',
     PRIMARY KEY ( id )
    )comment='角色表' ENGINE=InnoDB DEFAULT CHARSET=utf8;



 insert into T_WMS_ROLE (id,role_name,role_code,type,create_by,update_by) values('5e9066572ab8463392c3b418edd3b914','超级管理员','superAdmin','1','sys' ,'sys');
 insert into T_WMS_USER (id,user_name,role_code,user_pwd,create_by,update_by) values('574a612414b3464fb8e135bfc18ee777','super','superAdmin','MTIzNDU2','sys' ,'sys');
