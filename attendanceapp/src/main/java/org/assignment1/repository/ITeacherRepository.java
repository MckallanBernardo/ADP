package org.assignment1.repository;

import org.assignment1.domain.Teacher;

import java.util.ArrayList;

public interface ITeacherRepository extends IRepository<Teacher, String> {
    ArrayList<Teacher> findByTeacherName(String teacherID);

}
