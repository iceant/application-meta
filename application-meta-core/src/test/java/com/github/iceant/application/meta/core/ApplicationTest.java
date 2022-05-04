package com.github.iceant.application.meta.core;


import com.github.iceant.application.meta.core.converters.StringToInteger;
import com.github.iceant.application.meta.core.utils.JsonUtil;

import java.math.BigDecimal;
import java.security.Key;
import java.util.Map;
import java.util.function.Consumer;

class ApplicationTest {
    public static void main(String[] args) {
        Application application = new Application().setName("GB32960V2016");
        application.addDataType(new DataType().setName("BYTE").setLength(1).setValueType(PrimitiveDataType.BYTE));
        application.addDataType(new DataType().setName("WORD").setLength(2).setValueType(PrimitiveDataType.BYTE));
        application.addDataType(new DataType().setName("DWORD").setLength(4).setValueType(PrimitiveDataType.BYTE));
        application.addDataType(new DataType().setName("BLOB").setLength(FieldLength.UNKNOWN).setValueType(PrimitiveDataType.BYTE));
        application.addDataType(new DataType().setName("STRING").setLength(FieldLength.UNKNOWN).setValueType(PrimitiveDataType.BYTE));

        application.addDataShape(new DataShape()
                .setName("GB32960V2016Packet").setUserFriendlyName("GB32960V2016数据包").setOrdinal(0)
                        .setBooleanAspect(Aspects.ASPECTS_ISINPUT, true)
                .addFieldShape(new FieldShape().setName("startFlag")
                        .setStringAspect(Aspects.ASPECTS_DATA_TYPE, "STRING")
                        .setFieldLength(2)
                        .setOrdinal(1)
                        .setIsNullable(false)
                        .setDefaultValue("##")
                ).addFieldShape(new FieldShape().setName("commandFlag")
                        .setDataType("BYTE")
                        .setFieldLength(1)
                        .setOrdinal(2)
                        .setIntegerAspect(Aspects.ASPECTS_OFFSET, 2)
                        .setIsNullable(false)
                        .setEnumValue(EnumValue.of(KeyValue.of("车辆登入", 0x01),
                                KeyValue.of("实时信息上报", 0x02),
                                KeyValue.of("补发信息上报", 0x03),
                                KeyValue.of("车辆登出", 0x04),
                                KeyValue.of("平台登入", 0x05),
                                KeyValue.of("平台登出", 0x06)
                        ))
                ).addFieldShape(new FieldShape().setName("responseFlag")
                        .setDataType("BYTE")
                        .setFieldLength(1)
                        .setOrdinal(3)
                        .setIntegerAspect(Aspects.ASPECTS_OFFSET, 3)
                        .setEnumValue(EnumValue.of(KeyValue.of("成功", 0x01), KeyValue.of("错误", 0x02), KeyValue.of("VIN重复", 0x03), KeyValue.of("错误", 0xFE)))
                ).addFieldShape(new FieldShape().setName("vin")
                        .setDataType("STRING")
                        .setOrdinal(4)
                        .setFieldLength(17)
                        .setIntegerAspect(Aspects.ASPECTS_OFFSET, 4)
                ).addFieldShape(new FieldShape().setName("encryptMethod")
                        .setDataType("BYTE")
                        .setOrdinal(5)
                        .setFieldLength(1)
                        .setIntegerAspect(Aspects.ASPECTS_OFFSET, 21)
                        .setEnumValue(EnumValue.of(KeyValue.of("数据不加密", 0x01), KeyValue.of("数据经过RSA加密", 0x02), KeyValue.of("数据经过AES128加密", 0x03), KeyValue.of("异常", 0xFE), KeyValue.of("无效", 0xFF)))
                ).addFieldShape(new FieldShape().setName("dataUnitSize")
                        .setDataType("WORD")
                        .setOrdinal(6)
                        .setFieldLength(2)
                        .setIntegerAspect(Aspects.ASPECTS_OFFSET, 22)
                        .setRangeValue(RangeValue.<Integer>fromString("[0, 65535]", StringToInteger.INSTANCE))
                ).addFieldShape(new FieldShape().setName("dataUnit")
                        .setDataType("BLOB")
                        .setOrdinal(7)
                        .setFieldLength(FieldLength.UNKNOWN)
                        .setStringAspect(Aspects.ASPECTS_VALUETYPEIDENTIFYFIELD, "commandFlag") // commandFlag 指定这个字段的值
                        .setArrayAspect("VALUE_DATASHAPE_MAP", "KEY-VALUE",
                                KeyValue.of(1, "VehicleLoginDU"),
                                KeyValue.of(2, "RealtimeDU"),
                                KeyValue.of(3, "ReissueDU"),
                                KeyValue.of(4, "VehicleLogoutDU"),
                                KeyValue.of(5, "PlatformLoginDU"),
                                KeyValue.of(6, "PlatformLogoutDU"))
                ).addFieldShape(new FieldShape().setName("CRC")
                        .setDataType("BYTE")
                        .setOrdinal(8)
                        .setFieldLength(FieldLength.ONE)
                        .setIntegerAspect(Aspects.ASPECTS_OFFSET, -1)
                        .setStringAspect(Aspects.ASPECTS_CRC_ALG, "BCC")
                )
        );

        application.addDataShape(new DataShape().setName("Timestamp")
                .setUserFriendlyName("时间")
                .addFieldShape(new FieldShape().setName("Year").setUserFriendlyName("年").setValueType(PrimitiveDataType.BYTE).setFieldLength(1).setRangeValue(RangeValue.fromString("[0~99]", StringToInteger.INSTANCE)))
                .addFieldShape(new FieldShape().setName("Month").setUserFriendlyName("月").setValueType(PrimitiveDataType.BYTE).setFieldLength(1).setRangeValue(RangeValue.fromString("[1~12]", StringToInteger.INSTANCE)))
                .addFieldShape(new FieldShape().setName("Day").setUserFriendlyName("日").setValueType(PrimitiveDataType.BYTE).setFieldLength(1).setRangeValue(RangeValue.fromString("[1~31]", StringToInteger.INSTANCE)))
                .addFieldShape(new FieldShape().setName("Hour").setUserFriendlyName("小时").setValueType(PrimitiveDataType.BYTE).setFieldLength(1).setRangeValue(RangeValue.fromString("[0~23]", StringToInteger.INSTANCE)))
                .addFieldShape(new FieldShape().setName("Minute").setUserFriendlyName("分钟").setValueType(PrimitiveDataType.BYTE).setFieldLength(1).setRangeValue(RangeValue.fromString("[0~59]", StringToInteger.INSTANCE)))
                .addFieldShape(new FieldShape().setName("Second").setUserFriendlyName("秒").setValueType(PrimitiveDataType.BYTE).setFieldLength(1).setRangeValue(RangeValue.fromString("[0~59]", StringToInteger.INSTANCE)))
        );

        application.addDataShape(new DataShape().setName("VehicleLoginDU")
                .addFieldShape(new FieldShape().setName("acquireTime")
                        .setUserFriendlyName("数据采集时间")
                        .setDataType("Timestamp")
                ).addFieldShape(new FieldShape().setName("loginSequence").setUserFriendlyName("登入流水号")
                        .setDataType("WORD")
                ).addFieldShape(new FieldShape().setName("ICCID").setUserFriendlyName("ICCID")
                        .setDataType("STRING")
                        .setFieldLength(20)
                ).addFieldShape(new FieldShape().setName("chargeableSubSystemNumber").setUserFriendlyName("可充电储能子系统数")
                        .setDataType("BYTE")
                        .setFieldLength(1)
                        .setRangeValue(RangeValue.ofInteger("[0, 250]"))
                ).addFieldShape(new FieldShape().setName("chargeableSubSystemCodeLength").setUserFriendlyName("可充电储能子系统编码长度")
                        .setDataType("BYTE")
                        .setFieldLength(1)
                        .setRangeValue(RangeValue.ofInteger("[0-50]"))
                ).addFieldShape(new FieldShape().setName("chargeableSubSystemCodeArray").setUserFriendlyName("可充电储能子系统编码")
                        .setStringAspect("FIELD_LENGTH_EXPRESS", "chargeableSubSystemNumber * chargeableSubSystemCodeArray")
                )
        );

        application.addDataShape(new DataShape().setName("TypeEntity")
                .addFieldShape(new FieldShape().setName("type").setDataType("BYTE").setFieldLength(1))
                .addFieldShape(new FieldShape().setName("blob").setDataType("BLOB")
                        .setFieldLength(FieldLength.UNKNOWN)
                        .setStringAspect(Aspects.ASPECTS_VALUETYPEIDENTIFYFIELD, "type") // commandFlag 指定这个字段的值
                        .setArrayAspect("VALUE_DATASHAPE_MAP", "KEY-VALUE",
                                KeyValue.of(1, "VehicleEntity"),
                                KeyValue.of(2, "DriverMotorEntity"),
                                KeyValue.of(3, "FuelBatteryEntity"),
                                KeyValue.of(4, "EngineEntity"),
                                KeyValue.of(5, "LocationEntity"),
                                KeyValue.of(6, "ExtremeEntity"),
                                KeyValue.of(7, "AlarmEntity"))
                )
        );

        application.addDataShape(new DataShape().setName("GEAR").setUserFriendlyName("档位状态")
                .addFieldShape(new FieldShape().setName("gear").setUserFriendlyName("档位")
                        .setDataType("BIT")
                        .setFieldLength(4)
                        .setStringAspect("OFFSET", "[0-3]")
                        .setEnumValue(EnumValue.of(KeyValue.of(0, "空档"), KeyValue.of(1, "1档")
                                , KeyValue.of(2, "2档")
                                , KeyValue.of(3, "3档")
                                , KeyValue.of(4, "4档")
                                , KeyValue.of(5, "5档")
                                , KeyValue.of(6, "6档")
                                , KeyValue.of(7, "7档")
                                , KeyValue.of(8, "8档")
                                , KeyValue.of(9, "9档")
                                , KeyValue.of(0x0A, "10档")
                                , KeyValue.of(0x0B, "11档")
                                , KeyValue.of(0x0C, "12档")
                                , KeyValue.of(0x0D, "倒档")
                                , KeyValue.of(0x0E, "自动D档")
                                , KeyValue.of(0x0F, "停车P档")
                                ))
                ).addFieldShape(new FieldShape().setName("brakeStatus").setUserFriendlyName("是否有制动力")
                        .setIntegerAspect("OFFSET", 4)
                        .setDataType("BIT")
                        .setFieldLength(1)
                        .setEnumValue(EnumValue.of(KeyValue.of(0, "无制动力"), KeyValue.of(1, "有制动力")))
                ).addFieldShape(new FieldShape().setName("driveStatus").setUserFriendlyName("是否有驱动力")
                        .setIntegerAspect("OFFSET", 5)
                        .setDataType("BIT")
                        .setFieldLength(1)
                        .setEnumValue(EnumValue.of(KeyValue.of(0, "无驱动力"), KeyValue.of(1, "有驱动力")))
                )
        );

        application.addDataShape(new DataShape().setName("VehicleEntity")
                .addFieldShape(new FieldShape()
                        .setName("vehicleStatus")
                        .setDataType("BYTE")
                        .setFieldLength(1)
                        .setEnumValue(EnumValue.of(KeyValue.of(0x01, "车辆启动状态"),
                                KeyValue.of(0x02, "熄火"),
                                KeyValue.of(0x03, "其他状态"),
                                KeyValue.of(0xFE, "异常"),
                                KeyValue.of(0xFF, "无效")
                                ))
                ).addFieldShape(new FieldShape()
                        .setName("chargeStatus")
                        .setDataType("BYTE")
                        .setFieldLength(1)
                        .setEnumValue(EnumValue.of(
                                KeyValue.of(0x01, "停车充电"),
                                KeyValue.of(0x02, "行驶充电"),
                                KeyValue.of(0x03, "未充电状态"),
                                KeyValue.of(0x04, "充电完成"),
                                KeyValue.of(0xFE, "异常"),
                                KeyValue.of(0xFF, "无效")
                        ))
                ).addFieldShape(new FieldShape()
                        .setName("runMode").setUserFriendlyName("运行模式")
                        .setDataType("BYTE")
                        .setFieldLength(1)
                        .setEnumValue(EnumValue.of(
                                KeyValue.of(0x01, "纯电"),
                                KeyValue.of(0x02, "混动"),
                                KeyValue.of(0x03, "燃油"),
                                KeyValue.of(0xFE, "异常"),
                                KeyValue.of(0xFF, "无效")
                        ))
                ).addFieldShape(new FieldShape()
                        .setName("vehicleSpeed").setUserFriendlyName("车速")
                        .setDataType("WORD")
                        .setRangeValue(RangeValue.ofInteger("[0-2200]"))
                        .setEnumValue(EnumValue.of(
                                KeyValue.of(0xFFFE, "异常"),
                                KeyValue.of(0xFFFF, "无效")
                        ))
                        .setValueResolution(BigDecimal.valueOf(0.1))
                        .setUnit("km/h")
                ).addFieldShape(new FieldShape()
                        .setName("ODO").setUserFriendlyName("累计里程")
                        .setDataType("DWORD")
                        .setRangeValue(RangeValue.ofInteger("[0-9999999]"))
                        .setEnumValue(EnumValue.of(
                                KeyValue.of(0xFFFFFFFE, "异常"),
                                KeyValue.of(0xFFFFFFFF, "无效")
                        ))
                        .setValueResolution(BigDecimal.valueOf(0.1))
                        .setUnit("km")
                ).addFieldShape(new FieldShape()
                        .setName("totalVoltage").setUserFriendlyName("总电压")
                        .setDataType("WORD")
                        .setRangeValue(RangeValue.ofInteger("[0-10000]"))
                        .setEnumValue(EnumValue.of(
                                KeyValue.of(0xFFFE, "异常"),
                                KeyValue.of(0xFFFF, "无效")
                        ))
                        .setUnit("V")
                        .setValueResolution(BigDecimal.valueOf(0.1))
                ).addFieldShape(new FieldShape()
                        .setName("totalCurrent").setUserFriendlyName("总电流")
                        .setDataType("WORD")
                        .setRangeValue(RangeValue.ofInteger("[0-20000]"))
                        .setEnumValue(EnumValue.of(
                                KeyValue.of(0xFFFE, "异常"),
                                KeyValue.of(0xFFFF, "无效")
                        ))
                        .setUnit("A")
                        .setValueResolution(BigDecimal.valueOf(0.1))
                ).addFieldShape(new FieldShape()
                        .setName("SOC").setUserFriendlyName("SOC")
                        .setDataType("BYTE")
                        .setRangeValue(RangeValue.ofInteger("[0-100]"))
                        .setEnumValue(EnumValue.of(
                                KeyValue.of(0xFE, "异常"),
                                KeyValue.of(0xFF, "无效")
                        ))
                        .setUnit("%")
                        .setValueResolution(BigDecimal.ONE)
                ).addFieldShape(new FieldShape()
                        .setName("DCDCStatus").setUserFriendlyName("DC-DC状态")
                        .setDataType("BYTE")
                        .setEnumValue(EnumValue.of(
                                KeyValue.of(0x01, "工作"),
                                KeyValue.of(0x02, "断开"),
                                KeyValue.of(0xFE, "异常"),
                                KeyValue.of(0xFF, "无效")
                        ))
                ).addFieldShape(new FieldShape().setName("gear").setUserFriendlyName("档位")
                        .setDataType("GEAR")
                ).addFieldShape(new FieldShape().setName("register").setUserFriendlyName("绝缘电阻")
                        .setDataType("WORD")
                        .setRangeValue(RangeValue.ofInteger("[0-60000]"))
                        .setUnit("kΩ")
                ).addFieldShape(new FieldShape().setName("reserved").setUserFriendlyName("预留")
                        .setDataType("WORD")
                )
        );

        application.addDataShape(new DataShape().setName("RealtimeDU")
                .addFieldShape(new FieldShape().setName("acquireTime")
                        .setUserFriendlyName("数据采集时间")
                        .setDataType("Timestamp")
                ).addFieldShape(new FieldShape().setName("entities")
                        .setDataType("ARRAY")
                        .setStringAspect(Aspects.ASPECTS_ARRAY_ELEM_TYPE, "TypeEntity")
                )
        );


        String json = JsonUtil.toJson(application);
        System.out.println(json);
        Application application1 = JsonUtil.fromJson(json, Application.class);
        FieldShape fieldShape = application1.getDataShape("GB32960V2016Packet").getFieldShape("commandFlag");
        System.out.println(fieldShape.getDataType());
        System.out.println(application1.getDataType(fieldShape.getDataType()));
        fieldShape.getEnumValue().stream().forEach(new Consumer() {
            public void accept(Object o) {
                KeyValue keyValue = KeyValue.of((Map)o);
                System.out.print(keyValue.key);
                System.out.print(" = ");
                System.out.println(keyValue.value);
            }
        });


    }
}