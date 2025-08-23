package za.ac.cput.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Student;
import za.ac.cput.repository.IStudentRepository;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StudentServiceTest {

    @Autowired
    private StudentService service;

    @Autowired
    private IStudentRepository repository;

    private final Student student = new Student.Builder()
            .setStudentID("S1")
            .setFirstName("Alice")
            .setLastName("Smith")
            .build();

    @Test
    void create() {
        Student created = service.create(student);
        assertNotNull(created);
    }

    @Test
    void read() {
        service.create(student);
        Student read = service.read(student.getStudentID());
        assertNotNull(read);
    }

    @Test
    void update() {
        service.create(student);
        Student updated = service.update(
                new Student.Builder().copy(student).setFirstName("Alicia").build());
        assertNotNull(updated);
        assertEquals("Alicia", updated.getFirstName());
    }

    @Test
    void delete() {
        service.create(student);
        service.delete(student.getStudentID());
        Student deleted = service.read(student.getStudentID());
        assertNull(deleted);
    }

    @Test
    void getAll() {
        service.create(student);
        assertFalse(service.getAll().isEmpty());
    }

    @Test
    void findById() {
        service.create(student);
        Student found = service.findById(student.getStudentID());
    }
}
