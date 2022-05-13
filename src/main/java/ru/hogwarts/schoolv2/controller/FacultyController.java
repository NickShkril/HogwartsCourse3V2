package ru.hogwarts.schoolv2.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.schoolv2.model.Faculty;
import ru.hogwarts.schoolv2.service.FacultyService;

import java.util.Collection;

@RestController
@RequestMapping("faculty")
public class FacultyController {

    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @PostMapping
    public ResponseEntity<Faculty> createFaculty(@RequestBody Faculty faculty) {
        Faculty createdFaculty = facultyService.createFaculty(faculty);
        return ResponseEntity.ok(createdFaculty);
    }

    @GetMapping("{facultyId}")
    public ResponseEntity<Faculty> getFacultyById(@PathVariable long facultyId) {
        Faculty faculty = facultyService.getFaculty(facultyId);
        if (faculty == null) {
            return ResponseEntity.badRequest().build()  ;
        }
        return ResponseEntity.ok(faculty);
    }

    @GetMapping({"filter/{color}"})
    public ResponseEntity<Collection<Faculty>> getFacultyByColor(@PathVariable String color) {
        Collection<Faculty> faculties = facultyService.getFacultyByColor(color);
        if (faculties.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(faculties);
    }

    @PutMapping
    public ResponseEntity<Faculty> updateFaculty(@RequestBody Faculty faculty) {
        Faculty updatedFaculty = facultyService.updateFaculty(faculty);
        if (updatedFaculty == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(updatedFaculty);
    }

    @DeleteMapping("{facultyId}")
    public ResponseEntity<Faculty> deleteFaculty(@PathVariable long facultyId) {
        Faculty faculty = facultyService.deleteFaculty(facultyId);
        if (faculty == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(faculty);
    }
}
