package com.miya.wx.service.impl;

import com.miya.wx.entity.MessageLog;
import com.miya.wx.mapper.MessageLogMapper;
import com.miya.wx.service.IMessageLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 消息发送记录  服务实现类
 * </p>
 *
 * @author Caixiaowei
 * @since 2020-07-24
 */
@Service
public class MessageLogServiceImpl extends ServiceImpl<MessageLogMapper, MessageLog> implements IMessageLogService {

}
