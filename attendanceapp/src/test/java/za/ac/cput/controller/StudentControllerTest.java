package za.ac.cput.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import za.ac.cput.domain.Student;
import za.ac.cput.factory.StudentFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class StudentControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    private String baseUrl;
    private Student student;

    @BeforeEach
    void setUp() {
        baseUrl = "http://localhost:" + port + "/attendanceapp/students";
        student = StudentFactory.createStudent("S001", "Jane", "Smith");
    }

    @Test
    void create() {
        String url = baseUrl + "/create";
        Student created = restTemplate.postForObject(url, student, Student.class);
        assertNotNull(created);
        assertEquals(student.getStudentID(), created.getStudentID());
    }

    @Test
    void read() {
        restTemplate.postForObject(baseUrl + "/create", student, Student.class);
        String url = baseUrl + "/read/" + student.getStudentID();
        Student found = restTemplate.getForObject(url, Student.class);
        assertNotNull(found);
        assertEquals(student.getStudentID(), found.getStudentID());
    }

    @Test
    void update() {
        restTemplate.postForObject(baseUrl + "/create", student, Student.class);
        Student updatedStudent = new Student.Builder().copy(student).setFirstName("Janet").build();
        restTemplate.put(baseUrl + "/update", updatedStudent);
        Student found = restTemplate.getForObject(baseUrl + "/read/" + student.getStudentID(), Student.class);
        assertNotNull(found);
        assertEquals("Janet", found.getFirstName());
    }

    @Test
    void delete() {
        restTemplate.postForObject(baseUrl + "/create", student, Student.class);
        String url = baseUrl + "/delete/" + student.getStudentID();
        restTemplate.delete(url);
        Student deleted = restTemplate.getForObject(baseUrl + "/read/" + student.getStudentID(), Student.class);
        assertNull(deleted);
    }

    @Test
        void getAll() {
        restTemplate.postForObject(baseUrl + "/create", student, Student.class);
        String url = baseUrl + "/getAll";
        List<?> students = restTemplate.getForObject(url, List.class);
        assertNotNull(students);
        assertFalse(students.isEmpty());
    }

    @Test
    void findById() {
        restTemplate.postForObject(baseUrl + "/create", student, Student.class);
        String url = baseUrl + "/findById/" + student.getStudentID();
        Student found = restTemplate.getForObject(url, Student.class);
        assertNotNull(found);
        assertEquals(student.getStudentID(), found.getStudentID());
    }
}
