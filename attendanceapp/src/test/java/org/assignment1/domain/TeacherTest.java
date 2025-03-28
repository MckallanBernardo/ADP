package org.assignment1.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TeacherTest {

    @Test
    public void testTeacherCreation() {
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

    @Test
    public void testTeacherToString() {
        Teacher teacher = new Teacher.Builder()
                .setTeacherID("1777")
                .setFirstName("Luke")
                .setLastName("Palm")
                .setSubject("English")
                .build();

        String expected = "Teacher{teacherID='1777', firstName='Luke', lastName='Palm', subject='English'}";
        assertEquals(expected, teacher.toString());
    }
}