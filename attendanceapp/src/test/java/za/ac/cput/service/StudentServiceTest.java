package za.ac.cput.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import za.ac.cput.domain.Student;
import za.ac.cput.repository.IStudentRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock
    private IStudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    private Student student;

    @BeforeEach
    void setUp() {
        student = new Student.Builder()
                .setStudentID("S1")
                .setFirstName("Alice")
                .setLastName("Smith")
                .build();
    }

    @Test
    void createStudent() {
        when(studentRepository.save(any(Student.class))).thenReturn(student);
        Student created = studentService.create(student);
        assertThat(created).isNotNull();
        assertThat(created.getStudentID()).isEqualTo("S1");
    }

    @Test
    void readStudent() {
        when(studentRepository.findById("S1")).thenReturn(Optional.of(student));
        Student found = studentService.read("S1");
        assertThat(found).isNotNull();
        assertThat(found.getFirstName()).isEqualTo("Alice");
    }

    @Test
    void updateStudent() {
        when(studentRepository.save(any(Student.class))).thenReturn(student);
        Student updated = studentService.update(student);
        assertThat(updated).isNotNull();
        assertThat(updated.getStudentID()).isEqualTo("S1");
    }

    @Test
    void deleteStudent() {
        doNothing().when(studentRepository).deleteById("S1");
        boolean deleted = studentService.delete("S1");
        assertThat(deleted).isTrue();
        verify(studentRepository, times(1)).deleteById("S1");
    }

    @Test
    void getAllStudents() {
        List<Student> students = Arrays.asList(student);
        when(studentRepository.findAll()).thenReturn(students);
        List<Student> result = studentService.getAll();
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getStudentID()).isEqualTo("S1");
    }
}