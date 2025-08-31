package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.stylesheets.LinkStyle;
import za.ac.cput.domain.AttendanceRecord;
import za.ac.cput.service.AttendanceRecordService;

import java.util.List;

@RestController
@RequestMapping("/attendancerecord")
@CrossOrigin
public class AttendanceRecordController {

    private final AttendanceRecordService service;

    @Autowired
    public AttendanceRecordController(AttendanceRecordService service){
        this.service = service;
    }

    @PostMapping("/create")
    public AttendanceRecord create(@RequestBody AttendanceRecord attendanceRecord){
        return service.create(attendanceRecord);
    }

    @GetMapping("/read/{id}")
    public AttendanceRecord read(@PathVariable String id){
        return service.read(id);
    }

    @PutMapping("/update")
    public AttendanceRecord update(@RequestBody AttendanceRecord attendanceRecord){
        return service.update(attendanceRecord);
    }

    @DeleteMapping("/delete/{id}")
    public boolean delete(@PathVariable String id){
        if (service.delete(id))
            return false;
        return true;
    }

    @GetMapping("/getAll")
    public List<AttendanceRecord> getAll(){
        return service.getAll();
    }

    @GetMapping("/search")
    public java.util.List<AttendanceRecord> search(@RequestParam String studentId, @RequestParam(required = false) String start, @RequestParam(required = false) String end) {
        java.time.LocalDate s = (start == null || start.isBlank()) ? null : java.time.LocalDate.parse(start);
        java.time.LocalDate e = (end == null || end.isBlank()) ? null : java.time.LocalDate.parse(end);
        return service.findByStudentAndDateRange(studentId, s, e);
    }
}
