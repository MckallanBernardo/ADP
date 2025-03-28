package org.assignment1.repository;

import org.assignment1.domain.Student;
import java.util.ArrayList;
import java.util.Optional;

/* StudentRepository.java
   Student repository class
   Author: Nur Amod (230543502)
   Date: 27 March 2025
*/

public class StudentRepository implements IStudentRepository {
    private final ArrayList<Student> students = new ArrayList<>();

    @Override
    public Student create(Student student) {
        students.add(student);
        return student;
    }

    @Override
    public Optional<Student> findById(String studentID) {
        return students.stream().filter(s -> s.getStudentID().equals(studentID)).findFirst();
    }

    @Override
    public ArrayList<Student> findAll() {
        return new ArrayList<>(students);
    }

    @Override
    public Student update(Student updatedStudent) {
        delete(updatedStudent.getStudentID());
        students.add(updatedStudent);
        return updatedStudent;
    }

    @Override
    public void delete(String studentID) {
        students.removeIf(s -> s.getStudentID().equals(studentID));
    }

    @Override
    public ArrayList<Student> findByLastName(String lastName) {
        ArrayList<Student> result = new ArrayList<>();
        for (Student student : students) {
            if (student.getLastName().equals(lastName)) {
                result.add(student);
            }
        }
        return result;
    }
}
