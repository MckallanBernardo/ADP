package org.assignment1.repository;

import org.assignment1.domain.Teacher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/* TeacherRepositoryTest.java
TeacherRepositoryTest class
Author: Aidan Coetzee (230563724)
Date: 27 March 2025
 */

class TeacherRepositoryTest {

    private TeacherRepository repository;
    private Teacher teacher1, teacher2;

    @BeforeEach
    void setUp() {
        repository = new TeacherRepository();
        teacher1 = new Teacher("0777", "Aidan", "Coetzee", "Mathematics");
        teacher2 = new Teacher("1777", "Luke","Palm", "English");
        repository.create(teacher1);
        repository.create(teacher2);
    }

    @Test
    void testCreate() {
        Teacher teacher3 = new Teacher("2777", "Alice"," Brown", "History");
        Teacher created = repository.create(teacher3);
        assertNotNull(created);
        assertEquals("2777", created.getTeacherID());
        assertEquals(3, repository.findAll().size());
    }

    @Test
    void testFindById() {
        Optional<Teacher> found = repository.findById("0777");
        assertTrue(found.isPresent());
        assertEquals("Aidan", found.get().getTeacherID());
    }

    @Test
    void testFindAll() {
        assertEquals(2, repository.findAll().size());
    }

    @Test
    void testUpdate() {
        Teacher updatedTeacher = new Teacher("0777", "Aidan", " Coetzee", "Physics");
        repository.update(updatedTeacher);
        Optional<Teacher> found = repository.findById("0777");
        assertTrue(found.isPresent());
        assertEquals("Physics", found.get().getSubject());
    }

    @Test
    void testDelete() {
        repository.delete("T002");
        assertEquals(1, repository.findAll().size());
        assertFalse(repository.findById("T002").isPresent());
    }

    @Test
    void testFindByTeacherName() {
        assertEquals(1, repository.findByTeacherName("T001").size());
    }

}