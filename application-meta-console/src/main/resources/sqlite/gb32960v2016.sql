delete from t_primitive_data_type where id>0;
INSERT INTO t_primitive_data_type(id, name, user_friendly_name, description) values (1, 'BIT', 'BIT', 'BIT数据类型');
INSERT INTO t_primitive_data_type(id, name, user_friendly_name, description) values (2, 'BYTE', 'BYTE', 'BYTE数据类型');
INSERT INTO t_primitive_data_type(id, name, user_friendly_name, description) values (3, 'STRING', 'STRING', 'STRING数据类型');
INSERT INTO t_primitive_data_type(id, name, user_friendly_name, description) values (4, 'BOOLEAN', 'BOOLEAN', 'BOOLEAN数据类型');
INSERT INTO t_primitive_data_type(id, name, user_friendly_name, description) values (5, 'INTEGER', 'INTEGER', 'INTEGER数据类型');
INSERT INTO t_primitive_data_type(id, name, user_friendly_name, description) values (6, 'DECIMAL', 'DECIMAL', 'DECIMAL数据类型');
INSERT INTO t_primitive_data_type(id, name, user_friendly_name, description) values (7, 'OBJECT', 'OBJECT', 'OBJECT数据类型');
INSERT INTO t_primitive_data_type(id, name, user_friendly_name, description) values (8, 'ARRAY', 'ARRAY', 'ARRAY数据类型');

delete from t_protocol where id>0;
INSERT INTO t_protocol(id, name, user_friendly_name, description) values (1, 'GB32960V2016', 'GB32960 V2016', 'GB32960 2016版本');


delete from t_protocol_data_type where id>0;
INSERT INTO t_protocol_data_type(id, protocol_id, name, user_friendly_name, description) values (1, 1, 'BYTE', '字节', '无符号单字节整型(字节:8位)');
INSERT INTO t_protocol_data_type(id, protocol_id, name, user_friendly_name, description) values (2, 1, 'WORD', '字', '无符号双字节整型(字:16位)');
INSERT INTO t_protocol_data_type(id, protocol_id, name, user_friendly_name, description) values (3, 1, 'DWORD', '双字', '无符号四字节整型(双字:32位)');
INSERT INTO t_protocol_data_type(id, protocol_id, name, user_friendly_name, description) values (4, 1, 'BYTE[n]', 'BLOB', 'n 字节');
INSERT INTO t_protocol_data_type(id, protocol_id, name, user_friendly_name, description) values (5, 1, 'STRING', '字符串', 'ASCII字符码，若无数据放一个0终结符;含汉字时，采用区位码编码，占用2字节，编码见GB18030');

delete from t_protocol_data_shape where id>0;
INSERT INTO t_protocol_data_shape(id, protocol_id, name, user_friendly_name, description) values (1, '1', 'GB32960V2016Packet', 'GB32960V2016数据包', 'GB32960V2016数据包');

delete from t_protocol_field_shape where id>0;
INSERT INTO t_protocol_field_shape(id, protocol_id, data_shape_id, name, user_friendly_name, description, protocol_data_type, field_length, start_offset, default_value) values (1, 1, 1, 'startFlag', '起始符', '固定值: ##, 0x2323', 5, 2, 0, '##');
INSERT INTO t_protocol_field_shape(id, protocol_id, data_shape_id, name, user_friendly_name, description, protocol_data_type, field_length, start_offset, enum_value) values (2, 1, 1, 'commandFlag', '命令标识', '命令单元命令标识', 1, 1, 2,  '{"0x01":"车辆登入", "0x02":"实时信息上报", "0x03":"补发信息上报", "0x04":"车辆登出", "0x05":"平台登入", "0x06":"平台登出"}');
INSERT INTO t_protocol_field_shape(id, protocol_id, data_shape_id, name, user_friendly_name, description, protocol_data_type, field_length, start_offset, enum_value) values (3, 1, 1, 'responseFlag', '应答标志', '命令单元应答标志', 1, 1, 3, '{"0x01", "成功", "0x02":"错误", "0x03":"VIN重复", "0xFE":"命令"}');
INSERT INTO t_protocol_field_shape(id, protocol_id, data_shape_id, name, user_friendly_name, description, protocol_data_type, field_length, start_offset) values (4, 1, 1, 'VIN', '唯一识别码', '当传输车辆信息时，遵守GB16735规定，传输其它数据，使用自定义编码', 5, 17, 4);
INSERT INTO t_protocol_field_shape(id, protocol_id, data_shape_id, name, user_friendly_name, description, protocol_data_type, field_length, start_offset, enum_value) values (5, 1, 1, 'encryptMethod', '数据单元加密方式', '数据加密方式', 1, 1, 21, '{"0x01", "数据不加密", "0x02":"数据经过RSA算法加密", "0x03":"数据经过AES128加密", "0xFE":"异常", "0xFF":"无效"}');
INSERT INTO t_protocol_field_shape(id, protocol_id, data_shape_id, name, user_friendly_name, description, protocol_data_type, field_length, start_offset, range_value) values (6, 1, 1, 'dataUnitSize', '数据单元长度', '数据单元长度', 2, 2, 22, '[0~65535]');
INSERT INTO t_protocol_field_shape(id, protocol_id, data_shape_id, name, user_friendly_name, description, protocol_data_type, field_length, start_offset, value_pick_from_field_id) values (7, 1, 1, 'dataUnit', '数据单元', '数据单元', 1, -1, 24, 2);
INSERT INTO t_protocol_field_shape(id, protocol_id, data_shape_id, name, user_friendly_name, description, protocol_data_type, field_length, start_offset) values (8, 1, 1, 'crc', '校验码', '校验码采用BCC异或校验算法', 1, 1, -1);



