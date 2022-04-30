create table if not exists t_meta_datashape
(
    id INTEGER PRIMARY KEY AUTOINCREMENT ,
    name TEXT,
    user_friendly_name TEXT,
    description TEXT,
    creation_datetime TIMESTAMP,
    unique (name)
);

create table if not exists t_meta_fieldshape
(
    id INTEGER PRIMARY KEY AUTOINCREMENT ,
    datashape_id INTEGER NOT NULL ,
    name TEXT,
    user_friendly_name TEXT,
    description TEXT,
    creation_datetime TIMESTAMP,
    data_type INTEGER, -- 数据类型: 1:STRING, 2:BOOLEAN, 3:INTEGER, 4:DECIMAL, 5:BLOB, 6:OBJECT, 7:ARRAY
    is_nullable BOOLEAN, -- 是否可以为空，如果不为空，表示必填项
    is_primary_key BOOLEAN, -- 是否是主键
    unique (datashape_id, name)
);

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
    parent_id INTEGER DEFAULT 0,
    creation_datetime TIMESTAMP
);