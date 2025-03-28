package org.assignment1.repository;

import org.assignment1.domain.AttendanceRecord;
import org.assignment1.domain.Classroom;
import org.assignment1.domain.Student;
import org.assignment1.domain.Teacher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class AttendanceRecordRepositoryTest {
    private AttendanceRecordRepository attendanceRecordRepository;
    private AttendanceRecord attendanceRecord1, attendanceRecord2, attendanceRecord3;

    @BeforeEach
    void setUp() {
        attendanceRecordRepository = new AttendanceRecordRepository();

        ArrayList<Student> students = new ArrayList<>();
        ArrayList<Teacher> teachers = new ArrayList<>();

        ArrayList<Classroom> classrooms = new ArrayList<>();
        classrooms.add(new Classroom("Room 101"));

        // Create Attendance Records
        attendanceRecord1 = new AttendanceRecord("001", LocalDate.of(2025, 3, 25), "PRESENT", students, teachers, classrooms);
        attendanceRecord2 = new AttendanceRecord("002", LocalDate.of(2025, 3, 26), "ABSENT", students, teachers, classrooms);
        attendanceRecord3 = new AttendanceRecord("003", LocalDate.of(2025, 3, 27), "LATE", students, teachers, classrooms);

        // Add test records to the repository
        attendanceRecordRepository.create(attendanceRecord1);
        attendanceRecordRepository.create(attendanceRecord2);
        attendanceRecordRepository.create(attendanceRecord3);
    }

    @Test
    void create() {
        // Arrange
        AttendanceRecord newRecord = new AttendanceRecord("004", LocalDate.of(2025, 3, 28), "PRESENT", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

        // Act
        AttendanceRecord createdRecord = attendanceRecordRepository.create(newRecord);

        // Assert
        assertNotNull(createdRecord);
        assertEquals("004", createdRecord.getId());
    }

    @Test
    void findById() {
        // Act
        Optional<AttendanceRecord> foundRecord = attendanceRecordRepository.findById("001");

        // Assert
        assertTrue(foundRecord.isPresent());
        assertEquals("001", foundRecord.get().getId());
    }

    @Test
    void findAll() {
        // Act
        List<AttendanceRecord> records = attendanceRecordRepository.findAll();

        // Assert
        assertNotNull(records);
        assertEquals(3, records.size()); // 3 records were added in setUp()
    }

    @Test
    void update() {
        // Arrange
        AttendanceRecord updatedRecord = new AttendanceRecord("002", LocalDate.of(2025, 3, 26), "PRESENT", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

        // Act
        AttendanceRecord result = attendanceRecordRepository.update(updatedRecord);

        // Assert
        assertNotNull(result);
        assertEquals("PRESENT", result.getStatus()); // Ensure status changed
    }

    @Test
    void delete() {
        // Act
        boolean deleted = attendanceRecordRepository.delete("003");

        // Assert
        assertTrue(deleted);
        assertFalse(attendanceRecordRepository.findById("003").isPresent());
    }

    @Test
    void getAll() {
        // Act
        List<AttendanceRecord> records = attendanceRecordRepository.getAll();

        // Assert
        assertNotNull(records);
        assertEquals(3, records.size()); // Ensuring it returns all records
    }
}
