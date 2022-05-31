package ru.hogwarts.schoolv2.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

    @Override
    public Faculty createFaculty(Faculty faculty) {
        logger.info("Was invoked method for create Faculty");
        return facultyRepository.save(faculty);
    }

    @Override
    public Faculty getFacultyById(long facultyId) {
        logger.info("Was invoked method for get Faculty by Id");
        return getFaculty(facultyId);
    }

    @Override
    public List<Faculty> getFacultyByColor(String color) {
        logger.info("Was invoked method for get Faculty by Color");
        return facultyRepository.findFacultyByColor(color);
    }

    @Override
    public List<Faculty> getFIndByNameOrColor(String nameOrColor) {
        logger.info("Was invoked method for find by Name or Color");
        return facultyRepository.findFacultyByColorOrNameAllIgnoreCase(nameOrColor, nameOrColor);
    }

    @Override
    public Faculty updateFaculty(Faculty faculty) {
        logger.info("Was invoked method for update faculty");
        return facultyRepository.save(faculty);
    }

    @Override
    public void deleteFaculty(long facultyId) {
        logger.info("Was invoked method for delete faculty");
        facultyRepository.deleteById(facultyId);
    }


    public Faculty getFaculty(long facultyId) {
        logger.info("Was invoked method for get faculty");
        return facultyRepository.findById(facultyId).orElseThrow(() -> new NotFoundException("Факульетет не найден"));
    }

    @Override
    public List<Student> getFacultyStudents(long facultyId) {
        logger.info("Was invoked method for get Faculty students");
        Faculty faculty = getFaculty(facultyId);
        return faculty.getStudentsList();
    }
}