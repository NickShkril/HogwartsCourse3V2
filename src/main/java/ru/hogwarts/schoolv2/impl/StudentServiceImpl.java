package ru.hogwarts.schoolv2.impl;

import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;
import ru.hogwarts.schoolv2.model.Faculty;
import ru.hogwarts.schoolv2.model.Student;
import ru.hogwarts.schoolv2.reposotories.StudentRepository;
import ru.hogwarts.schoolv2.service.StudentService;

import java.util.Collection;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student getStudentsById(long studentId) {
        return studentRepository.getById(studentId).orElseThrow();
    }

    @Override
    public Faculty getStudentFacultyById(long studentId) {
        return getStudentsById(studentId).getFaculty();
    }

    @Override
    public List<Student> getAllStudentsByAge(int age) {
        return studentRepository.findStudentByAge(age);
    }

    @Override
    public Collection<Student> findStudentByAgeBetweenMinAndMax(int min, int max) {
        return studentRepository.findStudentByAgeBetween(min, max);
    }

    @Override
    public Student updateStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public void deleteStudent(long studentId) {
        studentRepository.deleteById(studentId);
    }

    private Student getStudent(long studentId) {
        return studentRepository.findById(studentId).orElseThrow(
                () -> new NotFoundException("Студент не найден!")
        );
    }
}
