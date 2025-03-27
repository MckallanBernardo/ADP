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
        // Arrange - Creating sample teachers and students lists
        ArrayList<Teacher> teachers = new ArrayList<>();
        teachers.add(new Teacher("T001", "John Doe"));

        ArrayList<Student> students = new ArrayList<>();
        students.add(new Student("S001", "Jane Smith"));

        // Act - Create a Classroom using the Factory
        Classroom classroom = ClassroomFactory.createClassroom("101", "Math", teachers, students);

        // Assert - Validate that the classroom was created correctly
        assertNotNull(classroom);
        assertEquals("101", classroom.getClassroomID());
        assertEquals("Math", classroom.getClassName());
        assertEquals(1, classroom.getTeacher().size());
        assertEquals(1, classroom.getStudent().size());
        assertEquals("John Doe", classroom.getTeacher().get(0).getName());
        assertEquals("Jane Smith", classroom.getStudent().get(0).getName());
    }
    }
}