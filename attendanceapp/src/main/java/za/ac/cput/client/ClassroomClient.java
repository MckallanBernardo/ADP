package za.ac.cput.client;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import za.ac.cput.domain.Classroom;

import java.util.Arrays;
import java.util.List;

public class ClassroomClient {
    private static final String BASE_URL = "http://localhost:8080/attendanceapp/classrooms";
    private final RestTemplate restTemplate;

    public ClassroomClient() {
        this.restTemplate = new RestTemplate();
    }

    // CREATE
    public Classroom create(Classroom classroom) {
        return restTemplate.postForObject(
                BASE_URL + "/create",
                classroom,
                Classroom.class
        );
    }

    // READ by classroomId
    public Classroom read(String classroomId) {
        return restTemplate.getForObject(
                BASE_URL + "/read/" + classroomId,
                Classroom.class
        );
    }

    // UPDATE
    public Classroom update(Classroom classroom) {
        restTemplate.put(BASE_URL + "/update", classroom);
        return classroom; // return the updated classroom
    }

    // DELETE
    public void delete(String classroomId) {
        restTemplate.delete(BASE_URL + "/delete/" + classroomId);
    }

    // GET ALL
    public List<Classroom> getAll() {
        ResponseEntity<Classroom[]> response =
                restTemplate.getForEntity(BASE_URL + "/getAll", Classroom[].class);

        return Arrays.asList(response.getBody());
    }

    // FIND BY ID (extra endpoint in your controller)
    public Classroom findById(String id) {
        return restTemplate.getForObject(
                BASE_URL + "/findById/" + id,
                Classroom.class
        );
    }
}
