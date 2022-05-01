package com.github.iceant.application.meta.console.storage.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author Chen Peng
 * @since 2022-05-01
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName(value="t_field_shape", autoResultMap = true)
public class TFieldShape implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private Long id;

    @TableField("data_type_id")
    private Integer dataTypeId;

    @TableField("name")
    private String name;

    @TableField("user_friendly_name")
    private String userFriendlyName;

    @TableField("description")
    private String description;

    @TableField("creation_datetime")
    private LocalDateTime creationDatetime;

    @TableField("application_id")
    private Integer applicationId;

    @TableField("data_shape_id")
    private Integer dataShapeId;

    @TableField("is_primary_key")
    private Boolean isPrimaryKey;

    @TableField("is_nullable")
    private Boolean isNullable;

    @TableField("is_foreign_key")
    private Boolean isForeignKey;

    @TableField("foreign_data_shape")
    private String foreignDataShape;

    @TableField("foreign_field_name")
    private String foreignFieldName;

    @TableField("default_value")
    private String defaultValue;

    @TableField("min_value")
    private String minValue;

    @TableField("max_value")
    private String maxValue;

    @TableField("field_length")
    private Integer fieldLength;

    @TableField("field_max_length")
    private Integer fieldMaxLength;

    @TableField("enum_values")
    private String enumValues;

    @TableField("range_values")
    private String rangeValues;

    @TableField("unit")
    private String unit;

    @TableField("scale")
    private Integer scale;

    @TableField("value_offset")
    private Integer valueOffset;

    @TableField("value_resolution")
    private BigDecimal valueResolution;

    public static final String ID = "id";

    public static final String DATA_TYPE_ID = "data_type_id";

    public static final String NAME = "name";

    public static final String USER_FRIENDLY_NAME = "user_friendly_name";

    public static final String DESCRIPTION = "description";

    public static final String CREATION_DATETIME = "creation_datetime";

    public static final String APPLICATION_ID = "application_id";

    public static final String DATA_SHAPE_ID = "data_shape_id";

    public static final String IS_PRIMARY_KEY = "is_primary_key";

    public static final String IS_NULLABLE = "is_nullable";

    public static final String IS_FOREIGN_KEY = "is_foreign_key";

    public static final String FOREIGN_DATA_SHAPE = "foreign_data_shape";

    public static final String FOREIGN_FIELD_NAME = "foreign_field_name";

    public static final String DEFAULT_VALUE = "default_value";

    public static final String MIN_VALUE = "min_value";

    public static final String MAX_VALUE = "max_value";

    public static final String FIELD_LENGTH = "field_length";

    public static final String FIELD_MAX_LENGTH = "field_max_length";

    public static final String ENUM_VALUES = "enum_values";

    public static final String RANGE_VALUES = "range_values";

    public static final String UNIT = "unit";

    public static final String SCALE = "scale";

    public static final String VALUE_OFFSET = "value_offset";

    public static final String VALUE_RESOLUTION = "value_resolution";

}
