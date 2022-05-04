drop table if exists t_protocol;
CREATE TABLE IF NOT EXISTS t_protocol
(
    id INTEGER PRIMARY KEY AUTOINCREMENT ,
    name TEXT,
    user_friendly_name TEXT, -- 显示名称
    description TEXT, -- 描述
    create_datetime TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- 创建时间
    unique (name)
);

-- 协议中定义的数据类型
drop table if exists t_protocol_data_type;
CREATE TABLE IF NOT EXISTS t_protocol_data_type
(
    id INTEGER PRIMARY KEY AUTOINCREMENT ,
    protocol_id INTEGER NOT NULL , -- 所属协议
    name TEXT, -- 名称
    user_friendly_name TEXT, -- 显示名称
    description TEXT, -- 描述
    create_datetime TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- 创建时间
    unique (protocol_id, name)
);

-- 协议中定义的数据结构
drop table if exists t_protocol_data_shape;
create table if not exists t_protocol_data_shape
(
    id INTEGER PRIMARY KEY AUTOINCREMENT ,
    protocol_id INTEGER NOT NULL ,
    name TEXT,
    user_friendly_name TEXT, -- 显示名称
    description TEXT, -- 描述
    create_datetime TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    unique (protocol_id, name)
);

-- 协议中数据结构的字段
drop table if exists t_protocol_field_shape;
create table if not exists t_protocol_field_shape
(
    id INTEGER PRIMARY KEY AUTOINCREMENT ,
    protocol_id INTEGER NOT NULL ,
    data_shape_id INTEGER NOT NULL ,
    name TEXT, -- 内部代码
    user_friendly_name TEXT,
    description TEXT,
    create_datetime TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    start_offset INTEGER, -- 起始位置
    protocol_data_type INTEGER, -- 协议的数据类型
    field_length INTEGER, -- 字段类型
    field_max_length INTEGER, -- 字段最大长度
    default_value TEXT, -- 默认值
    min_value TEXT,
    max_value TEXT,
    enum_value TEXT,
    range_value TEXT,
    value_pick_from_field_id INTEGER, -- 数据类型由哪个字段指定
    unique (data_shape_id, name)
);

-- 字段值规范
drop table if exists t_protocol_field_validation;
create table if not exists t_protocol_field_validation
(
    id INTEGER PRIMARY KEY AUTOINCREMENT ,
    name TEXT,
    user_friendly_name TEXT,
    description TEXT,
    create_datetime TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    unique (name)
);