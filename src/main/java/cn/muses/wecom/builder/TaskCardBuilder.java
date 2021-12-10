package cn.muses.wecom.builder;

import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.bean.message.WxCpXmlMessage;
import me.chanjar.weixin.cp.bean.message.WxCpXmlOutMessage;

/**
 * @author jervis
 * @date 2021-11-30
 */
public class TaskCardBuilder extends AbstractBuilder {

    @Override
    public WxCpXmlOutMessage build(String content, WxCpXmlMessage wxMessage, WxCpService service) {
        return WxCpXmlOutMessage.TASK_CARD().replaceName(content)
            .fromUser(wxMessage.getToUserName()).toUser(wxMessage.getFromUserName())
            .build();
    }
}
