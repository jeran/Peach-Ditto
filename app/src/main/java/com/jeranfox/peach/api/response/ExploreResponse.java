package com.jeranfox.peach.api.response;

public class ExploreResponse extends ApiResponse {
    Data data;

    public Connection[] getConnections() {
        return data.connections;
    }

    static class Data {
        Connection[] connections;
    }
}
