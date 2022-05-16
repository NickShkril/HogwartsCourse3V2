package ru.hogwarts.schoolv2.service;

import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;
import ru.hogwarts.schoolv2.model.Faculty;
import ru.hogwarts.schoolv2.model.Student;
import ru.hogwarts.schoolv2.reposotories.FacultyRepository;

import java.util.List;

@Service
public class FacultyService {

    private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty createFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public Faculty getFacultyById(long facultyId) {
        return getFaculty(facultyId);
    }

    public List<Faculty> getFacultyByColor(String color) {
        return facultyRepository.findFacultyByColor(color);
    }

    public List<Faculty> getFIndByNameOrColor(String nameOrColor) {
        return facultyRepository.findFacultyByColorOrNameAllIgnoreCase(nameOrColor, nameOrColor);
    }

    public Faculty updateFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public void deleteFaculty(long facultyId) {
        facultyRepository.deleteById(facultyId);
    }

    public Faculty getFaculty(long facultyId) {
        return facultyRepository.findById(facultyId).orElseThrow(() -> new NotFoundException("Факульетет не найден"));
    }

    public List<Student> getFacultyStudents(long facultyId) {
        Faculty faculty = getFaculty(facultyId);
        return faculty.getStudentsList();
    }
}
