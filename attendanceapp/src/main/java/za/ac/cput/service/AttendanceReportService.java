package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.AttendanceReport;
import za.ac.cput.repository.IAttendanceReportRepository;

import java.util.List;

@Service
public class AttendanceReportService implements IAttendanceReportService {

    private final IAttendanceReportRepository repository;

    @Autowired
    public AttendanceReportService(IAttendanceReportRepository repository) {
        this.repository = repository;
    }

    @Override
    public AttendanceReport create(AttendanceReport entity) {
        return repository.save(entity);
    }

    @Override
    public AttendanceReport read(String id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public AttendanceReport update(AttendanceReport entity) {
        if(repository.existsById(entity.getReportID())) {
            return repository.save(entity);
        }
        return null;
    }

    @Override
    public boolean delete(String id) {
        if(repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<AttendanceReport> getAll() {
        return repository.findAll();
    }

    @Override
    public AttendanceReport findById(String id) {
        return repository.findById(id).orElse(null);
    }
}
