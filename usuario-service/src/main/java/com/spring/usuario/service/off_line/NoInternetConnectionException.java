package com.spring.usuario.service.off_line;

public class NoInternetConnectionException extends RuntimeException {
    public NoInternetConnectionException(String message) {
        super(message);
    }
}
