package za.ac.cput.factory;

import za.ac.cput.domain.AttendanceRecord;
import za.ac.cput.domain.AttendanceReport;
import za.ac.cput.util.Helper;

import java.time.LocalDate;
import java.util.List;

public class AttendanceReportFactory {

    public static AttendanceReport createAttendanceReport(String reportID, List<AttendanceRecord> records,
                                                          LocalDate startDate, LocalDate endDate) {
        if (Helper.isNullOrEmpty(reportID))
            throw new IllegalArgumentException("Report ID cannot be null or empty.");

        if (records == null || records.isEmpty())
            throw new IllegalArgumentException("Attendance records list cannot be null or empty.");

        if (startDate == null || endDate == null)
            throw new IllegalArgumentException("Start date and end date cannot be null.");

        if (startDate.isAfter(endDate))
            throw new IllegalArgumentException("Start date cannot be after end date.");

        return new AttendanceReport.Builder()
                .setReportID(reportID)
                .setRecords(new java.util.ArrayList<>(records))
                .setStartDate(startDate)
                .setEndDate(endDate)
                .build();
    }
}
