package za.ac.cput.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Teacher;
import za.ac.cput.factory.TeacherFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class TeacherServiceTest {

    @Autowired
    private TeacherService teacherService;
    private Teacher teacher = TeacherFactory.createTeacher("12345", "Aidan", "Coetzee", "Maths");

    @Test
    void create() {
        Teacher createdTeacher = teacherService.create(teacher);
        assertNotNull(createdTeacher);
        System.out.println(createdTeacher);
    }

    @Test
    void read() {
        Teacher read = teacherService.read(teacher.getTeacherID());
        assertNotNull(read);
        System.out.println(read);
    }

    @Test
    void update() {
        Teacher newTeacher = new Teacher.Builder().copy(teacher).setTeacherID(teacher.getTeacherID()).build();
        Teacher updatedTeacher = teacherService.update(teacher);
        assertNotNull(updatedTeacher);
        System.out.println(updatedTeacher);
    }

    @Test
    void delete() {
        Teacher createTeacher = teacherService.create(teacher);
        boolean deleted = teacherService.delete(teacher.getTeacherID());
        assertTrue(deleted);
    }

    @Test
    void getAll() {
        teacherService.create(teacher);
        List<Teacher> teachers = teacherService.getAll();
        assertNotNull(teachers);
        System.out.println(teachers);
    }

    @Test
    void findById() {
        Teacher createTeacher = teacherService.create(teacher);
        Teacher foundTeacher = teacherService.findById(createTeacher.getTeacherID());
        assertNotNull(foundTeacher);
    }
}