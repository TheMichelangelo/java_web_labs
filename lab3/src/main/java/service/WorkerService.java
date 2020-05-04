package service;

import model.Profession;
import model.Worker;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WorkerService {
    private final static String ADD_WORKER_QUERY = "INSERT INTO worker (worker_name,worker_surname, age, salary, " +
            "profession) VALUES (?,?,?,?,?);";
    private final static String SELECT_ALL_WORKERS_QUERY = "Select * from worker";
    private final static String SELECT_WORKER_QUERY = "Select * from worker where worker_id=?;";
    private final static String DELETE_WORKER_QUERY = "DELETE from worker where worker_id=?;";
    private final static String UPDATE_WORKER_QUERY = "UPDATE worker SET worker_name =?, worker_surname=?, age=?, " +
            "salary=?, profession=?, crew_id=? where worker_id =?;";
    private final static String SELECT_WORKERS_BY_CREW_ID = "Select * from worker where crew_id=?;";

    public void deleteWorker(Worker worker) {
        try {
            Connection conn = DBConnection.getNewConnection();
            PreparedStatement pstm = conn.prepareStatement(DELETE_WORKER_QUERY);
            pstm.setLong(1, worker.getId());
            pstm.execute();
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Worker> getAllWorkers() {
        try {
            Connection conn = DBConnection.getNewConnection();
            PreparedStatement pstm = conn.prepareStatement(SELECT_ALL_WORKERS_QUERY);
            ResultSet rs = pstm.executeQuery();
            List<Worker> workerList = new ArrayList<>();
            while (rs.next()) {
                Worker worker = new Worker();
                worker.setAge(rs.getInt("age"));
                worker.setName(rs.getString("worker_name"));
                worker.setSalary(rs.getInt("salary"));
                worker.setSurname(rs.getString("worker_surname"));
                worker.setId(rs.getLong("worker_id"));
                worker.setCrewId(rs.getLong("crew_id"));
                worker.setProfession(Profession.values()[rs.getInt("profession")]);
                workerList.add(worker);
            }
            rs.close();
            conn.close();
            return workerList;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Worker getWorker(long workerId) {
        try {
            Connection conn = DBConnection.getNewConnection();
            PreparedStatement pstm = conn.prepareStatement(SELECT_WORKER_QUERY);
            pstm.setLong(1, workerId);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                Worker worker = new Worker();
                worker.setAge(rs.getInt("age"));
                worker.setName(rs.getString("worker_name"));
                worker.setSalary(rs.getInt("salary"));
                worker.setSurname(rs.getString("worker_surname"));
                worker.setId(rs.getLong("worker_id"));
                worker.setCrewId(rs.getLong("crew_id"));
                worker.setProfession(Profession.values()[rs.getInt("profession")]);
                conn.close();
                return worker;
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

    public Worker addWorker(String workerName, String workerSurname, int age, int salary, Profession profession) {
        try {
            Connection conn = DBConnection.getNewConnection();
            PreparedStatement pstm = conn.prepareStatement(ADD_WORKER_QUERY,Statement.RETURN_GENERATED_KEYS);
            pstm.setString(1, workerName);
            pstm.setString(2, workerSurname);
            pstm.setInt(3, age);
            pstm.setInt(4, salary);
            pstm.setInt(5, profession.ordinal());

            pstm.executeUpdate();

            Worker worker = new Worker();
            worker.setAge(age);
            worker.setName(workerName);
            worker.setSalary(salary);
            worker.setSurname(workerSurname);
            worker.setProfession(profession);
            ResultSet generatedKeys = pstm.getGeneratedKeys();
            if (generatedKeys.next()) {
                worker.setId(generatedKeys.getLong(1));
                return worker;
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

    public void updateWorker(Worker worker) {
        try {
            Connection conn = DBConnection.getNewConnection();
            PreparedStatement pstm = conn.prepareStatement(UPDATE_WORKER_QUERY);
            pstm.setString(1, worker.getName());
            pstm.setString(2, worker.getSurname());
            pstm.setInt(3, worker.getAge());
            pstm.setInt(4, worker.getSalary());
            pstm.setInt(5, worker.getProfession().ordinal());
            pstm.setLong(6, worker.getCrewId());
            pstm.setLong(7, worker.getId());

            pstm.executeUpdate();
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Worker> getWorkersFromCrewId(long crewId)
    {
        try {
            Connection conn = DBConnection.getNewConnection();
            PreparedStatement pstm = conn.prepareStatement(SELECT_WORKERS_BY_CREW_ID);
            pstm.setLong(1, crewId);
            ResultSet rs = pstm.executeQuery();
            List<Worker> workerList = new ArrayList<>();
            while (rs.next()) {
                Worker worker = new Worker();
                worker.setAge(rs.getInt("age"));
                worker.setName(rs.getString("worker_name"));
                worker.setSalary(rs.getInt("salary"));
                worker.setSurname(rs.getString("worker_surname"));
                worker.setId(rs.getLong("worker_id"));
                worker.setCrewId(crewId);
                worker.setProfession(Profession.values()[rs.getInt("profession")]);
                workerList.add(worker);
            }
            conn.close();
            return workerList;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Worker> getWorkersWithoutCrew()
    {
        return getWorkersFromCrewId(0);
    }

    public void unassignWorkerFromCrew(Worker worker)
    {
        worker.setCrewId(0L);
        updateWorker(worker);
    }
}
