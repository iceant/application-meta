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
@TableName(value="t_app_menu", autoResultMap = true)
public class TAppMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private Long id;

    @TableField("name")
    private String name;

    @TableField("user_friendly_name")
    private String userFriendlyName;

    @TableField("description")
    private String description;

    @TableField("icon")
    private String icon;

    @TableField("path")
    private String path;

    @TableField("view")
    private String view;

    @TableField("parent_id")
    private Integer parentId;

    @TableField("order_index")
    private Integer orderIndex;

    @TableField("creation_datetime")
    private LocalDateTime creationDatetime;

    public static final String ID = "id";

    public static final String NAME = "name";

    public static final String USER_FRIENDLY_NAME = "user_friendly_name";

    public static final String DESCRIPTION = "description";

    public static final String ICON = "icon";

    public static final String PATH = "path";

    public static final String VIEW = "view";

    public static final String PARENT_ID = "parent_id";

    public static final String ORDER_INDEX = "order_index";

    public static final String CREATION_DATETIME = "creation_datetime";

}
