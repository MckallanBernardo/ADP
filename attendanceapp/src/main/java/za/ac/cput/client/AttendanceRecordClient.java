package za.ac.cput.client;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import za.ac.cput.domain.AttendanceRecord;

import java.util.Arrays;
import java.util.List;

public class AttendanceRecordClient {
    private static final String BASE_URL = "http://localhost:8080/attendancerecord";
    private final RestTemplate restTemplate = new RestTemplate();

    public AttendanceRecord create(AttendanceRecord record) {
        return restTemplate.postForObject(BASE_URL + "/create", record, AttendanceRecord.class);
    }

    public AttendanceRecord read(String id) {
        return restTemplate.getForObject(BASE_URL + "/read/" + id, AttendanceRecord.class);
    }

    public AttendanceRecord update(AttendanceRecord record) {
        restTemplate.put(BASE_URL + "/update", record);
        return record;
    }

    public void delete(String id) {
        restTemplate.delete(BASE_URL + "/delete/" + id);
    }

    public List<AttendanceRecord> getAll() {
        ResponseEntity<AttendanceRecord[]> response =
                restTemplate.getForEntity(BASE_URL + "/getAll", AttendanceRecord[].class);
        return Arrays.asList(response.getBody());
    }

    public java.util.List<AttendanceRecord> findByStudentAndDateRange(String studentId, String start, String end){
        String url = BASE_URL + "/search?studentId=" + studentId;
        if(start != null && !start.isBlank()) url += "&start=" + start;
        if(end != null && !end.isBlank()) url += "&end=" + end;
        ResponseEntity<AttendanceRecord[]> response =
                restTemplate.getForEntity(url, AttendanceRecord[].class);
        return java.util.Arrays.asList(response.getBody());
    }

}
