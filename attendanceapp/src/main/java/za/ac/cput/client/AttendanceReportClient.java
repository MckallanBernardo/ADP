package za.ac.cput.client;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import za.ac.cput.domain.AttendanceReport;

import java.util.Arrays;
import java.util.List;

public class AttendanceReportClient {
    // ✅ Fixed to match controller path
    private static final String BASE_URL = "http://localhost:8080/api/attendance-reports";
    private final RestTemplate restTemplate;

    public AttendanceReportClient() {
        this.restTemplate = new RestTemplate();
    }

    // Create
    public AttendanceReport create(AttendanceReport report) {
        String url = BASE_URL + "/create";
        return restTemplate.postForObject(url, report, AttendanceReport.class);
    }

    // Read
    public AttendanceReport read(String id) {
        String url = BASE_URL + "/read/" + id;
        return restTemplate.getForObject(url, AttendanceReport.class);
    }

    // ✅ Fixed: Return a List instead of array
    public List<AttendanceReport> getAll() {
        String url = BASE_URL + "/getAll";
        ResponseEntity<AttendanceReport[]> response =
                restTemplate.getForEntity(url, AttendanceReport[].class);
        return Arrays.asList(response.getBody());
    }
}
