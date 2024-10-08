package com.mjc.school.service.exception;

public class NewsException extends Exception{
    private final String errorCode;
    private final String errorMessage;

    public NewsException(String errorCode, String errorMessage){
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return "ERROR_CODE: " + errorCode + " ERROR_MESSAGE: " + errorMessage;
    }
}
