package org.assignment1.repository;


import org.assignment1.domain.Classroom;
import org.assignment1.repository.IClassroomRepository;

import java.util.ArrayList;
import java.util.Optional;

/* ClassroomRepository.java
ClassroomRepository class
Author: Mckallan Bernardo (219018243)
Date: 27 March 2025
*/

public class ClassroomRepository implements IClassroomRepository {
    private final ArrayList<Classroom> classrooms = new ArrayList<>();

    @Override
    public Classroom create(Classroom classroom) {
        classrooms.add(classroom);
        return classroom;
    }

    @Override
    public Optional<Classroom> findById(String id) {
        return classrooms.stream().filter(c -> c.getClassroomID().equals(id)).findFirst();
    }

    @Override
    public ArrayList<Classroom> findAll() {
        return new ArrayList<>(classrooms);
    }

    @Override
    public Classroom update(Classroom updatedClassroom) {
        delete(updatedClassroom.getClassroomID());
        classrooms.add(updatedClassroom);
        return updatedClassroom;
    }

    @Override
    public void delete(String id) {
        classrooms.removeIf(c -> c.getClassroomID().equals(id));
    }

    @Override
    public ArrayList<Classroom> findByClassName(String className) {
        ArrayList<Classroom> result = new ArrayList<>();
        for (Classroom c : classrooms) {
            if (c.getClassName().equalsIgnoreCase(className)) {
                result.add(c);
            }
        }
        return result;
    }
}