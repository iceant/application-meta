delete from t_primitive_data_type where id>0;
INSERT INTO t_primitive_data_type(id, name, user_friendly_name, description) values (1, 'BIT', 'BIT', 'BIT数据类型');
INSERT INTO t_primitive_data_type(id, name, user_friendly_name, description) values (2, 'BYTE', 'BYTE', 'BYTE数据类型');
INSERT INTO t_primitive_data_type(id, name, user_friendly_name, description) values (3, 'STRING', 'STRING', 'STRING数据类型');
INSERT INTO t_primitive_data_type(id, name, user_friendly_name, description) values (4, 'BOOLEAN', 'BOOLEAN', 'BOOLEAN数据类型');
INSERT INTO t_primitive_data_type(id, name, user_friendly_name, description) values (5, 'INTEGER', 'INTEGER', 'INTEGER数据类型');
INSERT INTO t_primitive_data_type(id, name, user_friendly_name, description) values (6, 'DECIMAL', 'DECIMAL', 'DECIMAL数据类型');
INSERT INTO t_primitive_data_type(id, name, user_friendly_name, description) values (7, 'OBJECT', 'OBJECT', 'OBJECT数据类型');
INSERT INTO t_primitive_data_type(id, name, user_friendly_name, description) values (8, 'ARRAY', 'ARRAY', 'ARRAY数据类型');

delete from d_protocol_type where id>0;
INSERT INTO d_protocol_type(id, name, user_friendly_name, description, create_datetime) values(1, 'BYTE', '基于字节的数据协议', '基于字节的数据协议', CURRENT_TIMESTAMP);
INSERT INTO d_protocol_type(id, name, user_friendly_name, description, create_datetime) values(2, 'JSON', '基于JSON的数据协议', '基于JSON的数据协议', CURRENT_TIMESTAMP);

delete from t_protocol where id>0;
INSERT INTO t_protocol(id, name, user_friendly_name, description, create_datetime) values (1, 'GB32960V2016', 'GB32960V2016', 'GB32960 V2016年版本', CURRENT_TIMESTAMP);
INSERT INTO t_protocol(id, name, user_friendly_name, description, create_datetime) values (2, 'DB4403', 'DB4403V2020', 'DB4403 V2020年版本', CURRENT_TIMESTAMP);