package org.assignment1.factory;

import org.assignment1.factory.AttendanceReportFactory;
import org.assignment1.domain.AttendanceReport;
import org.assignment1.domain.AttendanceRecord;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

/* AttendanceReportFactoryTest.java
AttendanceReportFactoryTest test class
Author: Keagan Christians (230702732)
Date: 28 March 2025
*/

public class AttendanceReportFactoryTest {

    @Test
    public void testAttendanceReportFactory() {
        ArrayList<AttendanceRecord> records = new ArrayList<>();
        records.add(new AttendanceRecord.AttendanceRecordBuilder()
                .setRecordID("R001")
                .setDate(LocalDate.now())
                .setStatus("PRESENT")
                .build());

        AttendanceReport report = AttendanceReportFactory.createAttendanceReport("Report001", LocalDate.now().minusDays(7), LocalDate.now(), records);

        assertNotNull(report);
        assertEquals("Report001", report.getReportID());
        assertEquals(1, report.getRecords().size());
    }
}
