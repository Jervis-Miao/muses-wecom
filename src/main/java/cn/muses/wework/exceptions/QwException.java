/*
 * Copyright 2019 All rights reserved.
 */

package cn.muses.wework.exceptions;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author jervis
 * @date 2021/11/30.
 */
public class QwException extends RuntimeException {
    private static final long serialVersionUID = -357641166647154098L;

    private Error error;

    public QwException(String message) {
        this(Error.GENERAL, message);
    }

    public QwException(Error error, String message) {
        super(message);
        this.error = error;
    }

    public QwException(String message, Throwable cause) {
        this(Error.GENERAL, message, cause);
    }

    public QwException(Error error, String message, Throwable cause) {
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
