package za.ac.cput.client;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import za.ac.cput.domain.AttendanceReport;

import java.util.Arrays;
import java.util.List;

public class AttendanceReportClient {
    private static final String BASE_URL = "http://localhost:8080/api/attendance-reports";
    private final RestTemplate restTemplate = new RestTemplate();

    public AttendanceReport create(AttendanceReport report) {
        return restTemplate.postForObject(BASE_URL + "/create", report, AttendanceReport.class);
    }

    public AttendanceReport read(String id) {
        return restTemplate.getForObject(BASE_URL + "/read/" + id, AttendanceReport.class);
    }

    public AttendanceReport update(AttendanceReport report) {
        restTemplate.put(BASE_URL + "/update", report);
        return report;
    }

    public void delete(String id) {
        restTemplate.delete(BASE_URL + "/delete/" + id);
    }

    public List<AttendanceReport> getAll() {
        ResponseEntity<AttendanceReport[]> response =
                restTemplate.getForEntity(BASE_URL + "/all", AttendanceReport[].class);
        return Arrays.asList(response.getBody());
    }
}
