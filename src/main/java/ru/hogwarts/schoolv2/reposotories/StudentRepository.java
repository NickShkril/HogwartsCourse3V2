package ru.hogwarts.schoolv2.reposotories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hogwarts.schoolv2.model.Student;

import java.util.List;
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findStudentByAgeBetween(int min, int max);
    List<Student> findStudentByAge(int age);
}
