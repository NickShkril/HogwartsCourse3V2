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
import java.util.Locale;
import java.util.stream.Stream;

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

    @Override
    public List<String> nameStartWithWordA() {
        return studentRepository.findAll()
                .stream()
                .parallel()
                .map(student -> student.getName())
                .filter(a -> a.charAt(0) == 'A')
                .map(a -> a.toUpperCase(Locale.ROOT))
                .sorted()
                .toList();
    }

    @Override
    public Double averageAgeOfAllStudents() {
        return studentRepository.findAll()
                .stream()
                .mapToDouble(student -> student.getAge())
                .average()
                .orElseThrow(() -> new NotFoundException("Not found any student"));
    }


    // Что тут вообще надо делать? Сама идея предлагает a+b заменить на метод Integer::sum
    @Override
    public Integer fastestSum() {
        return Stream.iterate(1, a -> a + 1)
                .limit(1_000_000)
                .reduce(0, (a, b) -> a + b);
    }


    @Override
    public void printName() {
        List<Student> studentsList = studentRepository.findAll();

        System.out.println(studentsList.get(0).getName());
        System.out.println(studentsList.get(1).getName());

        new Thread(() -> {
            System.out.println(studentsList.get(2).getName());
            System.out.println(studentsList.get(3).getName());
        }).start();

        new Thread(() -> {
            System.out.println(studentsList.get(4).getName());
            System.out.println(studentsList.get(5).getName());
        }).start();


    }

    @Override
    public void printNameSync() {
        methodForSyncPrint(0);
        methodForSyncPrint(1);

        new Thread(() -> {
            methodForSyncPrint(2);
            methodForSyncPrint(3);
        }).start();


        new Thread(() -> {
            methodForSyncPrint(4);
            methodForSyncPrint(5);
        }).start();

    }


    private synchronized void methodForSyncPrint(long id) {
        String studentList = studentRepository.getById(id).getName();
        System.out.println(studentList);
    }

}


