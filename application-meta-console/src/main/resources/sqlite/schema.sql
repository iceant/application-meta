----------------
-- 菜单
----------------
create table if not exists t_app_menu
(
    id INTEGER PRIMARY KEY AUTOINCREMENT ,
    name TEXT,
    user_friendly_name TEXT,
    description TEXT,
    icon TEXT,
    path TEXT,
    view TEXT,
    parent_id INTEGER DEFAULT 0,
    order_index INTEGER DEFAULT 0,
    creation_datetime TIMESTAMP
);

----------------
-- 应用模型
----------------
create table if not exists t_data_type
(
    id INTEGER PRIMARY KEY AUTOINCREMENT ,
    name TEXT,
    user_friendly_name TEXT,
    description TEXT,
    creation_datetime TIMESTAMP,
    unique(name)
);

create table if not exists t_application
(
    id INTEGER PRIMARY KEY AUTOINCREMENT ,
    name TEXT,
    user_friendly_name TEXT,
    description TEXT,
    creation_datetime TIMESTAMP,
    unique(name)
);

create table if not exists t_data_shape
(
    id INTEGER PRIMARY KEY AUTOINCREMENT ,
    name TEXT,
    user_friendly_name TEXT,
    description TEXT,
    creation_datetime TIMESTAMP,
    application_id INTEGER NOT NULL ,
    unique(application_id, name)
);

create table if not exists t_field_shape
(
    id INTEGER PRIMARY KEY AUTOINCREMENT ,
    application_id INTEGER ,
    data_shape_id INTEGER ,
    data_type_id INTEGER ,
    name TEXT,
    user_friendly_name TEXT,
    description TEXT,
    creation_datetime TIMESTAMP,
    is_primary_key BOOLEAN DEFAULT FALSE,
    is_nullable BOOLEAN DEFAULT TRUE,
    is_foreign_key BOOLEAN DEFAULT FALSE, -- 是否对外部引用
    foreign_data_shape TEXT, -- 外部引用时，记录应用的数据类型名称
    foreign_field_name TEXT , -- 外部引用的字段名称
    default_value TEXT, -- 以字符串存储的默认值
    min_value TEXT, -- 最小值
    max_value TEXT, -- 最大值
    field_length INTEGER, -- 字段长度, precision
    field_max_length INTEGER, -- 字段最大长度
    enum_values TEXT, -- 用文本记录的一个数组，用逗号分割
    range_values TEXT, -- 用文本记录的边界值
    unit TEXT, -- 单位,比如g,km, kWh, Ω, ℃等
    scale INTEGER, -- 小数位数
    value_offset INTEGER, -- 数值偏移量, 实际值=字面值 * 分辨率 + 偏移量
    value_resolution DECIMAL, -- 数值分辨率, 实际值=字面值 * 分辨率 + 偏移量
    unique (data_shape_id, name)
);
