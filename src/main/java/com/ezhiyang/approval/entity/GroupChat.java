package com.ezhiyang.approval.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDateTime;
import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 群聊
 * </p>
 *
 * @author Caixiaowei
 * @since 2020-06-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class GroupChat implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 群聊id
     */
    private String chatid;

    /**
     * 群聊名称
     */
    private String name;

    /**
     * 群主
     */
    private String owner;

    private Boolean isDeleted;

    private Long createBy;

    private LocalDateTime createTime;

    private Long updateBy;

    private LocalDateTime updateTime;


}
