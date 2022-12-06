package com.example.lucampusteacherdata.model;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Teacher {

    @Id
    @GeneratedValue
    private int id;
    private String teacherAuthId;
    private String name;
    private String surname;
    private String office;
    private String major;
}
