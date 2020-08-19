package com.miya.wx.service.impl;

import com.miya.wx.entity.MessageGroupChatUser;
import com.miya.wx.mapper.MessageGroupChatUserMapper;
import com.miya.wx.service.IMessageGroupChatUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 群聊成员 服务实现类
 * </p>
 *
 * @author Caixiaowei
 * @since 2020-07-24
 */
@Service
public class MessageGroupChatUserServiceImpl extends ServiceImpl<MessageGroupChatUserMapper, MessageGroupChatUser> implements IMessageGroupChatUserService {

}
