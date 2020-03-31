package service;

import model.Profession;
import model.Worker;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WorkerService {
    private final static String ADD_WORKER_QUERY = "INSERT INTO worker (worker_name,worker_surname, age, salary, " +
            "profession) VALUES (?,?,?,?,?);";
    private final static String SELECT_WORKER_QUERY = "Select * from worker where worker_id=?;";
    private final static String DELETE_WORKER_QUERY = "DELETE * from worker where worker_id=?;";
    private final static String UPDATE_WORKER_QUERY = "UPDATE worker SET worker_name =?, worker_surname=?, age=?, " +
            "salary=?, profession=? where worker_id = ?;";

    public void deleteWorker(long workerId) {
        try {
            Connection conn = DBConnection.getNewConnection();
            PreparedStatement pstm = conn.prepareStatement(DELETE_WORKER_QUERY);
            pstm.setLong(1, workerId);
            pstm.execute();
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Worker getWorker(long workerId) {
        try {
            Connection conn = DBConnection.getNewConnection();
            PreparedStatement pstm = conn.prepareStatement(SELECT_WORKER_QUERY);
            pstm.setLong(1, workerId);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                Worker worker = new Worker();
                worker.setAge(rs.getInt(rs.getInt("age")));
                worker.setName(rs.getString(rs.getString("worker_name")));
                worker.setSalary(rs.getInt(rs.getInt("salary")));
                worker.setSurname(rs.getString(rs.getString("worker_surname")));
                worker.setId(rs.getLong("id"));
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
            PreparedStatement pstm = conn.prepareStatement(ADD_WORKER_QUERY);
            pstm.setString(1, workerName);
            pstm.setString(2, workerSurname);
            pstm.setInt(3, age);
            pstm.setInt(4, salary);
            pstm.setInt(5, profession.ordinal());

            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                Worker worker = new Worker();
                worker.setAge(age);
                worker.setName(workerName);
                worker.setSalary(salary);
                worker.setSurname(workerSurname);
                worker.setId(rs.getLong("id"));
                worker.setProfession(profession);
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

    public void updateWorker(Worker worker) {
        try {
            Connection conn = DBConnection.getNewConnection();
            PreparedStatement pstm = conn.prepareStatement(UPDATE_WORKER_QUERY);
            pstm.setString(1, worker.getName());
            pstm.setString(2, worker.getSurname());
            pstm.setInt(3, worker.getAge());
            pstm.setInt(4, worker.getSalary());
            pstm.setInt(5, worker.getProfession().ordinal());
            pstm.setLong(6, worker.getId());

            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                Worker newWorker = new Worker();
                conn.close();
            }
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}