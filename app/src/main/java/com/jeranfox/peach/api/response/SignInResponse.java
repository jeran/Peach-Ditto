package com.jeranfox.peach.api.response;

import java.util.List;

public class SignInResponse extends ApiResponse {
    private Data data;

    public String getToken() {
        if (data == null || data.streams == null || data.streams.isEmpty()) {
            return null;
        }
        return data.streams.get(0).token;
    }

    static class Data {
        List<IdTokenPair> streams;
        String token;
    }

    static class IdTokenPair {
        String id;
        String token;
    }
}

