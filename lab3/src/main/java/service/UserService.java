package service;

import model.Role;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;

public class UserService {
    private final static String ADD_USER_QUERY = "INSERT INTO user (user_name, password, role) VALUES (?,?,?);";
    private final static String SELECT_USER_QUERY = "Select * from user where user_name=? and password=?;";

    public static String hashPassword(String password) {
        return Base64.getEncoder().withoutPadding().encodeToString(password.getBytes());
    }

    public User getUser(String userName, String password) {
        try {
            Connection conn = DBConnection.getNewConnection();
            PreparedStatement pstm = conn.prepareStatement(SELECT_USER_QUERY);
            pstm.setString(1, userName);
            pstm.setString(2, hashPassword(password));
            ResultSet rs = pstm.executeQuery();

            if (rs.next()) {
                User user = new User();
                user.setName(userName);
                user.setId(rs.getLong("id"));
                user.setRole(Role.values()[rs.getInt("role")]);
                conn.close();
                return user;
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

    public User addUser(String userName, String password, Role role) {
        try {
            Connection conn = DBConnection.getNewConnection();
            PreparedStatement pstm = conn.prepareStatement(ADD_USER_QUERY);
            pstm.setString(1, userName);
            pstm.setString(2, hashPassword(password));
            pstm.setInt(3, role.ordinal());
            ResultSet rs = pstm.executeQuery();

            if (rs.next()) {
                User user = new User();
                user.setName(userName);
                user.setId(rs.getLong("id"));
                user.setRole(role);
                conn.close();
                return user;
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

}
