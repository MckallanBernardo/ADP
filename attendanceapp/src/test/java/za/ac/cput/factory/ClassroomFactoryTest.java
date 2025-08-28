package za.ac.cput.factory;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Classroom;
import za.ac.cput.domain.Student;
import za.ac.cput.domain.Teacher;

import java.util.Collections;
import java.util.List;

class ClassroomFactoryTest {

    private ClassroomFactory factory;

    @BeforeEach
    void setUp() {
        factory = new ClassroomFactory();
    }

    @Test
    void createClassroom() {
        Classroom classroom = factory.createClassroom("C001", "101");
        assertNotNull(classroom);
        assertEquals("C001", classroom.getClassroomId());
        assertEquals("101", classroom.getClassName());
    }
}
