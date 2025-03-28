package org.assignment1.factory;

import org.assignment1.domain.Student;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

/* StudentFactoryTest.java
StudentFactoryTest class
Author: Nur Amod (230543502)
Date: 27 March 2025
*/

class StudentFactoryTest {

    @Test
    void testCreateStudent() {
        Student student = StudentFactory.createStudent("2301", "John", "Doe", LocalDate.of(2003, 5, 10));
        assertNotNull(student);
        assertEquals("2301", student.getStudentID());
        assertEquals("John", student.getFirstName());
        assertEquals("Doe", student.getLastName());
        assertEquals(LocalDate.of(2003, 5, 10), student.getDateOfBirth());
    }
}
