package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.ac.cput.domain.Teacher;

public interface ITeacherRepository extends JpaRepository<Teacher, String> {
}
