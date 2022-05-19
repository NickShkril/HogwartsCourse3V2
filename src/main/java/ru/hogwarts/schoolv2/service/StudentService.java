package ru.hogwarts.schoolv2.service;

import ru.hogwarts.schoolv2.model.Faculty;
import ru.hogwarts.schoolv2.model.Student;

import java.util.Collection;
import java.util.List;


public interface StudentService {


    Student createStudent(Student student);

    Student getStudentsById(long studentId);

    Faculty getStudentFacultyById(long studentId);

    List<Student> getAllStudentsByAge(int age);

    Collection<Student> findStudentByAgeBetweenMinAndMax(int min, int max);

    Student updateStudent(Student student);

    void deleteStudent(long studentId);
}
