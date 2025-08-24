package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Classroom;
import za.ac.cput.service.ClassroomService;

import java.util.List;

@RestController
@RequestMapping("/attendanceapp/classrooms")
public class ClassroomController {

    private ClassroomService service;

    @Autowired
    public ClassroomController(ClassroomService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public Classroom create(@RequestBody Classroom classroom) {
        return service.create(classroom);
    }

    @GetMapping("/read/{classroomId}")
    public Classroom read(@PathVariable String classroomId) {
        return service.read(classroomId);
    }

    @PutMapping("/update")
    public Classroom update(@RequestBody Classroom classroom) {
        return service.update(classroom);
    }

    @DeleteMapping("/delete/{classroomId}")
    public void delete(@PathVariable String classroomId) {
        service.delete(classroomId);
    }

    @GetMapping("/getAll")
    public List<Classroom> getAll() {
        return service.getAll();
    }

    @GetMapping("/findById/{id}")
    public Classroom findById(@PathVariable String id) {
        return service.findById(id);
    }
}
