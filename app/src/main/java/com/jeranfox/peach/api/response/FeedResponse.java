package com.jeranfox.peach.api.response;

public class FeedResponse extends ApiResponse {
    Data data;

    static class Data {
        Connection[] connections;
        FriendRequest[] inboundFriendRequests;
        FriendRequest[] outboundFriendRequests;
        Stream requesterStream;
    }

    public Connection[] getConnections() {
        return data.connections;
    }

    public FriendRequest[] getInboundFriendRequests() {
        return data.inboundFriendRequests;
    }

    public FriendRequest[] getOutboundFriendRequests() {
        return data.outboundFriendRequests;
    }

    public Stream getRequesterStream() {
        return data.requesterStream;
    }
}
