package ru.hogwarts.schoolv2.reposotories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.hogwarts.schoolv2.model.Student;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findStudentByAgeBetween(int min, int max);

    List<Student> findStudentByAge(int age);

    @Query(value = "SELECT * FROM student ORDER BY id DESC Limit 5", nativeQuery = true)
    List<Student> lastFiveStudents();

    @Query(value = "SELECT Count(id) FROM student", nativeQuery = true)
    int studentAmount();

    @Query(value = "SELECT AVG(age) FROM student", nativeQuery = true)
    double studentAverageAge();


}
