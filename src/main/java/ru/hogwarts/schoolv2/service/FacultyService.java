package ru.hogwarts.schoolv2.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.schoolv2.model.Faculty;

import java.util.*;

@Service
public class FacultyService {

    private Map<Long, Faculty> faculties = new HashMap<>();
    private long id = 0L;

    public Faculty createFaculty(Faculty faculty) {
        faculty.setId(++id);
        faculties.put(id, faculty);
        return faculty;
    }

    public Faculty getFaculty(long facultyId) {
        return faculties.get(facultyId);
    }

    public Collection<Faculty> getFacultyByColor(String color) {
        List<Faculty> facultyByColor = new ArrayList<>();
        for (Faculty faculty : faculties.values()) {
            if (faculty.getColor().equals(color)) {
                facultyByColor.add(faculty);
            }
        }
        return facultyByColor;
    }

    public Faculty updateFaculty(Faculty faculty) {
       if(faculties.containsKey(faculty.getId())){
           faculties.put(faculty.getId(), faculty);
           return faculty;
       }
        return null;
    }

    public Faculty deleteFaculty(long facultyId) {
        return faculties.remove(facultyId);
    }

}
