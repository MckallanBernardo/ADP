package org.assignment1.repository;

import org.assignment1.domain.Classroom;
import java.util.ArrayList;

/* IClassroomRepository.java
IClassroomRepository Interface
Author: Mckallan Bernardo (219018243)
Date: 27 March 2025
*/

public interface IClassroomRepository extends IRepository<Classroom, String> {
    ArrayList<Classroom> findByClassName(String className);
}
