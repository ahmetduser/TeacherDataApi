package com.example.lucampusteacherdata.controller;

import com.example.lucampusteacherdata.model.Teacher;
import com.example.lucampusteacherdata.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/lucampus/teacher")
public class TeacherController {

    @Autowired
    TeacherService service;

    @GetMapping()
    public List<Teacher> fetchTeacherList() {
        return service.fetchTeacherList();
    }

    @RequestMapping(value = "createTeacher", method = RequestMethod.POST, produces = {
            MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public void createTeacher(@Validated @RequestBody Teacher t) {
        service.saveTeacherDetails(t);
    }

    @GetMapping("findTeacher/{authId}")
    public ResponseEntity<Teacher> findTeacher(@PathVariable("authId") String authId) {
        return new ResponseEntity<>(service.findTeacher(authId), HttpStatus.OK);
    }
}
