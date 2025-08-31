package za.ac.cput.service;

import za.ac.cput.domain.AttendanceRecord;

public interface IAttendanceRecordService extends IService<AttendanceRecord, String> {
    java.util.List<AttendanceRecord> findByStudentAndDateRange(String studentId, java.time.LocalDate start, java.time.LocalDate end);

    //List<AttendanceRecord> findByAttendanceRecord();
}
