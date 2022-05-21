package ru.hogwarts.schoolv2;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import ru.hogwarts.schoolv2.controller.StudentController;
import ru.hogwarts.schoolv2.model.Student;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private StudentController studentController;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void contextLoads() throws IOException {
        Assertions.assertThat(studentController).isNotNull();
    }

    @Test
    void testPostStudent() {
        Student student = new Student();
        student.setId(1);
        student.setAge(11);
        student.setName("Vanya");

        Assertions.assertThat(this.testRestTemplate.postForObject("http://localhost:" + port + "/student", student, String.class)).isNotNull();
    }

    @Test
    void testGetStudentById() {
        Assertions.assertThat(this.testRestTemplate.getForObject("http://localhost:" + port + "/student/1", String.class)).contains("Igor");
    }

    @Test
    void testGetFacultyStudent() {
        Assertions.assertThat(this.testRestTemplate.getForObject("http://localhost:" + port + "/student/2/faculty", String.class)).isNotNull();
    }
    @Test
    void testGetStudentByAge() {
        Assertions.assertThat(this.testRestTemplate.getForObject("http://localhost:" + port + "/student/filter/11", String.class)).isNotEmpty();
    }

    @Test
    void testGetStudentFinAgeBetween() {
        Assertions.assertThat(this.testRestTemplate.getForObject("http://localhost:" + port + "/student/findAgeBetween?min=0&max=27", List.class)).isNotEmpty();
    }

    @Test
    void testDeleteStudent() {
        assertDoesNotThrow(() -> this.testRestTemplate.delete("http://localhost:" + port + "/student/2"));
    }

    @Test
    void testPutStudent() {
        Student student = new Student();
        student.setId(1);
        student.setAge(11);
        student.setName("Vanya");

        assertDoesNotThrow(() -> this.testRestTemplate.put("http://localhost:" + port + "/student/2", student));
    }
    }

