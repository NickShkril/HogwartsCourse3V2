package ru.hogwarts.schoolv2.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.schoolv2.model.Student;

import java.util.*;

@Service
public class StudentService {

    private Map<Long, Student> students = new HashMap<>();
    private long id = 0;

    public Student createStudent(Student student) {
        student.setId(++id);
        students.put(id, student);
        return student;
    }

    public Student getStudents(long studentId) {
        return students.get(studentId);
    }

    public Collection<Student> getStudentByAge(int age) {
        List<Student> studentByAge = new ArrayList<>();
        for (Student student : students.values()) {
            if (student.getAge() == age) {
                studentByAge.add(student);
            }
        }
        return studentByAge;
    }

    public Student updateStudent(Student student) {
        if (students.containsKey(student.getId())) {
            students.put(student.getId(), student);
            return student;
        }
        return null;
    }

    public Student deleteStudent(long studentId) {
        return students.remove(studentId);
    }
}
