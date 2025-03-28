package org.assignment1.repository;

import org.assignment1.domain.AttendanceRecord;

import java.util.List;

public interface IAttendanceRecordRepository extends IRepository<AttendanceRecord, String> {
    List<AttendanceRecord> getAll();
}
