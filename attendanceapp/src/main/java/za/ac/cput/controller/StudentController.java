package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Student;
import java.util.List;
import za.ac.cput.service.StudentService;

   @RestController
   @RequestMapping("/attendanceapp/students")
   public class StudentController {

       @Autowired
       private StudentService studentService;

       @PostMapping ("/create")
       public Student createStudent(@RequestBody Student student) {
           return studentService.create(student);
       }

       @GetMapping("read/{studentId}")
       public Student getStudent(@PathVariable String studentId) {
           return studentService.read(studentId);
       }
         @PutMapping("/update")
         public Student updateStudent(@RequestBody Student student) {
              return studentService.update(student);
       }
         @DeleteMapping("/delete/{studentId}")
            public void deleteStudent(@PathVariable String studentId) {
                studentService.delete(studentId);
            }
         @GetMapping("/getAll")
            public List<Student> getAllStudents() {
                return studentService.getAll();
            }
            @GetMapping("/findById/{id}")
            public Student findById(@PathVariable String id) {
                return studentService.findById(id);
            }
}
