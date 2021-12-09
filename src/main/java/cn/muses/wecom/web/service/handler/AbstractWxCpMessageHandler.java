package cn.muses.wecom.web.service.handler;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.cp.message.WxCpMessageHandler;
import me.chanjar.weixin.cp.message.WxCpMessageRouterRule;

/**
 * @author jervis
 * @date 2021-11-30
 */
public abstract class AbstractWxCpMessageHandler implements WxCpMessageHandler, Ordered {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    public List<RouterStrategy> strategies;

    public AbstractWxCpMessageHandler(RouterStrategy... strategies) {
        this.strategies = Arrays.asList(strategies);
    }

    /**
     * 处理器路由权重
     *
     * @return
     */
    @Override
    public int getOrder() {
        return 10000;
    }

    public List<RouterStrategy> getStrategies() {
        return strategies;
    }

    /**
     * 路由策略
     */
    public static class RouterStrategy {

        /**
         * 全部事件拦截
         */
        public static final RouterStrategy ALL = new RouterStrategy(null, null);

        /**
         * 默认事件路由，全局唯一
         */
        public static final RouterStrategy DEFAULT = new RouterStrategy(null, null);

        private boolean async = true;

        private String msgType;

        private String event;

        public RouterStrategy(String event) {
            this(WxConsts.XmlMsgType.EVENT, event);
        }

        public RouterStrategy(String msgType, String event) {
            this(false, msgType, event);
        }

        public RouterStrategy(boolean async, String msgType, String event) {
            this.async = async;
            this.msgType = msgType;
            this.event = event;
        }

        public boolean isAsync() {
            return async;
        }

        public void setAsync(boolean async) {
            this.async = async;
        }

        public String getMsgType() {
            return msgType;
        }

        public void setMsgType(String msgType) {
            this.msgType = msgType;
        }

        public String getEvent() {
            return event;
        }

        public void setEvent(String event) {
            this.event = event;
        }
    }

    private WxCpMessageRouterRule routerRule;

    public WxCpMessageRouterRule getRouterRule() {
        return routerRule;
    }

    public void setRouterRule(WxCpMessageRouterRule routerRule) {
        this.routerRule = routerRule;
    }
}
