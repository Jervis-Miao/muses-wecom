/*
 * Copyright 2021 XYZ Co., Ltd. All Rights Reserved
 */
package cn.muses.wecom.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.api.impl.WxCpMessageServiceImpl;
import me.chanjar.weixin.cp.bean.message.WxCpLinkedCorpMessage;
import me.chanjar.weixin.cp.bean.message.WxCpLinkedCorpMessageSendResult;
import me.chanjar.weixin.cp.bean.message.WxCpMessage;
import me.chanjar.weixin.cp.bean.message.WxCpMessageSendResult;
import me.chanjar.weixin.cp.bean.message.WxCpMessageSendStatistics;

/**
 * @author miaoqiang
 * @date 2021/12/10.
 */
@Component
public class WxCpMessageAsyncService extends WxCpMessageServiceImpl {

    @Autowired
    public WxCpMessageAsyncService(@Lazy @Qualifier("wxCpService-1000002") WxCpService cpService) {
        super(cpService);
    }

    @Async
    @Override
    public WxCpMessageSendResult send(WxCpMessage message) throws WxErrorException {
        return super.send(message);
    }

    @Async
    @Override
    public WxCpMessageSendStatistics getStatistics(int timeType) throws WxErrorException {
        return super.getStatistics(timeType);
    }

    @Async
    @Override
    public WxCpLinkedCorpMessageSendResult sendLinkedCorpMessage(WxCpLinkedCorpMessage message)
        throws WxErrorException {
        return super.sendLinkedCorpMessage(message);
    }
}
