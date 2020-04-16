package service;

import model.Crew;
import model.Flight;
import model.Profession;
import model.Worker;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FlightService {
    private final static String ADD_FLIGHT_QUERY = "";
    private final static String SELECT_FLIGHT_QUERY = "Select * from flight where flight_id=?;";
    private final static String DELETE_FLIGHT_QUERY = "DELETE * from flight where flight_id=?;";
    private final static String UPDATE_FLIGHT_QUERY = "";
    private final static String SELECT_FLIGHTS_BY_CREW_ID = "Select * from flight where crew_id=?;";

    public void deleteFlight(long flightId) {
        try {
            Connection conn = DBConnection.getNewConnection();
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Flight getFlight(long flightId) {
        try {
            Connection conn = DBConnection.getNewConnection();
            conn.close();
            return null;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    public Flight addFlight() {
        try {
            Connection conn = DBConnection.getNewConnection();
            conn.close();
            return null;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateFlight(Flight flight) {
        try {
            Connection conn = DBConnection.getNewConnection();
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Flight> getFlightsFromCrewId(long crewId)
    {
        try {
            Connection conn = DBConnection.getNewConnection();
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Flight> getFlightsWithoutCrew()
    {
        return getFlightsFromCrewId(0);
    }

    public void UnassignCrewFromFlight(Flight flight)
    {
        flight.setCrew_id(0L);
        updateFlight(flight);
    }
}
