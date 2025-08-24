package za.ac.cput.service;

import za.ac.cput.domain.AttendanceRecord;

import java.util.List;

public interface IAttendanceRecordService extends IService <AttendanceRecord, String>{
    List<AttendanceRecord> findByAttendanceRecord();
}
