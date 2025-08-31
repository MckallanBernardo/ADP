package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.AttendanceReport;
import za.ac.cput.service.AttendanceReportService;

import java.util.List;

@RestController
@RequestMapping("/api/attendance-reports")
public class AttendanceReportController {

    private AttendanceReportService service;

    @Autowired
    public AttendanceReportController(AttendanceReportService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public ResponseEntity<AttendanceReport> create(@RequestBody AttendanceReport report) {
        AttendanceReport created = service.create(report);
        return ResponseEntity.ok(created);
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<AttendanceReport> read(@PathVariable String id) {
        AttendanceReport report = service.read(id);
        if(report == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(report);
    }

    @PutMapping("/update")
    public ResponseEntity<AttendanceReport> update(@RequestBody AttendanceReport report) {
        AttendanceReport updated = service.update(report);
        if(updated == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        if(service.delete(id))
            return ResponseEntity.ok().build();
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<AttendanceReport>> getAll() {
        List<AttendanceReport> reports = service.getAll();
        return ResponseEntity.ok(reports);
    }
}