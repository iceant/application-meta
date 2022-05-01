delete from t_data_type where id>0;
INSERT INTO t_data_type(id, name) values (1, 'STRING');
INSERT INTO t_data_type(id, name) values (2, 'BOOLEAN');
INSERT INTO t_data_type(id, name) values (3, 'INTEGER');
INSERT INTO t_data_type(id, name) values (4, 'DECIMAL');
INSERT INTO t_data_type(id, name) values (5, 'BLOB');
INSERT INTO t_data_type(id, name) values (6, 'OBJECT');
INSERT INTO t_data_type(id, name) values (7, 'ARRAY');

delete from t_app_menu where id>0;
INSERT INTO t_app_menu(id, name, icon, path, view, creation_datetime, parent_id, order_index) values(1, '业务数据模型', 'grid', '/data', null, CURRENT_TIMESTAMP, 0, 1);
INSERT INTO t_app_menu(id, name, icon, path, view, creation_datetime, parent_id, order_index) values(2, '基本数据类型', 'star', '/data/type', 'DataType', CURRENT_TIMESTAMP, 1, 1);
INSERT INTO t_app_menu(id, name, icon, path, view, creation_datetime, parent_id, order_index) values(3, '业务数据类型', 'star', '/data/shape', 'DataShape', CURRENT_TIMESTAMP, 1, 2);

delete from t_data_shape where id >0;
INSERT INTO t_data_shape(id, name, user_friendly_name, description, creation_datetime, application_id) values (1, 'GB32960协议包', 'GB32960协议包', 'GB32960协议包', CURRENT_TIMESTAMP, 1);
INSERT INTO t_data_shape(id, name, user_friendly_name, description, creation_datetime, application_id) values (2, 'DB4403协议包', 'DB4403协议包', 'DB4403协议包', CURRENT_TIMESTAMP, 1);
