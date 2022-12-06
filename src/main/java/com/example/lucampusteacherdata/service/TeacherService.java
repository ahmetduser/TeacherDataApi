package com.example.lucampusteacherdata.service;

import com.example.lucampusteacherdata.model.Teacher;

import java.util.List;

public interface TeacherService {

    void saveTeacherDetails(Teacher teacher);

    List<Teacher> fetchTeacherList();

    Teacher findTeacher(String id);

}
