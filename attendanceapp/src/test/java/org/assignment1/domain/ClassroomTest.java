package org.assignment1.domain;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
class ClassroomTest {

    private static Classroom classroom;
    private static ArrayList<Teacher> teachers;
    private static ArrayList<Student> students;

    @BeforeAll
    public static void setUp() {
        classroom = new Classroom.ClassroomBuilder()
                .setClassroomID("C101")
                .setClassName("Mathematics")
                .setTeacher(new ArrayList<>(teachers))
                .setStudent(new ArrayList<>(students))
                .build();
    }
    @Test
    void getClassroomID() {
        // Act: Retrieve the classroom ID
        String classroomID = classroom.getClassroomID();

        assertEquals("C101", classroomID, "The classroom ID should be C101");

    }

    @Test
    void getClassName() {
        assertEquals("Mathematics", classroom.getClassName(), "The class name should be Mathematics");
    }

    @Test
    void getTeacher() {
        teachers.add(new Teacher("T001", "John", "Smith", "Physical Science"));
        assertEquals(teachers, classroom.getTeacher(), "The teacher list should match");
    }

    @Test
    void getStudent() {
        students.add(new Student("S001", "Lizzy", "Mcguire", LocalDate.of(2024, 3, 19)));
        assertEquals(students, classroom.getStudent(), "The student list should match");
    }

    @Test
    void testToString() {
        String expected = "Classroom{" +
                "classroomID='C101', " +
                "className='Mathematics', " +
                "teacherName=[Teacher{teacherID='T001', name='John', surname='Dory', subject='Mathematics'}], " +
                "studentList=[Student{studentID='S001', name='Alice', 'White', date='2024-03-19'}]" +
                '}';

        assertEquals(expected, classroom.toString(), "The toString method should return the expected string");
    }
}