package com.jeranfox.peach.api.response;

public class ApiResponse {
    private Error error;
    private int success;

    public boolean wasSuccess() {
        return success == 1;
    }

    public String getErrorMessage() {
        if (error == null) {
            return null;
        }
        return error.Message;
    }

    public int getErrorCode() {
        return error.Code;
    }

    static class Error {
        int Code;
        String Message;
    }
}
