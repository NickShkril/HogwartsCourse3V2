package ru.hogwarts.schoolv2.reposotories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.schoolv2.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
