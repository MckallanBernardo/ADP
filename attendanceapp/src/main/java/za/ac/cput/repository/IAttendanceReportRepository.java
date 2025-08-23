package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.ac.cput.domain.AttendanceReport;

public interface IAttendanceReportRepository extends JpaRepository<AttendanceReport, String> {
}
