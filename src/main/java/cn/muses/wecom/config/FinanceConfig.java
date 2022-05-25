/*
 * Copyright 2021 XYZ Co., Ltd. All Rights Reserved
 */
package cn.muses.wecom.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.tencent.wework.FinanceService;

/**
 * @author miaoqiang
 * @date 2021/12/8.
 */
@Configuration
public class FinanceConfig {

    /**
     * 会话存档密钥
     */
    @Value("${wechat.finance.secret:1}")
    private String secret;

    /**
     * 会话私钥
     */
    @Value("${wechat.finance.privateKey:2}")
    private String privateKey;

    /**
     * 上传路径
     */
    @Value("${wechat.finance.profile:3}")
    private String profile;

    @Bean
    public FinanceService financeService(WxCpProperties properties) {
        return new FinanceService(properties.getCorpId(), secret, privateKey, "", "", profile);
    }
}
