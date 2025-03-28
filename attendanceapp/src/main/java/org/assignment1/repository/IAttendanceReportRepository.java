package org.assignment1.repository;

import org.assignment1.domain.AttendanceReport;

import java.time.LocalDate;
import java.util.ArrayList;

/* IAttendanceReportRepository.java
IAttendanceReportRepository Interface
Author: Keagan Christians (230702732)
Date: 28 March 2025
*/

public interface IAttendanceReportRepository extends IRepository<AttendanceReport, String> {
    ArrayList<AttendanceReport> findByDateRange(LocalDate startDate, LocalDate endDate);
}
