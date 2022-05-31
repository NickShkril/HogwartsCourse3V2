package ru.hogwarts.schoolv2.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

    @Override
    public Student createStudent(Student student) {
        logger.info("Was invoked method for create Student");
        return studentRepository.save(student);
    }

    @Override
    public Student getStudentsById(long studentId) {
        logger.info("Was invoked method for get student by ID");
        return studentRepository.findById(studentId).orElseThrow();
    }

    @Override
    public Faculty getStudentFacultyById(long studentId) {
        logger.info("Was invoked method for get student faculty by ID");
        return getStudentsById(studentId).getFaculty();
    }

    @Override
    public List<Student> getAllStudentsByAge(int age) {
        logger.info("Was invoked method for get all students by AGE");
        return studentRepository.findStudentByAge(age);
    }

    @Override
    public Collection<Student> findStudentByAgeBetweenMinAndMax(int min, int max) {
        logger.info("Was invoked method for find students by age between min and max");
        return studentRepository.findStudentByAgeBetween(min, max);
    }

    @Override
    public Student updateStudent(Student student) {
        logger.info("Was invoked method for update Student");
        return studentRepository.save(student);
    }

    @Override
    public void deleteStudent(long studentId) {
        logger.info("Was invoked method for delete student");
        studentRepository.deleteById(studentId);
    }

    private Student getStudent(long studentId) {
        logger.info("Was invoked method for get Student");
        return studentRepository.findById(studentId).orElseThrow(
                () -> new NotFoundException("Студент не найден!")
        );
    }

    @Override
    public Integer amountStudents() {
        logger.info("Was invoked method for get amount students");
        return studentRepository.studentAmount();
    }

    @Override
    public Double studentAverageAge() {
        logger.info("Was invoked method for find student average age");
        return studentRepository.studentAverageAge();
    }

    @Override
    public List<Student> lastFiveStudents() {
        logger.info("Was invoked method for find last five Students");
        return studentRepository.lastFiveStudents();
    }


}
