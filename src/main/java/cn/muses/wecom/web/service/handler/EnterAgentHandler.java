package cn.muses.wecom.web.service.handler;

import java.util.Map;

import org.springframework.stereotype.Component;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.bean.message.WxCpXmlMessage;
import me.chanjar.weixin.cp.bean.message.WxCpXmlOutMessage;
import me.chanjar.weixin.cp.constant.WxCpConsts;

/**
 * 进入应用事件.
 *
 * @author jervis
 * @date 2021-11-30
 */
@Component
public class EnterAgentHandler extends AbstractWxCpMessageHandler {

    public EnterAgentHandler() {
        super(new RouterStrategy(WxCpConsts.EventType.ENTER_AGENT));
    }

    @Override
    public WxCpXmlOutMessage handle(WxCpXmlMessage wxMessage, Map<String, Object> context, WxCpService wxCpService,
        WxSessionManager sessionManager) throws WxErrorException {
        // do something
        logger.info("用户: {}, 进入了应用: {}", wxMessage.getFromUserName(), wxMessage.getAgentId());
        // return WxCpXmlOutMessage.TEXT().content("欢迎进入test应用！！！")
        // .fromUser(wxMessage.getToUserName()).toUser(wxMessage.getFromUserName())
        // .build();
        return null;
    }
}
