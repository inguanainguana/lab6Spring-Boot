package ru.komogorova.MySpringBoot2Dbase.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.komogorova.MySpringBoot2Dbase.dto.ResponseWrapper;
import ru.komogorova.MySpringBoot2Dbase.entity.Student;
import ru.komogorova.MySpringBoot2Dbase.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/students")
    public ResponseEntity<ResponseWrapper<List<Student>>> showAllStudents() {
        List<Student> students = studentService.getAllStudents();
        return ResponseEntity.ok(new ResponseWrapper<>(true, "Список студентов успешно получен", students));
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<ResponseWrapper<Student>> getStudent(@PathVariable("id") int id) {
        Student student = studentService.getStudent(id);
        if (student == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseWrapper<>(false, "Студент с id = " + id + " не найден", null));
        }
        return ResponseEntity.ok(new ResponseWrapper<>(true, "Студент найден", student));
    }

    @PostMapping("/students")
    public ResponseEntity<ResponseWrapper<Student>> saveStudent(@RequestBody Student student) {
        Student savedStudent = studentService.saveStudent(student);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseWrapper<>(true, "Студент успешно сохранен", savedStudent));
    }

    @PutMapping("/students")
    public ResponseEntity<ResponseWrapper<Student>> updateStudent(@RequestBody Student student) {
        Student updatedStudent = studentService.saveStudent(student);
        return ResponseEntity.ok(new ResponseWrapper<>(true, "Данные студента успешно обновлены", updatedStudent));
    }

    @DeleteMapping("/students/{id}")
    public ResponseEntity<ResponseWrapper<String>> deleteStudent(@PathVariable("id") int id) {
        Student student = studentService.getStudent(id);
        if (student == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseWrapper<>(false, "Студент с id = " + id + " не найден для удаления", null));
        }
        studentService.deleteStudent(id);
        return ResponseEntity.ok(new ResponseWrapper<>(true, "Студент с id = " + id + " успешно удален", null));
    }
}