package za.ac.cput.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import za.ac.cput.domain.Classroom;
import za.ac.cput.domain.Student;
import za.ac.cput.domain.Teacher;
import za.ac.cput.factory.StudentFactory;
import za.ac.cput.factory.TeacherFactory;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ClassroomControllerTest {

    private static Classroom classroom;
    private static Teacher teacher;
    private static Student student1;
    private static List<Student> students;

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort   //this injects the correct random port
    private int port;

    private String baseUrl;

    @BeforeEach
    void setUp() {
        baseUrl = "http://localhost:" + port + "/attendanceapp/classrooms";

        teacher = TeacherFactory.createTeacher("T001", "John", "Doe", "Mathematics");
        student1 = StudentFactory.createStudent("S001", "Jane", "Smith");

        students = new ArrayList<>();
        students.add(student1);

        classroom = new Classroom.Builder()
                .setClassroomId("C1")
                .setClassName("101")
                .build();
    }

    @Test
    void create() {
        String url = baseUrl + "/create";
        Classroom createdClassroom = restTemplate.postForObject(url, classroom, Classroom.class);
        assertNotNull(createdClassroom);
        assertEquals(classroom.getClassroomId(), createdClassroom.getClassroomId());
    }

    @Test
    void read() {
        String url = baseUrl + "/read/" + classroom.getClassroomId();
        restTemplate.postForObject(baseUrl + "/create", classroom, Classroom.class);
        Classroom readClassroom = restTemplate.getForObject(url, Classroom.class);
        assertNotNull(readClassroom);
        assertEquals(classroom.getClassroomId(), readClassroom.getClassroomId());
    }

    @Test
    void update() {
        String url = baseUrl + "/update";
        restTemplate.postForObject(baseUrl + "/create", classroom, Classroom.class);
        Classroom updatedClassroom = new Classroom.Builder().copy(classroom).setClassName("102").build();
        restTemplate.put(url, updatedClassroom);
        Classroom retrievedClassroom = restTemplate.getForObject(baseUrl + "/read/" + classroom.getClassroomId(), Classroom.class);
        assertNotNull(retrievedClassroom);
        assertEquals("102", retrievedClassroom.getClassName());
    }

    @Test
    void delete() {
        String url = baseUrl + "/delete/" + classroom.getClassroomId();
        restTemplate.postForObject(baseUrl + "/create", classroom, Classroom.class);
        restTemplate.delete(url);
        Classroom deletedClassroom = restTemplate.getForObject(baseUrl + "/read/" + classroom.getClassroomId(), Classroom.class);
        assertNull(deletedClassroom);
    }

    @Test
    void getAll() {
        String url = baseUrl + "/getAll";
        restTemplate.postForObject(baseUrl + "/create", classroom, Classroom.class);
        List<?> classrooms = restTemplate.getForObject(url, List.class);
        assertNotNull(classrooms);
        assertFalse(classrooms.isEmpty());
    }

    @Test
    void findById() {
        String url = baseUrl + "/findById/" + classroom.getClassroomId();
        restTemplate.postForObject(baseUrl + "/create", classroom, Classroom.class);
        Classroom foundClassroom = restTemplate.getForObject(url, Classroom.class);
        assertNotNull(foundClassroom);
        assertEquals(classroom.getClassroomId(), foundClassroom.getClassroomId());
    }
}