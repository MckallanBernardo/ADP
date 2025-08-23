package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Teacher;

import static org.junit.jupiter.api.Assertions.*;

class TeacherFactoryTest {

    @Test
    void createTeacher() {
        Teacher teacher = TeacherFactory.createTeacher("1234", "Aidan", "Coetzee", "Gambling");
        assertNotNull(teacher);
        System.out.println(teacher);
    }
}