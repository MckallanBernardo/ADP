package org.assignment1.repository;

import org.assignment1.domain.Classroom;
import org.assignment1.domain.Student;
import org.assignment1.domain.Teacher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ClassroomRepositoryTest {
    private ClassroomRepository classroomRepository;
    private Classroom classroom1, classroom2, classroom3;
    private Classroom updatedClassroom;

    @BeforeEach
    void setUp() {
        classroomRepository = new ClassroomRepository();

        ArrayList<Teacher> teachers = new ArrayList<>();
        teachers.add(new Teacher("T001", "John", "Smith", "Physical Science"));

        ArrayList<Student> students = new ArrayList<>();
        students.add(new Student("S001", "Lizzy", "Mcguire", LocalDate.of(2024, 3, 19)));

        classroom1 = new Classroom.ClassroomBuilder()
                .setClassroomID("C101")
                .setClassName("Mathematics")
                .setTeacher(new ArrayList<>(teachers))
                .setStudent(new ArrayList<>(students))
                .build();

        classroom2 = new Classroom.ClassroomBuilder()
                .setClassroomID("C102")
                .setClassName("Science")
                .setTeacher(new ArrayList<>(teachers))
                .setStudent(new ArrayList<>(students))
                .build();

        classroom3 = new Classroom.ClassroomBuilder()
                .setClassroomID("C103")
                .setClassName("Mathematics")
                .setTeacher(new ArrayList<>(teachers))
                .setStudent(new ArrayList<>(students))
                .build();

        classroomRepository.create(classroom1);
        classroomRepository.create(classroom2);
        classroomRepository.create(classroom3);

    }

    @Test
    void create() {
        Classroom createdClassroom = classroomRepository.create(classroom1);

        assertNotNull(createdClassroom, "Created classroom should not be null");
        assertEquals(classroom1, createdClassroom, "Created classroom should match the input classroom");
    }

    @Test
    void findById() {
        Optional<Classroom> foundClassroom = classroomRepository.findById("C101");

        assertTrue(foundClassroom.isPresent(), "Classroom should be found");
        assertEquals(classroom1, foundClassroom.get(), "Found classroom should match the expected classroom");
    }

    @Test
    void findAll() {
        ArrayList<Classroom> allClassrooms = classroomRepository.findAll();

        assertNotNull(allClassrooms, "The returned list should not be null");
        assertEquals(2, allClassrooms.size(), "The list should contain 2 classrooms");
        assertTrue(allClassrooms.contains(classroom1), "The list should contain classroom1");
        assertTrue(allClassrooms.contains(classroom2), "The list should contain classroom2");
    }

    @Test
    void update() {
        ArrayList<Teacher> updatedTeachers = new ArrayList<>();
        updatedTeachers.add(new Teacher("T004", "Jack", "Simpson", "Advanced Mathematics"));

        ArrayList<Student> updatedStudents = new ArrayList<>();
        updatedStudents.add(new Student("S005", "Anthony", "Hopkins", LocalDate.of(2024, 3, 20)));

        updatedClassroom = new Classroom.ClassroomBuilder()
                .setClassroomID("C103")
                .setClassName("Advanced Mathematics")  // Updated class name
                .setTeacher(new ArrayList<>(updatedTeachers))
                .setStudent(new ArrayList<>(updatedStudents))
                .build();

        Classroom returnedClassroom = classroomRepository.update(updatedClassroom);

        // Assert: Check if the updated classroom is returned correctly
        assertNotNull(returnedClassroom, "The updated classroom should not be null");
        assertEquals(updatedClassroom, returnedClassroom, "The returned classroom should match the updated classroom");

        // Verify that the classroom has been updated
        Optional<Classroom> foundClassroom = classroomRepository.findById("C101");
        assertTrue(foundClassroom.isPresent(), "The updated classroom should be found in the repository");
        assertEquals("Advanced Mathematics", foundClassroom.get().getClassName(), "The class name should be updated");
        assertEquals(updatedClassroom.getTeacher(), foundClassroom.get().getTeacher(), "The teacher list should be updated");
        assertEquals(updatedClassroom.getStudent(), foundClassroom.get().getStudent(), "The student list should be updated");

    }

    @Test
    void delete() {
        classroomRepository.delete("C101");

        Optional<Classroom> foundClassroom = classroomRepository.findById("C101");
        assertFalse(foundClassroom.isPresent(), "Classroom with ID C101 should be deleted");
    }

    @Test
    void findByClassName() {
        ArrayList<Classroom> mathClassrooms = classroomRepository.findByClassName("Mathematics");

        assertNotNull(mathClassrooms, "The returned list should not be null");
        assertEquals(2, mathClassrooms.size(), "There should be 2 classrooms with the name 'Mathematics'");

        assertTrue(mathClassrooms.contains(classroom1), "The list should contain classroom1");
        assertTrue(mathClassrooms.contains(classroom3), "The list should contain classroom3");

        ArrayList<Classroom> nonExistentClassrooms = classroomRepository.findByClassName("History");

        assertNotNull(nonExistentClassrooms, "The returned list should not be null");
        assertTrue(nonExistentClassrooms.isEmpty(), "The list should be empty for a non-existent class name");

    }
}