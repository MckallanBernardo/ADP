package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.AttendanceRecord;

@Repository
public interface IAttendanceRecordRepository extends JpaRepository<AttendanceRecord,String> {
    java.util.List<AttendanceRecord> findByStudent_StudentIDAndDateBetween(String studentID, java.time.LocalDate startDate, java.time.LocalDate endDate);
    java.util.List<AttendanceRecord> findByStudent_StudentID(String studentID);

}
