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
 * @since 2022-05-01
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName(value="t_protocol_account", autoResultMap = true)
public class TProtocolAccount implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private Long id;

    @TableField("username")
    private String username;

    @TableField("password")
    private String password;

    @TableField("protocol_version")
    private String protocolVersion;

    @TableField("description")
    private String description;

    @TableField("creation_datetime")
    private LocalDateTime creationDatetime;

    public static final String ID = "id";

    public static final String USERNAME = "username";

    public static final String PASSWORD = "password";

    public static final String PROTOCOL_VERSION = "protocol_version";

    public static final String DESCRIPTION = "description";

    public static final String CREATION_DATETIME = "creation_datetime";

}
