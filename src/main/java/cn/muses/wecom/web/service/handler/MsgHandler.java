package cn.muses.wecom.web.service.handler;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.muses.wecom.builder.TextBuilder;
import cn.muses.wecom.builder.WxCpMessageBuilder;
import cn.muses.wecom.utils.JsonMapper;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.cp.api.WxCpMessageService;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.bean.message.WxCpXmlMessage;
import me.chanjar.weixin.cp.bean.message.WxCpXmlOutMessage;

/**
 * 默认消息事件
 *
 * @author jervis
 * @date 2021-11-30
 */
@Component
public class MsgHandler extends AbstractWxCpMessageHandler {

    @Autowired
    private WxCpMessageService wxCpMessageService;

    @Autowired
    private JsonMapper jsonMapper;

    public MsgHandler() {
        super(RouterStrategy.DEFAULT);
    }

    @Override
    public int getOrder() {
        return Integer.MAX_VALUE;
    }

    @Override
    public WxCpXmlOutMessage handle(WxCpXmlMessage wxMessage, Map<String, Object> context, WxCpService cpService,
        WxSessionManager sessionManager) throws WxErrorException {
        final String msgType = wxMessage.getMsgType();
        if (msgType == null) {
            // 如果msgType没有，就自己根据具体报文内容做处理
        }

        if (!msgType.equals(WxConsts.XmlMsgType.EVENT)) {
            // TODO 可以选择将消息保存到本地
        }

        String content = wxMessage.getContent();
        // TODO 组装回复消息
        String replyContent = "";
        if (content.equals("你好")) {
            replyContent = "你好呀！";
        } else if (content.equals("你是谁")) {
            replyContent = "你好，我是jervis！";
        } else if (content.equals("领奖")) {
            wxCpMessageService.send(WxCpMessageBuilder.buildTaskCard());
        } else {
            replyContent += "收到信息内容：" + content;
        }

        return new TextBuilder().build(replyContent, wxMessage, cpService);
    }
}
