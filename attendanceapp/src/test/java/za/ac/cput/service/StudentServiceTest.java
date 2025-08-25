package za.ac.cput.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Student;
import za.ac.cput.factory.StudentFactory;
import za.ac.cput.repository.IStudentRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StudentServiceTest {

    @Autowired
    private StudentService service;

    @Autowired
    private IStudentRepository studentRepository;

    private Student student;

    @BeforeEach
    void setUp() {
        studentRepository.deleteAll();
        student = StudentFactory.createStudent("S001", "Jane", "Smith");
    }

    @Test
    void create() {
        Student created = service.create(student);
        assertNotNull(created);
        assertEquals(student.getStudentID(), created.getStudentID());
    }

    @Test
    void read() {
        service.create(student);
        Student read = service.read(student.getStudentID());
        assertNotNull(read);
        assertEquals(student.getStudentID(), read.getStudentID());
    }

    @Test
    void update() {
        service.create(student);
        Student updatedStudent = new Student.Builder().copy(student).setFirstName("Janet").build();
        Student updated = service.update(updatedStudent);
        assertNotNull(updated);
        assertEquals("Janet", updated.getFirstName());
    }

    @Test
    void delete() {
        service.create(student);
        boolean deleted = service.delete(student.getStudentID());
        assertTrue(deleted);
    }

    @Test
    void getAll() {
        service.create(student);
        List<Student> students = service.getAll();
        assertNotNull(students);
        assertFalse(students.isEmpty());
    }

    @Test
    void findById() {
        service.create(student);
        Student found = service.findById(student.getStudentID());
        assertNotNull(found);
        assertEquals(student.getStudentID(), found.getStudentID());
    }
}