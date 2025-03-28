package org.assignment1.repository;

import org.assignment1.domain.Classroom;
import org.assignment1.domain.Teacher;

import java.util.ArrayList;
import java.util.Optional;

public class TeacherRepository implements ITeacherRepository{

    private final ArrayList<Teacher> teachers = new ArrayList<Teacher>();

    @Override
    public Teacher create(Teacher teacher){
    teachers.add(teacher);
    return teacher;
    }

    @Override
    public Optional<Teacher> findById(String id) {
        return teachers.stream().filter(c -> c.getTeacherID().equals(id)).findFirst();
    }

    @Override
    public ArrayList<Teacher> findAll() {
        return new ArrayList<>(teachers);
    }

    @Override
    public Teacher update(Teacher updateTeacher) {
        delete(updateTeacher.getTeacherID());
        teachers.add(updateTeacher);
        return updateTeacher;
    }

    @Override
    public void delete(String id) {
        teachers.removeIf(c -> c.getTeacherID().equals(id));

    }

    @Override
    public ArrayList<Teacher> findByTeacherName(String teacherID) {
        ArrayList<Teacher> result = new ArrayList<>();
        for (Teacher c : teachers) {
            if (c.getTeacherID().equalsIgnoreCase(teacherID)) {
                result.add(c);
            }
        }
        return result;

    }


}
