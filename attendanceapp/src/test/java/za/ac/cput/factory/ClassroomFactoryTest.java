package za.ac.cput.factory;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Classroom;
import za.ac.cput.domain.Student;
import za.ac.cput.domain.Teacher;

import java.util.Collections;
import java.util.List;

class ClassroomFactoryTest {

    private ClassroomFactory factory;
    private Teacher teacher;
    private Student student1;
    private List<Student> students;

    @BeforeEach
    void setUp() {
        factory = new ClassroomFactory();

        teacher = TeacherFactory.createTeacher("T001", "John", "Doe", "Mathematics");
        student1 = StudentFactory.createStudent("S001", "Jane", "Smith");
        students = Collections.singletonList(student1);
    }

    @Test
    void createClassroom() {
        Classroom classroom = factory.createClassroom("C001", "101", teacher, students);
        assertNotNull(classroom);
        assertEquals("C001", classroom.getClassroomId());
        assertEquals("101", classroom.getClassName());
        assertEquals(teacher, classroom.getTeacher());
        assertIterableEquals(students, classroom.getStudent());
    }
}
