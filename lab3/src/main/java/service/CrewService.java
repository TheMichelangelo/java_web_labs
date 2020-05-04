package service;

import model.Crew;
import model.Flight;
import model.Worker;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CrewService {
    private final static String DELETE_CREW_QUERY = "DELETE from crew where crew_id=?";
    private final static String SELECT_ALL_CREWS_QUERY = "SELECT * from crew";
    private final static String SELECT_CREW_QUERY = "SELECT * from crew where crew_id=?";
    private final static String ADD_CREW_QUERY = "INSERT INTO crew (crew_number) VALUES (?)";
    private final static String UPDATE_CREW_QUERY = "UPDATE crew SET crew_number=? where crew_id=?";

    public boolean checkCrewIsAssigned(Crew crew) {
        FlightService fs = new FlightService();
        List<Flight> flights = fs.getFlightsFromCrewId(crew.getId());
        if (flights.isEmpty())
            return false;
        return true;
    }

    public boolean checkCrewIsComplete(Crew crew) {
        WorkerService ws = new WorkerService();
        List<Worker> workers = ws.getWorkersFromCrewId(crew.getId());
        int pilotsCount = 0, navigatorCount = 0, operatorCount = 0, stewardessCount = 0;
        for (Worker w : workers) {
            switch (w.getProfession()) {
                case PILOT:
                    pilotsCount++;
                    break;
                case NAVIGATOR:
                    navigatorCount++;
                    break;
                case OPERATOR:
                    operatorCount++;
                    break;
                case STEWARDESS:
                    stewardessCount++;
                    break;
            }
        }
        if (pilotsCount < 2 || navigatorCount < 1 || operatorCount < 1 || stewardessCount < 2)
            return false;
        return true;
    }

    public Crew removeCrewFromFlight(Crew crew, Flight flight) {
        crew.removeFlight(flight);
        FlightService fs = new FlightService();
        fs.unassignCrewFromFlight(flight);
        return crew;
    }

    public Crew assignCrewToFlight(Crew crew, Flight flight) {
        FlightService fs = new FlightService();
        flight.setCrewId(crew.getId());
        fs.updateFlight(flight);
        crew.addFlight(flight);
        return crew;
    }

    public Crew removeWorkerFromCrew(Crew crew, Worker worker) {
        crew.removeWorker(worker);
        WorkerService ws = new WorkerService();
        ws.unassignWorkerFromCrew(worker);
        return crew;
    }

    public Crew assignWorkerToCrew(Crew crew, Worker worker) {
        WorkerService ws = new WorkerService();
        worker.setCrewId(crew.getId());
        ws.updateWorker(worker);
        crew.addWorker(worker);
        return crew;
    }

    public Crew addCrew(int no) {
        try {
            Connection conn = DBConnection.getNewConnection();
            PreparedStatement pstm = conn.prepareStatement(ADD_CREW_QUERY,Statement.RETURN_GENERATED_KEYS);
            pstm.setInt(1, no);

            pstm.executeUpdate();
            Crew crew = new Crew();
            crew.setNo(no);
            ResultSet generatedKeys = pstm.getGeneratedKeys();
            if (generatedKeys.next()) {
                crew.setId(generatedKeys.getLong(1));
                generatedKeys.close();
                conn.close();
                return crew;
            }
            generatedKeys.close();
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
            PreparedStatement pstm = conn.prepareStatement(UPDATE_CREW_QUERY,Statement.RETURN_GENERATED_KEYS);
            pstm.setInt(1, crew.getNo());
            pstm.setLong(2, crew.getId());
            pstm.executeUpdate();
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
            PreparedStatement pstm = conn.prepareStatement(DELETE_CREW_QUERY);
            WorkerService ws = new WorkerService();
            FlightService fs = new FlightService();
            List<Worker> stuff = crew.getStaff();
            List<Flight> flights = crew.getFlights();
            for (Worker w : stuff)
                ws.unassignWorkerFromCrew(w);
            for (Flight f : flights)
                fs.unassignCrewFromFlight(f);
            pstm.setLong(1, crew.getId());
            pstm.execute();
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Crew getCrew(long crewId) {
        try {
            Connection conn = DBConnection.getNewConnection();
            PreparedStatement pstm = conn.prepareStatement(SELECT_CREW_QUERY);
            pstm.setLong(1, crewId);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                Crew crew = new Crew();
                crew.setNo(rs.getInt("crew_number"));
                crew.setId(rs.getLong("crew_id"));
                WorkerService ws = new WorkerService();
                List<Worker> staff = ws.getWorkersFromCrewId(crew.getId());
                FlightService fs = new FlightService();
                List<Flight> flights = fs.getFlightsFromCrewId(crew.getId());
                crew.setFlights(flights);
                crew.setStaff(staff);
                rs.close();
                conn.close();
                return crew;
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

    public List<Crew> getAllCrews() {
        try {
            Connection conn = DBConnection.getNewConnection();
            PreparedStatement pstm = conn.prepareStatement(SELECT_ALL_CREWS_QUERY);
            ResultSet rs = pstm.executeQuery();
            List<Crew> crewList = new ArrayList<>();
            while (rs.next()) {
                Crew crew = new Crew();
                crew.setNo(rs.getInt("crew_number"));
                crew.setId(rs.getLong("crew_id"));
                WorkerService ws = new WorkerService();
                List<Worker> staff = ws.getWorkersFromCrewId(crew.getId());
                FlightService fs = new FlightService();
                List<Flight> flights = fs.getFlightsFromCrewId(crew.getId());
                crew.setFlights(flights);
                crew.setStaff(staff);
                crewList.add(crew);
            }
            rs.close();
            conn.close();
            return crewList;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
