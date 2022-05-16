package ru.hogwarts.schoolv2.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.schoolv2.model.Faculty;
import ru.hogwarts.schoolv2.model.Student;
import ru.hogwarts.schoolv2.service.FacultyService;

import java.util.Collection;
import java.util.List;

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
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(faculty);
    }
    @GetMapping("students")
    public ResponseEntity<List<Student>> getFacultyStudents(@RequestParam("facultyId") long facultyId) {
        List<Student> students = facultyService.getFacultyStudents(facultyId);
        if (students.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(students);
    }

    @GetMapping("filter/color")
    public ResponseEntity<Collection<Faculty>> getFacultyByColor(@PathVariable String color) {
        List<Faculty> faculties = facultyService.getFacultyByColor(color);
        if (faculties.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(faculties);
    }

    @GetMapping("/findNameOrColor")
    public ResponseEntity<List<Faculty>> getFacultyByNameOrColor(@PathVariable String nameOrColor) {
        List<Faculty> faculties = facultyService.getFIndByNameOrColor(nameOrColor);
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
        facultyService.deleteFaculty(facultyId);
        return ResponseEntity.ok().build();
    }
}
