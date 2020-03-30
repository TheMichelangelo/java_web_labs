package model;

import java.util.ArrayList;
import java.util.List;

public class Airline {
    int id;
    List<Flight> flights;
    List<User> users;
    String name;

    public Airline() {
        this.name="";
        this.users = new ArrayList<User>();
        this.flights = new ArrayList<Flight>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Flight> getFlights() {
        return flights;
    }

    public void setFlights(List<Flight> flights) {
        this.flights = flights;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
