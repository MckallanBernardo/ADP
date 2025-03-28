package org.assignment1.factory;

import org.assignment1.domain.Student;
import java.time.LocalDate;

/* StudentFactory.java
Student Factory class
Author: Nur Amod (230543502)
Date: 18 March 2025
*/

public class StudentFactory {
    public static Student createStudent(String studentID, String firstName, String lastName, LocalDate dateOfBirth) {
        return new Student.Builder()
                .setStudentID(studentID)
                .setFirstName(firstName)
                .setLastName(lastName)
                .setDateOfBirth(dateOfBirth)
                .build();
    }
}

