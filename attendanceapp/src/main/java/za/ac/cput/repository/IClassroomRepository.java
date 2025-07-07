package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.ac.cput.domain.Classroom;

public interface IClassroomRepository extends JpaRepository<Classroom, String> {
}
