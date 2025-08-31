package za.ac.cput.client;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import za.ac.cput.domain.AttendanceRecord;

import java.util.Arrays;
import java.util.List;

public class AttendanceRecordClient {
    private static final String BASE_URL = "http://localhost:8080/attendancerecord"; // ✅ matches controller
    private final RestTemplate restTemplate;

    public AttendanceRecordClient() {
        this.restTemplate = new RestTemplate();
    }

    // Create AttendanceRecord
    public AttendanceRecord create(AttendanceRecord record) {
        String url = BASE_URL + "/create";
        return restTemplate.postForObject(url, record, AttendanceRecord.class);
    }

    // Read AttendanceRecord by ID
    public AttendanceRecord read(String id) {
        String url = BASE_URL + "/read/" + id;
        return restTemplate.getForObject(url, AttendanceRecord.class);
    }

    // ✅ Fixed: Return a List instead of array
    public List<AttendanceRecord> getAll() {
        String url = BASE_URL + "/getAll";
        ResponseEntity<AttendanceRecord[]> response =
                restTemplate.getForEntity(url, AttendanceRecord[].class);
        return Arrays.asList(response.getBody());
    }
}
