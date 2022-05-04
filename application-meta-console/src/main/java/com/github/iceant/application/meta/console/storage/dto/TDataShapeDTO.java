package com.github.iceant.application.meta.console.storage.dto;

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
public class TDataShapeDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    private String userFriendlyName;

    private String description;

    private LocalDateTime creationDatetime;

    private Integer applicationId;


}
