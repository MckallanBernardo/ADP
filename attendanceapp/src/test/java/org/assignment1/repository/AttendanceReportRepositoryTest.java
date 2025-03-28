package org.assignment1.repository;

import org.assignment1.domain.AttendanceRecord;
import org.assignment1.domain.AttendanceReport;
import org.assignment1.factory.AttendanceReportFactory;
import org.assignment1.repository.AttendanceReportRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/* AttendanceReportRepositoryTest.java
AttendanceReportRepositoryTest test class
Author: Keagan Christians (230702732)
Date: 28 March 2025
*/

public class AttendanceReportRepositoryTest {

    private AttendanceReportRepository repository;
    private AttendanceReport report;
    private ArrayList<AttendanceRecord> records;

    @BeforeEach
    public void setUp() {
        repository = new AttendanceReportRepository();

        // Create a few attendance records
        AttendanceRecord record1 = new AttendanceRecord.AttendanceRecordBuilder()
                .setRecordID("1")
                .setDate(LocalDate.of(2025, 3, 25))
                .setStatus("PRESENT")
                .setStudent(new ArrayList<>())
                .setTeacher(new ArrayList<>())
                .setClassroom(new ArrayList<>())
                .build();

        AttendanceRecord record2 = new AttendanceRecord.AttendanceRecordBuilder()
                .setRecordID("2")
                .setDate(LocalDate.of(2025, 3, 26))
                .setStatus("ABSENT")
                .setStudent(new ArrayList<>())
                .setTeacher(new ArrayList<>())
                .setClassroom(new ArrayList<>())
                .build();

        // Create an AttendanceReport using the factory
        records = new ArrayList<>();
        records.add(record1);
        records.add(record2);

        report = AttendanceReportFactory.createAttendanceReport(
                "Report1",
                LocalDate.of(2025, 3, 25),
                LocalDate.of(2025, 3, 26),
                records
        );
    }

    @Test
    public void testCreateAttendanceReport() {
        repository.create(report);
        AttendanceReport foundReport = repository.findById("Report1").orElse(null);
        assertNotNull(foundReport, "Report should be created and found.");
        assertEquals("Report1", foundReport.getReportID(), "Report ID should match.");
    }

    @Test
    public void testFindAttendanceReportById() {
        repository.create(report);
        AttendanceReport foundReport = repository.findById("Report1").orElse(null);
        assertNotNull(foundReport, "Report should be found.");
        assertEquals("Report1", foundReport.getReportID(), "Report ID should match.");
    }

    @Test
    public void testUpdateAttendanceReport() {
        repository.create(report);

        // Modify the report's end date and update
        report = new AttendanceReport.Builder()
                .setReportID("Report1")
                .setRecords(records)
                .setStartDate(LocalDate.of(2025, 3, 25))
                .setEndDate(LocalDate.of(2025, 3, 27)) // Update the end date
                .build();
        repository.update(report);

        AttendanceReport updatedReport = repository.findById("Report1").orElse(null);
        assertNotNull(updatedReport, "Report should be updated and found.");
        assertEquals(LocalDate.of(2025, 3, 27), updatedReport.getEndDate(), "End date should be updated.");
    }

    @Test
    public void testDeleteAttendanceReport() {
        repository.create(report);
        repository.delete("Report1");

        AttendanceReport foundReport = repository.findById("Report1").orElse(null);
        assertNull(foundReport, "Report should be deleted and not found.");
    }

    @Test
    public void testFindAllAttendanceReports() {
        repository.create(report);

        ArrayList<AttendanceReport> allReports = repository.findAll();
        assertFalse(allReports.isEmpty(), "All reports should not be empty.");
        assertEquals(1, allReports.size(), "There should be exactly one report.");
    }
}
