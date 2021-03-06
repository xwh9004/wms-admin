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
    )comment='角色表' ENGINE=InnoDB DEFAULT CHARSET=utf8;




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


--初始化sql 数据
INSERT INTO T_WMS_USER (id,user_name,role_code,user_pwd,create_by,update_by) values('574a612414b3464fb8e135bfc18ee777','super','superAdmin','MTIzNDU2','sys' ,'sys');
INSERT INTO `t_wms_role` VALUES ('5e9066572ab8463392c3b418edd3b914','超级管理员','superAdmin','1','1','sys','2022-01-27 06:33:22','super','2022-01-27 06:33:22')
INSERT INTO `t_wms_menu` (id,menu_name, menu_code, parent_id, level_path, level_no, url, seq , type, del_flag, create_by, create_time, update_by, update_time, status, icon, hidden, redirect, path, component) VALUES ('05bd84d9f8734ff884c1a8dd106e2dfc','供应商管理','vendor','0e4e797b0f1345ea95235c33451f56d1','/0e4e797b0f1345ea95235c33451f56d1/null',2,'vendor',6,'1','1','super','2022-01-29 05:59:39','super','2022-01-29 05:59:39','1',NULL,'0',NULL,'vendor','views/system/vendor/vendor-list'),('0e4e797b0f1345ea95235c33451f56d1','系统管理','system','-1','/0e4e797b0f1345ea95235c33451f56d1',1,'/system',4,'0','1','admin','2022-01-28 02:08:29','super','2022-01-28 02:08:29','1','setting','0','noRedirect','/system',NULL),('1c27d3dc22404882a743953a64b390d6','出库管理','storage-out-list','987fdd4232fa494c8c42ab584d87d6cb','/6658c01c6a6e4f24810821352eaba0fc/null/null',3,NULL,1,'1','1','super','2022-02-03 03:10:43','super','2022-02-03 03:10:43','1',NULL,'0',NULL,'list','views/workspace/storage-out/list'),('1eb64d3653da49c284316304e7a2230f','新增库存盘点','stock-inventory-add','c3e98100d3f0426780c763aaaff6c511','/6658c01c6a6e4f24810821352eaba0fc/null/null',3,NULL,3,'1','1','super','2022-02-10 02:43:08','super','2022-02-10 02:43:08','1',NULL,'1',NULL,'addInventory','views/workspace/stock/addInventory'),('20e3e09784144ea3a0f65f2955af1ae8','货物列表','product-list','9b09c6ae28874971838c06140dd8e0fe','/0e4e797b0f1345ea95235c33451f56d1/null/null',3,'/system/product/list',2,'1','1','super','2022-01-29 06:11:16','super','2022-01-29 06:11:16','1',NULL,'0',NULL,'product-list','views/system/product/product-list/product-list'),('23b372a7fce34ffe9ac419ad9b414b08','调拨管理','storage-shift-list','e6a9f906b23b4f06a574e850913bbd90','/6658c01c6a6e4f24810821352eaba0fc/null/null',3,NULL,1,'1','1','super','2022-02-10 02:23:11','super','2022-02-10 02:23:11','1',NULL,'0',NULL,'list','views/workspace/storage-shift/list'),('36ce63d96376466d8998fc0dcc0729ec','报表管理','report','-1','/36ce63d96376466d8998fc0dcc0729ec',1,'/report',3,'0','1','admin','2022-01-28 02:08:29','super','2022-01-28 02:08:29','1','excel','0',NULL,'/report',NULL),('38ee05499a554042a82b1a20996ffd43','调拨详情','storage-shift-detail','e6a9f906b23b4f06a574e850913bbd90','/6658c01c6a6e4f24810821352eaba0fc/null/null',3,NULL,3,'1','1','super','2022-02-10 02:34:09','super','2022-02-10 02:34:09','1',NULL,'1',NULL,'detail/:receiptNo','views/workspace/storage-shift/detail'),('3afe8c5b0a424d09be556da75c2e1b2e','入库操作','storage-in','6658c01c6a6e4f24810821352eaba0fc','/6658c01c6a6e4f24810821352eaba0fc/null',2,'storage-in',1,'1','1','super','2022-01-31 11:01:56','super','2022-01-31 11:01:56','1',NULL,'0','/workspace/storage-in/list','storage-in','views/workspace/storage-in/index'),('41c65abc4ef14356bad02c65f2db0c12','调拨报表','storage-shift-report','36ce63d96376466d8998fc0dcc0729ec','/36ce63d96376466d8998fc0dcc0729ec/null',2,'storage-shift',3,'1','1','super','2022-02-01 09:51:48','super','2022-02-01 09:51:48','1',NULL,'0',NULL,'storage-shift','views/report/storage-shift/storage-shift'),('43df252f35f74a7a8fb9464658312729','出库详情','storage-out-detail','987fdd4232fa494c8c42ab584d87d6cb','/6658c01c6a6e4f24810821352eaba0fc/null/null',3,NULL,3,'1','1','super','2022-02-03 03:16:14','super','2022-02-03 03:16:14','1',NULL,'1','','detail/:receiptNo','views/workspace/storage-out/detail'),('47c597ea24804905baaba35eedae3440','查看详情','BTN000002','9a080095778344d3af92dd86b9dbc67f','/6658c01c6a6e4f24810821352eaba0fc/null/null/null',4,'/storage-in/add/detail',2,'2','1','super','2022-02-22 13:48:43','super','2022-02-22 13:48:43','1',NULL,'0',NULL,NULL,NULL),('4b6d7edec69341fd87097501187b5617','组织管理','organization','0e4e797b0f1345ea95235c33451f56d1','/0e4e797b0f1345ea95235c33451f56d1/null',2,NULL,7,'1','1','super','2022-02-24 14:27:58','super','2022-02-24 14:27:58','1',NULL,'0',NULL,'organization','views/system/organization/organization'),('51e3ab8b85ad45cf87bcb9141dcdddb9','报废报表','discard-report','36ce63d96376466d8998fc0dcc0729ec','/36ce63d96376466d8998fc0dcc0729ec/51e3ab8b85ad45cf87bcb9141dcdddb9',2,NULL,4,'1','1','super','2022-03-07 03:00:38','super','2022-03-07 03:00:38','1',NULL,'0',NULL,'discard','views/report/discard/discard'),('53414f38f4164d5aa774053f18b4fa98','库区管理','region','aacbfbd802bb4eeb9961eba8c7f8399a','/0e4e797b0f1345ea95235c33451f56d1/null/null',3,'/system/storage/repository',1,'1','1','super','2022-01-29 06:01:51','super','2022-01-29 06:01:51','1',NULL,'0',NULL,'region','views/system/warehouse/region/region'),('60a37808324b4a0cb0e6463d08634bdf','入库详情','storage-in-detail','3afe8c5b0a424d09be556da75c2e1b2e','/6658c01c6a6e4f24810821352eaba0fc/null/null',3,NULL,3,'1','1','super','2022-02-03 02:58:15','super','2022-02-03 02:58:15','1',NULL,'1',NULL,'detail/:receiptNo','views/workspace/storage-in/detail'),('661a2fd29f954652885d937d29688ab7','新增调拨','storage-shift-add','e6a9f906b23b4f06a574e850913bbd90','/6658c01c6a6e4f24810821352eaba0fc/null/null',3,NULL,2,'1','1','super','2022-02-10 02:33:19','super','2022-02-10 02:33:19','1',NULL,'1',NULL,'add','views/workspace/storage-shift/add'),('6658c01c6a6e4f24810821352eaba0fc','仓库作业','workspace','-1','/6658c01c6a6e4f24810821352eaba0fc',1,'/operate',2,'0','1','admin','2022-01-28 02:08:29','super','2022-01-28 02:08:29','1','workspace','0','noRedirect','/workspace',NULL),('71a23ae58f36400a944e9e23c3894259','货架管理','rack','aacbfbd802bb4eeb9961eba8c7f8399a','/0e4e797b0f1345ea95235c33451f56d1/null/null',3,'/system/storage/rack',2,'1','1','super','2022-01-29 06:03:07','super','2022-01-29 06:03:07',NULL,NULL,'0',NULL,'storage-rack','views/system/warehouse/storage-rack/storage-rack'),('7b88b5e8b0d14f7c9fe2162d2ef1dd69','报废管理','discard-list','b4189b849e0e4664a2ed518d4e4260ee','/6658c01c6a6e4f24810821352eaba0fc/null/null',3,NULL,1,'1','1','super','2022-02-10 02:37:16','super','2022-02-10 02:37:16','1',NULL,'0',NULL,'list','views/workspace/discard/list'),('81377bdaf17149998e2535efb0dea666','公告管理','bulletin','0e4e797b0f1345ea95235c33451f56d1','/0e4e797b0f1345ea95235c33451f56d1/null',2,NULL,8,'1','1','super','2022-02-24 14:39:55','super','2022-02-24 14:39:55','1',NULL,'0',NULL,'bulletin','views/system/bulletin/bulletin'),('824851de333743228c45c22e088bb4f9','类目管理','category','9b09c6ae28874971838c06140dd8e0fe','/0e4e797b0f1345ea95235c33451f56d1/null/null',3,'/system/product/category',1,'1','1','super','2022-01-29 06:10:04','super','2022-01-29 06:10:04','1',NULL,'0',NULL,'category','views/system/product/category/category'),('854e078ed9fa4c009cdb2ffdc24a9842','新增入库','BTN000001','9a080095778344d3af92dd86b9dbc67f','/6658c01c6a6e4f24810821352eaba0fc/null/null/null',4,'/menu/storage-in/add',1,'2','1','super','2022-02-22 13:42:11','super','2022-02-22 13:42:11','1',NULL,'0',NULL,'',NULL),('88eb7225d47b4fd1899823f749395a14','库存报表','stock-report','36ce63d96376466d8998fc0dcc0729ec','/36ce63d96376466d8998fc0dcc0729ec/null',2,'stock',7,'1','1','super','2022-02-01 09:58:44','super','2022-02-01 09:58:44','1',NULL,'0',NULL,'stock','views/report/stock/stock'),('8d8e67329ad24897aacfac0f1499614e','新增出库','storage-out-add','987fdd4232fa494c8c42ab584d87d6cb','/6658c01c6a6e4f24810821352eaba0fc/null/null',3,NULL,2,'1','1','super','2022-02-03 03:12:45','super','2022-02-03 03:12:45','1',NULL,'1','','add','views/workspace/storage-out/add'),('987fdd4232fa494c8c42ab584d87d6cb','出库操作','storage-out','6658c01c6a6e4f24810821352eaba0fc','/6658c01c6a6e4f24810821352eaba0fc/null',2,'storage-out',2,'1','1','super','2022-01-31 11:47:41','super','2022-01-31 11:47:41','1',NULL,'0','/workspace/storage-out/list','storage-out','views/workspace/storage-out/index'),('98d72cd6ed894fafbad6d2e8f953524d','菜单管理','menu','0e4e797b0f1345ea95235c33451f56d1','/0e4e797b0f1345ea95235c33451f56d1/98d72cd6ed894fafbad6d2e8f953524d',2,'menu',1,'1','1','super','2022-01-29 02:15:48','super','2022-01-29 02:15:48','1',NULL,'0',NULL,'menu','views/system/menu/menu'),('9a080095778344d3af92dd86b9dbc67f','入库管理','storage-in-list','3afe8c5b0a424d09be556da75c2e1b2e','/6658c01c6a6e4f24810821352eaba0fc/null/null',3,NULL,1,'1','1','super','2022-02-03 02:55:43','super','2022-02-03 02:55:43','1',NULL,'0',NULL,'list','views/workspace/storage-in/list'),('9b09c6ae28874971838c06140dd8e0fe','货物管理','product','0e4e797b0f1345ea95235c33451f56d1','/0e4e797b0f1345ea95235c33451f56d1/null',2,'product',5,'1','1','super','2022-01-29 05:47:44','super','2022-01-29 05:47:44','1',NULL,'0','','product','views/system/product/index'),('9eb54f8f25c8475ba42ef92ee42ee1b7','库存盘点','stock-inventory-list','c3e98100d3f0426780c763aaaff6c511','/6658c01c6a6e4f24810821352eaba0fc/null/null',3,NULL,2,'1','1','super','2022-02-10 02:41:58','super','2022-02-10 02:41:58','1',NULL,'0',NULL,'inventoryList','views/workspace/stock/inventoryList'),('a15d581a64414abe9ff11b2784c29991','全景视图','dashboard','-1','/a15d581a64414abe9ff11b2784c29991',1,'/dashboard',1,'0','1','admin','2022-01-19 03:12:20','super','2022-01-19 03:12:20','1','dashboard','0','/dashboard/index','/dashboard',NULL),('a8a048806f8843eeb350ed55349da7b2','货物报表','product-report','36ce63d96376466d8998fc0dcc0729ec','/36ce63d96376466d8998fc0dcc0729ec/null',2,'product',6,'1','1','super','2022-02-01 09:58:13','super','2022-02-01 09:58:13','1',NULL,'0',NULL,'product','views/report/product/product'),('aacbfbd802bb4eeb9961eba8c7f8399a','仓库管理','storage','0e4e797b0f1345ea95235c33451f56d1','/0e4e797b0f1345ea95235c33451f56d1/null',2,'storage',4,'1','1','super','2022-01-29 05:47:04','super','2022-01-29 05:47:04','1',NULL,'0','noRedirect','warehouse','views/system/warehouse/index'),('aaf53476ae7d4b2bb72d4eb9a53d8cb5','查询','BTN000003','9a080095778344d3af92dd86b9dbc67f','/6658c01c6a6e4f24810821352eaba0fc/null/null/null',4,'/storage-in/list',3,'2','1','super','2022-02-22 14:35:26','super','2022-02-22 14:35:26','1',NULL,'0',NULL,NULL,NULL),('b4189b849e0e4664a2ed518d4e4260ee','报废操作','discard','6658c01c6a6e4f24810821352eaba0fc','/6658c01c6a6e4f24810821352eaba0fc/null',2,'discard',4,'1','1','super','2022-01-31 11:50:00','super','2022-01-31 11:50:00','1',NULL,'0','/workspace/discard/list','discard','views/workspace/discard/index'),('b5015e1ad74e4fe583c17e4ffd58e304','新增详情','discard-detail','b4189b849e0e4664a2ed518d4e4260ee','/6658c01c6a6e4f24810821352eaba0fc/null/null',3,NULL,3,'1','1','super','2022-02-10 02:38:52','super','2022-02-10 02:38:52','1',NULL,'1',NULL,'detail/:receiptNo','views/workspace/discard/detail'),('b9e42e76a4844b7e87859662a3799afd','盘点报表','inventory-report','36ce63d96376466d8998fc0dcc0729ec','/36ce63d96376466d8998fc0dcc0729ec/b9e42e76a4844b7e87859662a3799afd',2,NULL,5,'1','1','super','2022-03-08 11:34:24','super','2022-03-08 11:34:24','1',NULL,'0',NULL,'inventory','views/report/inventory/inventory'),('c3e98100d3f0426780c763aaaff6c511','库存管理','stock','6658c01c6a6e4f24810821352eaba0fc','/6658c01c6a6e4f24810821352eaba0fc/null',2,'stock',5,'1','1','super','2022-01-31 11:50:49','super','2022-01-31 11:50:49','1',NULL,'0','/workspace/stock/maintainList','stock','views/workspace/stock/index'),('cec0fe4a4efe41c58f20304d72d75215','全景视图','dashboard-index','a15d581a64414abe9ff11b2784c29991','/a15d581a64414abe9ff11b2784c29991/null',2,'index',1,'1','1','super','2022-01-31 11:43:58','super','2022-01-31 11:43:58','1',NULL,'0',NULL,'index','views/dashboard/index'),('d28c1582356a43a5bccf20c6b2b56403','新增入库','strorage-in-add','3afe8c5b0a424d09be556da75c2e1b2e','/6658c01c6a6e4f24810821352eaba0fc/null/null',3,NULL,2,'1','1','super','2022-02-03 02:56:37','super','2022-02-03 02:56:37','1',NULL,'1',NULL,'add','views/workspace/storage-in/add'),('d3375837a01e426191694a3e3a0eb590','库存维护','stock-maintain-list','c3e98100d3f0426780c763aaaff6c511','/6658c01c6a6e4f24810821352eaba0fc/null/null',3,NULL,1,'1','1','super','2022-02-10 02:40:47','super','2022-02-10 02:40:47','1',NULL,'0',NULL,'maintainList','views/workspace/stock/maintainList'),('d4499077fc364a32acb9192a7c3f30b4','入库报表','storage-in-report','36ce63d96376466d8998fc0dcc0729ec','/36ce63d96376466d8998fc0dcc0729ec/null',2,'storage-in',1,'1','1','super','2022-02-01 09:47:47','super','2022-02-01 09:47:47','1',NULL,'0',NULL,'storage-in','views/report/storage-in/storage-in'),('e6a9f906b23b4f06a574e850913bbd90','调拨操作','storage-shift','6658c01c6a6e4f24810821352eaba0fc','/6658c01c6a6e4f24810821352eaba0fc/null',2,'storage-shift',3,'1','1','super','2022-01-31 11:48:53','super','2022-01-31 11:48:53','1',NULL,'0','/workspace/storage-shift/list','storage-shift','views/workspace/storage-shift/index'),('e834976e345b4c79b1b2cde7797c2927','新增报废','discard-add','b4189b849e0e4664a2ed518d4e4260ee','/6658c01c6a6e4f24810821352eaba0fc/null/null',3,NULL,2,'1','1','super','2022-02-10 02:37:58','super','2022-02-10 02:37:58','1',NULL,'1',NULL,'add','views/workspace/discard/add'),('f1232944d3684033a64ec56dcdca84c2','库存盘点详情','stock-inventory-detail','c3e98100d3f0426780c763aaaff6c511','/6658c01c6a6e4f24810821352eaba0fc/null/null',3,NULL,4,'1','1','super','2022-02-10 02:43:52','super','2022-02-10 02:43:52','1',NULL,'1',NULL,'inventoryDetail/:receiptNo','views/workspace/stock/inventoryDetail'),('f58a2de7b3474ec785c911eb6a266ec2','角色管理','role','0e4e797b0f1345ea95235c33451f56d1','/0e4e797b0f1345ea95235c33451f56d1/f58a2de7b3474ec785c911eb6a266ec2',2,'role',2,'1','1','super','2022-01-29 03:11:44','super','2022-01-29 03:11:44','1',NULL,'0',NULL,'role','views/system/role/role'),('f5e334f25b2b466d9204e40ebee99e6b','出库报表','storage-out-report','36ce63d96376466d8998fc0dcc0729ec','/36ce63d96376466d8998fc0dcc0729ec/null',2,'storage-out',2,'1','1','super','2022-02-01 09:48:34','super','2022-02-01 09:48:34','1',NULL,'0',NULL,'storage-out','views/report/storage-out/storage-out'),('fe5c63a3ea934205ae27e46393255b02','用户管理','user','0e4e797b0f1345ea95235c33451f56d1','/0e4e797b0f1345ea95235c33451f56d1/null',2,'user',3,'1','1','super','2022-01-29 03:24:16','super','2022-01-29 03:24:16','1',NULL,'0',NULL,'user','views/system/user/user');

--管理员角色表
INSERT INTO `T_WMS_USER_ROLE` VALUES('eafe8c5b0a424d09be556d575c2e1b2e','574a612414b3464fb8e135bfc18ee777','5e9066572ab8463392c3b418edd3b914','1','sys',now(),'sys',now()),
--管理员菜单权限
INSERT INTO `t_wms_role_menu` VALUES
('5e9066572ab8463392c3b418edd3b914','a15d581a64414abe9ff11b2784c29991','1','sys',now(),'sys',now()),
('5e9066572ab8463392c3b418edd3b914','cec0fe4a4efe41c58f20304d72d75215','1','sys',now(),'sys',now()),
('5e9066572ab8463392c3b418edd3b914','6658c01c6a6e4f24810821352eaba0fc','1','sys',now(),'sys',now()),
('5e9066572ab8463392c3b418edd3b914','3afe8c5b0a424d09be556da75c2e1b2e','1','sys',now(),'sys',now()),
('5e9066572ab8463392c3b418edd3b914','9a080095778344d3af92dd86b9dbc67f','1','sys',now(),'sys',now()),
('5e9066572ab8463392c3b418edd3b914','d28c1582356a43a5bccf20c6b2b56403','1','sys',now(),'sys',now()),
('5e9066572ab8463392c3b418edd3b914','60a37808324b4a0cb0e6463d08634bdf','1','sys',now(),'sys',now()),
('5e9066572ab8463392c3b418edd3b914','987fdd4232fa494c8c42ab584d87d6cb','1','sys',now(),'sys',now()),
('5e9066572ab8463392c3b418edd3b914','1c27d3dc22404882a743953a64b390d6','1','sys',now(),'sys',now()),
('5e9066572ab8463392c3b418edd3b914','8d8e67329ad24897aacfac0f1499614e','1','sys',now(),'sys',now()),
('5e9066572ab8463392c3b418edd3b914','43df252f35f74a7a8fb9464658312729','1','sys',now(),'sys',now()),
('5e9066572ab8463392c3b418edd3b914','e6a9f906b23b4f06a574e850913bbd90','1','sys',now(),'sys',now()),
('5e9066572ab8463392c3b418edd3b914','23b372a7fce34ffe9ac419ad9b414b08','1','sys',now(),'sys',now()),
('5e9066572ab8463392c3b418edd3b914','661a2fd29f954652885d937d29688ab7','1','sys',now(),'sys',now()),
('5e9066572ab8463392c3b418edd3b914','38ee05499a554042a82b1a20996ffd43','1','sys',now(),'sys',now()),
('5e9066572ab8463392c3b418edd3b914','b4189b849e0e4664a2ed518d4e4260ee','1','sys',now(),'sys',now()),
('5e9066572ab8463392c3b418edd3b914','7b88b5e8b0d14f7c9fe2162d2ef1dd69','1','sys',now(),'sys',now()),
('5e9066572ab8463392c3b418edd3b914','e834976e345b4c79b1b2cde7797c2927','1','sys',now(),'sys',now()),
('5e9066572ab8463392c3b418edd3b914','b5015e1ad74e4fe583c17e4ffd58e304','1','sys',now(),'sys',now()),
('5e9066572ab8463392c3b418edd3b914','c3e98100d3f0426780c763aaaff6c511','1','sys',now(),'sys',now()),
('5e9066572ab8463392c3b418edd3b914','d3375837a01e426191694a3e3a0eb590','1','sys',now(),'sys',now()),
('5e9066572ab8463392c3b418edd3b914','9eb54f8f25c8475ba42ef92ee42ee1b7','1','sys',now(),'sys',now()),
('5e9066572ab8463392c3b418edd3b914','1eb64d3653da49c284316304e7a2230f','1','sys',now(),'sys',now()),
('5e9066572ab8463392c3b418edd3b914','f1232944d3684033a64ec56dcdca84c2','1','sys',now(),'sys',now()),
('5e9066572ab8463392c3b418edd3b914','36ce63d96376466d8998fc0dcc0729ec','1','sys',now(),'sys',now()),
('5e9066572ab8463392c3b418edd3b914','d4499077fc364a32acb9192a7c3f30b4','1','sys',now(),'sys',now()),
('5e9066572ab8463392c3b418edd3b914','f5e334f25b2b466d9204e40ebee99e6b','1','sys',now(),'sys',now()),
('5e9066572ab8463392c3b418edd3b914','41c65abc4ef14356bad02c65f2db0c12','1','sys',now(),'sys',now()),
('5e9066572ab8463392c3b418edd3b914','a8a048806f8843eeb350ed55349da7b2','1','sys',now(),'sys',now()),
('5e9066572ab8463392c3b418edd3b914','88eb7225d47b4fd1899823f749395a14','1','sys',now(),'sys',now()),
('5e9066572ab8463392c3b418edd3b914','0e4e797b0f1345ea95235c33451f56d1','1','sys',now(),'sys',now()),
('5e9066572ab8463392c3b418edd3b914','98d72cd6ed894fafbad6d2e8f953524d','1','sys',now(),'sys',now()),
('5e9066572ab8463392c3b418edd3b914','f58a2de7b3474ec785c911eb6a266ec2','1','sys',now(),'sys',now()),
('5e9066572ab8463392c3b418edd3b914','fe5c63a3ea934205ae27e46393255b02','1','sys',now(),'sys',now()),
('5e9066572ab8463392c3b418edd3b914','aacbfbd802bb4eeb9961eba8c7f8399a','1','sys',now(),'sys',now()),
('5e9066572ab8463392c3b418edd3b914','53414f38f4164d5aa774053f18b4fa98','1','sys',now(),'sys',now()),
('5e9066572ab8463392c3b418edd3b914','71a23ae58f36400a944e9e23c3894259','1','sys',now(),'sys',now()),
('5e9066572ab8463392c3b418edd3b914','9b09c6ae28874971838c06140dd8e0fe','1','sys',now(),'sys',now()),
('5e9066572ab8463392c3b418edd3b914','824851de333743228c45c22e088bb4f9','1','sys',now(),'sys',now()),
('5e9066572ab8463392c3b418edd3b914','20e3e09784144ea3a0f65f2955af1ae8','1','sys',now(),'sys',now()),
('5e9066572ab8463392c3b418edd3b914','05bd84d9f8734ff884c1a8dd106e2dfc','1','sys',now(),'sys',now());