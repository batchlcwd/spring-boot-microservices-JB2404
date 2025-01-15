package com.substring.foodie.exception;

public class InvalidFilePathException extends RuntimeException {
    public InvalidFilePathException(String message) {
        super(message);
    }

    public InvalidFilePathException() {
        super("Invalid file path");
    }
}
