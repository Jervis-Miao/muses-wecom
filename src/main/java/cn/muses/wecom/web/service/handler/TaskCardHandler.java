/*
 * Copyright 2021 XYZ Co., Ltd. All Rights Reserved
 */
package cn.muses.wecom.web.service.handler;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.muses.wecom.web.service.WxCpMessageAsyncService;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.bean.message.WxCpMessage;
import me.chanjar.weixin.cp.bean.message.WxCpXmlMessage;
import me.chanjar.weixin.cp.bean.message.WxCpXmlOutMessage;
import me.chanjar.weixin.cp.constant.WxCpConsts;

/**
 * @author miaoqiang
 * @date 2021/12/10.
 */
@Component
public class TaskCardHandler extends AbstractWxCpMessageHandler {

    @Autowired
    private WxCpMessageAsyncService wxCpMessageAsyncService;

    public TaskCardHandler() {
        super(new RouterStrategy(WxCpConsts.EventType.TASKCARD_CLICK));
    }

    @Override
    public WxCpXmlOutMessage handle(WxCpXmlMessage wxMessage, Map<String, Object> context, WxCpService cpService,
        WxSessionManager sessionManager) throws WxErrorException {

        String msg = "";
        if (wxMessage.getEventKey().equals("222")) {
            msg = "很遗憾，您选择了不抽取奖品！";
        } else {
            if (System.currentTimeMillis() % 3 == 0) {
                msg = "恭喜，您中奖啦！";
            } else {
                msg = "很遗憾，您与奖品擦肩而过！";
            }
        }

        wxCpMessageAsyncService.send(WxCpMessage.TEXT().toUser(wxMessage.getFromUserName()).content(msg).build());

        return null;
    }
}
