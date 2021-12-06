package cn.muses.wework.web.service.handler;

import java.util.Map;

import org.springframework.stereotype.Component;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.bean.message.WxCpXmlMessage;
import me.chanjar.weixin.cp.bean.message.WxCpXmlOutMessage;

/**
 * 点击菜单链接|扫码事件
 *
 * @author jervis
 * @date 2021-11-30
 */
@Component
public class NullHandler extends AbstractWxCpMessageHandler {

    public NullHandler(){
        // 点击菜单链接事件（这里使用了一个空的处理器，可以根据自己需要进行扩展）
        // 扫码事件（这里使用了一个空的处理器，可以根据自己需要进行扩展）
        super(new RouterStrategy( WxConsts.MenuButtonType.VIEW),new RouterStrategy(WxConsts.EventType.SCAN));
    }

    @Override
    public WxCpXmlOutMessage handle(
        WxCpXmlMessage wxMessage, Map<String, Object> context, WxCpService cpService,
        WxSessionManager sessionManager) {
        return null;
    }
}
