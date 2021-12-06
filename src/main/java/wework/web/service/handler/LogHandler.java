package wework.web.service.handler;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.muses.wework.utils.JsonMapper;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.bean.message.WxCpXmlMessage;
import me.chanjar.weixin.cp.bean.message.WxCpXmlOutMessage;

/**
 * 记录所有事件的日志
 *
 * @author jervis
 * @date 2021-11-30
 */
@Component
public class LogHandler extends AbstractWxCpMessageHandler {

    @Autowired
    private JsonMapper jsonMapper;

    public LogHandler() {
        super(RouterStrategy.ALL);
    }

    @Override
    public int getOrder() {
        return Integer.MIN_VALUE;
    }

    @Override
    public WxCpXmlOutMessage handle(WxCpXmlMessage wxMessage, Map<String, Object> context, WxCpService cpService,
        WxSessionManager sessionManager) {
        this.logger.info("\n接收到请求消息，内容：{}", jsonMapper.toJson(wxMessage));
        return null;
    }
}
