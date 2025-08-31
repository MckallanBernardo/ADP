package za.ac.cput.client;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import za.ac.cput.domain.Teacher;

import java.util.Arrays;
import java.util.List;

@Component
public class TeacherClient {

    private final RestTemplate rest = new RestTemplate();
    private final String base = "http://localhost:8080/attendanceapp/teacher";

    public Teacher create(Teacher teacher) {
        return rest.postForObject(base + "/create", teacher, Teacher.class);
    }

    public Teacher read(String teacherID) {
        return rest.getForObject(base + "/read/" + teacherID, Teacher.class);
    }

    public Teacher update(Teacher teacher) {
        rest.put(base + "/update", teacher);
        return teacher;
    }

    public void delete(String teacherID) {
        rest.delete(base + "/delete/" + teacherID);
    }

    public List<Teacher> getAll() {
        ResponseEntity<Teacher[]> response = rest.getForEntity(base + "/getAll", Teacher[].class);
        return Arrays.asList(response.getBody() != null ? response.getBody() : new Teacher[0]);
    }

    public Teacher findById(String id) {
        return rest.getForObject(base + "/findById/" + id, Teacher.class);
    }

    /** Simple "auth": lastname = username, teacherID = password */
    public boolean authenticate(String lastName, String teacherID) {
        Teacher t = read(teacherID);
        return t != null && lastName != null && lastName.equalsIgnoreCase(t.getLastName());
    }
}
