package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.ac.cput.domain.AttendanceRecord;

public interface IAttendanceRecordRepository extends JpaRepository<AttendanceRecord,String> {
}
