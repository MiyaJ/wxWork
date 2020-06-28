package com.ezhiyang.approval.service.impl;

import com.ezhiyang.approval.entity.GroupChatUser;
import com.ezhiyang.approval.mapper.GroupChatUserMapper;
import com.ezhiyang.approval.service.IGroupChatUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 群聊成员 服务实现类
 * </p>
 *
 * @author Caixiaowei
 * @since 2020-06-28
 */
@Service
public class GroupChatUserServiceImpl extends ServiceImpl<GroupChatUserMapper, GroupChatUser> implements IGroupChatUserService {

}
