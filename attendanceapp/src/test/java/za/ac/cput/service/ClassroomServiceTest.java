package za.ac.cput.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Classroom;
import za.ac.cput.repository.IClassroomRepository;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ClassroomServiceTest {

    @Autowired
    private ClassroomService service;

    @Autowired
    private IClassroomRepository classroomRepository;

    private Classroom classroom;

    @BeforeEach
    void setUp() {
        classroomRepository.deleteAll();

        classroom = new Classroom.Builder()
                .setClassroomId("C1")
                .setClassName("101")
                .build();
    }
    @Test
    void create() {
        Classroom created = service.create(classroom);
        assertNotNull(created);
    }

    @Test
    void read() {
        Classroom created = service.create(classroom);
        Classroom read = service.read(created.getClassroomId());
        assertNotNull(read);
    }

    @Test
    void update() {
        Classroom created = service.create(classroom);
        Classroom updated = service.update
                (new Classroom.Builder().copy(created).setClassName("102").build());
        assertNotNull(updated);
        assertEquals("102", updated.getClassName());
    }

    @Test
    void delete() {
        Classroom created = service.create(classroom);
        boolean deleted = service.delete(created.getClassroomId());
        assertTrue(deleted);
    }

    @Test
    void getAll() {
        service.create(classroom);
        List<Classroom> classrooms = service.getAll();
        assertNotNull(classrooms);
        assertFalse(classrooms.isEmpty());
    }

    @Test
    void findById() {
        Classroom created = service.create(classroom);
        Classroom found = service.findById(created.getClassroomId());
        assertNotNull(found);
    }
}