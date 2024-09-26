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

//    public void delUser(int id) {
//        try {
//            try {
//                Connection conn = DriverManager.getConnection(url, username, passwd);
//                Statement smt = conn.createStatement();
//                String query1;
//                query1 = String.format("select id from students order by id limit 1 offset %d;", id - 1);
//                ResultSet rs = smt.executeQuery(query1);
//                String query;
//                rs.next();
//                int index = rs.getInt("id");
//
//                query = String.format("delete from students where id = %d", index);
//                smt.executeUpdate(query);
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            }
//        } catch (RuntimeException e) {
//            throw new RuntimeException(e);
//        }
//    }

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

    public List<user> getuse() throws SQLException {
        Connection conn = DriverManager.getConnection(url, username, passwd);
        try {
            Statement smt = conn.createStatement();
            String query = "select * from students";
            ResultSet res = smt.executeQuery(query);
            while (res.next()) {
                user us = new user();
                int age = res.getInt("age");
                char gender = res.getString("gender").charAt(0);
                double salary = res.getDouble("salary");
                String name = res.getString("name");
//                int id = res.getInt("id");
                us.setGender(gender);
                us.setAge(age);
                us.setSalary(salary);
                us.setName(name);
                users.add(us);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }
}
