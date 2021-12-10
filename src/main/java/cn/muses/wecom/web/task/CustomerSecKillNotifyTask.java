/*
 * Copyright 2021 XYZ Co., Ltd. All Rights Reserved
 */
package cn.muses.wecom.web.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import cn.muses.wecom.builder.WxCpMessageBuilder;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.api.WxCpMessageService;

/**
 * 客户秒杀通知
 *
 * @author miaoqiang
 * @date 2021/12/9.
 */
@Component
public class CustomerSecKillNotifyTask {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private WxCpMessageService wxCpMessageService;

    @Scheduled(fixedRate = 4 * 60 * 60 * 1000)
    public void FinanceTask() throws WxErrorException {
        wxCpMessageService.send(WxCpMessageBuilder.buildTaskCard());
    }
}
