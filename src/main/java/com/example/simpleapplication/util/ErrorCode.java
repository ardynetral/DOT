package com.example.simpleapplication.util;

public class ErrorCode {
    public enum ERROR {
        INTERNAL_SERVER_ERROR("Internal server error"),
        BAD_REQUEST("Bad Request"),
        FORBIDDEN("Forbidden"),
        ERROR_VALIDATION("Error Validation"),
        DATABASE_TIMEOUT("Failed to connect to database"),
        DATA_NOT_FOUND("Data not found");

        private String message;

        ERROR(String message) {
            this.message = message;
        }

        public String getMessage() {
            return this.message;
        }
    }

    public static String messageOf(String code) {
        try {
            return ERROR.valueOf(code).message;
        } catch (Exception e) {
            return "Unknown Error: " + code;
        }
    }
}
