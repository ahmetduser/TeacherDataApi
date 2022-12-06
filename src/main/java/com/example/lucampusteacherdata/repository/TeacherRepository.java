package com.example.lucampusteacherdata.repository;

import com.example.lucampusteacherdata.model.Teacher;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class TeacherRepository {
    private Connection con = null;
    @Value("${spring.datasource.url}")
    String dbUrl;
    @Value("${spring.datasource.username}")
    String userName;
    @Value("${spring.datasource.password}")
    String password;


    private Connection getDBConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(dbUrl, userName, password);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getClass().getSimpleName() + " - " + e.getMessage());
        }

        return con;
    }

    public void saveTeacherDetails(Teacher teacher) throws SQLException {
        String create = "INSERT INTO teacher ( teacherAuthId, name, surname, office, major) " +
                " VALUES ( ?, ?, ?, ?, ?)";

        PreparedStatement stm = getDBConnection().prepareStatement(create);

        stm.setString(1, teacher.getTeacherAuthId());
        stm.setString(2, teacher.getName());
        stm.setString(3, teacher.getSurname());
        stm.setString(4, teacher.getOffice());
        stm.setString(5, teacher.getMajor());
        stm.executeUpdate();
    }

    public List<Teacher> fetchAllTeachers() {
        List<Teacher> teacherList = new ArrayList<>();
        Teacher teacher;
        String selectAll = "SELECT * FROM teacher";

        try {
            Statement stm = getDBConnection().createStatement();
            ResultSet rs = stm.executeQuery(selectAll);

            while (rs.next()) {
                teacher = new Teacher();
                teacher.setId(rs.getInt("id"));
                teacher.setTeacherAuthId(rs.getString("teacherAuthId"));
                teacher.setName(rs.getString("name"));
                teacher.setSurname(rs.getString("surname"));
                teacher.setOffice(rs.getString("office"));
                teacher.setMajor(rs.getString("major"));
                teacherList.add(teacher);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return teacherList;
    }

    public Optional<Teacher> findTeacher(String id) {
        Optional<Teacher> te = Optional.empty();
        String find = "SELECT * FROM teacher WHERE teacherAuthId = '" + id + "'";
        Teacher teacher = new Teacher();

        try {
            Statement stm = getDBConnection().createStatement();
            ResultSet rs = stm.executeQuery(find);

            while (rs.next()) {
                teacher.setId(rs.getInt("id"));
                teacher.setTeacherAuthId(rs.getString("teacherAuthId"));
                teacher.setName(rs.getString("name"));
                teacher.setSurname(rs.getString("surname"));
                teacher.setOffice(rs.getString("office"));
                teacher.setMajor(rs.getString("major"));

                te = Optional.of(teacher);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return te;
    }
}
