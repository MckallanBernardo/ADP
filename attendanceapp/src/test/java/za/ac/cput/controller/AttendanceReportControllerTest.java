package za.ac.cput.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import za.ac.cput.domain.AttendanceRecord;
import za.ac.cput.domain.AttendanceReport;
import za.ac.cput.domain.Classroom;
import za.ac.cput.factory.AttendanceRecordFactory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AttendanceReportControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private String baseUrl;
    private Classroom classroom;
    private AttendanceReport report;

    @BeforeEach
    void setUp() {
        baseUrl = "http://localhost:" + port + "/api/attendance-reports";

        classroom = new Classroom.Builder()
                .setClassroomId("C001")
                .setClassName("101")
                .build();

        List<AttendanceRecord> records = new ArrayList<>();
        records.add(AttendanceRecordFactory.createAttendanceRecord("R001", LocalDate.now(), "Present", classroom));
        records.add(AttendanceRecordFactory.createAttendanceRecord("R002", LocalDate.now(), "Absent", classroom));

        report = new AttendanceReport.Builder()
                .setReportID("AR001")
                .setRecords(records)
                .setStartDate(LocalDate.now().minusDays(1))
                .setEndDate(LocalDate.now())
                .build();
    }

    @Test
    void create() {
        AttendanceReport created = restTemplate.postForObject(baseUrl + "/create", report, AttendanceReport.class);
        assertNotNull(created);
        assertEquals(report.getReportID(), created.getReportID());
    }

    @Test
    void read() {
        restTemplate.postForObject(baseUrl + "/create", report, AttendanceReport.class);
        AttendanceReport read = restTemplate.getForObject(baseUrl + "/read/" + report.getReportID(), AttendanceReport.class);
        assertNotNull(read);
        assertEquals(report.getReportID(), read.getReportID());
    }

    @Test
    void update() {
        restTemplate.postForObject(baseUrl + "/create", report, AttendanceReport.class);
        AttendanceReport updated = new AttendanceReport.Builder()
                .setReportID(report.getReportID())
                .setRecords(report.getRecords())
                .setStartDate(report.getStartDate())
                .setEndDate(report.getEndDate().plusDays(1))
                .build();

        restTemplate.put(baseUrl + "/update", updated);
        AttendanceReport retrieved = restTemplate.getForObject(baseUrl + "/read/" + report.getReportID(), AttendanceReport.class);
        assertNotNull(retrieved);
        assertEquals(updated.getEndDate(), retrieved.getEndDate());
    }

    @Test
    void delete() {
        restTemplate.postForObject(baseUrl + "/create", report, AttendanceReport.class);
        restTemplate.delete(baseUrl + "/delete/" + report.getReportID());
        AttendanceReport deleted = restTemplate.getForObject(baseUrl + "/read/" + report.getReportID(), AttendanceReport.class);
        assertNull(deleted);
    }

    @Test
    void getAll() {
        restTemplate.postForObject(baseUrl + "/create", report, AttendanceReport.class);
        List<?> reports = restTemplate.getForObject(baseUrl + "/all", List.class);
        assertNotNull(reports);
        assertFalse(reports.isEmpty());
    }
}
