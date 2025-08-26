package za.ac.cput.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.AttendanceRecord;
import za.ac.cput.domain.Classroom;
import za.ac.cput.factory.AttendanceRecordFactory;
import za.ac.cput.factory.ClassroomFactory;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class AttendanceRecordServiceTest {
    @Autowired
    private IAttendanceRecordService service;
    private LocalDate dateVar = LocalDate.now();

    private Classroom classroom = new Classroom.Builder()
            .setClassroomId("CLA34356")
            .setClassName("Maths")
            .build();

    private AttendanceRecord attendanceRecord = AttendanceRecordFactory.createAttendanceRecord("2300123", dateVar, "Present", classroom);

    @Test
    void create(){
        AttendanceRecord saved = service.create(attendanceRecord);
        assertNotNull(saved);
        System.out.println("Created: " + saved);
    }

    @Test
    void read() {
        AttendanceRecord read = service.read(attendanceRecord.getRecordID());
        assertNotNull(read);
        System.out.println("Read: " + read);
    }

    @Test
    void update() {
        AttendanceRecord updated = new AttendanceRecord.Builder().copy(attendanceRecord)
                .setStatus("Absent")
                .build();
        assertNotNull(service.update(updated));
        System.out.println("Updated: " + updated);
    }

    @Test
    void getAll(){
        System.out.println("Get All: " + service.getAll());
    }
}
