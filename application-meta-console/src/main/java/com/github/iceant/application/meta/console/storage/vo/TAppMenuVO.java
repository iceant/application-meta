package com.github.iceant.application.meta.console.storage.vo;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author Chen Peng
 * @since 2022-04-30
 */
@Getter
@Setter
public class TAppMenuVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String name;

    private com.github.iceant.application.meta.console.domain.LocaleString userFriendlyName;

    private com.github.iceant.application.meta.console.domain.LocaleString description;

    private String icon;

    private String path;

    private Integer parentId;

    private LocalDateTime creationDatetime;


}