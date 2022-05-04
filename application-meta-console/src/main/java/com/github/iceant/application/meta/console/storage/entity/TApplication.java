package com.github.iceant.application.meta.console.storage.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
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
 * @since 2022-05-02
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName(value="t_application", autoResultMap = true)
public class TApplication implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private Long id;

    @TableField("name")
    private String name;

    @TableField("user_friendly_name")
    private String userFriendlyName;

    @TableField("description")
    private String description;

    @TableField("creation_datetime")
    private LocalDateTime creationDatetime;

    public static final String ID = "id";

    public static final String NAME = "name";

    public static final String USER_FRIENDLY_NAME = "user_friendly_name";

    public static final String DESCRIPTION = "description";

    public static final String CREATION_DATETIME = "creation_datetime";

}
