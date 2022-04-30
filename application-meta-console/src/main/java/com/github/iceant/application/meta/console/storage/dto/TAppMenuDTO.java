package com.github.iceant.application.meta.console.storage.dto;

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
public class TAppMenuDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    private com.github.iceant.application.meta.console.domain.LocaleString userFriendlyName;

    private com.github.iceant.application.meta.console.domain.LocaleString description;

    private String icon;

    private String path;

    private String view;

    private Integer parentId;

    private LocalDateTime creationDatetime;


}
