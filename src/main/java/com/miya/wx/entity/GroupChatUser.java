package com.miya.wx.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 群聊成员
 * </p>
 *
 * @author Caixiaowei
 * @since 2020-06-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class GroupChatUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 群聊id
     */
    private String chatid;

    /**
     * 成员id
     */
    private String userid;


}
