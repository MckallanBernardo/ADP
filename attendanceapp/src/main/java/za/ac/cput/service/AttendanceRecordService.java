package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.AttendanceRecord;
import za.ac.cput.repository.IAttendanceRecordRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class AttendanceRecordService implements IAttendanceRecordService{

    private static IAttendanceRecordRepository attendRecordRepo;

    //private static IAttendanceRecordService service;

    @Autowired
    public AttendanceRecordService(IAttendanceRecordRepository attendRecordRepo){
        this.attendRecordRepo = attendRecordRepo;
    }


    @Override
    public AttendanceRecord create(AttendanceRecord attendanceRecord) {
        return attendRecordRepo.save(attendanceRecord);
    }

    @Override
    public AttendanceRecord read(String id) {
        return attendRecordRepo.findById(id).orElse(null);
    }

    @Override
    public AttendanceRecord update(AttendanceRecord attendanceRecord) {
        return attendRecordRepo.save(attendanceRecord);
    }

    @Override
    public boolean delete(String id) {
        if (attendRecordRepo.existsById(id));
        return true;
    }

    /// #NOTE: complete findByEntity, same as findById
//    @Override
//    public List<AttendanceRecord> findByAttendanceRecord() {
//        List<AttendanceRecord> attRecordList = new ArrayList<>();
//        attRecordList.add(attendRecordRepo);
//        for (AttendanceRecord E:attRecordList){
//
//        }
//        return attRecordList;
//    }


    @Override
    public List<AttendanceRecord> getAll() {
        return attendRecordRepo.findAll();
    }

    @Override
    public AttendanceRecord findById(String id) {
        return null;
    }

    ///  #NOTE: complete findById, using for-each loop
//    @Override
//    public AttendanceRecord findById(String id) {
//        return null;
//    }
}
