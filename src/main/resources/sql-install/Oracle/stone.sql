

-- Drop Tables 

DROP TABLE st_config CASCADE CONSTRAINTS^_^
DROP TABLE st_enum_value CASCADE CONSTRAINTS^_^
DROP TABLE st_data_filter_item CASCADE CONSTRAINTS^_^
DROP TABLE st_data_filter CASCADE CONSTRAINTS^_^
DROP TABLE st_dict_item CASCADE CONSTRAINTS^_^
DROP TABLE st_dict CASCADE CONSTRAINTS^_^
DROP TABLE st_group_user CASCADE CONSTRAINTS^_^
DROP TABLE st_group CASCADE CONSTRAINTS^_^
DROP TABLE st_role_user CASCADE CONSTRAINTS^_^
DROP TABLE st_role_operation CASCADE CONSTRAINTS^_^
DROP TABLE st_role CASCADE CONSTRAINTS^_^
DROP TABLE st_user CASCADE CONSTRAINTS^_^



-- Drop Sequences 

DROP SEQUENCE SEQ_st_data_filter_id^_^
DROP SEQUENCE SEQ_st_data_filter_item_id^_^
DROP SEQUENCE SEQ_st_dict_item_id^_^
DROP SEQUENCE SEQ_st_enum_value_id^_^
DROP SEQUENCE SEQ_st_group_id^_^
DROP SEQUENCE SEQ_st_group_user_id^_^
DROP SEQUENCE SEQ_st_role_id^_^
DROP SEQUENCE SEQ_st_role_operation_id^_^
DROP SEQUENCE SEQ_st_role_user_id^_^
DROP SEQUENCE SEQ_st_user_id^_^




-- Create Sequences 

CREATE SEQUENCE SEQ_st_data_filter_id INCREMENT BY 1 START WITH 1^_^
CREATE SEQUENCE SEQ_st_data_filter_item_id INCREMENT BY 1 START WITH 1^_^
CREATE SEQUENCE SEQ_st_dict_item_id INCREMENT BY 1 START WITH 1^_^
CREATE SEQUENCE SEQ_st_enum_value_id INCREMENT BY 1 START WITH 1^_^
CREATE SEQUENCE SEQ_st_group_id INCREMENT BY 1 START WITH 1^_^
CREATE SEQUENCE SEQ_st_group_user_id INCREMENT BY 1 START WITH 1^_^
CREATE SEQUENCE SEQ_st_role_id INCREMENT BY 1 START WITH 1^_^
CREATE SEQUENCE SEQ_st_role_operation_id INCREMENT BY 1 START WITH 1^_^
CREATE SEQUENCE SEQ_st_role_user_id INCREMENT BY 1 START WITH 1^_^
CREATE SEQUENCE SEQ_st_user_id INCREMENT BY 1 START WITH 1^_^



-- Create Tables 

CREATE TABLE st_config
(
	code varchar2(120) NOT NULL,
	class_code varchar2(255) NOT NULL,
	value varchar2(255),
	CONSTRAINT st_config_pkey PRIMARY KEY (code, class_code)
)^_^


CREATE TABLE st_data_filter
(
	id number(10,0) NOT NULL,
	name varchar2(64),
	remark varchar2(256),
	CONSTRAINT pk_st_data_filter PRIMARY KEY (id)
)^_^


CREATE TABLE st_data_filter_item
(
	id number(10,0) NOT NULL,
	prefix_code varchar2(5),
	column_code varchar2(64),
	compare_symbol varchar2(64),
	first_value varchar2(2000),
	second_value varchar2(2000),
	suffix_code varchar2(5),
	data_type varchar2(128),
	type varchar2(16),
	sort_no number(10,0),
	data_filter_id number(10,0),
	CONSTRAINT pk_st_data_filter_item PRIMARY KEY (id)
)^_^


CREATE TABLE st_dict
(
	code varchar2(64) NOT NULL,
	name varchar2(20),
	sort_no number(10,0),
	remark varchar2(100),
	CONSTRAINT st_dict_pkey PRIMARY KEY (code)
)^_^


CREATE TABLE st_dict_item
(
	id number(10,0) NOT NULL,
	label varchar2(20),
	value varchar2(20),
	sort_no number(10,0),
	remark varchar2(50),
	dict_code varchar2(64),
	CONSTRAINT st_dict_itme_pkey PRIMARY KEY (id)
)^_^


CREATE TABLE st_enum_value
(
	id number(10,0) NOT NULL,
	data_filter_item_id number(10,0),
	data_value varchar2(64),
	CONSTRAINT pk_st_enum_value PRIMARY KEY (id)
)^_^


CREATE TABLE st_group
(
	id number(10,0) NOT NULL,
	parent_id number(10,0),
	code varchar2(20),
	name varchar2(64),
	is_leaf char,
	sort_no number(10,0),
	type varchar2(20),
	logic_deleted char,
	CONSTRAINT st_group_pkey PRIMARY KEY (id)
)^_^


CREATE TABLE st_group_user
(
	id number(10,0) NOT NULL,
	user_id number(10,0) NOT NULL,
	group_id number(10,0) NOT NULL,
	is_default char DEFAULT '0',
	sort_no number(10,0),
	CONSTRAINT st_group_re_user_pkey PRIMARY KEY (id)
)^_^


CREATE TABLE st_role
(
	id number(10,0) NOT NULL,
	name varchar2(64),
	enabled char,
	sort_no number(10,0),
	CONSTRAINT st_role_pkey PRIMARY KEY (id)
)^_^


CREATE TABLE st_role_operation
(
	id number(10,0) NOT NULL,
	role_id number(10,0) NOT NULL,
	operation_code varchar2(100),
	data_filter_id number(10,0),
	CONSTRAINT st_role_operation_pkey PRIMARY KEY (id)
)^_^


CREATE TABLE st_role_user
(
	id number(10,0) NOT NULL,
	user_id number(10,0) NOT NULL,
	role_id number(10,0) NOT NULL,
	CONSTRAINT st_role_user_pkey PRIMARY KEY (id)
)^_^


CREATE TABLE st_user
(
	id number(10,0) NOT NULL,
	account varchar2(64) NOT NULL,
	password varchar2(256),
	name varchar2(64),
	type varchar2(16),
	phone varchar2(64),
	email varchar2(64),
	enabled char,
	logic_deleted char,
	CONSTRAINT st_user_pkey PRIMARY KEY (id)
)^_^



-- Create Foreign Keys 

ALTER TABLE st_data_filter_item
	ADD CONSTRAINT fk_st_data__reference_st_data_ FOREIGN KEY (data_filter_id)
	REFERENCES st_data_filter (id)
^_^


ALTER TABLE st_enum_value
	ADD CONSTRAINT fk_st_enum__reference_st_data_ FOREIGN KEY (data_filter_item_id)
	REFERENCES st_data_filter_item (id)
^_^


ALTER TABLE st_dict_item
	ADD FOREIGN KEY (dict_code)
	REFERENCES st_dict (code)
^_^


ALTER TABLE st_group_user
	ADD FOREIGN KEY (group_id)
	REFERENCES st_group (id)
^_^


ALTER TABLE st_role_user
	ADD CONSTRAINT st_role_user_role_id_fkey FOREIGN KEY (role_id)
	REFERENCES st_role (id)
^_^


ALTER TABLE st_role_operation
	ADD CONSTRAINT st_role_operation_fkey FOREIGN KEY (role_id)
	REFERENCES st_role (id)
^_^


ALTER TABLE st_group_user
	ADD CONSTRAINT group_re_user_user_id_fkey FOREIGN KEY (user_id)
	REFERENCES st_user (id)
^_^


ALTER TABLE st_role_user
	ADD CONSTRAINT st_role_user_user_id_fkey FOREIGN KEY (user_id)
	REFERENCES st_user (id)
^_^


-- Comments 

COMMENT ON COLUMN st_config.code IS '类的属性名'^_^
COMMENT ON COLUMN st_config.value IS '值'^_^
COMMENT ON COLUMN st_data_filter.id IS '数据过滤器'^_^
COMMENT ON COLUMN st_data_filter.name IS '名称'^_^
COMMENT ON COLUMN st_data_filter.remark IS '备注'^_^
COMMENT ON COLUMN st_data_filter_item.id IS '表达式id'^_^
COMMENT ON COLUMN st_data_filter_item.prefix_code IS '前缀'^_^
COMMENT ON COLUMN st_data_filter_item.column_code IS '列编码'^_^
COMMENT ON COLUMN st_data_filter_item.compare_symbol IS '操作符'^_^
COMMENT ON COLUMN st_data_filter_item.first_value IS '第一值'^_^
COMMENT ON COLUMN st_data_filter_item.second_value IS '第二值'^_^
COMMENT ON COLUMN st_data_filter_item.suffix_code IS '后缀'^_^
COMMENT ON COLUMN st_data_filter_item.data_type IS '数值类型'^_^
COMMENT ON COLUMN st_data_filter_item.type IS '表达式类型'^_^
COMMENT ON COLUMN st_data_filter_item.sort_no IS '序号'^_^
COMMENT ON COLUMN st_data_filter_item.data_filter_id IS '数据过滤器'^_^
COMMENT ON COLUMN st_enum_value.data_filter_item_id IS '表达式id'^_^
COMMENT ON COLUMN st_group.id IS '群组id'^_^
COMMENT ON COLUMN st_group.parent_id IS '父群组id'^_^
COMMENT ON COLUMN st_group.code IS '群组编码'^_^
COMMENT ON COLUMN st_group.name IS '群组名称'^_^
COMMENT ON COLUMN st_group.is_leaf IS '是否叶子节点'^_^
COMMENT ON COLUMN st_group.sort_no IS '序号'^_^
COMMENT ON COLUMN st_group.type IS '组织类型'^_^
COMMENT ON COLUMN st_group.logic_deleted IS '逻辑删除'^_^
COMMENT ON COLUMN st_group_user.id IS 'id'^_^
COMMENT ON COLUMN st_group_user.user_id IS '用户Id'^_^
COMMENT ON COLUMN st_group_user.group_id IS '群组id'^_^
COMMENT ON COLUMN st_group_user.is_default IS '是否默认群组'^_^
COMMENT ON COLUMN st_group_user.sort_no IS '序号'^_^
COMMENT ON COLUMN st_role.id IS '角色Id'^_^
COMMENT ON COLUMN st_role.name IS '角色名称'^_^
COMMENT ON COLUMN st_role.enabled IS '是否启用'^_^
COMMENT ON COLUMN st_role.sort_no IS '序号'^_^
COMMENT ON COLUMN st_role_operation.id IS '主键'^_^
COMMENT ON COLUMN st_role_operation.role_id IS '角色Id'^_^
COMMENT ON COLUMN st_role_operation.operation_code IS '操作代码'^_^
COMMENT ON COLUMN st_role_operation.data_filter_id IS '数据集合Id'^_^
COMMENT ON COLUMN st_role_user.user_id IS '用户Id'^_^
COMMENT ON COLUMN st_role_user.role_id IS '角色Id'^_^
COMMENT ON COLUMN st_user.id IS '用户Id'^_^
COMMENT ON COLUMN st_user.account IS '账户'^_^
COMMENT ON COLUMN st_user.password IS '密码'^_^
COMMENT ON COLUMN st_user.name IS '用户名'^_^
COMMENT ON COLUMN st_user.phone IS '手机号码'^_^
COMMENT ON COLUMN st_user.email IS '电子邮箱'^_^
COMMENT ON COLUMN st_user.enabled IS '是否可用'^_^
COMMENT ON COLUMN st_user.logic_deleted IS '是否删除'^_^

--end--create
