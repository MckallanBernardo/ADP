package za.ac.cput.controller;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import za.ac.cput.domain.AttendanceRecord;
import za.ac.cput.domain.Classroom;
import za.ac.cput.domain.Student;
import za.ac.cput.domain.Teacher;
import za.ac.cput.factory.AttendanceRecordFactory;
import za.ac.cput.factory.ClassroomFactory;
import za.ac.cput.factory.TeacherFactory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AttendanceRecordControllerTest {

    private static AttendanceRecord attendanceRecord;
    private static Classroom classroom;
    private static Teacher teacher;
    private static Student student;
    private static ArrayList<Student> students = new ArrayList<>();
    static LocalDate dateToday = LocalDate.now();

    private TestRestTemplate restTemplate;

    private static final String baseURL = "http://localhost:8080/attendancerecord";

    @BeforeAll
    public static void setUp() {
        students.add(student);
        student = new Student.Builder().copy(student)
                .setStudentID("STU001")
                .setFirstName("Billy")
                .setLastName("Jean")
                .build();

        teacher = new Teacher.Builder().copy(teacher)
                .setTeacherID("JD001")
                .setFirstName("JOHN")
                .setLastName("DOE")
                .setSubject("Pimping")
                .build();


        classroom = new Classroom.Builder().copy(classroom)
                .setClassroomId("CLASS001")
                .setClassName("Pimping 101")
                .setTeacher(teacher)
                .setStudent(students)
                .build();

        attendanceRecord = new AttendanceRecord.Builder().copy(attendanceRecord)
                .setRecordID("REC001")
                .setDate(dateToday)
                .setStatus("Present")
                .setClassroom(classroom)
                .build();
    }

    @Test
    void create() {
        String url = baseURL + "/create/";

        ResponseEntity<AttendanceRecord> postResponse = restTemplate.postForEntity(url, attendanceRecord, AttendanceRecord.class);
        assertNotNull(postResponse);

        // assertEquals(attendanceRecord.getRecordID(), createdAttendanceRecord.getId());
        ///  response comes back with a header and body; below is the body of the response

        AttendanceRecord attendanceRecordSaved = postResponse.getBody();
        assertEquals(attendanceRecord.getRecordID(), attendanceRecordSaved.getRecordID());
        System.out.println("Created: " + attendanceRecordSaved);

    }

    @Test
    void read() {
        String url = baseURL + "/read/" + attendanceRecord.getRecordID();

        ResponseEntity<AttendanceRecord> getResponse = restTemplate.getForEntity(url, AttendanceRecord.class);

        assertEquals(attendanceRecord.getRecordID(), getResponse.getBody().getRecordID());
        System.out.println("Read: "+ getResponse.getBody());

    }

    @Test
    void update() {
        AttendanceRecord updatedRecord = new AttendanceRecord.Builder().copy(attendanceRecord)
                .setStatus("Absent")
                .setDate(dateToday)
                .build();

        String url = baseURL + "/update/";
        this.restTemplate.put(url, updatedRecord);

        // Verify the update by reading the updated record
        ResponseEntity<AttendanceRecord> putResponse = restTemplate.getForEntity(baseURL + "/read/" + updatedRecord.getRecordID(), AttendanceRecord.class);
        assertEquals(putResponse.getStatusCode(), HttpStatus.OK);
        assertNotNull(putResponse.getBody());
        //assertEquals(attendanceRecord.getRecordID(), putResponse.getBody().getRecordID());
        System.out.println("Update: " + putResponse.getBody());
    }

    @Test
    void delete() {
        String url = baseURL + "/delete/" + attendanceRecord.getRecordID();
        restTemplate.delete(url);

        // Verify the delete was successful
        ResponseEntity<AttendanceRecord> deleteResponse = restTemplate.getForEntity(baseURL + "/read/" + attendanceRecord.getRecordID(), AttendanceRecord.class);
        assertEquals(HttpStatus.NOT_FOUND, deleteResponse.getStatusCode());
        System.out.println("Deleted: " + "true");
    }

    @Test
    void getAll() {
        String url = baseURL + "/getall";
        ResponseEntity<AttendanceRecord> response = restTemplate.getForEntity(url, AttendanceRecord.class);
        assertNotNull(response.getBody());
        // assertTrue(response.getBody().length > 0);
        System.out.println("Get All: " + response.getBody());
        //for (AttendanceRecord atr : response.getBody()) {
          //  System.out.println(atr);
        //}
    }
}