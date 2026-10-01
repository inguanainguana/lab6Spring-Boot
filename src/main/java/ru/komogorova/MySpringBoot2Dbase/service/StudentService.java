package ru.komogorova.MySpringBoot2Dbase.service;

import ru.komogorova.MySpringBoot2Dbase.entity.Student;
import java.util.List;

public interface StudentService {
    List<Student> getAllStudents();
    Student saveStudent(Student student);
    Student getStudent(int id);
    void deleteStudent(int id);
}
