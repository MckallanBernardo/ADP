package org.assignment1.factory;

import org.assignment1.domain.Classroom;
import org.assignment1.domain.Student;
import org.assignment1.domain.Teacher;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ClassroomFactoryTest {

    @Test
    void createClassroomTest(){
        ArrayList<Teacher> teachers = new ArrayList<>();
        teachers.add(new Teacher("T001", "John", "Smith", "Physical Science"));

        ArrayList<Student> students = new ArrayList<>();
        students.add(new Student("S001", "Lizzy", "Mcguire", (2024, 3, 19));

        Classroom classroom = ClassroomFactory.createClassroom("C101", "T1.1", teachers, students);

        assertNotNull(classroom, "Classroom should not be null");
        assertEquals("C101", classroom.getClassroomID(), "Classroom ID should be 'C101'");
        assertEquals("T1.1", classroom.getClassName(), "Classroom name should be 'T1.1'");
        assertEquals(1, classroom.getTeacher().size(), "There should be 1 teacher in the classroom");
        assertEquals(1, classroom.getStudent().size(), "There should be 1 student in the classroom");
    }

}