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

create table  if not exists T_WMS_ORG(
  id VARCHAR(40) comment '主键ID',
  org_name VARCHAR(200) comment '机构名称',
  org_code VARCHAR(200) comment '机构代码',
  parent_id VARCHAR(40) comment '父ID',
  level_path varChar(500) comment '路径层级',
  seq int(11) comment '顺序',
  level_no int(11) comment '层级',
  del_flag VARCHAR(1) default '1' comment '是否删除',
  create_by VARCHAR(200) comment '创建人',
  create_time TIMESTAMP  DEFAULT CURRENT_TIMESTAMP comment '创建时间',
  update_by VARCHAR(200)   comment '最后更新人',
  update_time TIMESTAMP  DEFAULT CURRENT_TIMESTAMP comment '最后更新时间',
  PRIMARY KEY ( id )
)comment='组织表' ENGINE=InnoDB DEFAULT CHARSET=utf8;


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
     user_pwd VARCHAR(500) NOT NULL comment '用户密码',
     dept_name VARCHAR(100)  comment '部门名称',,
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


create table  if not exists T_WMS_RECEIPT_RECORD(
  id varChar(40) NOT NULL comment '主键id',
  receipt_no VARCHAR(40) NOT NULL comment '单据编号',
  receipt_type varChar(2) comment '单据类型',
  sub_type varChar(2) comment '记录类型',
  region_id varChar(40) comment '收货出货库',
  from_id varChar(40) comment '调拨出库库id',
  to_id varChar(40) comment '调拨入库库id',
  apply_id varChar(40) comment '申请人工号',
  operator_id varChar(40) comment '经办人工号',
  prod_type_nums int(11) comment '货物种数',
  total_amount int(11) comment '货物总量',
  total_price int(11) comment '货物总价',
  description VARCHAR(200) NOT NULL comment '描述',
  apply_date TIMESTAMP  DEFAULT CURRENT_TIMESTAMP comment '申请时间',
  del_flag VARCHAR(1) default '1' comment '是否删除',
  create_by VARCHAR(200) comment '创建人',
  create_time TIMESTAMP  DEFAULT CURRENT_TIMESTAMP comment '创建时间',
  update_by VARCHAR(200)   comment '最后更新人',
  update_time TIMESTAMP  DEFAULT CURRENT_TIMESTAMP comment '最后更新时间',
  PRIMARY KEY ( id )
)comment='申请记录表' ENGINE=InnoDB DEFAULT CHARSET=utf8;


create table  if not exists T_WMS_STORAGE_IN_DETAIL_RECORD(
  id int(20) primary key not null auto_increment,
  receipt_id VARCHAR(40) NOT NULL comment '单据ID',
  prod_id VARCHAR(40) comment '产品ID',
  prod_amount int(11) comment '产品数量',
  prod_unit_price int(11) comment '产品单价',
  rack_id varChar(40) comment '货架号',
  rack_takes int(11) comment '占用货架数量',
  status varChar(2) comment '入库状态',
  del_flag VARCHAR(1) default '1' comment '是否删除',
  create_by VARCHAR(200) comment '创建人',
  create_time TIMESTAMP  DEFAULT CURRENT_TIMESTAMP comment '创建时间',
  update_by VARCHAR(200)   comment '最后更新人',
  update_time TIMESTAMP  DEFAULT CURRENT_TIMESTAMP comment '最后更新时间'
)comment='入库详情记录表' ENGINE=InnoDB DEFAULT CHARSET=utf8;



create table  if not exists T_WMS_STOCK_CHANGE_RECORD(
  id int(20) primary key not null auto_increment,
  receipt_no VARCHAR(40) NOT NULL comment '单据编号',
  region_id VARCHAR(40) NOT NULL comment '库区ID',
  prod_id VARCHAR(40) comment '产品ID',
  current_stock int(11) comment '当前库存',
  change_stock int(11) comment '变化库存',
  del_flag VARCHAR(1) default '1' comment '是否删除',
  create_by VARCHAR(200) comment '创建人',
  create_time TIMESTAMP  DEFAULT CURRENT_TIMESTAMP comment '创建时间',
  update_by VARCHAR(200)   comment '最后更新人',
  update_time TIMESTAMP  DEFAULT CURRENT_TIMESTAMP comment '最后更新时间'
)comment='产品库存变更记录表' ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table  if not exists T_WMS_STORAGE_OUT_DETAIL_RECORD(
  id int(20) primary key not null auto_increment,
  receipt_id VARCHAR(40) NOT NULL comment '单据ID',
  prod_id VARCHAR(40) comment '产品ID',
  prod_amount int(11) comment '产品数量',
  prod_unit_price int(11) comment '产品单价',
  status varChar(2) comment '状态',
  del_flag VARCHAR(1) default '1' comment '是否删除',
  create_by VARCHAR(200) comment '创建人',
  create_time TIMESTAMP  DEFAULT CURRENT_TIMESTAMP comment '创建时间',
  update_by VARCHAR(200)   comment '最后更新人',
  update_time TIMESTAMP  DEFAULT CURRENT_TIMESTAMP comment '最后更新时间'
)comment='出库详情记录表' ENGINE=InnoDB DEFAULT CHARSET=utf8;


create table  if not exists T_WMS_SHIFT_DETAIL_RECORD(
  id int(20) primary key not null auto_increment,
  receipt_id VARCHAR(40) NOT NULL comment '单据ID',
  prod_id VARCHAR(40) comment '产品ID',
  prod_amount int(11) comment '产品数量',
  prod_unit_price int(11) comment '产品单价',
  status varChar(2) comment '状态',
  del_flag VARCHAR(1) default '1' comment '是否删除',
  create_by VARCHAR(200) comment '创建人',
  create_time TIMESTAMP  DEFAULT CURRENT_TIMESTAMP comment '创建时间',
  update_by VARCHAR(200)   comment '最后更新人',
  update_time TIMESTAMP  DEFAULT CURRENT_TIMESTAMP comment '最后更新时间'
)comment='移库详情记录表' ENGINE=InnoDB DEFAULT CHARSET=utf8;


create table  if not exists T_WMS_DISCARD_DETAIL_RECORD(
  id int(20) primary key not null auto_increment,
  receipt_id VARCHAR(40) NOT NULL comment '单据ID',
  prod_id VARCHAR(40) comment '产品ID',
  prod_amount int(11) comment '产品数量',
  prod_unit_price int(11) comment '产品单价',
  status varChar(2) comment '状态',
  del_flag VARCHAR(1) default '1' comment '是否删除',
  create_by VARCHAR(200) comment '创建人',
  create_time TIMESTAMP  DEFAULT CURRENT_TIMESTAMP comment '创建时间',
  update_by VARCHAR(200)   comment '最后更新人',
  update_time TIMESTAMP  DEFAULT CURRENT_TIMESTAMP comment '最后更新时间'
)comment='报废详情录表' ENGINE=InnoDB DEFAULT CHARSET=utf8;



create table  if not exists T_WMS_STOCK(
  id int(20) primary key not null auto_increment,
  region_id VARCHAR(40) comment '库区ID',
  prod_id VARCHAR(40) comment '产品ID',
  current_stock int(11) comment '当前库存',
  del_flag VARCHAR(1) default '1' comment '是否删除',
  create_by VARCHAR(200) comment '创建人',
  create_time TIMESTAMP  DEFAULT CURRENT_TIMESTAMP comment '创建时间',
  update_by VARCHAR(200)   comment '最后更新人',
  update_time TIMESTAMP  DEFAULT CURRENT_TIMESTAMP comment '最后更新时间'
)comment='库存记录表' ENGINE=InnoDB DEFAULT CHARSET=utf8;

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



create table  if not exists T_WMS_STOCK_MAINTAIN(
  id int(20) primary key not null auto_increment,
  prod_id VARCHAR(40) comment '产品ID',
  up_bound int(11) comment '库存上限',
  down_bound int(11) comment '库存下限',
  del_flag VARCHAR(1) default '1' comment '是否删除',
  create_by VARCHAR(200) comment '创建人',
  create_time TIMESTAMP  DEFAULT CURRENT_TIMESTAMP comment '创建时间',
  update_by VARCHAR(200)   comment '最后更新人',
  update_time TIMESTAMP  DEFAULT CURRENT_TIMESTAMP comment '最后更新时间'
)comment='库存维护记录表' ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table  if not exists T_WMS_INVENTORY_DETAIL_RECORD(
  id int(20) primary key not null auto_increment,
  receipt_id VARCHAR(40) NOT NULL comment '单据ID',
  prod_id VARCHAR(40) comment '产品ID',
  prod_amount int(11) comment '产品数量',
  prod_unit_price int(11) comment '产品单价',
  status varChar(2) comment '状态',
  del_flag VARCHAR(1) default '1' comment '是否删除',
  create_by VARCHAR(200) comment '创建人',
  create_time TIMESTAMP  DEFAULT CURRENT_TIMESTAMP comment '创建时间',
  update_by VARCHAR(200)   comment '最后更新人',
  update_time TIMESTAMP  DEFAULT CURRENT_TIMESTAMP comment '最后更新时间'
)comment='盘点详情录表' ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
  lessee_addr varChar(200)  comment '承租方单位地址',
  lessee_contact varChar(40)  comment '承租方电话',
  lessee_phone varChar(20) comment '承租方单位',
  sign_date   datetime comment '签约日期',
  effective_date   datetime comment '生效日期',
  expire_date   datetime comment '到期日期',
  deposit   int(11) comment '合同押金',
  bill_method   VARCHAR(1) comment '1 算头又算尾 2 算头不算尾',
  is_default VARCHAR(1) comment '默认地址',
  del_flag VARCHAR(1) default '1' comment '是否删除',
  create_by VARCHAR(200) comment '创建人',
  create_time TIMESTAMP  DEFAULT CURRENT_TIMESTAMP comment '创建时间',
  update_by VARCHAR(200)   comment '最后更新人',
  update_time TIMESTAMP  DEFAULT CURRENT_TIMESTAMP comment '最后更新时间'
)comment='合同表' ENGINE=InnoDB DEFAULT CHARSET=utf8;
