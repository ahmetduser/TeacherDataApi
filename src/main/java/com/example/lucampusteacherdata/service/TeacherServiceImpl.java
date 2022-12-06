package com.example.lucampusteacherdata.service;

import com.example.lucampusteacherdata.model.Teacher;
import com.example.lucampusteacherdata.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    TeacherRepository repo;

    @Override
    public void saveTeacherDetails(Teacher teacher) {
        try {
            repo.saveTeacherDetails(teacher);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Teacher> fetchTeacherList() {
        return repo.fetchAllTeachers();
    }

    @Override
    public Teacher findTeacher(String authId) {
        return repo.findTeacher(authId).get();
    }
}
