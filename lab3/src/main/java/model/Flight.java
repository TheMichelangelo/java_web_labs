package model;

import java.sql.Time;

public class Flight {
    Long id;
    Time timeFlightStarts;
    Time timeArrival;
    int planeNo;
    Crew flightCrew;

    public Flight() {
        this.planeNo = 0;
        this.timeFlightStarts = new Time(0);
        this.timeArrival = new Time(0);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Crew getFlightCrew() {
        return flightCrew;
    }

    public void setFlightCrew(Crew flightCrew) {
        this.flightCrew = flightCrew;
    }
}
