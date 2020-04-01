package model;

import java.util.ArrayList;
import java.util.List;

public class Crew {
    Long id;
    int no;
    List<Worker> staff = new ArrayList<>();;
    List<Flight> flights  = new ArrayList<>();;

    public Crew() {
        this.no=0;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public List<Flight> getFlights() {
        return flights;
    }

    public void setFlights(List<Flight> flights) {
        this.flights = flights;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Worker> getStaff() {
        return staff;
    }

    public void setStaff(List<Worker> staff) {
        this.staff = staff;
    }
}
