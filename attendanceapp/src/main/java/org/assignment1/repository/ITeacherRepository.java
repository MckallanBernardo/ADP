package org.assignment1.repository;

import org.assignment1.domain.Teacher;

import java.util.ArrayList;

/* ITeacherRepository.java
ITeacherRepository interface
Author: Aidan Coetzee (230563724)
Date: 27 March 2025
 */

public interface ITeacherRepository extends IRepository<Teacher, String> {
    ArrayList<Teacher> findByTeacherName(String teacherID);

}
