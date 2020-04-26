package service;

import model.Flight;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FlightService {
    private final static String ADD_FLIGHT_QUERY = "INSERT INTO flight (plane_no, location_to, location_from, " +
            "time_arrival, time_flight_starts) VALUES (?,?,?,?,?);";
    private final static String SELECT_ALL_FLIGHTS = "Select * from flight";
    private final static String SELECT_FLIGHT_QUERY = "Select * from flight where flight_id=?;";
    private final static String SELECT_FLIGHTS_BY_CREW_ID = "Select * from flight where crew_id=?;";
    private final static String DELETE_FLIGHT_QUERY = "DELETE * from flight where flight_id=?;";
    private final static String UPDATE_FLIGHT_QUERY = "UPDATE flight SET plane_no =?, location_to=?, location_from=?," +
            "time_arrival=?, time_flight_starts=? crew_id=? where flight_id = ?;";


    public void deleteFlight(Flight flight) {
        try {
            Connection conn = DBConnection.getNewConnection();
            PreparedStatement pstm = conn.prepareStatement(DELETE_FLIGHT_QUERY);
            pstm.setLong(1, flight.getId());
            pstm.execute();
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Flight> getAllFlights() {
        try {
            Connection conn = DBConnection.getNewConnection();
            PreparedStatement pstm = conn.prepareStatement(SELECT_ALL_FLIGHTS);
            ResultSet rs = pstm.executeQuery();
            List<Flight> flights = new ArrayList<Flight>();
            if (rs.next()) {
                Flight flight = new Flight();
                flight.setLocationFrom(rs.getString("location_from"));
                flight.setLocationTo(rs.getString("location_to"));
                flight.setId(rs.getLong("flight_id"));
                flight.setPlaneNo(rs.getInt("plane_no"));
                flight.setTimeArrival(rs.getTime("time_arrival"));
                flight.setTimeFlightStarts(rs.getTime("time_flight_starts"));
                flight.setCrewId(rs.getLong("crew_id"));
                flights.add(flight);
            }
            rs.close();
            conn.close();
            return flights;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    public Flight getFlight(long flightId) {
        try {
            Connection conn = DBConnection.getNewConnection();
            PreparedStatement pstm = conn.prepareStatement(SELECT_FLIGHT_QUERY);
            pstm.setLong(1, flightId);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                Flight flight = new Flight();
                flight.setLocationFrom(rs.getString("location_from"));
                flight.setLocationTo(rs.getString("location_to"));
                flight.setId(rs.getLong("flight_id"));
                flight.setPlaneNo(rs.getInt("plane_no"));
                flight.setTimeArrival(rs.getTime("time_arrival"));
                flight.setTimeFlightStarts(rs.getTime("time_flight_starts"));
                flight.setCrewId(rs.getLong("crew_id"));
                rs.close();
                conn.close();
                return flight;
            }
            conn.close();
            return null;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    public Flight addFlight(int planeNo, String locationTo, String locationFrom, Time timeArrival, Time timeStarts) {
        try {
            Connection conn = DBConnection.getNewConnection();
            PreparedStatement pstm = conn.prepareStatement(ADD_FLIGHT_QUERY);
            pstm.setInt(1, planeNo);
            pstm.setString(2, locationTo);
            pstm.setString(3, locationFrom);
            pstm.setTime(4, timeArrival);
            pstm.setTime(5, timeStarts);

            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                Flight flight = new Flight();
                flight.setPlaneNo(planeNo);
                flight.setLocationTo(locationTo);
                flight.setLocationFrom(locationFrom);
                flight.setTimeArrival(timeArrival);
                flight.setTimeFlightStarts(timeStarts);
                flight.setId(rs.getLong("flight_id"));
                flight.setCrewId(rs.getLong("crew_id"));
                conn.close();
                return flight;
            }
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
            PreparedStatement pstm = conn.prepareStatement(UPDATE_FLIGHT_QUERY);
            pstm.setInt(1, flight.getPlaneNo());
            pstm.setString(2, flight.getLocationTo());
            pstm.setString(3, flight.getLocationFrom());
            pstm.setTime(4, flight.getTimeArrival());
            pstm.setTime(5, flight.getTimeFlightStarts());
            pstm.setLong(6, flight.getCrewId());
            pstm.setLong(7, flight.getId());

            pstm.executeUpdate();
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Flight> getFlightsFromCrewId(long crewId) {
        try {
            Connection conn = DBConnection.getNewConnection();
            PreparedStatement pstm = conn.prepareStatement(SELECT_FLIGHTS_BY_CREW_ID);
            pstm.setLong(1, crewId);
            ResultSet rs = pstm.executeQuery();
            List<Flight> flights = new ArrayList<Flight>();
            if (rs.next()) {
                Flight flight = new Flight();
                flight.setLocationFrom(rs.getString("location_from"));
                flight.setLocationTo(rs.getString("location_to"));
                flight.setId(rs.getLong("flight_id"));
                flight.setPlaneNo(rs.getInt("plane_no"));
                flight.setTimeArrival(rs.getTime("time_arrival"));
                flight.setTimeFlightStarts(rs.getTime("time_flight_starts"));
                flight.setCrewId(rs.getLong("crew_id"));
                flights.add(flight);
            }
            rs.close();
            conn.close();
            return flights;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    public List<Flight> getFlightsWithoutCrew() {
        return getFlightsFromCrewId(0L);
    }

    public void unassignCrewFromFlight(Flight flight) {
        flight.setCrewId(0L);
        updateFlight(flight);
    }
}
