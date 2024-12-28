package com.nttdata.backend.app.exception;


public class InternalServerException extends RuntimeException {
    public InternalServerException(String message) {
        super(message);
    }
}

