package org.assignment1.repository;

import org.assignment1.domain.Student;
import java.util.ArrayList;

/* IStudentRepository.java
IStudentRepository interface
Author: Nur Amod (230543502)
Date: 27 March 2025
*/

public interface IStudentRepository extends IRepository<Student, String> {
    ArrayList<Student> findByLastName(String lastName);
}
