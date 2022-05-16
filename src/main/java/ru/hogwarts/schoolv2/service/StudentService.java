package ru.hogwarts.schoolv2.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.schoolv2.model.Faculty;
import ru.hogwarts.schoolv2.model.Student;
import ru.hogwarts.schoolv2.reposotories.StudentRepository;

import java.util.Collection;
import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }


    public List<Student> getAllStudentsByAge(int age) {
        return studentRepository.findStudentByAge(age);
    }


    public Student getStudents(long studentId) {
        return studentRepository.getById(studentId);
    }


    public Student updateStudent(Student student) {
        return studentRepository.save(student);
    }

    public void deleteStudent(long studentId) {
        studentRepository.deleteById(studentId);
    }

    public Faculty getStudentFacultyById(long studentId) {
        return getStudents(studentId).getFaculty();
    }

    public Collection<Student> findStudentByAgeBetweenMinAndMax(int min, int max) {
        return studentRepository.findStudentByAgeBetween(min, max);
    }
}
