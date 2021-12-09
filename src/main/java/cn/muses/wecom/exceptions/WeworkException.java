/*
 * Copyright 2019 All rights reserved.
 */

package cn.muses.wecom.exceptions;

import org.apache.commons.lang3.builder.ToStringBuilder;

import cn.muses.wecom.constants.ResultErrorConst.Error;

/**
 * @author jervis
 * @date 2021/11/30.
 */
public class WeworkException extends RuntimeException {
    private static final long serialVersionUID = -357641166647154098L;

    private Error error;

    public WeworkException(String message) {
        this(Error.GENERAL, message);
    }

    public WeworkException(Error error, String message) {
        super(message);
        this.error = error;
    }

    public WeworkException(String message, Throwable cause) {
        this(Error.GENERAL, message, cause);
    }

    public WeworkException(Error error, String message, Throwable cause) {
        super(message, cause);
        this.error = error;
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("error", error)
            .append("message", getMessage()).toString();
    }
}
