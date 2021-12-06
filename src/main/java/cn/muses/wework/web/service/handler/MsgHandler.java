package cn.muses.wework.web.service.handler;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.muses.wework.builder.TextBuilder;
import cn.muses.wework.utils.JsonMapper;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.session.WxSessionManager;
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
        WxSessionManager sessionManager) {
        final String msgType = wxMessage.getMsgType();
        if (msgType == null) {
            // 如果msgType没有，就自己根据具体报文内容做处理
        }

        if (!msgType.equals(WxConsts.XmlMsgType.EVENT)) {
            // TODO 可以选择将消息保存到本地
        }

        // TODO 组装回复消息
        String content = "收到信息内容：" + wxMessage.getContent();

        return new TextBuilder().build(content, wxMessage, cpService);
    }
}
