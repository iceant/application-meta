package com.github.iceant.application.meta.console.storage.vo;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.math.*;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author Chen Peng
 * @since 2022-05-02
 */
@Getter
@Setter
@Accessors(chain = true)
public class TFieldShapeVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Integer dataTypeId;

    private String name;

    private String userFriendlyName;

    private String description;

    private LocalDateTime creationDatetime;

    private Integer applicationId;

    private Integer dataShapeId;

    private Boolean isPrimaryKey;

    private Boolean isNullable;

    private Boolean isForeignKey;

    private String foreignDataShape;

    private String foreignFieldName;

    private String defaultValue;

    private String minValue;

    private String maxValue;

    private Integer fieldLength;

    private Integer fieldMaxLength;

    private String enumValues;

    private String rangeValues;

    private String unit;

    private Integer scale;

    private Integer valueOffset;

    private BigDecimal valueResolution;


}
