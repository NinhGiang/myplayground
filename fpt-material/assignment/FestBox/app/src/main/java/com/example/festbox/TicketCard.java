package com.example.festbox;

public class TicketCard {
    private String ticketID;
    private String eventID;
    private String startEndTime;
    private String address;
    private String qrString;
    private boolean used;

    public TicketCard(String ticketID, String eventID, String startEndTime, String address, String qrString, boolean used) {
        this.ticketID = ticketID;
        this.eventID = eventID;
        this.startEndTime = startEndTime;
        this.address = address;
        this.qrString = qrString;
        this.used = used;
    }

    public String getTicketID() {
        return ticketID;
    }

    public void setTicketID(String ticketID) {
        this.ticketID = ticketID;
    }

    public String getEventID() {
        return eventID;
    }

    public void setEventID(String eventID) {
        this.eventID = eventID;
    }

    public String getStartEndTime() {
        return startEndTime;
    }

    public void setStartEndTime(String startEndTime) {
        this.startEndTime = startEndTime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getQrString() {
        return qrString;
    }

    public void setQrString(String qrString) {
        this.qrString = qrString;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }
}
