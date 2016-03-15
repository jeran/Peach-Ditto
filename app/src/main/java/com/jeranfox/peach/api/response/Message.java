package com.jeranfox.peach.api.response;

public class Message {
    String text;
    String type;
    double height;
    String src;
    int width;
    String posterSrc;
    String subtype;
    String[] formattedAddress;
    String foursquareId;
    String iconSrc;
    double lat;
    double lon;
    String name;
    String description;
    String imageURL;

    public String getText() {
        return text;
    }

    public String getType() {
        return type;
    }
}
