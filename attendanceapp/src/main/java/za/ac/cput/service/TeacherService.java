package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Teacher;
import za.ac.cput.repository.ITeacherRepository;

import java.util.List;


@Service
    public class TeacherService implements ITeacherService {

        private final ITeacherRepository repository;

        @Autowired
        public TeacherService(ITeacherRepository repository) {
            this.repository = repository;
        }

        @Override
        public Teacher create(Teacher newTeacher) {
            return this.repository.save(newTeacher);
        }

        @Override
        public Teacher read(String id) {
            return this.repository.findById(id).orElse(null);
        }

        @Override
        public Teacher update(Teacher teacher) {
            return this.repository.save(teacher);
        }

        @Override
        public void delete(String id) {
            this.repository.deleteById(id);

        }

        @Override
        public List<Teacher> getAll() {
            return repository.findAll();
        }

        @Override
        public Teacher findById(String id) {
            return read(id);
        }
    }

