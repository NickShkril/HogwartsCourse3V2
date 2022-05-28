package ru.hogwarts.schoolv2;

import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.hogwarts.schoolv2.controller.AvatarController;
import ru.hogwarts.schoolv2.controller.FacultyController;
import ru.hogwarts.schoolv2.controller.StudentController;
import ru.hogwarts.schoolv2.impl.FacultyServiceImpl;
import ru.hogwarts.schoolv2.model.Faculty;
import ru.hogwarts.schoolv2.model.Student;
import ru.hogwarts.schoolv2.reposotories.FacultyRepository;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@AutoConfigureMockMvc
public class FacultyControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FacultyRepository facultyRepository;

    @MockBean
    private AvatarController avatarController;

    @MockBean
    private StudentController studentController;

    @MockBean
    private Faculty faculty;

    @SpyBean
    private FacultyServiceImpl facultyService;

    @InjectMocks
    private FacultyController facultyController;

    @Test
    public void saveFacultyTest() throws Exception {

        final String name = "Griffindor";
        final String color = "red";
        final long id = 1;

        JSONObject facultyObject = new JSONObject();
        facultyObject.put("name", "Griffindor");
        facultyObject.put("color", "red");

        Faculty faculty = new Faculty();
        faculty.setId(1L);
        faculty.setName("Griffindor");
        faculty.setColor("red");

        when(facultyRepository.save(any(Faculty.class))).thenReturn(faculty);
        when(facultyRepository.findById(any(Long.class))).thenReturn(Optional.of(faculty));

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/faculty")
                        .content(facultyObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.color").value(color));
    }

    @Test
    public void GetFacultyByIdTest() throws Exception {

        final String name = "Griffindor";
        final String color = "red";
        final long id = 1;

        JSONObject facultyObject = new JSONObject();
        facultyObject.put("name", "Griffindor");
        facultyObject.put("color", "red");

        Faculty faculty = new Faculty();
        faculty.setId(1L);
        faculty.setName("Griffindor");
        faculty.setColor("red");

        when(facultyRepository.save(any(Faculty.class))).thenReturn(faculty);
        when(facultyRepository.findById(any(Long.class))).thenReturn(Optional.of(faculty));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.color").value(color));

    }


    @Test
    public void getFacultyStudentsTest() throws Exception {
        final String name = "Griffindor";
        final String color = "red";
        final long id = 1;
        List<Student> students = List.of(new Student(1L, "Vanya", 11),
                new Student(2L, "Petya", 22));

        Faculty facultyCopy = new Faculty();
        facultyCopy.setId(id);
        facultyCopy.setName(name);
        facultyCopy.setColor(color);
        facultyCopy.setStudentsList(students);

        JSONObject facultyObject = new JSONObject();
        facultyObject.put(name, facultyCopy);

        doReturn(students).when(facultyService).getFacultyStudents(eq(id));
//        when(facultyService.getFacultyStudents(any(Long.class))).thenReturn(students);
//        when(facultyRepository.findById(any(Long.class))).thenReturn(Optional.of(facultyCopy));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/students?facultyId=1")
                        .content(facultyObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(2)))
                .andExpect(jsonPath("$[0].name").value("Vanya"))
                .andExpect(jsonPath("$[1].age").value("22"));
    }

    @Test
    public void getFacultyByColorTest() throws Exception {
        final String name1 = "Griffindor";
        final String name2 = "Slizerin";
        final String color1 = "red";
        final String color2 = "green";
        final long id1 = 1;
        final long id2 = 2;

        Faculty faculty1 = new Faculty();
        faculty1.setId(id1);
        faculty1.setName(name1);
        faculty1.setColor(color1);

        Faculty faculty2 = new Faculty();
        faculty2.setId(id2);
        faculty2.setName(name2);
        faculty2.setColor(color2);

        JSONObject facultyObject = new JSONObject();
        facultyObject.put(name1, faculty1);
        facultyObject.put(name2, faculty2);

        List<Faculty> faculties = List.of(faculty1, faculty2);

        when(facultyService.getFacultyByColor(any(String.class))).thenReturn(faculties);
        when(facultyRepository.findFacultyByColor(any(String.class))).thenReturn(faculties);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/filter/color/Green")
                        .content(facultyObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(2)))
                .andExpect(jsonPath("$[0].name").value("Griffindor"))
                .andExpect(jsonPath("$[0].color").value("Red"))
                .andExpect(jsonPath("$[1].name").value("Slizerin"))
                .andExpect(jsonPath("$[1].color").value("Green"));
    }

    @Test
    public void getFindNameOrColorTest() throws Exception {
        long id1 = 1L;
        long id2 = 2L;
        String name1 = "Griffindor";
        String name2 = "Slizerin";
        String color = "Red";

        Faculty faculty1 = new Faculty();
        faculty1.setId(id1);
        faculty1.setName(name1);
        faculty1.setColor(color);

        Faculty faculty2 = new Faculty();
        faculty2.setId(id2);
        faculty2.setName(name2);
        faculty2.setColor(color);

        JSONObject facultyObject = new JSONObject();
        facultyObject.put(name1, faculty1);
        facultyObject.put(name2, faculty2);

        List<Faculty> faculties = List.of(faculty1, faculty2);
        List<Faculty> faculties1 = List.of(faculty1);

        when(facultyService.getFIndByNameOrColor("Green")).thenReturn(faculties);
        when(facultyService.getFIndByNameOrColor("Griffindor")).thenReturn(faculties1);
        when(facultyRepository.findFacultyByColorOrNameAllIgnoreCase(color, color)).thenReturn(faculties);
        when(facultyRepository.findFacultyByColorOrNameAllIgnoreCase(name1, name1)).thenReturn(faculties1);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/findNameOrColor?nameOrColor=Red")
                        .content(facultyObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(2)))
                .andExpect(jsonPath("$[0].name").value("Griffindor"))
                .andExpect(jsonPath("$[0].color").value("Red"));
//                .andExpect(jsonPath("$[1].name").value("Slizerin"))
//                .andExpect(jsonPath("$[1].color").value("Green"));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/findNameOrColor?nameOrColor=Griffindor")
                        .content(facultyObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].name").value("Griffindor"))
                .andExpect(jsonPath("$[0].color").value("Red"))
                .andExpect(jsonPath("$[0].id").value("1"));
    }

    @Test
    public void updateFacultyTest() throws Exception {
        long id = 1L;
        String name = "Griffindor";
        String color = "Red";

        JSONObject facultyObject = new JSONObject();
        facultyObject.put(name, id);

        Faculty faculty = new Faculty();
        faculty.setId(id);
        faculty.setName(name);
        faculty.setColor(color);

        when(facultyRepository.save(any(Faculty.class))).thenReturn(faculty);
        when(facultyService.updateFaculty(any(Faculty.class))).thenReturn(faculty);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/faculty")
                        .content(facultyObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.color").value(color));
    }

    @Test
    public void deleteFacultyTest() throws Exception {
        long id = 1L;
        String name = "Griffindor";
        String color = "Red";

        JSONObject facultyObject = new JSONObject();
        facultyObject.put(name, id);

        Faculty faculty = new Faculty();
        faculty.setId(id);
        faculty.setName(name);
        faculty.setColor(color);

        when(facultyRepository.save(any(Faculty.class))).thenReturn(faculty);
        when(facultyService.updateFaculty(any(Faculty.class))).thenReturn(faculty);

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/faculty/1")
                        .content(facultyObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}



