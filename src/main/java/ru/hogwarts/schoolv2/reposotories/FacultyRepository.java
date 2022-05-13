package ru.hogwarts.schoolv2.reposotories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.schoolv2.model.Faculty;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {
}
