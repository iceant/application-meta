-- 原始数据类型
create table if not exists t_primitive_data_type
(
    id INTEGER PRIMARY KEY AUTOINCREMENT ,
    name TEXT NOT NULL , -- BIT, BYTE, STRING, BOOLEAN, INTEGER, DECIMAL, OBJECT, ARRAY
    user_friendly_name TEXT,
    description TEXT,
    unique (name)
);

-- 选项集
create table if not exists t_option_set
(
    id INTEGER PRIMARY KEY AUTOINCREMENT ,
    name TEXT NOT NULL ,
    user_friendly_name TEXT,
    description TEXT,
    create_datetime TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    unique (name)
);

-- 选项
create table if not exists t_option
(
    id INTEGER PRIMARY KEY AUTOINCREMENT ,
    name TEXT NOT NULL ,
    user_friendly_name TEXT,
    description TEXT,
    create_datetime TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    value_type INTEGER NOT NULL , -- 数值类型, 在 t_primitive_data_type 中定义
    length INTEGER, -- 值长度
    max_length INTEGER, -- 值最大长度,
    max_value TEXT, -- 用字符串存储的最大值
    min_value TEXT, -- 用字符串存储的最小值
    enum_value TEXT, -- 用逗号分割的可选值,JSON格式
    range_value TEXT, -- 用 (min, max) 表示的值, [ 表示包含， ( 表示不包含
    default_value TEXT, -- 用 TEXT 存储的默认值，根据数据类型进行转换
    unique (name)
);

-- 选项集中选项内容
create table if not exists t_option_set_option
(
    id INTEGER PRIMARY KEY AUTOINCREMENT ,
    option_set_id INTEGER NOT NULL ,
    option_id INTEGER NOT NULL ,
    UNIQUE (option_set_id, option_id)
);

-- 选项驱动表
create table if not exists t_option_driver
(
    id INTEGER PRIMARY KEY AUTOINCREMENT ,
    option_id INTEGER NOT NULL , -- 选项ID
    type INTEGER NOT NULL ,  -- 类型: 1-ENABLE 可选, 2-DISABLE 不可选, 3-SELECTED 被选中
    expression TEXT -- 表达式
);

-- 协议类型
create table if not exists t_protocol_type
(
    id INTEGER PRIMARY KEY AUTOINCREMENT ,
    name TEXT NOT NULL ,
    user_friendly_name TEXT,
    description TEXT,
    create_datetime TIMESTAMP default CURRENT_TIMESTAMP,
    communicate_type INTEGER NOT NULL , --  通讯方式: 1-SOCKET, 2-HTTP, 3-HTTPS
    unique (name)
);

-- 协议中的数据类型
create table if not exists t_protocol_data_type
(
    id INTEGER PRIMARY KEY AUTOINCREMENT ,
    name TEXT NOT NULL ,
    user_friendly_name TEXT,
    description TEXT,
    create_datetime TIMESTAMP default CURRENT_TIMESTAMP,
    unique (name)
);

drop table if exists t_protocol;
create table if not exists t_protocol
(
    id INTEGER PRIMARY KEY AUTOINCREMENT ,
    name TEXT NOT NULL ,
    revision TEXT , -- 协议版本
    user_friendly_name TEXT,
    description TEXT,
    create_datetime TIMESTAMP default CURRENT_TIMESTAMP,
    unique (name)
);



create table if not exists t_protocol_data_shape
(
    id INTEGER PRIMARY KEY AUTOINCREMENT ,
    name TEXT NOT NULL ,
    user_friendly_name TEXT,
    description TEXT,
    create_datetime TIMESTAMP default CURRENT_TIMESTAMP,
    unique (name)
);