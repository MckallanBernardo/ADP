package org.assignment1.repository;

import org.assignment1.domain.AttendanceReport;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

/* AttendanceReportRepository.java
AttendanceReportRepository class
Author: Keagan Christians (230702732)
Date: 28 March 2025
*/

public class AttendanceReportRepository implements IAttendanceReportRepository {
    private final ArrayList<AttendanceReport> reports = new ArrayList<>();

    @Override
    public AttendanceReport create(AttendanceReport report) {
        reports.add(report);
        return report;
    }

    @Override
    public Optional<AttendanceReport> findById(String id) {
        return reports.stream().filter(r -> r.getReportID().equals(id)).findFirst();
    }

    @Override
    public ArrayList<AttendanceReport> findAll() {
        return new ArrayList<>(reports);
    }

    @Override
    public AttendanceReport update(AttendanceReport updatedReport) {
        delete(updatedReport.getReportID());
        reports.add(updatedReport);
        return updatedReport;
    }

    @Override
    public void delete(String id) {
        reports.removeIf(r -> r.getReportID().equals(id));
    }

    @Override
    public ArrayList<AttendanceReport> findByDateRange(LocalDate startDate, LocalDate endDate) {
        ArrayList<AttendanceReport> result = new ArrayList<>();
        for (AttendanceReport report : reports) {
            if ((report.getStartDate().isEqual(startDate) || report.getStartDate().isAfter(startDate)) &&
                    (report.getEndDate().isEqual(endDate) || report.getEndDate().isBefore(endDate))) {
                result.add(report);
            }
        }
        return result;
    }
}
