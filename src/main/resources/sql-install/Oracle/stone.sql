
/* Drop Triggers */

DROP TRIGGER TRI_st_data_filter_id;
DROP TRIGGER TRI_st_data_filter_item_id;
DROP TRIGGER TRI_st_dict_item_id;
DROP TRIGGER TRI_st_enum_value_id;
DROP TRIGGER TRI_st_group_id;
DROP TRIGGER TRI_st_group_user_id;
DROP TRIGGER TRI_st_role_id;
DROP TRIGGER TRI_st_role_operation_id;
DROP TRIGGER TRI_st_role_user_id;
DROP TRIGGER TRI_st_user_id;



/* Drop Tables */

DROP TABLE st_config CASCADE CONSTRAINTS;
DROP TABLE st_enum_value CASCADE CONSTRAINTS;
DROP TABLE st_data_filter_item CASCADE CONSTRAINTS;
DROP TABLE st_data_filter CASCADE CONSTRAINTS;
DROP TABLE st_dict_item CASCADE CONSTRAINTS;
DROP TABLE st_dict CASCADE CONSTRAINTS;
DROP TABLE st_group_user CASCADE CONSTRAINTS;
DROP TABLE st_group CASCADE CONSTRAINTS;
DROP TABLE st_role_user CASCADE CONSTRAINTS;
DROP TABLE st_role_operation CASCADE CONSTRAINTS;
DROP TABLE st_role CASCADE CONSTRAINTS;
DROP TABLE st_user CASCADE CONSTRAINTS;



/* Drop Sequences */

DROP SEQUENCE SEQ_st_data_filter_id;
DROP SEQUENCE SEQ_st_data_filter_item_id;
DROP SEQUENCE SEQ_st_dict_item_id;
DROP SEQUENCE SEQ_st_enum_value_id;
DROP SEQUENCE SEQ_st_group_id;
DROP SEQUENCE SEQ_st_group_user_id;
DROP SEQUENCE SEQ_st_role_id;
DROP SEQUENCE SEQ_st_role_operation_id;
DROP SEQUENCE SEQ_st_role_user_id;
DROP SEQUENCE SEQ_st_user_id;




/* Create Sequences */

CREATE SEQUENCE SEQ_st_data_filter_id INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE SEQ_st_data_filter_item_id INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE SEQ_st_dict_item_id INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE SEQ_st_enum_value_id INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE SEQ_st_group_id INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE SEQ_st_group_user_id INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE SEQ_st_role_id INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE SEQ_st_role_operation_id INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE SEQ_st_role_user_id INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE SEQ_st_user_id INCREMENT BY 1 START WITH 1;



/* Create Tables */

CREATE TABLE st_config
(
	-- 类的属性名
	code varchar2(120) NOT NULL,
	class_code varchar2(255) NOT NULL,
	-- 值
	value varchar2(255),
	CONSTRAINT st_config_pkey PRIMARY KEY (code, class_code)
);


CREATE TABLE st_data_filter
(
	-- 数据过滤器
	id number(10,0) NOT NULL,
	-- 名称
	name varchar2(64),
	-- 备注
	remark varchar2(256),
	CONSTRAINT pk_st_data_filter PRIMARY KEY (id)
);


CREATE TABLE st_data_filter_item
(
	-- 表达式id
	id number(10,0) NOT NULL,
	-- 前缀
	prefix_code varchar2(5),
	-- 列编码
	column_code varchar2(64),
	-- 操作符
	compare_symbol varchar2(64),
	-- 第一值
	first_value varchar2(2000),
	-- 第二值
	second_value varchar2(2000),
	-- 后缀
	suffix_code varchar2(5),
	-- 数值类型
	data_type varchar2(128),
	-- 表达式类型
	type varchar2(16),
	-- 序号
	sort_no number(10,0),
	-- 数据过滤器
	data_filter_id number(10,0),
	CONSTRAINT pk_st_data_filter_item PRIMARY KEY (id)
);


CREATE TABLE st_dict
(
	code varchar2(64) NOT NULL,
	name varchar2(20),
	sort_no number(10,0),
	remark varchar2(100),
	CONSTRAINT st_dict_pkey PRIMARY KEY (code)
);


CREATE TABLE st_dict_item
(
	id number(10,0) NOT NULL,
	label varchar2(20),
	value varchar2(20),
	sort_no number(10,0),
	remark varchar2(50),
	dict_code varchar2(64),
	CONSTRAINT st_dict_itme_pkey PRIMARY KEY (id)
);


CREATE TABLE st_enum_value
(
	id number(10,0) NOT NULL,
	-- 表达式id
	data_filter_item_id number(10,0),
	data_value varchar2(64),
	CONSTRAINT pk_st_enum_value PRIMARY KEY (id)
);


CREATE TABLE st_group
(
	-- 群组id
	id number(10,0) NOT NULL,
	-- 父群组id
	parent_id number(10,0),
	-- 群组编码
	code varchar2(20),
	-- 群组名称
	name varchar2(64),
	-- 是否叶子节点
	is_leaf char,
	-- 序号
	sort_no number(10,0),
	-- 组织类型
	type varchar2(20),
	-- 逻辑删除
	logic_deleted char,
	CONSTRAINT st_group_pkey PRIMARY KEY (id)
);


CREATE TABLE st_group_user
(
	-- id
	id number(10,0) NOT NULL,
	-- 用户Id
	user_id number(10,0) NOT NULL,
	-- 群组id
	group_id number(10,0) NOT NULL,
	-- 是否默认群组
	is_default char DEFAULT '0',
	-- 序号
	sort_no number(10,0),
	CONSTRAINT st_group_re_user_pkey PRIMARY KEY (id)
);


CREATE TABLE st_role
(
	-- 角色Id
	id number(10,0) NOT NULL,
	-- 角色名称
	name varchar2(64),
	-- 是否启用
	enabled char,
	-- 序号
	sort_no number(10,0),
	CONSTRAINT st_role_pkey PRIMARY KEY (id)
);


CREATE TABLE st_role_operation
(
	-- 主键
	id number(10,0) NOT NULL,
	-- 角色Id
	role_id number(10,0) NOT NULL,
	-- 操作代码
	operation_code varchar2(100),
	-- 数据集合Id
	data_filter_id number(10,0),
	CONSTRAINT st_role_operation_pkey PRIMARY KEY (id)
);


CREATE TABLE st_role_user
(
	id number(10,0) NOT NULL,
	-- 用户Id
	user_id number(10,0) NOT NULL,
	-- 角色Id
	role_id number(10,0) NOT NULL,
	CONSTRAINT st_role_user_pkey PRIMARY KEY (id)
);


CREATE TABLE st_user
(
	-- 用户Id
	id number(10,0) NOT NULL,
	-- 账户
	account varchar2(64) NOT NULL,
	-- 密码
	password varchar2(256),
	-- 用户名
	name varchar2(64),
	-- 手机号码
	phone varchar2(64),
	-- 电子邮箱
	email varchar2(64),
	-- 是否可用
	enabled char,
	-- 是否删除
	logic_deleted char,
	CONSTRAINT st_user_pkey PRIMARY KEY (id)
);



/* Create Foreign Keys */

ALTER TABLE st_data_filter_item
	ADD CONSTRAINT fk_st_data__reference_st_data_ FOREIGN KEY (data_filter_id)
	REFERENCES st_data_filter (id)
;


ALTER TABLE st_enum_value
	ADD CONSTRAINT fk_st_enum__reference_st_data_ FOREIGN KEY (data_filter_item_id)
	REFERENCES st_data_filter_item (id)
;


ALTER TABLE st_dict_item
	ADD FOREIGN KEY (dict_code)
	REFERENCES st_dict (code)
;


ALTER TABLE st_group_user
	ADD FOREIGN KEY (group_id)
	REFERENCES st_group (id)
;


ALTER TABLE st_role_user
	ADD CONSTRAINT st_role_user_role_id_fkey FOREIGN KEY (role_id)
	REFERENCES st_role (id)
;


ALTER TABLE st_role_operation
	ADD CONSTRAINT st_role_operation_fkey FOREIGN KEY (role_id)
	REFERENCES st_role (id)
;


ALTER TABLE st_role_user
	ADD CONSTRAINT st_role_user_user_id_fkey FOREIGN KEY (user_id)
	REFERENCES st_user (id)
;


ALTER TABLE st_group_user
	ADD CONSTRAINT group_re_user_user_id_fkey FOREIGN KEY (user_id)
	REFERENCES st_user (id)
;



/* Create Triggers */

CREATE OR REPLACE TRIGGER TRI_st_data_filter_id BEFORE INSERT ON st_data_filter
FOR EACH ROW
BEGIN
	SELECT SEQ_st_data_filter_id.nextval
	INTO :new.id
	FROM dual;
END;

/

CREATE OR REPLACE TRIGGER TRI_st_data_filter_item_id BEFORE INSERT ON st_data_filter_item
FOR EACH ROW
BEGIN
	SELECT SEQ_st_data_filter_item_id.nextval
	INTO :new.id
	FROM dual;
END;

/

CREATE OR REPLACE TRIGGER TRI_st_dict_item_id BEFORE INSERT ON st_dict_item
FOR EACH ROW
BEGIN
	SELECT SEQ_st_dict_item_id.nextval
	INTO :new.id
	FROM dual;
END;

/

CREATE OR REPLACE TRIGGER TRI_st_enum_value_id BEFORE INSERT ON st_enum_value
FOR EACH ROW
BEGIN
	SELECT SEQ_st_enum_value_id.nextval
	INTO :new.id
	FROM dual;
END;

/

CREATE OR REPLACE TRIGGER TRI_st_group_id BEFORE INSERT ON st_group
FOR EACH ROW
BEGIN
	SELECT SEQ_st_group_id.nextval
	INTO :new.id
	FROM dual;
END;

/

CREATE OR REPLACE TRIGGER TRI_st_group_user_id BEFORE INSERT ON st_group_user
FOR EACH ROW
BEGIN
	SELECT SEQ_st_group_user_id.nextval
	INTO :new.id
	FROM dual;
END;

/

CREATE OR REPLACE TRIGGER TRI_st_role_id BEFORE INSERT ON st_role
FOR EACH ROW
BEGIN
	SELECT SEQ_st_role_id.nextval
	INTO :new.id
	FROM dual;
END;

/

CREATE OR REPLACE TRIGGER TRI_st_role_operation_id BEFORE INSERT ON st_role_operation
FOR EACH ROW
BEGIN
	SELECT SEQ_st_role_operation_id.nextval
	INTO :new.id
	FROM dual;
END;

/

CREATE OR REPLACE TRIGGER TRI_st_role_user_id BEFORE INSERT ON st_role_user
FOR EACH ROW
BEGIN
	SELECT SEQ_st_role_user_id.nextval
	INTO :new.id
	FROM dual;
END;

/

CREATE OR REPLACE TRIGGER TRI_st_user_id BEFORE INSERT ON st_user
FOR EACH ROW
BEGIN
	SELECT SEQ_st_user_id.nextval
	INTO :new.id
	FROM dual;
END;

/




/* Comments */

COMMENT ON COLUMN st_config.code IS '类的属性名';
COMMENT ON COLUMN st_config.value IS '值';
COMMENT ON COLUMN st_data_filter.id IS '数据过滤器';
COMMENT ON COLUMN st_data_filter.name IS '名称';
COMMENT ON COLUMN st_data_filter.remark IS '备注';
COMMENT ON COLUMN st_data_filter_item.id IS '表达式id';
COMMENT ON COLUMN st_data_filter_item.prefix_code IS '前缀';
COMMENT ON COLUMN st_data_filter_item.column_code IS '列编码';
COMMENT ON COLUMN st_data_filter_item.compare_symbol IS '操作符';
COMMENT ON COLUMN st_data_filter_item.first_value IS '第一值';
COMMENT ON COLUMN st_data_filter_item.second_value IS '第二值';
COMMENT ON COLUMN st_data_filter_item.suffix_code IS '后缀';
COMMENT ON COLUMN st_data_filter_item.data_type IS '数值类型';
COMMENT ON COLUMN st_data_filter_item.type IS '表达式类型';
COMMENT ON COLUMN st_data_filter_item.sort_no IS '序号';
COMMENT ON COLUMN st_data_filter_item.data_filter_id IS '数据过滤器';
COMMENT ON COLUMN st_enum_value.data_filter_item_id IS '表达式id';
COMMENT ON COLUMN st_group.id IS '群组id';
COMMENT ON COLUMN st_group.parent_id IS '父群组id';
COMMENT ON COLUMN st_group.code IS '群组编码';
COMMENT ON COLUMN st_group.name IS '群组名称';
COMMENT ON COLUMN st_group.is_leaf IS '是否叶子节点';
COMMENT ON COLUMN st_group.sort_no IS '序号';
COMMENT ON COLUMN st_group.type IS '组织类型';
COMMENT ON COLUMN st_group.logic_deleted IS '逻辑删除';
COMMENT ON COLUMN st_group_user.id IS 'id';
COMMENT ON COLUMN st_group_user.user_id IS '用户Id';
COMMENT ON COLUMN st_group_user.group_id IS '群组id';
COMMENT ON COLUMN st_group_user.is_default IS '是否默认群组';
COMMENT ON COLUMN st_group_user.sort_no IS '序号';
COMMENT ON COLUMN st_role.id IS '角色Id';
COMMENT ON COLUMN st_role.name IS '角色名称';
COMMENT ON COLUMN st_role.enabled IS '是否启用';
COMMENT ON COLUMN st_role.sort_no IS '序号';
COMMENT ON COLUMN st_role_operation.id IS '主键';
COMMENT ON COLUMN st_role_operation.role_id IS '角色Id';
COMMENT ON COLUMN st_role_operation.operation_code IS '操作代码';
COMMENT ON COLUMN st_role_operation.data_filter_id IS '数据集合Id';
COMMENT ON COLUMN st_role_user.user_id IS '用户Id';
COMMENT ON COLUMN st_role_user.role_id IS '角色Id';
COMMENT ON COLUMN st_user.id IS '用户Id';
COMMENT ON COLUMN st_user.account IS '账户';
COMMENT ON COLUMN st_user.password IS '密码';
COMMENT ON COLUMN st_user.name IS '用户名';
COMMENT ON COLUMN st_user.phone IS '手机号码';
COMMENT ON COLUMN st_user.email IS '电子邮箱';
COMMENT ON COLUMN st_user.enabled IS '是否可用';
COMMENT ON COLUMN st_user.logic_deleted IS '是否删除';
/
