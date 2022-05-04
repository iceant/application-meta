delete from t_primitive_data_type where id>0;
INSERT INTO t_primitive_data_type(id, name, user_friendly_name, description) values (1, 'BIT', 'BIT', 'BIT数据类型');
INSERT INTO t_primitive_data_type(id, name, user_friendly_name, description) values (2, 'BYTE', 'BYTE', 'BYTE数据类型');
INSERT INTO t_primitive_data_type(id, name, user_friendly_name, description) values (3, 'STRING', 'STRING', 'STRING数据类型');
INSERT INTO t_primitive_data_type(id, name, user_friendly_name, description) values (4, 'BOOLEAN', 'BOOLEAN', 'BOOLEAN数据类型');
INSERT INTO t_primitive_data_type(id, name, user_friendly_name, description) values (5, 'INTEGER', 'INTEGER', 'INTEGER数据类型');
INSERT INTO t_primitive_data_type(id, name, user_friendly_name, description) values (6, 'DECIMAL', 'DECIMAL', 'DECIMAL数据类型');
INSERT INTO t_primitive_data_type(id, name, user_friendly_name, description) values (7, 'OBJECT', 'OBJECT', 'OBJECT数据类型');
INSERT INTO t_primitive_data_type(id, name, user_friendly_name, description) values (8, 'ARRAY', 'ARRAY', 'ARRAY数据类型');


delete from t_option_set where id>0;
INSERT INTO t_option_set(id, name, user_friendly_name, description, create_datetime)values (1, '基本数据类型', '基本数据类型', '基本数据类型', CURRENT_TIMESTAMP);

delete from t_option where id>0;
INSERT INTO t_option(id, name, user_friendly_name, description, value_type, create_datetime) values (1, 'BIT', 'BIT比特', '基本数据类型BIT', 5 ,CURRENT_TIMESTAMP);

delete from t_option_set_option where id>0;
INSERT INTO t_option_set_option(id, option_set_id, option_id)values (1, 1, 1);