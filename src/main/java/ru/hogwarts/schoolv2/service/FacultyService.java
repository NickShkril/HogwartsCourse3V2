package ru.hogwarts.schoolv2.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.schoolv2.model.Faculty;
import ru.hogwarts.schoolv2.reposotories.FacultyRepository;

import java.util.*;

@Service
public class FacultyService {

    private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty createFaculty(Faculty faculty) {
       return facultyRepository.save(faculty);
    }

    public Faculty getFaculty(long facultyId) {
        return facultyRepository.getById(facultyId);
    }

    public Collection<Faculty> getFacultyByColor(String color) {
        List<Faculty> facultyByColor = new ArrayList<>();
        for (Faculty faculty : facultyRepository.findAll()) {
            if (faculty.getColor().equals(color)) {
                facultyByColor.add(faculty);
            }
        }
        return facultyByColor;
    }

    public Faculty updateFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public void deleteFaculty(long facultyId) {
        facultyRepository.deleteById(facultyId);
    }

}
