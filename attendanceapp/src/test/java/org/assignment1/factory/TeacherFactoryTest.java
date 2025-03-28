package org.assignment1.factory;

import org.assignment1.domain.Teacher;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TeacherFactoryTest {

    @Test
    void createTeacher() {
        Teacher teacher = new Teacher.Builder()
                .setTeacherID("0777")
                .setFirstName("Aidan")
                .setLastName("Coetzee")
                .setSubject("Mathematics")
                .build();

        assertNotNull(teacher);
        assertEquals("0777", teacher.getTeacherID());
        assertEquals("Aidan", teacher.getFirstName());
        assertEquals("Coetzee", teacher.getLastName());
        assertEquals("Mathematics", teacher.getSubject());
    }
}