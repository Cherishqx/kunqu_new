package com.example.myapplication2.Data;

public class TicketInformation {
    private String perId;
    private String name;
    private String time;
    private String place;
    private String fare;
    private String picName;

    public TicketInformation(String perId, String name, String time, String place, String fare, String picName) {
        this.perId = perId;
        this.name = name;
        this.time = time;
        this.place = place;
        this.fare = fare;
        this.picName = picName;
    }

    public String getPicName() {
        return picName;
    }

    // Getter 和 Setter 方法
    public String getPerId() {
        return perId;
    }

    public void setPerId(String perId) {
        this.perId = perId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getFare() {
        return fare;
    }

    public void setFare(String fare) {
        this.fare = fare;
    }
}
