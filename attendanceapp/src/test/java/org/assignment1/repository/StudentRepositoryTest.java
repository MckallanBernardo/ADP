package org.assignment1.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.assignment1.domain.Student;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/* StudentRepositoryTest.java
   Test class for StudentRepository
   Author: Nur Amod (230543502)
   Date: 28 March 2025
*/

class StudentRepositoryTest {

    private final StudentRepository repository = new StudentRepository();
    private final Student student = new Student("123456789", "Keagan", "Bernardo", LocalDate.now());

    @Test
    void create() {
        Student created = repository.create(student);
        assertNotNull(created);
        assertEquals(student.getStudentID(), created.getStudentID());
    }

    @Test
    void findById() {
        repository.create(student);
        Optional<Student> found = repository.findById("123456789");
        assertTrue(found.isPresent());
        assertEquals(student.getStudentID(), found.get().getStudentID());
    }

    @Test
    void findAll() {
        repository.create(student);
        List<Student> students = repository.findAll();
        assertFalse(students.isEmpty());
    }

    @Test
    void update() {
        repository.create(student);
        Student updatedStudent = new Student("123456789", "Keagan", "Smith", LocalDate.now());
        Student result = repository.update(updatedStudent);
        assertNotNull(result);
        assertEquals("Smith", result.getLastName());
    }

    @Test
    void delete() {
        repository.create(student);
        repository.delete("123456789");
        Optional<Student> deletedStudent = repository.findById("123456789");
        assertFalse(deletedStudent.isPresent());
    }

    @Test
    void findByLastName() {
        repository.create(student);
        List<Student> students = repository.findByLastName("Bernardo");
        assertFalse(students.isEmpty());
        assertEquals("Bernardo", students.get(0).getLastName());
    }
}
