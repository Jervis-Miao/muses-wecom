package cn.muses.wework.web.service.handler;

import java.util.Map;

import org.springframework.stereotype.Component;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.bean.message.WxCpXmlMessage;
import me.chanjar.weixin.cp.bean.message.WxCpXmlOutMessage;

/**
 * 取消关注事件
 *
 * @author jervis
 * @date 2021-11-30
 */
@Component
public class UnsubscribeHandler extends AbstractWxCpMessageHandler {

    public UnsubscribeHandler() {
        super(new RouterStrategy(WxConsts.EventType.UNSUBSCRIBE));
    }

    @Override
    public WxCpXmlOutMessage handle(WxCpXmlMessage wxMessage, Map<String, Object> context, WxCpService cpService,
        WxSessionManager sessionManager) {
        String openId = wxMessage.getFromUserName();
        this.logger.info("取消关注用户 OPENID: " + openId);
        // TODO 可以更新本地数据库为取消关注状态
        return null;
    }
}
