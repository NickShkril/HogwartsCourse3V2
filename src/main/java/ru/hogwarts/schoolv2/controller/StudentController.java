package ru.hogwarts.schoolv2.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.schoolv2.model.Faculty;
import ru.hogwarts.schoolv2.model.Student;
import ru.hogwarts.schoolv2.service.StudentService;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        Student createdStudent = studentService.createStudent(student);
        return ResponseEntity.ok(createdStudent);
    }

    @GetMapping("{studentId}")
    public ResponseEntity<Student> getStudentById(@PathVariable long studentId) {
        Student student = studentService.getStudents(studentId);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    @GetMapping("{id}/faculty")
    public ResponseEntity<Faculty> getStudentFaculty(@PathVariable long id) {
        Faculty faculty = studentService.getStudentFacultyById(id);
        if (faculty == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(faculty);
    }

    @GetMapping("/filter/{age}")
    public ResponseEntity<List<Student>> getAllStudentsByAge(@PathVariable int age) {
        List<Student> students = studentService.getAllStudentsByAge(age);
        if (students.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(students);
    }

    @GetMapping("/findAgeBetween")
    public ResponseEntity<Collection<Student>> getFindByAgeBetween(@RequestParam int min, @RequestParam int max) {
        Collection<Student> students = studentService.findStudentByAgeBetweenMinAndMax(min, max);
        if (students.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(students);
    }


    @PutMapping
    public ResponseEntity<Student> updateStudent(@RequestBody Student student) {
        Student updatedStudent = studentService.updateStudent(student);
        if (updatedStudent == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedStudent);
    }

    @DeleteMapping("{studentId}")
    public ResponseEntity<Student> deleteStudent(@PathVariable long studentId) {
        studentService.deleteStudent(studentId);
        return ResponseEntity.ok().build();
    }
}
