/*
 * Copyright 2019 All rights reserved.
 */

package wework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author jervis
 * @date 2021/11/29.
 */
@SpringBootApplication
@EnableAspectJAutoProxy(exposeProxy = true)
public class QwApplication {

    public static void main(String[] args) {
        SpringApplication.run(QwApplication.class, args);
    }
}
