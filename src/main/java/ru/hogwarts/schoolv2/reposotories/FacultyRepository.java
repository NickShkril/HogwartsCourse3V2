package ru.hogwarts.schoolv2.reposotories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hogwarts.schoolv2.model.Faculty;

import java.util.List;
@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Long> {
    List<Faculty> findFacultyByColor(String color);
    List<Faculty> findFacultyByColorOrNameAllIgnoreCase(String color, String name);
}
