package org.boygear.exceptions;

//ToDo learn exception handling
public class BadRequestException extends RuntimeException{
    public BadRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
