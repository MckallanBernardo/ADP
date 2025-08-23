package za.ac.cput.factory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Student;

import static org.junit.jupiter.api.Assertions.*;

class StudentFactoryTest {

    private StudentFactory factory;

    @BeforeEach
    void setUp() {
        factory = new StudentFactory();
    }

    @Test
    void createStudent() {
        Student student = factory.createStudent(
                "S1",
                "Alice",
                "Smith"
        );
        assertNotNull(student);
        assertEquals("S1", student.getStudentID());
        assertEquals("Alice", student.getFirstName());
        assertEquals("Smith", student.getLastName());

    }
}
