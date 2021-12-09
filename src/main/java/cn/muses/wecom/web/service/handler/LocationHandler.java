package cn.muses.wecom.web.service.handler;

import java.util.Map;

import org.springframework.stereotype.Component;

import cn.muses.wecom.builder.TextBuilder;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.bean.message.WxCpXmlMessage;
import me.chanjar.weixin.cp.bean.message.WxCpXmlOutMessage;

/**
 * 上报|接收地理位置消息
 *
 * @author jervis
 * @date 2021-11-30
 */
@Component
public class LocationHandler extends AbstractWxCpMessageHandler {

    public LocationHandler() {
        super(new RouterStrategy(WxConsts.EventType.LOCATION), new RouterStrategy(WxConsts.XmlMsgType.LOCATION, null));
    }

    @Override
    public WxCpXmlOutMessage handle(WxCpXmlMessage wxMessage, Map<String, Object> context, WxCpService cpService,
        WxSessionManager sessionManager) {
        if (wxMessage.getMsgType().equals(WxConsts.XmlMsgType.LOCATION)) {
            // TODO 接收处理用户发送的地理位置消息
            try {
                String content = "感谢反馈，您的的地理位置已收到！";
                return new TextBuilder().build(content, wxMessage, null);
            } catch (Exception e) {
                this.logger.error("位置消息接收处理失败", e);
                return null;
            }
        }

        // 上报地理位置事件
        String content =
            String.format("\n上报地理位置，纬度 : %s\n经度 : %s\n精度 : %s", wxMessage.getLatitude(), wxMessage.getLongitude(),
                wxMessage.getPrecision());
        this.logger.info(content);

        // TODO 可以将用户地理位置信息保存到本地数据库，以便以后使用
        return null;
    }
}
