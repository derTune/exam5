package kg.serv.dao;

import kg.serv.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    UserDao udDb = new UserDao();
    static List<User> userList = new ArrayList<>();
    private static final String url = "jdbc:postgresql://localhost:5432/";
    private static final String user = "postgres";
    private static final String password = "1234";

    public static Connection connect() {
        Connection conn = null;
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected successfully");
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println(e.getMessage());
        }
        return conn;
    }

    public User addUser() {
        User u = new User();
        String SQL = "insert into users (name, birth_date, gender)" +
                "values (?, ?, ?)";
        try (Connection conn = udDb.connect();
             PreparedStatement statement = conn.prepareStatement(SQL)) {
            statement.setString(1, u.getName());
            statement.setInt(2, u.getBirthDate());
            statement.setString(3, u.getGender());
            statement.executeUpdate();
            return u;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public List<User> getUserList() {
        return userList;
    }

    public User getUserById(int id) {
        String SQL = "select * from users where id = ?";
        User u = null;
        try (Connection conn = udDb.connect();
            PreparedStatement statement = conn.prepareStatement(SQL)) {
            statement.setInt(1, id);
            statement.executeUpdate();
            try (ResultSet rs = statement.executeQuery()) {
                if(rs.next()) {
                    u = new User();
                    u.setName(rs.getString("NAME"));
                    u.setBirthDate(rs.getInt("BIRTH_DATE"));
                    u.setGender(rs.getString("GENDER"));
                    return u;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return u;
    }

    public User updateUser(int id) {
        String SQL = "update users set name = ?, birth_date = ?, gender = ? where id = ?";
        User u = null;
        try (Connection conn = udDb.connect();
             PreparedStatement statement = conn.prepareStatement(SQL)) {
            statement.setString(1, u.getName());
            statement.setInt(2, u.getBirthDate());
            statement.setString(3, u.getGender());
            statement.executeUpdate();
            return u;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return u;
    }

    public boolean deleteUserById(int id) {
        String SQL = "delete from users where id = ?";
        User u = null;
        try (Connection conn = udDb.connect();
             PreparedStatement statement = conn.prepareStatement(SQL)) {
            statement.setInt(1, id);
            statement.executeUpdate();
            try (ResultSet rs = statement.executeQuery()) {
                if(rs.next()) {
                   return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
