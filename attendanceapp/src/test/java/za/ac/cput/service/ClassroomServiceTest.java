package za.ac.cput.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Classroom;
import za.ac.cput.domain.Student;
import za.ac.cput.domain.Teacher;
import za.ac.cput.factory.ClassroomFactory;
import za.ac.cput.factory.StudentFactory;
import za.ac.cput.factory.TeacherFactory;
import za.ac.cput.repository.IClassroomRepository;
import za.ac.cput.repository.IStudentRepository;
import za.ac.cput.repository.ITeacherRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ClassroomServiceTest {

    @Autowired
    private ClassroomService service;

    @Autowired
    private IClassroomRepository classroomRepository;
    @Autowired
    private ITeacherRepository teacherRepository;
    @Autowired
    private IStudentRepository studentRepository;

    private Teacher teacher;
    private Student student1;
    private List<Student> students;
    private Classroom classroom;

    @BeforeEach
    void setUp() {
        classroomRepository.deleteAll();
        teacherRepository.deleteAll();
        studentRepository.deleteAll();

        teacher = TeacherFactory.createTeacher("T001", "John", "Doe", "Mathematics");
        teacherRepository.save(teacher);

        student1 = StudentFactory.createStudent("S001", "Jane", "Smith");
        studentRepository.save(student1);

        students = new ArrayList<>();
        students.add(student1);

        classroom = new Classroom.Builder()
                .setClassroomId("C1")
                .setClassName("101")
                .setTeacher(teacher)
                .setStudent(students)
                .build();
    }
    @Test
    void create() {
        Classroom created = service.create(classroom);
        assertNotNull(created);
    }

    @Test
    void read() {
        Classroom created = service.create(classroom);
        Classroom read = service.read(created.getClassroomId());
        assertNotNull(read);
    }

    @Test
    void update() {
        Classroom created = service.create(classroom);
        Classroom updated = service.update
                (new Classroom.Builder().copy(created).setClassName("102").build());
        assertNotNull(updated);
        assertEquals("102", updated.getClassName());
    }

    @Test
    void delete() {
        Classroom created = service.create(classroom);
        boolean deleted = service.delete(created.getClassroomId());
        assertTrue(deleted);
    }

    @Test
    void getAll() {
        service.create(classroom);
        List<Classroom> classrooms = service.getAll();
        assertNotNull(classrooms);
        assertFalse(classrooms.isEmpty());
    }

    @Test
    void findById() {
        Classroom created = service.create(classroom);
        Classroom found = service.findById(created.getClassroomId());
        assertNotNull(found);
    }
}