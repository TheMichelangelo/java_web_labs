package model;

import java.sql.Time;

public class Flight {
    Long id;
    String timeFlightStarts;
    String timeArrival;
    String locationFrom;
    String locationTo;
    int planeNo;
    Long crewId;

    public Flight() {
        this.planeNo = 0;
        this.timeFlightStarts = "00:00";
        this.timeArrival = "00:00";
        this.locationFrom="";
        this.locationTo="";
    }

    public String getLocationFrom() {
        return locationFrom;
    }

    public void setLocationFrom(String locationFrom) {
        this.locationFrom = locationFrom;
    }

    public String getLocationTo() {
        return locationTo;
    }

    public void setLocationTo(String locationTo) {
        this.locationTo = locationTo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCrewId() {
        return crewId;
    }

    public void setCrewId(Long crewId) {
        this.crewId = crewId;
    }

    public String getTimeFlightStarts() {
        return timeFlightStarts;
    }

    public void setTimeFlightStarts(String timeFlightStarts) {
        this.timeFlightStarts = timeFlightStarts;
    }

    public String getTimeArrival() {
        return timeArrival;
    }

    public void setTimeArrival(String timeArrival) {
        this.timeArrival = timeArrival;
    }

    public int getPlaneNo() {
        return planeNo;
    }

    public void setPlaneNo(int planeNo) {
        this.planeNo = planeNo;
    }
}
