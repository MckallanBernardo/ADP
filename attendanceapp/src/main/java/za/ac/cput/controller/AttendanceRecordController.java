package za.ac.cput.controller;

import org.springframework.web.bind.annotation.*;
import org.w3c.dom.stylesheets.LinkStyle;
import za.ac.cput.domain.AttendanceRecord;
import za.ac.cput.service.AttendanceRecordService;

import java.util.List;

@RestController
@RequestMapping("/attendancerecord")
public class AttendanceRecordController {

    private static AttendanceRecordService service;

    public AttendanceRecordController(AttendanceRecordService service){
        this.service = service;
    }

    @PostMapping("create")
    public AttendanceRecord create(@RequestBody AttendanceRecord attendanceRecord){
        return service.create(attendanceRecord);
    }

    @GetMapping("read{id}")
    public AttendanceRecord read(@PathVariable String id){
        return service.read(id);
    }

    @PutMapping("/update")
    public AttendanceRecord update(@RequestBody AttendanceRecord attendanceRecord){
        return service.update(attendanceRecord);
    }

    @GetMapping("/getAll")
    public List<AttendanceRecord> getAll(){
        return service.getAll();
    }

///    #NOTE: I HAVE TO GET TO THE DELETE
//    @DeleteMapping("/delete")
//    public boolean delete(@PathVariable String id){
//        if (service.delete(id))
//            return false;
//        return false;
//    }
}
