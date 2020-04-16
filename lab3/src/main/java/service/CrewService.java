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

public class CrewService {
    private final static String DELETE_CREW_QUERY = "DELETE * from worker where worker_id=?";
    private final static String SELECT_CREW_QUERY = "Select * from crew where crew_id=?";
    private final static String ADD_CREW_QUERY = "";
    private final static String ADD_WORKER_TO_CREW="";
    private final static String DELETE_WORKER_FROM_CREW="";

    private boolean crewIsAssigned()
    {
        return true;
    }

    public Crew removeCrewFromFlight(Crew crew,Flight flight) {
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

    public Crew assignCrewToFlight(Crew crew, Flight flight) {
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

    public Crew removeWorkerFromCrew(Crew crew,Worker worker) {
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

    public Crew assignWorkerToCrew(Crew crew,Worker worker) {
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

    public Crew addCrew(String workerName, String workerSurname, int age, int salary, Profession profession) {
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

    public void updateCrew(Crew crew) {
        try {
            Connection conn = DBConnection.getNewConnection();
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteCrew(Crew crew) {
        try {
            Connection conn = DBConnection.getNewConnection();
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
