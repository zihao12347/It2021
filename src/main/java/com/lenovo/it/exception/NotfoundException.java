package com.lenovo.it.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 自定义异常类，该类表示找不到资源抛出的异常
 */
@ResponseStatus(HttpStatus.NOT_FOUND)   //返回的状态码，表示该异常为找不到资源异常
public class NotfoundException extends RuntimeException {
    public NotfoundException() {
    }

    public NotfoundException(String message) {
        super(message);
    }

    public NotfoundException(String message, Throwable cause) {
        super(message, cause);
    }
}