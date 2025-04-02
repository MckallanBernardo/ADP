package org.assignment1.repository;


import org.assignment1.domain.AttendanceRecord;
import java.util.*;



/* AttendanceRecordRepository.java
AttendanceRecordRepository class
Author: Ross Barth (220612986)
Date: 27 March 2025
*/

public class AttendanceRecordRepository implements IAttendanceRecordRepository {

    private static IAttendanceRecordRepository repository = null;

    private List<AttendanceRecord> attendanceRecordList;

    AttendanceRecordRepository() {
        attendanceRecordList = new ArrayList<AttendanceRecord>();
    }

    public static IAttendanceRecordRepository getInstance() {
        if (repository == null) {
            repository = new AttendanceRecordRepository();
        }
        return repository;
    }

    @Override
    public List<AttendanceRecord> getAll() {
        return attendanceRecordList;
    }

    @Override
    public AttendanceRecord create(AttendanceRecord entity) {
        boolean success = attendanceRecordList.add(entity);
        if (success) {
            return entity;
        }
        return null;
    }

    @Override
    public Optional<AttendanceRecord> findById(String s) {
        for (AttendanceRecord r : attendanceRecordList) {
            if (r.getRecordID().equals(s))
                return Optional.of(r);
        }
        return Optional.empty();
    }

    @Override
    public ArrayList<AttendanceRecord> findAll() {
        return null;
    }

    @Override
    public AttendanceRecord update(AttendanceRecord entity) {
        return null;
    }

    @Override
    public void delete(String s) {
        Optional<AttendanceRecord> attendanceRecordToDelete = findById(s);
        //if (attendanceRecordToDelete == null);
    }
}
