package org.example.springa.DBC;

import org.example.springa.users.user;
import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class useDAO {

    private final String username = "root";
    private final String passwd = "141525";
    private final String url = "jdbc:mysql://localhost:3306/users";
    public List<user> users = new ArrayList<user>();

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, passwd);
    }

    public void setUser(@NotNull user user) {
        String query = "insert into students(name, age, salary, gender) values (?,?,?,?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)
        ) {
            pstmt.setString(1, user.getName());
            pstmt.setInt(2, user.getAge());
            pstmt.setDouble(3, user.getSalary());
            pstmt.setString(4, String.valueOf(user.getGender()));
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delUser(int id) {
        String findIdQuery = String.format("SELECT id FROM students ORDER BY id LIMIT 1 OFFSET %d;", id - 1);
        String delByIdQuery = "DELETE FROM students WHERE id = ?;";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
        ) {
            ResultSet res = stmt.executeQuery(findIdQuery);
            res.next();
            int index = res.getInt("id");
            PreparedStatement pstmt = conn.prepareStatement(delByIdQuery);
            pstmt.setInt(1, index);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<user> getUsers() {
        String query = "SELECT * FROM students";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
        ) {
            ResultSet res = stmt.executeQuery(query);
            while (res.next()) {
                user us = new user();
                us.setName(res.getString("name"));
                us.setAge(res.getInt("age"));
                us.setSalary(res.getDouble("salary"));
                us.setGender(res.getString("gender").charAt(0));
                users.add(us);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    public void changeUser(int id, @NotNull user targetUs) {
        String findId = String.format("SELECT id FROM students ORDER BY id LIMIT 1 OFFSET %d", id - 1);
        String query = "UPDATE students SET name = ?, age = ?, salary=?, gender = ? where id= ?";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
        ) {
            ResultSet res = stmt.executeQuery(findId);
            res.next();
            int index = res.getInt("id");
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, targetUs.getName());
            pstmt.setInt(2, targetUs.getAge());
            pstmt.setDouble(3, targetUs.getSalary());
            pstmt.setString(4, String.valueOf(targetUs.getGender()));
            pstmt.setInt(5, index);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
