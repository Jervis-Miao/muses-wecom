/*
 * Copyright 2019 All rights reserved.
 */

package cn.muses.wework.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.fasterxml.jackson.databind.ObjectMapper;

import cn.muses.wework.utils.JsonMapper;

/**
 * @author jervis
 * @date 2021/11/30.
 */
@Configuration
public class NormalBeansConfig {

    @Bean
    @Primary
    public JsonMapper initJsonMapper(ObjectMapper objectMapper) {
        return new JsonMapper(objectMapper);
    }
}
