package za.ac.cput.client;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import za.ac.cput.domain.Student;

import java.util.Arrays;
import java.util.List;

public class StudentClient {
    private static final String BASE_URL = "http://localhost:8080/attendanceapp/students";
    private final RestTemplate restTemplate;

    public StudentClient() {
        this.restTemplate = new RestTemplate();
    }

    public Student create(Student student) {
        ResponseEntity<Student> response = restTemplate.postForEntity(
                BASE_URL + "/create", student, Student.class);
        return response.getBody();
    }

    public Student read(String id) {
        return restTemplate.getForObject(BASE_URL + "/read/" + id, Student.class);
    }

    public Student update(Student student) {
        restTemplate.put(BASE_URL + "/update", student);
        return student;
    }

    public void delete(String id) {
        restTemplate.delete(BASE_URL + "/delete/" + id);
    }

    public List<Student> getAll() {
        ResponseEntity<Student[]> response =
                restTemplate.getForEntity(BASE_URL + "/getAll", Student[].class);
        return Arrays.asList(response.getBody());
    }
}
