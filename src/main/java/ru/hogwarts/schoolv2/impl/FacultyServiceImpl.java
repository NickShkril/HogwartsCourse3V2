package ru.hogwarts.schoolv2.impl;

import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;
import ru.hogwarts.schoolv2.model.Faculty;
import ru.hogwarts.schoolv2.model.Student;
import ru.hogwarts.schoolv2.reposotories.FacultyRepository;
import ru.hogwarts.schoolv2.service.FacultyService;

import java.util.List;

@Service
public class FacultyServiceImpl implements FacultyService {

    private final FacultyRepository facultyRepository;

    public FacultyServiceImpl(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    @Override
    public Faculty createFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    @Override
    public Faculty getFacultyById(long facultyId) {
        return getFaculty(facultyId);
    }

    @Override
    public List<Faculty> getFacultyByColor(String color) {
        return facultyRepository.findFacultyByColor(color);
    }

    @Override
    public List<Faculty> getFIndByNameOrColor(String nameOrColor) {
        return facultyRepository.findFacultyByColorOrNameAllIgnoreCase(nameOrColor, nameOrColor);
    }

    @Override
    public Faculty updateFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    @Override
    public void deleteFaculty(long facultyId) {
        facultyRepository.deleteById(facultyId);
    }


    public Faculty getFaculty(long facultyId) {
        return facultyRepository.findById(facultyId).orElseThrow(() -> new NotFoundException("Факульетет не найден"));
    }
    @Override
    public List<Student> getFacultyStudents(long facultyId) {
        Faculty faculty = getFaculty(facultyId);
        return faculty.getStudentsList();
    }
}