package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Classroom;
import za.ac.cput.repository.IClassroomRepository;

import java.util.List;

@Service
public class ClassroomService implements IClassroomService{

    private final IClassroomRepository repository;

    @Autowired
    public ClassroomService(IClassroomRepository repository) {
        this.repository = repository;
    }


    @Override
    public Classroom create(Classroom newClassroom) {
        return this.repository.save(newClassroom);
    }

    @Override
    public Classroom read(String id) {
        return this.repository.findById(id).orElse(null);
    }

    @Override
    public Classroom update(Classroom classroom) {
        return this.repository.save(classroom);
    }

    @Override
    public void delete(String id) {
        this.repository.deleteById(id);
    }

    @Override
    public List<Classroom> getAll() {
        return repository.findAll();
    }

    @Override
    public Classroom findById(String id) {
        return read(id);
    }
}
