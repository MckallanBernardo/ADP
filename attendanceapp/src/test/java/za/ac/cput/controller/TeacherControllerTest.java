package za.ac.cput.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import za.ac.cput.domain.Classroom;
import za.ac.cput.domain.Teacher;
import za.ac.cput.factory.TeacherFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TeacherControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    private Teacher teacher;
    private String baseUrl;

    @BeforeEach
    void setUp() {
        baseUrl = "http://localhost:" + port + "/attendanceapp/teacher";

        teacher = TeacherFactory.createTeacher("T001", "John", "Doe", "Mathematics");

    }

    @Test
    void create() {
        String url = baseUrl + "/create";
        Teacher newTeacher = restTemplate.postForObject(url, teacher, Teacher.class);
        assertNotNull(newTeacher);
        assertEquals(teacher.getTeacherID(), newTeacher.getTeacherID());
    }

    @Test
    void read() {
        String url = baseUrl + "/read" + teacher.getTeacherID();
        restTemplate.postForObject(baseUrl + "/create", teacher, Teacher.class);
        Teacher readTeacher = restTemplate.getForObject(url, Teacher.class);
        assertNotNull(readTeacher);
        assertEquals(teacher.getTeacherID(), readTeacher.getTeacherID());
    }

    @Test
    void update() {
        String url = baseUrl + "/update";
        restTemplate.postForObject(baseUrl + "/create", teacher, Teacher.class);
        Teacher updateTeacher = new Teacher.Builder().copy(teacher).setFirstName("Aidan").build();
        restTemplate.put(url, updateTeacher);
        Teacher readTeacher = restTemplate.getForObject(baseUrl + "/read/" + teacher.getTeacherID(), Teacher.class);
        assertNotNull(readTeacher);
        assertEquals("Aidan", readTeacher.getFirstName());
    }

    @Test
    void delete() {
        String url = baseUrl + "/delete/" + teacher.getTeacherID();
        restTemplate.postForObject(baseUrl + "/create", teacher, Teacher.class);
        restTemplate.delete(url);
        Teacher deleteTeacher = restTemplate.getForObject(baseUrl + "/read/" + teacher.getTeacherID(), Teacher.class);
        assertNull(deleteTeacher);
    }

    @Test
    void getAll() {
        String url = baseUrl + "/getAll";
        restTemplate.postForObject(baseUrl + "/create", teacher, Teacher.class);
        List<Teacher> teachers = restTemplate.getForObject(url, List.class);
        assertNotNull(teachers);
        assertFalse(teachers.isEmpty());
    }

    @Test
    void findById() {
        String url = baseUrl + "/findById/" + teacher.getTeacherID();
        restTemplate.postForObject(baseUrl + "/create", teacher, Teacher.class);
        Teacher foundTeacher = restTemplate.getForObject(url, Teacher.class);
        assertNotNull(foundTeacher);
        assertEquals(teacher.getTeacherID(), foundTeacher.getTeacherID());
    }

}