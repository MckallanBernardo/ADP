package org.assignment1.domain;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

/* StudentTest.java
   Test class for Student model
   Author: Nur Amod (230543502)
   Date: 28 March 2025
*/

class StudentTest {

    @Test
    void getStudentID() {
        Student student = new Student("123456789", "Keagan", "Bernardo", LocalDate.now());
        assertEquals("123456789", student.getStudentID());
    }

    @Test
    void getFirstName() {
        Student student = new Student("123456789", "Keagan", "Bernardo", LocalDate.now());
        assertEquals("Keagan", student.getFirstName());
    }

    @Test
    void getLastName() {
        Student student = new Student("123456789", "Keagan", "Bernardo", LocalDate.now());
        assertEquals("Bernardo", student.getLastName());
    }

    @Test
    void getDateOfBirth() {
        LocalDate today = LocalDate.now();
        Student student = new Student("123456789", "Keagan", "Bernardo", today);
        assertEquals(today, student.getDateOfBirth());
    }

    @Test
    void testToString() {
        Student student = new Student("123456789", "Keagan", "Bernardo", LocalDate.now());
        String expected = "Student{studentID='123456789', firstName='Keagan', lastName='Bernardo', dateOfBirth=" + LocalDate.now() + "}";
        assertEquals(expected, student.toString());
    }
}
