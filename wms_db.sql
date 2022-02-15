create table  if not exists T_WMS_MENU(
 id VARCHAR(40) comment '主键ID',
  menu_name VARCHAR(200) NOT NULL comment '菜单名称',
  menu_code VARCHAR(200) NOT NULL comment '菜单代码',
  parent_id VARCHAR(40)  NOT NULL comment '父ID',
  level_path VARCHAR(500) comment '菜单层级路径',
  level_no int(2) NOT NULL comment '菜单层级',
  url VARCHAR(1000) comment '菜单路径',
  seq int(10) comment '展示顺序',
  type VARCHAR(2) comment '资源类型 0 目录 1 菜单 2 按钮',
  status VARCHAR(1) comment '状态  1 启用 0 停用',
  hidden VARCHAR(1) comment '是否隐藏 0 否 1 是';
  icon VARCHAR(500) comment '图标',
  del_flag VARCHAR(1) default '1' comment '是否删除',
  create_by VARCHAR(200) comment '创建人',
  create_time TIMESTAMP  DEFAULT CURRENT_TIMESTAMP comment '创建时间',
  update_by VARCHAR(200)   comment '最后更新人',
  update_time TIMESTAMP  DEFAULT CURRENT_TIMESTAMP comment '最后更新时间',
  PRIMARY KEY ( id )
 )comment='菜单表' ENGINE=InnoDB DEFAULT CHARSET=utf8;


  ALTER TABLE T_WMS_MENU add redirect VARCHAR(1000) comment '重定向' ;
  ALTER TABLE T_WMS_MENU add path VARCHAR(1000) comment '路径' ;
  ALTER TABLE T_WMS_MENU add component VARCHAR(1000) comment '页面地址' ;


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


 create table  if not exists T_WMS_STORAGES_REGION(
   id VARCHAR(40) NOT NULL comment '仓库ID',
   region_no VARCHAR(20) NOT NULL comment '仓库编号',
   region_name VARCHAR(40) NOT NULL comment '仓库名称',
   region_type VARCHAR(40) NOT NULL comment '仓库类型 1 一级库 2 二级库',
   address VARCHAR(1000) comment '地址',
   description VARCHAR(300) comment '描述',
   del_flag VARCHAR(1) default '1' comment '是否删除',
   create_by VARCHAR(200) comment '创建人',
   create_time TIMESTAMP  DEFAULT CURRENT_TIMESTAMP comment '创建时间',
   update_by VARCHAR(200)   comment '最后更新人',
   update_time TIMESTAMP  DEFAULT CURRENT_TIMESTAMP comment '最后更新时间',
   PRIMARY KEY ( id )
  )comment='仓库库区表' ENGINE=InnoDB DEFAULT CHARSET=utf8;
  ALTER TABLE T_WMS_STORAGES_REGION add address VARCHAR(1000) comment '地址' ;


create table  if not exists T_WMS_REGION_RACKS(
   id VARCHAR(40) NOT NULL comment 'ID',
   rack_no VARCHAR(40) NOT NULL comment '货架编号',
   region_id VARCHAR(40) NOT NULL comment '库区id',
   rack_type VARCHAR(300)  comment '存放商品类型，逗号分割多种类型',
   total_racks int(11)  comment '货位架数',
   used_racks int(11)  comment '已用货位数',
   description VARCHAR(300) comment '描述',
   del_flag VARCHAR(1) default '1' comment '是否删除',
   create_by VARCHAR(200) comment '创建人',
   create_time TIMESTAMP  DEFAULT CURRENT_TIMESTAMP comment '创建时间',
   update_by VARCHAR(200)   comment '最后更新人',
   update_time TIMESTAMP  DEFAULT CURRENT_TIMESTAMP comment '最后更新时间',
   PRIMARY KEY (id)
  )comment='库区货架表' ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE T_WMS_REGION_RACKS MODIFY  region_id VARCHAR(40) ;


  create table  if not exists T_WMS_PROD_CATEGORY(
     id VARCHAR(40) NOT NULL comment 'ID',
     name VARCHAR(100) NOT NULL comment '大类名称',
     code VARCHAR(40) NOT NULL comment '大类编码',
     description VARCHAR(200) NOT NULL comment '描述',
     del_flag VARCHAR(1) default '1' comment '是否删除',
     create_by VARCHAR(200) comment '创建人',
     create_time TIMESTAMP  DEFAULT CURRENT_TIMESTAMP comment '创建时间',
     update_by VARCHAR(200)   comment '最后更新人',
     update_time TIMESTAMP  DEFAULT CURRENT_TIMESTAMP comment '最后更新时间',
     PRIMARY KEY ( id )
    )comment='货物大类表' ENGINE=InnoDB DEFAULT CHARSET=utf8;
  ALTER TABLE T_WMS_PROD_CATEGORY add code VARCHAR(40) comment '大类编码' ;


create table  if not exists T_WMS_PRODUCT(
  prod_no VARCHAR(40) NOT NULL comment '产品标号',
  product_name VARCHAR(100) NOT NULL comment '产品名称',
  vendor varChar(200) comment '供应商',
  type  varChar(100)   comment '产品型号',
  category_id  varChar(40)  NOT NULL comment '产品类目',
  description VARCHAR(200) NOT NULL comment '描述',
  del_flag VARCHAR(1) default '1' comment '是否删除',
  create_by VARCHAR(200) comment '创建人',
  create_time TIMESTAMP  DEFAULT CURRENT_TIMESTAMP comment '创建时间',
  update_by VARCHAR(200)   comment '最后更新人',
  update_time TIMESTAMP  DEFAULT CURRENT_TIMESTAMP comment '最后更新时间',
  PRIMARY KEY ( prod_no )
)comment='货物表' ENGINE=InnoDB DEFAULT CHARSET=utf8;


create table  if not exists T_WMS_VENDOR(
  id varChar(40) NOT NULL comment '主键id',
  vendor_no VARCHAR(40) NOT NULL comment '供应商编号',
  name varChar(200) comment '供应商名称',
  type  varChar(100)   comment '供应商类型 个人/代理',
  description VARCHAR(200) NOT NULL comment '描述',
  address VARCHAR(1000) NOT NULL comment '供应商地址',
  contact varChar(100) comment '联系人',
  contact_phone  varChar(40)   comment '联系人电话',
  status varChar(2) NOT NULL comment '状态  可用 不可用',
  del_flag VARCHAR(1) default '1' comment '是否删除',
  create_by VARCHAR(200) comment '创建人',
  create_time TIMESTAMP  DEFAULT CURRENT_TIMESTAMP comment '创建时间',
  update_by VARCHAR(200)   comment '最后更新人',
  update_time TIMESTAMP  DEFAULT CURRENT_TIMESTAMP comment '最后更新时间',
  PRIMARY KEY ( id )
)comment='供应商表' ENGINE=InnoDB DEFAULT CHARSET=utf8;

