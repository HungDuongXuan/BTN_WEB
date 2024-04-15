package com.dzungyb.btn_web.error;

public class ErrorException extends Throwable {
    String message;

    public ErrorException(String message) {
        super(message);
        System.out.println(message);
    }

}