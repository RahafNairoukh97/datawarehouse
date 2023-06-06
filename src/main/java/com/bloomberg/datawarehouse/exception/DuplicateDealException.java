package com.bloomberg.datawarehouse.exception;

public class DuplicateDealException extends RuntimeException {

    public DuplicateDealException(final String message) {
        super(message);
    }
}
