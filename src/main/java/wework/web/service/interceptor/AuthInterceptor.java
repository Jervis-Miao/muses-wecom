/*
 * Copyright 2021 XYZ Co., Ltd. All Rights Reserved
 */
package wework.web.service.interceptor;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.bean.message.WxCpXmlMessage;
import me.chanjar.weixin.cp.message.WxCpMessageInterceptor;

/**
 * @author miaoqiang
 * @date 2021/12/3.
 */
public class AuthInterceptor implements WxCpMessageInterceptor {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public boolean intercept(WxCpXmlMessage wxMessage, Map<String, Object> context, WxCpService wxCpService,
        WxSessionManager sessionManager) throws WxErrorException {

        logger.info("进入拦截器");

        return true;
    }
}
