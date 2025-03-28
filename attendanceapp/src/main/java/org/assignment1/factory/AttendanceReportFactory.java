package org.assignment1.factory;

import org.assignment1.domain.AttendanceRecord;
import org.assignment1.domain.AttendanceReport;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/* AttendanceReportFactory.java
AttendanceReportFactory class
Author: Keagan Christians (230702732)
Date: 28 March 2025
*/

public class AttendanceReportFactory {
    public static AttendanceReport createAttendanceReport(
            String reportID, LocalDate startDate, LocalDate endDate, ArrayList<AttendanceRecord> records) {

        return new AttendanceReport.Builder()
                .setReportID(reportID)
                .setRecords(records)
                .setStartDate(startDate)
                .setEndDate(endDate)
                .build();
    }
}
