package model;

import java.sql.Time;

public class Flight {
    Long id;
    Time timeFlightStarts;
    Time timeArrival;
    String locationFrom;
    String locationTo;
    int planeNo;
    Long crewId;

    public Flight() {
        this.planeNo = 0;
        this.timeFlightStarts = new Time(0);
        this.timeArrival = new Time(0);
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

    public Time getTimeFlightStarts() {
        return timeFlightStarts;
    }

    public void setTimeFlightStarts(Time timeFlightStarts) {
        this.timeFlightStarts = timeFlightStarts;
    }

    public Time getTimeArrival() {
        return timeArrival;
    }

    public void setTimeArrival(Time timeArrival) {
        this.timeArrival = timeArrival;
    }

    public int getPlaneNo() {
        return planeNo;
    }

    public void setPlaneNo(int planeNo) {
        this.planeNo = planeNo;
    }
}
