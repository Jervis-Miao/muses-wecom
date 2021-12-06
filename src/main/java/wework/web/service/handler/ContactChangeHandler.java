package wework.web.service.handler;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.muses.wework.builder.TextBuilder;
import cn.muses.wework.utils.JsonMapper;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.bean.message.WxCpXmlMessage;
import me.chanjar.weixin.cp.bean.message.WxCpXmlOutMessage;
import me.chanjar.weixin.cp.constant.WxCpConsts;

/**
 * 通讯录变更事件处理器.
 *
 * @author jervis
 * @date 2021-11-30
 */
@Component
public class ContactChangeHandler extends AbstractWxCpMessageHandler {

    @Autowired
    private JsonMapper jsonMapper;

    public ContactChangeHandler() {
        super(new RouterStrategy(WxCpConsts.EventType.CHANGE_CONTACT));
    }

    @Override
    public WxCpXmlOutMessage handle(WxCpXmlMessage wxMessage, Map<String, Object> context, WxCpService cpService,
        WxSessionManager sessionManager) {
        String content = "收到通讯录变更事件，内容：" + jsonMapper.toJson(wxMessage);
        this.logger.info(content);

        return new TextBuilder().build(content, wxMessage, cpService);
    }

}
