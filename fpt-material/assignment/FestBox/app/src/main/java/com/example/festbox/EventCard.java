package com.example.festbox;

public class EventCard {
    private String eventID;
    private String imgUrl;
    private String title;
    private String startTime;
    private String address;
    private boolean available;

    public EventCard(String eventID, String imgUrl, String title, String startTime, String address, boolean available) {
        this.eventID = eventID;
        this.imgUrl = imgUrl;
        this.startTime = startTime;
        this.address = address;
        this.available = available;
    }

    public String getEventID() {
        return eventID;
    }

    public void setEventID(String eventID) {
        this.eventID = eventID;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
