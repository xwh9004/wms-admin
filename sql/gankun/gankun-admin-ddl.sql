create table  if not exists T_WMS_USER(
     id VARCHAR(40) comment '主键ID',
     user_name VARCHAR(40) NOT NULL comment '用户名称',
     user_pwd VARCHAR(500) NOT NULL comment '用户密码',
     dept_name VARCHAR(100)  comment '部门名称',
     del_flag VARCHAR(1) default '1' comment '是否删除',
     create_by VARCHAR(200) comment '创建人',
     create_time TIMESTAMP  DEFAULT CURRENT_TIMESTAMP comment '创建时间',
     update_by VARCHAR(200)   comment '最后更新人',
     update_time TIMESTAMP  DEFAULT CURRENT_TIMESTAMP comment '最后更新时间',
     PRIMARY KEY ( id )
 )comment='用户表' ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table  if not exists T_WMS_USER_ROLE(
    id int(20) primary key not null auto_increment,
    user_id VARCHAR(40) NOT NULL comment '用户ID',
    role_id VARCHAR(40) NOT NULL comment '角色ID',
    del_flag VARCHAR(1) default '1' comment '是否删除',
    create_by VARCHAR(200) comment '创建人',
    create_time TIMESTAMP  DEFAULT CURRENT_TIMESTAMP comment '创建时间',
    update_by VARCHAR(200)   comment '最后更新人',
    update_time TIMESTAMP  DEFAULT CURRENT_TIMESTAMP comment '最后更新时间'
)comment='用户角色表' ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
  hidden VARCHAR(1) comment '是否隐藏 0 否 1 是',
  icon VARCHAR(500) comment '图标',
  component VARCHAR(1000) comment '页面地址',
  redirect VARCHAR(1000) comment '重定向',
  path VARCHAR(1000) comment '路径',
  del_flag VARCHAR(1) default '1' comment '是否删除',
  create_by VARCHAR(200) comment '创建人',
  create_time TIMESTAMP  DEFAULT CURRENT_TIMESTAMP comment '创建时间',
  update_by VARCHAR(200)   comment '最后更新人',
  update_time TIMESTAMP  DEFAULT CURRENT_TIMESTAMP comment '最后更新时间',
  PRIMARY KEY ( id )
 )comment='菜单表' ENGINE=InnoDB DEFAULT CHARSET=utf8;

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


create table  if not exists T_WMS_PROD_CATEGORY(
   id VARCHAR(40) NOT NULL comment 'ID',
   name VARCHAR(100) NOT NULL comment '大类名称',
   code VARCHAR(40) NOT NULL comment '大类编码',
   unit_id VARCHAR(40) Not null comment '单位ID',
   description VARCHAR(200) NOT NULL comment '描述',
   del_flag VARCHAR(1) default '1' comment '是否删除',
   create_by VARCHAR(200) comment '创建人',
   create_time TIMESTAMP NOT NULL  DEFAULT CURRENT_TIMESTAMP comment '创建时间',
   update_by VARCHAR(200)   comment '最后更新人',
   update_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP comment '最后更新时间',
   PRIMARY KEY ( id )
)comment='货物大类表' ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table  if not exists T_WMS_PRODUCT(
  id VARCHAR(40) NOT NULL comment '产品ID',
  prod_no VARCHAR(40) NOT NULL comment '产品编号',
  prod_name VARCHAR(100) NOT NULL comment '产品名称',
  vendor_id varChar(40) comment '供应商',
  type  varChar(100)   comment '产品型号',
  unit_price int(20)  comment '价格',
  category_id  varChar(40)  NOT NULL comment '产品类目',
  description VARCHAR(200)  comment '描述',
  del_flag VARCHAR(1) default '1' comment '是否删除',
  create_by VARCHAR(200) comment '创建人',
  create_time TIMESTAMP  DEFAULT CURRENT_TIMESTAMP comment '创建时间',
  update_by VARCHAR(200)   comment '最后更新人',
  update_time TIMESTAMP  DEFAULT CURRENT_TIMESTAMP comment '最后更新时间',
  PRIMARY KEY ( id )
)comment='货物表' ENGINE=InnoDB DEFAULT CHARSET=utf8;


create table  if not exists T_WMS_MEASUREMENT_UNIT (
  id int(20) primary key not null auto_increment,
  unit_symbol VARCHAR(40) NOT NULL comment '单位编号',
  unit_name VARCHAR(40) NOT NULL comment '单位名称',
  del_flag VARCHAR(1) default '1' comment '是否删除',
  create_by VARCHAR(200) comment '创建人',
  create_time TIMESTAMP  DEFAULT CURRENT_TIMESTAMP comment '创建时间',
  update_by VARCHAR(200)   comment '最后更新人',
  update_time TIMESTAMP  DEFAULT CURRENT_TIMESTAMP comment '最后更新时间'
)comment='计量单位表' ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table  if not exists T_WMS_LESSEE_INFO (
  id int(20) primary key not null auto_increment,
  lessee_no VARCHAR(40) NOT NULL comment '承租单位编号',
  lessee_company VARCHAR(200) NOT NULL comment '承租单位',
  contact VARCHAR(40) NOT NULL comment '承租联系人',
  phone   VARCHAR(20) comment '承租联系人电话',
  del_flag VARCHAR(1) default '1' comment '是否删除',
  create_by VARCHAR(200) comment '创建人',
  create_time TIMESTAMP  DEFAULT CURRENT_TIMESTAMP comment '创建时间',
  update_by VARCHAR(200)   comment '最后更新人',
  update_time TIMESTAMP  DEFAULT CURRENT_TIMESTAMP comment '最后更新时间'
)comment='承租单位表' ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table  if not exists T_WMS_LESSEE_ADDRESS (
  id int(20) primary key not null auto_increment,
  lessee_id int(20) NOT NULL comment '承租单位ID',
  company_address VARCHAR(50) NOT NULL comment '承租单位地址',
  contact   VARCHAR(50) comment '承租联系人电话',
  phone   VARCHAR(20) comment '联系人',
  is_default VARCHAR(1) comment '默认地址',
  del_flag VARCHAR(1) default '1' comment '是否删除',
  create_by VARCHAR(200) comment '创建人',
  create_time TIMESTAMP  DEFAULT CURRENT_TIMESTAMP comment '创建时间',
  update_by VARCHAR(200)   comment '最后更新人',
  update_time TIMESTAMP  DEFAULT CURRENT_TIMESTAMP comment '最后更新时间'
)comment='承租单位地址表' ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table  if not exists T_WMS_LEASE_CONTRACT (
  id int(20) primary key not null auto_increment,
  contract_no varChar(40) comment '合同编号',
  business_user varChar(40) comment '业务人员',
  lessee_no varChar(40) NOT NULL comment '承租方编号',
  lessee_company varChar(200) NOT NULL comment '承租方单位',
  lessee_address varChar(200)  comment '承租方单位地址',
  lessee_contact varChar(40)  comment '承租方电话',
  lessee_phone varChar(20) comment '承租方单位',
  sign_date   datetime comment '签约日期',
  effective_date   datetime comment '生效日期',
  expire_date   datetime comment '到期日期',
  deposit   int(11) comment '合同押金',
  bill_method   VARCHAR(1) comment '1 算头又算尾 2 算头不算尾',
  status VARCHAR(1) comment '0 未生效 1 已生效 2 已失效',
  del_flag VARCHAR(1) default '1' comment '是否删除',
  create_by VARCHAR(200) comment '创建人',
  create_time TIMESTAMP  DEFAULT CURRENT_TIMESTAMP comment '创建时间',
  update_by VARCHAR(200)   comment '最后更新人',
  update_time TIMESTAMP  DEFAULT CURRENT_TIMESTAMP comment '最后更新时间'
)comment='合同表' ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table  if not exists T_WMS_BULLETIN_INFO(
  id int(20) primary key not null auto_increment,
  BULLETIN_INFO VARCHAR(2000) comment '公告内容',
  is_publish VARCHAR(1) comment '是否发布',
  del_flag VARCHAR(1) default '1' comment '是否删除',
  create_by VARCHAR(200) comment '创建人',
  create_time TIMESTAMP  DEFAULT CURRENT_TIMESTAMP comment '创建时间',
  update_by VARCHAR(200)   comment '最后更新人',
  update_time TIMESTAMP  DEFAULT CURRENT_TIMESTAMP comment '最后更新时间'
)comment='布告信息表' ENGINE=InnoDB DEFAULT CHARSET=utf8;

  create table  if not exists T_WMS_CONTRACT_PROD_REL(
    id int(20) primary key not null auto_increment,
    contract_id int(20) NOT NULL comment '合同id',
    prod_id VARCHAR(40) NOT NULL comment '产品ID',
    lease_unit_price int(20) NOT NULL comment '单价/天',
    del_flag VARCHAR(1) default '1' comment '是否删除',
    create_by VARCHAR(200) comment '创建人',
    create_time TIMESTAMP  DEFAULT CURRENT_TIMESTAMP comment '创建时间',
    update_by VARCHAR(200)   comment '最后更新人',
    update_time TIMESTAMP  DEFAULT CURRENT_TIMESTAMP comment '最后更新时间'
   )comment='合同货物关系表' ENGINE=InnoDB DEFAULT CHARSET=utf8;

  create table  if not exists T_WMS_TAKE_IN_RECORD(
    id int(20) primary key not null auto_increment,
    take_in_no VARCHAR(40) NOT NULL comment '收货单号',
	take_in_type VARCHAR(2)  comment '收货类型',
    business_user VARCHAR(40) NOT NULL comment '业务人员',
    contract_no VARCHAR(40) NOT NULL comment '合同编号',
    contract_company VARCHAR(40) NOT NULL comment '合同单位',
	total_amount int(20)  comment '收货数量',
	total_weight int(20)  comment '收货总重量',
	prod_types   int(20)  comment '货品种类数',
	unload_fee   int(20)  comment '卸车费',
	pile_fee     int(20)    comment '堆码费',
	others_fee   int(20)  comment '其他杂费',
    take_in_time TIMESTAMP   comment '收货时间',
	description VARCHAR(2000) comment '描述',
	status VARCHAR(1) default '0' comment '0 未签收 1 已签收',
    del_flag VARCHAR(1) default '1' comment '是否删除',
    create_by VARCHAR(200) comment '创建人',
    create_time TIMESTAMP  DEFAULT CURRENT_TIMESTAMP comment '创建时间',
    update_by VARCHAR(200)   comment '最后更新人',
    update_time TIMESTAMP  DEFAULT CURRENT_TIMESTAMP comment '最后更新时间'
   )comment='收货记录表' ENGINE=InnoDB DEFAULT CHARSET=utf8;

  create table  if not exists T_WMS_TAKE_IN_DETAIL(
    id int(20) primary key not null auto_increment,
    take_in_no VARCHAR(40) NOT NULL comment '收货单号',
    prod_no VARCHAR(40) NOT NULL comment '货物ID',
    prod_name VARCHAR(200) comment '货物名称',
    prod_unit VARCHAR(40)  comment '货物单位',
    prod_amount int(20)  comment '货物数量',
	unit_price int(20)  comment '货物单价',
    del_flag VARCHAR(1) default '1' comment '是否删除',
    create_by VARCHAR(200) comment '创建人',
    create_time TIMESTAMP  DEFAULT CURRENT_TIMESTAMP comment '创建时间',
    update_by VARCHAR(200)   comment '最后更新人',
    update_time TIMESTAMP  DEFAULT CURRENT_TIMESTAMP comment '最后更新时间'
   )comment='收货记录详情表' ENGINE=InnoDB DEFAULT CHARSET=utf8;

  create table  if not exists T_WMS_TAKE_OUT_RECORD(
    id int(20) primary key not null auto_increment,
    take_out_no VARCHAR(40) NOT NULL comment '发货单号',
	take_out_type VARCHAR(2)  comment '发货类型',
    business_user VARCHAR(40) NOT NULL comment '业务人员',
    contract_no VARCHAR(40) NOT NULL comment '合同编号',
    contract_company VARCHAR(40) NOT NULL comment '合同单位',
	total_amount int(20)  comment '发货数量',
	total_weight int(20)  comment '发货总重量',
	prod_types   int(20)  comment '货品种类数',
	package_num   int(20)  comment '包裹数',
	ship_fee   int(20)  comment '运费',
	load_fee   int(20)  comment '上车费',
	pile_fee   int(20)    comment '堆码费',
	others_fee   int(20)  comment '其他杂费',
    take_out_time TIMESTAMP   comment '发货时间',
	description VARCHAR(2000) comment '描述',
	status VARCHAR(1) default '0' comment '0 未发货 1 已发货',
    del_flag VARCHAR(1) default '1' comment '是否删除',
    create_by VARCHAR(200) comment '创建人',
    create_time TIMESTAMP  DEFAULT CURRENT_TIMESTAMP comment '创建时间',
    update_by VARCHAR(200)   comment '最后更新人',
    update_time TIMESTAMP  DEFAULT CURRENT_TIMESTAMP comment '最后更新时间'
   )comment='发货记录表' ENGINE=InnoDB DEFAULT CHARSET=utf8;



  create table  if not exists T_WMS_TAKE_OUT_DETAIL(
    id int(20) primary key not null auto_increment,
    take_out_no VARCHAR(40) NOT NULL comment '发货单号',
    prod_no VARCHAR(40) NOT NULL comment '货物ID',
    prod_name VARCHAR(200) comment '货物名称',
    prod_unit VARCHAR(40)  comment '货物单位',
	prod_amount int(20)  comment '货物数量',
	unit_price int(20)  comment '货物单价',
    del_flag VARCHAR(1) default '1' comment '是否删除',
    create_by VARCHAR(200) comment '创建人',
    create_time TIMESTAMP  DEFAULT CURRENT_TIMESTAMP comment '创建时间',
    update_by VARCHAR(200)   comment '最后更新人',
    update_time TIMESTAMP  DEFAULT CURRENT_TIMESTAMP comment '最后更新时间'
   )comment='发货记录详情表' ENGINE=InnoDB DEFAULT CHARSET=utf8;