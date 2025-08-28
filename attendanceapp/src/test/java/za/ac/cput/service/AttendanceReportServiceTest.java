package za.ac.cput.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.*;
import za.ac.cput.factory.AttendanceRecordFactory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AttendanceReportServiceTest {

    @Autowired
    private AttendanceReportService service;

    private Classroom classroom;
    private Student student;
    private Teacher teacher;
    private AttendanceReport report;

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
                .setSubject("Math")
                .build();

        List<AttendanceRecord> records = new ArrayList<>();
        records.add(AttendanceRecordFactory.createAttendanceRecord(
                "R001", LocalDate.now(), "Present",
                classroom, student, teacher));

        records.add(AttendanceRecordFactory.createAttendanceRecord(
                "R002", LocalDate.now(), "Absent",
                classroom, student, teacher));

        report = new AttendanceReport.Builder()
                .setReportID("AR001")
                .setRecords(records)
                .setStartDate(LocalDate.now().minusDays(1))
                .setEndDate(LocalDate.now())
                .build();
    }

    @Test
    void create() {
        AttendanceReport created = service.create(report);
        assertNotNull(created);
    }

    @Test
    void read() {
        service.create(report);
        AttendanceReport read = service.read(report.getReportID());
        assertNotNull(read);
        assertEquals(report.getReportID(), read.getReportID());
    }

    @Test
    void update() {
        service.create(report);
        AttendanceReport updatedReport = new AttendanceReport.Builder()
                .setReportID(report.getReportID())
                .setRecords(report.getRecords())
                .setStartDate(report.getStartDate())
                .setEndDate(report.getEndDate().plusDays(1))
                .build();

        AttendanceReport updated = service.update(updatedReport);
        assertNotNull(updated);
        assertEquals(updatedReport.getEndDate(), updated.getEndDate());
    }

    @Test
    void delete() {
        service.create(report);
        boolean deleted = service.delete(report.getReportID());
        assertTrue(deleted);
    }

    @Test
    void getAll() {
        service.create(report);
        List<AttendanceReport> reports = service.getAll();
        assertNotNull(reports);
        assertFalse(reports.isEmpty());
    }

    @Test
    void findById() {
        service.create(report);
        AttendanceReport found = service.findById(report.getReportID());
        assertNotNull(found);
    }
}

