package org.assignment1.domain;

import org.assignment1.domain.AttendanceReport;
import org.assignment1.domain.AttendanceRecord;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

/* AttendanceReportTest.java
AttendanceReportTest test class
Author: Keagan Christians (230702732)
Date: 28 March 2025
*/

public class AttendanceReportTest {

    @Test
    public void testAttendanceReportCreation() {
        ArrayList<AttendanceRecord> records = new ArrayList<>();
        records.add(new AttendanceRecord.AttendanceRecordBuilder()
                .setRecordID("R001")
                .setDate(LocalDate.now())
                .setStatus("PRESENT")
                .build());

        AttendanceReport report = new AttendanceReport.Builder()
                .setReportID("Report001")
                .setRecords(records)
                .setStartDate(LocalDate.now().minusDays(7))
                .setEndDate(LocalDate.now())
                .build();

        assertNotNull(report);
        assertEquals("Report001", report.getReportID());
        assertEquals(1, report.getRecords().size());
        assertEquals(1, report.getTotalDaysPresent());
        assertEquals(0, report.getTotalDaysAbsent());
    }
}
