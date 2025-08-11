package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Student;
import za.ac.cput.repository.IStudentRepository;

import java.util.List;
@Service
public class StudentService implements IStudentService {

    private final IStudentRepository repository;

    @Autowired
    public StudentService(IStudentRepository repository) {
        this.repository = repository;
    }

    @Override
    public Student create(Student newStudent) {
        return this.repository.save(newStudent);
    }

    @Override
    public Student read(String id) {
        return this.repository.findById(id).orElse(null);
    }

    @Override
    public Student update(Student student) {
        return this.repository.save(student);
    }

    @Override
    public void delete(String id) {
        this.repository.deleteById(id);
    }

    @Override
    public List<Student> getAll() {
        return repository.findAll();
    }
    @Override
    public Student findById(String id) {
        return read(id);
    }
}
