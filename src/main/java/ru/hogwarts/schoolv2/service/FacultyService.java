package ru.hogwarts.schoolv2.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.schoolv2.model.Faculty;
import ru.hogwarts.schoolv2.model.Student;

import java.util.List;

@Service
public interface FacultyService {


    Faculty createFaculty(Faculty faculty);

    Faculty getFacultyById(long facultyId);

    List<Faculty> getFacultyByColor(String color);

    List<Faculty> getFIndByNameOrColor(String nameOrColor);

    List<Student> getFacultyStudents(long facultyId);

    Faculty updateFaculty(Faculty faculty);

    void deleteFaculty(long facultyId);

    String longestNameOfFaculties();
}
