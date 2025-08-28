package za.ac.cput.factory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import za.ac.cput.domain.AttendanceRecord;
import za.ac.cput.domain.AttendanceReport;
import za.ac.cput.domain.Classroom;
import za.ac.cput.domain.Student;
import za.ac.cput.domain.Teacher;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AttendanceReportFactoryTest {

    private Classroom classroom;
    private Student student;
    private Teacher teacher;

    @BeforeEach
    void setUp() {
        classroom = new Classroom.Builder()
                .setClassroomId("C001")
                .setClassName("101")
                .build();

        student = new Student.Builder()
                .setStudentID("S001")
                .setFirstName("John")
                .setLastName("Doe")
                .build();

        teacher = new Teacher.Builder()
                .setTeacherID("T001")
                .setFirstName("Jane")
                .setLastName("Smith")
                .setSubject("Mathematics")
                .build();
    }

    @Test
    void createAttendanceReport() {
        List<AttendanceRecord> records = new ArrayList<>();
        records.add(AttendanceRecordFactory.createAttendanceRecord(
                "R001", LocalDate.now(), "Present", classroom, student, teacher));
        records.add(AttendanceRecordFactory.createAttendanceRecord(
                "R002", LocalDate.now(), "Absent", classroom, student, teacher));

        AttendanceReport report = AttendanceReportFactory.createAttendanceReport(
                "AR001",
                records,
                LocalDate.now().minusDays(1),
                LocalDate.now().plusDays(1)
        );

        assertNotNull(report);
        assertEquals("AR001", report.getReportID());
        assertEquals(1, report.getTotalDaysAbsent());
        assertEquals(1, report.getTotalDaysPresent());
        assertEquals(2, report.getRecords().size());
    }

    @Test
    void createAttendanceReportInvalidDates() {
        List<AttendanceRecord> records = new ArrayList<>();
        records.add(AttendanceRecordFactory.createAttendanceRecord(
                "R003", LocalDate.now(), "Present", classroom, student, teacher));

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            AttendanceReportFactory.createAttendanceReport(
                    "AR002",
                    records,
                    LocalDate.now().plusDays(1),
                    LocalDate.now()
            );
        });

        assertEquals("Start date cannot be after end date.", exception.getMessage());
    }
}
