package cn.muses.wecom.web.service.handler;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.muses.wecom.builder.TextBuilder;
import cn.muses.wecom.utils.JsonMapper;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.bean.message.WxCpXmlMessage;
import me.chanjar.weixin.cp.bean.message.WxCpXmlOutMessage;
import me.chanjar.weixin.cp.constant.WxCpConsts;

/**
 * 通讯录变更（添加|删除用户，通过ChangeType识别）事件处理器.
 *
 * @author jervis
 * @date 2021-11-30
 */
@Component
public class ChangeExternalContactHandler extends AbstractWxCpMessageHandler {

    @Autowired
    private JsonMapper jsonMapper;

    public ChangeExternalContactHandler() {
        super(new RouterStrategy(WxCpConsts.EventType.CHANGE_EXTERNAL_CONTACT));
    }

    @Override
    public WxCpXmlOutMessage handle(WxCpXmlMessage wxMessage, Map<String, Object> context, WxCpService cpService,
        WxSessionManager sessionManager) {
        String content = "收到添加企业客户事件，内容：" + jsonMapper.toJson(wxMessage);
//        this.logger.info(content);

        final String welcomeCode = wxMessage.getWelcomeCode();
        this.logger.info("agentId: {}, welcomeCode: {}", wxMessage.getAgentId(), welcomeCode);

        return new TextBuilder().build(content, wxMessage, cpService);
    }
}
