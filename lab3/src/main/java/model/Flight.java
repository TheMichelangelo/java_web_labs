package model;

import java.sql.Time;

public class Flight {
    Long id;
    Time timeFlightStarts;
    Time timeArrival;
    int planeNo;
    Long crew_id;

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

    public Long getCrew_id() {
        return crew_id;
    }

    public void setCrew_id(Long crew_id) {
        this.crew_id = crew_id;
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
