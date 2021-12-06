package wework.config;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import com.google.common.collect.Maps;

import cn.muses.wework.web.service.handler.AbstractWxCpMessageHandler;
import cn.muses.wework.web.service.handler.AbstractWxCpMessageHandler.RouterStrategy;
import cn.muses.wework.web.service.interceptor.AuthInterceptor;
import lombok.val;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.api.impl.WxCpServiceImpl;
import me.chanjar.weixin.cp.config.impl.WxCpDefaultConfigImpl;
import me.chanjar.weixin.cp.message.WxCpMessageRouter;
import me.chanjar.weixin.cp.message.WxCpMessageRouterRule;

/**
 * @author jervis
 * @date 2021-11-30
 */
@Configuration
@EnableConfigurationProperties(WxCpProperties.class)
public class WxCpConfig {
    private WxCpProperties properties;
    private List<AbstractWxCpMessageHandler> handlers;

    /**
     * agentId -> WxCpMessageRouter|WxCpService
     */
    private static Map<Integer, WxCpMessageRouter> routers = Maps.newHashMap();
    private static Map<Integer, WxCpService> cpServices = Maps.newHashMap();

    @Autowired
    public WxCpConfig(List<AbstractWxCpMessageHandler> handlers, WxCpProperties properties) {
        this.handlers = handlers;
        this.properties = properties;
    }

    /**
     * 获取服务路由
     *
     * @return
     */
    public static Map<Integer, WxCpMessageRouter> getRouters() {
        return routers;
    }

    /**
     * 获取服务
     *
     * @param agentId
     * @return
     */
    public static WxCpService getCpService(Integer agentId) {
        return cpServices.get(agentId);
    }

    /**
     * 初始化服务
     */
    @PostConstruct
    public void initServices() {
        cpServices = this.properties.getAppConfigs().stream().map(a -> {
            val configStorage = new WxCpDefaultConfigImpl();
            configStorage.setCorpId(this.properties.getCorpId());
            configStorage.setAgentId(a.getAgentId());
            configStorage.setCorpSecret(a.getSecret());
            configStorage.setToken(a.getToken());
            configStorage.setAesKey(a.getAesKey());
            val service = new WxCpServiceImpl();
            service.setWxCpConfigStorage(configStorage);
            routers.put(a.getAgentId(), this.newRouter(service));
            return service;
        }).collect(Collectors.toMap(service -> service.getWxCpConfigStorage().getAgentId(), a -> a));
    }

    /**
     * 注册路由
     *
     * @param wxCpService
     * @return
     */
    private WxCpMessageRouter newRouter(WxCpService wxCpService) {
        // 路由
        final val newRouter = new WxCpMessageRouter(wxCpService);

        // 默认处理器
        AbstractWxCpMessageHandler dh = null;

        // 扫描注册所有路由规则处理器
        for (AbstractWxCpMessageHandler h : this.handlers) {
            List<RouterStrategy> strategies;
            if (CollectionUtils.isNotEmpty(strategies = h.getStrategies())) {
                for (RouterStrategy s : strategies) {
                    if (RouterStrategy.DEFAULT.equals(s)) {
                        if (null != dh) {
                            throw new IllegalArgumentException(
                                String.format("Multiple default router rule is unsupported, %s and %s is conflicting!",
                                    dh.getClass(), h.getClass()));
                        }
                        dh = h;
                        continue;
                    }
                    this.registerRouteRule(newRouter, h, s);
                }
            }
        }

        // 最后注册默认路由规则处理器
        if (null != dh) {
            this.registerRouteRule(newRouter, dh, RouterStrategy.DEFAULT);
        }

        return newRouter;
    }

    /**
     * 注册路由规则
     *
     * @param router
     * @param h
     * @param s
     */
    private void registerRouteRule(WxCpMessageRouter router, AbstractWxCpMessageHandler h, RouterStrategy s) {
        String msgType, event;
        final WxCpMessageRouterRule rule = router.rule().async(s.isAsync());
        if (StringUtils.isNotBlank(msgType = s.getMsgType())) {
            rule.msgType(msgType);
        }
        if (StringUtils.isNotBlank(event = s.getEvent())) {
            rule.event(event);
        }
        rule.handler(h);
        if (s.equals(RouterStrategy.ALL)) {
            rule.next();
        } else {
            rule.interceptor(new AuthInterceptor());
            rule.end();
        }
    }
}
