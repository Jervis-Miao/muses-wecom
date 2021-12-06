package cn.muses.wework.web.controller;

import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * 配置错误状态与对应访问路径
 *
 * @author jervis
 * @date 2021-11-30
 */
@Component
public class ErrorPageConfiguration implements ErrorPageRegistrar {

    @Override
    public void registerErrorPages(ErrorPageRegistry errorPageRegistry) {
        errorPageRegistry.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/error/404"),
            new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/error/500"));
    }
}
