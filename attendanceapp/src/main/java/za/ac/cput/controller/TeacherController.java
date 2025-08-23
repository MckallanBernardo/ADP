package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Teacher;
import za.ac.cput.service.TeacherService;

import java.util.List;

@RestController
@RequestMapping("/attendanceapp/teacher")
public class TeacherController {

    private TeacherService service;

    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.service = teacherService;
    }
    @PostMapping("/create")
    public Teacher create(@RequestBody Teacher teacher) {
        return service.create(teacher);
    }
    @GetMapping("/read/{teacherID}")
    public Teacher read(@PathVariable String teacherID) {
        return service.read(teacherID);
    }
    @PutMapping("/update")
    public Teacher update(@RequestBody Teacher teacher) {
        return service.update(teacher);
    }
    @DeleteMapping("/delete/{teacherID}")
    public void delete(@PathVariable String teacherID) {
        service.delete(teacherID);
    }
    @GetMapping("/getAll")
    public List<Teacher> getAll() {
        return service.getAll();
    }
    @GetMapping("/findById/{id}")
    public Teacher findById(@PathVariable String id){
        return service.findById(id);
    }



}
