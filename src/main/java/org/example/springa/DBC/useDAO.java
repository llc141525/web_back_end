package org.example.springa.DBC;

import org.example.springa.users.user;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class useDAO {

    private final String username = "root";
    private final String passwd = "141525";
    private final String url = "jdbc:mysql://localhost:3306/users";
    public List<user> users = new ArrayList<user>();

    public void setUser(user user) {
        try {
            Connection conn = DriverManager.getConnection(url, username, passwd);
            Statement smt = conn.createStatement();
            String query;
            query = String.format("insert into students (age, gender, name, salary)\n" +
                    "values (%d, '%c', '%s', %f);", user.getAge(), user.getGender(), user.getName(), user.getSalary());
            smt.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delUser(int id) {
        try {
            try {
                Connection conn = DriverManager.getConnection(url, username, passwd);
                Statement smt = conn.createStatement();
                String query;
                query = String.format("delete from students order by id limit 1 offset %d - 1", id);
                smt.executeUpdate(query);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (RuntimeException e) {
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
